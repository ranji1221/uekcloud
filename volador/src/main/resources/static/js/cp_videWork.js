$(function(){

	// 视频侧边笔记与章节显示
	let work = $('.cp_work');
	let flag =true;
    let tab = $(".cp_cate span");
    let cp_tab = $('.cp_work .cp_tab');
    let old ="";
    let flag1 = true;
	work.click(function(){
		return false;
	});
	$('.cp_videoBox').click(function(){
		work.css({'right':'-352px'});
        old='';
        flag1=true;
	});
	tab.click(function(){
		let index = $(this).index();
		tab.removeClass('active');
		$(this).addClass('active');
		cp_tab.css({'display':'none'}).eq(index).css({'display':'block'});
        if(flag1){
            work.css({right:'0px'});
            console.log(100);
            flag1=false;
            return;
        }
        if(old!=this&&old!=""){
            old =this;
            return ;
        }
        if(flag){
            work.css({right:'0px'});
            old =this;
        }else{
            old='';
            flag1=true;
            work.css({right:'-352px'});
        }
        flag=!flag;
       
        return false;
	});


	// 视频的基本配置
	
	var videoObject = {
							container: '#video', //容器的ID或className
							variable: 'player',//播放函数名称
							// poster:'material/poster.jpg',//封面图片
							//flashplayer:true,
							video:'video/王菲 - 匆匆那年.mp4'//视频地址
					};
	var player = new ckplayer(videoObject);

	// 页面刷新状态下不会改变视频的播放进度设置
	let videoData = localStorage.getItem('video')?JSON.parse(localStorage.getItem('video')):'';
	let video = $('video')[0];
	if(videoData){
		if(videoData.src==video.src){
			video.currentTime=videoData.time;
			video.muted='muted'
			video.pause();
		}
	}else{

		let obj = {src:video.src,time:0}
		localStorage.setItem('video',JSON.stringify(obj));	
	}
	video.ontimeupdate=function(){
		let obj = JSON.parse(localStorage.getItem('video'));
		obj.time = this.currentTime;
		localStorage.setItem('video',JSON.stringify(obj));	
	}



	



	// 笔记、评论、章节的切换
	$('.cp_purchase .cp_tabBox>li').click(function(){
		let num = $(this).index();
		let content = $('.cp_tabCon .container .col_left');
		$('.cp_purchase .cp_tabBox>li').removeClass('active').eq(num).addClass('active');
		if(num==0){
			
			content.html(`
				<!--添加评论列表开始-->
				<div class="wzq_show_comment_add">
				    <form class="wzq_add_form wzq_clear">
				        <div class="wzq_clear">
				            <div class="wzq_user_img">
				                <img src="images/wzq_user_img.jpg" alt="">
				            </div>
				            <div class="wzq_textarea">
				                <textarea name="" id="wzq_textarea" onfocus="if(value=='快来发布评论吧...') {value=''}" onblur="if(value=='') {value='快来发布评论吧...'}">快来发布评论吧...</textarea>
				            </div>
				        </div>
				        <div class="wzq_icon_add">
				            <span data-icon-flag="true">添加表情</span>
				            <div class="wzq_icon_box">

				            </div>
				        </div>
				        <div class="wzq_add_submit"><input type="button" value="发表评论"></div>
				    </form>
				</div>
				<!--添加评论列表开结束-->

				<!--评论列表开始-->
				<ul class="wzq_show_comment_list wqf_comment">
				    <li class="wzq_show_comment_item">
				        <div class="wzq_clear">
				            <div class="wzq_user_img">
				                <img src="images/wzq_user_img.jpg" alt="">
				            </div>
				            <!--评论区内容开始，回复评论区时候，添加此节点-->
				            <div class="wzq_comment_item_info">
				                <h6 class="wzq_comment_item_title">也入耳朵</h6>
				                <span class="wzq_comment_item_time">5小时前</span>
				                <div class="wzq_comment_item_des">
				                    <p class="hidd_more">向手写大佬低头～向手写大佬低头～向手写大佬低头～效果图？太牛了吧。什么软件做的？？？？效果图？太牛了吧。什么软件做的？？？？</p>
				                    <div class="wzq_comment_reply">
				                        <span class="wzq_jubao"><i class="fy_icon">&#xe652;</i>举报</span>
				                        <span title="回复" class="wzq_comment_replys" data-flag="true"><i class="fy_icon">&#xe679;</i></span>
				                        <span class="wzq_comment_zan wzq_comment_zanend"><i class="fy_icon">&#xe6b3;</i></span>
				                    </div>
				                </div>
				            </div>
				            <!--评论区内容结束-->
				        </div>
				        <!--评论区回复表单开始-->
				        <div class="wzq_reply">
				            <form action="" class="wzq_reply_form">
				                <div class="wzq_reply_text">
				                    <span>回复</span>
				                    <em class="wzq_reply_user hidden_one">小灰灰灰</em>
				                    <textarea class="wzq_reply_textarea" name="" onfocus="if(value=='请回复...') {value=''}" onblur="if(value=='') {value='请回复...'}">请回复...</textarea>
				                </div>
				                <div class="wzq_clear">
				                    <div class="wzq_icon_add">
				                        <span data-icon-flag="true">添加表情</span>
				                        <div class="wzq_icon_box">

				                        </div>
				                    </div>
				                    <div class="wzq_reply_submit"><input type="submit" value="回复"></div>
				                </div>
				            </form>
				        </div>
				        <!--评论区回复表单结束-->
				    </li>
				    <li class="wzq_show_comment_item">
				        <div class="wzq_clear">
				            <div class="wzq_user_img">
				                <img src="images/wzq_user_img.jpg" alt="">
				            </div>
				            <!--评论区内容开始，回复评论区时候，添加此节点-->
				            <div class="wzq_comment_item_info">
				                <h6 class="wzq_comment_item_title">也入耳朵</h6>
				                <span class="wzq_comment_item_time">5小时前</span>
				                <div class="wzq_comment_item_des">
				                    <p class="hidd_more">向手写大佬低头～向手写大佬低头～向手写大佬低头～效果图？太牛了吧。什么软件做的？？？？效果图？太牛了吧。什么软件做的？？？？</p>
				                    <div class="wzq_comment_reply">
				                        <span class="wzq_jubao"><i class="fy_icon">&#xe652;</i>举报</span>
				                        <span title="回复" class="wzq_comment_replys" data-flag="true"><i class="fy_icon">&#xe679;</i></span>
				                        <span class="wzq_comment_zan wzq_comment_zanend"><i class="fy_icon">&#xe6b3;</i></span>
				                    </div>
				                </div>
				            </div>
				            <!--评论区内容结束-->
				        </div>
				        <!--评论区回复表单开始-->
				        <div class="wzq_reply">
				            <form action="" class="wzq_reply_form">
				                <div class="wzq_reply_text">
				                    <span>回复</span>
				                    <em class="wzq_reply_user hidden_one">小灰灰灰</em>
				                    <textarea class="wzq_reply_textarea" name="" onfocus="if(value=='请回复...') {value=''}" onblur="if(value=='') {value='请回复...'}">请回复...</textarea>
				                </div>
				                <div class="wzq_clear">
				                    <div class="wzq_icon_add">
				                        <span data-icon-flag="true">添加表情</span>
				                        <div class="wzq_icon_box">

				                        </div>
				                    </div>
				                    <div class="wzq_reply_submit"><input type="submit" value="回复"></div>
				                </div>
				            </form>
				        </div>
				        <!--评论区回复表单结束-->
				    </li>
				</ul>
				<!--评论列表结束-->
			`);
		}else if(num==1){
			
			content.html(`
				<ul class='cp_note'>
                    <li>
                        <div class="left"><span>2018年</span><br><span>3月15日</span>
                        </div>
                        <div class="middle">
                            <div class="line"></div>
                        </div>
                        <div class="right">
                            <p class='hidden_one'>在我的BGM中没人能够打败我</p>
                            <p class='hidd_more2' >反向生成失败 RuntimeException 和 报 tk.mybatis.mapper.generator.MapperPlugin 类为找到的错误。有遇到这个问题的朋友吗？在下愚钝，未能解决...</p>
                            <p>
                                源自<span>1-1Python课程初探</span>
                            </p>
                        </div>
                    </li>
                     <li class="active">
                        <div class="left"><span>2018年</span><br><span>3月15日</span>
                        </div>
                        <div class="middle">
                            <div class="line"></div>
                        </div>
                        <div class="right">
                            <p class='hidden_one'>在我的BGM中没人能够打败我</p>
                            <p class='hidd_more2' >反向生成失败 RuntimeException 和 报 tk.mybatis.mapper.generator.MapperPlugin 类为找到的错误。有遇到这个问题的朋友吗？在下愚钝，未能解决...</p>
                            <p>
                                源自<span>1-1Python课程初探</span>
                            </p>
                        </div>
                    </li>
                     <li>
                        <div class="left"><span>2018年</span><br><span>3月15日</span>
                        </div>
                        <div class="middle">
                        </div>
                        <div class="right">
                            <p class='hidden_one'>在我的BGM中没人能够打败我</p>
                            <p class='hidd_more2' >反向生成失败 RuntimeException 和 报 tk.mybatis.mapper.generator.MapperPlugin 类为找到的错误。有遇到这个问题的朋友吗？在下愚钝，未能解决...</p>
                            <p class='hidden_one' >
                                源自<span>1-1Python课程初探</span>
                            </p>
                        </div>
                    </li>
                </ul>
                <!-- 查看更多笔记开始 -->
                <div class="wzq_show_comment_more">
                    <div class="wzq_show_more">
                        <span>查看更多评论</span>
                        <i class="fy_icon">&#xe98f;</i>
                    </div>
                </div> 
				`);

		}else if(num==2){
			content.html(`
				<ul class="wqf_chapter_list"> 
	                <li>
	                    <h2 class="title">
	                        <i class="icon">=</i>
	                        <span>第一章 SpringBoot简介</span>
	                    </h2>
	                    <ul class="list">
	                        <li>
	                            <a href="javascript:;">
	                                <i class="fy_icon">&#xe629;</i>
	                                1-1 SpringBoot简介
	                                <span class="time">20:20</span>
	                                <span class="tishi">免费试听5分钟 <i>→</i></span>
	                            </a>
	                            <p class="hidd_more">主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。</p>
	                        </li>
	                    </ul>
	                </li>
	                <li>
	                    <h2 class="title">
	                        <i class="icon">=</i>
	                        <span>第二章 SpringBoot简介</span>
	                    </h2>
	                    <ul class="list">
	                        <li>
	                            <a href="javascript:;">
	                                <i class="fy_icon">&#xe629;</i>
	                                1-1 SpringBoot简介
	                                <span class="time">20:20</span>
	                                <span class="tishi">免费试听5分钟 <i>→</i></span>
	                            </a>
	                            <p class="hidd_more">主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。</p>
	                        </li>
	                        <li>
	                            <a href="javascript:;">
	                                <i class="fy_icon">&#xe629;</i>
	                                1-1 SpringBoot简介
	                                <span class="time">20:20</span>
	                                <span class="tishi">免费试听5分钟 <i>→</i></span>
	                            </a>
	                            <p class="hidd_more">主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。</p>
	                        </li>
	                        <li>
	                            <a href="javascript:;">
	                                <i class="fy_icon">&#xe629;</i>
	                                1-1 SpringBoot简介
	                                <span class="time">20:20</span>
	                                <span class="tishi">免费试听5分钟 <i>→</i></span>
	                            </a>
	                            <p class="hidd_more">主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。</p>
	                        </li>
	                    </ul>
	                </li>
	                <li>
	                    <h2 class="title">
	                        <i class="icon">=</i>
	                        <span>第三章 SpringBoot简介</span>
	                    </h2>
	                    <ul class="list">
	                        <li>
	                            <a href="javascript:;">
	                                <i class="fy_icon">&#xe629;</i>
	                                1-1 SpringBoot简介
	                                <span class="time">20:20</span>
	                                <span class="tishi">免费试听5分钟 <i>→</i></span>
	                            </a>
	                            <p class="hidd_more">主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。</p>
	                        </li>
	                        <li>
	                            <a href="javascript:;">
	                                <i class="fy_icon">&#xe629;</i>
	                                1-1 SpringBoot简介
	                                <span class="time">20:20</span>
	                                <span class="tishi">免费试听5分钟 <i>→</i></span>
	                            </a>
	                            <p class="hidd_more">主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。主要讲解常用色彩属性，以及网络上比较流行的一些配色方法总结，未来流行配色趋势。</p>
	                        </li>
	                    </ul>
	                </li>
            	</ul>
				`);
			$('.wqf_chapter_list ul').hide();
		    $('.wqf_chapter_list ul>li>p').hide();
		    $('.wqf_chapter_list ').on('click','.title,li>a ',function(){   
		        let index = $(this).index('.wqf_chapter_list .title');
		        if($(this).hasClass('title')){
		            $('.wqf_chapter_list ul').eq(index).slideToggle();
		        }else{
		            $(this).next().slideToggle();
		        }
    })
		}
	})




})
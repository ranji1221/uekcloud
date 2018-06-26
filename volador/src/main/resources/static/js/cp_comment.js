$(function(){
    function getComment(){
            let content = $('.col_left .cp_commentBox');
            
            let wzq_page = $('.col_left .wzq_page');
            let tips =  $('.cp_tabCon .tips');
            tips.css('display','block');
            console.log(courseId)
            $.ajax({
                url:"courseCommentList",
                type:'post',
                data:{courseId,userId},
                dataType:'json',
                success:function(data){
                    data = data.data;
                    tips.html('全部评论 <span>'+data.commentAndReply.length+'</span>');
                    let comment=$('<ul class="wzq_show_comment_list wqf_comment">');
                    content.html(`
                            <div class="wzq_show_comment_add">
                                <form class="wzq_add_form wzq_clear">
                                    <div class="wzq_clear">
                                        <div class="wzq_user_img">
                                            <img src="${data.userInfo.head_image}" alt="">
                                        </div>
                                        <div class="wzq_textarea">
                                            <textarea name="" id="wzq_textarea" onfocus="if(value=='快来发布评论吧...') {value=''}" onblur="if(value=='') {value='快来发布评论吧...'}">快来发布评论吧...</textarea>
                                        </div>
                                    </div>
                                    <div class="wzq_icon_add">
                                        <div class="wzq_icon_box">

                                        </div>
                                    </div>
                                    <div class="wzq_add_submit"><input type="button" value="发表评论"></div>
                                </form>
                            </div>
                    `);
                    if(data.commentAndReply.length<5&&data.commentAndReply.length!=0){
                        
                        data.commentAndReply.forEach(function(val,n){
                            var str = "";
                            str+=`<div class="wzq_clear">
                                    <div class="wzq_user_img">
                                        <img src="${val.comment.head_image}" alt="">
                                    </div>
                                    <!--评论区内容开始，回复评论区时候，添加此节点-->
                                    <div class="wzq_comment_item_info">
                                        <h6 class="wzq_comment_item_title">${val.comment.nickName}</h6>
                                        <span class="wzq_comment_item_time">${getDate(val.comment.createTime)}</span>
                                        <div class="wzq_comment_item_des clear_both">
                                            <p class="hidd_more">${val.comment['content']}</p>
                                            <div class="wzq_comment_reply">
                                                <span title="回复" class="wzq_comment_replys" data-flag="true" replyusername="${val.comment.nickName}" replyUserId="${val.comment.id}"><i class="fy_icon">&#xe679;</i></span>
                                                <span class="wzq_comment_zan wzq_comment_zanend" commentId="${val.comment.id}"><i class="fy_icon">&#xe6b3;</i> ${val.comment.good}</span>
                                            </div>
                                        </div>`
                            val.reply.forEach((ele,i)=>{
                                str+=`<div class="wqf_reply">
                                    <span uid="${ele.userId}">${ele.userName} 回复 ${ele.replyusername==null?'':'@'+ele.replyusername}：${ele.reply}</span>
                                    <div class="wzq_comment_reply">
                                        <span title="回复" class="wzq_comment_replys" data-flag="true" replyusername="${ele.userName}" commentId="${ele.commentId}" replyUserId="${ele.userId}"><i class="fy_icon">&#xe679;</i></span>
                                    </div>
                                </div>`   // wqf_reply 结束
                            }) 

                                // wzq_comment_item_info 结束    wzq_clear 结束
                            str+=`</div>  
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
                                                <div class="wzq_icon_box">

                                                </div>
                                            </div>
                                            <div class="wzq_reply_submit"><input type="button" value="回复"></div>
                                        </div>
                                    </form>
                                </div>
                                <!--评论区回复表单结束-->
                                `
                            $('<li class="wzq_show_comment_item" index='+n+'>').html(str).appendTo(comment);
                        })
                        comment.appendTo(content);
                    }else if(data.commentAndReply.length==0){
                        wzq_page.css('display','none');
                        comment.html(`
                        <div class="noComment">
                            <div class="con">
                             <img src="images/cp_13.png" alt="">
                             <p>
                                还没有人<a href="#">评论</a><br>我来成为第一个提问的人
                             </p>
                            </div>
                        </div>`);
                        comment.appendTo(content);
                        tips.css('display','none');
                    }else{
                        
                        let num = Math.ceil(data.commentAndReply.length/5); 
                        let next = $('.wzq_page_next',wzq_page);
                        wzq_page.css('display','block');
                        $('.cp_num',wzq_page).remove();
                        // 控制分页的呈现
                        if(num<10){
                            for(let n=1;n<=num;n++){
                                if(n==1){
                                    next.before($('<a class="cp_num wzq_page_active" >').html(n));
                                }else{
                                    next.before($('<a class="cp_num" >').html(n));
                                }
                            }
                        }else{
                            for(let n=1;n<=10;n++){
                                if(n==1){
                                    next.before($('<a class="cp_num wzq_page_active" >').html(n));  
                                }else{
                                    next.before($('<a class="cp_num" >').html(n));
                                }
                            }  
                        }
                        getData();
                        // 分页按钮的点击效果
                        wzq_page.off('click','a').on('click','a',tabClick);
                        function tabClick(){
                            let next = $(this).next();
                            // 注册分页的点击事件
                            if($(this).hasClass('cp_num')){
                                $('.wzq_page_active',wzq_page).removeClass('wzq_page_active');
                                $(this).addClass('wzq_page_active');
                                if(next.hasClass('wzq_page_next')){
                                    let val = parseInt($(this).html());
                                    checkPage(val+1,num,$(this),wzq_page,next);
                                }
                            }
                            // 分页前进按钮的执行程序
                            if($(this).hasClass('wzq_page_prev')){
                                let active = $('.wzq_page_active',wzq_page);
                                let prev = $('.wzq_page_active',wzq_page).prev();
                                if(prev.hasClass('cp_num')){
                                    $('.wzq_page_active',wzq_page).removeClass('wzq_page_active');
                                    prev.addClass('wzq_page_active');
                                }else{
                                    let val = parseInt(active.html());
                                    let next = $('.wzq_page_next',wzq_page);
                                    if(val>10){
                                        checkPage(val-10,num,$(this),wzq_page,next,'desc');
                                    }
                                }
                            }
                            // 分页后退按钮的执行程序
                            if($(this).hasClass('wzq_page_next')){
                                let active = $('.wzq_page_active',wzq_page);
                                let next = $('.wzq_page_active',wzq_page).next();
                                if(next.hasClass('cp_num')){
                                    $('.wzq_page_active',wzq_page).removeClass('wzq_page_active');
                                    next.addClass('wzq_page_active');
                                }else{
                                    let val = parseInt(active.html());
                                    if(val<num){
                                        checkPage(val+1,num,$(this),wzq_page,next);
                                    }
                                }
                            }
                            // 分页首页功能的实现
                            if($(this).hasClass('wzq_page_index')){
                                let next = $('.wzq_page_next',wzq_page);
                                checkPage(1,num,$(this),wzq_page,next);
                            }
                            // 分页尾页功能的实现
                            if($(this).hasClass('wzq_page_last')){
                                let next = $('.wzq_page_next',wzq_page);
                                if(num>10){
                                    checkPage(num-9,num,$(this),wzq_page,next,'desc');
                                }else{
                                    checkPage(1,num,$(this),wzq_page,next,'desc');
                                }
                                
                            }
                            getData();
                        }
                        // 获取相应分页的数据
                        function getData(){
                            comment.html("");
                            let activeNum = (parseInt($('.wzq_page_active',wzq_page).html())-1)*5>0?(parseInt($('.wzq_page_active',wzq_page).html())-1)*5:0;
                            let dataObj = data.commentAndReply.slice(activeNum,activeNum+5);
                                dataObj.forEach(function(val,n){
                                var str = "";
                                str+=`<div class="wzq_clear">
                                        <div class="wzq_user_img">
                                            <img src="${val.comment.head_image}" alt="">
                                        </div>
                                        <!--评论区内容开始，回复评论区时候，添加此节点-->
                                        <div class="wzq_comment_item_info">
                                            <h6 class="wzq_comment_item_title">${val.comment.nickName}</h6>
                                            <span class="wzq_comment_item_time">${getDate(val.comment.createTime)}</span>
                                            <div class="wzq_comment_item_des clear_both">
                                                <p class="hidd_more">${val.comment['content']}</p>
                                                <div class="wzq_comment_reply">
                                                    <span title="回复" class="wzq_comment_replys" data-flag="true" replyusername="${val.comment.nickName}" commentId="${val.comment.id}" replyUserId="${val.comment.userId}"><i class="fy_icon">&#xe679;</i></span>
                                                    <span class="wzq_comment_zan wzq_comment_zanend" commentId="${val.comment.id}"><i class="fy_icon">&#xe6b3;</i> ${val.comment.good}</span>
                                                </div>
                                            </div>`
                                val.reply.forEach((ele,i)=>{
                                    str+=`<div class="wqf_reply">
                                        <span uid="${ele.userId}">${ele.userName} 回复 ${ele.replyusername==null?'':'@'+ele.replyusername}：${ele.reply}</span>
                                        <div class="wzq_comment_reply">
                                            <span title="回复" class="wzq_comment_replys" data-flag="true" replyusername="${ele.userName}" commentId="${ele.commentId}" replyUserId="${ele.userId}"><i class="fy_icon">&#xe679;</i></span>
                                        </div>
                                    </div>`   // wqf_reply 结束
                                }) 
                                // wzq_comment_item_info 结束    wzq_clear 结束
                                str+=`</div>  
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
                                                    <div class="wzq_icon_box">

                                                    </div>
                                                </div>
                                                <div class="wzq_reply_submit"><input type="button" value="回复"></div>
                                            </div>
                                        </form>
                                    </div>
                                    <!--评论区回复表单结束-->
                                    `
                                $('<li class="wzq_show_comment_item" index='+n+'>').html(str).appendTo(comment);
                            })
                            comment.appendTo(content);
                        }
                        //分页判断函数
                        function checkPage(val,num,obj,wzq_page,next,type){
                            var type = type || 'asc';
                            console.log(num,val);
                            if((val-1)==num){
                                $('.wzq_page_active',wzq_page).removeClass('wzq_page_active');
                                obj.addClass('wzq_page_active');
                                return;
                            }
                            let flag = (num-val)/10>1?true:false;
                            $('.cp_num',wzq_page).remove();
                            if(flag){
                                for(let n=val;n<(val+10);n++){
                                    if(type=='asc'){
                                       if(n==val){
                                        next.before($('<a class="cp_num wzq_page_active" >').html(n));
                                        }else{
                                            next.before($('<a class="cp_num" >').html(n));
                                        } 
                                    }else{
                                        if(n==(val+9)){
                                        next.before($('<a class="cp_num wzq_page_active" >').html(n));
                                        }else{
                                            next.before($('<a class="cp_num" >').html(n));
                                        }
                                    } 
                                } 
                            }else{
                                for(let n=val;n<=num;n++){
                                    if(type=='asc'){
                                       if(n==val){
                                        next.before($('<a class="cp_num wzq_page_active" >').html(n));
                                        }else{
                                            next.before($('<a class="cp_num" >').html(n));
                                        } 
                                    }else{
                                        if(n==num){
                                        next.before($('<a class="cp_num wzq_page_active" >').html(n));
                                        }else{
                                            next.before($('<a class="cp_num" >').html(n));
                                        }
                                    } 
                                } 
                            }
                        } 
                    }
                    // 发表评论判断与ajax数据发送
                   // $('.col_left').off('click').on('click','.wzq_add_submit>input',function(){
                    $(".wzq_add_submit>input").on('click',function(){
                    	let con = $('#wzq_textarea');
                        if(con.val().trim()=="快来发布评论吧..."||con.val().trim()==""){
                         // getTips("内容不能为空");
                         layer.msg('内容不能为空', function(){
                         //关闭后的操作
                         });
                        }else{
                            let content = con.val();
                            $.ajax({
                                url:'course_addcomment',
                                data:{userId,courseId,content},
                                type:'post',
                                success:function(val){
                                    let tim = new Date();
                                    let sum = tim.getTime();
                                    if(val.info=='未登录'){
                                        // getTips("未登录","点击此处登录","login");
                                        layer.confirm('您未登录？', {
                                          btn: ['去登陆','取消'] //按钮
                                        }, function(){
                                          location.href="login"
                                        });
                                    }else if(val.info=='success'){
                                        // getTips("评论成功");
                                        layer.msg("评论成功")
                                        let con = $('.wzq_show_comment_list .noComment');
                                        if(con[0]){
                                            con.remove();
                                        }
                                        // 评论成功后创建节点，添加刚才评论的内容
                                        $('<li class="wzq_show_comment_item">').html(`
                                                <div class="wzq_clear">
                                                    <div class="wzq_user_img">
                                                        <img src="${data.userInfo.head_image}" alt="">
                                                    </div>
                                                    <div class="wzq_comment_item_info">
                                                        <h6 class="wzq_comment_item_title">${data.userInfo.nickname}</h6>
                                                        <span class="wzq_comment_item_time">${getDate(sum)}</span>
                                                        <div class="wzq_comment_item_des clear_both">
                                                            <p class="hidd_more">${content}</p>
                                                            <div class="wzq_comment_reply">
                                                                <span title="回复" class="wzq_comment_replys" data-flag="true" replyusername="${val.nickName}" commentId="${val.commentId}" replyuserid="${userId}"><i class="fy_icon">&#xe679;</i></span>
                                                                <span class="wzq_comment_zan wzq_comment_zanend" commentId="${val.commentId}"><i class="fy_icon">&#xe6b3;</i> 0</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="wzq_reply">
                                                    <form action="" class="wzq_reply_form">
                                                        <div class="wzq_reply_text">
                                                            <span>回复</span>
                                                            <em class="wzq_reply_user hidden_one">小灰灰灰</em>
                                                            <textarea class="wzq_reply_textarea" name="" onfocus="if(value=='请回复...') {value=''}" onblur="if(value=='') {value='请回复...'}">请回复...</textarea>
                                                        </div>
                                                        <div class="wzq_clear">
                                                            <div class="wzq_icon_add">
                                                                <div class="wzq_icon_box">

                                                                </div>
                                                            </div>
                                                            <div class="wzq_reply_submit"><input type="button" value="回复"></div>
                                                        </div>
                                                    </form>
                                                </div>
                                            `).prependTo('.wzq_show_comment_list');
                                        let obj = {
                                                    "comment":{"id":67,
                                                                "createTime":sum,
                                                                "updateTime":sum,
                                                                "guid":"218d8480c50f49c699efbc50ebea248e",
                                                                "content":"测试评论1",
                                                                "nickName":"飞鱼学员371",
                                                                "head_image":data.userInfo.head_image
                                                            },
                                                    "reply":[]
                                                };
                                            data.commentAndReply.unshift(obj);
                                    }else if(val.info=='fail'){
                                        // getTips("评论失败");
                                        layer.msg("评论失败")
                                    }
                                    $('#wzq_textarea').val('');
                                }
                            })
                        }
                    })

                    var my_parent = "";
                    // 发表回复的功能的ajax触发  
                    $('.col_left').off('click').on('click','.wzq_reply_submit input',function(){
                    	console.log(my_parent)
                        let con = $('.wzq_reply_textarea',$(this).parents('.wzq_reply_form'));
                    	
                    	if(userId){
                            layer.confirm('您未登录？', {
                                btn: ['去登陆','取消'] //按钮
                            }, function(){
                                location.href="login"
                            });
                        }else if(con.val().trim()=="请回复..."||con.val().trim()==""){
                            // getTips("内容不能为空");
                            layer.msg('内容不能为空', function(){
                            //关闭后的操作
                            });
                        }else{
                            // let user = $('.wzq_comment_replys',my_parent).attr('replyusername');   // 小写变大写
                            let commentId = my_parent.attr("commentid")
                            let user = my_parent.attr("replyusername")
                            let replyUserId = my_parent.attr("replyUserId")
                           // let  replyId = $('span[uid]',my_parent).attr('uid');
                            let reply = con.val();
                            console.log(userId,commentId,reply,replyUserId)
                            
                            $.ajax({
                                url:'chapter_comment_reply',
                                data:{userId,commentId,reply,replyUserId},
                               //  data:{userId,chapterId,reply,replayId},
                                type:'post',
                                success:function(val){
                                    let tim = new Date();
                                    let sum = tim.getTime();
                                    if(val.code==200){
                                    	layer.msg("评论成功")
                                        let content = $('<div class="wqf_reply">').html(`<span uid="null">我 回复 ${user}：${reply}</span>
                                            <div class="wzq_comment_reply">
                                                                                                            
                                                <span title="回复" class="wzq_comment_replys" data-flag="false" replyusername="${user}" commentId="${val.commentId}" replyuserid="${userId}"><i class="fy_icon"></i></span>
                                            </div>`).appendTo(my_parent.parents(".wzq_comment_item_info"));
                                         // replyuserName小写变大写
                                    }else if(val.info=='fail'){
                                    	layer.msg("评论失败")
                                    }
                                    con.val('');
                                }
                            })
                        }
                    })
                   
                    //点击回复评论区出现
                    $(".col_left").delegate(".wzq_comment_reply .wzq_comment_replys","click",function () {
                    	
                        // my_parent  = $(this).parents('.wzq_reply');
                        my_parent  = $(this);
                        console.log(my_parent)
                        var flag = $(this).attr("data-flag");
                        var $that = $(this)
                        
                        var reply = $(this).parents(".wzq_show_comment_item").find(".wzq_reply")
                        if(flag == 'true'){
                            reply.slideDown(200,function () {
                                reply.find(".wzq_reply_user").html($that.attr("replyusername"))    // 小写变大写
                                $that.attr("data-flag","false");
                            });
                        }else{
                            reply.slideUp(200,function () {
                                $that.attr("data-flag","true");
                            });
                        }
                    })
                    //添加表情点击事件委托
                    // $(".wzq_icon_add").delegate("span","click",function () {
                    //     var iconFlag = $(this).attr("data-icon-flag");
                    //     if(iconFlag == "true"){
                    //         $(this).next().css("display","block");
                    //         $(this).attr("data-icon-flag","false") ;
                    //     }else{
                    //         $(this).next().css("display","none");
                    //         $(this).attr("data-icon-flag","true") ;
                    //     }

                    // })
                    // 点赞功能
                    $(".col_left").delegate(".wzq_comment_zan.wzq_comment_zanend","click",function () {
                        var commentId = $(this).attr("commentid")
                        console.log(commentId)
                        if (this.style.color) {
                            // 取消赞
                        	var _this = this
                            $.ajax({
                                url:'comment_good_down',
                                data:{commentId},
                                type:'post',
                                success:function(val){
                                	$(_this).css({color:""})
                                    $(_this).html(`<i class="fy_icon">&#xe6b3;</i> ${val.good}`)
                                    $(_this).children().css({color:""})
                                }
                            })
                        }else{
                            // 点赞
                        	var _this = this
                            $.ajax({
                                url:'comment_good',
                                data:{commentId},
                                type:'post',
                                success:function(val){
                                	$(_this).css({color:"#e64444"})
                                    $(_this).html(`<i class="fy_icon">&#xe6b3;</i> ${val.good}`)
                                    $(_this).children().css({color:"#e64444"})
                                }
                            })	
                        }
                    })
                }

            })
    }
    getComment();




    // 处理评论的时间
    function getDate(num){
        let times = new Date();
        let timeNum=times.getTime()-num;
        let seconds = Math.ceil(timeNum/1000);
        if(seconds>60){
            let minute = Math.ceil(timeNum/(60*1000));
            if(minute>60){
                let hour = Math.ceil(minute/60);
                if(hour>12){
                    let day = Math.floor(hour/24);
                    if(day>30){
                        let month = Math.floor(day/30);
                        if(month>12){
                            let year = Math.floor(month/12);
                            return year+'年前';
                        }else{
                            return month+'月前';
                        }
                    }else{
                        return day+'天前';
                    }
                }else{
                    return hour+'小时前';
                }
            }else{
                return minute+'分钟前';
            } 
        }else{
            return seconds+'秒前';
        }  
    }
    // 处理的是笔记的时间
    Date.prototype.toLocaleString = function() {
          return {year:this.getFullYear() + "年",dateTime:(this.getMonth() + 1) + "月" + this.getDate() + "日 " };
    };
    function getLocal(num){
        let time = new Date(num);
        return time.toLocaleString();
    }

    
})
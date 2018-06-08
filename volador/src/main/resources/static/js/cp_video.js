$(function(){
   
    // 视频侧边栏部分的js控制
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
        if($('.cp_chapter').hasClass('active')){
            $('.cp_videoBox .cp_div').html('');

            // 视频侧边的章节显示的ajax部分
            $.ajax({
            	url:"chapterList",
                type:'post',
                data:{userId,courseId},
                dataType:'json',
                success:function(record){
                    let obj = record;
                    obj.chapterTitle.forEach(function(val,n){
                        let arr1 =  val.chapter_title.split(/：|:/);
                        let parent = $('<ul class="hidden_one">').html('<span class="title">'+arr1[0]+'</span>'+arr1[1]);
                        obj.chapterList['chapter'+n].forEach(function(k,j){
                            let arr2 = k.chapter_name.split(/\s/);
                            console.log(k.id)
                            if(chapterId==k.id){
                                // $('<li class="hidden_one active">').html("<i class='fy_icon'>&#xe629;</i><span>"+(n+1)+"-"+(j+1)+"</span>"+k.chapter_name+"<b>正在学</b>").appendTo(parent);
                                $('<li class="hidden_one  active">').html("<i class='fy_icon'>&#xe629;</i><a href='course_video?chapterId="+k.id+"' >"+k.chapter_name+"</a><b>正在学</b>").appendTo(parent);
                            }else{
                                $('<li class="hidden_one">').html("<i class='fy_icon'>&#xe629;</i><a href='course_video?chapterId="+k.id+"' >"+k.chapter_name+"</a>").appendTo(parent);
                            }
                            
                        })
                        parent.appendTo($('.cp_videoBox .cp_div'));
                    })
                }
            })
        }
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

    if(!userId){
        $('.cp_videoBox .cp_form  :input').hide();
        $('.cp_videoBox .cp_form p').show();
    }else{
        $('.cp_videoBox .cp_form p').hide();
        $('.cp_videoBox .cp_form:input').show();
    }
    //页面刷新状态下不会改变视频的播放进度设置
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
    function getComment(){
            let content = $('.cp_tabCon .container .col_left .cp_commentBox');
            let wzq_page = $('.cp_tabCon .container .col_left .wzq_page');
            let tips =  $('.cp_tabCon .tips');
            tips.css('display','block');
            $.ajax({
                url:"chapter_commentList",
                type:'post',
                data:{userId,chapterId},
                dataType:'json',
                success:function(data){
                    tips.html('全部评论 <span>'+data.commentList.length+'</span>');
                    let comment=$('<ul class="wzq_show_comment_list wqf_comment">');
                    content.html(`
                            <div class="wzq_show_comment_add">
                                <form class="wzq_add_form wzq_clear">
                                    <div class="wzq_clear">
                                        <div class="wzq_user_img">
                                            <img src="${data.UserHeadImage}" alt="">
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
                    `);
                    if(data.commentList.length<5&&data.commentList.length!=0){
                        data.commentList.forEach(function(val,n){
                            $('<li class="wzq_show_comment_item">').html(`
                                <div class="wzq_clear">
                                    <div class="wzq_user_img">
                                        <img src="${val.head_image}" alt="">
                                    </div>
                                    <!--评论区内容开始，回复评论区时候，添加此节点-->
                                    <div class="wzq_comment_item_info">
                                        <h6 class="wzq_comment_item_title">${val.nickName}</h6>
                                        <span class="wzq_comment_item_time">${getDate(val.createTime)}</span>
                                        <div class="wzq_comment_item_des">
                                            <p class="hidd_more">${val['content']}</p>
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
                            `).appendTo(comment);
                        })
                        comment.appendTo(content);
                    }else if(data.commentList.length==0){
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
                        wzq_page.css('display','none');
                        tips.css('display','none');
                    }else{
                        
                        let num = Math.ceil(data.commentList.length/5); 
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
                            let dataObj = data.commentList.slice(activeNum,activeNum+5);
                            dataObj.forEach(function(val,n){
                                $('<li class="wzq_show_comment_item">').html(`
                                    <div class="wzq_clear">
                                        <div class="wzq_user_img">
                                            <img src="${val.head_image}" alt="">
                                        </div>
                                        <!--评论区内容开始，回复评论区时候，添加此节点-->
                                        <div class="wzq_comment_item_info">
                                            <h6 class="wzq_comment_item_title">${val.nickName}</h6>
                                            <span class="wzq_comment_item_time">${getDate(val.createTime)}</span>
                                            <div class="wzq_comment_item_des">
                                                <p class="hidd_more">${val['content']}</p>
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
                                `).appendTo(comment);
                            });
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
                    $('.col_left').on('click','.wzq_add_submit>input',function(){
                        let con = $('#wzq_textarea');
                        if(con.val().trim()==""){
                         getTips("内容不能为空");
                        }else{
                            let content = con.val();
                            $.ajax({
                                url:'course_chapter_comment',
                                data:{userId,chapterId,content},
                                type:'post',
                                success:function(val){
                                    let tim = new Date();
                                    let sum = tim.getTime();
                                    if(val.info=='未登录'){
                                        getTips("未登录","点击此处登录","login");
                                    }else if(val.info=='success'){
                                        getTips("评论成功");
                                        if(data.commentList.length<5){
                                            // 评论成功后创建节点，添加刚才评论的内容
                                            $('<li class="wzq_show_comment_item">').html(`
                                                <div class="wzq_clear">
                                                    <div class="wzq_user_img">
                                                        <img src="${data.UserHeadImage}" alt="">
                                                    </div>
                                                    <div class="wzq_comment_item_info">
                                                        <h6 class="wzq_comment_item_title">也入耳朵</h6>
                                                        <span class="wzq_comment_item_time">${getDate(sum)}</span>
                                                        <div class="wzq_comment_item_des">
                                                            <p class="hidd_more">${content}</p>
                                                            <div class="wzq_comment_reply">
                                                                <span class="wzq_jubao"><i class="fy_icon">&#xe652;</i>举报</span>
                                                                <span title="回复" class="wzq_comment_replys" data-flag="true"><i class="fy_icon">&#xe679;</i></span>
                                                                <span class="wzq_comment_zan wzq_comment_zanend"><i class="fy_icon">&#xe6b3;</i></span>
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
                                                                <span data-icon-flag="true">添加表情</span>
                                                                <div class="wzq_icon_box">

                                                                </div>
                                                            </div>
                                                            <div class="wzq_reply_submit"><input type="submit" value="回复"></div>
                                                        </div>
                                                    </form>
                                                </div>
                                            `).appendTo('.wzq_show_comment_list');
                                        }else{
                                            let obj = {
                                                "createTime" : sum,
                                                "updateTime" : sum,
                                                "content" : content,
                                                "nickName" : "小小新",
                                                "head_image" : data.UserHeadImage
                                            };
                                            data.commentList.push(obj);
                                        }
                                    }else if(val.info=='fail'){
                                        getTips("评论失败");
                                    }
                                    $('#wzq_textarea').val('');
                                }
                            })
                        }
                    })

                    //点击回复评论区出现
                    $(".wzq_show_comment_item").delegate(".wzq_comment_reply .wzq_comment_replys","click",function () {
                        var flag = $(this).attr("data-flag");
                        var $that = $(this)
                        if(flag == 'true'){
                            $(this).parents(".wzq_show_comment_item").find(".wzq_reply").slideDown(200,function () {
                                $that.attr("data-flag","false");
                            });
                        }else{
                            $(this).parents(".wzq_show_comment_item").find(".wzq_reply").slideUp(200,function () {
                                $that.attr("data-flag","true");
                            });
                        }
                    })
                    //添加表情点击事件委托
                    $(".wzq_icon_add").delegate("span","click",function () {
                        var iconFlag = $(this).attr("data-icon-flag");
                        if(iconFlag == "true"){
                            $(this).next().css("display","block");
                            $(this).attr("data-icon-flag","false") ;
                        }else{
                            $(this).next().css("display","none");
                            $(this).attr("data-icon-flag","true") ;
                        }

                    })

                }

            })
    }
    getComment();

    // 笔记、评论、章节的切换
    $('.cp_purchase .cp_tabBox>li').click(function(){
        let num = $(this).index();
        let content = $('.cp_tabCon .container .col_left .cp_commentBox');
        let wzq_page = $('.cp_tabCon .container .col_left .wzq_page');
        let tips =  $('.cp_tabCon .tips');
        $('.cp_purchase .cp_tabBox>li').removeClass('active').eq(num).addClass('active');
        content.html('');
        // 评论
        if(num==0){
            getComment();
        // 笔记
        }else if(num==1){
            tips.css('display','block');
            // 笔记
            $.ajax({
                url:"userNoteList",
                type:'post',
                data:{userId,chapterId},
                dataType:'json',
                success:function(obj){
                    tips.html('全部笔记 <span>'+obj.length+'</span>');
                    let note = $("<ul class='cp_note' >");
                    let data = obj;
                    wzq_page.css('display','block');
                    if(obj.length < 5 && obj.length!=0 ){
                        data.forEach(function(val,n){
                            $('<li>').html(`
                                <div class="left">
                                    <span>${getLocal(val.createTime).year}</span><br><span>${getLocal(val.createTime)['dateTime']}</span>
                                </div>
                                <div class="middle">
                                    <div class="line"></div>
                                </div>
                                <div class="right">
                                    <p class='hidden_one'>${val['title']}</p>
                                    <p class='hidd_more2' >${val['content']}</p>
                                </div>
                            `).appendTo(note);
                        }) 
                        note.appendTo(content);
                    }else if(obj.length==0){
                        wzq_page.css('display','none');
                        note.html(`
                        <div class="noComment">
                            <div class="con">
                             <img src="images/cp_13.png" alt="">
                             <p>
                                还没有<a href="#">笔记</a><br>快点记下笔记呗！！！
                             </p>
                            </div>
                        </div>`);
                        note.appendTo(content);
                        wzq_page.css('display','none');
                        tips.css('display','none');
                    }else{
                        let num = Math.ceil(obj.length/5); 
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
                            note.html("");
                            let activeNum = (parseInt($('.wzq_page_active',wzq_page).html())-1)*5>0?(parseInt($('.wzq_page_active',wzq_page).html())-1)*5:0;
                            let dataObj = data.slice(activeNum,activeNum+5);
                            dataObj.forEach(function(val,n){
                            $('<li>').html(`
                                    <div class="left">
                                        <span>${getLocal(val.createTime).year}</span><br><span>${getLocal(val.createTime)['dateTime']}</span>
                                    </div>
                                    <div class="middle">
                                        <div class="line"></div>
                                    </div>
                                    <div class="right">
                                        <p class='hidden_one'>${val['title']}</p>
                                        <p class='hidd_more2' >${val['content']}</p>
                                    </div>
                                `).appendTo(note);
                            }) 
                            note.appendTo(content);
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
                }
            })
        // 章节
        }else if(num==2){
            wzq_page.css('display','none');
            tips.css('display','none');
            // 章节
            $.ajax({
                url:"chapterList",
                type:'post',
                data:{userId,courseId},
                dataType:'json',
                success:function(data){
                    console.dir(data);
                    let chapter = $('<ul class="wqf_chapter_list">');
                    data.chapterTitle.forEach(function(val,index){
                        let li = $("<li>").html(`
                            <h2 class="title">
                                <i class="icon">=</i>
                                <span>${val.chapter_title}</span>
                            </h2>
                        `);
                        let ul = $('<ul class="list" >');
                        data.chapterList["chapter"+index].forEach(function(m,n){
                            $('<li>').html(`
                                <a href="course_video?chapterId=${m.id}">
                                    <i class="fy_icon">&#xe629;</i>
                                    ${m.chapter_name}
                                    <span class="time">${m.total_time}</span>
                                </a>
                                <p class="hidd_more">${m.chapter_info}</p>
                            `).appendTo(ul);
                        })
                        ul.appendTo(li);
                        li.appendTo(chapter);
                    })
                    chapter.appendTo(content);
                }
            })
        }    
    });

    
    // 发表笔记判断与ajax数据发送
    $('.cp_videoBox .cp_form>input[type="submit"]').click(function(){
        let con = $('.cp_form>textarea');
        let title = $('.cp_form>input[name="title"]');
        if(con.val().trim()==""||title.val().trim()==""){
            getTips("内容不能为空");
        }else{
            let content = con.val();
            let biaoti = title.val();
            $.ajax({
                url:'addNote',
                data:{userId,chapterId,"noteTitle":biaoti,'noteContent':content},
                type:'post',
                success:function(val){
                    let tim = new Date();
                    let sum = tim.getTime();
                    if(val.info=='未登录'){
                        getTips("未登录","点击此处登录","登陆页面的路径");
                    }else if(val.info=='success'){
                        getTips("笔记添加成功");
                        // if(obj.length<5){
                        //     // 笔记成功后创建节点，添加刚才评论的内容
                        //     $('<li>').html(`
                        //         <div class="left">
                        //             <span>${getLocal(sum).year}</span><br><span>${getLocal(sum)['dateTime']}</span>
                        //         </div>
                        //         <div class="middle">
                        //             <div class="line"></div>
                        //         </div>
                        //         <div class="right">
                        //             <p class='hidden_one'>${biaoti}</p>
                        //             <p class='hidd_more2' >${content}</p>
                        //         </div>
                        //     `).appendTo('.cp_commentBox .cp_note');
                        // }else{
                        //     let obj1 = {
                        //         "createTime" : sum,
                        //         "updateTime" : sum,
                        //         "content" : content,
                        //         "title" : biaoti,
                        //         "chapterId" : 0
                        //     };
                        //     obj.push(obj1);
                        // }
                    }else if(val.info=='fail'){
                        getTips("笔记添加失败");
                    }
                    con.val('');
                    title.val('');
                }
            })
        }
    })
    

    



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

    // 控制信息提示
    function getTips(msg,con='',url=''){
        con = con||"";
        url = url||"";
        console.log(con,url)
        if(!con){
            $('<div class="cp_tips">').css({
                "width":'300px',
                "height":'50px',
                "background":'rgba(0,0,0,0.9)',
                "border-radius":'5px',
                "position":'fixed',
                "left":'0px',
                "right":'0px',
                "top":"0px",
                "bottom":'0px',
                "margin":"auto",
                "color":'white',
                "border":'1px solid #FD6756',
                "line-height":'50px',
                "padding-left":'50px',
                "opacity":'1',
                "transition":'1s'
            }).html(msg).appendTo('body');   
        }else{
            $('<div class="cp_tips">').css({
                "width":'300px',
                "height":'50px',
                "background":'rgba(0,0,0,0.6)',
                "border-radius":'5px',
                "position":'fixed',
                "left":'0px',
                "right":'0px',
                "top":"0px",
                "bottom":'0px',
                "margin":"auto",
                "color":'white',
                "border":'1px solid #FD6756',
                "line-height":'50px',
                "padding-left":'50px',
                "opacity":'1',
                "transition":'1s'
            }).html(msg+'<a href='+url+'>'+con+'</a>').appendTo('body');
            $('.cp_tips a').css({'color':'#FD6756','textDecoration':'underline'});
        }
        $("<span>").css({
            "position":'absolute',
            "left":'20px',
            "top":'0px',
            "bottom":"0px",
            "width":"20px",
            "height":"20px",
            "margin":"auto",
            "lineHeight":'20px',
            "cursor":'pointer',
            "transition":'0.3s'
        })
        .hover(function(){
            $(this).css({'color':'#FD6756'});
        },function(){
            $(this).css({'color':'white'});
        })  
        .html('x')
        .appendTo($('.cp_tips'));
        $('.cp_tips span').click(function(){
            $('.cp_tips').remove();
        })
        // setTimeout(function(){
        //     $('.cp_tips').css('opacity',0);
        //     $('.cp_tips').on('webkitTransitionEnd',function(){
        //         $('.cp_tips').remove();
        //     })
        // },2000)
    }  
})
$(function () {
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

    //点击选择表情之后事件委派
    $(".wzq_icon_add").delegate(".wzq_icon_box","click",function () {
        
    })


    // 发表评论判断与ajax数据发送
    let con = $('#wzq_textarea');
    console.log($('.wzq_add_submit input'))
    $('.wzq_add_submit input').click(function(){
   
        if(!con.val().trim()){
            alert("内容不能为空");
        }else{
            let content = con.val();
            let courseId = $("#courseId").val();
            var userId = $("#userId").val();
            $.ajax({
                url:"course_addcomment",
                data:{"content":content,
                	"courseId":courseId,
                	"userId":userId},
                type:'post',
                success:function(data){
                    if(data.info =='未登录'){
                        alert('请登陆');
                        location.href = 'login';
                    }else if(data.info =='success'){
                        alert('评论成功');
                        // 评论成功后创建节点，添加刚才评论的内容
                        $('<li class="wzq_show_comment_item">').html(`
                            <div class="wzq_clear">
                                <div class="wzq_user_img">
                                    <img src="${data.headImage}" alt="">
                                </div>
                                <div class="wzq_comment_item_info">
                                    <h6 class="wzq_comment_item_title">${data.nickName}</h6>
                                    <span class="wzq_comment_item_time">刚刚</span>
                                    <div class="wzq_comment_item_des">
                                        <p class="hidd_more">${data.content}</p>
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
                            `).prependTo('.wzq_show_comment_list');
                    }else if(data=='fail'){
                        alert('评论失败');
                    }
                }
            })
        }
    })


  
})
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
})
$(function () {

    $(".wzq_zhiye_item  .wzq_zhiye_item_top").click(function () {
        var zhiyeFlag = $(this).parent().attr("data-zhiye-flag")
        $(this).next().slideToggle("slow");
        if(zhiyeFlag == "true"){
            $(this).find(".wzq_zhiye_icon .fy_icon").html("&#xe618;").css("color","#f5a324");
            $(this).parent().attr("data-zhiye-flag","false")
        }else{
            $(this).find(".wzq_zhiye_icon .fy_icon").html("&#xe604;").css("color","#7c7c7c");
            $(this).parent().attr("data-zhiye-flag","true")
        }
    })

    $(".wzq_zhiye_con_info").click(function(){
        $(this).nextAll(".wqf_classlist").slideToggle()
    })

})
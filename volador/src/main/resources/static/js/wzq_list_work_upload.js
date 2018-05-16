
$(function () {
    $(".wzq_work_item_status .wzq_work_zan").click(function () {
        $(this).addClass("wzq_work_zanend")
    })

    $(".wzq_work_item_edit .wzq_work_del>span").click(function () {
        $(this).css("color","#fd6756").next(".wzq_del_or").css("display","block");
    })
    $(".wzq_work_item_edit .wzq_work_del .wzq_del_or span:nth-child(1)").click(function () {
        alert("删除")
    })

    $(".wzq_work_item_edit .wzq_work_del .wzq_del_or span:nth-child(2)").click(function () {
        alert("取消")
        $(this).parent().css("display","none")
    })


})

$(function () {
    $.each($(".wzq_course_time"),function (i,n) {
        $(this).css("height",$(n).next(".wzq_course_list").height()+"px");
    })

})
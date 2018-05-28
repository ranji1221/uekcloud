
var wzq_page_prev = $(".wzq_page_prev");   // 上一页按键
var str = "";
for (var i = 0; i < page_date.pageCount; i++) {
	if (i==page_date.currentPage-1) {
		str +=  "<a href='' class='wzq_page_active'>"+ (i+1) +"</a>"
	}else{
		str +=  "<a href='"+page_date.interfaceName+"?page="+(i+1)+"'>"+ (i+1) +"</a>"
	}
}
wzq_page_prev.after(str)

console.log(1)
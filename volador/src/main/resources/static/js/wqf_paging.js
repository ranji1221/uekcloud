
var wzq_page = $(".wzq_page");   // 上一页按键

var now = page_date.currentPage-1<=0?1:page_date.currentPage-1
var next = page_date.currentPage>=page_date.pageCount?page_date.currentPage:page_date.currentPage+1


var str = "";
str+="<a href='"+page_date.interfaceName+"?page=1' class='wzq_page_index'>首页</a><a href='"+page_date.interfaceName+"?page="+now+"' class='wzq_page_prev'>上一页</a>"

for (var i = 1; i <= page_date.pageCount; i++) {
	if (i==page_date.currentPage) {
		str +=  "<a href='"+page_date.interfaceName+"?page="+i+"' class='wzq_page_active'>"+ i +"</a>"
	}else{
		str +=  "<a href='"+page_date.interfaceName+"?page="+i+"'>"+ i +"</a>"
	}
}

str+="<a href='"+page_date.interfaceName+"?page="+next+"'class='wzq_page_next'>下一页</a> <a href='"+page_date.interfaceName+"?page="+ (page_date.pageCount) +"' class='wzq_page_last'>尾页</a>"
wzq_page.append(str)


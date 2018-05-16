$(function () {
	var totop_hover = $(".wqf_totop .wqf_hover");
	var totop_zi = $(".wqf_totop .wqf_hover span");

	totop_hover.hover(function(){
		totop_zi.eq($(this).index()-2).css({display:"block"}).animate({right:80,opacity:1},300)
	},function(){
		var i = $(this).index()-2
		totop_zi.eq(i).animate({right:130,opacity:0},300,function(){
			totop_zi.eq(i).css({display:"none"})
		})
	})



	// 回到顶部
	var toTop = $(".wqf_totop #toTop");
	toTop.click(function() {
	$("html,body").animate({scrollTop:0}, 500);
	}); 
})
var class_btn = $('.class_select ul li')

class_btn.on('click',function(){
	console.log($(this).index())
})
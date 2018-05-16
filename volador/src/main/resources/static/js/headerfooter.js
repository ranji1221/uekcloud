
$(function () {
	// 头部 消息提示框的显示隐藏
	var message_yes = $('#public_header>.container .is_login .login_yes>.message');
	var message_box = $('#public_header>.container .is_login .login_yes>.message .message_box');
	var t1 = null;
	message_yes.hover(function(){
		clearTimeout(t1);
		message_box.fadeIn(200)
	},function(){
		t1 = setTimeout(function(){
			message_box.fadeOut(200)
		}, 500);
	})


	var self_yes = $('#public_header>.container .is_login .login_yes>.self');
	var self_box = $('#public_header>.container .is_login .login_yes>.self .message_box');
	var t2 = null;
	self_yes.hover(function(){
		clearTimeout(t2);
		self_box.fadeIn(200)
	},function(){
		t2 = setTimeout(function(){
			self_box.fadeOut(200)
		}, 500);
	})


	var search_box = $('#public_header .search input');
	var alert_box = $('#public_header .search .alert_box');

	search_box.focus(function(){
		alert_box.slideDown(300);
	})
	search_box.blur(function(){
		alert_box.slideUp(300);
	})


})
var _ = (function(obj) {
	$(obj.onLoad);
	$(window).resize(obj.resize);
	return obj;
})({
	url : "http://nowonbunskinex.tistory.com",
	onLoad : function() {
		/* 메뉴 상태.. 처음 누르면 메뉴가 나옴. 다시 누르면 메뉴가 들어감 */
		$(".menu-click").on("click", function() {
			let type = $(this).data("menu-type");
			if (type === "close") {
				_.menu.left.off();
				_.menu.back.off();
				return;
			}
			if ($("aside#leftside").hasClass("on")) {
				_.menu.left.off();
				_.menu.back.off();
				return;
			}
			_.menu.left.on();
			_.menu.back.on();
		});
		// $(".menu-toggle").on("click", function() {
		$(document).on("click", ".menu-toggle", function() {
			$sub = $(this).parent().parent().find("ul");
			if ($(this).hasClass("glyphicon-triangle-bottom")) {
				$sub.removeClass("off");
				$(this).removeClass("glyphicon-triangle-bottom");
				$(this).addClass("glyphicon-triangle-top");
			} else {
				$sub.addClass("off");
				$(this).addClass("glyphicon-triangle-bottom");
				$(this).removeClass("glyphicon-triangle-top");
			}
		});
	},
	resize : function() {
		/* onResize */
		_.menu.left.off();
		_.menu.back.off();
	},
	menu : {
		left : {
			/* 사이드바 생길 때 처리 */
			on : function() {
				_.changeOnOff($("aside#leftside"), true);
			},
			/* 사이드바 없어질 때 처리 */
			off : function() {
				_.changeOnOff($("aside#leftside"), false);
			}
		},
		back : {
			on : function() {
				_.changeOnOff($("section.backgroundLayout"), true);
				_.changeOnOff($("main"), true);
				$("html").addClass("backstatic");
			},
			off : function() {
				_.changeOnOff($("section.backgroundLayout"), false);
				_.changeOnOff($("main"), false);
				$("html").removeClass("backstatic");
			}
		}
	},
	/* 클래스 상태 변경 state=true, on state=false off */
	changeOnOff : function(dom, state) {
		if (dom.hasClass("on")) {
			dom.removeClass("on");
		}
		if (dom.hasClass("off")) {
			dom.removeClass("off");
		}
		dom.addClass(state ? "on" : "off");
	}
});

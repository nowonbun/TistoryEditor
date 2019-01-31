var _ = (function(obj){
	$(obj.onLoad);
	return obj;
})({
	url: "http://nowonbunskinex.tistory.com",
	onLoad: function(){
		
	}
});
/*onResize*/
$(window).resize(function(){
	sideLeftOff();
	sideBackOff();
});
/*메뉴 상태.. 처음 누르면 메뉴가 나옴. 다시 누르면 메뉴가 들어감*/
function menu(state){
	if(state === "close"){
		sideLeftOff();
		sideBackOff();
		return;
	}
	if($("aside#leftside").hasClass("on")){
		sideLeftOff();
		sideBackOff();
		return;
	}
	sideBackOn();
	sideLeftOn();	
}
function sideBackOn(){
	changeClassState($("section.backgroundLayout"),true);
	/*화면 고정처리*/
	$('html').css("overflow","hidden");
	/*화면 깨짐 버그처리*/
	$('html').css("position","fixed");
	$('body').css("width",$(window).width());
	changeClassState($("main"),true);
}
function sideBackOff(){
	changeClassState($("section.backgroundLayout"),false);
	changeClassState($("main"),false);
	/*화면 깨짐 버그처리*/
	$('body').css("width","");
	$('html').css("position","static");
	/*화면 고정처리*/
	$('html').css("overflow","auto");
}
/*사이드바 없어질 때 처리*/
function sideLeftOff(){
	changeClassState($("aside#leftside"),false);
}
/*사이드바 생길 때 처리*/
function sideLeftOn(){
	changeClassState($("aside#leftside"),true);
}
function sideRightOn(){
	changeClassState($("aside#rightside"),true);
}

/*클래스 상태 변경*/
function changeClassState(dom,state){
	if(dom.hasClass("on")){
		dom.removeClass("on");
	}
	if(dom.hasClass("off")){
		dom.removeClass("off");
	}
	dom.addClass(state?"on":"off");
}

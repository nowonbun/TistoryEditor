<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr" style="position: static; overflow: auto;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
		<meta name="theme-color" content="#29343a">
		<title>명월 일지 :: [MFC]네이버 실시간 검색 순위 CS프로그램 - 6</title>
		
		<link rel="stylesheet" type="text/css" href="./css/blog/style.css">
		<link rel="stylesheet" type="text/css" href="./css/blog/menubar.css">
		<script src="./js/blog/jquery-1.12.1.min.js"></script>
		<script src="./js/blog/bootstrap.min.js"></script>
	<header>
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<a class="navbar-brand" href="https://nowonbun.tistory.com/252?category=507115#" onclick="menu(&#39;left&#39;)"><span class="menuSpan">MENU</span></a> 
				<a class="navbar-brand title" href="https://nowonbun.tistory.com/" style="left: 605px;"><span>명월 일지</span></a>
				<a class="navbar-brand" href="https://nowonbun.tistory.com/252?category=507115#" onclick="menu(&#39;right&#39;)"><span class="menuSpan">LIST</span></a>
			</div>
		</nav>
	</header>
	<section class="headerspace"></section>
	<aside id="leftside" class="off" style="height: 449px;">
		<h2 class="side-header"><img src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/1935C94C505D9F8B13" alt="v명월v">명월 일지</h2>
		<div class="side-menu bs-glyphicons">
			<ul class="bs-glyphicons-list">
			<li onclick="location.href=&#39;/&#39;;">
				<span class="glyphicon glyphicon-home" aria-hidden="true" title="홈으로"></span>
				<span>홈으로</span>
			</li>
			<li onclick="location.href=&#39;mailto:nowonbun@naver.com&#39;;">
				<span class="glyphicon glyphicon-envelope" aria-hidden="true" title="메일보내기"></span>
				<span>메일발송</span>
			</li>
			<li onclick="window.external.AddFavorite(&#39;http://nowonbun.tistory.com&#39;,&#39;☆~명월일지~☆&#39;);">
				<span class="glyphicon glyphicon-star-empty" aria-hidden="true" title="즐겨찾기"></span>
				<span>즐겨찾기</span>
			</li>
			<li onclick="location.href=&#39;/guestbook&#39;;">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true" title="방명록"></span>
				<span>방명록</span>
			</li>
			<li onclick="menu(&#39;close&#39;);">
				<span class="glyphicon glyphicon-remove" aria-hidden="true" title="메뉴닫기"></span>
				<span>메뉴닫기</span>
			</li>
			</ul>
		</div>
		<div class="side-list" style="min-height: 247px;">
			<ul class="side-nav">
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8">
						개발 노트						
											<a href="https://nowonbun.tistory.com/252?category=507115#" class="glyphicon glyphicon-triangle-bottom pull-right" aria-hidden="true" onclick="openSublist($(this));"></a></a>

										<ul class="sub_category_list off">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/C%20%EC%96%B8%EC%96%B4">
									C 언어
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/C%EF%BC%83">
									C＃
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Java">
									Java
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/JQuery">
									JQuery
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Javascript">
									Javascript
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Window">
									Window
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Linux">
									Linux
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/%EC%A7%80%EC%8B%9D%20In">
									지식 In
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/%EC%A0%95%EB%B3%B4%20%EB%B0%8F%20%EC%9E%A1%EB%8B%B4">
									정보 및 잡담
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/%EC%98%88%EC%A0%9C%EC%86%8C%EC%8A%A4">
									예제소스
									
																	</a>
							</li>
											</ul>
					
				</li>
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8">
						프로젝트						
											<a href="https://nowonbun.tistory.com/252?category=507115#" class="glyphicon glyphicon-triangle-bottom pull-right" aria-hidden="true" onclick="openSublist($(this));"></a></a>

										<ul class="sub_category_list off">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EA%B0%80%EA%B3%84%EB%B6%80%281%29">
									가계부(1)
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EA%B0%80%EA%B3%84%EB%B6%80%282%29">
									가계부(2)
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EB%B8%94%EB%A1%9C%EA%B7%B8%20%EA%B4%80%EB%A0%A8">
									블로그 관련
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EC%9D%BC%EB%B3%B8%20%EB%B8%94%EB%A1%9C%EA%B7%B8">
									일본 블로그
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%ED%8B%B0%EC%8A%A4%ED%86%A0%EB%A6%AC%20%EC%8A%A4%ED%82%A8%20%EC%88%98%EC%A0%95">
									티스토리 스킨 수정
									
																	</a>
							</li>
											</ul>
					
				</li>
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80">
						공부						
											<a href="https://nowonbun.tistory.com/252?category=507115#" class="glyphicon glyphicon-triangle-bottom pull-right" aria-hidden="true" onclick="openSublist($(this));"></a></a>

										<ul class="sub_category_list off">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/C%20%EC%96%B8%EC%96%B4">
									C 언어
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Java">
									Java
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/C%EF%BC%83">
									C＃
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Jquery%2CCSS">
									Jquery,CSS
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Android">
									Android
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/MSSQL">
									MSSQL
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Illestrator%20CS2">
									Illestrator CS2
									
																	</a>
							</li>
											</ul>
					
				</li>
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%EA%B7%B8%20%EC%99%B8">
						그 외						
											<a href="https://nowonbun.tistory.com/252?category=507115#" class="glyphicon glyphicon-triangle-bottom pull-right" aria-hidden="true" onclick="openSublist($(this));"></a></a>

										<ul class="sub_category_list off">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B7%B8%20%EC%99%B8/%EB%B9%84%EA%B3%B5%EA%B0%9C%20%EB%AC%B8%EC%84%9C">
									비공개 문서
									
																	</a>
							</li>
											</ul>
					
				</li>
					</ul>
		</div>
		<div class="side-footer bs-glyphicons">
			<ul class="bs-glyphicons-list">
				<li onclick="login();" class="logout off">
					<span class="glyphicon glyphicon-log-in" aria-hidden="true" title="로그인"></span>
					<span>로그인</span>
				</li>
				<li onclick="logout();" class="login on">
					<span class="glyphicon glyphicon-log-out" aria-hidden="true" title="로그인"></span>
					<span>로그아웃</span>
				</li>
				<li onclick="addLink();" class="login on">
					<span class="glyphicon glyphicon-list-alt" aria-hidden="true" title="링크추가"></span>
					<span>링크추가</span>
				</li>
				<li onclick="writeTistory();" class="admin off">
					<span class="glyphicon glyphicon-leaf" aria-hidden="true" title="글쓰기"></span>
					<span>글쓰기</span>
				</li>
				<li onclick="adminTistory();" class="admin off">
					<span class="glyphicon glyphicon-cog" aria-hidden="true" title="관리"></span>
					<span>관리</span>
				</li>
			</ul>
		</div>
	</aside>
	<aside id="rightside" class="off" style="height: 449px;">
		<h2 class="side-header">
			<a href="https://nowonbun.tistory.com/252?category=507115#" onclick="menu(&#39;close&#39;);">
				<span class="glyphicon glyphicon-remove" aria-hidden="true" title="메뉴닫기"></span>
				<span>메뉴닫기</span>
			</a>
			<span>
				
					<input type="text" name="search" value="" onkeypress="if (event.keyCode == 13) { try{window.location.href=&#39;/search/&#39;+looseURIEncode(document.getElementsByName(&#39;search&#39;)[0].value);document.getElementsByName(&#39;search&#39;)[0].value=&#39;&#39;;return false;}catch(e){} }">
					<input value="검색" type="button" onclick="try{window.location.href=&#39;/search/&#39;+looseURIEncode(document.getElementsByName(&#39;search&#39;)[0].value);document.getElementsByName(&#39;search&#39;)[0].value=&#39;&#39;;return false;}catch(e){}" class="submit">
				
			</span>
		</h2>
		<div id="recentList">
			<h3 class="rightsideTitle">최근에 올라온 글</h3>
			<ol>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/386"> 구글 블로그의 본문 위젯 -3 [일본 블로그].</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/385"> 구글 블로거의 분문 위젯 -2 [일본 블로그].</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/384"> 구글 블로거의 분문 위젯 -1 [일본 블로그].</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/383"> 구글 블로거(Blogger) 위젯 설정 및 해더...</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/382"> 구글 블로거(Blogger) 초기화 [일본 블로...</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/381"> 일본 블로그 다시 시작. (Blogger 테마...</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/380"> Java 코딩 규약 - 3 [Java / 자바].</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/379"> Google Java Style - 2 [Java / 자바].</a></li>
				<li><a class="recentlyPost" href="https://nowonbun.tistory.com/378"> Google Java Style - 1 [Java / 자바].</a></li>
				<li></li>
			</ol>
		</div>
		<hr>
		<div id="categoryList">
			<h3 class="rightsideTitle">카테고리 다른글</h3>
			<ol>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/252?category=507115">[MFC]네이버 실시간 검색 순위 CS프로그램 - 6</a></li>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/189?category=507115">[MFC] 서비스 프로그램</a></li>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/186?category=507115">[C++] ShowWindow[메크로 상수], Window 메시지 상수</a></li>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/146?category=507115">[C++] afxsock 통신 - 클라이언트편</a></li>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/145?category=507115">[C++] afxsock 통신 - 서버편</a></li>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/132?category=507115">C++ Dll 만들어서 C#에서 사용하기 (마샬링) [C++]</a></li>
				<li><a class="anotherPost" href="https://nowonbun.tistory.com/101?category=507115">[C++] ActiveX 만들기</a></li>
				<li></li>
				<li></li>
				<li></li>
			</ol>
		</div>
	</aside>
	<section class="backgroundLayout off" onclick="menu(&#39;close&#39;);"></section>
	<main role="main" class="off" style="min-height: 411px;">
	<!-- start:main -->
		<div id="main" class="container">
			<!-- 메인 -->
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<!-- ************************검색 결과 리스트************************-->
				
				<!-- ************************검색 결과 리스트************************-->
				<!-- ************************공지사항************************-->
				
				<!-- ************************공지사항************************-->
				<!-- ************************방명록************************-->
				
				<!-- ************************방명록************************-->
				<!-- ************************글************************-->
				
				<article class="entry">
					
					
						<!-- 글 관련 정보 : 글제목, 카테고리, 날짜, 관리자 -->
						<div class="titleArea">
							<div class="title">[MFC]네이버 실시간 검색 순위 CS프로그램 - 6</div>
							<hr class="titileHr">
							<div class="categoryArea">
								<a href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/C%20%EC%96%B8%EC%96%B4">개발 노트/C 언어</a> &nbsp;2013.08.09 09:00
								
							</div>
						</div>
						<!-- 글 관련 정보 : 글제목, 카테고리, 날짜, 관리자 -->
						<!-- 블로그 글 -->
						<div class="adsenseHeadDefault">
			<ins class="adsbygoogle" style="display: block; height: 90px;" data-ad-client="ca-pub-6300064900388375" data-ad-slot="4848477643" data-ad-format="auto" data-adsbygoogle-status="done"><ins id="aswift_1_expand" style="display:inline-table;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:1110px;background-color:transparent;"><ins id="aswift_1_anchor" style="display:block;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:1110px;background-color:transparent;"><iframe width="1110" height="90" frameborder="0" marginwidth="0" marginheight="0" vspace="0" hspace="0" allowtransparency="true" scrolling="no" allowfullscreen="true" onload="var i=this.id,s=window.google_iframe_oncopy,H=s&amp;&amp;s.handlers,h=H&amp;&amp;H[i],w=this.contentWindow,d;try{d=w.document}catch(e){}if(h&amp;&amp;d&amp;&amp;(!d.body||!d.body.firstChild)){if(h.call){setTimeout(h,0)}else if(h.match){try{h=s.upd(h,i)}catch(e){}w.location.replace(h)}}" id="aswift_1" name="aswift_1" style="left:0;position:absolute;top:0;border:0px;width:1110px;height:90px;" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/saved_resource(2).html"></iframe></ins></ins></ins>
		</div>
						<div class="article"><div class="tt_article_useless_p_margin"><script async="" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/f(6).txt"></script>
<!-- Tistory_Top -->
<ins class="adsbygoogle" style="display:inline-block;width:300px;height:90px" data-ad-client="ca-pub-6300064900388375" data-ad-slot="1106768449" data-adsbygoogle-status="done"><ins id="aswift_0_expand" style="display:inline-table;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:300px;background-color:transparent;"><ins id="aswift_0_anchor" style="display:block;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:300px;background-color:transparent;"><iframe width="300" height="90" frameborder="0" marginwidth="0" marginheight="0" vspace="0" hspace="0" allowtransparency="true" scrolling="no" allowfullscreen="true" onload="var i=this.id,s=window.google_iframe_oncopy,H=s&amp;&amp;s.handlers,h=H&amp;&amp;H[i],w=this.contentWindow,d;try{d=w.document}catch(e){}if(h&amp;&amp;d&amp;&amp;(!d.body||!d.body.firstChild)){if(h.call){setTimeout(h,0)}else if(h.match){try{h=s.upd(h,i)}catch(e){}w.location.replace(h)}}" id="aswift_0" name="aswift_0" style="left:0;position:absolute;top:0;border:0px;width:300px;height:90px;" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/saved_resource(3).html"></iframe></ins></ins></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({}); </script>
<br><br>
<p><span style="FONT-SIZE: 11pt">안녕하세요 명월입니다.</span></p>
<p><span style="FONT-SIZE: 11pt">이번 포스팅에서는 클라이언트를 만들어 보도록 하겠습니다. 서버 프로그램은 C#으로 만들었지만 클라이언트 프로그램은 MFC로 작성해 보도록 하겠습니다.</span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">(이미 프로그램을 완성되서 하는 말이지만 역시 MFC는 C#보다는 재미는 떨어지네요.. 뭔지 일 같은 느낌도 들고 ㅎㅎ 그래서 이 프로그램도 거창하게 생각은 했지만 하다가 귀찮아서 대충 만들어 버렸네요.)</span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">클라이언트에서 중요한 것은 서버와의 커넥션이 중요하고 그 데이터를 가져와서 폼에 그리는 것이 중요하겠습니다.</span></p>
<p>&nbsp;</p>
<p style="FLOAT: none; TEXT-ALIGN: left; CLEAR: none"><span class="imageblock" style="display:inline-block;width:630px;;height:auto;max-width:100%"><span data-url="https://t1.daumcdn.net/cfile/tistory/222FF43651FE4FCB2C?original" data-lightbox="lightbox"><img src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/222FF43651FE4FCB2C" style="cursor: pointer;max-width:100%;height:auto" width="630" height="723" filename="K-20.jpg" filemime="image/jpeg"></span></span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">소켓 설정부 입니다.</span></p>
<p><span style="FONT-SIZE: 11pt">소켓은 굳히 비동기로 세팅할 필요가 없기 때문에 그냥 동기 모드로 작성했습니다.</span></p>
<p><span style="FONT-SIZE: 11pt">커넥트가 이루어 진후에 MWOK! 프로토콜을 날린후 실시간 검색 전문을 수신받는 형태 입니다.</span></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p style="FLOAT: none; TEXT-ALIGN: left; CLEAR: none"><span class="imageblock" style="display:inline-block;width:630px;;height:auto;max-width:100%"><span data-url="https://t1.daumcdn.net/cfile/tistory/271C843C51FE508A17?original" data-lightbox="lightbox"><img src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/271C843C51FE508A17" style="cursor: pointer;max-width:100%;height:auto" width="630" height="593" filename="K-21.jpg" filemime="image/jpeg"></span></span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">위 소스는 전문으로 받은 데이터를 클라이언트에 맞게 데이터 세팅하는 부분입니다.</span></p>
<p><span style="FONT-SIZE: 11pt">C#에서는 List에서 처리한것을 C++에서는 Vector로 처리한 것일 뿐 형태는 같다고 보면 되겠습니다.</span></p>
<p>&nbsp;</p>
<p style="FLOAT: none; TEXT-ALIGN: left; CLEAR: none"><span class="imageblock" style="display:inline-block;width:630px;;height:auto;max-width:100%"><span data-url="https://t1.daumcdn.net/cfile/tistory/2234A83851FE50FF27?original" data-lightbox="lightbox"><img src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/2234A83851FE50FF27" style="cursor: pointer;max-width:100%;height:auto" width="630" height="622" filename="K-22.jpg" filemime="image/jpeg"></span></span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">데이터를 그리는 부분입니다.. 그냥 DC를 받아서 전무 DrawText로 그려 버렸습니다. </span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">완성된 화면입니다.</span></p>
<p>&nbsp;</p>
<p style="FLOAT: none; TEXT-ALIGN: left; CLEAR: none"><span class="imageblock" style="display:inline-block;width:650px;;height:auto;max-width:100%"><img src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/2647F33851FE526C2A" style="max-width:100%;height:auto" width="650" height="383" filename="K-24.jpg" filemime="image/jpeg"></span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">실시간 화면이 약간은 다르네요... 갱신 속도 차이이므로 프로그램의 오류는 아닙니다.</span></p>
<p><span style="FONT-SIZE: 11pt">또 잘 되나 해서 3개까지 켜보았는데. 별 무리는 없네요.. 뭐 작은 프로그램이니깐 이런걸로 버벅 거릴 컴퓨터는 요즘....없겠죠?</span></p>
<p>&nbsp;</p>
<p><span style="FONT-SIZE: 11pt">참고 소스입니다.</span></p>
<p>&nbsp;</p>
<p><span class="imageblock" style="display:inline-block;;height:auto;max-width:100%"><a href="http://nowonbun.tistory.com/attachment/cfile26.uf@2613F13651FE53371FFD06.zip"><img src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/zip.gif" alt="" style="vertical-align: middle;"> RealSearchClient.zip</a></span></p>
<p></p><div class="container_postbtn"><div class="postbtn_like"><div class="like_btn" data-uoc-svc="tistory" data-uoc-uid="1041549_252" data-uoc-sc="401" data-uoc-pcurl="https://nowonbun.tistory.com/252" data-uoc-fetchurl="http://api.kakao.tistory.com/like/fetch?uid=1041549_252"><label class="uoc-icon"><span class="ico_postbtn ico_like"></span> <span class="txt_like uoc-text screen_out">공감</span><span class="txt_like uoc-count">5</span><input type="button" class="inp_btn uoc-button"></label></div><span class="ico_bar"></span><label data-thumbnail-url="https://t1.daumcdn.net/cfile/tistory/222FF43651FE4FCB2C" data-title="[MFC]네이버 실시간 검색 순위 CS프로그램 - 6" data-description="안녕하세요 명월입니다. 이번 포스팅에서는 클라이언트를 만들어 보도록 하겠습니다. 서버 프로그램은 C#으로 만들었지만 클라이언트 프로그램은 MFC로 작성해 보도록 하겠습니다. (이미 프로그램을 완성되서 하는.." data-profile-image="https://t1.daumcdn.net/cfile/tistory/1935C94C505D9F8B13" data-profile-name="v명월v" data-pc-url="https://nowonbun.tistory.com/252" data-blog-title="명월 일지"><span class="ico_postbtn ico_sns">sns</span><input type="button" class="inp_btn"></label><span class="ico_bar"></span><label data-entry-id="252"><span class="ico_postbtn ico_report">신고</span><input type="button" class="inp_btn"></label></div></div><div class="another_category another_category_color_blue">
<h4>'<a href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8">개발 노트</a>&nbsp;&gt;&nbsp;<a href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/C%20%EC%96%B8%EC%96%B4">C 언어</a>' 카테고리의 다른 글</h4>
<table>
<tbody><tr>
<th>
<a href="https://nowonbun.tistory.com/252?category=507115" class="current">[MFC]네이버 실시간 검색 순위 CS프로그램 - 6</a>&nbsp;&nbsp;<span>(5)</span>
</th>
<td>
2013.08.09</td>
</tr>
<tr>
<th>
<a href="https://nowonbun.tistory.com/189?category=507115">[MFC] 서비스 프로그램</a>&nbsp;&nbsp;<span>(4)</span>
</th>
<td>
2012.12.02</td>
</tr>
<tr>
<th>
<a href="https://nowonbun.tistory.com/186?category=507115">[C++] ShowWindow[메크로 상수], Window 메시지 상수</a>&nbsp;&nbsp;<span>(0)</span>
</th>
<td>
2012.10.20</td>
</tr>
<tr>
<th>
<a href="https://nowonbun.tistory.com/146?category=507115">[C++] afxsock 통신 - 클라이언트편</a>&nbsp;&nbsp;<span>(0)</span>
</th>
<td>
2012.09.30</td>
</tr>
<tr>
<th>
<a href="https://nowonbun.tistory.com/145?category=507115">[C++] afxsock 통신 - 서버편</a>&nbsp;&nbsp;<span>(0)</span>
</th>
<td>
2012.09.30</td>
</tr>
<tr>
<th>
<a href="https://nowonbun.tistory.com/132?category=507115">C++ Dll 만들어서 C#에서 사용하기 (마샬링) [C++]</a>&nbsp;&nbsp;<span>(2)</span>
</th>
<td>
2012.09.23</td>
</tr>
<tr>
<th>
<a href="https://nowonbun.tistory.com/101?category=507115">[C++] ActiveX 만들기</a>&nbsp;&nbsp;<span>(5)</span>
</th>
<td>
2012.09.14</td>
</tr>
</tbody></table></div></div></div>
						<div class="adsenseFootDefault">
			<ins class="adsbygoogle" style="display: block; height: 90px;" data-ad-client="ca-pub-6300064900388375" data-ad-slot="7801944041" data-ad-format="auto" data-adsbygoogle-status="done"><ins id="aswift_2_expand" style="display:inline-table;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:1110px;background-color:transparent;"><ins id="aswift_2_anchor" style="display:block;border:none;height:90px;margin:0;padding:0;position:relative;visibility:visible;width:1110px;background-color:transparent;"><iframe width="1110" height="90" frameborder="0" marginwidth="0" marginheight="0" vspace="0" hspace="0" allowtransparency="true" scrolling="no" allowfullscreen="true" onload="var i=this.id,s=window.google_iframe_oncopy,H=s&amp;&amp;s.handlers,h=H&amp;&amp;H[i],w=this.contentWindow,d;try{d=w.document}catch(e){}if(h&amp;&amp;d&amp;&amp;(!d.body||!d.body.firstChild)){if(h.call){setTimeout(h,0)}else if(h.match){try{h=s.upd(h,i)}catch(e){}w.location.replace(h)}}" id="aswift_2" name="aswift_2" style="left:0;position:absolute;top:0;border:0px;width:1110px;height:90px;" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/saved_resource(4).html"></iframe></ins></ins></ins>
		</div>
						<!-- 블로그 글 -->
						<!-- 2-8-3. 글 관련 태그 -->
						
						<!-- 2-8-3. 글 관련 태그 -->
						<hr>
						<!-- 2-8-4. 트랙백과 댓글 보이기/감추기 -->
						<div class="actionTrail ">
							<ul class="nav nav-tabs nav-justified">
								<li><a href="https://nowonbun.tistory.com/252?category=507115#commentList" onclick="commnet();" class="off"> 댓글 </a></li>
							</ul>
						</div>
						<!-- 2-8-4. 트랙백과 댓글 보이기/감추기 -->
						<div id="commentList" class="tab-content off">
							<!-- 2-8-6. 댓글 -->
							<div class="commentCnt">
							댓글 <span class="cnt"><span id="commentCount252">5</span></span>개가 달렸습니다.
							</div>
							<div id="entry252Comment" style="display:block">
								<div class="comment">
									<!-- 2-8-6-2. 댓글 쓰기 -->
									<form method="post" action="https://nowonbun.tistory.com/comment/add/252" onsubmit="return false" style="margin: 0">
									<div class="commentWrite col-lg-12 col-md-12 col-sm-12 col-xs-12">
										 
										<div class="row commentSet">
											<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
												<label for="name_20">name</label>
											</div>
											<div class="col-lg-4 col-md-10 col-sm-12 col-xs-12">
												<input type="text" id="name_20" name="name" value="" class="form-control textField commenttext" placeholder="enter name">
											</div>
											<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
												<label for="password_20">password</label>
											</div>
											<div class="col-lg-4 col-md-10 col-sm-12 col-xs-12">
												<input type="password" id="password_20" class="form-control textField commenttext" maxlength="8" name="password" value="" placeholder="password">
											</div>
											<div class="col-lg-2 col-md-12 col-sm-12 col-xs-12">
												<div>
													<input id="secret_20" type="checkbox" name="secret" value="1"> <label> 비밀글 </label>
												</div>
											</div>
										</div>
										 
										<textarea id="comment_" placeholder="enter comment" name="comment" class="form-control commenttext" rows="3" style="resize: none;"></textarea>
										<a class="btnComment pull-right" onclick="addComment(this, 252); return false;">댓글쓰기</a>
									</div>
									</form>
									<!-- 2-8-6-2. 댓글 쓰기 -->
									<!-- 2-8-6-1. 댓글 리스트 -->
									
									<div class="commentList col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<ol class="reply list-unstyled">
											
											<li id="comment11583314" class="commentList">
												<div class="rp_secret hiddenComment">
													<span class="name"></span><br> <span class="date"> 2013.10.11 15:10</span> | <span class="control"> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="deleteComment(11583314);return false" class="modify commentA">&nbsp;<span>수정/삭제</span></a> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="commentComment(11583314); return false" class="write commentA">&nbsp;<span>댓글쓰기</span></a>
													</span>
													<hr class="commentHr">
													<p class="commentContents">비밀댓글입니다</p>
												</div> 
											</li>
											
											<li id="comment11583315" class="commentList">
												<div class="rp_general">
													<span class="name"><a href="http://youthtraveler.tistory.com/" onclick="return openLinkInNewWindow(this)">청년여행자</a></span><br> <span class="date"> 2013.10.11 15:11 <a href="https://nowonbun.tistory.com/toolbar/popup/abuseReport/?entryId=252&amp;commentId=11583315" onclick="window.open(this.href, &#39;tistoryThisBlogPopup&#39;, &#39;width=550, height=510, toolbar=no, menubar=no, status=no, scrollbars=no&#39;); return false;">신고</a></span> | <span class="control"> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="deleteComment(11583315);return false" class="modify commentA">&nbsp;<span>수정/삭제</span></a> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="commentComment(11583315); return false" class="write commentA">&nbsp;<span>댓글쓰기</span></a>
													</span>
													<hr class="commentHr">
													<p class="commentContents">자주소통해요 ~</p>
												</div> 
												<ul>
													
													<li id="comment11589963">
														<div class="rp_admin">
															<span class="name"><a href="https://nowonbun.tistory.com/" onclick="return openLinkInNewWindow(this)">明月 v명월v</a><span class="tistoryProfileLayerTrigger" onclick="TistoryProfile.show(event, this, {&quot;title&quot;:&quot;\uba85\uc6d4 \uc77c\uc9c0&quot;,&quot;url&quot;:&quot;https:\/\/nowonbun.tistory.com&quot;,&quot;nickname&quot;:&quot;v\uba85\uc6d4v&quot;,&quot;items&quot;:[]}); return false;"></span></span><br> <span class="date"> 2013.10.15 23:29 <a href="https://nowonbun.tistory.com/toolbar/popup/abuseReport/?entryId=252&amp;commentId=11589963" onclick="window.open(this.href, &#39;tistoryThisBlogPopup&#39;, &#39;width=550, height=510, toolbar=no, menubar=no, status=no, scrollbars=no&#39;); return false;">신고</a></span> | <span class="control"> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="deleteComment(11589963); return false;" class="modify">&nbsp;<span>수정/삭제</span></a>
															</span>
															<hr class="commentHr">
															<p class="commentContents">감사합니다.. 블로그 방문해 주셔서...<br>
저도 자주 소통했으면 좋겠습니다.</p>
														</div>
													</li>
													
												</ul>
												
											</li>
											
											<li id="comment12270698" class="commentList">
												<div class="rp_secret hiddenComment">
													<span class="name"></span><br> <span class="date"> 2015.06.11 21:16</span> | <span class="control"> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="deleteComment(12270698);return false" class="modify commentA">&nbsp;<span>수정/삭제</span></a> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="commentComment(12270698); return false" class="write commentA">&nbsp;<span>댓글쓰기</span></a>
													</span>
													<hr class="commentHr">
													<p class="commentContents">비밀댓글입니다</p>
												</div> 
												<ul>
													
													<li id="comment12274262">
														<div class="rp_admin">
															<span class="name"><a href="https://nowonbun.tistory.com/" onclick="return openLinkInNewWindow(this)">明月 v명월v</a><span class="tistoryProfileLayerTrigger" onclick="TistoryProfile.show(event, this, {&quot;title&quot;:&quot;\uba85\uc6d4 \uc77c\uc9c0&quot;,&quot;url&quot;:&quot;https:\/\/nowonbun.tistory.com&quot;,&quot;nickname&quot;:&quot;v\uba85\uc6d4v&quot;,&quot;items&quot;:[]}); return false;"></span></span><br> <span class="date"> 2015.06.17 01:00 <a href="https://nowonbun.tistory.com/toolbar/popup/abuseReport/?entryId=252&amp;commentId=12274262" onclick="window.open(this.href, &#39;tistoryThisBlogPopup&#39;, &#39;width=550, height=510, toolbar=no, menubar=no, status=no, scrollbars=no&#39;); return false;">신고</a></span> | <span class="control"> <a href="https://nowonbun.tistory.com/252?category=507115#" onclick="deleteComment(12274262); return false;" class="modify">&nbsp;<span>수정/삭제</span></a>
															</span>
															<hr class="commentHr">
															<p class="commentContents">안녕하세요 블로그 방문 감사합니다.<br>
워낙 오래된 자료라 기억이 가물가물한데요..<br>
제가 지금 보기로는 DrawRow라는 메시지함수에 넣은 것 같네요..<br>
예제 소스가 있으니 다운 받으셔서 참고해 주세요.</p>
														</div>
													</li>
													
												</ul>
												
											</li>
											
										</ol>
									</div>
									
								<!-- 2-8-6-1. 댓글 리스트 -->
								</div>
							</div><script type="text/javascript">loadedComments[252]=true;findFragmentAndHighlight(252)</script>
							<!-- 2-8-6. 댓글 -->
						</div>
					
				</article>
				
				<!-- ************************글************************-->
			</div>
			<!-- 메인 -->
		</div>
	<!-- end:main -->
	</main>
	<!-- ************************2-9. 페이징************************ -->
	
		<section class="paging">
		<a class="glyphicon glyphicon-backward no-more-prev" aria-hidden="true" title="prev"></a>
	 		<span class="numbox"><span class="selected">1</span><span class="splite">/</span><span class="max">7</span></span> 
		<a href="https://nowonbun.tistory.com/189?category=507115" class="glyphicon glyphicon-forward " aria-hidden="true" title="next"></a>
		</section>
	
	<!-- ************************2-9. 페이징************************ -->
	<!-- start:footer -->
	<footer>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 footer-bottom">
			<!-- 방문자수 -->
			<p>
				<span class="total">Total : 632,894 </span>
				<span class="today">Today : 487</span>
				<span class="yesterday">Yesterday : 556</span>
			</p>
			<p>Copyright © 2016 nowonbun.tistory.com</p>
		</div>
	</footer>
	<!-- end:footer -->
	<section style="display: none" id="template">
		<div id="adsenseHeader">
			<ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-6300064900388375" data-ad-slot="4848477643" data-ad-format="auto"></ins>
		</div>
		<div id="adsenseFooter">
			<ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-6300064900388375" data-ad-slot="7801944041" data-ad-format="auto"></ins>
		</div>
		<div id="categoryTemplate"><ul class="tt_category">
	<li class="">
		<a class="link_tit" href="https://nowonbun.tistory.com/category">
			분류 전체보기			
					</a>

				<ul class="category_list">
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8">
						개발 노트						
											</a>

										<ul class="sub_category_list">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/C%20%EC%96%B8%EC%96%B4">
									C 언어
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/C%EF%BC%83">
									C＃
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Java">
									Java
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/JQuery">
									JQuery
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Javascript">
									Javascript
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Window">
									Window
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/Linux">
									Linux
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/%EC%A7%80%EC%8B%9D%20In">
									지식 In
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/%EC%A0%95%EB%B3%B4%20%EB%B0%8F%20%EC%9E%A1%EB%8B%B4">
									정보 및 잡담
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B0%9C%EB%B0%9C%20%EB%85%B8%ED%8A%B8/%EC%98%88%EC%A0%9C%EC%86%8C%EC%8A%A4">
									예제소스
									
																	</a>
							</li>
											</ul>
					
				</li>
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8">
						프로젝트						
											</a>

										<ul class="sub_category_list">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EA%B0%80%EA%B3%84%EB%B6%80%281%29">
									가계부(1)
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EA%B0%80%EA%B3%84%EB%B6%80%282%29">
									가계부(2)
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EB%B8%94%EB%A1%9C%EA%B7%B8%20%EA%B4%80%EB%A0%A8">
									블로그 관련
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%EC%9D%BC%EB%B3%B8%20%EB%B8%94%EB%A1%9C%EA%B7%B8">
									일본 블로그
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%ED%8B%B0%EC%8A%A4%ED%86%A0%EB%A6%AC%20%EC%8A%A4%ED%82%A8%20%EC%88%98%EC%A0%95">
									티스토리 스킨 수정
									
																	</a>
							</li>
											</ul>
					
				</li>
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80">
						공부						
											</a>

										<ul class="sub_category_list">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/C%20%EC%96%B8%EC%96%B4">
									C 언어
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Java">
									Java
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/C%EF%BC%83">
									C＃
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Jquery%2CCSS">
									Jquery,CSS
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Android">
									Android
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/MSSQL">
									MSSQL
									
																	</a>
							</li>
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B3%B5%EB%B6%80/Illestrator%20CS2">
									Illestrator CS2
									
																	</a>
							</li>
											</ul>
					
				</li>
							<li class="">
					<a class="link_item" href="https://nowonbun.tistory.com/category/%EA%B7%B8%20%EC%99%B8">
						그 외						
											</a>

										<ul class="sub_category_list">
													<li class="">
								<a class="link_sub_item" href="https://nowonbun.tistory.com/category/%EA%B7%B8%20%EC%99%B8/%EB%B9%84%EA%B3%B5%EA%B0%9C%20%EB%AC%B8%EC%84%9C">
									비공개 문서
									
																	</a>
							</li>
											</ul>
					
				</li>
					</ul>
			</li>
</ul></div>
		<div id="blogImage">https://t1.daumcdn.net/cfile/tistory/1935C94C505D9F8B13</div>
		<div id="loginstate" class="login"></div>
		
				<!-- 최근에 올라온 글 -->
				<div class="recentPost">
					<h3>최근에 올라온 글 </h3>
					<ul>
						
							<li>
								<a href="https://nowonbun.tistory.com/386"> 구글 블로그의 본문 위젯 -3 [일본 블로그].</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries386"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/385"> 구글 블로거의 분문 위젯 -2 [일본 블로그].</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries385"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/384"> 구글 블로거의 분문 위젯 -1 [일본 블로그].</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries384"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/383"> 구글 블로거(Blogger) 위젯 설정 및 해더...</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries383"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/382"> 구글 블로거(Blogger) 초기화 [일본 블로...</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries382"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/381"> 일본 블로그 다시 시작. (Blogger 테마...</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries381"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/380"> Java 코딩 규약 - 3 [Java / 자바].</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries380"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/379"> Google Java Style - 2 [Java / 자바].</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries379"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/378"> Google Java Style - 1 [Java / 자바].</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries378"></span></span>
							</li>
						

							<li>
								<a href="https://nowonbun.tistory.com/377"> Jquery의 이벤트(Event) 관련 함수 - 2 (...</a> 
								<span class="cnt"><span id="commentCountOnRecentEntries377"></span></span>
							</li>
						
					</ul>
				</div>
			
	</section>
	<script async="" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/f(6).txt"></script>
	<script src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/nowonbun.js"></script>
<script type="text/javascript" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/wcslog.js"></script>
<script type="text/javascript">
	if(!wcs_add) var wcs_add = {};
	wcs_add["wa"] = encodeURI("240b7144110708");
	wcs_do();
</script><script>
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	ga('create', 'UA-58360998-1', 'auto');
	ga('send', 'pageview');
</script><script src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/search_dragselection.min.js"></script>
<script>
    (function($) {
        $("body").bind('copy', function (e) {

            var url = document.location.href,
                decodedUrl = decodeURI(url),
                selection = window.getSelection();


            if (typeof window.getSelection == "undefined") {//IE8 or earlier...
                event.preventDefault();

                var pagelink = '\n\n 출처: ' + decodedUrl,
                    copytext = selection + pagelink;

                if (window.clipboardData) {
                    window.clipboardData.setData('Text', copytext);
                }
                return;
            }
            var body_element = document.getElementsByTagName('body')[0];

            //if the selection is short let's not annoy our users
            if (("" + selection).length < 30) return;

            //create a div outside of the visible area
            var newdiv = document.createElement('div');
            newdiv.style.position = 'absolute';
            newdiv.style.left = '-99999px';
            body_element.appendChild(newdiv);
            newdiv.appendChild(selection.getRangeAt(0).cloneContents());

            //we need a <pre> tag workaround
            //otherwise the text inside "pre" loses all the line breaks!
            if (selection.getRangeAt(0).commonAncestorContainer.nodeName == "PRE") {
                newdiv.innerHTML = "<pre>" + newdiv.innerHTML + "</pre>";
            }

            newdiv.innerHTML += '<br /><br />출처: <a href="' + url + '">' + decodedUrl + '</a> [명월 일지]';

            selection.selectAllChildren(newdiv);
            window.setTimeout(function () {
                body_element.removeChild(newdiv);
            }, 200);

        });
    })(tjQuery);
</script><script src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/lightbox.min.js"></script><div id="lightboxOverlay" class="lightboxOverlay" style="display: none;"></div><div id="lightbox" class="lightbox" style="display: none;"><div class="lb-outerContainer"><div class="lb-container"><img class="lb-image" src="data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw=="><div class="lb-nav"><a class="lb-prev" href="https://nowonbun.tistory.com/252?category=507115"></a><a class="lb-next" href="https://nowonbun.tistory.com/252?category=507115"></a></div><div class="lb-loader"><a class="lb-cancel"></a></div></div></div><div class="lb-dataContainer"><div class="lb-data"><div class="lb-details"><span class="lb-caption"></span><span class="lb-number"></span></div><div class="lb-closeContainer"><a class="lb-close"></a></div></div></div></div>
	<script>
	    lightbox.option({
			"fadeDuration": 200,
		    "resizeDuration": 200,
		    "wrapAround": false,
			"albumLabel": "%1 / %2",
			"fitImagesInViewport":true ,
			"stopEvent": false
	    })
	</script><script type="text/javascript">
var _tiq = 'undefined' !== typeof _tiq ? _tiq : [];
_tiq.push(['__setConfig', {enableScroll:true, enableClick:true, enableButton:true}]);
_tiq.push(["__setParam", "svcdomain", "user.tistory.com"]);
(function(d) {
	var se = d.createElement('script'); se.type = 'text/javascript'; se.async = true;
	se.src = location.protocol + '//m2.daumcdn.net/tiara/js/td.min.js';
	var s = d.getElementsByTagName('head')[0]; s.appendChild(se);
})(document);
_tiq.push(['__setParam', 'param_ex', JSON.stringify({"userId":"771442","blogId":"1041549","entryId":"252"})]);
_tiq.push(['__trackPageview']);
_tiq.push(['__content', 't_content', {
'c_id':'1041549_252', 
'c_title':'[MFC]네이버 실시간 검색 순위 CS프로그램 - 6', 
'type':'article', 
'author':'', 
'author_id':'771442', 
'cp':'명월 일지', 
'cp_id':'1041549', 
'regdata':'2013-08-09 09:00:00', 
'plink':'https://nowonbun.tistory.com/252', 
'media':'pcweb', 
}]);
var addEvent = function (evt, fn) { window.addEventListener ? window.addEventListener(evt, fn, false) : window.attachEvent('on' + evt, fn); };
var removeEvent = function(evt, fn) { window.removeEventListener ? window.removeEventListener(evt, fn, false) : window.detachEvent('on' + evt, fn);};
var ua = navigator.userAgent.toLowerCase(); var isIOS = /iP[ao]d|iPhone/i.test(ua);
var contentExStat = function() {
		_tiq.push(['__content', 't_content_ex', {
			'data_type':'usage'
, 'meta': {
'c_id':'1041549_252', 
'c_title':'[MFC]네이버 실시간 검색 순위 CS프로그램 - 6', 
'type':'article', 
'author':'', 
'author_id':'771442', 
'cp':'명월 일지', 
'cp_id':'1041549', 
'regdata':'2013-08-09 09:00:00', 
'plink':'https://nowonbun.tistory.com/252', 
'media':'pcweb', 
}
}]);
		removeEvent(isIOS ? 'pagehide' : 'beforeunload', contentExStat);
};
addEvent(isIOS ? 'pagehide' : 'beforeunload', contentExStat);
</script>
<script type="text/javascript">window.roosevelt_params_queue = window.roosevelt_params_queue || []; window.roosevelt_params_queue.push({channel_id: "dk", channel_label: 'tistory'});</script>
<script type="text/javascript" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/roosevelt_dk_bt.js" async=""></script><script type="text/javascript">if(window.console!=undefined){setTimeout(console.log.bind(console,"%cTISTORY","font:8em Arial;color:#EC6521;font-weight:bold"),0);setTimeout(console.log.bind(console,"%c  나를 표현하는 블로그","font:2em sans-serif;color:#333;"),0);}</script><iframe style="position:absolute;width:1px;height:1px;left:-100px;top:-100px" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/api.html" id="editEntry"></iframe><div id="layer_sns" class="layer_sns">
        <div class="bg_layer"></div>
        <ul class="list_sns">
            <li><a class="ico_sns ico_fb" data-service="facebook">페이스북 공유하기</a></li>
            <li><a class="ico_sns ico_kt" data-service="kakaotalk">카카오톡 공유하기</a></li>
            <li><a class="ico_sns ico_ks" data-service="kakaostory">카카오스토리 공유하기</a></li>
            <li><a class="ico_sns ico_tw" data-service="twitter">트위터 공유하기</a></li>
        </ul>
        <button type="button" class="btn_close"><span class="ico_sns ico_close">sns공유하기 레이어 닫기</span></button>
    </div>

<iframe id="google_osd_static_frame_1310910427550" name="google_osd_static_frame" style="display: none; width: 0px; height: 0px;" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/saved_resource(5).html"></iframe></body><iframe id="google_shimpl" style="display: none;" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/saved_resource(6).html"></iframe><iframe id="google_esf" name="google_esf" src="./명월 일지 __ [MFC]네이버 실시간 검색 순위 CS프로그램 - 6_files/zrt_lookup.html" data-ad-client="ca-pub-6300064900388375" style="display: none;"></iframe></html>
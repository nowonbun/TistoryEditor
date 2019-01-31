<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="theme-color" content="#29343a">
<title>Tistory Editor</title>
<!-- link rel="stylesheet" type="text/css" href="./css/blog/style.css"-->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="./css/blog/nowonbun.css">
<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" onclick="menu('left')"><span class="menuSpan">MENU</span></a> 
				<a class="navbar-brand title" href="/"><span>Tistory editor</span></a>
			</div>
		</nav>
	</header>
	<section class="headerspace"></section>
	<aside id="leftside">
		<h2 class="side-header">Menu</h2>
		<div class="side-menu bs-glyphicons">
			<ul class="bs-glyphicons-list">
			<li onclick="location.href='/';">
				<span class="glyphicon glyphicon-home" aria-hidden="true" title="홈으로"></span>
				<span>홈으로</span>
			</li>
			<li onclick="location.href='mailto:nowonbun@naver.com';">
				<span class="glyphicon glyphicon-envelope" aria-hidden="true" title="메일보내기"></span>
				<span>메일발송</span>
			</li>
			<li onclick="window.external.AddFavorite('http://nowonbun.tistory.com','☆~명월일지~☆');">
				<span class="glyphicon glyphicon-star-empty" aria-hidden="true" title="즐겨찾기"></span>
				<span>즐겨찾기</span>
			</li>
			<li onclick="location.href='[##_guestbook_link_##]';">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true" title="방명록"></span>
				<span>방명록</span>
			</li>
			<li onclick="menu('close');">
				<span class="glyphicon glyphicon-remove" aria-hidden="true" title="메뉴닫기"></span>
				<span>메뉴닫기</span>
			</li>
			</ul>
		</div>
		<div class="side-list">
			<ul class="side-nav"></ul>
		</div>
		<div class="side-footer bs-glyphicons">
			<ul class="bs-glyphicons-list">
				<li onclick="login();" class='logout on'>
					<span class="glyphicon glyphicon-log-in" aria-hidden="true" title="로그인"></span>
					<span>로그인</span>
				</li>
				<li onclick="logout();" class='login off'>
					<span class="glyphicon glyphicon-log-out" aria-hidden="true" title="로그인"></span>
					<span>로그아웃</span>
				</li>
				<li onclick="addLink();" class='login off'>
					<span class="glyphicon glyphicon-list-alt" aria-hidden="true" title="링크추가"></span>
					<span>링크추가</span>
				</li>
				<li onclick="writeTistory();" class='admin off'>
					<span class="glyphicon glyphicon-leaf" aria-hidden="true" title="글쓰기"></span>
					<span>글쓰기</span>
				</li>
				<li onclick="adminTistory();" class='admin off'>
					<span class="glyphicon glyphicon-cog" aria-hidden="true" title="관리"></span>
					<span>관리</span>
				</li>
			</ul>
		</div>
	</aside>
	<section class="backgroundLayout" onclick="menu('close');"></section>
	<main role="main" class="off">
	<!-- start:main -->
		<div id="main" class="container">
			<!-- 메인 -->
			<!-- 메인 -->
		</div>
	<!-- end:main -->
	</main>
	<!-- start:footer -->
	<footer>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 footer-bottom">
			<!-- 방문자수 -->
			<p>Tistory Editor</p>
			<p>Copyright © 2016 nowonbun.tistory.com</p>
		</div>
	</footer>
	<!-- end:footer -->
	<script src="./js/blog/nowonbun.js"></script>
</body>
</html>
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
<!-- https://getbootstrap.com/docs/3.3/getting-started/ -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css">
<link rel="stylesheet" type="text/css" href="./css/blog/nowonbun.css">
<link rel="stylesheet" type="text/css" href="./css/blog/loader.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<a class="navbar-brand menu-click" data-menu-type="open" href="javascript:void(0);"> <span class="menuSpan">MENU</span></a> <a class="navbar-brand title" href="/"> <span>Tistory editor</span>
				</a>
			</div>
		</nav>
	</header>
	<aside id="leftside">
		<h2 class="side-header">Menu</h2>
		<div class="side-menu bs-glyphicons">
			<ul class="bs-glyphicons-list">
				<li onclick="location.href='./';"><span class="glyphicon glyphicon-home" aria-hidden="true" title="홈으로"></span> <span>홈으로</span></li>
				<li onclick="location.href='mailto:nowonbun@naver.com';"><span class="glyphicon glyphicon-envelope" aria-hidden="true" title="메일보내기"></span> <span>메일발송</span></li>
				<li onclick="window.external.AddFavorite('http://nowonbun.tistory.com','☆~명월일지~☆');"><span class="glyphicon glyphicon-star-empty" aria-hidden="true" title="즐겨찾기"></span> <span>즐겨찾기</span></li>
				<li onclick="location.href='[##_guestbook_link_##]';"><span class="glyphicon glyphicon-pencil" aria-hidden="true" title="방명록"></span> <span>방명록</span></li>
				<li class="menu-click" data-menu-type="close"><span class="glyphicon glyphicon-remove" aria-hidden="true" title="메뉴닫기"></span> <span>메뉴닫기</span></li>
			</ul>
		</div>
		<div class="side-list">
			<ul class="side-nav side-menu-list"></ul>
		</div>
	</aside>
	<section class="backgroundLayout menu-click" data-menu-type="open"></section>
	<div class="loader off"></div>
	<section class="loader-layout off"></section>
	<main> <!-- start:main --> <!-- main-container	 -->
		<div id="main" class="main-container">
		<!-- 메인 -->
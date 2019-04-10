<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div class="searchList">
		<h3>
			<span>${title}</span> 검색결과
		</h3>
		<p>검색 건수 : ${count}건</p>
	</div>
	<div class="list-area"></div>
	<input type="hidden" id="pType" value="${pType}"> 
	<input type="hidden" id="pId" value="${pId}"> 
	<input type="hidden" id="count" value="${count}">
	<input type="hidden" id="pageCount" value="${pageCount}">
</div>
<template class="list-article">
<article class="list-item">
	<div class="list-row pos-right ratio-fixed ratio-4by3 crop-center lts-narrow fouc clearfix searchListEntity">
		<div class="list-body" style="width: 100%;">
			<div class="flexbox">
				<a class="list-link" href="">
					<h3 class="list-head ie-nanum ci-link"></h3>
				</a>
				<div class="list-meta ie-dotum">
					<a href="" target="_blank" class="p-category ci-color">블로그 원본 링크</a> <br /> 
					<span class="timeago ff-h dt-published tag-column"></span> <br />
					 <span class="timeago ff-h dt-published date-column"></span>
				</div>
			</div>
		</div>
	</div>
</article>
</template>
<!-- https://aljjabaegi.tistory.com/191 -->
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
	var _list$ = (function(obj) {
		obj.init();
		$(obj.onLoad);
		return obj;
	})({
		page : 0,
		count : Number($.trim($("#count").val())),
		pageCount : Number($.trim($("#pageCount").val())),
		init : function() {
			$(window).scroll(function() {
				if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
					if (_list$.count > _list$.page * _list$.pageCount) {
						_list$.getList();
					}
				}
			});
		},
		onLoad : function() {
			_list$.getList();
		},
		getList : function() {
			_common$.loading.on();
			if (_list$.count === 0) {
				var $article = $("<article class='no-list-item'></article>");
				var $entity = $("<div class='list-row pos-right ratio-fixed ratio-4by3 crop-center lts-narrow fouc clearfix searchListEntity'></div>");
				var $entity_body = $("<div style='width: 100%;text-align:center;'></div>");
				$entity_body.append("검색된 결과가 없습니다.");
				$(".list-area").html("");
				$entity.append($entity_body);
				$article.append($entity);
				$(".list-area").append($article);
				_common$.loading.off();
				return;
			}
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					page : _list$.page,
					type : $("#pType").val(),
					id : $("#pId").val()
				},
				url : "./list.ajax",
				success : function(data) {
					_common$.loading.off();
					for (var i = 0; i < data.length; i++) {
						var post = data[i];
						var $article = $($(".list-article").html());
						$article.find(".list-link").prop("href", "./post.html?idx=" + post.postIdx + "&id=" + post.postId);
						$article.find(".ci-link").text(post.title);
						$article.find(".p-category").prop("href", post.postUrl);
						try {
							$article.find(".tag-column").text("tag - " + JSON.parse(post.tags).tag.toString());
						} catch (e) {

						}

						$article.find(".date-column").text(post.date);
						$(".list-area").append($article);
					}
					_list$.page++;
				},
				error : function(e) {

				}
			});
		}
	});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
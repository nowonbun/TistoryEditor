<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div class="searchList">
		<h3>
			<span>블로그 설명서 및 수정이력</span> 검색결과
		</h3>
		<p>검색 건수 : 21건</p>
	</div>
	<c:forEach items="${list}" var="item">
		<article>
			<div class="list-row pos-right ratio-fixed ratio-4by3 crop-center lts-narrow fouc clearfix searchListEntity">
				<div class="list-body" style="width: 100%;">
					<div class="flexbox">
						<a class="list-link" href="/post.html?idx=${item.postIdx}&id=${item.postId}">
							<h3 class="list-head ie-nanum ci-link">${item.title}</h3>
						</a>
						<div class="list-meta ie-dotum">
							<a href="${item.postUrl}" target="_blank" class="p-category ci-color">블로그 원본 링크</a>
							<br />
							<span class="timeago ff-h dt-published">[${item.tags}]</span>
							<br /> 
							<span class="timeago ff-h dt-published">${item.date}</span>
						</div>
					</div>
				</div>
			</div>
		</article>
	</c:forEach>
</div>
<!-- https://aljjabaegi.tistory.com/191 -->
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<article class="entry">
	<div class="titleArea">
		<div class="title">${post.title}</div>
		<hr class="titileHr">
		<div class="categoryArea">
			<a href="./list.html?type=category&id=507115">${post.category}</a> &nbsp;${post.date}
		</div>
	</div>
	<div class="article">
		<div class="tt_article_useless_p_margin">
			${post.contents}
		</div>
		<hr />
		<div class="list-meta ie-dotum">
			<a href="${post.postUrl}" target="_blank" class="p-category ci-color">블로그 원본 링크</a> 
			<br /> 
			<span class="timeago ff-h dt-published tag-column">${post.tags}</span> 
		</div>
	</div>
</article>
<input type="hidden" id="idx" value="${post.idx}">
<input type="hidden" id="postId" value="${post.postId }">
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<!-- https://summernote.org/getting-started/ -->
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
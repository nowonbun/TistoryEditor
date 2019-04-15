<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<style>
.post-status{
    margin-left: 20px;
    padding: 5px;
    font-size: 1.2rem;
}
</style>
<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">초기화 확인</h4>
			</div>
			<div class="modal-body">
				<p>정말로 초기화하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-danger" id="init_btn">초기화</button>
			</div>
		</div>
	</div>
</div>
<div class="row" style="margin-bottom: 20px;">
	<div class="col-md-4 col-md-offset-8" style="text-align: right;">
		<button class="btn btn-success" id="add_btn">포스트 추가</button>
		<button class="btn btn-danger" data-toggle="modal" data-target="#myModal">Push 초기화</button>
	</div>
</div>
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div class="searchList">
		<h3>Push 등록 대기 리스트</h3>
		<p>
			건수 : <span id="list_count">${count}</span>건
		</p>
	</div>
	<div class="list-area"></div>
	<input type="hidden" id="count" value="${count}"> <input type="hidden" id="pageCount" value="${pageCount}">
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
					<a href="" target="_blank" class="p-category ci-color">블로그 원본 링크</a> <br /> <span class="timeago ff-h dt-published tag-column"></span> <br /> <span class="timeago ff-h dt-published date-column"></span>
				</div>
			</div>
		</div>
	</div>
</article>
</template>
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
	var _main$ = (function(obj) {
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
					if (_main$.count > _main$.page * _main$.pageCount) {
						_main$.getWaitingPost();
					}
				}
			});
			$("#add_btn").on("click", function() {
				location.href = "./create.html";
			});
			$("#init_btn").on("click", function() {
				_common$.loading.on();
				$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						page : _main$.page
					},
					url : "./initWaitlist.ajax",
					success : function(data) {
						_main$.count = 0;
						$("#list_count").text("0");
						_main$.getWaitingPost();
						$('#myModal').modal('hide');
						_common$.loading.off();
						toastr.success(data.message);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR);
						console.log(errorThrown);
						toastr.error("예상치 못한 에러가 발생했습니다. 로그를 확인해 주십시오.");
					},
					complete : function(jqXHR, textStatus) {
						_common$.loading.off();
					}
				});
			});
		},
		onLoad : function() {
			_main$.getWaitingPost();
		},
		getWaitingPost : function() {
			_common$.loading.on();
			if (_main$.count === 0) {
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
					page : _main$.page
				},
				url : "./getWaitlist.ajax",
				success : function(data) {
					_common$.loading.off();
					for (var i = 0; i < data.length; i++) {
						var post = data[i];
						var $article = $($(".list-article").html());
						$article.find(".list-link").prop("href", "./post.html?idx=" + post.postIdx + "&id=" + post.postId);
						$article.find(".ci-link").html(post.title);
						$status = $("<span class='post-status'></span>");
						if (post.status === 1) {
							$status.addClass("bg-info");
							$status.append("추가");
						} else if (post.status === 2) {
							$status.addClass("bg-warning");
							$status.append("수정");
						} else if (post.status === 3) {
							$status.addClass("bg-danger");
							$status.append("삭제");
						}
						$article.find(".ci-link").append($status);
						$article.find(".p-category").prop("href", post.postUrl);
						try {
							$article.find(".tag-column").text("tag - " + JSON.parse(post.tags).tag.toString());
						} catch (e) {

						}
						if($.trim(post.date) === ""){
							$article.find(".date-column").text("-");
						} else {
							$article.find(".date-column").text(post.date);	
						}
						$(".list-area").append($article);
					}
					_main$.page++;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(errorThrown);
					toastr.error("예상치 못한 에러가 발생했습니다. 로그를 확인해 주십시오.");
				}
			});
		}
	});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<style>
#title_txt {
	font-size: 25px;
}

#tag_txt {
	font-size: 18px;
}

#article_contents {
	min-height: calc(40vh);
}
</style>
<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">삭제 확인</h4>
			</div>
			<div class="modal-body">
				<p>정말로 삭제하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-danger" id="delete_btn">삭제</button>
			</div>
		</div>
	</div>
</div>
<div class="row" style="margin-bottom: 20px;">
	<div class="col-md-4 col-md-offset-8" style="text-align: right;">
		<button class="btn btn-success" id="modify_btn">수정</button>
		<button class="btn btn-danger" data-toggle="modal" data-target="#myModal">삭제</button>
	</div>
</div>
<article class="entry">
	<div class="titleArea">
		<div class="title" id="article_title">${post.title}</div>
		<hr class="titileHr">
		<div class="categoryArea">
			<a href="./list.html?type=category&id=${post.categoryId}" id="article_list_link">${post.category}</a> &nbsp;${post.date}
		</div>
	</div>
	<div class="article">
		<div class="tt_article_useless_p_margin" id="article_contents">${post.contents}</div>
		<hr />
		<div class="list-meta ie-dotum">
			<a href="${post.postUrl}" target="_blank" class="p-category ci-color">블로그 원본 링크</a> <br /> <span class="timeago ff-h dt-published tag-column" id="article_tag">${post.tags}</span>
		</div>
	</div>
</article>
<input type="hidden" id="idx" value="${post.idx}">
<input type="hidden" id="postId" value="${post.postId }">
<input type="hidden" id="mode" value="view">
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<!-- https://summernote.org/getting-started/ -->
<script>
	var _post$ = (function(obj) {
		obj.init();
		$(obj.onLoad);
		return obj;
	})({
		init : function() {
			$("#delete_btn").on("click", function() {
				_common$.loading.on();
				$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						idx : $("#idx").val(),
						postId : $("#postId").val()
					},
					url : "./deletePost.ajax",
					success : function(data) {
						if (data.ret) {
							toastr.error(data.message);
							_common$.setQueueMessage("error", data.message);
							location.href = $("#article_list_link").prop("href");
						}
					},
					error : function(e) {

					}
				});
			});
			$("#modify_btn").on("click", function() {
				_common$.loading.on();
				var mode = $("#mode").val();
				if ("view" === mode) {
					var title = $("#article_title").html();
					$("#article_title").html("");
					$("#article_title").append($("<input type='text' class='form-control' id='title_txt'>").val(title));
					$('#article_contents').summernote({
						height : $("#article_contents").height() + 100
					});
					var tag = $("#article_tag").html();
					$("#article_tag").html("");
					$("#article_tag").append($("<input type='text' class='form-control' id='tag_txt'>").val(tag));
					$("#mode").val("edit");
					_common$.loading.off();
					return;
				}
				if($.trim($("#title_txt").val()) === ""){
					toastr.warning("제목을 입력해 주십시오.");
					_common$.loading.off();
					return;
				}
				$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						idx : $("#idx").val(),
						postId : $("#postId").val(),
						title : $("#title_txt").val(),
						contents : $("#article_contents").summernote('code'),
						tags : $("#tag_txt").val()
					},
					url : "./modifyPost.ajax",
					success : function(data) {
						if (data.ret) {
							toastr.success(data.message);
							_common$.setQueueMessage("success", data.message);
							location.href = $("#article_list_link").prop("href");
						}
					},
					error : function(e) {

					}
				});
			});
		},
		onLoad : function() {

		}
	})
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<style>
#title_txt {
	font-size: 25px;
}

#tag_txt {
	font-size: 18px;
}
</style>
<div class="row" style="margin-bottom: 20px;">
	<div class="col-md-4 col-md-offset-8" style="text-align: right;">
		<button class="btn btn-success" id="add_btn">추가</button>
	</div>
</div>
<article class="entry">
	<div class="titleArea">
		<div class="title" id="article_title">
			<input type='text' class='form-control' id='title_txt'>
		</div>
		<hr class="titileHr">
		<div class="categoryArea">
			<select class="form-control" id="category_sel">
				<c:forEach items="${categoryselect}" var="item">
					<option value="${item.value}">${item.text}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="article">
		<div class="tt_article_useless_p_margin" id="article_contents"></div>
		<hr />
		<div class="list-meta ie-dotum">
			<span class="timeago ff-h dt-published tag-column" id="article_tag"> <input type='text' class='form-control' id='tag_txt'>
			</span>
		</div>
	</div>
</article>
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
	var _create$ = (function(obj) {
		obj.init();
		$(obj.onLoad);
		return obj;
	})({
		init : function() {
			$("#add_btn").on("click", function() {
				if($.trim($("#title_txt").val()) === ""){
					toastr.warning("제목을 입력해 주십시오.");
					return;
				}
				if($.trim($("#category_sel").val()) === "-1"){
					toastr.warning("카테고리를 선택해 주십시오.");
					return;
				}
				_common$.loading.on();
				$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						title : $("#title_txt").val(),
						category : $("#category_sel").val(),
						contents : $("#article_contents").summernote('code'),
						tags : $("#tag_txt").val()
					},
					url : "./createPost.ajax",
					success : function(data) {
						if (data.ret) {
							toastr.success(data.message);
							_common$.setQueueMessage("success", data.message);
							location.href = "./main.html";
						}
					},
					error : function(e) {

					}
				});
			});
		},
		onLoad : function() {
			_common$.loading.on();
			$('#article_contents').summernote({
				height : $("main").height() - 350
			});
			_common$.loading.off();
		}
	})
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
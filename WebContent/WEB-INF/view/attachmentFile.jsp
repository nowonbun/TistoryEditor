<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<div class="page-title">
	<h3>첨부 파일</h3>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<table id="table_id" class="display">
			<thead>
				<tr>
					<th>Idx</th>
					<th>Link</th>
					<th>Object</th>
					<th>Date</th>
					<th>state</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<div class="row" style="margin: 20px 0px;">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div class="col-xs-2">
			<select id="dataType" class="form-control">
				<option value="-1">-----</option>
				<option value="0">링크 등록</option>
				<option value="1">첨부 파일 등록</option>
			</select>
		</div>
		<div class="col-xs-4 link-area">
			<input type="text" class="form-control" id="link" placeholder="링크">
		</div>
		<div class="col-xs-4 file-area" style="display: none;">
			<input type="file" class="form-control" id="fileData" placeholder="파일 첨부">
		</div>
		<div class="col-xs-4">
			<input type="text" class="form-control" id="memo" placeholder="메모">
		</div>
		<div class="col-xs-2">
			<button class="btn btn-success" id="apply">등록</button>
		</div>
	</div>
</div>
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
	var _attach$ = (function(obj) {
		obj.init();
		$(obj.onLoad);
		return obj;
	})({
		columns : [ {
			data : "idx"
		}, {
			data : "link",
			render : function(data) {
				return "<a href='"+data+"' target=_blank>" + data + "</a>";
			}
		}, {
			data : "objectData",
			render : function(data) {
				if ($.trim(data) === "") {
					return "";
				}
				return "<a href='./getAttachment.html?idx=" + data + "' target=_blank>link</a>";
			}
		}, {
			data : "date",
		}, {
			data : "state",
			render : function(data) {
				if ($.trim(data) === "") {
					return "";
				}
				return "<a href='./syncAttachment.html?idx=" + data + "' target=_blank>sync</a>";
			}
		} ],
		datatable : null,
		init : function() {
			$("#dataType").on("change", function() {
				if ($("#dataType option:selected").val() === "1") {
					$(".file-area").show();
					$(".link-area").hide();
					$(".link-area").val("");
					return;
				}
				$(".file-area").hide();
				$(".link-area").show();
				$(".file-area").val("");
			});
			$("#apply").on("click", function() {
				if ($("#dataType option:selected").val() === "-1") {
					toastr.error('등록 타입을 선택하여 주십시오.');
					return;
				}
				if ($("#dataType option:selected").val() === "0") {
					if ($.trim($("#link").val()) === "") {
						toastr.error('링크를 등록하여 주십시오.');
					}
					_attach$.apply(null);
				}
				if ($("#dataType option:selected").val() === "1") {
					if ($.trim($("#fileData").val()) === "") {
						toastr.error('파일를 등록하여 주십시오.');
					}
					_attach$.readFile(_attach$.apply);
				}

			});
		},
		apply : function(filedata) {
			_common$.loading.on();
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					type : $("#dataType option:selected").val(),
					link : $("#link").val(),
					memo : $("#memo").val(),
					data : filedata
				},
				url : "./applyAttachment.ajax",
				success : function(data) {
					if (data.ret) {
						toastr.success(data.message);
						_attach$.datatable.ajax.reload();
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(errorThrown);
					toastr.error("예상치 못한 에러가 발생했습니다. 로그를 확인해 주십시오.");
				},
				complete : function(jqXHR, textStatus) {
					$("#dataType").val(-1);
					$("#link").val("");
					$("#memo").val("");
					$("#fileData").val("");
					_common$.loading.off();
				}
			});
		},
		onLoad : function() {
			_attach$.datatable = $('#table_id').DataTable({
				ajax : "./attachmentList.ajax",
				columns : _attach$.columns
			});
		},
		readFile : function(cb) {
			var reader = new FileReader();
			reader.onload = function(e) {
				cb(reader.result);
			}
			var file = $("#fileData")[0].files[0];
			reader.readAsDataURL(file);
		}
	});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>

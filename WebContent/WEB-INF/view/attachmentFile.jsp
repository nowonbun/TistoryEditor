<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div class="col-xs-2">
			<select id="dataType">
				<option value="-1">&lt;-- Please select one --&gt;</option>
				<option value="0">0</option>
				<option value="1">1</option>
			</select>
		</div>
		<div class="col-xs-2"><input type="text" class="form-control" id="postid"></div>
		<div class="col-xs-6"><input type="text" class="form-contrl" id="data"> </div>
		<div class="col-xs-2"><button class="form-control"></button></div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<table id="table_id" class="display">
			<thead>
				<tr>
					<th>Idx</th>
					<th>PostID</th>
					<th>Link</th>
					<th>Object</th>
					<th>Date</th>
					<th>state</th>
				</tr>
			</thead>
		</table>
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
			data : "postId"
		}, {
			data : "link"
		}, {
			data : "objectData"
		}, {
			data : "date",
		}, {
			data : "state"
		} ],
		init : function() {

		},
		onLoad : function() {
			$('#table_id').DataTable({
				ajax : "./attachmentList.ajax",
				columns : _attach$.columns
			});
		},
	});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>

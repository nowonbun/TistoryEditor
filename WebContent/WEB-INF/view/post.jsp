<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>

<div id="summernote"></div>
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
$(function(){
	$('#summernote').summernote({
	    placeholder: 'Hello bootstrap 4',
	    tabsize: 2,
	    height: 100
	  });
});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>
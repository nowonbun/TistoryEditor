<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
</body>
<script>
	var _main$ = (function(obj) {
		obj.init();
		$(obj.onLoad);
		return obj;
	})({
		init : function() {

		},
		onLoad : function() {

		},
		getMenu : function() {
			$.ajax({
				url : "menu.ajax",
				type : "POST",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					console.log(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(errorThrown);
					toastr.error("system error!");
				},
				complete : function(jqXHR, textStatus) {

				}
			});
		}
	});
</script>
</html>
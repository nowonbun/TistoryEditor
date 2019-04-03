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
					var $side_menu = $(".side-menu-list");
					$side_menu.html("");
					for (var i = 0; i < data.length; i++) {
						var blog = data[i];
						var $blog = $("<li></li>");
						if ($blog.child != null && $blog.child.length > 0) {
							$blog.append($("<a class='link_item' href='javascript:void(0)'><i class='glyphicon glyphicon-triangle-bottom pull-right menu-toggle'></i></a>").append(blog.name));
							$category_ui = $("<ul class='sub_category_list off'></ul>");
							for (var j = 0; j < $blog.child.length; j++) {
								var category = $blog.child[j];
								var $category_li = $("<li class=''></li>");
								//TODO: The link need be added for this here.
								var $category_anchor = $("<a class='link_sub_item'></a>").prop("href","").append(category.name);
								$category_li.append($category_anchor);
								$category.append($category_li);
							}
							$blog.append($category);
						} else {
							//TODO: The link need be added for this here.
							$blog.append($("<a class='link_item' href='javascript:void(0)'><i class='glyphicon glyphicon-triangle-bottom pull-right menu-toggle'></i></a>").append(blog.name));
						}

						$side_menu.append($blog);
					}

					$side_menu.append("<li><a class='link_item' href=''./admin.html'>관리</a></li>");

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
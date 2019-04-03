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
						if (blog.child != null && blog.child.length > 0) {
							$blog.append($("<a class='link_item' href='javascript:void(0)'><i class='glyphicon glyphicon-triangle-bottom pull-right menu-toggle'></i></a>").append(blog.name));
							var $category_ul = $("<ul class='sub_category_list off'></ul>");
							for (var j = 0; j < blog.child.length; j++) {
								var category = blog.child[j];
								var $category_li = $("<li class=''></li>");
								var $category_anchor = $("<a class='link_sub_item'></a>");
								if (category.child != null && category.child.length > 0) {
									$category_anchor.prop("href", "javascript:void(0)");
									$category_anchor.append("<i class='glyphicon glyphicon-triangle-bottom pull-right menu-toggle'></i>")
									$category_anchor.append(category.name);
									var $sub_ul = $("<ul class='sub_category_list off'></ul>");
									for (var z = 0; z < category.child.length; z++) {
										var sub = category.child[z];
										var $sub_li = $("<li class=''></li>");
										var $sub_anchor = $("<a class='link_sub_item'></a>").prop("href", "javascript:void(0)").append(sub.name);
										$sub_li.append($sub_anchor);
										$sub_ul.append($sub_li);			
									}
									$category_li.append($sub_ul);
								} else {
									//TODO: The link need be added for this here.
									$category_anchor.prop("href", "javascript:void(0)");
									$category_anchor.append(category.name);
								}
								$category_li.append($category_anchor);
								$category_ul.append($category_li);
							}
							$blog.append($category_ul);
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
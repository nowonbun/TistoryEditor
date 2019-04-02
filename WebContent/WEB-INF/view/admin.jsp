<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./particle/top.jsp"></jsp:include>
<style>
.card {
	height: 7.5rem;
	border: 1px solid #dff0d8;
	padding: 1.0rem 2.0rem;
	border-radius: 5px;
	font-size: 2.5rem;
	box-shadow: 2px 4px 8px #cecece;
}
.card.disabled{
	background-color: #b9b9b9;
	border: 1px solid #b9b9b9;
}

.progress {
	height: 7.5rem;
	border: 1px solid #dff0d8;
	padding: 1.0rem 2.0rem;
	border-radius: 5px;
	font-size: 2.5rem;
	box-shadow: 2px 4px 8px #cecece;
}

.progress .progress-bar {
	font-size: 2.5rem;
	padding-top: 2rem;
	color: #000;
	background-image: -webkit-linear-gradient(top, #dff0d8 0, #dff0d8 100%);
	background-color: #dff0d8;
	border: 1px solid #dff0ff;
}

.card .footer {
	font-size: 1.5rem;
	text-align: right;
	font-weight: 600;
}

.card:hover:not(.disabled){
	cursor: pointer;
	box-shadow: 4px 8px 16px #cecece;
}

.card:hover:not(.disabled) .glyphicon-refresh {
	-webkit-animation: fa-spin 2s infinite linear;
	animation: fa-spin 2s infinite linear;
}

div.row>div {
	margin-bottom: 1rem;
}
</style>
<div class="row">
	<div class="col-xs-12 col-sm-6 col-md-3">
		<div class="card bg-success pull-btn card-btn">
			<div class="header">
				<i class="glyphicon glyphicon-refresh"></i>
			</div>
			<div class="footer">
				<span>Blog pull post </span>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 col-md-3">
		<div class="card bg-success push-btn card-btn">
			<div class="header">
				<i class="glyphicon glyphicon-refresh"></i>
			</div>
			<div class="footer">
				<span>Blog push post </span>
			</div>
		</div>
	</div>
	<!-- div class="col-xs-12 col-sm-6">
		<div class="progress">
			<div class="progress-bar syc-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
	</div-->
</div>
<div class="row">
	<div class="col-xs-12 col-sm-6">
		<input type="text" class="form-control" readonly="readonly" id="status">
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-6">
		<div class="input-group">
			<span class="input-group-addon">Last updated time</span>
			<input id="timestamp" class="form-control" readonly="readonly">
		</div>
	</div>
</div>
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
	var buffering = 0;
	var _admin$ = (function(obj) {
		obj.init();
		$(obj.onLoad)
		return obj;
	})({
		isRunning : false,
		init : function() {
			$(".pull-btn").on("click", function() {
				if (!$(".card-btn").hasClass("disabled")) {
					window.location.href = "./syncstart.auth?type=pull";
				} else {
					//toastr.error('The sync is running!', 'Sync error');
				}
			});
		},
		onLoad : function() {
			_admin$.sync.getStatus();
			//$(".syc-progress").html("Ready...");
			/*$(".syn-btn").on("click", function() {
				_admin$.sync.start();
			});*/
		},
		sync : {
			ajax : function(url, data, cb) {
				$.ajax({
					url : url,
					type : "POST",
					dataType : "json",
					data : data,
					success : function(data, textStatus, jqXHR) {
						cb.call(this, data);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR);
						console.log(errorThrown);
						toastr.error("system error!");
					},
					complete : function(jqXHR, textStatus) {

					}
				});
			},
			getStatus : function() {
				_admin$.sync.ajax("./syncStatus.ajax", null, function(data) {
					//console.log(data);
					if (data.state !== "wait") {
						$(".card-btn").addClass("disabled");
						_admin$.isRunning = true;
					} else {
						$(".card-btn").removeClass("disabled");
						if (_admin$.isRunning) {

						}
						_admin$.isRunning = false;
					}
					$("#status").val("State: " + data.state + ", Message: " + data.messgae);
					$("#timestamp").val(moment(new Date).format("YYYY-MM-DD HH:mm:ss"));
					setTimeout(function() {
						_admin$.sync.getStatus();
					}, 500);
				});
			}
		}
	/*sync : {
		ajax : function(url, data, cb) {
			$.ajax({
				url : url,
				type : "POST",
				dataType : "json",
				data : data,
				success : function(data, textStatus, jqXHR) {
					cb.call(this, data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					console.log(errorThrown);
					toastr.error("system error!");
				},
				complete : function(jqXHR, textStatus) {

				}
			});
		},
		start : function() {
			console.log("start");
			if (!$(".syn-btn").hasClass("start")) {
				$(".syn-btn").addClass("start");
				$(".syn-btn").addClass("bg-info");
				$(".syn-btn").removeClass("bg-success");
				_admin$.sync.ajax("./sync.ajax", null, function() {
					setTimeout(_admin$.sync.interval, 1000);
				});
			}
		},
		interval : function() {
			if (buffering < 10) {
				$(".syc-progress").html("Process...");
				buffering++;
				$(".progress-bar").css("width", (buffering * 10) + "%");
				setTimeout(_admin$.sync.interval, 1000);
			} else {
				buffering = 0;
				$(".syc-progress").html("Ready...");
				$(".syn-btn").removeClass("start");
				$(".syn-btn").removeClass("bg-info");
				$(".syn-btn").addClass("bg-success");
				$(".progress-bar").css("width", "0");
			}

		}
	}*/
	});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>

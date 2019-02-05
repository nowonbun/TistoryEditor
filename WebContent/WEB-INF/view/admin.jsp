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
.progress{
	height: 7.5rem;
    border: 1px solid #dff0d8;
    padding: 1.0rem 2.0rem;
    border-radius: 5px;
    font-size: 2.5rem;
    box-shadow: 2px 4px 8px #cecece;
}
.progress .progress-bar{
	font-size: 2.5rem;
    padding-top: 2rem;
    color:#000;
	background-image: -webkit-linear-gradient(top,#dff0d8 0,#dff0d8 100%);
	background-color: #dff0d8;
	border: 1px solid #dff0ff;
}
.card .footer{
	font-size: 1.5rem;
    text-align: right;
    font-weight: 600;
}
.card:hover:not(.start){
	cursor: pointer;
	box-shadow: 4px 8px 16px #cecece;
}
.card:hover:not(.start) .glyphicon-refresh
{
    -webkit-animation: fa-spin 2s infinite linear;
    animation: fa-spin 2s infinite linear;
}
div.row>div{
	margin-bottom: 1rem;
}
</style>
<div class="row">
	<div class="col-xs-12 col-sm-6 col-md-3">
		<div class="card bg-success syn-btn">
			<div class="header"><i class="glyphicon glyphicon-refresh"></i></div>
			<div class="footer"><span>Blog post sync</span></div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-6">
		<div class="progress">
		  <div class="progress-bar syc-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
	</div>
</div>
hello world
<jsp:include page="./particle/bottom.jsp"></jsp:include>
<script>
var buffering = 0;
var _$ = (function(obj){
	$(obj.onLoad)
	return obj;
})({
	onLoad: function(){
		$(".syc-progress").html("Ready...");
		$(".syn-btn").on("click", function(){
			_$.sync.start();
		});
	},
	sync:{
		start: function(){
			console.log("start");
			if(!$(".syn-btn").hasClass("start")){
				$(".syn-btn").addClass("start");
				$(".syn-btn").addClass("bg-info");
				$(".syn-btn").removeClass("bg-success");
				setTimeout(_$.sync.interval,1000);
			}
		},
		interval: function(){
			if(buffering < 10){
				$(".syc-progress").html("Process...");
				buffering++;
				$(".progress-bar").css("width",(buffering*10)+"%");
				setTimeout(_$.sync.interval,1000);	
			}else{
				buffering = 0;
				$(".syc-progress").html("Ready...");
				$(".syn-btn").removeClass("start");
				$(".syn-btn").removeClass("bg-info");
				$(".syn-btn").addClass("bg-success");
				$(".progress-bar").css("width","0");
			}
			
		}
	} 
});
</script>
<jsp:include page="./particle/bottom2.jsp"></jsp:include>

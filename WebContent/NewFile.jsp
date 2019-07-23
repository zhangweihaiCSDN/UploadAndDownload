<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax测试</title>
	<script src="static/js/jquery-3.4.1/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		function loadXMLDoc () {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp=new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function() {
				if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			    	document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
			    }
			}
			xmlhttp.open("GET","AjaxController",true);
			xmlhttp.send();
		}
		/* $(document).ready(function(){
			$("div").click(function(){
				$(this).hide();
			});
		}); */
		$(function () {
			$("div").click(function(){
				$(this).hide();
			})
		});
	</script>
</head>
<body>
	<div id="myDiv"><h3>Let AJAX change this text</h3></div>
	<button type="button" onclick="loadXMLDoc()">Change Content</button>
</body>
</html>
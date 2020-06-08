<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title><sitemesh:write property='title'/></title>
	<link rel="stylesheet" href="<c:url value="/fontawesome/css/all.css" />" >
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.css" />">
	<link rel="stylesheet" href="<c:url value="/css/jquery-ui.css" />">
	<link rel="stylesheet" href="<c:url value="/css/ui.jqgrid-bootstrap4.css" />">
	<link rel="stylesheet" href="<c:url value="/css/easyui.css" />">
	<link rel="stylesheet" href="<c:url value="/css/icon.css" />">
	<link rel="stylesheet" href="<c:url value="/css/default.css" />">
	<script src="<c:url value="/js/jquery-3.4.1.js" />"></script>
	<script src="<c:url value="/js/bootstrap.js" />"></script>
	<script src="<c:url value="/js/jquery.easyui.min.js" />"></script>
	<script src="<c:url value="/js/jquery.validate.js" />"></script>
	<script src="<c:url value="/js/localization/messages_zh_TW.js" />"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-sm navbar-light bg-light navbar-dark bg-dark flex-md-row fixed-top">
			<a class="navbar-brand" href="<c:url value="/" />">
				<img src="images/img01.png" width="30" height="30" class="d-inline-block align-top" alt="">
				GarySystem
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav" id="menu">
					<li class="nav-item"><a class="nav-link" href="<c:url value="/F010001_SCN1" />">蝦皮測試</a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value="/F010002_SCN1" />">全家PDF切割</a></li>
				</ul>
			</div>
		</nav>
	</header>
	
	<main>
		<sitemesh:write property='body'/>
	</main>
	

	<!-- script -->
    
    <script>
    	
    </script>
</body>
</html>
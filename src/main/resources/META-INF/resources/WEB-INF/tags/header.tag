<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="scriptlibs" required="false"%>
<%@ attribute name="scripts" required="false"%>
<%@ attribute name="styles" required="false"%>
<%@ attribute name="title" required="true"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="jsPath" value="${contextPath}/js/" />
<c:set var="jsLib" value="${contextPath}/js/lib/" />
<c:set var="cssPath" value="${contextPath}/css/" />
<c:set var="img" value="${contextPath}/img/" />
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"
	charset="UTF-8" />
<c:forEach items="${scriptlibs}" var="js">
	<script type="text/javascript" src="${jsLib}${fn:trim(js)}"></script>
</c:forEach>
<c:forEach items="${scripts}" var="js">
	<script type="text/javascript" src="${jsPath}${fn:trim(js)}"></script>
</c:forEach>
<c:forEach items="${styles}" var="css">
	<link rel="stylesheet" type="text/css" href="${cssPath}${fn:trim(css)}" />
</c:forEach>
<link rel="stylesheet" type="text/css" href="${cssPath}main.css" />
<link rel="stylesheet" type="text/css"
	href="${cssPath}materialize.min.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="/img/favicon.ico" rel="shortcut icon">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<script src="${jsPath}jquery.js"></script>
<script src="${jsPath}materialize.min.js"></script>
<script src="${jsPath}inicializer.js"></script>
</head>
<body>

	<c:set value="${contextPath }/aluno/form" var="cadastro" />
	<c:set value="${contextPath }/aluno" var="lista" />
	<c:set value="${contextPath }/firebase" var="firebase" />
	<c:set value="${contextPath }/" var="index" />

	<header class="header">
		<div class="navbar-fixed">
			<nav>
				<div class="nav-wrapper white">
					<a href="${index}" class="brand-logo"> <img id="logo-header"
						src="${img}logo-alura.svg">
					</a> <a href="#" data-activates="mobile-demo" class="button-collapse"><i
						class="material-icons icon-font-default-color">menu</i></a>
					<ul class="right hide-on-med-and-down main-nav">
						<li><a href="${cadastro}">Cadastro</a></li>
						<li><a href="${firebase}">Firebase</a></li>
						<li><a href="${lista}">Lista</a></li>
					</ul>
					<ul class="side-nav main-nav" id="mobile-demo">
						<li><a href="${cadastro}">Cadastro</a></li>
						<li><a href="${firebase}">Firebase</a></li>
						<li><a href="${lista}">Lista</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</header>
	<main class="container">
	<h2 class="main-title center">${title }</h2>
	<br />
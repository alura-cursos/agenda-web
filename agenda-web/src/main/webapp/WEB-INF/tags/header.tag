<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="scriptlibs" required="false"%>
<%@ attribute name="scripts" required="false"%>
<%@ attribute name="styles" required="false"%>
<%@ attribute name="title" required="true"%>
<c:url var="jsPath" value="/resources/js/" />
<c:url var="jsLib" value="/resources/js/lib/" />
<c:url var="cssPath" value="/resources/css/" />
<c:url var="url" value="/" />
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<script>
	var url = "${url}";
</script>
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
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>

	<c:url value="/aluno/form" var="cadastro" />
	<c:url value="/aluno" var="lista" />
	<c:url value="/" var="index" />

	<header class="header">
		<nav>
			<div class="nav-wrapper black">
				<a href="${index}" class="brand-logo"><img id="logo-header"
					src="<c:url value='/resources/img/alura-logo-white.png'></c:url>"></a>
				<ul id="nav-mobile" class="right hide-on-med-and-down">
					<li><a href="${lista}">Alunos</a></li>
					<li><a href="${cadastro}">Cadastrar</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<main class="container">
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="scriptlibs" required="false"%>
<%@ attribute name="scripts" required="false"%>
<%@ attribute name="styles" required="false"%>
<%@ attribute name="title" required="true"%>
<c:url var="jsPath" value="/js/" />
<c:url var="jsLib" value="/js/lib/" />
<c:url var="cssPath" value="/css/" />
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
<link rel="stylesheet" type="text/css"
	href="${cssPath}materialize.min.css" />
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script src="${jsPath}/jquery.js"></script>
<script src="${jsPath}/materialize.min.js"></script>
<script src="${jsPath}/inicializer.js"></script>
</head>
<body>

	<c:url value="/aluno/form" var="cadastro" />
	<c:url value="/aluno" var="lista" />
	<c:url value="/firebase" var="firebase" />
	<c:url value="/" var="index" />

	<header class="header">
		<div class="navbar-fixed">
			<nav>
				<div class="nav-wrapper black z-depth-3">
					<a href="${index}" class="brand-logo"><img id="logo-header"
						src="<c:url value='/img/alura-logo-white.png'></c:url>"></a> <a
						href="#" data-activates="mobile-demo" class="button-collapse"><i
						class="material-icons">menu</i></a>
					<ul class="right hide-on-med-and-down">
						<li><a href="${cadastro}">Cadastro</a></li>
						<li><a href="${firebase}">Firebase</a></li>
						<li><a href="${lista}">Lista</a></li>
					</ul>
					<ul class="side-nav" id="mobile-demo">
						<li><a href="${cadastro}">Cadastro</a></li>
						<li><a href="${firebase}">Firebase</a></li>
						<li><a href="${lista}">Lista</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</header>
	<main class="container">
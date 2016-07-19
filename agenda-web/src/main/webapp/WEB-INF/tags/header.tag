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
<script   src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
<meta charset="UTF-8">
<c:forEach items="${scriptlibs}" var="js">
	<script type="text/javascript" src="${jsLib}${fn:trim(js)}"></script>
</c:forEach>
<c:forEach items="${scripts}" var="js">
	<script type="text/javascript" src="${jsPath}${fn:trim(js)}"></script>
</c:forEach>
<c:forEach items="${styles}" var="css">
	<link rel="stylesheet" type="text/css" href="${cssPath}${fn:trim(css)}" />
</c:forEach>
</head>
<body>
	<main class="container"> <header class="header">Agenda
		de alunos - Alura</header>
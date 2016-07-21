<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header styles="materialize.css" scripts="materialize.js"
	title="Lista de alunos"></html:header>

<h1 class="header center">Alunos</h1>

<ul class="collection">
	<c:forEach items="${alunos}" var="aluno">
		<html:avatar aluno="${aluno}" />
	</c:forEach>
</ul>

<html:footer></html:footer>
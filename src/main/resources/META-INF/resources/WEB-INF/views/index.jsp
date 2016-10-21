<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header styles="materialize.css" scripts="materialize.js"
	title="Agenda de alunos"></html:header>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set value="${contextPath }/aluno" var="lista" />
<c:set value="${contextPath }/firebase" var="configuracao_firebase" />
<c:set value="${contextPath }/aluno/form" var="cadastro" />

<div class="row center">
	<a href="${cadastro}">
		<div class="col s12 m4 l4  waves-effect waves-light">
			<div class="card-panel hoverable z-depth-1 center">
				<i class="large material-icons ">perm_identity </i>
				<div class="truncate">Cadastro de aluno</div>
			</div>
		</div>
	</a> <a href="${configuracao_firebase}">
		<div class="col s12 m4 l4  waves-effect waves-light ">
			<div class="card-panel hoverable z-depth-1 center ">
				<i class="large material-icons ">settings </i>
				<div class="truncate">Configuração do Firebase</div>
			</div>
		</div>
	</a> <a href="${lista}">
		<div class="col s12 m4 l4 waves-effect waves-light">
			<div class="card-panel hoverable z-depth-1 center">
				<i class="large material-icons ">list</i>
				<div class="truncate">Lista de alunos</div>
			</div>
		</div>
	</a>
</div>


<html:footer></html:footer>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header styles="materialize.css" scripts="materialize.js"
	title="Agenda de alunos"></html:header>

<c:url value="/aluno" var="lista"></c:url>
<c:url value="/firebase" var="configuracao_firebase"></c:url>
<c:url value="/aluno/form" var="cadastro"></c:url>

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
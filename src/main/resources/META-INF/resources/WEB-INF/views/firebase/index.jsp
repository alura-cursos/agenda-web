<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header title="Firebase"></html:header>

<c:url value="firebase/config" var="firebase_config" />
<c:url value="firebase/mensagem" var="firebase_mensagem" />

<h1 class="header center">Firebase</h1>

${sucesso }

<div class="row center">
	<a href="${firebase_config }">
		<div class="col s12 m6">
			<div class="card-panel hoverable z-depth-1 center">
				<i class="large material-icons ">settings</i>
				<div class="header ">Configuração</div>
			</div>
		</div>
	</a> <a href="${firebase_mensagem }">
		<div class="col s12 m6">
			<div class="card-panel hoverable z-depth-1 ">
				<i class="large material-icons">message</i>
				<div class="header ">Mensagem</div>
			</div>
		</div>
	</a>
</div>
</div>

<html:footer></html:footer>
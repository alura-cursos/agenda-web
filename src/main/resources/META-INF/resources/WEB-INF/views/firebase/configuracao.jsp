<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header title="Configuração"></html:header>

<c:url value='/firebase' var="actionUrl" />

<h1 class="header center">Configuração - Firebase</h1>

<c:if test="${info != null}">
	<script>
		Materialize.toast('${info}', 4000)
	</script>
</c:if>

<div class="row">
	<form:form action="${actionUrl}" commandName="firebaseConfig"
		class="col s12">

		<html:input label="API Key" name="apikey" />
		<html:input label="API URL" name="URL" />
		<form:button class="btn waves-effect waves-light">Salvar</form:button>
	</form:form>
</div>


<html:footer></html:footer>
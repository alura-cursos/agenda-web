<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header title="Configuração Firebase"></html:header>

<c:url value='/firebase' var="actionUrl" />

<c:choose>
	<c:when test="${sucesso != null}">
		<script>
			Materialize.toast('${sucesso}', 4000)
		</script>
		<c:set var="icon" value="done" />
		<c:set var="show" value="true" />
	</c:when>
	<c:when test="${falha != null}">
		<script>
			Materialize.toast('${falha}', 4000)
		</script>
		<c:set var="icon" value="error" />
		<c:set var="show" value="true" />
	</c:when>
	<c:otherwise>
		<c:set var="show" value="false" />
	</c:otherwise>
</c:choose>

<div class="row">
	<form:form action="${actionUrl}" commandName="config" class="col s12">

		<html:input label="API Key" name="apikey" icon="${icon}"
			show_icon="${show}" />
		<html:input label="API URL" name="URL" />
		<form:button class="btn waves-effect waves-light">Salvar</form:button>
	</form:form>
</div>


<html:footer></html:footer>
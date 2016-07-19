<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value='/aluno' var="actionUrl" />

<html:header styles="materialize.min.css"
	scripts="jquery.js, materialize.min.js" title="Cadastro"></html:header>

<div class="row">
	<form:form action="${actionUrl}" commandName="aluno" class="col s12">

		<html:input label="Nome" name="nome" />
		<html:input label="Endereço" name="endereco" />
		<html:input label="Telefone" name="telefone" />
		<html:input label="Site" name="site" />
		
		<form:select path="nota">
			<form:option value="1" />
			<form:option value="2" />
			<form:option value="3" />
			<form:option value="4" />
			<form:option value="5" />
		</form:select>
		<form:button class="btn waves-effect waves-light">Cadastrar</form:button>
	</form:form>
</div>

<html:footer></html:footer>
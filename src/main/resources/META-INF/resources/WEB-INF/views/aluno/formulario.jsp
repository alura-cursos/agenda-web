<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value='/aluno' var="actionUrl" />

<html:header title="Cadastro"></html:header>

<h1 class="header center ">Cadastro de aluno</h1>




<div class="row">
	<form:form action="${actionUrl}" commandName="aluno" class="col s12">
		<c:if test="${aluno.id != null }">
			<form:input type="hidden" path="id" value="${aluno.id }"></form:input>
		</c:if>
		<html:input label="Nome" name="nome" />
		<html:input label="Endereço" name="endereco" />
		<html:input label="Telefone" name="telefone" />
		<html:input label="Site" name="site" />
		<div class="input-field col s12">
			<form:select path="nota">
				<form:options items="${notas}" />
			</form:select>
			<label for="nota">Nota</label>
		</div>
		<form:button class="btn waves-effect waves-light">Cadastrar</form:button>
	</form:form>
</div>



<html:footer></html:footer>
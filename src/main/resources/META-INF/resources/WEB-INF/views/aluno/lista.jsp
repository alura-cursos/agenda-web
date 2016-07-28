<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html:header scripts="aluno/deletar_aluno.js"
	styles="aluno/lista_aluno.css" title="Lista de alunos">
</html:header>

<h1 class="header center">Alunos</h1>

<c:if test="${info != null}">
	<script>
		Materialize.toast('${info}', 4000)
	</script>
</c:if>

<c:if test="${alunos.size() > 0}">
	<ul class="collection">
		<c:forEach items="${alunos}" var="aluno">
			<html:avatar aluno="${aluno}" />
		</c:forEach>
	</ul>
</c:if>

<html:footer></html:footer>
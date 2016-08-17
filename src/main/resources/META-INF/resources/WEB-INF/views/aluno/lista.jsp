<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="html" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/aluno/form" var='form'></c:url>

<html:header scripts="aluno/deletar_aluno.js"
	styles="aluno/lista_aluno.css" title="Alunos">
</html:header>

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

<div class="fixed-action-btn horizontal"
	style="bottom: 45px; right: 40px;">
	<a href="${form }" class="btn-floating btn-large red waves-effect waves-light"> <i
		class="large material-icons">add</i>
	</a>
</div>

<html:footer></html:footer>
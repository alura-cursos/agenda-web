<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="aluno" required="true"
	type="br.com.caelum.alura.core.agenda.model.Aluno"%>
<c:url value='/resources/img/alura-logo-black.png' var="foto" />
<c:url value='aluno' var="edit" />

<li class="collection-item avatar"><img src="${foto}" alt=""
	class="circle"> <span class="title">${aluno.nome}</span>
	<p>${aluno.telefone }</p></li>
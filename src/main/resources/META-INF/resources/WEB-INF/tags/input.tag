<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="mask" required="false"%>
<%@ attribute name="icon" required="false"%>
<%@ attribute name="show_icon" required="false"%>

<div class="row">
	<div class="input-field col s12">
		<c:if test="${show_icon}">
			<i class="material-icons prefix">${icon}</i>
		</c:if>
		<form:input path="${name}" data-mask="${mask}" cssClass="input" />
		<form:label path="${name}" cssClass="label" data-success="teste">${label}</form:label>
	</div>
</div>
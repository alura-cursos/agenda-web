<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="mask" required="false"%>

<div class="row">
	<div class="input-field col s12">
		<form:input path="${name}" data-mask="${mask}" cssClass="input" />
		<form:label path="${name}" cssClass="label">${label}</form:label>
	</div>
</div>
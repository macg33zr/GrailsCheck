<%@ page import="grailscheck.MyDom" %>



<div class="fieldcontain ${hasErrors(bean: myDomInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="myDom.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${myDomInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: myDomInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="myDom.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${myDomInstance?.description}"/>
</div>


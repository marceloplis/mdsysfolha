<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Busca pelo nome do usuário (informe pelo menos 3 caracteres)</h4>
	<hr></hr> 
		
	<html:form action="/secure/admin/usuario?method=buscar" styleId="form">		
		<label>Nome:</label>
		<html:text property="nmBusca" styleId="nmBusca"/>
		<br/>
		<html:submit styleClass="btn btn-primary">Buscar</html:submit>
		&nbsp;
		<html:link action="/secure/admin/usuario?method=todos" styleClass="btn btn-primary">Listar Todos</html:link>		
		&nbsp;
		<html:link action="/secure/admin/usuario.do?method=formulario" styleClass="btn btn-primary">Novo Usuário</html:link>			
	</html:form>
	
</div>
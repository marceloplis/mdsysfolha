<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Cadastro de Usuário</h4>
	<hr></hr> 
	
	<html:form action="/secure/admin/processacargadados?method=carregarArquivo" enctype="multipart/form-data" method="post">
		<label>Tipo Arquivo:</label>
		<html:select property="tipoArquivo">
			<html:option value="">Selecione</html:option>
			<html:option value="F">Funcionario</html:option>
			<html:option value="L">Lançamentos</html:option>
		</html:select>
		<br/>
		<label>Arquivo:</label>
		<html:file property="file" 		styleClass="input-xxlarge"/>		
		<br/><br/>	
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>									

	</html:form>
</div>
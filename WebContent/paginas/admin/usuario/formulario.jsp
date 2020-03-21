<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Cadastro de Usuário</h4>
	<hr></hr> 
	
	<html:form action="/secure/admin/gravausuario?method=gravar">
		<html:hidden property="usuario.idUsuario"/>
		<html:hidden property="senhaAtual"/>
		<label>Nome:</label>
		<html:text property="usuario.nome" 		styleClass="input-xxlarge"/>
		<label>Username:</label>
		<html:text property="usuario.username"	styleClass="input-medium"/>
		<label>Senha:</label>
		<html:password property="usuario.senha"	styleClass="input-medium"/>	
		<label>Ativo:</label>
		<html:select property="usuario.flAtivo"  	styleClass="input-xlarge">
			<html:option value="">Selecione</html:option>
			<html:option value="false">Não</html:option>
			<html:option value="true">Sim</html:option>
		</html:select>	
		<br/>	
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>									

	</html:form>
</div>
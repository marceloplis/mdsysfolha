<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Alteração de Senha</h4>
	<hr></hr> 

	<html:form action="/secure/gravarsenha?method=gravarsenha">
		<label>Nova Senha:</label>
		<html:password property="novasenha"/>
		<label>Confirme Nova Senha:</label>
		<html:password property="novasenhaconfirma"/>
		</br>
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	</html:form>
	
</div>
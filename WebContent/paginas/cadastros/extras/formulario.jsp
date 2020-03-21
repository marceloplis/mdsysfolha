<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Cadastro de Lan�amentos Extras</h4>
	<hr></hr> 
	
	<html:form action="/secure/cadastro/gravaextra.do?method=gravar">
    	<label>Nome:</label>
		<html:text property="extra.descricao" 		styleClass="input-xxlarge"/>
		<label>Tipo Valor:</label>
		<html:select property="extra.tipo_valor"  	styleClass="input-xlarge">
			<html:option value="">Selecione</html:option>
			<html:option value="M">Moeda</html:option>
			<html:option value="P">Porcentagem</html:option>
		</html:select>
		<label>Tipo Lan�amento</label> 
		<html:select property="extra.tipo_lancamento">
			<html:option value="">Selecione</html:option>
			<html:option value="C">Cr�dito</html:option>
			<html:option value="D">D�bito</html:option>
		</html:select>
		
		<br/>	
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>
    </html:form>
</div>
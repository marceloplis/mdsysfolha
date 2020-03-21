<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Cadastro de Benefício</h4>
	<hr></hr> 
	
	<html:form action="/secure/cadastro/gravabeneficio.do?method=gravar">
    	<label>Nome:</label>
		<html:text property="beneficio.descricao" 		styleClass="input-xxlarge"/>
		<label>Tipo Valor:</label>
		<html:select property="beneficio.tipo_valor"  	styleClass="input-xlarge">
			<html:option value="">Selecione</html:option>
			<html:option value="M">Moeda</html:option>
			<html:option value="P">Porcentagem</html:option>
		</html:select>
		<label>Ordem de Cálculo:</label>
		<html:select property="beneficio.ordem"  	styleClass="input-xlarge">
			<html:option value="0">Selecione</html:option>
			<c:forEach var="o" items="${beneficioForm.listOrdem}">
				<html:option value="${o}">
					${o}
				</html:option>
			</c:forEach>
		</html:select>
		<label>Base de Cálculo:</label>
		<html:select property="beneficio.base_calculo"  	styleClass="input-xlarge">
			<html:option value="">Selecione</html:option>
			<html:option value="B">Salário Bruto</html:option>
			<html:option value="L">Salário Líquido</html:option>
		</html:select>
		<label>Altera a Base de Cálculo (Utilizado para cálculo de Descontos):</label>
		<html:select property="beneficio.altera_base_calculo"  	styleClass="input-xlarge">
			<html:option value="">Selecione</html:option>
			<html:option value="S">Sim</html:option>
			<html:option value="N">Não</html:option>
		</html:select>
		
		<br/>	
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>
    </html:form>
</div>
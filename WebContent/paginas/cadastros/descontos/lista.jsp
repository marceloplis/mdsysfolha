<%@include file="/templates/tags.jsp" %>

<h4>Descontos</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<a data-target="#myModal" role="button" class="btn" data-toggle="modal">Novo Desconto</a>
 
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/desconto.do?method=todos">
  	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel"></h3>
  	</div>
  	<div class="modal-body">
	    <html:form action="/secure/cadastro/gravadesconto.do?method=gravar">
	    	<label>Nome:</label>
			<html:text property="desconto.descricao" 		styleClass="input-xxlarge"/>
			<label>Tipo Valor:</label>
			<html:select property="desconto.tipo_valor"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<html:option value="M">Moeda</html:option>
				<html:option value="P">Porcentagem</html:option>
			</html:select>
			<label>Ordem de Cálculo:</label>
			<html:select property="desconto.ordem"  	styleClass="input-xlarge">
				<html:option value="0">Sem Ordem</html:option>
				<c:forEach var="o" items="${descontoForm.listOrdem}">
					<html:option value="${o}">
						${o}
					</html:option>
				</c:forEach>
			</html:select>
			<label>Base de Cálculo:</label>
			<html:select property="desconto.base_calculo"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<html:option value="B">Salário Bruto</html:option>
				<html:option value="L">Salário Calculado</html:option>
			</html:select>
			<br/>	
			<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	    </html:form>
  	</div>
  	<div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
  	</div>
</div>

<display:table id="desconto" name="${descontoForm.listDescontos}" requestURI="/secure/cadastro/desconto.do"
               class="table table-bordered"
               sort="list" defaultsort="4"
               pagesize="20" export="true">

	<display:column property="id"	  			title="ID"			sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="descricao"		title="Descrição"	sortable="true" headerClass="sortable" style="width: 50%;"/>
	<display:column 					    	title="Tipo Valor"	sortable="true" headerClass="sortable" style="width: 10%;" >
		${desconto.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"}
	</display:column>
	<display:column property="ordem"			title="Ordem de Cálculo" sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column 					    	title="Base de Cálculo"	 sortable="true" headerClass="sortable" style="width: 10%;" >
		${desconto.base_calculo eq 'B' ? "Salário Bruto" : desconto.base_calculo eq 'L' ? "Salário Calculado" : ""}
	</display:column>

	<display:column style="width: 05%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/desconto?method=formulario&idParam=${desconto.id}" styleClass="btn"><img src="../../shared/img/acessar.png"/></html:link>
	</display:column>	
	
	<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/desconto?method=excluir&idParam=${desconto.id}" styleClass="btn" styleId="delete${desconto.id}">Excluir</html:link>
			<script>
				$('#delete${desconto.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Confirma e exclusão ${desconto.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="descontos.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

<%@include file="/templates/tags.jsp" %>

<h4>Lançamentos Extras</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<a data-target="#myModal" role="button" class="btn" data-toggle="modal">Novo Lançamento Extra</a>
 
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/extras.do?method=todos">
  	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel"></h3>
  	</div>
  	<div class="modal-body">
	    <html:form action="/secure/cadastro/gravaextra.do?method=gravar">
	    	<label>Nome:</label>
			<html:text property="extra.descricao" 		styleClass="input-xxlarge"/>
			<label>Tipo Valor:</label>
			<html:select property="extra.tipo_valor"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<html:option value="M">Moeda</html:option>
				<html:option value="P">Porcentagem</html:option>
			</html:select>
			<label>Tipo Lançamento</label> 
  			<html:select property="extra.tipo_lancamento">
  				<html:option value="">Selecione</html:option>
  				<html:option value="C">Crédito</html:option>
  				<html:option value="D">Débito</html:option>
  			</html:select>
			<br/>	
			<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	    </html:form>
  	</div>
  	<div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
  	</div>
</div>

<display:table id="extra" name="${lancamentoExtraForm.listExtras}" requestURI="/secure/cadastro/lancamentosextra.do"
               class="table table-bordered"
               sort="list" defaultsort="2"
               pagesize="20" export="true">

	<display:column property="id"	  			title="ID"			sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="descricao"		title="Descrição"	sortable="true" headerClass="sortable" style="width: 30%;"/>
	<display:column 					    	title="Tipo Valor"	sortable="true" headerClass="sortable" style="width: 10%;" >
		${extra.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"}
	</display:column>
	<display:column 					    	title="Tipo"			sortable="true" headerClass="sortable" style="width: 05%;" >
		${extra.tipo_lancamento eq 'C' ? "Crédito" : "Débito"}
	</display:column>
	
	<display:column style="width: 05%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/lancamentosextra?method=formulario&idParam=${extra.id}" styleClass="btn"><img src="../../shared/img/acessar.png"/></html:link>
	</display:column>	
	
	
	<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/lancamentosextra?method=excluir&idParam=${extra.id}" styleClass="btn" styleId="delete${extra.id}">Excluir</html:link>
			<script>
				$('#delete${extra.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Confirma e exclusão do Lançamento Extra ${extra.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="extras.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

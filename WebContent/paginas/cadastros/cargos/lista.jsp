<%@include file="/templates/tags.jsp" %>

<h4>Cargos</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<a data-target="#myModal" role="button" class="btn" data-toggle="modal">Novo Cargo</a>
 
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/cargo.do?method=todos">
  	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel"></h3>
  	</div>
  	<div class="modal-body">
	    <html:form action="/secure/cadastro/gravacargo.do?method=gravar">
	    	<label>Nome:</label>
			<html:text property="cargo.descricao" 		styleClass="input-xxlarge"/>
			<br/>	
			<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	    </html:form>
  	</div>
  	<div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
  	</div>
</div>

<display:table id="cargo" name="${cargoForm.listCargos}" requestURI="/secure/cadastro/cargo.do"
               class="table table-bordered"
               sort="list" defaultsort="2"
               pagesize="20" export="true">

	<display:column property="id"	  			title="ID"			sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="descricao"		title="Descrição"	sortable="true" headerClass="sortable" style="width: 50%;"/>

	<display:column title="Acessar" style="width: 05%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/cargo?method=formulario&idParam=${cargo.id}" styleClass="btn"><img src="../../shared/img/acessar.png"/></html:link>
	</display:column>	
	
	<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/cargo?method=excluir&idParam=${cargo.id}" styleClass="btn" styleId="delete${cargo.id}">Excluir</html:link>
			<script>
				$('#delete${cargo.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Confirma e exclusão do Cargo ${cargo.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="cargos.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

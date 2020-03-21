<%@include file="/templates/tags.jsp" %>

<style>
	.datepicker{z-index:1151 !important;}
</style>

<h4>Folha de Pagamento</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>


<c:if test="${folhaForm.funcsSemCargo > 0 }">
	<div class="alert alert-warning">
	 	<strong>ATENÇÃO!</strong> Há ${folhaForm.funcsSemCargo} <html:link action="/secure/cadastro/funcionario.do?method=todos">Funcionário(s)</html:link> sem Cargo associado e isso impossibilita geração de Holerites!
	</div>
</c:if>

<a data-target="#myModal" role="button" class="btn" data-toggle="modal" href="#">Nova Folha de Pagamento</a>
 
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/folhapagamento.do?method=todos">
  	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel"></h3>
  	</div>
  	<div class="modal-body">
	    <html:form action="/secure/cadastro/gravafolhapagamento.do?method=gravar">
	    	<div class="input-append date" id="datepicker" data-date="01-2016" data-date-format="mm-yyyy">
				<html:text property="mes_ano" styleClass="input-xlarge" readonly="readonly"/>
	 			<span class="add-on"><i class="icon-th"></i></span>      
			</div> 	 
			<label>Tipo da Folha de Pagamento</label> 
  			<html:select property="folha.tipo">
  				<html:option value="M">Mensal</html:option>
  				<html:option value="D">Décimo Terceiro</html:option>
  			</html:select>   			
			<br/>	
			<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	    </html:form>
  	</div>
  	<div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
  	</div>
  	<script>	
		$("#datepicker").datepicker({
		    viewMode: 'years',
		    format: 'mm-yyyy'
		});
	</script>
</div>
	

<display:table id="folha" name="${folhaForm.listFolhas}" requestURI="/secure/cadastro/folhapagamento.do"
               class="table table-bordered"
               sort="list" defaultsort="4"
               pagesize="20" export="true">

	<display:column property="ano"				title="Ano"				sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="mes"				title="Mês"				sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column 					    	title="Tipo"			sortable="true" headerClass="sortable" style="width: 10%;" >
		${folha.tipo eq 'M' ? "Mensal" : "13º"}
	</display:column>
	<display:column 					    	title="Status"			sortable="true" headerClass="sortable" style="width: 10%;" >
		${folha.status eq 'A' ? "Aberta" : folha.status eq 'G' ? "Gerada" : "Finalizada"}
	</display:column>
	<display:column property="data_criacao"		title="Data Criação"	sortable="true" headerClass="sortable" style="width: 20%;"/>
	
	<display:column style="width: 10%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/folhapagamento?method=detalhe&idParam=${folha.id}" styleClass="btn"><img src="../../shared/img/acessar.png"/></html:link>
	</display:column>	
	
	
	<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/folhapagamento?method=excluir&idParam=${folha.id}" styleClass="btn" styleId="delete${folha.id}">Excluir</html:link>
			<script>
				$('#delete${folha.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Confirma e exclusão da Folha de Pagamento ${folha.ano} / ${folha.mes}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="folhas.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>


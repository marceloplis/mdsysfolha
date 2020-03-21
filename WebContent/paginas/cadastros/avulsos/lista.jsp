<%@include file="/templates/tags.jsp" %>

<style>
.datepicker{z-index:1151 !important;}
</style>

<h4>Lançamentos Avulsos</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="form-actions">
	<html:form action="/secure/cadastro/outroslancamentos?method=filtro" styleId="formP">
		<div class="span5">				  			
  			<label>Período</label> 
  			&nbsp;De&nbsp;
  			<div class="input-append date" id="datepickerInicio" data-date="01-2016" data-date-format="dd/mm/yyyy">
				<html:text property="dtfiltroInicio" styleClass="input-medium" readonly="true"/>
 				<span class="add-on"><i class="icon-th"></i></span>      
			</div> 
			&nbsp;Até&nbsp;
			<div class="input-append date" id="datepickerFim" data-date="01-2016" data-date-format="dd/mm/yyyy">
				<html:text property="dtfiltroFim" styleClass="input-medium" readonly="true"/>
 				<span class="add-on"><i class="icon-th"></i></span>      
			</div> 
		</div>
		<div class="span2">				  			
  			<label>Loja</label>
  			<html:select property="filtLoja" styleClass="input-medium">
  				<html:option value="">Todas</html:option>
  				<html:option value="1">1</html:option>
  				<html:option value="2">2</html:option>
  				<html:option value="3">3</html:option>
  				<html:option value="4">4</html:option>
  			</html:select>
		</div>
		<div class="span3">				  			
  			<label>Funcionário</label>
  			<html:select property="filtFunc" styleClass="input-xxlarge">
  				<html:option value="">Todos</html:option>
  				<c:forEach var="func" items="${lancamentoAvulsoForm.listFuncionarios}">
					<html:option value="${func.cpf}">
						${func.nome}
					</html:option>
				</c:forEach>
  			</html:select>
		</div>
		<div class="span3">		
			<html:submit styleClass="btn btn-primary">Filtrar</html:submit> 
			&nbsp;	
			<html:link action="/secure/cadastro/outroslancamentos.do?method=todos" styleClass="btn btn-primary">Limpar</html:link>							
		</div>
	</html:form>
</div>

<a data-target="#myModal" role="button" class="btn" data-toggle="modal">Novo Lançamento</a>
 
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/outroslancamentos.do?method=todos">
  	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel"></h3>
  	</div>
  	<div class="modal-body">
	    <html:form action="/secure/cadastro/gravalancamentoavulso.do?method=gravar">
	    	<label>Funcionário(a):</label>
			<html:select property="avulso.cpf"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<c:forEach var="func" items="${lancamentoAvulsoForm.listFuncionarios}">
					<html:option value="${func.cpf}">
						${func.nome} - ${func.cpf}
					</html:option>
				</c:forEach>
			</html:select>
			<label>Data do Lançamento:</label>
			<div class="input-append date" id="datepicker" data-date="01-2016" data-date-format="dd/mm/yyyy">
				<html:text property="dtLctoParse" styleClass="input-xlarge" readonly="readonly"/>
	 			<span class="add-on"><i class="icon-th"></i></span>      
			</div> 
			<label>Descrição:</label>
			<html:text property="avulso.descricao" styleClass="input-xlarge"/>
			<label>Tipo</label> 
  			<html:select property="avulso.tipo">
  				<html:option value="">Selecione</html:option>
  				<html:option value="C">Crédito</html:option>
  				<html:option value="D">Débito</html:option>
  			</html:select>
  			<label>Tipo da Folha de Pagamento</label> 
  			<html:select property="avulso.tp_folha">
  				<html:option value="M">Mensal</html:option>
  				<html:option value="D">Décimo Terceiro</html:option>
  			</html:select>
  			<label>Valor:</label>
			<html:text property="avulso.valorParse" styleClass="input-medium" maxlength="10" onkeypress="return formata_decimal(this, '', ',', event, 10);"/>
			<br/>	
			<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	    </html:form>
  	</div>
  	<div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
  	</div>
  	<script>	
		$("#datepicker").datepicker();
		$("#datepickerInicio").datepicker();
		$("#datepickerFim").datepicker();
	</script>
</div>

<display:table id="avulso" name="${lancamentoAvulsoForm.listAvulsos}" requestURI="/secure/cadastro/outroslancamentos.do"
               class="table table-bordered"
               sort="list" defaultsort="2"
               pagesize="30" export="true">

	<display:column property="id"	  			title="ID"				sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column 	     					title="Funcionário"		sortable="true" headerClass="sortable" style="width: 30%;">
		${avulso.funcionario.nome } - ${avulso.funcionario.cpf }
	</display:column>
	<display:column property="data"				title="Data Lcto"		sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="descricao"		title="Descrição"		sortable="true" headerClass="sortable" style="width: 15%;"/>
	<display:column 					    	title="Tipo"			sortable="true" headerClass="sortable" style="width: 05%;" >
		${avulso.tipo eq 'C' ? "Crédito" : "Débito"}
	</display:column>
	<display:column property="valor"			title="Valor"			sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="data_criacao"		title="Data Cadastro"	sortable="true" headerClass="sortable" style="width: 10%;"/>	

	<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/outroslancamentos?method=excluir&idParam=${avulso.id}" styleClass="btn" styleId="delete${avulso.id}">Excluir</html:link>
			<script>
				$('#delete${avulso.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Confirma e exclusão do Lançamento ${avulso.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="avulsos.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

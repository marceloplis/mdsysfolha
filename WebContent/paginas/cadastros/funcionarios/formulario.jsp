<%@include file="/templates/tags.jsp" %>

<style>
.datepicker{z-index:1151 !important;}
</style>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Cadastro de Funcionário</h4>
	<hr></hr> 
	
	<html:form action="/secure/cadastro/gravafuncionario?method=atualizar">
		<label>CPF:</label>
		<html:text property="funcionario.cpf" 		styleClass="input-xxlarge" 	  readonly="true" disabled="true"/>
		<label>Loja:</label>
		<html:text property="funcionario.loja" 		styleClass="input-xxlarge"/>
		<label>Data de Admissão:</label>
		<div class="input-append date" id="datepicker" data-date="01-2016" data-date-format="dd/mm/yyyy">
			<html:text property="dtAdmissaoParse" styleClass="input-medium" readonly="true"/>
 			<span class="add-on"><i class="icon-th"></i></span>      
		</div> 
		<label>Nome:</label>
		<html:text property="funcionario.nome" 		styleClass="input-xxlarge"/>
		<label>Salário:</label>
		<html:text property="salarioParse"			styleClass="input-medium" maxlength="10" onkeypress="return formata_decimal(this, '', ',', event, 10);"/>		
		<label>Ativo:</label>
		<html:select property="funcionario.fl_ativo"  	styleClass="input-xlarge">
			<html:option value="N">Não</html:option>
			<html:option value="S">Sim</html:option>
		</html:select>	
		<label>Cargo:</label>				
		<html:select property="idCargo" styleClass="input-xlarge">
			<html:option value="">Selecione</html:option>
			<c:forEach var="cargo" items="${funcionarioForm.listCargos}">
				<html:option value="${cargo.id}">
					${cargo.descricao}
				</html:option>
			</c:forEach>
		</html:select>
		<br/>	
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>									
		&nbsp;
		<html:link action="/secure/cadastro/funcionario.do?method=filtro" styleClass="btn btn-primary">Voltar</html:link>
	</html:form>
	<script>	
		$("#datepicker").datepicker();
	</script>
	<hr>
	<h6 style="background-color: #1E90FF">Lançamentos Extras Mensais</h6>
	
	<a data-target="#myModalExtra" role="button" class="btn" data-toggle="modal">Incluir Lançamento</a>
 
	<div class="modal fade hide" id="myModalExtra" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/funcionario.do?method=formulario">
	  	<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel"></h3>
	  	</div>
	  	<div class="modal-body">
		    <html:form action="/secure/cadastro/funcionario.do?method=gravarLancamentoExtra">
		    	<label>Lançamento Extra</label>				
				<html:select property="idExtra" styleClass="input-xlarge">
					<html:option value="">Selecione</html:option>
					<c:forEach var="extra" items="${funcionarioForm.listExtras}">
						<html:option value="${extra.id}">
							${extra.descricao} (${extra.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"})
						</html:option>
					</c:forEach>
				</html:select>
				
				<label>Valor:</label>
				<html:text property="lancamentoExtra.valorParse" styleClass="input-medium" maxlength="10" onkeypress="return formata_decimal(this, '', ',', event, 10);"/>		
		
				<br/>	
				<html:submit styleClass="btn btn-primary">Gravar</html:submit>
		    </html:form>
	  	</div>
	  	<div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
	  	</div>
	</div>
	
	<display:table id="funcExtra" name="${funcionarioForm.funcionario.lancamentosExtras}" requestURI="/secure/cadastro/funcionario.do"
               class="table table-bordered"
               sort="list" defaultsort="1"
               pagesize="20" export="false">

		<display:column property="pk.extra.descricao"	title="Descrição"	sortable="false" headerClass="sortable" style="width: 30%;"/>
		<display:column 					    			title="Tipo Valor"	sortable="false" headerClass="sortable" style="width: 15%;" >
			${funcExtra.pk.extra.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"}
		</display:column>
		<display:column property="valorParse"		title="Valor"		sortable="false" headerClass="sortable" style="width: 15%;"/>

		<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/funcionario?method=excluirLancamentoExtra&idExtra=${funcExtra.pk.extra.id}&idParam=${funcionarioForm.funcionario.cpf}" styleClass="btn" styleId="deleteExtra${funcExtra.pk.extra.id}">Excluir</html:link>
			<script>
				$('#deleteExtra${funcExtra.pk.extra.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Deseja remover o lançamento extra ${funcExtra.pk.extra.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
		</display:column>

	</display:table>
	
</div>
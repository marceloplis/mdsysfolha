<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Cadastro de Cargo</h4>
	<hr></hr> 
	
	<html:form action="/secure/cadastro/gravacargo?method=gravar">
		<label>Nome:</label>
		<html:text property="cargo.descricao" 		styleClass="input-xxlarge"/>
		<br/>	
		<html:submit styleClass="btn btn-primary">Gravar</html:submit>		
	</html:form>
	
	<hr>
	<h6 style="background-color: #1E90FF">Benefícios do Cargo</h6>
	
	<a data-target="#myModalBenef" role="button" class="btn" data-toggle="modal">Incluir Benefício</a>
 
	<div class="modal fade hide" id="myModalBenef" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/cargo.do?method=formulario">
	  	<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel"></h3>
	  	</div>
	  	<div class="modal-body">
		    <html:form action="/secure/cadastro/cargo.do?method=gravarBeneficio">
		    	<label>Benefício</label>				
				<html:select property="beneficioId" styleClass="input-xlarge">
					<html:option value="">Selecione</html:option>
					<c:forEach var="benef" items="${cargoForm.listBeneficios}">
						<html:option value="${benef.id}">
							${benef.descricao} (${benef.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"})
						</html:option>
					</c:forEach>
				</html:select>
				
				<label>Valor:</label>
				<html:text property="cargoBeneficio.valorBeneficioParse" styleClass="input-medium" maxlength="10" onkeypress="return formata_decimal(this, '', ',', event, 10);"/>		
		
				<br/>	
				<html:submit styleClass="btn btn-primary">Gravar</html:submit>
		    </html:form>
	  	</div>
	  	<div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
	  	</div>
	</div>
	
	<display:table id="cargobenef" name="${cargoForm.cargo.cargoBeneficios}" requestURI="/secure/cadastro/cargo.do"
               class="table table-bordered"
               sort="list" defaultsort="1"
               pagesize="20" export="false">

		<display:column property="pk.beneficio.descricao"	title="Descrição"	sortable="false" headerClass="sortable" style="width: 30%;"/>
		<display:column 					    			title="Tipo Valor"	sortable="false" headerClass="sortable" style="width: 15%;" >
			${cargobenef.pk.beneficio.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"}
		</display:column>
		<display:column property="valorBeneficioParse"		title="Valor"		sortable="false" headerClass="sortable" style="width: 15%;"/>

		<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/cargo?method=excluirBeneficio&beneficioId=${cargobenef.pk.beneficio.id}&idParam=${cargoForm.cargo.id}" styleClass="btn" styleId="deleteBenef${cargobenef.pk.beneficio.id}">Excluir</html:link>
			<script>
				$('#deleteBenef${cargobenef.pk.beneficio.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Deseja remover o benefício ${cargobenef.pk.beneficio.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
		</display:column>

	</display:table>
	
	<hr>
	<h6 style="background-color: #CD5C5C">Descontos do Cargo</h6>
	
	<a data-target="#myModalDesc" role="button" class="btn" data-toggle="modal">Incluir Desconto</a>
 
	<div class="modal fade hide" id="myModalDesc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/cargo.do?method=formulario">
	  	<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel"></h3>
	  	</div>
	  	<div class="modal-body">
		    <html:form action="/secure/cadastro/cargo.do?method=gravarDesconto">
		    	<label>Desconto</label>				
				<html:select property="descontoId" styleClass="input-xlarge">
					<html:option value="">Selecione</html:option>
					<c:forEach var="desc" items="${cargoForm.listDescontos}">
						<html:option value="${desc.id}">
							${desc.descricao} (${desc.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"})
						</html:option>
					</c:forEach>
				</html:select>
				
				<label>Valor:</label>
				<html:text property="cargoDesconto.valorDescontoParse" styleClass="input-medium" maxlength="10" onkeypress="return formata_decimal(this, '', ',', event, 10);"/>		
		
				<br/>	
				<html:submit styleClass="btn btn-primary">Gravar</html:submit>
		    </html:form>
	  	</div>
	  	<div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
	  	</div>
	</div>
	
	<display:table id="cargodesc" name="${cargoForm.cargo.cargoDescontos}" requestURI="/secure/cadastro/cargo.do"
               class="table table-bordered"
               sort="list" defaultsort="1"
               pagesize="20" export="false">

		<display:column property="pk.desconto.descricao"	title="Descrição"	sortable="false" headerClass="sortable" style="width: 30%;"/>
		<display:column 					    			title="Tipo Valor"	sortable="false" headerClass="sortable" style="width: 15%;" >
			${cargodesc.pk.desconto.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"}
		</display:column>
		<display:column property="valorDescontoParse"		title="Valor"		sortable="false" headerClass="sortable" style="width: 15%;"/>

		<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/cargo?method=excluirDesconto&descontoId=${cargodesc.pk.desconto.id}&idParam=${cargoForm.cargo.id}" styleClass="btn" styleId="deleteDesc${cargodesc.pk.desconto.id}">Excluir</html:link>
			<script>
				$('#deleteDesc${cargodesc.pk.desconto.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Deseja remover o desconto ${cargodesc.pk.desconto.descricao}?", function (response) {			
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
<%@include file="/templates/tags.jsp" %>

<h4>Benef�cios</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<a data-target="#myModal" role="button" class="btn" data-toggle="modal">Novo Benef�cio</a>
 
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-remote="/secure/cadastro/beneficio.do?method=todos">
  	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
	    <h3 id="myModalLabel"></h3>
  	</div>
  	<div class="modal-body">
	    <html:form action="/secure/cadastro/gravabeneficio.do?method=gravar">
	    	<label>Nome:</label>
			<html:text property="beneficio.descricao" 		styleClass="input-xxlarge"/>
			<label>Tipo Valor:</label>
			<html:select property="beneficio.tipo_valor"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<html:option value="M">Moeda</html:option>
				<html:option value="P">Porcentagem</html:option>
			</html:select>
			<label>Ordem de C�lculo:</label>
			<html:select property="beneficio.ordem"  	styleClass="input-xlarge">
				<html:option value="0">Sem Ordem</html:option>
				<c:forEach var="o" items="${beneficioForm.listOrdem}">
					<html:option value="${o}">
						${o}
					</html:option>
				</c:forEach>
			</html:select>
			<label>Base de C�lculo:</label>
			<html:select property="beneficio.base_calculo"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<html:option value="B">Sal�rio Bruto</html:option>
				<html:option value="L">Sal�rio L�quido</html:option>
			</html:select>
			<label>Altera a Base de C�lculo (Utilizado para c�lculo de Descontos):</label>
			<html:select property="beneficio.altera_base_calculo"  	styleClass="input-xlarge">
				<html:option value="">Selecione</html:option>
				<html:option value="S">Sim</html:option>
				<html:option value="N">N�o</html:option>
			</html:select>
			<br/>	
			<html:submit styleClass="btn btn-primary">Gravar</html:submit>
	    </html:form>
  	</div>
  	<div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
  	</div>
</div>

<display:table id="beneficio" name="${beneficioForm.listBeneficios}" requestURI="/secure/cadastro/beneficio.do"
               class="table table-bordered"
               sort="list" defaultsort="4"
               pagesize="20" export="true">

	<display:column property="id"	  			title="ID"			sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="descricao"		title="Descri��o"	sortable="true" headerClass="sortable" style="width: 30%;"/>
	<display:column 					    	title="Tipo Valor"	sortable="true" headerClass="sortable" style="width: 10%;" >
		${beneficio.tipo_valor eq 'M' ? "Moeda" : "Porcentagem"}
	</display:column>
	<display:column property="ordem"			title="Ordem de C�lculo" sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column 					    	title="Base de C�lculo"	 sortable="true" headerClass="sortable" style="width: 10%;" >
		${beneficio.base_calculo eq 'B' ? "Sal�rio Bruto" : beneficio.base_calculo eq 'L' ? "Sal�rio Calculado" : ""}
	</display:column>
	<display:column 					    	title="Altera Base de C�lculo"	 sortable="true" headerClass="sortable" style="width: 10%;" >
		${beneficio.altera_base_calculo eq 'S' ? "Sim" : "N�o"}
	</display:column>

	<display:column style="width: 05%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/beneficio?method=formulario&idParam=${beneficio.id}" styleClass="btn"><img src="../../shared/img/acessar.png"/></html:link>
	</display:column>	
	
	
	<display:column style="width: 05%; text-align:center; vertical-align:middle;" media="html">
			<html:link action="/secure/cadastro/beneficio?method=excluir&idParam=${beneficio.id}" styleClass="btn" styleId="delete${beneficio.id}">Excluir</html:link>
			<script>
				$('#delete${beneficio.id}').on('click', function (e, confirmed) {
					if (!confirmed) {
				        e.preventDefault();
				        var location = $(this).attr('href');
				        bootbox.confirm("Confirma e exclus�o do benef�cio ${beneficio.descricao}?", function (response) {			
				            if(response) {
				            	window.location.replace(location);
				            }
				        });
				    }
				});
			</script>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="beneficios.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

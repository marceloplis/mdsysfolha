<%@include file="/templates/tags.jsp" %>

<h4>Funcionários</h4>
<hr></hr> 

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="form-actions">
	<html:form action="/secure/cadastro/funcionario?method=filtro" styleId="formP">
		<span class="span1"><h4>Filtro:</h4></span>
		<div class="span2">				  			
  			<label>Loja</label> 
  			<html:select property="filtLoja">
  				<html:option value="0">Todas</html:option>
  				<html:option value="1">1</html:option>
  				<html:option value="2">2</html:option>
  				<html:option value="3">3</html:option>
  				<html:option value="4">4</html:option>
  				<html:option value="5">5</html:option>
  			</html:select>
		</div>
		<div class="span2">				  			
  			<label>Status</label>
  			<html:select property="filtAtivo">
  				<html:option value="">Todos</html:option>
  				<html:option value="S">Ativo</html:option>
  				<html:option value="N">Inativo</html:option>
  			</html:select>
		</div>
		<div class="span2">				  			
  			<label>Cargo</label>
  			<html:select property="filtCargo">
  				<html:option value="">Todos</html:option>
  				<html:option value="-1">Pendente de Atribuição</html:option>
  				<c:forEach var="cargo" items="${funcionarioForm.listCargos}">
					<html:option value="${cargo.id}">
						${cargo.descricao}
					</html:option>
				</c:forEach>
  			</html:select>
		</div>
		<div class="span2">	
			<label>Nome</label>
			<html:text property="filtNome"></html:text>
			<div>*Busca por nome parcial</div>
		</div>
		<div class="span2">		
			<label>&nbsp;</label>	
			<html:submit styleClass="btn btn-primary">Filtrar</html:submit> 	
			&nbsp;		
			<html:link action="/secure/cadastro/funcionario.do?method=todos" styleClass="btn btn-primary">Limpar</html:link>	
		</div>
	</html:form>
</div>

<display:table id="func" name="${funcionarioForm.listFuncionarios}" requestURI="/secure/cadastro/funcionario.do"
               class="table table-bordered"
               sort="list" defaultsort="2"
               pagesize="20" export="true">

	<display:column property="cpf"	  			title="CPF"		sortable="true" headerClass="sortable" style="width: 10%;"/>
	<display:column property="nome"		  		title="Nome"	sortable="true" headerClass="sortable" style="width: 30%;"/>
	<display:column property="loja"	  			title="Loja"	sortable="true"  headerClass="sortable" style="width: 10%;"/>
	<display:column property="cargo.descricao"	title="Cargo"	sortable="true"  headerClass="sortable" style="width: 15%;"/>
	<display:column 					    	title="Status"	sortable="false" headerClass="sortable" style="width: 10%;" >
		${func.fl_ativo eq 'S' ? "Ativo" : "Inativo"}
	</display:column>
	<display:column style="width: 05%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/funcionario?method=formulario&idParam=${func.cpf}" styleClass="btn"><img src="../../shared/img/acessar.png"/></html:link>
	</display:column>
	<display:setProperty name="export.excel.filename" value="funcionarios.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

<%@include file="/templates/tags.jsp" %>

<c:choose>
	<c:when test="${cargaDadosForm.tipoArquivo eq 'F'}">
		<h4>Arquivo FUNCIONARIO Processado com Sucesso!</h4>
		<hr></hr> 
		<display:table id="func" name="${cargaDadosForm.funcionarios}" requestURI="/secure/admin/processacargadados.do"
               class="table table-bordered"
	           sort="list" defaultsort="2"
	           pagesize="20" export="true">
	
			<display:column property="cpf"	  		title="CPF"		sortable="true" headerClass="sortable" style="width: 15%;"/>
			<display:column property="nome"		  	title="Nome"	sortable="true" headerClass="sortable" style="width: 30%;"/>
			<display:column 	  					title="Salário"	sortable="true" headerClass="sortable" style="width: 15%;">
				<fmt:setLocale value="pt_BR"/>
				<fmt:formatNumber value="${func.salario}" type="currency"/>
			</display:column>	
			<display:column property="loja"	  		title="Loja"	sortable="true" headerClass="sortable" style="width: 15%;"/>
			<display:setProperty name="export.excel.filename" value="usuarios.xls"/>
		    <display:setProperty name="export.excel" value="true" />
		</display:table>
	</c:when>
	<c:otherwise>
		<h4>Arquivo LANÇAMENTOS Processado com Sucesso!</h4>
		<hr></hr>
		<display:table id="lcto" name="${cargaDadosForm.lancamentos}" requestURI="/secure/admin/processacargadados.do"
               class="table table-bordered"
	           sort="list" defaultsort="2"
	           pagesize="20" export="true">
	
			<display:column 				  		title="Tipo"		sortable="true" headerClass="sortable" style="width: 15%;">
				<c:choose>
					<c:when test="${lcto.tipo eq 'D'}">
						Desconto
					</c:when>
					<c:otherwise>
						Crédito
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column property="cpf"			title="CPF"			sortable="true" headerClass="sortable" style="width: 30%;"/>
			<display:column property="descricao"	title="Descrição"	sortable="true" headerClass="sortable" style="width: 30%;"/>
			<display:column 	  					title="Data"		sortable="true" headerClass="sortable" style="width: 15%;">
				<fmt:formatDate pattern="dd/MM/yyyy" value="${lcto.data}" />
			</display:column>
			<display:column 	  					title="Valor"	sortable="true" headerClass="sortable" style="width: 15%;">
				<fmt:setLocale value="pt_BR"/>
				<fmt:formatNumber value="${lcto.valor}" type="currency"/>
			</display:column>	
			<display:setProperty name="export.excel.filename" value="usuarios.xls"/>
		    <display:setProperty name="export.excel" value="true" />
		</display:table>
	</c:otherwise>
</c:choose>


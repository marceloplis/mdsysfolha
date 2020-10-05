<%@include file="/templates/tags.jsp" %>

<h4>Holerites</h4>
<hr></hr> 

<div class="form-actions">
	<div class="span3">	
		<label><b>M�s:</b> ${folhaForm.folha.mes}</label>	
		<label><b>Ano:</b> ${folhaForm.folha.ano}</label>
		<label>
			<b>Tipo:</b> ${folhaForm.folha.tipo eq 'M' ? "Mensal" : folhaForm.folha.tipo eq 'D' ? "13�" : "Rescis�o"}
		</label>
	</div>
	<html:form action="/secure/cadastro/folhapagamento/holerites?method=exibeHolerite" styleId="formP">
		<span class="span1"><h4>Filtro:</h4></span>
		<div class="span3">				  			
  			<label>Loja</label>
  			<html:select property="filtLoja" styleClass="input-medium">
  				<html:option value="">Todas</html:option>
  				<html:option value="1">1</html:option>
  				<html:option value="2">2</html:option>
  				<html:option value="3">3</html:option>
  				<html:option value="4">4</html:option>
  				<html:option value="5">5</html:option>
  			</html:select>
		</div>
		<div class="span6">		
			<html:submit styleClass="btn btn-primary">Filtrar</html:submit> 
			&nbsp;	
			<html:link action="/secure/cadastro/folhapagamento/holerites.do?method=exibeHolerite&idParam=${folhaForm.folha.id}&filtLoja=&FiltFunc=" styleClass="btn btn-primary">Limpar</html:link>							
		</div>
	</html:form>
</div>

<button class="btn" aria-hidden="true" id="imprimir1">Imprimir</button>
&nbsp;
<html:link action="/secure/cadastro/folhapagamento.do?method=detalhe&idParam=${folhaForm.folha.id}" styleClass="btn btn-primary">Voltar</html:link>


<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<display:table id="func" name="${folhaForm.holerites}" requestURI="/secure/cadastro/folhapagamento/holerites.do"
               class="table table-bordered"
               sort="list" defaultsort="2"
               pagesize="30" export="true">

	<display:column property="funcionario.nome"	title="Nome"		sortable="true" headerClass="sortable" style="width: 15%;"/>
	<display:column property="loja"	  			title="Loja"		sortable="true"  headerClass="sortable" style="width: 03%;"/>
	<display:column property="cargo"			title="Cargo"		sortable="true"  headerClass="sortable" style="width: 08%;"/>
	<display:column property="salario"	  		title="Vencimento"	sortable="false" headerClass="sortable" style="width: 06%;"/>	
	
	<display:column property="totalBeneficiosParse"	  		title="Cargo(+)"	sortable="false" headerClass="sortable" style="width: 08%;"/>
	<display:column property="totalAvulsoBeneficiosParse"	title="Outros(+)"	sortable="false" headerClass="sortable" style="width: 08%;"/>
	<display:column property="totalExtrasBeneficiosParse"	title="Func.(+)"	sortable="false" headerClass="sortable" style="width: 08%;"/>
	<display:column property="totalDescontosParse"	  		title="Cargo(-)"	sortable="false" headerClass="sortable" style="width: 08%;"/>
	<display:column property="totalAvulsoDescontosParse"	title="Outros(-)"	sortable="false" headerClass="sortable" style="width: 08%;"/>	
	<display:column property="totalExtrasDescontosParse"	title="Func.(-)"	sortable="false" headerClass="sortable" style="width: 08%;"/>
	<display:column property="arredondamentoParse"	  		title="(+)"			sortable="false" headerClass="sortable" style="width: 08%;"/>
	<display:column property="totalParse"	  				title="Total"		sortable="false" headerClass="sortable" style="width: 08%;"/>
	
	<display:column style="width: 05%; text-align:center;" media="html">
		<html:link action="/secure/cadastro/folhapagamento/holerites?method=imprimir&idParam=${folhaForm.folha.id}&filtFunc=${func.funcionario.cpf}" target="_blank" styleClass="btn"><img src="../../../shared/img/print-icon.png"/></html:link>
	</display:column>
	
	<display:setProperty name="export.excel.filename" value="holerites.xls"/>
    <display:setProperty name="export.excel" value="true" />
</display:table>

<button class="btn" aria-hidden="true" id="imprimir2">Imprimir</button>
&nbsp;
<html:link action="/secure/cadastro/folhapagamento.do?method=detalhe&idParam=${folhaForm.folha.id}" styleClass="btn btn-primary">Voltar</html:link>

<script>
$('#imprimir1').on('click', function (e, confirmed) {
	var location = './holerites.do?method=imprimir&filtFunc=';
    window.open(location,'_blank');			
});

$('#imprimir2').on('click', function (e, confirmed) {
	var location = './holerites.do?method=imprimir&filtFunc=';
    window.open(location,'_blank');			
});
</script>

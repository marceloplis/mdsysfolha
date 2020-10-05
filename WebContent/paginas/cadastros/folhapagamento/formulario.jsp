<%@include file="/templates/tags.jsp" %>

<div class="form-msg-error">
	<jsp:include page="/templates/message-error.jsp"/>
</div>

<div class="formulario">	

	<h4>Folha de Pagamento</h4>
	<hr></hr> 
	
	<form>
		<label>Mês:</label>	
		<input type="text" readonly="readonly" value="${folhaForm.folha.mes}">
		<label>Ano:</label>	
		<input type="text" readonly="readonly" value="${folhaForm.folha.ano}">
		<label>Tipo:</label>
		<input type="text" readonly="readonly" value="${folhaForm.folha.tipo eq 'M' ? "Mensal" : folhaForm.folha.tipo eq 'D' ? "13º" : "Rescisão"}">
		<label>Status:</label>	
		<input type="text" readonly="readonly" value="${folhaForm.folha.status eq 'A' ? "Aberta" : folha.status eq 'G' ? "Gerada" : "Finalizada"}">
	</form>
	
	<div>
		<c:choose>
			<c:when test="${folhaForm.folha.status eq 'A'}">
				<button class="btn" aria-hidden="true" id="calcular">Calcular</button>
			</c:when>
			<c:otherwise>
				<button class="btn" aria-hidden="true" id="holerites">Holerites</button>
				&nbsp;&nbsp;
				<button class="btn" aria-hidden="true" id="del_holerites">Apagar Holerites</button>
			</c:otherwise>	
		</c:choose>
		<br><br>
		<div>
			<html:link action="/secure/cadastro/folhapagamento.do?method=todos" styleClass="btn btn-primary">Voltar</html:link>
		</div>
	</div>

	<script>
		$('#calcular').on('click', function (e, confirmed) {
			if (!confirmed) {
		        e.preventDefault();
		        var location = '../cadastro/folhapagamento.do?method=calcular&idParam=${folhaForm.folha.id}';

		        bootbox.confirm("Confirma o cálculo para a Folha de Pagamento ${folha.tipo eq 'M' ? 'Mensal' : folha.tipo eq 'D' ? '13º' : 'Rescisão'} ${folhaForm.folha.ano} / ${folhaForm.folha.mes}?", function (response) {			
		            if(response) {
		            	$(this).html('<img src="../../shared/img/fbloader.gif" />');
		            	window.location.replace(location);		            	
		            }		            
		        });		        
		    }
			
		});
		
		
		$('#holerites').on('click', function (e, confirmed) {
			var location = '../cadastro/folhapagamento/holerites.do?method=exibeHolerite&idParam=${folhaForm.folha.id}&filtLoja=&FiltFunc=';
		    window.location.replace(location);			
		});
		
		
		$('#del_holerites').on('click', function (e, confirmed) {
			if (!confirmed) {
		        e.preventDefault();
		        var location = '../cadastro/folhapagamento.do?method=apagarHolerites&idParam=${folhaForm.folha.id}';
		        bootbox.confirm("Gostaria de Cancelar os Holerites da Folha de Pagamento ${folhaForm.folha.ano} / ${folhaForm.folha.mes}?", function (response) {			
		            if(response) {
		            	$(this).html('<img src="../../shared/img/fbloader.gif" />');
		            	window.location.replace(location);		            	
		            }		            
		        });		        
		    }
			
		});
	</script>
	
	

</div>
<%@include file="/templates/tags.jsp" %>

<STYLE TYPE="text/css">
.folha {
    page-break-after: always;
}

td{
	width: 200px;
}

</STYLE>

<c:forEach var="h" items="${folhaForm.holerites}" varStatus="hs">
	<c:set value="folha" var="cssClass"></c:set>
	<c:if test="${hs.last}"> 
	  <c:set value=" " var="cssClass"></c:set>
	</c:if>

	<div class="${cssClass}">
		<table>
			<tr>
				<td colspan="5"><b>Noroeste Calçados - Financeiro</b></td>
				<td><b>Loja: ${h.funcionario.loja}</b></td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
			<tr>
				<td>Nome:</td>
				<td colspan="5"><b>${h.funcionario.nome}</b></td>
			</tr>
			<tr>
				<td>Função:</td>
				<td colspan="2"><b>${h.funcionario.cargo.descricao}</b></td>
				<td>Data-Base:</td>
				<td colspan="2"><b>${folhaForm.folha.mes}/${folhaForm.folha.ano}</b></td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
			<tr>
				<td colspan="3"><b>Descrição</b></td>
				<td><b>Tipo</b></td>
				<td align="right"><b>Vencimentos</b></td>
				<td align="right"><b>Descontos</b></td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
			<c:if test="${folhaForm.folha.tipo eq 'M'}">			
				<tr>
					<td colspan="3">Salário</td>
					<td>Provento</td>
					<td align="right">${h.folhaFuncionario.salarioParse}</td>
					<td align="right">0,00</td>
				</tr>
			</c:if>
			<c:forEach var="hb" items="${h.beneficios}">
				<tr>
					<td colspan="3">${hb.beneficio.descricao}</td>
					<td>Provento</td>
					<td align="right">${hb.valorBeneficioParse}</td>
					<td align="right">0,00</td>
				</tr>
			</c:forEach>
			<c:forEach var="hab" items="${h.avulsoBeneficios}">
				<tr>
					<td colspan="3">${hab.avulso.descricao}</td>
					<td>Provento</td>
					<td align="right">${hab.avulso.valorParse}</td>
					<td align="right">0,00</td>
				</tr>
			</c:forEach>
			<c:forEach var="hd" items="${h.descontos}">
				<tr>
					<td colspan="3">${hd.desconto.descricao}</td>
					<td>Desconto</td>
					<td align="right">0,00</td>
					<td align="right">${hd.valorDescontoParse}</td>
				</tr>
			</c:forEach>			
			<c:forEach var="had" items="${h.avulsoDescontos}">
				<tr>
					<td colspan="3">${had.avulso.descricao}</td>
					<td>Desconto</td>
					<td align="right">0,00</td>
					<td align="right">${had.avulso.valorParse}</td>
				</tr>
			</c:forEach>
			<c:forEach var="lex" items="${h.extras}">
				<tr>
					<td colspan="3">${lex.extra.descricao}</td>
					<td>${lex.extra.tipo_lancamento eq 'D' ? 'Dessconto' : 'Provento'}</td>
					<td align="right">${lex.extra.tipo_lancamento eq 'D' ? '0,00' : lex.valorParse}</td>
					<td align="right">${lex.extra.tipo_lancamento eq 'D' ? (lex.valorParse) : '0,00'}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3">Arredondamento</td>
				<td>Provento</td>
				<td align="right">${h.arredondamentoParse}</td>
				<td align="right">0,00</td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
				<td><b>Totais R$</b></td>
				<td align="right">${h.somaTotaisBeneficiosParse}</td>
				<td align="right">${h.somaTotaisDescontosParse}</td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
				<td><b>Valor Líquido R$</b></td>
				<td align="right">${h.totalParse}</td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
			<tr>
				<td colspan="4">
					---------------------------------------------------------------------<br/>
					Assinatura Funcionário
				</td>
				<td colspan="2">____/____/________</td>
			</tr>
			<tr>
				<td colspan="6"><hr class="mini"></hr></td>
			</tr>
		</table>    	 
	       
	</div>
	
	<br><br><br><br>

</c:forEach>


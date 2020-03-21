<%@include file="/templates/tags.jsp" %>

<div class="span10" style="width: 99%">
	<div class="hero-unit">
    	<h4>Cadastros Primários</h4>
        <p style="text-align: justify;">
        	<h5><html:link action="/secure/cadastro/beneficio.do?method=todos">1 - Benefícios</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) São todos os Proventos obrigatórios para TODOS os funcionários</label>
	        		<label>ii)O campo <b>Ordem</b> é obrigatório e deve ser observado que ele define a ordem de cálculo para a folha de pagamento</label> 
	        	</i>
        	</p>
        	
        	<h5><html:link action="/secure/cadastro/desconto.do?method=todos">2 - Descontos</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) São todos os Descontos obrigatórios para TODOS os funcionários</label>
	        		<label>ii)O campo <b>Ordem</b> é obrigatório e deve ser observado que ele define a ordem de cálculo para a folha de pagamento</label> 
	        	</i>
        	</p>
        	
        	<h5><html:link action="/secure/cadastro/cargo.do?method=todos">3 - Cargos</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) Devem ser cadastrados antes de Importar Funcionários.</label>
	        		<label>ii)Após cadastrar, <b>Acessar</b> o formulário e associar Benefícios e Descontos.</label>
	        	</i>
        	</p>
        	
        	<h5><html:link action="/secure/cadastro/lancamentosextra.do?method=todos">4 - Lançamentos Extras</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) Devem ser cadastrados e depois relacioná-los aos <html:link action="/secure/cadastro/funcionario.do?method=todos">Funcionários</html:link>.</label>
	        	</i>
        	</p>
        	
        </p>
	</div>  
	
	<div class="hero-unit">
    	<h4><html:link action="/secure/admin/arquivo.do?method=formulario">Carga de Dados</html:link></h4>
        <p style="text-align: justify;">
        	<i>
        		<label>i) Importar arquivos disponibilizados pelo SETA para Funcionários e Lançamentos Avulsos.</label>
        		<label>ii)Após Importar os Funcionários, deve acessar o cadastros dos <html:link action="/secure/cadastro/funcionario.do?method=todos">Funcionários</html:link> e atribuir o Cargo.</label>        		
        	</i>

        </p>
	</div>  
	
	<div class="hero-unit">
    	<h4><html:link action="/secure/cadastro/folhapagamento.do?method=todos">Folha de Pagamento</html:link></h4>
        <p style="text-align: justify;">
        	<i>
        		<label>i) O ANO e MÊS deve ser preenchido com o período base de pagamento (Exemplo: Se o pagamento é correspondente ao mês de Agosto de 2016, preencher com 2016 e 08 respectivamente).</label>
        		<label>ii)Antes de calcular a Folha de Pagamento, garantir que todos os Funcionários e Lançamentos Avulsos foram importados.</label>
        	</i>
        </p>
	</div>             
</div>
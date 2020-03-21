<%@include file="/templates/tags.jsp" %>

<div class="span10" style="width: 99%">
	<div class="hero-unit">
    	<h4>Cadastros Prim�rios</h4>
        <p style="text-align: justify;">
        	<h5><html:link action="/secure/cadastro/beneficio.do?method=todos">1 - Benef�cios</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) S�o todos os Proventos obrigat�rios para TODOS os funcion�rios</label>
	        		<label>ii)O campo <b>Ordem</b> � obrigat�rio e deve ser observado que ele define a ordem de c�lculo para a folha de pagamento</label> 
	        	</i>
        	</p>
        	
        	<h5><html:link action="/secure/cadastro/desconto.do?method=todos">2 - Descontos</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) S�o todos os Descontos obrigat�rios para TODOS os funcion�rios</label>
	        		<label>ii)O campo <b>Ordem</b> � obrigat�rio e deve ser observado que ele define a ordem de c�lculo para a folha de pagamento</label> 
	        	</i>
        	</p>
        	
        	<h5><html:link action="/secure/cadastro/cargo.do?method=todos">3 - Cargos</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) Devem ser cadastrados antes de Importar Funcion�rios.</label>
	        		<label>ii)Ap�s cadastrar, <b>Acessar</b> o formul�rio e associar Benef�cios e Descontos.</label>
	        	</i>
        	</p>
        	
        	<h5><html:link action="/secure/cadastro/lancamentosextra.do?method=todos">4 - Lan�amentos Extras</html:link></h5>
        	<p class="lead">
	        	<i>
	        		<label>i) Devem ser cadastrados e depois relacion�-los aos <html:link action="/secure/cadastro/funcionario.do?method=todos">Funcion�rios</html:link>.</label>
	        	</i>
        	</p>
        	
        </p>
	</div>  
	
	<div class="hero-unit">
    	<h4><html:link action="/secure/admin/arquivo.do?method=formulario">Carga de Dados</html:link></h4>
        <p style="text-align: justify;">
        	<i>
        		<label>i) Importar arquivos disponibilizados pelo SETA para Funcion�rios e Lan�amentos Avulsos.</label>
        		<label>ii)Ap�s Importar os Funcion�rios, deve acessar o cadastros dos <html:link action="/secure/cadastro/funcionario.do?method=todos">Funcion�rios</html:link> e atribuir o Cargo.</label>        		
        	</i>

        </p>
	</div>  
	
	<div class="hero-unit">
    	<h4><html:link action="/secure/cadastro/folhapagamento.do?method=todos">Folha de Pagamento</html:link></h4>
        <p style="text-align: justify;">
        	<i>
        		<label>i) O ANO e M�S deve ser preenchido com o per�odo base de pagamento (Exemplo: Se o pagamento � correspondente ao m�s de Agosto de 2016, preencher com 2016 e 08 respectivamente).</label>
        		<label>ii)Antes de calcular a Folha de Pagamento, garantir que todos os Funcion�rios e Lan�amentos Avulsos foram importados.</label>
        	</i>
        </p>
	</div>             
</div>
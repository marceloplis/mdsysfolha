<%@page import="br.com.mdsysfolha.entity.UsuarioEntity"%>
<%@include file="/templates/tags.jsp" %>

<%	
	UsuarioEntity usuario =  (UsuarioEntity)request.getSession().getAttribute("UsuarioSistema");	
%>

<div class="navbar container">
<div class="nav-collapse logo"></div>
</div>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
    	<div class="container-fluid">
        	<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
            	<span class="icon-bar"></span>
          	</button>
          	<a class="brand" href="#">&nbsp;</a>
          	<div class="nav-collapse collapse">
	            <p class="navbar-text pull-right">
	              	<b>Logado como:</b> <%=usuario.getNome() %> <html:link action="formlogin?method=logout" styleClass="navbar-link" title="LOGOUT"><b style="color: #C00;">SAIR</b></html:link>
	            </p>
	            <ul class="nav">
	            	<li>
	            		<html:link action="/secure/home.do?method=home">Home</html:link>
	            	</li>
	            	
	            	<li class="dropdown">
	                	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Carga de Dados<b class="caret"></b></a>
	                	<ul class="dropdown-menu">            	
            				
							<li>
								<html:link action="/secure/admin/arquivo.do?method=formulario">Carregar Arquivos Seta</html:link>
							</li>	
							
							
						</ul>
					</li>	
	            	
	            	<li class="dropdown">
	                	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastros<b class="caret"></b></a>
	                	<ul class="dropdown-menu">
            	
            				<li>
								<html:link action="/secure/cadastro/beneficio.do?method=todos">Benefícios</html:link>
							</li>
							<li>
								<html:link action="/secure/cadastro/desconto.do?method=todos">Descontos</html:link>
							</li>
							<li>
								<html:link action="/secure/cadastro/cargo.do?method=todos">Cargos</html:link>
							</li>
							<li>
								<html:link action="/secure/cadastro/lancamentosextra.do?method=todos">Lançamentos Extras</html:link>
							</li>            					
							<li>
								<html:link action="/secure/cadastro/funcionario.do?method=todos">Funcionários</html:link>
							</li>	
							<li>
								<html:link action="/secure/cadastro/outroslancamentos.do?method=todos">Lançamentos Avulsos</html:link>
							</li>					
						</ul>
					</li>	             					
					
            		<li>
						<html:link action="/secure/cadastro/folhapagamento.do?method=todos">Folha de Pagamento</html:link>
					</li>           	
					
					<li class="dropdown">
	                	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Sistema<b class="caret"></b></a>
	                	<ul class="dropdown-menu">
            	
            				<li>
								<html:link action="/secure/admin/usuario.do?method=formconsulta">Usuários</html:link>
							</li>
							<!-- li>
								<a href="#">Parâmetros</a>
							</li -->						           				
							
						</ul>
					</li>
		             
		            <li>
				  		<html:link action="/secure/trocarsenha.do?method=trocarsenha">Trocar Senha</html:link>
				  	</li>
		              
	            </ul>
        	</div><!--/.nav-collapse -->
    	</div>
	</div>
</div>

<script>
$(function(){
	function stripTrailingSlash(str) {
		if(str.substr(-1) == '/') {
	      	return str.substr(0, str.length - 1);
	    }
	    return str;
	}

  	var url = window.location.pathname;  
	var activePage = stripTrailingSlash(url);

	$('.nav li a').each(function(){  
		var currentPage = stripTrailingSlash($(this).attr('href'));	    
		
	    if (currentPage.indexOf(activePage) == 0) {
			if(activePage.indexOf('/admin/') > 0 ){
				$(this).parent().parent().parent().addClass('active');
				$(this).parent().parent().parent().parent().parent().addClass('active');
			}else{
	      		$(this).parent().addClass('active'); 
			}
	    }
	});
});
</script>
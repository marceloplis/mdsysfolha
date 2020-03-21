<%@include file="/templates/tags.jsp" %>

<div class="formulario">

	<div class="span10">
      
      	<div style="display: none;">
            EXCEPTION_ERROR
      	</div>
 
      	<div class="grid_16" style="padding:0;">       
        	<div class="box_erro">                             
	            <div class="block" id="janela1">                                      
	                <h1>Ocorreu um erro:</h1>
	                <div class="space"></div>                  
	                
	                <span>Tipo:</span><br/> 
	                <span>${tipo}</span>
	                <br/>
	                
	                <span>Causa:</span><br/>
	                <span>${causa}</span>
	                <br/>
	                
	                <span>Mensagem:</span><br/>
	                <span>${mensagem}</span>                                  
	                <div class="clear"></div>                                   
	            </div>
        	</div>               
    	</div>  
      
     
      	<div class="grid_16" style="padding:0;">       
        	<div class="box">                      
            	<div class="box_topo">
                  	<h2>StackTrace:</h2>                                       
                  	<a href="#" id="toggle-janela2"></a>     
            	</div>      
            	<div class="block" id="janela2">                     
                 	<p><br/>${stacktrace}</p>               
            	</div>                  
       			<div class="clear"></div>          
     		</div>         
    	</div>   
    
    </div>
 
</div>

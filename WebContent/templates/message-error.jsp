<%@include file="/templates/tags.jsp" %>

<logic:messagesPresent>   
   <div class="alert alert-block alert-error fade in">
	   <html:messages id="error">  
	      	<span class="alert-heading"><bean:write name="error"/><br></span>
	   </html:messages>  
   </div>
</logic:messagesPresent> 

<logic:messagesPresent message="true">  
   <div class="alert fade in">
	   <html:messages id="message" message="true">  
	     	<bean:write name="message"/><br>
	   </html:messages> 
	</div> 
</logic:messagesPresent>  
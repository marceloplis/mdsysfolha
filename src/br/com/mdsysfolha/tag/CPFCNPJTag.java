package br.com.mdsysfolha.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CPFCNPJTag extends TagSupport {

	private static final long serialVersionUID = 571857724813115255L;
	
	private String valor;

	@Override
	public int doStartTag() throws JspException {		
		try {	                       
	        
			if(this.valor != null && !this.valor.equals("")){  
				String cpfCnpjFormatado = CPFCNPJMask.getInstance().format(this.valor);				
				super.pageContext.getOut().print(cpfCnpjFormatado);	        
			} else {
	        	super.pageContext.getOut().print("");
	        }
			
		} catch (Exception e) {
			e.getMessage();	    	        
		}				
		return super.doStartTag();
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String cpfCnpj) {
		this.valor = cpfCnpj;
	}	
	
}

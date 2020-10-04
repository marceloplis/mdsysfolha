package br.com.mdsysfolha.action.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.UsuarioEntity;

public class NovaSenhaForm extends FormBase{

	private static final long serialVersionUID = 4019176598688627404L;
	
	private String novasenha;
	private String novasenhaconfirma;
	
	private UsuarioEntity usuario;

	public String getNovasenha() {
		return novasenha;
	}

	public void setNovasenha(String novasenha) {
		this.novasenha = novasenha;
	}

	public String getNovasenhaconfirma() {
		return novasenhaconfirma;
	}

	public void setNovasenhaconfirma(String novasenhaconfirma) {
		this.novasenhaconfirma = novasenhaconfirma;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	 
	      if( this.getNovasenha() == null || this.getNovasenha().length() < 1 ) {
	    	  errors.add("senha",new ActionMessage("error.required.a","nova senha"));
	      }
	      if( this.getNovasenhaconfirma() == null || this.getNovasenhaconfirma().length() < 1 ) {
	    	  errors.add("novasenhaconfirma",new ActionMessage("error.required.a","confirmação da nova senha"));
	      }
	      if(!this.getNovasenha().equals(this.getNovasenhaconfirma())){
	    	  errors.add("diferencasenha",new ActionMessage("error.novasenha.diferente"));
	      }
	      
	      return errors;
	      
	}	
	
}

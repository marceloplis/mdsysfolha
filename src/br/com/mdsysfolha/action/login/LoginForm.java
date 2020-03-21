package br.com.mdsysfolha.action.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;

public class LoginForm extends FormBase{

	private static final long serialVersionUID = -8620433400850706262L;
	
	private String username;
	private String senha;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( getUsername() == null || getUsername().length() < 1 ) {
	        errors.add("username",new ActionMessage("error.required.o","username"));
	      }
	      if( getSenha() == null || getSenha().length() < 1 ) {
	        errors.add("senha",new ActionMessage("error.required.a","senha"));
	      }

	      return errors;
	}
	
}

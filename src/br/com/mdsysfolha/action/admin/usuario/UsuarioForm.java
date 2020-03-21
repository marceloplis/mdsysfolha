package br.com.mdsysfolha.action.admin.usuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.UsuarioEntity;

public class UsuarioForm extends FormBase{

	private static final long serialVersionUID = 9003342327000588870L;

	private UsuarioEntity usuario;
	
	private List<UsuarioEntity> listUsuarios;
	
	private String senhaAtual;
	
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	
	public List<UsuarioEntity> getListUsuarios() {
		return listUsuarios;
	}
	public void setListUsuarios(List<UsuarioEntity> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}	
	
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}	
	
	
	/*Overwrite*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {		
		setNmBusca(null);
		setIdParam(null);
		this.senhaAtual = null;
	} 
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( usuario.getNome() == null || usuario.getNome().length() < 1 ) {
	    	  errors.add("nome",new ActionMessage("error.required.o","nome"));
	      }
	      if( usuario.getUsername() == null || usuario.getUsername().length() < 1 ) {
	    	  errors.add("username",new ActionMessage("error.required.o","username"));
	      }
	      if( usuario.getSenha() == null || usuario.getSenha().length() < 1 ) {
	    	  errors.add("senha",new ActionMessage("error.required.a","senha"));
	      }	      

	      return errors;
	}
	
}

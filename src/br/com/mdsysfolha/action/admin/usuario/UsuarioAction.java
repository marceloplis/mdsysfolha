package br.com.mdsysfolha.action.admin.usuario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.UsuarioController;
import br.com.mdsysfolha.entity.UsuarioEntity;

public class UsuarioAction extends ActionBase{

	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsuarioForm usuarioform = (UsuarioForm) form;
		usuarioform.setUsuario(new UsuarioEntity());
		if(usuarioform.getIdParam() != null){
			UsuarioController usuarioController = new UsuarioController();
			usuarioform.setUsuario(usuarioController.buscarById(usuarioform.getIdParam()));
			usuarioform.setSenhaAtual(usuarioform.getUsuario().getSenha());
		}else{
			usuarioform.reset(mapping, request);
		}
		
		return mapping.findForward("formulario");
	}
	
	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsuarioForm usuarioform = (UsuarioForm) form;
		
		UsuarioController usuarioController = new UsuarioController();	
		
		if(isTokenValid(request)) { 
			resetToken(request); 		    
		    
		    if(usuarioform.getUsuario().getIdUsuario() == null || usuarioform.getUsuario().getIdUsuario() == 0){
				
		    	UsuarioEntity usuario = usuarioController.buscaByUsername(usuarioform.getUsuario().getUsername());
				if(usuario != null){
					System.out.println("Usuário existente: "+ usuario.getUsername());
					ActionMessages errors = new ActionMessages();
					errors.add("usuario",new ActionMessage("error.usuario.username.existente", usuario.getUsername()));	
					saveErrors(request, errors);
					saveToken(request);
					return mapping.findForward("formulario");
				}
				
			}
		    
			UsuarioEntity usuario = usuarioController.gravar(usuarioform.getUsuario(), usuarioform.getSenhaAtual());
			usuarioform.setUsuario(usuario);  
		} 		
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.usuario.sucesso"));
        saveMessages(request, messages);
		
        List<UsuarioEntity> usuarios = usuarioController.listTodos();
		usuarioform.setListUsuarios(usuarios);
        
		saveToken(request);
		return mapping.findForward("sucesso"); 
	}	
	
	public ActionForward formconsulta(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		saveToken(request);
		return mapping.findForward("consulta");
	}
		
	public ActionForward buscar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		UsuarioForm usuarioform = (UsuarioForm) form;
		if(usuarioform.getNmBusca().length() < 3){			
			return mapping.findForward("consulta");
		}		
		
		if(isTokenValid(request)) {
			resetToken(request);
			UsuarioController usuarioController = new UsuarioController();
			List<UsuarioEntity> usuarios = usuarioController.buscar(usuarioform.getNmBusca());
			
			usuarioform.setListUsuarios(usuarios);
		}
		
		saveToken(request);
		return mapping.findForward("listar");
		
	}
	
	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsuarioForm usuarioform = (UsuarioForm) form;
		
		UsuarioController usuarioController = new UsuarioController();
		List<UsuarioEntity> usuarios = usuarioController.listTodos();
		usuarioform.setListUsuarios(usuarios);
		
		return mapping.findForward("listar");
	}	
	
	public ActionForward apagar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsuarioForm usuarioform = (UsuarioForm) form;
		
		UsuarioController usuarioController = new UsuarioController();
		
		if(isTokenValid(request)) { 
			resetToken(request);			
			usuarioController.apagar(usuarioform.getIdParam());
		} 		
		
		List<UsuarioEntity> usuarios = usuarioController.listTodos();
		usuarioform.setListUsuarios(usuarios);
		
		saveToken(request);
		return mapping.findForward("sucesso"); 
	}	
	
	
}

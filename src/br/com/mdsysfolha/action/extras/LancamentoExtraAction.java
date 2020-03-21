package br.com.mdsysfolha.action.extras;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.LancamentoExtraController;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;

public class LancamentoExtraAction extends ActionBase{

	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LancamentoExtraForm extraForm = (LancamentoExtraForm) form;
		
		LancamentoExtraController extraController = new LancamentoExtraController();
		List<LancamentosExtraEntity> extras = extraController.listTodos();
		extraForm.setListExtras(extras);
		extraForm.setExtra(new LancamentosExtraEntity());
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}	
	
	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LancamentoExtraForm extraForm = (LancamentoExtraForm) form;
		
		LancamentoExtraController extraController = new LancamentoExtraController();
		
		if(isTokenValid(request)) { 
			resetToken(request); 			
			LancamentosExtraEntity extra = extraController.gravar(extraForm.getExtra());
			extraForm.setExtra(extra);
			extraForm.setIdParam(new Long(extra.getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.extra.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		List<LancamentosExtraEntity> extras = extraController.listTodos();
		extraForm.setListExtras(extras);
		extraForm.setExtra(new LancamentosExtraEntity());
		
		return mapping.findForward("listar"); 
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LancamentoExtraForm extraForm = (LancamentoExtraForm) form;
		
		LancamentoExtraController extraController = new LancamentoExtraController();	
	
		if(extraForm.getIdParam() != null) { 	
			try{
				extraController.excluir(extraForm.getIdParam());
				
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("msg.extra.removido"));
		        saveMessages(request, messages); 
			}catch (Exception e){
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("error.exclusao.extra"));
		        saveMessages(request, messages);
			}
			
		}
		
		saveToken(request);
		
		List<LancamentosExtraEntity> extras = extraController.listTodos();
		extraForm.setListExtras(extras);
								
		return mapping.findForward("listar"); 
		
	}
	

	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LancamentoExtraForm extraForm = (LancamentoExtraForm) form;
		
		LancamentoExtraController extraController = new LancamentoExtraController();	
		
		extraForm.setExtra(new LancamentosExtraEntity());
		if(extraForm.getIdParam() != null){			
			extraForm.setExtra(extraController.buscarById(extraForm.getIdParam()));	
		}else{
			extraForm.reset(mapping, request);
		}
		
		return mapping.findForward("formulario");
	}
	
}

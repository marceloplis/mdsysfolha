package br.com.mdsysfolha.action.desconto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.DescontoController;
import br.com.mdsysfolha.entity.DescontoEntity;
import br.com.mdsysfolha.helper.FolhaHelper;

public class DescontoAction extends ActionBase{

	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DescontoForm descontoForm = (DescontoForm) form;
		
		DescontoController descontoController = new DescontoController();
		List<DescontoEntity> descontos = descontoController.listTodos();
		descontoForm.setListDescontos(descontos);
		descontoForm.setListOrdem(FolhaHelper.listaOrdemDesconto(descontos, null));
		descontoForm.setDesconto(new DescontoEntity());
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}	
	
	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DescontoForm descontoForm = (DescontoForm) form;
		
		DescontoController descontoController = new DescontoController();
		
		if(isTokenValid(request)) { 
			resetToken(request); 			
			DescontoEntity desconto = descontoController.gravar(descontoForm.getDesconto());
			descontoForm.setDesconto(desconto); 			
			descontoForm.setIdParam(new Long(desconto.getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.desconto.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
				
		List<DescontoEntity> descontos = descontoController.listTodos();
		descontoForm.setListDescontos(descontos);
		descontoForm.setListOrdem(FolhaHelper.listaOrdemDesconto(descontos, null));
		descontoForm.setDesconto(new DescontoEntity());
		
		return mapping.findForward("listar"); 
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DescontoForm descontoForm = (DescontoForm) form;
		
		DescontoController descontoController = new DescontoController();	
	
		if(descontoForm.getIdParam() != null) { 	
			
				descontoController.excluir(descontoForm.getIdParam());
				
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("msg.desconto.removido"));
		        saveMessages(request, messages); 
			
			
		}
		
		saveToken(request);
		
		List<DescontoEntity> descontos = descontoController.listTodos();
		descontoForm.setListDescontos(descontos);
		descontoForm.setListOrdem(FolhaHelper.listaOrdemDesconto(descontos, null));
								
		return mapping.findForward("listar"); 
		
	}
	
	
	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DescontoForm descontoForm = (DescontoForm) form;
		
		DescontoController descontoController = new DescontoController();	
		
		descontoForm.setDesconto(new DescontoEntity());
		if(descontoForm.getIdParam() != null){			
			descontoForm.setDesconto(descontoController.buscarById(descontoForm.getIdParam()));		
			descontoForm.setListOrdem(FolhaHelper.listaOrdemDesconto(descontoForm.getListDescontos(), descontoForm.getDesconto().getOrdem()));
		}else{
			descontoForm.reset(mapping, request);
		}
		
		return mapping.findForward("formulario");
	}
	
}

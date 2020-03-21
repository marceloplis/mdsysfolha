package br.com.mdsysfolha.action.beneficio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.BeneficioController;
import br.com.mdsysfolha.entity.BeneficioEntity;
import br.com.mdsysfolha.helper.FolhaHelper;

public class BeneficioAction extends ActionBase{

	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BeneficioForm beneficioForm = (BeneficioForm) form;
		
		BeneficioController beneficioController = new BeneficioController();
		List<BeneficioEntity> beneficios = beneficioController.listTodos();
		beneficioForm.setListBeneficios(beneficios);
		beneficioForm.setListOrdem(FolhaHelper.listaOrdemBeneficio(beneficios, null));
		beneficioForm.setBeneficio(new BeneficioEntity());
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}	
	
	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BeneficioForm beneficioForm = (BeneficioForm) form;
		
		BeneficioController beneficioController = new BeneficioController();
		
		if(isTokenValid(request)) { 
			resetToken(request); 			
			BeneficioEntity beneficio = beneficioController.gravar(beneficioForm.getBeneficio());
			beneficioForm.setBeneficio(beneficio);
			beneficioForm.setIdParam(new Long(beneficio.getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.beneficio.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		List<BeneficioEntity> beneficios = beneficioController.listTodos();
		beneficioForm.setListBeneficios(beneficios);
		beneficioForm.setListOrdem(FolhaHelper.listaOrdemBeneficio(beneficios, null));
		beneficioForm.setBeneficio(new BeneficioEntity());
		
		return mapping.findForward("listar"); 
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BeneficioForm beneficioForm = (BeneficioForm) form;
		
		BeneficioController beneficioController = new BeneficioController();	
	
		if(beneficioForm.getIdParam() != null) { 	
			try{
				beneficioController.excluir(beneficioForm.getIdParam());
				
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("msg.beneficio.removido"));
		        saveMessages(request, messages); 
			}catch (Exception e){
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("error.exclusao.beneficio"));
		        saveMessages(request, messages);
			}
			
		}
		
		saveToken(request);
		
		List<BeneficioEntity> beneficios = beneficioController.listTodos();
		beneficioForm.setListBeneficios(beneficios);
		beneficioForm.setListOrdem(FolhaHelper.listaOrdemBeneficio(beneficios, null));
								
		return mapping.findForward("listar"); 
		
	}
	

	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BeneficioForm beneficioForm = (BeneficioForm) form;
		
		BeneficioController beneficioController = new BeneficioController();	
		
		beneficioForm.setBeneficio(new BeneficioEntity());
		if(beneficioForm.getIdParam() != null){			
			beneficioForm.setBeneficio(beneficioController.buscarById(beneficioForm.getIdParam()));	
			beneficioForm.setListOrdem(FolhaHelper.listaOrdemBeneficio(beneficioForm.getListBeneficios(), beneficioForm.getBeneficio().getOrdem()));
		}else{
			beneficioForm.reset(mapping, request);
		}
		
		return mapping.findForward("formulario");
	}
	
}

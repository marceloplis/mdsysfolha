package br.com.mdsysfolha.action.avulso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.FuncionarioController;
import br.com.mdsysfolha.controller.LancamentoAvulsoController;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;
import br.com.mdsysfolha.util.Utils;

public class LancamentoAvulsoAction extends ActionBase{

	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LancamentoAvulsoForm avulsoForm = (LancamentoAvulsoForm) form;		
		
		LancamentoAvulsoController avulsoController = new LancamentoAvulsoController();
		avulsoForm.setDtLctoParse(null);
		avulsoForm.setDtfiltroInicio(null);
		avulsoForm.setDtfiltroFim(null);
		avulsoForm.setFiltFunc("");
		avulsoForm.setListAvulsos(avulsoController.listTodos());
		avulsoForm.setAvulso(new LancamentosAvulsoEntity());
		
		FuncionarioController funcController = new FuncionarioController();
		avulsoForm.setListFuncionarios(funcController.listTodos());
		
		avulsoForm.setDtfiltroInicio(null);
		avulsoForm.setDtfiltroFim(null);
		avulsoForm.setFiltFunc(null);
		avulsoForm.setFiltLoja(0);
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}
	
	public ActionForward filtro(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LancamentoAvulsoForm avulsoForm = (LancamentoAvulsoForm) form;		
		
		LancamentoAvulsoController avulsoController = new LancamentoAvulsoController();
		avulsoForm.setDtLctoParse(null);
		
		Calendar dtInicioNull = Calendar.getInstance(); 
		dtInicioNull.add(Calendar.MONTH, -2);			
		Calendar dtFimNull = Calendar.getInstance(); 
		
		Date dtInicio = avulsoForm.getDtfiltroInicio() != null && avulsoForm.getDtfiltroInicio().length() > 0 ? Utils.converteData(avulsoForm.getDtfiltroInicio()) : dtInicioNull.getTime();
		Date dtFim = avulsoForm.getDtfiltroFim() != null && avulsoForm.getDtfiltroFim().length() > 0 ? Utils.converteData(avulsoForm.getDtfiltroFim()) : dtFimNull.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		avulsoForm.setDtfiltroInicio(sdf.format(dtInicio));
		avulsoForm.setDtfiltroFim(sdf.format(dtFim));
		
		avulsoForm.setListAvulsos(avulsoController.filtro(dtInicio, dtFim, avulsoForm.getFiltFunc(), avulsoForm.getFiltLoja()));
		avulsoForm.setAvulso(new LancamentosAvulsoEntity());
		
		FuncionarioController funcController = new FuncionarioController();
		avulsoForm.setListFuncionarios(funcController.listTodos());
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}
	
	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LancamentoAvulsoForm avulsoForm = (LancamentoAvulsoForm) form;		
		
		LancamentoAvulsoController avulsoController = new LancamentoAvulsoController();
		
		if(isTokenValid(request)) { 
			resetToken(request); 	
			LancamentosAvulsoEntity avulso = avulsoController.gravar(avulsoForm.getAvulso());
			avulsoForm.setAvulso(avulso); 
			avulsoForm.setIdParam(new Long(avulso.getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.avulso.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		avulsoForm.setListAvulsos(avulsoController.listTodos());
		avulsoForm.setAvulso(new LancamentosAvulsoEntity());
		
		FuncionarioController funcController = new FuncionarioController();
		avulsoForm.setListFuncionarios(funcController.listTodos());
		
		return mapping.findForward("listar"); 
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LancamentoAvulsoForm avulsoForm = (LancamentoAvulsoForm) form;		
		
		LancamentoAvulsoController avulsoController = new LancamentoAvulsoController();
	
		if(avulsoForm.getIdParam() != null) { 	
			try{
				avulsoController.excluir(avulsoForm.getIdParam());
				
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("msg.avulso.removido"));
		        saveMessages(request, messages); 
			}catch (Exception e){
				ActionMessages messages = new ActionMessages();
		        messages.add("message", new ActionMessage("error.exclusao.avulso"));
		        saveMessages(request, messages);
			}
			
		}
		
		saveToken(request);
		
		avulsoForm.setListAvulsos(avulsoController.listTodos());
								
		return mapping.findForward("listar"); 
		
	}
	
}

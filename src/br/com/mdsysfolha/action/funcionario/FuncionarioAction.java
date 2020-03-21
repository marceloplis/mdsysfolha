package br.com.mdsysfolha.action.funcionario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.CargoController;
import br.com.mdsysfolha.controller.FuncionarioController;
import br.com.mdsysfolha.controller.LancamentoExtraController;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioLctosExtraEntity;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;


public class FuncionarioAction extends ActionBase{

	
	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FuncionarioForm funcform = (FuncionarioForm) form;
		
		FuncionarioController funcController = new FuncionarioController();
		List<FuncionarioEntity> funcs = funcController.listTodos();
		funcform.setListFuncionarios(funcs);
		
		CargoController cargoController = new CargoController();
		funcform.setListCargos(cargoController.listTodos());
		
		List<FuncionarioEntity> funcsSemCargo = funcController.filtro(0, null, null, null, -1);
		if(funcsSemCargo.size() > 0){
			ActionMessages messages = new ActionMessages();
	        messages.add("message", new ActionMessage("msg.funcionario.cargo.pendente",funcsSemCargo.size()));
	        saveMessages(request, messages);   
		}
		
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}
	
	
	public ActionForward filtro(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FuncionarioForm funcform = (FuncionarioForm) form;
		
		FuncionarioController funcController = new FuncionarioController();
		List<FuncionarioEntity> funcs = funcController.filtro(funcform.getFiltLoja(), funcform.getFiltAtivo(), funcform.getFiltCPF(), funcform.getFiltNome(), funcform.getFiltCargo());
		funcform.setListFuncionarios(funcs);
		
		return mapping.findForward("listar");
	}
	
	
	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FuncionarioForm funcform = (FuncionarioForm) form;		
		
		funcform.setFuncionario(new FuncionarioEntity());
		if(funcform.getIdParam() != null){
			FuncionarioController funcController = new FuncionarioController();
			funcform.setFuncionario(funcController.buscarById(funcform.getIdParam().toString()));
			funcform.setIdCargo(funcform.getFuncionario().getCargo() != null && funcform.getFuncionario().getCargo().getId() != null ? funcform.getFuncionario().getCargo().getId() : 0);
			
			CargoController cargoController = new CargoController();
			funcform.setListCargos(cargoController.listTodos());
			
			LancamentoExtraController extraController = new LancamentoExtraController();
			List<LancamentosExtraEntity> extras = extraController.listTodos();
			funcform.setListExtras(extras);
			funcform.setLancamentoExtra(new FuncionarioLctosExtraEntity());
			
			
		}else{
			funcform.reset(mapping, request);
		}
		
		return mapping.findForward("formulario");
	}
	
	
	public ActionForward gravarLancamentoExtra(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FuncionarioForm funcionarioForm = (FuncionarioForm) form;
		
		FuncionarioController funcController = new FuncionarioController();  
		
		if(isTokenValid(request)) { 
			resetToken(request); 	
			FuncionarioLctosExtraEntity lancamentoExtra = funcionarioForm.getLancamentoExtra();
			lancamentoExtra.getPk().setFuncionario(funcionarioForm.getFuncionario());
			lancamentoExtra.getPk().setExtra(new LancamentosExtraEntity(funcionarioForm.getIdExtra()));
			lancamentoExtra = funcController.gravarLancamentoExtra(lancamentoExtra);
			funcionarioForm.setLancamentoExtra(lancamentoExtra); 
			funcionarioForm.setIdParam(new Long(funcionarioForm.getFuncionario().getCpf()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.funcionariolancamentoextra.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		funcionarioForm.setFuncionario(funcController.buscarById(funcionarioForm.getIdParam().toString()));
		funcionarioForm.setLancamentoExtra(new FuncionarioLctosExtraEntity());
		funcionarioForm.setIdExtra(null);
		
		return mapping.findForward("formulario"); 
	}
	
	
	public ActionForward excluirLancamentoExtra(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FuncionarioForm funcionarioForm = (FuncionarioForm) form;
		
		FuncionarioController funcController = new FuncionarioController();  		
	
		if(funcionarioForm.getIdParam() != null && funcionarioForm.getIdExtra() != null) { 		
			FuncionarioLctosExtraEntity extra = new FuncionarioLctosExtraEntity();
			extra.getPk().setExtra(new LancamentosExtraEntity(funcionarioForm.getIdExtra()));
			extra.getPk().setFuncionario(funcionarioForm.getFuncionario());
			funcController.excluirLancamentoExtra(extra);
			
			ActionMessages messages = new ActionMessages();
	        messages.add("message", new ActionMessage("msg.funcionariolancamentoextra.removido"));
	        saveMessages(request, messages);   
			
		}
		
		funcionarioForm.setFuncionario(funcController.buscarById(funcionarioForm.getIdParam().toString()));
		funcionarioForm.setLancamentoExtra(new FuncionarioLctosExtraEntity());
		funcionarioForm.setIdExtra(null);
								
		return mapping.findForward("formulario"); 
		
	}
	
	
	public ActionForward atualizar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FuncionarioForm funcform = (FuncionarioForm) form;
		
		FuncionarioController funcController = new FuncionarioController();
		
		CargoController cargoController = new CargoController();
		funcform.getFuncionario().setCargo(cargoController.buscarById(funcform.getIdCargo()));

		funcController.atualiza(funcform.getFuncionario());
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.funcionario.sucesso"));
        saveMessages(request, messages);   
        
        saveToken(request);
        
		return mapping.findForward("formulario");
	}
	
	
}

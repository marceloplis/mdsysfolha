package br.com.mdsysfolha.action.cargo;

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
import br.com.mdsysfolha.controller.CargoController;
import br.com.mdsysfolha.controller.DescontoController;
import br.com.mdsysfolha.entity.BeneficioEntity;
import br.com.mdsysfolha.entity.CargoBeneficioEntity;
import br.com.mdsysfolha.entity.CargoDescontoEntity;
import br.com.mdsysfolha.entity.CargoEntity;
import br.com.mdsysfolha.entity.DescontoEntity;

public class CargoAction extends ActionBase{

	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();
		List<CargoEntity> cargos = cargoController.listTodos();
		cargoForm.setListCargos(cargos);
		cargoForm.setCargo(new CargoEntity());		
		
		saveToken(request);
		
		return mapping.findForward("listar");
	}	
	
	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();   
		
		if(isTokenValid(request)) { 
			resetToken(request); 			
			CargoEntity cargo = cargoController.gravar(cargoForm.getCargo());
			cargoForm.setCargo(cargo); 
			cargoForm.setIdParam(new Long(cargo.getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.cargo.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		cargoForm.setListCargos(cargoController.listTodos());
		cargoForm.setCargo(new CargoEntity());
		
		return mapping.findForward("listar"); 
	}	
	
	
	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CargoForm cargoForm = (CargoForm) form;	
		
		cargoForm.setCargo(new CargoEntity());
		if(cargoForm.getIdParam() != null){
			CargoController cargoController = new CargoController();   
			cargoForm.setCargo(cargoController.buscarById(cargoForm.getIdParam()));
			
			BeneficioController beneficioController = new BeneficioController();
			cargoForm.setListBeneficios(beneficioController.listTodos());
			
			DescontoController descontoController = new DescontoController();
			cargoForm.setListDescontos(descontoController.listTodos());
			
			cargoForm.setCargoBeneficio(new CargoBeneficioEntity());
			cargoForm.setCargoDesconto(new CargoDescontoEntity());
			
			cargoForm.setBeneficioId(null);
			cargoForm.setDescontoId(null);
			
		}else{
			cargoForm.reset(mapping, request);
		}
		
		return mapping.findForward("formulario");
	}
	
	
	public ActionForward gravarBeneficio(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();   
		
		if(isTokenValid(request)) { 
			resetToken(request); 	
			CargoBeneficioEntity cargoBeneficio = cargoForm.getCargoBeneficio();
			cargoBeneficio.getPk().setCargo(cargoForm.getCargo());
			cargoBeneficio.getPk().setBeneficio(new BeneficioEntity(cargoForm.getBeneficioId()));
			cargoBeneficio = cargoController.gravarBeneficio(cargoBeneficio);
			cargoForm.setCargoBeneficio(cargoBeneficio); 
			cargoForm.setIdParam(new Long(cargoForm.getCargo().getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.cargobeneficio.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		cargoForm.setCargo(cargoController.buscarById(cargoForm.getIdParam()));
		cargoForm.setCargoBeneficio(new CargoBeneficioEntity());
		cargoForm.setCargoDesconto(new CargoDescontoEntity());
		cargoForm.setBeneficioId(null);
		cargoForm.setDescontoId(null);
		
		return mapping.findForward("formulario"); 
	}
	
	public ActionForward gravarDesconto(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();   
		
		if(isTokenValid(request)) { 
			resetToken(request); 			
			CargoDescontoEntity cargoDesconto = cargoForm.getCargoDesconto();
			cargoDesconto.getPk().setCargo(cargoForm.getCargo());
			cargoDesconto.getPk().setDesconto(new DescontoEntity(cargoForm.getDescontoId()));
			cargoDesconto = cargoController.gravarDesconto(cargoForm.getCargoDesconto());
			cargoForm.setCargoDesconto(cargoDesconto); 
			cargoForm.setIdParam(new Long(cargoForm.getCargo().getId()));
		} 	
		
		ActionMessages messages = new ActionMessages();
        messages.add("message", new ActionMessage("msg.cargodesconto.sucesso"));
        saveMessages(request, messages);        
        
		saveToken(request);
		
		cargoForm.setCargo(cargoController.buscarById(cargoForm.getIdParam()));
		cargoForm.setCargoBeneficio(new CargoBeneficioEntity());
		cargoForm.setCargoDesconto(new CargoDescontoEntity());
		cargoForm.setBeneficioId(null);
		cargoForm.setDescontoId(null);
		
		return mapping.findForward("formulario"); 
	}
	
	public ActionForward excluir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();  		
	
		if(cargoForm.getIdParam() != null) { 		
			cargoController.excluir(cargoForm.getIdParam());
		}
		
		cargoForm.setListCargos(cargoController.listTodos());
								
		return mapping.findForward("listar"); 
		
	}
	
	public ActionForward excluirBeneficio(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();  		
	
		if(cargoForm.getIdParam() != null && cargoForm.getBeneficioId() != null) { 		
			CargoBeneficioEntity cb = new CargoBeneficioEntity();
			cb.getPk().setBeneficio(new BeneficioEntity(cargoForm.getBeneficioId()));
			cb.getPk().setCargo(new CargoEntity(cargoForm.getIdParam()));
			cargoController.excluirBeneficio(cb);
			
			ActionMessages messages = new ActionMessages();
	        messages.add("message", new ActionMessage("msg.cargobeneficio.removido"));
	        saveMessages(request, messages);   
			
		}
		
		cargoForm.setCargo(cargoController.buscarById(cargoForm.getIdParam()));		
		cargoForm.setBeneficioId(null);
		cargoForm.setDescontoId(null);
								
		return mapping.findForward("formulario"); 
		
	}
	
	
	public ActionForward excluirDesconto(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargoForm cargoForm = (CargoForm) form;
		
		CargoController cargoController = new CargoController();  		
	
		if(cargoForm.getIdParam() != null && cargoForm.getDescontoId() != null) { 		
			CargoDescontoEntity cd = new CargoDescontoEntity();
			cd.getPk().setDesconto(new DescontoEntity(cargoForm.getDescontoId()));
			cd.getPk().setCargo(new CargoEntity(cargoForm.getIdParam()));
			cargoController.excluirDesconto(cd);
			
			ActionMessages messages = new ActionMessages();
	        messages.add("message", new ActionMessage("msg.cargodesconto.removido"));
	        saveMessages(request, messages);   
			
		}
		
		cargoForm.setCargo(cargoController.buscarById(cargoForm.getIdParam()));		
		cargoForm.setBeneficioId(null);
		cargoForm.setDescontoId(null);
								
		return mapping.findForward("formulario"); 
		
	}
	
	
}

package br.com.mdsysfolha.action.beneficio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.BeneficioEntity;

public class BeneficioForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeneficioEntity beneficio;
	
	public List<Integer> listOrdem;
	
	public List<BeneficioEntity> listBeneficios;

		
	public BeneficioEntity getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(BeneficioEntity beneficio) {
		this.beneficio = beneficio;
	}
	
	public List<Integer> getListOrdem() {
		return listOrdem;
	}
	
	public void setListOrdem(List<Integer> listOrdem) {
		this.listOrdem = listOrdem;
	}

	public List<BeneficioEntity> getListBeneficios() {
		return listBeneficios;
	}

	public void setListBeneficios(List<BeneficioEntity> listBeneficios) {
		this.listBeneficios = listBeneficios;
	}


	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( beneficio.getDescricao() == null || beneficio.getDescricao().length() < 1 ) {
	    	  errors.add("nome",new ActionMessage("error.required.o","nome"));
	      }
	      
	      if( beneficio.getTipo_valor() == null || beneficio.getTipo_valor().length() < 1 ) {
	    	  errors.add("tipo",new ActionMessage("error.required.o","tipo valor"));
	      }
	      
	      if(beneficio.getOrdem() == null || beneficio.getOrdem() == 0) {
	    	  errors.add("ordem",new ActionMessage("error.required.o","ordem"));
	      }
	      
	      return errors;
	}
	
}

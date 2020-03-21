package br.com.mdsysfolha.action.desconto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.DescontoEntity;

public class DescontoForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DescontoEntity desconto;
	
	public List<Integer> listOrdem;
	
	public List<DescontoEntity> listDescontos;

	public DescontoEntity getDesconto() {
		return desconto;
	}

	public void setDesconto(DescontoEntity desconto) {
		this.desconto = desconto;
	}
	
	public List<Integer> getListOrdem() {
		return listOrdem;
	}
	
	public void setListOrdem(List<Integer> listOrdem) {
		this.listOrdem = listOrdem;
	}

	public List<DescontoEntity> getListDescontos() {
		return listDescontos;
	}

	public void setListDescontos(List<DescontoEntity> listDescontos) {
		this.listDescontos = listDescontos;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( desconto.getDescricao() == null || desconto.getDescricao().length() < 1 ) {
	    	  errors.add("nome",new ActionMessage("error.required.o","nome"));
	      }
	      
	      if( desconto.getTipo_valor() == null || desconto.getTipo_valor().length() < 1 ) {
	    	  errors.add("tipo",new ActionMessage("error.required.o","tipo valor"));
	      }
	      
	      if(desconto.getOrdem() == null || desconto.getOrdem() == 0) {
	    	  errors.add("ordem",new ActionMessage("error.required.o","ordem"));
	      }
	      
	      return errors;
	}
	
	
	
}

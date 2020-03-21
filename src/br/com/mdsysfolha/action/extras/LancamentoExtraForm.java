package br.com.mdsysfolha.action.extras;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;

public class LancamentoExtraForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LancamentosExtraEntity extra;
	
	public List<LancamentosExtraEntity> listExtras;

	public LancamentosExtraEntity getExtra() {
		return extra;
	}

	public void setExtra(LancamentosExtraEntity extra) {
		this.extra = extra;
	}

	public List<LancamentosExtraEntity> getListExtras() {
		return listExtras;
	}

	public void setListExtras(List<LancamentosExtraEntity> listExtras) {
		this.listExtras = listExtras;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( extra.getDescricao() == null || extra.getDescricao().length() < 1 ) {
	    	  errors.add("nome",new ActionMessage("error.required.o","nome"));
	      }
	      
	      if( extra.getTipo_valor() == null || extra.getTipo_valor().length() < 1 ) {
	    	  errors.add("tipo",new ActionMessage("error.required.o","tipo valor"));
	      }
	      
	      if(extra.getTipo_lancamento() == null || extra.getTipo_lancamento().length() < 1) {
	    	  errors.add("tipo_lancamento",new ActionMessage("error.required.o","tipo lancamento"));
	      }
	      
	      return errors;
	}
}

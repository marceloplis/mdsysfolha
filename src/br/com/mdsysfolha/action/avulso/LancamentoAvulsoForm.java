package br.com.mdsysfolha.action.avulso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;
import br.com.mdsysfolha.util.Utils;

public class LancamentoAvulsoForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LancamentosAvulsoEntity avulso;
	
	public List<LancamentosAvulsoEntity> listAvulsos;
	
	public List<FuncionarioEntity> listFuncionarios;
	
	private String dtLctoParse;
	
	private String dtfiltroInicio;
	
	private String dtfiltroFim;
	
	private String filtFunc;
	
	private Integer filtLoja;
	
	public LancamentosAvulsoEntity getAvulso() {
		return avulso;
	}

	public void setAvulso(LancamentosAvulsoEntity avulso) {
		this.avulso = avulso;
	}

	public List<LancamentosAvulsoEntity> getListAvulsos() {
		return listAvulsos;
	}

	public void setListAvulsos(List<LancamentosAvulsoEntity> listAvulsos) {
		this.listAvulsos = listAvulsos;
	}
	
	public List<FuncionarioEntity> getListFuncionarios() {
		return listFuncionarios;
	}
	
	public void setListFuncionarios(List<FuncionarioEntity> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}
	
	public String getDtLctoParse() {  
		if (avulso.getData()!= null)     
			dtLctoParse =  Utils.converteDataString(avulso.getData());  
		return dtLctoParse ;  
	}  
	  
	public void setDtLctoParse(String string) {  
		dtLctoParse = string;  
	    if ( (dtLctoParse != null) && (dtLctoParse.length() > 0) )      
	    	avulso.setData(Utils.converteData(dtLctoParse ));  
	} 
	
	public String getDtfiltroInicio() {
		return dtfiltroInicio;
	}
	
	public void setDtfiltroInicio(String dtfiltroInicio) {
		this.dtfiltroInicio = dtfiltroInicio;
	}
	
	public String getDtfiltroFim() {
		return dtfiltroFim;
	}
	
	public void setDtfiltroFim(String dtfiltroFim) {
		this.dtfiltroFim = dtfiltroFim;
	}
	
	public String getFiltFunc() {
		return filtFunc;
	}
	
	public void setFiltFunc(String filtFunc) {
		this.filtFunc = filtFunc;
	}
	
	public Integer getFiltLoja() {
		return filtLoja;
	}
	
	public void setFiltLoja(Integer filtLoja) {
		this.filtLoja = filtLoja;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( avulso.getCpf() == null || avulso.getCpf().length() == 0) {
	    	  errors.add("cpf",new ActionMessage("error.required.o","CPF"));
	      }
	      
	      if( avulso.getDescricao() == null || avulso.getDescricao().length() == 0) {
	    	  errors.add("descricao",new ActionMessage("error.required.a","Descricao"));
	      }
	      
	      if( dtLctoParse == null || dtLctoParse.length() == 0) {
	    	  errors.add("data",new ActionMessage("error.required.a","Data"));
	      }
	      
	      if( avulso.getTipo() == null || avulso.getTipo().length() == 0) {
	    	  errors.add("tipo",new ActionMessage("error.required.o","Tipo"));
	      }
	      
	      if( avulso.getValor() == null) {
	    	  errors.add("valor",new ActionMessage("error.required.o","Valor"));
	      }
	      	      
	      return errors;
	}
	
	
}

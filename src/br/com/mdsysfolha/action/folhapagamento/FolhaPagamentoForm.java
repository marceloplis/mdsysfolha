package br.com.mdsysfolha.action.folhapagamento;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.FolhaPagamentoEntity;
import br.com.mdsysfolha.vo.Holerite;

public class FolhaPagamentoForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mes_ano;
	
	public FolhaPagamentoEntity folha;
	
	public List<FolhaPagamentoEntity> listFolhas;
	
	public List<Holerite> holerites;
	
	private Integer filtLoja;
	
	private String filtFunc;
	
	public int funcsSemCargo;
	
	public FolhaPagamentoEntity getFolha() {
		return folha;
	}

	public void setFolha(FolhaPagamentoEntity folha) {
		this.folha = folha;
	}

	public List<FolhaPagamentoEntity> getListFolhas() {
		return listFolhas;
	}

	public void setListFolhas(List<FolhaPagamentoEntity> listFolhas) {
		this.listFolhas = listFolhas;
	}
	
	public String getMes_ano() {
		return mes_ano;
	}
	
	public void setMes_ano(String mes_ano) {
		this.mes_ano = mes_ano;
	}
	
	public List<Holerite> getHolerites() {
		return holerites;
	}
	
	public void setHolerites(List<Holerite> holerites) {
		this.holerites = holerites;
	}
	
	public int getFuncsSemCargo() {
		return funcsSemCargo;
	}
	
	public void setFuncsSemCargo(int funcsSemCargo) {
		this.funcsSemCargo = funcsSemCargo;
	}	
	
	public Integer getFiltLoja() {
		return filtLoja;
	}

	public void setFiltLoja(Integer filtLoja) {
		this.filtLoja = filtLoja;
	}
	
	public String getFiltFunc() {
		return filtFunc;
	}
	
	public void setFiltFunc(String filtFunc) {
		this.filtFunc = filtFunc;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( mes_ano == null || mes_ano.length() == 0) {
	    	  errors.add("mes_ano",new ActionMessage("error.required.o","periodo"));
	      }
	      	      
	      return errors;
	}
	
}

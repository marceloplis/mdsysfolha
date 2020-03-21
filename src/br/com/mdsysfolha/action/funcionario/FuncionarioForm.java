package br.com.mdsysfolha.action.funcionario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.CargoEntity;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioLctosExtraEntity;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;
import br.com.mdsysfolha.util.Utils;

public class FuncionarioForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FuncionarioEntity funcionario;
	
	private List<FuncionarioEntity> listFuncionarios;
	
	private List<CargoEntity> listCargos;
	
	public List<LancamentosExtraEntity> listExtras;
	
	private int filtLoja;
	
	private String filtAtivo;
	
	private String filtCPF;
	
	private String filtNome;
	
	private int filtCargo;
	
	private Long idCargo;
	
	private Long idExtra;
	
	private FuncionarioLctosExtraEntity lancamentoExtra;
	
	private String dtAdmissaoParse;
	private String salarioParse;

	public String getDtAdmissaoParse() {  
		if (funcionario.getData_admissao()!= null)     
			dtAdmissaoParse =  Utils.converteDataString(funcionario.getData_admissao());  
		return dtAdmissaoParse ;  
	}  
	  
	public void setDtAdmissaoParse(String string) {  
		dtAdmissaoParse = string;  
	    if ( (dtAdmissaoParse != null) && (!dtAdmissaoParse.equals("")) )      
	    	funcionario.setData_admissao(Utils.converteData(dtAdmissaoParse ));  
	} 
	
	public String getSalarioParse(){
		if(funcionario.getSalario() != null)
			salarioParse = Utils.doubleToMoeda(funcionario.getSalario());
		return salarioParse;
	}
	
	public void setSalarioParse(String string) {
		salarioParse = string;
		if((salarioParse != null) && (!salarioParse.equals("")))
			funcionario.setSalario(Utils.MoedaStrToDouble(salarioParse));
	}
	
	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<FuncionarioEntity> getListFuncionarios() {
		return listFuncionarios;
	}
	
	public void setListFuncionarios(List<FuncionarioEntity> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}
	
	public List<CargoEntity> getListCargos() {
		return listCargos;
	}
	
	public void setListCargos(List<CargoEntity> listCargos) {
		this.listCargos = listCargos;
	}
	
	public String getFiltAtivo() {
		return filtAtivo;
	}
	
	public void setFiltAtivo(String filtAtivo) {
		this.filtAtivo = filtAtivo;
	}
	
	public int getFiltLoja() {
		return filtLoja;
	}
	
	public void setFiltLoja(int filtLoja) {
		this.filtLoja = filtLoja;
	}
	
	public String getFiltCPF() {
		return filtCPF;
	}
	
	public void setFiltCPF(String filtCPF) {
		this.filtCPF = filtCPF;
	}
	
	public String getFiltNome() {
		return filtNome;
	}
	
	public void setFiltNome(String filtNome) {
		this.filtNome = filtNome;
	}
	
	public int getFiltCargo() {
		return filtCargo;
	}
	
	public void setFiltCargo(int filtCargo) {
		this.filtCargo = filtCargo;
	}
	
	public Long getIdCargo() {
		return idCargo;
	}
	
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}	
	
	
	public List<LancamentosExtraEntity> getListExtras() {
		return listExtras;
	}

	public void setListExtras(List<LancamentosExtraEntity> listExtras) {
		this.listExtras = listExtras;
	}

	public Long getIdExtra() {
		return idExtra;
	}

	public void setIdExtra(Long idExtra) {
		this.idExtra = idExtra;
	}

	public FuncionarioLctosExtraEntity getLancamentoExtra() {
		return lancamentoExtra;
	}

	public void setLancamentoExtra(FuncionarioLctosExtraEntity lancamentoExtra) {
		this.lancamentoExtra = lancamentoExtra;
	}

	/*Overwrite*/
	public void reset(ActionMapping mapping, HttpServletRequest request) { 
		setNmBusca(null);
		setIdParam(null);
	} 
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      
	      if( funcionario.getLoja() == 0) {
	    	  errors.add("loja",new ActionMessage("error.required.a","Loja"));
	      }	      
	      if( funcionario.getSalario() == null || funcionario.getSalario() == 0 ) {
	    	  errors.add("salario",new ActionMessage("error.required.o","Salario"));
	      }
	      
	      if( idCargo == 0) {
	    	  errors.add("Cargo",new ActionMessage("error.required.o","Cargo"));
	      }
	      
	      return errors;
	}
	
}

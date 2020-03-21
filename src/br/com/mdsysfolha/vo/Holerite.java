package br.com.mdsysfolha.vo;

import java.io.Serializable;
import java.util.List;

import br.com.mdsysfolha.entity.FolhaPagamentoFuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaAvulsoEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaBeneficioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaDescontoEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaExtraEntity;
import br.com.mdsysfolha.util.Utils;

public class Holerite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncionarioEntity funcionario;
	
	private FolhaPagamentoFuncionarioEntity folhaFuncionario;

	private List<LancamentosFolhaDescontoEntity> descontos;
	
	private List<LancamentosFolhaBeneficioEntity> beneficios;
	
	private List<LancamentosFolhaAvulsoEntity> avulsoDescontos;
	
	private List<LancamentosFolhaAvulsoEntity> avulsoBeneficios;
	
	private List<LancamentosFolhaExtraEntity> extras;
	
	private Double totalDescontos;
	private String totalDescontosParse;
	
	private Double totalBeneficios;
	private String totalBeneficiosParse;
	
	private Double totalAvulsoDescontos;
	private String totalAvulsoDescontosParse;
	
	private Double totalAvulsoBeneficios;
	private String totalAvulsoBeneficiosParse;
	
	private Double totalExtrasBeneficios;
	private String totalExtrasBeneficiosParse;
	
	private Double totalExtrasDescontos;
	private String totalExtrasDescontosParse;
	
	private String somaTotaisBeneficiosParse;
	private String somaTotaisDescontosParse;
	
	private Double total;
	private String totalParse;
	
	private Double arredondamento;
	private String arredondamentoParse;
	
	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}
	
	public FolhaPagamentoFuncionarioEntity getFolhaFuncionario() {
		return folhaFuncionario;
	}
	
	public void setFolhaFuncionario(FolhaPagamentoFuncionarioEntity folhaFuncionario) {
		this.folhaFuncionario = folhaFuncionario;
	}

	public List<LancamentosFolhaDescontoEntity> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<LancamentosFolhaDescontoEntity> descontos) {
		this.descontos = descontos;
	}

	public List<LancamentosFolhaBeneficioEntity> getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(List<LancamentosFolhaBeneficioEntity> beneficios) {
		this.beneficios = beneficios;
	}

	public List<LancamentosFolhaAvulsoEntity> getAvulsoDescontos() {
		return avulsoDescontos;
	}

	public void setAvulsoDescontos(
			List<LancamentosFolhaAvulsoEntity> avulsoDescontos) {
		this.avulsoDescontos = avulsoDescontos;
	}

	public List<LancamentosFolhaAvulsoEntity> getAvulsoBeneficios() {
		return avulsoBeneficios;
	}

	public void setAvulsoBeneficios(
			List<LancamentosFolhaAvulsoEntity> avulsoBeneficios) {
		this.avulsoBeneficios = avulsoBeneficios;
	}
	
	public List<LancamentosFolhaExtraEntity> getExtras() {
		return extras;
	}
	
	public void setExtras(List<LancamentosFolhaExtraEntity> extras) {
		this.extras = extras;
	}

	public Double getTotalDescontos() {
		return totalDescontos;
	}

	public void setTotalDescontos(Double totalDescontos) {
		this.totalDescontos = totalDescontos;
	}

	public Double getTotalBeneficios() {
		return totalBeneficios;
	}

	public void setTotalBeneficios(Double totalBeneficios) {
		this.totalBeneficios = totalBeneficios;
	}

	public Double getTotalAvulsoDescontos() {
		return totalAvulsoDescontos;
	}

	public void setTotalAvulsoDescontos(Double totalAvulsoDescontos) {
		this.totalAvulsoDescontos = totalAvulsoDescontos;
	}

	public Double getTotalAvulsoBeneficios() {
		return totalAvulsoBeneficios;
	}

	public void setTotalAvulsoBeneficios(Double totalAvulsoBeneficios) {
		this.totalAvulsoBeneficios = totalAvulsoBeneficios;
	}
	
	public Double getTotalExtrasBeneficios() {
		return totalExtrasBeneficios;
	}
	
	public void setTotalExtrasBeneficios(Double totalExtrasBeneficios) {
		this.totalExtrasBeneficios = totalExtrasBeneficios;
	}
	
	public Double getTotalExtrasDescontos() {
		return totalExtrasDescontos;
	}
	
	public void setTotalExtrasDescontos(Double totalExtrasDescontos) {
		this.totalExtrasDescontos = totalExtrasDescontos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Double getArredondamento() {
		return arredondamento;
	}
	
	public void setArredondamento(Double arredondamento) {
		this.arredondamento = arredondamento;
	}	
	
	public String getTotalDescontosParse(){
		if(getTotalDescontos() != null)
			totalDescontosParse = Utils.doubleToMoeda(getTotalDescontos());
		return totalDescontosParse;
	}
	
	public void setTotalDescontosParse(String string) {
		totalDescontosParse = string;
		if((totalDescontosParse != null) && (!totalDescontosParse.equals("")))
			setTotalDescontos(Utils.MoedaStrToDouble(totalDescontosParse));
	}
	
	public String getTotalBeneficiosParse(){
		if(getTotalBeneficios() != null)
			totalBeneficiosParse = Utils.doubleToMoeda(getTotalBeneficios());
		return totalBeneficiosParse;
	}
	
	public void setTotalBeneficiosParse(String string) {
		totalBeneficiosParse = string;
		if((totalBeneficiosParse != null) && (!totalBeneficiosParse.equals("")))
			setTotalBeneficios(Utils.MoedaStrToDouble(totalBeneficiosParse));
	}
	
	
	public String getTotalAvulsoDescontosParse(){
		if(getTotalAvulsoDescontos() != null)
			totalAvulsoDescontosParse = Utils.doubleToMoeda(getTotalAvulsoDescontos());
		return totalAvulsoDescontosParse;
	}
	
	public void setTotalAvulsoDescontosParse(String string) {
		totalAvulsoDescontosParse = string;
		if((totalAvulsoDescontosParse != null) && (!totalAvulsoDescontosParse.equals("")))
			setTotalAvulsoDescontos(Utils.MoedaStrToDouble(totalAvulsoDescontosParse));
	}
	
	public String getTotalAvulsoBeneficiosParse(){
		if(getTotalAvulsoBeneficios() != null)
			totalAvulsoBeneficiosParse = Utils.doubleToMoeda(getTotalAvulsoBeneficios());
		return totalAvulsoBeneficiosParse;
	}
	
	public void setTotalAvulsoBeneficiosParse(String string) {
		totalAvulsoBeneficiosParse = string;
		if((totalAvulsoBeneficiosParse != null) && (!totalAvulsoBeneficiosParse.equals("")))
			setTotalAvulsoBeneficios(Utils.MoedaStrToDouble(totalAvulsoBeneficiosParse));
	}
	
	public String getSomaTotaisBeneficiosParse(){
		if(getTotalBeneficios() != null && getTotalAvulsoBeneficios() != null && folhaFuncionario.getSalario() != null && getTotalExtrasBeneficios() != null)
			somaTotaisBeneficiosParse = Utils.doubleToMoeda(getTotalBeneficios() + getTotalAvulsoBeneficios() + folhaFuncionario.getSalario() + getTotalExtrasBeneficios() + getArredondamento());
		return somaTotaisBeneficiosParse;
	}
	
	public String getSomaTotaisDescontosParse() {
		if(getTotalDescontos() != null && getTotalAvulsoDescontos() != null && getTotalExtrasDescontos() != null)
			somaTotaisDescontosParse = Utils.doubleToMoeda(getTotalDescontos() + getTotalAvulsoDescontos() + getTotalExtrasDescontos());
		return somaTotaisDescontosParse;
	}
	
	public String getTotalExtrasBeneficiosParse(){
		if(getTotalExtrasBeneficios() != null)
			totalExtrasBeneficiosParse = Utils.doubleToMoeda(getTotalExtrasBeneficios());
		return totalExtrasBeneficiosParse;
	}
	
	public void setTotalExtrasBeneficiosParse(String string) {
		totalExtrasBeneficiosParse = string;
		if((totalExtrasBeneficiosParse != null) && (!totalExtrasBeneficiosParse.equals("")))
			setTotalExtrasBeneficios(Utils.MoedaStrToDouble(totalExtrasBeneficiosParse));
	}	
	
	
	public String getTotalExtrasDescontosParse(){
		if(getTotalExtrasDescontos() != null)
			totalExtrasDescontosParse = Utils.doubleToMoeda(getTotalExtrasDescontos());
		return totalExtrasDescontosParse;
	}
	
	public void setTotalExtrasDescontosParse(String string) {
		totalExtrasDescontosParse = string;
		if((totalExtrasDescontosParse != null) && (!totalExtrasDescontosParse.equals("")))
			setTotalExtrasDescontos(Utils.MoedaStrToDouble(totalExtrasDescontosParse));
	}
	
	
	
	public String getTotalParse(){
		if(getTotal() != null)
			totalParse = Utils.doubleToMoeda(getTotal());
		return totalParse;
	}
	
	public void setTotalParse(String string) {
		totalParse = string;
		if((totalParse != null) && (!totalParse.equals("")))
			setTotal(Utils.MoedaStrToDouble(totalParse));
	}
	
	
	public String getArredondamentoParse(){
		if(getArredondamento() != null)
			arredondamentoParse = Utils.doubleToMoeda(getArredondamento());
		return arredondamentoParse;
	}
	
	public void setArredondamentoParse(String string) {
		arredondamentoParse = string;
		if((arredondamentoParse != null) && (!arredondamentoParse.equals("")))
			setArredondamento(Utils.MoedaStrToDouble(arredondamentoParse));
	}
	

	public Integer getLoja() {		
		return getFolhaFuncionario() != null ? getFolhaFuncionario().getLoja() : getFuncionario().getLoja();
	}

	public String getCargo() {
		return getFolhaFuncionario() != null ? getFolhaFuncionario().getCargo() : getFuncionario().getCargo().getDescricao();
	}

	public String getSalario() {
		return getFolhaFuncionario() != null ? getFolhaFuncionario().getSalarioParse() : getFuncionario().getSalarioParse();
	}
	
	
	
	
}

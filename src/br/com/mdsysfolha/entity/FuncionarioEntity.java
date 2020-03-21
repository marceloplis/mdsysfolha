package br.com.mdsysfolha.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.mdsysfolha.util.Utils;

@Entity
@Table(name="tbfuncionarios")
public class FuncionarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FuncionarioEntity(){		
	}
	
	@Id
	@Column(name="func_cpf")
	private String cpf;
	
	@Column(name="func_nome")
	private String nome;
	
	@Column(name="func_salario")
	private Double salario;
	
	@Transient
	private String salarioParse;
	
	@Column(name="func_ativo")
	private String fl_ativo;
	
	@Column(name="func_dtAdmissao")
	private Date data_admissao;
	
	@Column(name="func_dtDemissao")
	private Date data_demissao;
	
	@Column(name="func_loja")
	private int loja;

	@OneToOne
	@JoinColumn(name = "func_cargo", referencedColumnName = "carg_sequencial")
	private CargoEntity cargo;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.funcionario", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<FuncionarioLctosExtraEntity> lancamentosExtras = new HashSet<FuncionarioLctosExtraEntity>(0);
	
	@Transient
	private Double salarioLiq;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getFl_ativo() {
		return fl_ativo;
	}

	public void setFl_ativo(String fl_ativo) {
		this.fl_ativo = fl_ativo;
	}

	public Date getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Date data_admissao) {
		this.data_admissao = data_admissao;
	}

	public Date getData_emissao() {
		return data_demissao;
	}

	public void setData_demissao(Date data_demissao) {
		this.data_demissao = data_demissao;
	}

	public int getLoja() {
		return loja;
	}

	public void setLoja(int loja) {
		this.loja = loja;
	}
	
	public CargoEntity getCargo() {
		return cargo;
	}
	
	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}
	
	public Set<FuncionarioLctosExtraEntity> getLancamentosExtras() {
		return lancamentosExtras;
	}
	
	public void setLancamentosExtras(
			Set<FuncionarioLctosExtraEntity> lancamentosExtras) {
		this.lancamentosExtras = lancamentosExtras;
	}
	
	public String getSalarioParse(){
		if(getSalario() != null)
			salarioParse = Utils.doubleToMoeda(getSalario());
		return salarioParse;
	}
	
	public void setSalarioParse(String string) {
		salarioParse = string;
		if((salarioParse != null) && (!salarioParse.equals("")))
			setSalario(Utils.MoedaStrToDouble(salarioParse));
	}
	
	public Double getSalarioLiq() {
		return salarioLiq;
	}
	
	public void setSalarioLiq(Double salarioLiq) {
		this.salarioLiq = salarioLiq;
	}
	
}

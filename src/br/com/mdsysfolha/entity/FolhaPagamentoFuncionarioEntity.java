package br.com.mdsysfolha.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.mdsysfolha.util.Utils;

@Entity
@Table(name="tbfolhapagamentofuncionario")
@SequenceGenerator(name = "seq_folhafunc", sequenceName = "seq_folhafunc", allocationSize = 1)
public class FolhaPagamentoFuncionarioEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_folhafunc")
	@Column(name="fpgfunc_sequencial")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "fpgfunc_folha", referencedColumnName = "fpgto_sequencial", nullable = true)
	private FolhaPagamentoEntity folhaPagamento;	
	
	@OneToOne
	@JoinColumn(name = "fpgfunc_funcionario", referencedColumnName = "func_cpf", nullable = true)
	private FuncionarioEntity funcionario;
	
	@Column(name="fpgfunc_cargo")
	private String cargo;
	
	@Column(name="fpgfunc_loja")
	private Integer loja;
	
	@Column(name="fpgfunc_salario")
	private Double salario;
	
	@Transient
	private String salarioParse;
	
	@Transient
	private Double salarioLiq;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FolhaPagamentoEntity getFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(FolhaPagamentoEntity folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
	}

	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Integer getLoja() {
		return loja;
	}

	public void setLoja(Integer loja) {
		this.loja = loja;
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

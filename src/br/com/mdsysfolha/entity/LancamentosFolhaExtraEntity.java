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
@Table(name="tbfolhapagamentofuncionarioextra")
@SequenceGenerator(name = "seq_lctos_extra", sequenceName = "seq_lctos_extra", allocationSize = 1)
public class LancamentosFolhaExtraEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_lctos_extra")
	@Column(name="fpgext_sequencial")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "fpgext_funcionario", referencedColumnName = "func_cpf", nullable = true)
	private FuncionarioEntity funcionario;
	
	@OneToOne
	@JoinColumn(name = "fpgext_folha", referencedColumnName = "fpgto_sequencial", nullable = true)
	private FolhaPagamentoEntity folhaPagamento;
	
	@OneToOne
	@JoinColumn(name = "fpgext_extra", referencedColumnName = "ext_sequencial", nullable = true)
	private LancamentosExtraEntity extra;
	
	@Column(name="fpgext_valor_calculado")
	private Double valor;
	
	@Transient
	private String valorParse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public FolhaPagamentoEntity getFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(FolhaPagamentoEntity folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
	}

	public LancamentosExtraEntity getExtra() {
		return extra;
	}

	public void setExtra(LancamentosExtraEntity extra) {
		this.extra = extra;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getValorParse(){
		if(getValor() != null)
			valorParse = Utils.doubleToMoeda(getValor());
		return valorParse;
	}
	
	public void setValorParse(String string) {
		valorParse = string;
		if((valorParse != null) && (!valorParse.equals("")))
			setValor(Utils.MoedaStrToDouble(valorParse));
	}
	
}

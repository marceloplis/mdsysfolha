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
@Table(name="tbfolhapagamentodesconto")
@SequenceGenerator(name = "seq_lctos_desc", sequenceName = "seq_lctos_desc", allocationSize = 1)
public class LancamentosFolhaDescontoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_lctos_desc")
	@Column(name="fpgdesc_sequencial")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "fpgdesc_funcionario", referencedColumnName = "func_cpf", nullable = true)
	private FuncionarioEntity funcionario;
	
	@OneToOne
	@JoinColumn(name = "fpgdesc_folha", referencedColumnName = "fpgto_sequencial", nullable = true)
	private FolhaPagamentoEntity folhaPagamento;
	
	@OneToOne
	@JoinColumn(name = "fpgdesc_desconto", referencedColumnName = "desc_sequencial", nullable = true)
	private DescontoEntity desconto;
	
	@Column(name="fpgdesc_valor_calculado")
	private Double valor;
	
	@Transient
	private String valorDescontoParse;

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

	public DescontoEntity getDesconto() {
		return desconto;
	}

	public void setDesconto(DescontoEntity desconto) {
		this.desconto = desconto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getValorDescontoParse(){
		if(getValor() != null)
			valorDescontoParse = Utils.doubleToMoeda(getValor());
		return valorDescontoParse;
	}
	
	public void setValorDescontoParse(String string) {
		valorDescontoParse = string;
		if((valorDescontoParse != null) && (!valorDescontoParse.equals("")))
			setValor(Utils.MoedaStrToDouble(valorDescontoParse));
	}	
	
	
}

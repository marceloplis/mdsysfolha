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
@Table(name="tbfolhapagamentobeneficio")
@SequenceGenerator(name = "seq_lctos_benef", sequenceName = "seq_lctos_benef", allocationSize = 1)
public class LancamentosFolhaBeneficioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_lctos_benef")
	@Column(name="fpgben_sequencial")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "fpgben_funcionario", referencedColumnName = "func_cpf", nullable = true)
	private FuncionarioEntity funcionario;
	
	@OneToOne
	@JoinColumn(name = "fpgben_folha", referencedColumnName = "fpgto_sequencial", nullable = true)
	private FolhaPagamentoEntity folhaPagamento;
	
	@OneToOne
	@JoinColumn(name = "fpgben_beneficio", referencedColumnName = "benef_sequencial", nullable = true)
	private BeneficioEntity beneficio;
	
	@Column(name="fpgben_valor_calculado")
	private Double valor;
	
	@Transient
	private String valorBeneficioParse;

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

	public BeneficioEntity getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(BeneficioEntity beneficio) {
		this.beneficio = beneficio;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getValorBeneficioParse(){
		if(getValor() != null)
			valorBeneficioParse = Utils.doubleToMoeda(getValor());
		return valorBeneficioParse;
	}
	
	public void setValorBeneficioParse(String string) {
		valorBeneficioParse = string;
		if((valorBeneficioParse != null) && (!valorBeneficioParse.equals("")))
			setValor(Utils.MoedaStrToDouble(valorBeneficioParse));
	}	
	
}

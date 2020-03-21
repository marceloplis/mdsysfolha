package br.com.mdsysfolha.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.mdsysfolha.util.Utils;

@Entity
@Table(name="tboutroslancamentos")
@SequenceGenerator(name = "seq_outros_lctos", sequenceName = "seq_outros_lctos", allocationSize = 1)
public class LancamentosAvulsoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LancamentosAvulsoEntity(){}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_outros_lctos")
	@Column(name="olcto_sequencial")
	private Long id;
	
	@Column(name="olcto_descricao")
	private String descricao;
	
	@Column(name="olcto_tipo")
	private String tipo;
	
	@Column(name="olcto_valor")
	private Double valor;
	
	@Column(name="olcto_data")
	private Date data;
	
	@Column(name="olcto_data_criacao")
	private Date data_criacao;
	
	@Column(name="olcto_arquivo")
	private String nm_arquivo;
	
	@Column(name="olcto_tipo_folha")
	private String tp_folha;
	
	@ManyToOne
	@JoinColumn(name = "olcto_funcionario", referencedColumnName = "func_cpf", nullable = true)
	private FuncionarioEntity funcionario;
	
	@Transient
	private String cpf;
	
	@Transient
	private String valorParse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getData_criacao() {
		return data_criacao;
	}
	
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNm_arquivo() {
		return nm_arquivo;
	}
	
	public void setNm_arquivo(String nm_arquivo) {
		this.nm_arquivo = nm_arquivo;
	}
	
	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
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
	
	public String getTp_folha() {
		return tp_folha;
	}
	
	public void setTp_folha(String tp_folha) {
		this.tp_folha = tp_folha;
	}
	
	
}

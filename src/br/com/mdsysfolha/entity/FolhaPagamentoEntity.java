package br.com.mdsysfolha.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbfolhapagamento")
@SequenceGenerator(name = "seq_folha", sequenceName = "seq_folha", allocationSize = 1)
public class FolhaPagamentoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_folha")
	@Column(name="fpgto_sequencial")
	private Long id;
	
	@Column(name="fpgto_ano")
	private int ano;
	
	@Column(name="fpgto_mes")
	private int mes;
	
	@Column(name="fpgto_data_criacao")
	private Date data_criacao;
	
	@Column(name="fpgto_status")
	private String status;
	
	@Column(name="fpgto_tipo")
	private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}

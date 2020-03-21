package br.com.mdsysfolha.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbarquivooutroslctos")
@SequenceGenerator(name = "seq_logarquivo", sequenceName = "seq_logarquivo", allocationSize = 1)
public class LogArquivosCargaDadosEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_logarquivo")
	@Column(name="arqlc_sequencial")
	private Long id;
		
	@Column(name="arqlc_nome")
	private String nome;
	
	@Column(name="arqlc_qt_debitos")
	private int qt_total_descontos;
	
	@Column(name="arqlc_qt_creditos")
	private int qt_total_beneficios;
	
	@Column(name="arqlc_qt_total")
	private int qt_total;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQt_total_descontos() {
		return qt_total_descontos;
	}

	public void setQt_total_descontos(int qt_total_descontos) {
		this.qt_total_descontos = qt_total_descontos;
	}

	public int getQt_total_beneficios() {
		return qt_total_beneficios;
	}

	public void setQt_total_beneficios(int qt_total_beneficios) {
		this.qt_total_beneficios = qt_total_beneficios;
	}

	public int getQt_total() {
		return qt_total;
	}

	public void setQt_total(int qt_total) {
		this.qt_total = qt_total;
	}
	
	
}

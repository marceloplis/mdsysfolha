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

@Entity
@Table(name="tbfolhapagamentooutroslctos")
@SequenceGenerator(name = "seq_lctos_avulso", sequenceName = "seq_lctos_avulso", allocationSize = 1)
public class LancamentosFolhaAvulsoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_lctos_avulso")
	@Column(name="fpgolc_sequencial")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "fpgolc_folha", referencedColumnName = "fpgto_sequencial", nullable = true)
	private FolhaPagamentoEntity folhaPagamento;
	
	@OneToOne
	@JoinColumn(name = "fpgolc_lancamento", referencedColumnName = "olcto_sequencial", nullable = true)
	private LancamentosAvulsoEntity avulso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FolhaPagamentoEntity getFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(FolhaPagamentoEntity folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
	}

	public LancamentosAvulsoEntity getAvulso() {
		return avulso;
	}

	public void setAvulso(LancamentosAvulsoEntity avulso) {
		this.avulso = avulso;
	}
	
	
}

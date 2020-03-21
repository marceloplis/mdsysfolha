package br.com.mdsysfolha.dao.common;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.mdsysfolha.dao.impl.BeneficioDao;
import br.com.mdsysfolha.dao.impl.CargoBeneficioDao;
import br.com.mdsysfolha.dao.impl.CargoDao;
import br.com.mdsysfolha.dao.impl.CargoDescontoDao;
import br.com.mdsysfolha.dao.impl.DescontoDao;
import br.com.mdsysfolha.dao.impl.FolhaPagamentoDao;
import br.com.mdsysfolha.dao.impl.FolhaPagamentoFuncionarioDao;
import br.com.mdsysfolha.dao.impl.FuncionarioDao;
import br.com.mdsysfolha.dao.impl.FuncionarioLancamentoExtraDao;
import br.com.mdsysfolha.dao.impl.LancamentosAvulsoDao;
import br.com.mdsysfolha.dao.impl.LancamentosExtraDao;
import br.com.mdsysfolha.dao.impl.LancamentosFolhaAvulsoDao;
import br.com.mdsysfolha.dao.impl.LancamentosFolhaBeneficioDao;
import br.com.mdsysfolha.dao.impl.LancamentosFolhaDescontoDao;
import br.com.mdsysfolha.dao.impl.LancamentosFolhaExtraDao;
import br.com.mdsysfolha.dao.impl.LogArquivosCargaDadosDao;
import br.com.mdsysfolha.dao.impl.UsuarioDao;
import br.com.mdsysfolha.util.HibernateUtil;

public class FactoryDao {

	private Session session;
    private Transaction transaction;  
      
      
    public FactoryDao(){
    	session =  HibernateUtil.currentSession();
    }  
	
	public void beginTransaction(){
		this.transaction = this.session.beginTransaction();
	}
	
	public void commit(){
		this.transaction.commit();
		this.transaction = null;
	}
	
	public boolean hasTransaction(){
		return this.transaction != null;
	}
	
	public void rollback(){
		this.transaction.rollback();
		this.transaction = null;
	}
	
	@SuppressWarnings("deprecation")
	public void close(){
		try {
			this.session.connection().close();
		} catch (HibernateException e) {
			System.out.println("Erro ao fechar conexão com o BD: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão com o BD: " + e.getMessage());
		}
		this.session.close();
	}
	
	
	public void clear(){
		this.session.clear();
	}
	
	/*
	 * Modelos
	 * 
	 */
	public UsuarioDao getUsuarioDao(){
		return new UsuarioDao(this.session);
	}
	
	public FuncionarioDao getFuncionarioDao(){
		return new FuncionarioDao(this.session);
	}
	
	public LancamentosAvulsoDao getLancamentoAvulsoDao(){
		return new LancamentosAvulsoDao(this.session);
	}
	
	public CargoDao getCargoDao(){
		return new CargoDao(this.session);
	}
	
	public BeneficioDao getBeneficioDao(){
		return new BeneficioDao(this.session);
	}
	
	public DescontoDao getDescontoDao(){
		return new DescontoDao(this.session);
	}
	
	public CargoBeneficioDao getCargoBeneficioDao(){
		return new CargoBeneficioDao(this.session);
	}
	
	public CargoDescontoDao getCargoDescontoDao(){
		return new CargoDescontoDao(this.session);
	}
	
	public FolhaPagamentoDao getFolhaPagamentoDao(){
		return new FolhaPagamentoDao(this.session);
	}
	
	public LancamentosFolhaAvulsoDao getLancamentosFolhaAvulsoDao(){
		return new LancamentosFolhaAvulsoDao(this.session);
	}
	
	public LancamentosFolhaBeneficioDao getLancamentosFolhaBeneficioDao(){
		return new LancamentosFolhaBeneficioDao(this.session);
	}
	
	public LancamentosFolhaDescontoDao getLancamentosFolhaDescontoDao(){
		return new LancamentosFolhaDescontoDao(this.session);
	}
	
	public LogArquivosCargaDadosDao getLogArquivosCargaDadosDao(){
		return new LogArquivosCargaDadosDao(this.session);
	}
	
	public LancamentosExtraDao getLancamentosExtraDao(){
		return new LancamentosExtraDao(this.session);
	}
	
	public FuncionarioLancamentoExtraDao getFuncionarioLancamentoExtraDao(){
		return new FuncionarioLancamentoExtraDao(this.session);
	}
	
	public LancamentosFolhaExtraDao getLancamentosFolhaExtraDao(){
		return new LancamentosFolhaExtraDao(this.session);
	}
	
	public FolhaPagamentoFuncionarioDao getFolhaPagamentoFuncionarioDao(){
		return new FolhaPagamentoFuncionarioDao(this.session);
	}
	
}

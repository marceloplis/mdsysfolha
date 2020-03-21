package br.com.mdsysfolha.controller;

import java.util.Date;
import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.FolhaPagamentoEntity;
import br.com.mdsysfolha.entity.FolhaPagamentoFuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaAvulsoEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaBeneficioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaDescontoEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaExtraEntity;

public class FolhaPagamentoController {

	private final FactoryDao factoryDao;
	
	public FolhaPagamentoController(){
		this.factoryDao = new FactoryDao();
	}
	
	public List<FolhaPagamentoEntity> listTodos(){		
		return this.factoryDao.getFolhaPagamentoDao().listaTudo();
	}
	
	public FolhaPagamentoEntity gravar(FolhaPagamentoEntity folha) throws Exception{		
		
		if(folha.getId() == null || folha.getId() == 0){	
			folha.setData_criacao(new Date());
			folha.setStatus("A");
			this.factoryDao.getFolhaPagamentoDao().adiciona(folha);
		}else{				
			this.factoryDao.getFolhaPagamentoDao().altera(folha);
		}
			
		return folha;
	}
	
	public void excluir(Long id){
		FolhaPagamentoEntity folha = new FolhaPagamentoEntity();
		folha.setId(id);		
		this.factoryDao.getFolhaPagamentoDao().remove(folha);
	}
	
	public FolhaPagamentoEntity buscarById(Long id){
		FolhaPagamentoEntity folha = this.factoryDao.getFolhaPagamentoDao().procura(id);
		return folha;
	}
	
	public void gravaFolhaFuncionario(FolhaPagamentoFuncionarioEntity folhaFunc){
		this.factoryDao.getFolhaPagamentoFuncionarioDao().adiciona(folhaFunc);
	}
	
	public void gravaDescontos(LancamentosFolhaDescontoEntity desc){
		this.factoryDao.getLancamentosFolhaDescontoDao().adiciona(desc);
	}
	
	public void gravaBeneficios(LancamentosFolhaBeneficioEntity benef){
		this.factoryDao.getLancamentosFolhaBeneficioDao().adiciona(benef);
	}
	
	public void gravaAvulsos(LancamentosFolhaAvulsoEntity avulso){
		this.factoryDao.getLancamentosFolhaAvulsoDao().adiciona(avulso);
	}
	
	public void gravaExtras(LancamentosFolhaExtraEntity extra){
		this.factoryDao.getLancamentosFolhaExtraDao().adiciona(extra);
	}
	
	public FolhaPagamentoFuncionarioEntity buscarFolhaFunc(FuncionarioEntity func, Long idFolha){		
		return this.factoryDao.getFolhaPagamentoFuncionarioDao().buscaPorFolhaFuncionario(func, idFolha);
	}
	
	public List<FolhaPagamentoFuncionarioEntity> listarFolhaFuncByFolha(Long idFolha, Integer loja){		
		return this.factoryDao.getFolhaPagamentoFuncionarioDao().listarFolhaFuncionarioPorFolha(idFolha, loja);
	}
	
	public List<LancamentosFolhaDescontoEntity> buscarFolhaDescByFunc(FuncionarioEntity func, Long idFolha){		
		return this.factoryDao.getLancamentosFolhaDescontoDao().listaDescontosPorFuncionario(func, idFolha);
	}
	
	public List<LancamentosFolhaBeneficioEntity> buscarFolhaBenefByFunc(FuncionarioEntity func, Long idFolha){		
		return this.factoryDao.getLancamentosFolhaBeneficioDao().listaBeneficiosPorFuncionario(func, idFolha);
	}
	
	public List<LancamentosFolhaAvulsoEntity> buscarFolhaAvulsoBenefByFunc(FuncionarioEntity func, Long idFolha){		
		return this.factoryDao.getLancamentosFolhaAvulsoDao().listaBeneficiosPorFuncionario(func, idFolha);
	}
	
	public List<LancamentosFolhaAvulsoEntity> buscarFolhaAvulsoDescByFunc(FuncionarioEntity func, Long idFolha){		
		return this.factoryDao.getLancamentosFolhaAvulsoDao().listaDescontosPorFuncionario(func, idFolha);
	}
	
	public List<LancamentosFolhaExtraEntity> buscarFolhaExtraByFunc(FuncionarioEntity func, Long idFolha){		
		return this.factoryDao.getLancamentosFolhaExtraDao().listaExtrasPorFuncionario(func, idFolha);
	}
		
	public void excluirHolerites(Long idFolha){
		
		this.factoryDao.getFolhaPagamentoFuncionarioDao().deleteByFolhaPagamento(idFolha);
		
		this.factoryDao.getLancamentosFolhaDescontoDao().deleteByFolhaPagamento(idFolha);
		
		this.factoryDao.getLancamentosFolhaBeneficioDao().deleteByFolhaPagamento(idFolha);
		
		this.factoryDao.getLancamentosFolhaAvulsoDao().deleteByFolhaPagamento(idFolha);
		
		this.factoryDao.getLancamentosFolhaExtraDao().deleteByFolhaPagamento(idFolha);
		
		
	}
	
}

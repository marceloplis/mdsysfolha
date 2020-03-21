package br.com.mdsysfolha.controller;

import java.util.Date;
import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;

public class LancamentoAvulsoController {

	private final FactoryDao factoryDao;
	
	public LancamentoAvulsoController(){
		this.factoryDao = new FactoryDao();
	}
	
	public List<LancamentosAvulsoEntity> listTodos(){		
		return this.factoryDao.getLancamentoAvulsoDao().listaTudo();
	}
	
	public List<LancamentosAvulsoEntity> filtro(Date dtInicio, Date dtFim, String cpf, Integer loja){		
		return this.factoryDao.getLancamentoAvulsoDao().filtro(dtInicio, dtFim, cpf, loja);
	}
	
	public List<LancamentosAvulsoEntity> listaByFuncData(String cpf, int ano, int mes, String tpFolha){		
		return this.factoryDao.getLancamentoAvulsoDao().listaByFuncData(cpf, ano, mes, tpFolha);
	}
	
	public LancamentosAvulsoEntity gravar(LancamentosAvulsoEntity avulso) throws Exception{		
		
		if(avulso.getId() == null || avulso.getId() == 0){	
			avulso.setData_criacao(new Date());
			FuncionarioEntity funcionario = new FuncionarioEntity();
			funcionario.setCpf(avulso.getCpf());
			avulso.setFuncionario(funcionario);
			this.factoryDao.getLancamentoAvulsoDao().adiciona(avulso);
		}else{				
			this.factoryDao.getLancamentoAvulsoDao().altera(avulso);
		}
			
		return avulso;
	}
	
	
	public void excluir(Long id){
		LancamentosAvulsoEntity avulso = new LancamentosAvulsoEntity();
		avulso.setId(id);		
		this.factoryDao.getLancamentoAvulsoDao().remove(avulso);
	}
	
}

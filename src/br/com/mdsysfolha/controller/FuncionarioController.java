package br.com.mdsysfolha.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioLctosExtraEntity;

public class FuncionarioController {

	private final FactoryDao factoryDao;
	
	public FuncionarioController(){
		this.factoryDao = new FactoryDao();
	}
	
	public FuncionarioEntity addByFile(FuncionarioEntity func) throws NoSuchAlgorithmException, Exception{
		
		FuncionarioEntity funcDB = this.factoryDao.getFuncionarioDao().buscaByCPF(func.getCpf());
		
		if(funcDB == null && func.getCpf() != null && func.getCpf().length() > 0){
			this.factoryDao.getFuncionarioDao().adiciona(func);
		}
		
		return func;
	}
	
	
	public FuncionarioEntity atualiza(FuncionarioEntity func) throws NoSuchAlgorithmException, Exception{
		this.factoryDao.getFuncionarioDao().atualiza(func);
		return func;
	}
	
	public List<FuncionarioEntity> listTodos(){		
		return this.factoryDao.getFuncionarioDao().listaTodos();
	}
	
	public List<FuncionarioEntity> listTodosAtivos(){		
		return this.factoryDao.getFuncionarioDao().listaTodosAtivos();
	}
	
	public List<FuncionarioEntity> filtro(int loja, String flAtivo, String cpf, String nome, int cargo){		
		return this.factoryDao.getFuncionarioDao().filtro(loja, flAtivo, cpf, nome, cargo);
	}
	
	public FuncionarioEntity buscarById(String id){
		FuncionarioEntity funcionario = this.factoryDao.getFuncionarioDao().buscaByCPF(id);
		return funcionario;
	}
	
	public FuncionarioLctosExtraEntity gravarLancamentoExtra(FuncionarioLctosExtraEntity extra){		
		this.factoryDao.getFuncionarioLancamentoExtraDao().SaveOrUpdate(extra);		
		return extra;	
	}
	
	
	public void excluirLancamentoExtra(FuncionarioLctosExtraEntity extra){
		this.factoryDao.getFuncionarioLancamentoExtraDao().remove(extra);
	}
	
	
}

package br.com.mdsysfolha.helper;

import java.util.Date;

import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;
import br.com.mdsysfolha.layouts.FuncionarioFile;
import br.com.mdsysfolha.layouts.LancamentoFile;

public class FileHelper {

	public static FuncionarioEntity transformToFuncionarioDB(FuncionarioFile file){
		
		FuncionarioEntity func = new FuncionarioEntity();
		func.setCpf(file.cpf);
		func.setNome(file.nome);
		func.setSalario(file.salario);
		func.setLoja(file.loja);
		func.setData_admissao(file.dataAdmissao);
		func.setFl_ativo(file.status);
		
		return func;
		
	}
	
	
	public static LancamentosAvulsoEntity transformToLancamentoDB(LancamentoFile file){
		
		LancamentosAvulsoEntity lcto = new LancamentosAvulsoEntity();
		lcto.setTipo(file.tipoLancamento);		
		lcto.setDescricao(file.descricao);
		lcto.setValor(file.valor);
		lcto.setData(file.data);
		lcto.setData_criacao(new Date());
		lcto.setCpf(file.cpf);
		
		return lcto;
		
	}
	
}

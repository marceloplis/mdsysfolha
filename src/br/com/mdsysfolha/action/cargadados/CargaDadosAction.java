package br.com.mdsysfolha.action.cargadados;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;
import org.coury.jfilehelpers.engines.FileHelperEngine;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.FuncionarioController;
import br.com.mdsysfolha.controller.LancamentoController;
import br.com.mdsysfolha.controller.LogArquivosCargaDadosController;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;
import br.com.mdsysfolha.entity.LogArquivosCargaDadosEntity;
import br.com.mdsysfolha.enums.TipoArquivoEnum;
import br.com.mdsysfolha.enums.TipoLancamentoEnum;
import br.com.mdsysfolha.helper.FileHelper;
import br.com.mdsysfolha.layouts.FuncionarioFile;
import br.com.mdsysfolha.layouts.LancamentoFile;

public class CargaDadosAction extends ActionBase {

	public ActionForward formulario(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("formulario");
	}
	
	@SuppressWarnings("deprecation")
	public ActionForward carregarArquivo(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CargaDadosForm fileUploadForm = (CargaDadosForm)form;
		
	    FormFile file = fileUploadForm.getFile();
	    String nm_arquivo = file.getFileName();
	    
	    LogArquivosCargaDadosController logController = new LogArquivosCargaDadosController();
	    
	    LogArquivosCargaDadosEntity logArq = logController.getByName(nm_arquivo);
        if(logArq != null){
        	ActionErrors error = new ActionErrors();
        	error.add("error", new ActionMessage("error.arquivo.existe"));
        	saveErrors(request, error);                     
    		return mapping.findForward("formulario");
        }
	    
	    try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while(br.ready()) {
                if(fileUploadForm.getTipoArquivo().equals(TipoArquivoEnum.FUNCIONARIO.getValue())){
	            	
                	FileHelperEngine<FuncionarioFile> engine = new FileHelperEngine<FuncionarioFile>(FuncionarioFile.class);
	                List<FuncionarioFile> funcs = new ArrayList<FuncionarioFile>();
	
	                funcs = engine.readStream(br, 1000);
	                
	                FuncionarioController funcController = new FuncionarioController();
	                List<FuncionarioEntity> funcsRetorno = new ArrayList<FuncionarioEntity>();
	                for(int i=0; i<funcs.size();i++){
	                	FuncionarioEntity func = FileHelper.transformToFuncionarioDB(funcs.get(i));
	                	funcsRetorno.add(funcController.addByFile(func));
	                }
	                fileUploadForm.setFuncionarios(funcsRetorno);
	                
                }else if(fileUploadForm.getTipoArquivo().equals(TipoArquivoEnum.LANCAMENTOS.getValue())){
                	
                	FileHelperEngine<LancamentoFile> engine = new FileHelperEngine<LancamentoFile>(LancamentoFile.class);
	                List<LancamentoFile> lctos = new ArrayList<LancamentoFile>();
	
	                lctos = engine.readStream(br, 1000);
	                
	                LancamentoController lctoController = new LancamentoController();
	                List<LancamentosAvulsoEntity> lctosRetorno = new ArrayList<LancamentosAvulsoEntity>();
	                
	                int qt_desc = 0;
	                int qt_benef = 0;
	                int qt_total = lctos.size();
	                
	                LogArquivosCargaDadosEntity log = new LogArquivosCargaDadosEntity();
	                log.setNome(nm_arquivo);
	                log.setQt_total(qt_total);
	                
	                for(int i=0; i<lctos.size();i++){
	                	LancamentosAvulsoEntity lcto = FileHelper.transformToLancamentoDB(lctos.get(i));
	                	if(lcto.getTipo().equals(TipoLancamentoEnum.CREDITO.getValue())){
	                		qt_benef++;
	                	}else if(lcto.getTipo().equals(TipoLancamentoEnum.DEBITO.getValue())){
	                		qt_desc++;
	                	}
	                	lcto.setNm_arquivo(nm_arquivo);
	                	lcto.setTp_folha("M");
	                	lctosRetorno.add(lctoController.addByFile(lcto));
	                }
	                log.setQt_total_beneficios(qt_benef);
	                log.setQt_total_descontos(qt_desc);
	                
	                logController.gravar(log);
	                
	                fileUploadForm.setLancamentos(lctosRetorno);
                	
                }
                
            }
            br.close();
        } catch(IOException ioe) {
             
        }           	   
	    
		return mapping.findForward("sucesso");
	}
	
}

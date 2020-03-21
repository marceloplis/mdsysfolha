package br.com.mdsysfolha.action.cargadados;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;

public class CargaDadosForm extends FormBase {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5059290127597134291L;

	private String tipoArquivo;
	
	private FormFile file;
	
	private List<FuncionarioEntity> funcionarios;
	
	private List<LancamentosAvulsoEntity> lancamentos;
	
	public String getTipoArquivo() {
		return tipoArquivo;
	}
	
	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	
	public List<FuncionarioEntity> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<FuncionarioEntity> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<LancamentosAvulsoEntity> getLancamentos() {
		return lancamentos;
	}
	
	public void setLancamentos(List<LancamentosAvulsoEntity> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
	    
		if(getTipoArquivo().length() == 0 || getTipoArquivo() == null || getTipoArquivo().equals("")){
			errors.add("common.tipo.file.err",
			new ActionMessage("error.common.tipo.file.required"));
			return errors;
		}
		
	    if( getFile().getFileSize()== 0){
	        errors.add("common.file.err",
	        new ActionMessage("error.common.file.required"));
	        return errors;
	    }
	    
	    //only allow textfile to upload
	    if(!"text/plain".equals(getFile().getContentType())){
	    	errors.add("common.file.err.ext",
	    	new ActionMessage("error.common.file.textfile.only"));
	        return errors;
	    }
	          
	      
	    return errors;
	}
	
}

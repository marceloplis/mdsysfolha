package br.com.mdsysfolha.action.common;

import org.apache.struts.action.ActionForm;

public class FormBase extends ActionForm{

	private static final long serialVersionUID = 1681360235153236618L;
	
	private String nmBusca;
	private Long idParam;
	
	public String getNmBusca() {
		return nmBusca;
	}
	public void setNmBusca(String nmBusca) {
		this.nmBusca = nmBusca;
	}
	
	public Long getIdParam() {
		return idParam;
	}
	public void setIdParam(Long idParam) {
		this.idParam = idParam;
	}
	
}

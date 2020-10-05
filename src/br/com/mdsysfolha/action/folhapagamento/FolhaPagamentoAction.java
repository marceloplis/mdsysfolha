package br.com.mdsysfolha.action.folhapagamento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.mdsysfolha.action.common.ActionBase;
import br.com.mdsysfolha.controller.FolhaPagamentoController;
import br.com.mdsysfolha.controller.FuncionarioController;
import br.com.mdsysfolha.controller.LancamentoAvulsoController;
import br.com.mdsysfolha.controller.LancamentoExtraController;
import br.com.mdsysfolha.entity.CargoBeneficioEntity;
import br.com.mdsysfolha.entity.CargoDescontoEntity;
import br.com.mdsysfolha.entity.FolhaPagamentoEntity;
import br.com.mdsysfolha.entity.FolhaPagamentoFuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioLctosExtraEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaAvulsoEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaBeneficioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaDescontoEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaExtraEntity;
import br.com.mdsysfolha.enums.StatusFolhaEnum;
import br.com.mdsysfolha.enums.TipoLancamentoEnum;
import br.com.mdsysfolha.enums.TipoValorEnum;
import br.com.mdsysfolha.util.Utils;
import br.com.mdsysfolha.vo.Holerite;

public class FolhaPagamentoAction extends ActionBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ActionForward todos(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController();
		List<FolhaPagamentoEntity> folhas = folhaController.listTodos();
		folhaForm.setListFolhas(folhas);
		folhaForm.setFolha(new FolhaPagamentoEntity());

		FuncionarioController funcController = new FuncionarioController();
		List<FuncionarioEntity> funcsSemCargo = funcController.filtro(0, null, null, null, -1);
		folhaForm.setFuncsSemCargo(funcsSemCargo.size());
		
		folhaForm.setFiltDtinicio(null);
		folhaForm.setFiltDtFim(null);
		folhaForm.setFiltStatus(null);

		saveToken(request);

		return mapping.findForward("listar");
	}
	
	public ActionForward filtro(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController();
		
		Calendar dtInicioNull = Calendar.getInstance(); 
		dtInicioNull.add(Calendar.MONTH, -6);			
		Calendar dtFimNull = Calendar.getInstance(); 	
		
		Date dtInicio = folhaForm.getFiltDtinicio() != null && folhaForm.getFiltDtinicio().length() > 0 ? Utils.converteData(folhaForm.getFiltDtinicio()) : dtInicioNull.getTime();
		Date dtFim = folhaForm.getFiltDtFim() != null && folhaForm.getFiltDtFim().length() > 0 ? Utils.converteData(folhaForm.getFiltDtFim()) : dtFimNull.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		folhaForm.setFiltDtinicio(sdf.format(dtInicio));
		folhaForm.setFiltDtFim(sdf.format(dtFim));
				
		List<FolhaPagamentoEntity> folhas = folhaController.filtro(dtInicio, dtFim, folhaForm.getFiltStatus());
		folhaForm.setListFolhas(folhas);
		folhaForm.setFolha(new FolhaPagamentoEntity());

		FuncionarioController funcController = new FuncionarioController();
		List<FuncionarioEntity> funcsSemCargo = funcController.filtro(0, null, null, null, -1);
		folhaForm.setFuncsSemCargo(funcsSemCargo.size());

		saveToken(request);

		return mapping.findForward("listar");
	}

	public ActionForward gravar(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController();  

		if(isTokenValid(request)) { 
			resetToken(request); 	
			String mes = folhaForm.getMes_ano().substring(0, folhaForm.getMes_ano().indexOf("-"));
			String ano = folhaForm.getMes_ano().substring(folhaForm.getMes_ano().indexOf("-")+1, folhaForm.getMes_ano().length());
			folhaForm.getFolha().setMes(Integer.parseInt(mes));
			folhaForm.getFolha().setAno(Integer.parseInt(ano));
			FolhaPagamentoEntity folha = folhaController.gravar(folhaForm.getFolha());
			folhaForm.setFolha(folha); 
			folhaForm.setIdParam(new Long(folha.getId()));
		} 	

		ActionMessages messages = new ActionMessages();
		messages.add("message", new ActionMessage("msg.folha.sucesso"));
		saveMessages(request, messages);        

		saveToken(request);

		folhaForm.setListFolhas(folhaController.listTodos());
		folhaForm.setFolha(new FolhaPagamentoEntity());

		return mapping.findForward("listar"); 
	}

	public ActionForward excluir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController(); 

		if(folhaForm.getIdParam() != null) { 	
			try{
				folhaController.excluir(folhaForm.getIdParam());

				ActionMessages messages = new ActionMessages();
				messages.add("message", new ActionMessage("msg.folha.removido"));
				saveMessages(request, messages); 
			}catch (Exception e){
				ActionMessages messages = new ActionMessages();
				messages.add("message", new ActionMessage("error.exclusao.folha"));
				saveMessages(request, messages);
			}

		}

		saveToken(request);

		folhaForm.setListFolhas(folhaController.listTodos());

		return mapping.findForward("listar"); 

	}

	public ActionForward detalhe (ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController(); 

		folhaForm.setFolha(new FolhaPagamentoEntity());
		if(folhaForm.getIdParam() != null){
			folhaForm.setFolha(folhaController.buscarById(folhaForm.getIdParam()));

		}else{
			folhaForm.reset(mapping, request);
		}

		return mapping.findForward("formulario"); 
	}

	public ActionForward apagarHolerites (ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController(); 

		folhaForm.setFolha(new FolhaPagamentoEntity());
		if(folhaForm.getIdParam() != null){
			folhaForm.setFolha(folhaController.buscarById(folhaForm.getIdParam()));
			folhaController.excluirHolerites(folhaForm.getIdParam());
			folhaForm.getFolha().setStatus(StatusFolhaEnum.ABERTA.getValue());
			folhaController.gravar(folhaForm.getFolha());
		}

		return mapping.findForward("formulario"); 
	}


	public ActionForward calcular (ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController(); 

		folhaForm.setFolha(new FolhaPagamentoEntity());
		if(folhaForm.getIdParam() != null){
			folhaForm.setFolha(folhaController.buscarById(folhaForm.getIdParam()));
			calcularFolha(folhaForm.getFolha());
			folhaForm.getFolha().setStatus(StatusFolhaEnum.GERADA.getValue());
			folhaController.gravar(folhaForm.getFolha());
		}else{
			folhaForm.reset(mapping, request);
		}

		return mapping.findForward("holerites"); 
	}

	private void calcularFolha(FolhaPagamentoEntity folha) throws Exception{

		FuncionarioController funcController = new FuncionarioController();
		List<FuncionarioEntity> funcs = funcController.listTodosAtivos();

		List<FolhaPagamentoFuncionarioEntity> listFolhaFunc = new ArrayList<FolhaPagamentoFuncionarioEntity>();
		Map<String, List<LancamentosFolhaDescontoEntity>> mapDesc = new HashMap<String, List<LancamentosFolhaDescontoEntity>>();
		Map<String, List<LancamentosFolhaBeneficioEntity>> mapBenef = new HashMap<String, List<LancamentosFolhaBeneficioEntity>>();
		Map<String, List<LancamentosFolhaAvulsoEntity>> mapAvulso = new HashMap<String, List<LancamentosFolhaAvulsoEntity>>();
		Map<String, List<LancamentosFolhaExtraEntity>> mapExtra = new HashMap<String, List<LancamentosFolhaExtraEntity>>();

		FolhaPagamentoController folhaController = new FolhaPagamentoController();

		for(FuncionarioEntity func : funcs){

			FolhaPagamentoFuncionarioEntity folhaFunc = new FolhaPagamentoFuncionarioEntity();			

			List<LancamentosFolhaDescontoEntity> listDesc = new ArrayList<LancamentosFolhaDescontoEntity>();
			List<LancamentosFolhaBeneficioEntity> listBenef = new ArrayList<LancamentosFolhaBeneficioEntity>();
			List<LancamentosFolhaAvulsoEntity> listAvulso = new ArrayList<LancamentosFolhaAvulsoEntity>();
			List<LancamentosFolhaExtraEntity> listExtra = new ArrayList<LancamentosFolhaExtraEntity>();

			if(folha.getTipo().equals("M")){	

				//Benef�cios tem que ser o primeiro c�lculo para setar inicial de sal�rio l�quido
				listBenef.addAll(calcBeneficios(func, folha, folhaFunc));	
				mapBenef.put(func.getCpf(), listBenef);

				listDesc.addAll(calcDescontos(func, folha, folhaFunc));	
				mapDesc.put(func.getCpf(), listDesc);

				listAvulso.addAll(calcAvulso(func, folha));
				mapAvulso.put(func.getCpf(), listAvulso);

				listExtra.addAll(calcExtras(func, folha, folhaFunc));
				mapExtra.put(func.getCpf(), listExtra);
			}else{
				listAvulso.addAll(calcAvulso(func, folha));
				mapAvulso.put(func.getCpf(), listAvulso);
			}

			if(listBenef.size() > 0 || listDesc.size() > 0 || listAvulso.size() > 0 || listExtra.size() > 0 ) {

				listFolhaFunc.add(setFolhaFuncionario(func, folha, folhaFunc));
			}


		}	

		if(folha.getTipo().equals("M")){

			for(FolhaPagamentoFuncionarioEntity f : listFolhaFunc){
				folhaController.gravaFolhaFuncionario(f);
			}

			for (Entry<String, List<LancamentosFolhaDescontoEntity>> entry : mapDesc.entrySet()) {
				for(LancamentosFolhaDescontoEntity d : entry.getValue()){
					folhaController.gravaDescontos(d);
				}
			}


			for (Entry<String, List<LancamentosFolhaBeneficioEntity>> entry : mapBenef.entrySet()) {
				for(LancamentosFolhaBeneficioEntity b : entry.getValue()){
					folhaController.gravaBeneficios(b);
				}
			}

			for (Entry<String, List<LancamentosFolhaExtraEntity>> entry : mapExtra.entrySet()) {
				for(LancamentosFolhaExtraEntity e : entry.getValue()){
					folhaController.gravaExtras(e);
				}	
			}

			for (Entry<String, List<LancamentosFolhaAvulsoEntity>> entry : mapAvulso.entrySet()) {
				for(LancamentosFolhaAvulsoEntity a : entry.getValue()){
					folhaController.gravaAvulsos(a);
				}
			}

		} else {

			for(FolhaPagamentoFuncionarioEntity f : listFolhaFunc){
				folhaController.gravaFolhaFuncionario(f);
			}

			for (Entry<String, List<LancamentosFolhaAvulsoEntity>> entry : mapAvulso.entrySet()) {
				for(LancamentosFolhaAvulsoEntity a : entry.getValue()){
					folhaController.gravaAvulsos(a);
				}
			}

		}

	}

	private FolhaPagamentoFuncionarioEntity setFolhaFuncionario(FuncionarioEntity func, FolhaPagamentoEntity folha, FolhaPagamentoFuncionarioEntity folhaFunc){		

		folhaFunc.setFuncionario(func);
		folhaFunc.setFolhaPagamento(folha);
		folhaFunc.setCargo(func.getCargo().getDescricao());
		folhaFunc.setLoja(func.getLoja());
		if(folha.getTipo().equals("M")){
			folhaFunc.setSalario(func.getSalario());
		}else{
			folhaFunc.setSalario(new Double("0"));
		}


		return folhaFunc;

	}

	public class OrdemCargoDesconto implements Comparator<CargoDescontoEntity> {
		public int compare(CargoDescontoEntity cargo1, CargoDescontoEntity cargo2) {
			return cargo1.getDesconto().getOrdem().
					compareTo(cargo2.getDesconto().getOrdem());
		}
	}

	private List<LancamentosFolhaDescontoEntity> calcDescontos(FuncionarioEntity func, FolhaPagamentoEntity folha, FolhaPagamentoFuncionarioEntity folhaFunc){		
		List<LancamentosFolhaDescontoEntity> listRet = new ArrayList<LancamentosFolhaDescontoEntity>();

		Double salario_liq = folhaFunc.getSalarioLiq() == null ? folhaFunc.getSalario() : folhaFunc.getSalarioLiq();

		List<CargoDescontoEntity> lista = new ArrayList<CargoDescontoEntity>(func.getCargo().getCargoDescontos());

		OrdemCargoDesconto comparator = new OrdemCargoDesconto();
		Collections.sort(lista, comparator);

		for(CargoDescontoEntity cargDesc : lista){			 
			LancamentosFolhaDescontoEntity descFolha = new LancamentosFolhaDescontoEntity();
			descFolha.setDesconto(cargDesc.getDesconto());
			descFolha.setFolhaPagamento(folha);
			descFolha.setFuncionario(func);
			Double valor = 0.0;
			if(cargDesc.getDesconto().getTipo_valor().equals(TipoValorEnum.MOEDA.getValue())){
				valor = cargDesc.getValor();
			}else{
				valor = ((cargDesc.getDesconto().getBase_calculo().equals("B") ? folhaFunc.getSalario() : salario_liq) * cargDesc.getValor())/100;
			}
			descFolha.setValor(valor);
			listRet.add(descFolha);
		}
		return listRet;
	}

	public class OrdemCargoBeneficio implements Comparator<CargoBeneficioEntity> {
		public int compare(CargoBeneficioEntity cargo1, CargoBeneficioEntity cargo2) {
			return cargo1.getBeneficio().getOrdem().
					compareTo(cargo2.getBeneficio().getOrdem());
		}
	}	

	private List<LancamentosFolhaBeneficioEntity> calcBeneficios(FuncionarioEntity func, FolhaPagamentoEntity folha, FolhaPagamentoFuncionarioEntity folhaFunc){
		List<LancamentosFolhaBeneficioEntity> listRet = new ArrayList<LancamentosFolhaBeneficioEntity>();

		Double salario_liq = folhaFunc != null && folhaFunc.getSalario() != null ? folhaFunc.getSalario() : func.getSalario();

		List<CargoBeneficioEntity> lista = new ArrayList<CargoBeneficioEntity>(func.getCargo().getCargoBeneficios());

		OrdemCargoBeneficio comparator = new OrdemCargoBeneficio();
		Collections.sort(lista, comparator);

		for(CargoBeneficioEntity cargBenef : lista){			 
			LancamentosFolhaBeneficioEntity benefFolha = new LancamentosFolhaBeneficioEntity();
			benefFolha.setBeneficio(cargBenef.getBeneficio());
			benefFolha.setFolhaPagamento(folha);
			benefFolha.setFuncionario(func);

			Double valor_benef = 0.0;

			if(cargBenef.getBeneficio().getTipo_valor().equals(TipoValorEnum.MOEDA.getValue())){
				valor_benef = cargBenef.getValor();
			}else{				
				valor_benef = ((cargBenef.getBeneficio().getBase_calculo().equals("B") ? (folhaFunc != null && folhaFunc.getSalario() != null ? folhaFunc.getSalario() : func.getSalario()) : salario_liq) * cargBenef.getValor())/100;
			}

			if(cargBenef.getBeneficio().getAltera_base_calculo().equals("S")){
				salario_liq = salario_liq + valor_benef;
				folhaFunc.setSalarioLiq(salario_liq);
			}

			benefFolha.setValor(valor_benef);
			listRet.add(benefFolha);
		}
		return listRet;

	}

	private List<LancamentosFolhaAvulsoEntity> calcAvulso(FuncionarioEntity func, FolhaPagamentoEntity folha){
		LancamentoAvulsoController controller = new LancamentoAvulsoController();
		List<LancamentosAvulsoEntity> avs = controller.listaByFuncData(func.getCpf(), folha.getAno(), folha.getMes(), folha.getTipo());

		List<LancamentosFolhaAvulsoEntity> listRet = new ArrayList<LancamentosFolhaAvulsoEntity>();
		for(LancamentosAvulsoEntity a : avs){
			LancamentosFolhaAvulsoEntity avulsoFolha = new LancamentosFolhaAvulsoEntity();
			avulsoFolha.setAvulso(a);
			avulsoFolha.setFolhaPagamento(folha);
			listRet.add(avulsoFolha);
		}

		return listRet;

	}

	private List<LancamentosFolhaExtraEntity> calcExtras(FuncionarioEntity func, FolhaPagamentoEntity folha, FolhaPagamentoFuncionarioEntity folhaFunc){		
		LancamentoExtraController controller = new LancamentoExtraController();
		List<FuncionarioLctosExtraEntity> lista =  controller.listaByFunc(func.getCpf());

		List<LancamentosFolhaExtraEntity> listRet = new ArrayList<LancamentosFolhaExtraEntity>();
		for(FuncionarioLctosExtraEntity e : lista){			 
			LancamentosFolhaExtraEntity extraFolha = new LancamentosFolhaExtraEntity();
			LancamentosExtraEntity extra = controller.buscarById(e.getPk().getExtra().getId());
			extraFolha.setExtra(extra);
			extraFolha.setFolhaPagamento(folha);
			extraFolha.setFuncionario(func);

			Double valor_extra = 0.0;

			if(extra.getTipo_valor().equals(TipoValorEnum.MOEDA.getValue())){
				valor_extra = e.getValor();
			}else{				
				valor_extra = (folhaFunc.getSalario() * e.getValor())/100;
			}

			extraFolha.setValor(valor_extra);
			listRet.add(extraFolha);
		}

		return listRet;

	}


	public ActionForward exibeHolerite (ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		FolhaPagamentoController folhaController = new FolhaPagamentoController();

		List<FolhaPagamentoFuncionarioEntity> folhasFuncs = folhaController.listarFolhaFuncByFolha(null, null, folhaForm.getIdParam(), folhaForm.getFiltLoja(), folhaForm.getFiltFunc());
		List<Holerite> listHolerites = new ArrayList<Holerite>();

		for(FolhaPagamentoFuncionarioEntity ff : folhasFuncs){

			Holerite holerite = new Holerite();		
			holerite.setFuncionario(ff.getFuncionario());

			holerite.setFolhaFuncionario(ff);

			List<LancamentosFolhaDescontoEntity> descontos = folhaController.buscarFolhaDescByFunc(ff.getFuncionario(), folhaForm.getIdParam());	
			holerite.setDescontos(descontos);
			Double totalDescontos = 0.0;
			for(LancamentosFolhaDescontoEntity desc : descontos){
				totalDescontos = totalDescontos + desc.getValor();
			}
			holerite.setTotalDescontos(totalDescontos);

			List<LancamentosFolhaBeneficioEntity> beneficios = folhaController.buscarFolhaBenefByFunc(ff.getFuncionario(), folhaForm.getIdParam());	
			holerite.setBeneficios(beneficios);
			Double totalBeneficios = 0.0;
			for(LancamentosFolhaBeneficioEntity benef : beneficios){
				totalBeneficios = totalBeneficios + benef.getValor();
			}
			holerite.setTotalBeneficios(totalBeneficios);

			List<LancamentosFolhaAvulsoEntity> avulsoDescontos = folhaController.buscarFolhaAvulsoDescByFunc(ff.getFuncionario(), folhaForm.getIdParam());	
			holerite.setAvulsoDescontos(avulsoDescontos);
			Double totalAvulsoDescontos = 0.0;
			for(LancamentosFolhaAvulsoEntity avdesc : avulsoDescontos){
				totalAvulsoDescontos = totalAvulsoDescontos + avdesc.getAvulso().getValor();
			}
			holerite.setTotalAvulsoDescontos(totalAvulsoDescontos);

			List<LancamentosFolhaAvulsoEntity> avulsoBeneficios = folhaController.buscarFolhaAvulsoBenefByFunc(ff.getFuncionario(), folhaForm.getIdParam());
			holerite.setAvulsoBeneficios(avulsoBeneficios);
			Double totalAvulsoBeneficios = 0.0;
			for(LancamentosFolhaAvulsoEntity avbenef : avulsoBeneficios){
				totalAvulsoBeneficios = totalAvulsoBeneficios + avbenef.getAvulso().getValor();
			}
			holerite.setTotalAvulsoBeneficios(totalAvulsoBeneficios);

			List<LancamentosFolhaExtraEntity> extras = folhaController.buscarFolhaExtraByFunc(ff.getFuncionario(), folhaForm.getIdParam());	
			holerite.setExtras(extras);
			Double totalExtrasBeneficios = 0.0;
			Double totalExtrasDescontos = 0.0;
			for(LancamentosFolhaExtraEntity e : extras){
				if(e.getExtra().getTipo_lancamento().equals(TipoLancamentoEnum.CREDITO.getValue()))
					totalExtrasBeneficios = totalExtrasBeneficios + e.getValor();
				else
					totalExtrasDescontos = totalExtrasDescontos + e.getValor();
			}
			holerite.setTotalExtrasBeneficios(totalExtrasBeneficios);
			holerite.setTotalExtrasDescontos(totalExtrasDescontos);

			Double total = 0.0;
			Double totalRound = 0.0;
			total = ff.getSalario() + totalBeneficios + totalAvulsoBeneficios + totalExtrasBeneficios;
			total = total - totalDescontos - totalAvulsoDescontos - totalExtrasDescontos;

			totalRound = Math.ceil(total);
			while((totalRound % 10) != 0){
				totalRound++;
			}
			holerite.setArredondamento(totalRound - total);

			holerite.setTotal(total + holerite.getArredondamento());


			listHolerites.add(holerite);

		}

		folhaForm.setHolerites(listHolerites);

		return mapping.findForward("holerites");

	}

	public ActionForward imprimir(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FolhaPagamentoForm folhaForm = (FolhaPagamentoForm) form;

		this.exibeHolerite(mapping, folhaForm, request, response);

		folhaForm.setFiltFunc(null);

		return mapping.findForward("imprimir");
	}


}

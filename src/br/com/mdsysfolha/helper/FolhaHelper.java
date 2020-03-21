package br.com.mdsysfolha.helper;

import java.util.ArrayList;
import java.util.List;

import br.com.mdsysfolha.entity.BeneficioEntity;
import br.com.mdsysfolha.entity.DescontoEntity;

public class FolhaHelper {

	public static List<Integer> listaOrdemBeneficio(List<BeneficioEntity> listBenf, Integer ordemSelect){
		
		List<Integer> listaUsada = new ArrayList<Integer>();
		
		List<Integer> listaRetorno = new ArrayList<Integer>();
		
		for(BeneficioEntity b : listBenf){
			if(b.getOrdem() != null && b.getOrdem() > 0)
				listaUsada.add(new Integer(b.getOrdem()));
			if(ordemSelect != null)
				listaUsada.remove(new Integer(ordemSelect));
		}
		
		
		for(int i=1;i<=100;i++){
			if(!listaUsada.contains(new Integer(i))){
				listaRetorno.add(i);
			}
		}
		
		return listaRetorno;
		
	}
	
	
	public static List<Integer> listaOrdemDesconto(List<DescontoEntity> listDesc, Integer ordemSelect){
		
		List<Integer> listaUsada = new ArrayList<Integer>();
		
		List<Integer> listaRetorno = new ArrayList<Integer>();
		
		for(DescontoEntity d : listDesc){
			if(d.getOrdem() != null && d.getOrdem() > 0)
				listaUsada.add(new Integer(d.getOrdem()));
			if(ordemSelect != null)
				listaUsada.remove(new Integer(ordemSelect));
		}
		
		
		for(int i=1;i<=100;i++){
			if(!listaUsada.contains(new Integer(i))){
				listaRetorno.add(i);
			}
		}
		
		return listaRetorno;
		
	}
	
}

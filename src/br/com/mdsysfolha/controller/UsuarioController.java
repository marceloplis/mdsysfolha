package br.com.mdsysfolha.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import br.com.mdsysfolha.action.login.LoginForm;
import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.UsuarioEntity;
import br.com.mdsysfolha.util.Utils;


public class UsuarioController {		
	
	private final FactoryDao factoryDao;
	
	public UsuarioController(){
		this.factoryDao = new FactoryDao();
	}
	
	public UsuarioEntity login(LoginForm form) throws SQLException, Exception{
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setUsername(form.getUsername().toLowerCase());
		usuario.setSenha(Utils.criptografaSenha(form.getSenha().toLowerCase()));
		usuario = this.factoryDao.getUsuarioDao().login(usuario.getUsername(), usuario.getSenha());
		return usuario;
		
	}
	
	public UsuarioEntity buscaByUsername(String username) throws SQLException, Exception{
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario = this.factoryDao.getUsuarioDao().buscaByUsername(username);
		return usuario;
		
	}
	
	public UsuarioEntity gravar(UsuarioEntity usuario, String senhaAtual) throws NoSuchAlgorithmException, Exception{
		
		if(usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0){
			usuario.setIdUsuario(null);
			String username = usuario.getUsername().toLowerCase();
			String senhaCriptografada = Utils.criptografaSenha(usuario.getSenha().toLowerCase());			
			usuario.setUsername(username);
			usuario.setSenha(senhaCriptografada);
			usuario.setFlAtivo(true);
			this.factoryDao.getUsuarioDao().adiciona(usuario);
		}else{
			if(senhaAtual != null && !usuario.getSenha().equals(senhaAtual)){
				usuario.setSenha(Utils.criptografaSenha(usuario.getSenha().toLowerCase()));
			}
			this.factoryDao.getUsuarioDao().atualiza(usuario);
		}	
		
		return usuario;
	}
	
	public UsuarioEntity buscarById(Long id){
		UsuarioEntity usuario = this.factoryDao.getUsuarioDao().procura(id);
		return usuario;
	}
	
	public List<UsuarioEntity> listTodos(){		
		return this.factoryDao.getUsuarioDao().listaTudo();
	}
	
	public List<UsuarioEntity> buscar(String termo){		
		List<UsuarioEntity> listUsuarios = this.factoryDao.getUsuarioDao().busca(termo);
		return listUsuarios;
	}
	
	public void apagar(Long id){
		UsuarioEntity usuario = this.buscarById(id);
		if(usuario != null){
			try{
				this.factoryDao.beginTransaction();
				this.factoryDao.getUsuarioDao().remove(usuario);
				this.factoryDao.commit();
			}catch (Exception e){			  
				this.factoryDao.rollback();  
			    e.printStackTrace();  
			}
		}
	}

	
}

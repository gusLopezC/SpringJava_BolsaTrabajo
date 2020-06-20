package com.gustavo.service;

import java.util.List;

import com.gustavo.model.Usuario;



public interface IUsuariosService {

	List<Usuario> buscarTodas();

	Usuario buscarPorID(Integer idUsuario);

	void guardar(Usuario usuario);
	
	void eliminarUser(Integer idUsuario);
}

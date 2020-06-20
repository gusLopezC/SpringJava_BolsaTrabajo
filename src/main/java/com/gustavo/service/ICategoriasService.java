package com.gustavo.service;

import java.util.List;

import com.gustavo.model.Categorias;


public interface ICategoriasService {

	List<Categorias> buscarTodas();

	Categorias buscarPorID(Integer idCategoria);

	void guardar(Categorias categoria);
	
	void eliminarCategoria(Integer idCategoria);
	
}

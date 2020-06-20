package com.gustavo.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gustavo.model.Vacante;

public interface IVacantesServices {
	
	List<Vacante> buscarTodas();
	
	Vacante buscarPorID(Integer idVacante);
	
	void guardar(Vacante vacante);

	List<Vacante> buscarDestacados();
	
	void eliminarVacante(Integer IdVacante);
	
	List<Vacante> buscarByExample(Example<Vacante> example);
	
	Page<Vacante> buscarTods(Pageable page);
	
}

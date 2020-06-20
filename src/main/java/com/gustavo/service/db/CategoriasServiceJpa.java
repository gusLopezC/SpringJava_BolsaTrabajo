package com.gustavo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.gustavo.model.Categorias;
import com.gustavo.repository.CategoriasRepository;
import com.gustavo.service.ICategoriasService;

@Service
@Primary
public class CategoriasServiceJpa implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository categoriasRepositorio;

	@Override
	public List<Categorias> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriasRepositorio.findAll();
	}

	@Override
	public Categorias buscarPorID(Integer idCategoria) {
		// TODO Auto-generated method stub
		Optional<Categorias> optional = categoriasRepositorio.findById(idCategoria);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Categorias categoria) {
		// TODO Auto-generated method stub
		categoriasRepositorio.save(categoria);
	}

	@Override
	public void eliminarCategoria(Integer idCategoria) {
		// TODO Auto-generated method stub
		categoriasRepositorio.deleteById(idCategoria);
	}

}

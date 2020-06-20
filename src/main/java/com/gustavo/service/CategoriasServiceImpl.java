package com.gustavo.service;

import java.text.ParseException;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gustavo.model.Categorias;


@Service
public class CategoriasServiceImpl implements ICategoriasService{
	
	private List<Categorias> lista  = null;
	
	public CategoriasServiceImpl() {
		// TODO Auto-generated constructor stub
		lista = new LinkedList<Categorias>();
		// Categoria 1
		Categorias cat1 = new Categorias();
		cat1.setId(1);
		cat1.setNombre("Contabilidad");
		cat1.setDescripcion("Descripcion de la categoria Contabilidad");
		
		// Categoria 2
		Categorias cat2 = new Categorias();
		cat2.setId(2);
		cat2.setNombre("Ventas");
		cat2.setDescripcion("Trabajos relacionados con Ventas");
		
					
		// Categoria 3
		Categorias cat3 = new Categorias();
		cat3.setId(3);
		cat3.setNombre("Comunicaciones");
		cat3.setDescripcion("Trabajos relacionados con Comunicaciones");
		
		// Categoria 4
		Categorias cat4 = new Categorias();
		cat4.setId(4);
		cat4.setNombre("Arquitectura");
		cat4.setDescripcion("Trabajos de Arquitectura");
		
		// Categoria 5
		Categorias cat5 = new Categorias();
		cat5.setId(5);
		cat5.setNombre("Educacion");
		cat5.setDescripcion("Maestros, tutores, etc");
		
		/**
		 * Agregamos los 5 objetos de tipo Categoria a la lista ...
		 */
		lista.add(cat1);			
		lista.add(cat2);
		lista.add(cat3);
		lista.add(cat4);
		lista.add(cat5);
	}

	@Override
	public List<Categorias> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Categorias buscarPorID(Integer idCategoria) {
		// TODO Auto-generated method stub
		for (Categorias v: lista) {
			if(v.getId()==idCategoria) {
				return v;
			}
		}
		return null;
	}

	@Override
	public void guardar(Categorias categoria) {
		
		// TODO Auto-generated method stub
		lista.add(categoria);
	}

	@Override
	public void eliminarCategoria(Integer idCategoria) {
		
		
	}

}

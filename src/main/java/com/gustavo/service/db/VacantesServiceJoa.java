package com.gustavo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gustavo.model.Vacante;
import com.gustavo.repository.VacantesRepository;
import com.gustavo.service.IVacantesServices;

@Service
@Primary
public class VacantesServiceJoa implements IVacantesServices {

	@Autowired
	private VacantesRepository repoVacantes;

	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return repoVacantes.findAll();
	}

	@Override
	public Vacante buscarPorID(Integer idVacante) {
		// TODO Auto-generated method stub
		
		Optional<Vacante> optional=	 repoVacantes.findById(idVacante);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		
		repoVacantes.save(vacante);

	}

	@Override
	public List<Vacante> buscarDestacados() {
		// TODO Auto-generated method stub
		return repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada"	);
	}

	@Override
	public void eliminarVacante(Integer IdVacante) {
		// TODO Auto-generated method stub
		
		repoVacantes.deleteById(IdVacante);
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		
		
		return repoVacantes.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTods(Pageable page) {
		// TODO Auto-generated method stub
		return repoVacantes.findAll(page);
	}

} 
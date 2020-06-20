package com.gustavo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.gustavo.model.Usuario;
import com.gustavo.repository.UsuariosRepository;
import com.gustavo.service.IUsuariosService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuarioRepo;

	@Override
	public List<Usuario> buscarTodas() {
		// TODO Auto-generated method stub
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario buscarPorID(Integer idUsuario) {
		// TODO Auto-generated method stub

		Optional<Usuario> optional = usuarioRepo.findById(idUsuario);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepo.save(usuario);
	}

	@Override
	public void eliminarUser(Integer idUsuario) {

		usuarioRepo.deleteById(idUsuario);
	}

}

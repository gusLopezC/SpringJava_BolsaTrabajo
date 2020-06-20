package com.gustavo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gustavo.repository.UsuariosRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		model.addAttribute("usuarios", repoUsuarios.findAll());
    
		return "usuarios/listUsuarios";
	}	
    
    @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		    	
		
		return "redirect:/usuarios/index";
	}
	
}

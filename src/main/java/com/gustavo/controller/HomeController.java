package com.gustavo.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gustavo.model.Perfil;
import com.gustavo.model.Usuario;
import com.gustavo.model.Vacante;
import com.gustavo.service.ICategoriasService;
import com.gustavo.service.IUsuariosService;
import com.gustavo.service.IVacantesServices;

@Controller
public class HomeController {

	@Autowired
	private ICategoriasService serviceCategorias;
	@Autowired
	private IVacantesServices serviceVacantes;

	@Autowired
	private IUsuariosService serviceUsuarios;

	@GetMapping("/")
	public String mostrarHome(Model model) {

		return "home";
	}

	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}

	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor

		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);

		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 * 
		 */
		serviceUsuarios.guardar(usuario);
		attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");

		return "redirect:/usuarios/index";

	}

	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {

		List<Vacante> lista = serviceVacantes.buscarTodas();

		model.addAttribute("vacantes", lista);

		return "tabla";
	}

	@GetMapping("/detalle")

	public String mostrarDetalle(Model model) {

		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero Telecomunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para soporte y redes");
		vacante.setFecha(new Date());
		vacante.setSalario(10000.0);
		model.addAttribute("vacante", vacante);

		return "detalle";

	}

	@GetMapping("/listado")

	public String mostrarListado(Model model) {

		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero en Sistemas");
		lista.add("Auxiliar contable");
		lista.add("Vendedor");
		lista.add("Arquitecto");

		model.addAttribute("empleos", lista);

		return "listado";

	}

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {

		System.out.println("Buscando por" + vacante);

		// where descripcion like '%?%'
		ExampleMatcher martcher = ExampleMatcher.matching().withMatcher("descripcion",
				ExampleMatcher.GenericPropertyMatchers.contains());

		Example<Vacante> example = Example.of(vacante, martcher);
		List<Vacante> vacantes = serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes", vacantes);
		return "home";

	}

	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a
	 * NULL
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteSeach = new Vacante();
		vacanteSeach.reset();
		model.addAttribute("vacantes", serviceVacantes.buscarDestacados());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		model.addAttribute("search", vacanteSeach);
	}

}// end class

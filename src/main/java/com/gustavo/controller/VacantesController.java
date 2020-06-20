package com.gustavo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gustavo.model.Vacante;
import com.gustavo.service.ICategoriasService;
import com.gustavo.service.IVacantesServices;
import com.gustavo.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacantesServices serviceVacantes;

	@Autowired
	@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategorias;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {

		List<Vacante> lista = serviceVacantes.buscarTodas();

		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/indexPaginate")
	public String mostrarIndexPagination(Model model, Pageable page) {
		
		Page<Vacante> lista = serviceVacantes.buscarTods(page);
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}

	@GetMapping("/create")
	public String formVacante(Vacante vacante, Model model) {

		return "vacantes/formVacantes";
	}

	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "vacantes/formVacantes";
		}

		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			// String ruta = "/home/gus/Proyectos/workspace-spring/empleos/src/main/resources/static/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
		serviceVacantes.guardar(vacante);
		attributes.addFlashAttribute("msg", "Atrivuto guardado");
		return "redirect:/vacantes/index";
	}
	/*
	 * @PostMapping("/save") public String guardar(@RequestParam("nombre") String
	 * nombre, @RequestParam("descripcion") String descripcion,
	 * 
	 * @RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha,
	 * 
	 * @RequestParam("destacado") int destacado, @RequestParam("salario") double
	 * salario,
	 * 
	 * @RequestParam("detalles") String detalles) {
	 * System.out.println("Nombre Vacante: " + nombre);
	 * System.out.println("Descripcion: " + descripcion);
	 * System.out.println("Estatus: " + estatus);
	 * System.out.println("Fecha Publicaci贸n: " + fecha);
	 * System.out.println("Destacado: " + destacado);
	 * System.out.println("Salario Ofrecido: " + salario);
	 * System.out.println("detalles: " + detalles); return "vacantes/listVacantes";
	 * }
	 */

	@GetMapping("/view/{id}")
	public String verDetalles(@PathVariable("id") int idVacante, Model model) {

		Vacante vacante = serviceVacantes.buscarPorID(idVacante);

		System.out.println("IdVacante" + vacante);
		model.addAttribute("vacante", vacante);

		return "detalle";
	}
	
	@GetMapping("/edit/{id}")
	public String  edit(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = serviceVacantes.buscarPorID(idVacante);
		model.addAttribute("vacante", vacante);
		
		return "vacantes/formVacantes";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
		serviceVacantes.eliminarVacante(idVacante);
		attributes.addFlashAttribute("msg", "La vacante fue eliminada");
		return "redirect:/vacantes/index";
	}

	@ModelAttribute
	public void setCategorias(Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}

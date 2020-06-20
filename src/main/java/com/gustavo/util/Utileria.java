package com.gustavo.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public class Utileria {

	public static String guardarArchivo(MultipartFile multiPart, String ruta) {
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal.replace(" ", "-");
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro.
			File imageFile = new File(ruta + nombreOriginal);
			System.out.println("Archivo: " + imageFile.getAbsolutePath());
			// Guardamos fisicamente el archivo en HD.
			multiPart.transferTo(imageFile);
			return nombreOriginal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	public static String ramdonText(int count) {
		
		String Caracteres = "QQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
		StringBuilder builer = new StringBuilder();
		
		while (count-- != 0) {
			 int caracter = (int) (Math.random() * Caracteres.length());
			 builer.append(Caracteres.charAt(caracter));
		}
		
		return builer.toString();
	}
}

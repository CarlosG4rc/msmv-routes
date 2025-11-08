package com.charlie.springcloud.msvc_routes.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jar")
public class JarController {
    @PostMapping("/analyze")
    public List<String> AnalyzeJar(@RequestParam("file") MultipartFile file) throws IOException{
    	List<String> filePaths = new ArrayList<>();
    	
    	//Se guarda temporalmente el archivo
    	File tempFile = File.createTempFile("upload-",  ".jar");
    	file.transferTo(tempFile);
    	
    	//Lee el jar
    	try(JarFile jarFile = new JarFile(tempFile)) {
    		jarFile.stream().forEach(entry -> filePaths.add(entry.getName()));
    	}
    	//Borramos archivo
    	tempFile.delete();
    	
    	return filePaths;
    }
	@GetMapping("/saludo")
	public String holi() {
		return "Holi, bienvenido al mundo del java cpn Spring Boot";
	}
}

package es.msines.simple.springboot.app.articulos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.msines.simple.springboot.app.articulos.models.service.IArticuloService;
import es.msines.simple.springboot.app.commons.models.entity.Articulo;

@RestController
public class ArticuloController {
	
	//@Autowired
	//private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IArticuloService service;
	
	@GetMapping("/listar")
	public List<Articulo> listar(){
		return service.findAll().stream().map(articulo ->{
			//articulo.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			articulo.setPort(port);
			return articulo;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Articulo detalle(@PathVariable Long id) {
		Articulo articulo = service.findById(id);
		//articulo.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		articulo.setPort(port);
		
		/*
		 * try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return articulo;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo crear(@RequestBody Articulo articulo) {
		return service.save(articulo);
		
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo editar(@RequestBody Articulo articulo, @PathVariable Long id) {
		Articulo articuloDb = service.findById(id);
		
		articuloDb.setNombre(articulo.getNombre());
        articuloDb.setPrecio(articulo.getPrecio());
        
        return service.save(articuloDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		service.deleteById(id);
	}
	

}

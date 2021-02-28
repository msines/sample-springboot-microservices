package es.msines.simple.springboot.app.pedido.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;

@FeignClient(name = "app-articulos")
public interface ArticuloClienteRest {
	
	@GetMapping("/listar")
	public List<Articulo> listar();
	
	@GetMapping("/ver/{id}")
	public Articulo detalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	public Articulo crear(@RequestBody Articulo articulo);
	
	@PutMapping("/editar/{id}")
	public Articulo update(@RequestBody Articulo articulo, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);

}

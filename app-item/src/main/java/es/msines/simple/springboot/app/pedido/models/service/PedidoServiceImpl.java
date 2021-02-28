package es.msines.simple.springboot.app.pedido.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;
import es.msines.simple.springboot.app.pedido.models.Pedido;

@Service("serviceRestTemplate")
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Pedido> findAll() {
		List<Articulo> articulos = Arrays.asList(clienteRest.getForObject("http://app-articulos/listar", Articulo[].class));
		
		return articulos.stream().map(p -> new Pedido(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Pedido findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Articulo articulo = clienteRest.getForObject("http://app-articulos/ver/{id}", Articulo.class, pathVariables);
		return new Pedido(articulo, cantidad);
	}

	@Override
	public Articulo save(Articulo articulo) {
		HttpEntity<Articulo> body = new HttpEntity<Articulo>(articulo);
		
		ResponseEntity<Articulo> response = clienteRest.exchange("http://app-articulos/crear", HttpMethod.POST, body, Articulo.class);
		Articulo articuloResponse = response.getBody();
		return articuloResponse;
	}

	@Override
	public Articulo update(Articulo articulo, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		HttpEntity<Articulo> body= new HttpEntity<Articulo>(articulo);
		ResponseEntity<Articulo> response = clienteRest.exchange("http://app-articulos/editar/{id}", 
				HttpMethod.PUT, body, Articulo.class, pathVariables);
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clienteRest.delete("http://app-articulos/eliminar/{id}", pathVariables);
		
	}

}

package es.msines.simple.springboot.app.pedido.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;
import es.msines.simple.springboot.app.pedido.models.Pedido;
import es.msines.simple.springboot.app.pedido.models.service.PedidoService;

@RefreshScope
@RestController
public class PedidoController {

	private static Logger log = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	private PedidoService pedidoService;

	@Value("${configuracion.texto}")
	private String texto;

	@GetMapping("/listar")
	public List<Pedido> listar() {
		return pedidoService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Pedido detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return pedidoService.findById(id, cantidad);
	}

	public Pedido metodoAlternativo(Long id, Integer cantidad) {
		Pedido pedido = new Pedido();
		Articulo articulo = new Articulo();

		pedido.setCantidad(cantidad);
		articulo.setId(id);
		articulo.setNombre("Camara Sony");
		articulo.setPrecio(500.00);
		pedido.setArticulo(articulo);
		return pedido;

	}

	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {

		log.info(texto);

		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", puerto);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo crear(@RequestBody Articulo articulo) {
		return pedidoService.save(articulo);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo editar(@RequestBody Articulo articulo, @PathVariable Long id) {
		return pedidoService.update(articulo, id);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		pedidoService.delete(id);
	}
}

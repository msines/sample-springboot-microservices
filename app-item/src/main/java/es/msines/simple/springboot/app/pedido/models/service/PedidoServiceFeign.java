package es.msines.simple.springboot.app.pedido.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;
import es.msines.simple.springboot.app.pedido.clientes.ArticuloClienteRest;
import es.msines.simple.springboot.app.pedido.models.Pedido;

@Service("serviceFeign")
public class PedidoServiceFeign implements PedidoService {

	@Autowired
	private ArticuloClienteRest clienteFeign;

	@Override
	public List<Pedido> findAll() {
		return clienteFeign.listar().stream().map(p -> new Pedido(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Pedido findById(Long id, Integer cantidad) {
		return new Pedido(clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Articulo save(Articulo articulo) {
		return clienteFeign.crear(articulo);
	}

	@Override
	public Articulo update(Articulo articulo, Long id) {
		return clienteFeign.update(articulo, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
	}

}

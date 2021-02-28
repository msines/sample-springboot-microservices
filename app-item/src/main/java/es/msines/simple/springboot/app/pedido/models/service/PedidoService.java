package es.msines.simple.springboot.app.pedido.models.service;

import java.util.List;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;
import es.msines.simple.springboot.app.pedido.models.Pedido;

public interface PedidoService {

	public List<Pedido> findAll();

	public Pedido findById(Long id, Integer cantidad);

	public Articulo save(Articulo articulo);

	public Articulo update(Articulo articulo, Long id);

	public void delete(Long id);
}

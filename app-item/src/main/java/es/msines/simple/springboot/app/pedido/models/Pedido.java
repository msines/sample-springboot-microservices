package es.msines.simple.springboot.app.pedido.models;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;

public class Pedido {

	private Articulo articulo;
	private Integer cantidad;

	public Pedido() {
	}

	public Pedido(Articulo articulo, Integer cantidad) {
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return articulo.getPrecio() * cantidad.doubleValue();
	}

}

package es.msines.simple.springboot.app.articulos.models.service;

import java.util.List;

import es.msines.simple.springboot.app.commons.models.entity.Articulo;

public interface IArticuloService {

	public List<Articulo> findAll();
	public Articulo findById(Long id);
	
	public Articulo save(Articulo articulo);
	
	public void deleteById(Long id);
}

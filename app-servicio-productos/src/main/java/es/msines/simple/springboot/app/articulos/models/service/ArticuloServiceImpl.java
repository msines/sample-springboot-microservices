package es.msines.simple.springboot.app.articulos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.msines.simple.springboot.app.articulos.models.repository.ArticuloRepository;
import es.msines.simple.springboot.app.commons.models.entity.Articulo;

@Service
public class ArticuloServiceImpl implements IArticuloService {

	@Autowired
	private ArticuloRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		return (List<Articulo>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		return repository.save(articulo);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}

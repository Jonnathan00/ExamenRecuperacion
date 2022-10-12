package com.examen.examen_rec.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examen.examen_rec.entity.Producto;

public interface ProductoService {

	public Iterable<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);
	
	public Optional<Producto> findById(Long codigo);
	
	public Producto save(Producto producto);
	
	public void deleteById(Long codigo);
}

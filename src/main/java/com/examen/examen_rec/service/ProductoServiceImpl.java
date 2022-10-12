package com.examen.examen_rec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.examen_rec.entity.Producto;
import com.examen.examen_rec.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository proRep;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findAll() {
		// TODO Auto-generated method stub
		return proRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return proRep.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Long codigo) {
		// TODO Auto-generated method stub
		return proRep.findById(codigo);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return proRep.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long codigo) {
		// TODO Auto-generated method stub
		proRep.deleteById(codigo);
	}

}

package com.examen.examen_rec.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.examen_rec.entity.Producto;
import com.examen.examen_rec.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	@Autowired
	private ProductoService proServ;
	
	//Metodo Crear
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Producto producto) {
		
		if(producto.getDescripcion().length() > 100) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cantidad de caracteres no puede ser mas de 100");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(proServ.save(producto));
	}
	
	//Metodo Listar
	@GetMapping
	public List<Producto> readAll() {
		
		List<Producto> prod = StreamSupport
				.stream(proServ.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return prod;
	}
	
	
	//Metodo Buscar Id
	@GetMapping("/{codigo}")
	public ResponseEntity<?> read (@PathVariable Long codigo) {
		
		Optional<Producto> opus = proServ.findById(codigo);
		
		if(!opus.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(opus);
	}
	
	//Metodo Actualizar
	@PutMapping("/{codigo}")
	public ResponseEntity<?> update (@RequestBody Producto productoDetails, @PathVariable(value = "codigo") Long codigo) {
		
		Optional<Producto> prod = proServ.findById(codigo);
		
		if(!prod.isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		prod.get().setDescripcion(productoDetails.getDescripcion());
		prod.get().setImagen(productoDetails.getImagen());
		prod.get().setPrecio(productoDetails.getPrecio());
		prod.get().setCantidad(productoDetails.getCantidad());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(proServ.save(prod.get()));
	}
	
	//Metodo Eliminar
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> delete (@PathVariable(value = "codigo") Long codigo) {
		
		if(!proServ.findById(codigo).isPresent()) {
			
			return ResponseEntity.notFound().build();
		}
		
		proServ.deleteById(codigo);
		return ResponseEntity.ok().build();
	}
		
}

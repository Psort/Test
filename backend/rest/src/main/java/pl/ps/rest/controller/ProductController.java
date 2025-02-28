package pl.ps.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ps.model.api.ProductDto;
import pl.ps.model.api.ProductEntry;
import pl.ps.service.ProductService;

import java.util.Set;

/**
 * @author Piotr Skowron
 */
@RestController
@RequestMapping(path = "/products", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	private final ProductService service;

	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping("/{userId}")
	public Set<ProductEntry> getProduct(@PathVariable Long userId) {
		return service.get(userId);
	}

	@PostMapping
	public Long creteProduct(@RequestBody ProductDto dto) {
		return service.create(dto);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	public void updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
		service.update(id, dto);
	}
}

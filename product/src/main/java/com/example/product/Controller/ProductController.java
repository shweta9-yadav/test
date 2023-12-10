package com.example.product.Controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.Dto.ProductDto;
import com.example.product.Entity.Product;
import com.example.product.Service.ProductService;
import com.example.product.ServiceImpl.ProductServiceImpl;
import com.example.product.configuration.MessageStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	private static final Logger logger = Logger.getLogger(ProductController.class.getName());

	
	@Autowired
	public ProductService productService;
	
	@PostMapping(value="/upsert")
	public ResponseEntity<MessageStatus<?>> upsert(@Valid @RequestBody(required = true) ProductDto product,HttpServletResponse resp){
		MessageStatus msg = new MessageStatus();
		msg=productService.upsert(product);
		if(msg.getStausCode()==HttpServletResponse.SC_OK) {
			return ResponseEntity.ok().body(msg);
		}
		else {
			return ResponseEntity.badRequest().body(msg);
		}
		
		
		
	}
	
	@GetMapping("/list")
	public List<Product> getList(){
        return productService.getAllProducts();

		
	} 
	@GetMapping("/{id}")
	public ProductDto getProdctId(@PathVariable Long id) throws Exception{
		ProductDto product= productService.getById(id);
		return  product;
		
	} 
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<MessageStatus<?>> delete(@RequestParam(required = true) Long id ){
		MessageStatus msg = new MessageStatus();
		msg = productService.delete(id);
		if(msg.getStausCode()==HttpServletResponse.SC_OK) {
			return ResponseEntity.ok().body(msg);
		}
		else {
			return ResponseEntity.badRequest().body(msg);
		}
		
	}
	
}

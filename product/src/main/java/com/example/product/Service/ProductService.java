package com.example.product.Service;

import java.util.List;

import com.example.product.Dto.ProductDto;
import com.example.product.Entity.Product;
import com.example.product.configuration.MessageStatus;
public interface ProductService {

	MessageStatus upsert(ProductDto product);

	List<Product> getAllProducts();

	ProductDto getById(Long id) throws Exception;

	MessageStatus delete(Long id);

}

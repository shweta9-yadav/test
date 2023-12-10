package com.example.product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.Entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByName(String name);

}

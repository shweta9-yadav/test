package com.example.product.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.product.Dto.ProductDto;
import com.example.product.Entity.Product;
import com.example.product.Repository.ProductRepository;
import com.example.product.Service.ProductService;
import com.example.product.configuration.MessageStatus;
import com.fasterxml.jackson.databind.util.BeanUtil;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

	@Autowired
	 private ProductRepository rep;
	
	@Override
	public MessageStatus upsert(ProductDto product) {
		MessageStatus msg = new MessageStatus();
		// TODO Auto-generated method stub
		try {
			logger.info("Inside upsert product");
			
			Long id = product.getId();
			Product duplicteName = rep.findByName(product.getName());
			if(duplicteName!=null){
                return new MessageStatus("Duplicate product name", HttpServletResponse.SC_NOT_ACCEPTABLE);

			}
			if(id==null) {
			try {	
			
				logger.info("Inside create product");
				Product pr = new Product();
			BeanUtils.copyProperties(product, pr);
			msg.setMessage("product created Successfully");
			msg.setStausCode(HttpServletResponse.SC_OK);
			
			if(msg.getStausCode()==HttpServletResponse.SC_OK) {
				rep.save(pr);
			}
			
			
			return msg;
			}
			catch (DataIntegrityViolationException e) {
				// TODO: handle exception
				logger.info("Error Occur Inside create/update product");
				e.printStackTrace();
			}	
		}
		else {
			logger.info("Inside update product");
			Product pr = new Product();
			BeanUtils.copyProperties(product, pr);
			msg.setMessage("product update Successfully");
			msg.setStausCode(HttpServletResponse.SC_OK);
			if(msg.getStausCode()==HttpServletResponse.SC_OK) {
				rep.save(pr);
			}
			return msg;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.info("Error Occur Inside upset product");
			e.printStackTrace();
		}
		
		return msg;
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("Inside a product list");
		return rep.findAll();
	}

	@Override
	public ProductDto getById(Long id) {
		// TODO Auto-generated method stub
		ProductDto pr = new ProductDto();
		Product pro = rep.findById(id)
		            .orElseThrow(() -> new OpenApiResourceNotFoundException("Product Not Found" + id));
		//Product pro = rep.findById(id).orElse(null);
		BeanUtils.copyProperties( pro,pr);
		logger.info("Inside a product Id");
		return pr;
	}

	@Override
	public MessageStatus delete(Long id) {
		// TODO Auto-generated method stub
		MessageStatus msg = new MessageStatus<>();
		logger.info("Inside delete product");
		try {
			Product pro = rep.findById(id).orElse(null);
			if(pro==null) {
				 return new MessageStatus<>("product is not found", HttpServletResponse.SC_BAD_REQUEST);
			}
			try {
					rep.deleteById(id);
		            return new MessageStatus<>("Product deleted successfully", HttpServletResponse.SC_OK);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception occur delete product");
			e.printStackTrace();
		}
		return msg;
	}

	


}

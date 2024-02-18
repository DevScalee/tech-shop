package com.idl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idl.services.ProductService;
import com.idl.models.Product;
import jakarta.validation.constraints.AssertFalse.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productS;
	

}

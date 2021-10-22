package com.san.shoppingcartservice.apis;

import com.san.shoppingcartservice.dtos.ProductDTO;
import com.san.shoppingcartservice.entity.Product;
import com.san.shoppingcartservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    //get all products
    @GetMapping("/get")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = productService.findAllProducts().stream().map(product -> modelMapper.map(product, ProductDTO.class)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    //add new product
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product requestProduct = modelMapper.map(productDTO, Product.class);
        Product product = productService.addProduct(requestProduct);
        return new ResponseEntity<>(modelMapper.map(product, ProductDTO.class), HttpStatus.CREATED);
    }

    //update a product
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") @NotNull Long id,
                                                    @Valid @RequestBody ProductDTO productDTO) {
        Product requestProduct = modelMapper.map(productDTO, Product.class);
        Product product = productService.updateProduct(id, requestProduct);
        return new ResponseEntity<>(modelMapper.map(product, ProductDTO.class), HttpStatus.OK);
    }

    //delete a product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") @NotNull Long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package tech.lander.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tech.lander.constants.CommonConstant;
import tech.lander.domain.Product;
import tech.lander.repository.ProductRepoNew;
import tech.lander.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
@Tag(name = "productapi", description = "Operations pertaining to the Product test api.")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepoNew productRepoNew;

    @Operation(summary = "Fetch all Products", description = "Returns all products without condition")
    @RequestMapping(value = "products", method = RequestMethod.GET)
    public List<Product> listAllProducts() {
        return productRepoNew.findAll();
    }

    @Operation(summary = "Fetch by description", description = "Returns products where the Product Name matches the entered string.")
    @RequestMapping(value = "products/desc", method = RequestMethod.GET)
    public List<Product> listByDesc(@RequestParam String desc) {
//        return productRepoNew.findByDescription(desc);
        return productRepository.findByProductName(desc);
    }

    @Operation(summary = "Fetch by status", description = "Returns products where the Product status matches the entered string.")
    @RequestMapping(value = "products/status", method = RequestMethod.GET)
    public List<Product> listByStatus(@RequestParam String status) {
        return productRepoNew.findByStatus(status);
    }

    @Operation(summary = "Add Product", description = "Creates a new Product.")
    @RequestMapping(value = "products", method = RequestMethod.POST)
    ResponseEntity<?> addProduct(@RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                productRepository.addProduct(product);
                return new ResponseEntity<String>(HttpStatus.CREATED);
            }
            catch (Exception e){
                return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    @Operation(summary = "Fetch Product by ID", description = "Returns a Product for the given ID.")
    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    public Product  getById(@PathVariable String id) {
        Optional<Product> foundProduct = productRepoNew.findById(id);
        return foundProduct.get();
        //TODO Add try catch
    }

    @Operation(summary = "Update a product", description = "Update a product entity.")
    @RequestMapping(value = "products/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        try {
            productRepoNew.save(product);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Delete a product", description = "Delete the product for the given ID.")
    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable String id) {
        productRepository.deleteProduct(id);
        return CommonConstant.MESSAGE_PRODUCT_DELETED;
    }
}
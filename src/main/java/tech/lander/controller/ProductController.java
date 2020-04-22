package tech.lander.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value="productapi", description = "Operations pertaining to the Product test api.")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepoNew productRepoNew;

    @ApiOperation(value = "Fetch all Products", notes = "Returns all products without condition")
    @RequestMapping(value = "products", method = RequestMethod.GET)
    public List<Product> listAllProducts() {
        return productRepoNew.findAll();
    }

    @ApiOperation(value = "Fetch by description", notes = "Returns products where the Product Name matches the entered string.")
    @RequestMapping(value = "products/desc", method = RequestMethod.GET)
    public List<Product> listByDesc(@RequestParam String desc) {
//        return productRepoNew.findByDescription(desc);
        return productRepository.findByProductName(desc);
    }

    @ApiOperation(value = "Fetch by status", notes = "Returns products where the Product status matches the entered string.")
    @RequestMapping(value = "products/status", method = RequestMethod.GET)
    public List<Product> listByStatus(@RequestParam String status) {
        return productRepoNew.findByStatus(status);
    }

    @ApiOperation(value = "Add Product", notes = "Creates a new Product.")
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

    @ApiOperation(value = "Fetch Product by ID", notes = "Returns a Product for the given ID.")
    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    public Product  getById(@PathVariable String id) {
        Optional<Product> foundProduct = productRepoNew.findById(id);
        return foundProduct.get();
        //TODO Add try catch
    }

    @ApiOperation(value = "Update a product", notes = "Update a product entity.")
    @RequestMapping(value = "products/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        try {
            productRepoNew.save(product);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Delete a product", notes = "Delete the product for the given ID.")
    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable String id) {
        productRepository.deleteProduct(id);
        return CommonConstant.MESSAGE_PRODUCT_DELETED;
    }
}

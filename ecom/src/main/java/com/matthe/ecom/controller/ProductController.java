package com.matthe.ecom.controller;

import com.matthe.ecom.model.Product;
import com.matthe.ecom.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public  String greet(){
        return "Hello World";
    }

    @GetMapping("/products")
    @Operation(summary = "Get all Products", description = "Get description of all the products")
    @ApiResponse(responseCode = "200", description = "Got all products successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<List<Product>> getallProducts(){
    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    @Operation(summary = "Get a product", description = "Get a particular product")
    @ApiResponse(responseCode = "200", description = "got a product successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product!=null){
            return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    @Operation(summary = "Add a product", description = "Add a product")
    @ApiResponse(responseCode = "200", description = "Added a product successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try{
            Product product1 = productService.addProduct(product, imageFile);
            return  new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch(Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    @Operation(summary = "Get product image", description = "Get a particular product's image")
    @ApiResponse(responseCode = "200", description = "Got image successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    @Operation(summary = "Update a product", description = "Update a particular product")
    @ApiResponse(responseCode = "200", description = "Updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,@RequestPart MultipartFile imageFile){
        Product product1 = null;
        try{
            product1=productService.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(product != null){
            return  new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>("Update Failed",HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/product/{id}")
    @Operation(summary = "Delete a product", description = "Delete a particular product")
    @ApiResponse(responseCode = "200", description = "Deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product= productService.getProductById(id);
        if(product!=null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Delete",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/products/search")
//    public ResponseEntity<List<Product>> searchProduct (@RequestParam String keyword){
//        System.out.println("searching with"+ keyword);
//        List<Product> products = productService.searchProducts(keyword);
//        return  new ResponseEntity<>(products,HttpStatus.OK);
//    }
}

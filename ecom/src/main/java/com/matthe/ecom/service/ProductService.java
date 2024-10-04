package com.matthe.ecom.service;

import com.matthe.ecom.model.Product;
import com.matthe.ecom.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {

        Product product = repo.findById(id).orElse(null);
        if (product != null) {
            System.out.println("Product ID: " + product.getId() + ", Stock Quantity: " + product.getStockQuantity());
        } else {
            System.out.println("Product not found with ID: " + id);
        }
        return product;
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct != null) {
            // Update the existing product's fields
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setReleaseDate(product.getReleaseDate());
            existingProduct.setProductAvailable(product.isProductAvailable());
            existingProduct.setStockQuantity(product.getStockQuantity());

            // Update the image data if it's provided
            if (imageFile != null && !imageFile.isEmpty()) {
                existingProduct.setImageData(imageFile.getBytes());
                existingProduct.setImageName(imageFile.getOriginalFilename());
                existingProduct.setImageType(imageFile.getContentType());
            }

            // Save the updated product
            return repo.save(existingProduct);
        } else {
            // Handle case where the product does not exist
            return null;
        }
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}

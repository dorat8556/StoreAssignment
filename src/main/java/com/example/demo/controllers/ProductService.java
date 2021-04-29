package com.example.demo.controllers;

import com.example.demo.jpa.entities.CategoryEntity;
import com.example.demo.jpa.entities.ProductEntity;
import com.example.demo.jpa.entities.SellerEntity;
import com.example.demo.jpa.repositories.CategoryJpaRepository;
import com.example.demo.jpa.repositories.ProductJpaRepository;
import com.example.demo.jpa.repositories.SellerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/product")
public class ProductService
{

    private final ProductJpaRepository _productJpaRepository;
    private final SellerJpaRepository _sellerJpaRepository;
    private final CategoryJpaRepository _categoryJpaRepository;

    public ProductService(ProductJpaRepository _productJpaRepository, SellerJpaRepository _sellerJpaRepository, CategoryJpaRepository _categoryJpaRepository) {
        this._productJpaRepository = _productJpaRepository;
        this._sellerJpaRepository = _sellerJpaRepository;
        this._categoryJpaRepository = _categoryJpaRepository;
    }


    //----------Retrieve Products----------------

    @GetMapping(path = "/mysql")
    public ResponseEntity<ProductEntity> getProductFromMysql(@RequestParam(value = "name") String name)
    {
        ProductEntity product = _productJpaRepository.findByName(name);
        if (product != null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        System.out.println("There isn't any Product in MySQL database with name: " + name);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(path = "/all/mysql")
    public List<ProductEntity> getAllProductsFromMysql()
    {
        return _productJpaRepository.findAll();
    }


    //----------Create a Product-----------------

    @PostMapping(path = "/mysql")
    public Object addNewProductInMysql(@RequestBody ProductEntity product)
    {
        //Check the constraints
        if (product.getName() == null || product.getName().trim().isEmpty())
        {
            return HttpStatus.BAD_REQUEST;
        }

        SellerEntity seller;
        try
        {
            seller = _sellerJpaRepository.findById(product.getSeller().getId()).orElseThrow(EntityNotFoundException::new);
        }
        catch (EntityNotFoundException e)
        {
            return HttpStatus.BAD_REQUEST;
        }

        HashSet<CategoryEntity> categories = new HashSet<>();
        try
        {
            for (CategoryEntity categoryEntity : product.getFallIntoCategories())
            {
                categories.add(_categoryJpaRepository.findById(categoryEntity.getId()).orElseThrow(EntityNotFoundException::new));
            }
        }
        catch (EntityNotFoundException e)
        {
            return HttpStatus.BAD_REQUEST;
        }

        if (!categories.isEmpty())
        {
            ProductEntity createdProductEntity = new ProductEntity(product.getName(),
                    product.getPrice(),
                    seller,
                    categories);
            createdProductEntity = _productJpaRepository.save(createdProductEntity);
            System.out.println("A new Product created in MySQL database with id: " + createdProductEntity.getId() + "  and name: " + createdProductEntity.getName());
            return createdProductEntity;
        }
        else
        {
            return HttpStatus.BAD_REQUEST;
        }
    }


    //----------Update a Product-----------------

    @PutMapping(path = "/mysql")
    public ResponseEntity<String> updateProductInMysql(@Valid @RequestBody ProductEntity product)
    {
        ProductEntity productEntity;
        SellerEntity sellerEntity;
        try
        {
            productEntity = _productJpaRepository.getOne(product.getId());
            System.out.println("The product " + productEntity.getName() + " with id " + productEntity.getId() + " is updating...");
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>("This product does not exists in MySQL database.", HttpStatus.NOT_FOUND);
        }
        try
        {
            sellerEntity = _sellerJpaRepository.getOne(product.getSeller().getId());
            System.out.println("The seller of this product is: " + sellerEntity.toString());
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>("The seller does not exists", HttpStatus.NOT_FOUND);
        }
        HashSet<CategoryEntity> categories = new HashSet<>();
        for (CategoryEntity categoryEntity : product.getFallIntoCategories())
        {
            _categoryJpaRepository.findById(categoryEntity.getId()).ifPresent(categories::add);
        }
        if (!categories.isEmpty())
        {
            productEntity.setName(product.getName());
            productEntity.setPrice(product.getPrice());
            productEntity.setSeller(sellerEntity);
            productEntity.setFallIntoCategories(categories);
            _productJpaRepository.save(productEntity);
            return new ResponseEntity<>("The product updated", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("The product must belongs to at least one category!", HttpStatus.BAD_REQUEST);
        }
    }
}

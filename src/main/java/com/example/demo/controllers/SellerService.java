package com.example.demo.controllers;


import com.example.demo.jpa.entities.ProfileEntity;
import com.example.demo.jpa.entities.SellerEntity;
import com.example.demo.jpa.repositories.SellerJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/seller")
public class SellerService
{
    private final SellerJpaRepository _sellerJpaRepository;

    public SellerService(SellerJpaRepository _sellerJpaRepository) {
        this._sellerJpaRepository = _sellerJpaRepository;
    }

    //----------Retrieve Sellers----------------

    @GetMapping(path = "/mysql")
    public ResponseEntity<?> getSellerFromMysql(@RequestParam(value = "id") long id)
    {
        try
        {
            SellerEntity seller = _sellerJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            System.out.println("The seller with id " + id + " = " + seller.toString());
            return new ResponseEntity<>(seller, HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>("There isn't any seller with this name in MySQL.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/all/mysql")
    public List<SellerEntity> getAllSellersFromMysql()
    {
        return _sellerJpaRepository.findAll();
    }


    //----------Create a Seller-----------------

    @PostMapping(path = "/mysql")
    public ResponseEntity<SellerEntity> addNewSellerInMysql(@Valid @RequestBody SellerEntity seller)
    {
        SellerEntity sellerEntity = new SellerEntity(seller.getAccountId());
        ProfileEntity profile = new ProfileEntity(sellerEntity, seller.getProfile().getFirstName(), seller.getProfile().getLastName());
        sellerEntity.setProfile(profile);
        sellerEntity = _sellerJpaRepository.save(sellerEntity);
        return new ResponseEntity<>(sellerEntity, HttpStatus.OK);
    }


    //----------Update a Seller-----------------

    @PutMapping(path = "/mysql")
    public ResponseEntity<String> updateSellerInMysql(@Valid @RequestBody SellerEntity seller)
    {
        SellerEntity sellerEntity = _sellerJpaRepository.findById(seller.getId()).orElse(null);
        if (sellerEntity == null)
        {
            return new ResponseEntity<>("This seller doesn't exists in MySQL.", HttpStatus.NOT_FOUND);
        }
        sellerEntity.setAccountId(seller.getAccountId());
        sellerEntity.getProfile().setFirstName(seller.getProfile().getFirstName());
        sellerEntity.getProfile().setLastName(seller.getProfile().getLastName());
        sellerEntity = _sellerJpaRepository.save(sellerEntity);
        System.out.println("__________________________________________________________________");
        System.out.println("The row of " + sellerEntity.toString() + " updated");
        return new ResponseEntity<>("The seller updated", HttpStatus.OK);
    }
}

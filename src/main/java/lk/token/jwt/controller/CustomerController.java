package lk.token.jwt.controller;


import lk.token.jwt.dto.CustomerDTO;
import lk.token.jwt.entity.Customer;
import lk.token.jwt.repo.CustomerRepo;
import lk.token.jwt.service.CustomerService;
import lk.token.jwt.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


/**
 * Created By Imesh Hirushan
 * Project Name : jwt
 * Package Name : lk.token.jwt.controller
 * Date : Mar 9, 2024
 * Time : 10:11 PM
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    final CustomerService customerService;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator, CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator1) {
        this.customerService = customerService;
        this.jwtTokenGenerator = jwtTokenGenerator1;
    }


    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer register = customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String , String>> loginCustomer(@RequestBody CustomerDTO customerDTO){
        HashMap<String, String> stringStringHashMap = customerService.logInCustomer(customerDTO);
        return new ResponseEntity<>(stringStringHashMap, HttpStatus.CREATED);
    }


    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Integer customerId , @RequestBody CustomerDTO customerDTO , @RequestHeader (name = "Authorization") String authorization){

        if (jwtTokenGenerator.validateJwtToken(authorization)){
            Customer customer = customerService.updateCustomer(customerId, customerDTO);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }
}

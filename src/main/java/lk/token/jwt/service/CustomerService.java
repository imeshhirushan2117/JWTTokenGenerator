package lk.token.jwt.service;

import lk.token.jwt.dto.CustomerDTO;
import lk.token.jwt.entity.Customer;
import lk.token.jwt.repo.CustomerRepo;
import lk.token.jwt.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;

/**
 * Created By Imesh Hirushan
 * Project Name : jwt
 * Package Name : lk.token.jwt.service
 * Date : Mar 9, 2024
 * Time : 9:55 PM
 */
@Service
public class CustomerService {

    final CustomerRepo customerRepo;
    final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CustomerService(CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public Customer registerCustomer(CustomerDTO customerDTO) {
        return customerRepo.save(new Customer(customerDTO.getEmail(), customerDTO.getPassword()));
    }

    public HashMap<String, String> logInCustomer(CustomerDTO customerDTO) {

        HashMap<String, String> response = new HashMap<>();
        Customer userByEmailAndPassword = customerRepo.findUserByEmailAndPassword(customerDTO.getEmail(), customerDTO.getPassword());

        System.out.println(userByEmailAndPassword);
        if (userByEmailAndPassword != null) {
            String token = this.jwtTokenGenerator.generateJwtToken(customerDTO);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");
        }
        return response;
    }

    public Customer updateCustomer(Integer id, CustomerDTO customerDTO) {
        if(customerRepo.existsById(id)){
            Customer save = customerRepo.save(new Customer(id, customerDTO.getEmail(),customerDTO.getPassword(),customerDTO.getNic()));
            return save;
        }else{
            return null;
        }

    }

//    public Customer updateCustomer(Integer id, CustomerDTO customerDTO, String authorizationHeader) {
//        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)){
//            if (customerRepo.existsById(id)) {
//                Customer save = customerRepo.save(new Customer(id, customerDTO.getEmail(),customerDTO.getPassword(),customerDTO.getNic()));
//                return save;
//            }else{
//                return null;
//            }
//        }else{
//            return null;
//        }
//
//
//    }

}

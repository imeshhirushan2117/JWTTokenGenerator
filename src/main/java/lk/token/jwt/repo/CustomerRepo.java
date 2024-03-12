package lk.token.jwt.repo;

import lk.token.jwt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By Imesh Hirushan
 * Project Name : jwt
 * Package Name : lk.token.jwt.repo
 * Date : Mar 9, 2024
 * Time : 9:55 PM
 */
public interface CustomerRepo extends JpaRepository <Customer , Integer> {
  Customer findUserByEmailAndPassword(String email, String password);

 // Customer findCustomerByEmailaAndPassword()

}

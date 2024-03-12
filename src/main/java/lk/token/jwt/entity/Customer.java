package lk.token.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By Imesh Hirushan
 * Project Name : jwt
 * Package Name : lk.token.jwt.entity
 * Date : Mar 9, 2024
 * Time : 9:51 PM
 */

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer cusId;
    private String email;
    private String password;
    private String nic;

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

}

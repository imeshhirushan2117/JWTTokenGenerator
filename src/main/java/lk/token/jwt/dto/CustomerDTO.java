package lk.token.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By Imesh Hirushan
 * Project Name : jwt
 * Package Name : lk.token.jwt.dto
 * Date : Mar 9, 2024
 * Time : 10:07 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private int cusId;
    private String email;
    private String password;
    private String nic;

}

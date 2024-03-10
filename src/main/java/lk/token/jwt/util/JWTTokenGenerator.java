package lk.token.jwt.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.token.jwt.dto.CustomerDTO;
import lk.token.jwt.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Created By Imesh Hirushan
 * Project Name : jwt
 * Package Name : lk.token.jwt.util
 * Date : Mar 9, 2024
 * Time : 10:39 PM
 */
@Component
public class JWTTokenGenerator {

    @Value("${customer.app.jwtSecret}")
    private String jwtSecret;

    @Value("${customer.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken(CustomerDTO customerDTO){
        return Jwts.builder()
                .setId(String.valueOf(customerDTO.getCusId()))
                .setSubject(customerDTO.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken){
        String jwtToken = authToken.substring("Bearer ".length());

       try {
           Jwts.parserBuilder().setSigningKey(key()).build().parse(jwtToken);
           return true;
       }catch (Exception e){
           System.out.println("Error when generating token...");
       }
       return false;
    }

}

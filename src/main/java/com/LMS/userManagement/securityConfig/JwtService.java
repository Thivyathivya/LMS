package com.LMS.userManagement.securityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    private final long jwtTokenExpiration=1000*60*15; //15mins
    private final long refreshTokenExpiration=604800000; //7 days

    public String extractUsername(String token) {
        return  extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
     //   Map<String,Object> extraClaims =new HashMap<>();
      //  extraClaims.put("role",userDetails.getAuthorities());
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return buildToken(extraClaims,userDetails,jwtTokenExpiration);
    }


    public boolean isTokenValid(String token ,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return  extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
final  Claims claims=extractAllClaims(token);
return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSigninKey() {

        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateRefreshToken(UserDetails userDetails){
            return buildToken(new HashMap<>(),userDetails,refreshTokenExpiration);
    }

    public String buildToken(Map<String,Object> extraClaims,
                             UserDetails userDetails,
                             long expiration){
        return  Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}

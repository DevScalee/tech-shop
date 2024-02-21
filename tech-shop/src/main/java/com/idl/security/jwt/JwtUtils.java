package com.idl.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.idl.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

//Convert the secret key to a Base64-encoded string (if needed)
  private String jwtSecret= Encoders.BASE64.encode(secretKey.getEncoded());
  //@Value("${techshop.app.jwtSecret}")
  

  @Value("${techshop.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  @SuppressWarnings("deprecation")
public String generateJwtToken(Authentication authentication) {

	    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

	    return Jwts.builder()
	        .setSubject((userPrincipal.getUsername()))
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	        .signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	  }

	  public String getUserNameFromJwtToken(String token) {
	    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	  }

	  public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (SignatureException e) {
	      logger.error("Signature JWT invalide: {}", e.getMessage());
	    } catch (MalformedJwtException e) {
	      logger.error("token JWT non valide: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	      logger.error("Tekon JWT est expiré: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	      logger.error("Le jeton JWT n’est pas pris en charge: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	      logger.error("La chaîne de caractères JWT est vide.: {}", e.getMessage());
	    }

	    return false;
	  }
	}

package com.project.util;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.models.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

@Component
public class JWTUtils implements Serializable {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "iat";
	private static final long serialVersionUID = -3301605591108950415L;
	private Clock clock = DefaultClock.INSTANCE;
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


	public String CreateJWTToken(Users user) {

		final Date createdDate = clock.now();
	    final Date expirationDate = calculateExpirationDate(createdDate);
		
		Claims claims = Jwts.claims();
		claims.put("name", user.getUserName());
		claims.put("email", user.getEmail());
		claims.put("user_id", user.getUserId());
		claims.setSubject(user.getUserName());
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET)
				.compact();

		return token;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
//		logger.info("all claims in getClaim from token are " + claims);
		T apply = claimsResolver.apply(claims);
//		logger.info("claimsResolver apply does" + claimsResolver.apply(claims));
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(Constants.JWT_SECRET).parseClaimsJws(token).getBody();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
//		JwtUserDetails user = userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(clock.now());
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + Constants.jwttokenexpirationinseconds * 1000);
	}

}

//  @Value("${jwt.signing.key.secret}")
//  private String secret;
//
//  @Value("${jwt.token.expiration.in.seconds}")
//  private Long expiration;
//
//  public Date getIssuedAtDateFromToken(String token) {
//    return getClaimFromToken(token, Claims::getIssuedAt);
//  }

//  private Boolean ignoreTokenExpiration(String token) {
//    // here you specify tokens, for that the expiration is ignored
//    return false;
//  }
//
//  public String generateToken(UserDetails userDetails) {
//    Map<String, Object> claims = new HashMap<>();
//    return doGenerateToken(claims, userDetails.getUsername());
//  }
//
//  private String doGenerateToken(Map<String, Object> claims, String subject) {
//    final Date createdDate = clock.now();
//    final Date expirationDate = calculateExpirationDate(createdDate);
//
//    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
//        .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
//  }
//
//  public Boolean canTokenBeRefreshed(String token) {
//    return (!isTokenExpired(token) || ignoreTokenExpiration(token));
//  }
//
//  public String refreshToken(String token) {
//    final Date createdDate = clock.now();
//    final Date expirationDate = calculateExpirationDate(createdDate);
//
//    final Claims claims = getAllClaimsFromToken(token);
//    claims.setIssuedAt(createdDate);
//    claims.setExpiration(expirationDate);
//
//    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
//  }
//

//

//}
//

package common;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Security {
	Algorithm algorithm = Algorithm.HMAC256("SimpleServlet");
	
	public String getJWTToken(String username) {
		String jwtToken = JWT.create()
				.withIssuer("DanTruongIT") // Người phát hành
				.withSubject("Subject JWT") //Subject
				.withClaim("username", username) // Data
				.withIssuedAt(new Date()) //Ngày phát hành
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24L)) // Hết hạn sau 1 ngày
//				.withNotBefore(new Date(System.currentTimeMillis() + 1000 * 60)) // Có hiệu lực sau 1 phút
				.sign(algorithm);
		return jwtToken;
	}
	
	public String decodedJWTToken(String token) {
		try {
			JWTVerifier verifier = JWT.require(algorithm)
					  .withIssuer("DanTruongIT")
					  .build();
			DecodedJWT decodedJWT = verifier.verify(token);
			String usr = decodedJWT.getClaim("username").asString();
			return usr;
		} catch (Exception e) {
			return null;
		}
	}
}

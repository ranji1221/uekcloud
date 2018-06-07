package org.ranji.lemon.volador.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.auth.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author sertion
 * @date 2018/6/6
 * @since JDK1.8
 * @version 1.0
 */
public class JWTUtil {

	/**
	  * 创建 jwt
	  * @param id
	  * @param subject
	  * @param ttlMillis
	  * @return
	  * @throws Exception
	  */
	  public static String createJWT(String id, String subject, long ttlMillis) throws Exception {
	       SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
	       long nowMillis = System. currentTimeMillis();
	       Date now = new Date( nowMillis);
	       SecretKey key = generalKey();
	       JwtBuilder builder = Jwts. builder()
	            .setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	           .signWith(signatureAlgorithm, key);
	       if (ttlMillis >= 0){
	           long expMillis = nowMillis + ttlMillis;
	           Date exp = new Date( expMillis);
	           builder.setExpiration( exp);
	       }
	       return builder.compact();
	 }

	  /**
	  * 解密 jwt
	  * @param jwt
	  * @return
	  * @throws Exception
	  */
	  public static Claims parseJWT(String jwt) throws Exception{
	       SecretKey key = generalKey();
	       Claims claims = Jwts. parser()
	          .setSigningKey( key)
	          .parseClaimsJws( jwt).getBody();
	       return claims;
	 }
	  
    /**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        String stringKey = "zhangsixing";
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    
}

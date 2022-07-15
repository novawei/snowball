package com.nova.common.security.util;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtils {
    private static final String SECRET_KEY = "3R7CIWbpgVc5j5a6DBD/oh71yZ37wQTHP/3A466X9sRTBEZHf3X+wrt12iuHgCS8bwyOSk+bXrQHKLiiSC5j8g==";

    public static String buildToken(String  userId) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        return Jwts.builder()
                .setSubject(userId)
                .signWith(key)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static String parseUserId(String token) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretKey = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretKey);
    }
}

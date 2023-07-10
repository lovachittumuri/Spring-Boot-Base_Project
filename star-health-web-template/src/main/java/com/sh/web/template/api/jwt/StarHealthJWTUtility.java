package com.sh.web.template.api.jwt;


import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.web.template.api.jwt.dto.DataForJwtTokenGeneration;
import com.sh.web.template.api.jwt.dto.DataFromJwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class StarHealthJWTUtility {

	public String generateJwtToken(DataForJwtTokenGeneration dataForJwtTokenGeneration) {
		String jwtToken = Jwts.builder()
				// SET HEADER
				.setHeader(dataForJwtTokenGeneration.getHeaderMap())
				// SET JWT BODY
				.setId(dataForJwtTokenGeneration.getTokenId()).setSubject(dataForJwtTokenGeneration.getSubject())
				.setIssuer(dataForJwtTokenGeneration.getTokenIssurer())
				.setIssuedAt(dataForJwtTokenGeneration.getTokenIssuedAt())
				.setExpiration(new Date(System.currentTimeMillis()
						+ TimeUnit.MINUTES.toMillis(dataForJwtTokenGeneration.getExpiration())))
				// SET JWT SIGNATURE
				.signWith(dataForJwtTokenGeneration.getSignatureAlgorithm(),
						Base64.getEncoder().encode(dataForJwtTokenGeneration.getSecretKey().getBytes()))
				.compact();
		return jwtToken;
	}

	public Boolean validateToken(String secretKey, String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
					.parseClaimsJws(token).getBody();
			System.out.println("Claims: " + claims);
			System.out.println("TOKEN IS VALID.");
			return true;
		} catch (ExpiredJwtException exp) {
			System.out.println("TOKEN EXPIRED.");
			return false;
		} catch (UnsupportedJwtException exp) {
			System.out.println("UNSUPPORTED TOKEN.");
			return false;
		} catch (MalformedJwtException exp) {
			System.out.println("TOKEN MALFORMED. INVALID TOKEN");
			return false;
		} catch (SignatureException exp) {
			System.out.println("SIGNATURE IS INVALID.");
			return false;
		} catch (IllegalArgumentException exp) {
			System.out.println("ILLEGAL ARGUMENT.");
			return false;
		} catch (Exception exp) {
			System.out.println("SOMETHING WENT WRONG WHILE VALIDATING TOKEN.");
			return false;
		}
	}

	public <T> DataFromJwtToken<T> readPayloadFromToken(String secretKey, String token, Class<T> subjectType) {
		DataFromJwtToken<T> tokenData = new DataFromJwtToken<T>();
		try {
			Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
					.parseClaimsJws(token).getBody();

			System.out.println("CLAIMS: " + claims);

			ObjectMapper mapper = new ObjectMapper();
			T subject = mapper.readValue(claims.getSubject(), subjectType);

			tokenData.setTokenId(claims.getId());
			tokenData.setTokenIssurer(claims.getIssuer());
			tokenData.setTokenIssuedAt(claims.getIssuedAt());
			tokenData.setExpiration(claims.getExpiration());
			tokenData.setSubject(subject);
			return tokenData;
		} catch (ExpiredJwtException exp) {
			System.out.println("TOKEN EXPIRED.");
			return null;
		} catch (UnsupportedJwtException exp) {
			System.out.println("UNSUPPORTED TOKEN.");
			return null;
		} catch (MalformedJwtException exp) {
			System.out.println("TOKEN MALFORMED. INVALID TOKEN");
			return null;
		} catch (SignatureException exp) {
			System.out.println("SIGNATURE IS INVALID.");
			return null;
		} catch (IllegalArgumentException exp) {
			System.out.println("ILLEGAL ARGUMENT.");
			return null;
		} catch (JsonMappingException exp) {
			System.out.println("EXCEPTION OCCURED WHILE MAPPING JSON");
			return null;
		} catch (JsonProcessingException exp) {
			System.out.println("EXCEPTION OCCURED WHILE PROCESSING JSON");
			return null;
		}
	}

	public <T> T readHeadersFromToken(String token, Class<T> headersType) {
		try {
			String[] split_string = token.split("\\.");
			String base64EncodedHeader = split_string[0];
			String headerJson = new String(Base64.getDecoder().decode(base64EncodedHeader));
			System.out.println("JWT Header : " + headerJson);

			ObjectMapper mapper = new ObjectMapper();
			T headers = mapper.readValue(headerJson, headersType);
			return headers;
		} catch (IllegalArgumentException exp) {
			System.out.println("ILLEGAL ARGUMENT.PLEASE PASS BASE64 ENCODED TOKEN.");
			return null;
		} catch (JsonMappingException exp) {
			System.out.println("EXCEPTION OCCURED WHILE MAPPING JSON");
			return null;
		} catch (JsonProcessingException exp) {
			System.out.println("EXCEPTION OCCURED WHILE PROCESSING JSON");
			return null;
		}
	}
}

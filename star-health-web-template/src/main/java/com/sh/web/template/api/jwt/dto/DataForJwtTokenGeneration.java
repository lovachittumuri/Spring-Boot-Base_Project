package com.sh.web.template.api.jwt.dto;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

@Data
public class DataForJwtTokenGeneration {
	//HEADERS
	Map<String, Object> headerMap=new ConcurrentHashMap<String, Object>();
	//PAYLOAD/BODY
	private String tokenIssurer;
	private Date tokenIssuedAt=new Date();
	private Long expiration;
	private String tokenId;
	private String subject;
	//SIGNATURE
	private SignatureAlgorithm signatureAlgorithm;
	private String secretKey;
}

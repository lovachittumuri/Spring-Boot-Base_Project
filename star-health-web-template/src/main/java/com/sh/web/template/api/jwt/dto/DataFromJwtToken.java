package com.sh.web.template.api.jwt.dto;

import java.util.Date;
import lombok.Data;

@Data
public class DataFromJwtToken<T> {
	private String tokenIssurer;
	private Date tokenIssuedAt;
	private Date expiration;
	private String tokenId;
	private T subject;
}

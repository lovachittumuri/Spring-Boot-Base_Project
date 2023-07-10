package com.sh.retail.api.model;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuoteRequest {

	@JsonProperty(value = "APIKEY")
	private String APIKEY;

	@JsonProperty(value = "SECRETKEY")
	private String SECRETKEY;
	
	@JsonProperty(value = "policyName")
	private String policyTypeName;
	
	@NotEmpty(message = "postalCode is mandatory.")
	@Size(min = 6,max = 6,message = "postalCode must be 6 digits.")
	private String postalCode;
}
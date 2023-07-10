package com.sh.retail.api.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(value = Include.NON_NULL)
public  class QuoteResponse {
	private String premium;
	private String serviceTax;
	private String totalPremium;
	private String discountAmount;
}

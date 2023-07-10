package com.sh.web.template.rest.client;

import java.util.List;
import java.util.Map;

import com.sh.web.template.model.StarHealthRestClientResponse;

/**
 * StarHealthRestClient<T,R,ER> is an abstraction layer on top of RestTemplate
 * provided by Spring framework
 *
 * @author Parisapogu Venkat
 */
public interface StarHealthRestClient<T, R, ER> {
	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 * @param requestBody       Provide request Pay load
	 * @param headersMap        Provide headers
	 * @param responseType      Provide success response pay load type
	 * @param errorResponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Map<String, String> headersMap,
			Class<R> responseType, Class<ER> errorResponseType);

	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 *                          to call
	 * @param requestBody       Provide request Pay load
	 * @param responseType      Provide success response pay load type
	 * @param errorresponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Class<R> responseType,
			Class<ER> errorresponseType);

	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 *                          to call
	 * @param requestBody       Provide request Pay load
	 * @param responseType      Provide success response pay load type
	 * @param uriVariables      Provide path parameters and query parameters
	 * @param errorresponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse RestTemplate Wrapper RestClient P
	 *         Venkat,M.Sc(cs) Page 2
	 */
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Class<R> responseType,
			Map<String, String> uriVariables, Class<ER> errorresponseType);

	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 *                          to call
	 * @param requestBody       Provide request Pay load
	 * @param responseType      Provide success response pay load type
	 * @param uriVariables      Provide path parameters and query parameters
	 * @param headersMap        Provide headers
	 * @param errorresponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> callPostMethod(String url, T requestBody, Class<R> responseType,
			Map<String, String> uriVariables, Map<String, String> headersMap, Class<ER> errorresponseType);

	/**
	 * @param url                     Provide URL of the Restful service to whom you
	 *                                want to call
	 * @param requestBody             Provide request Pay load
	 * @param responseType            Provide success response pay load type
	 * @param uriVariables            Provide path parameters and query parameters
	 * @param multipleHeaderValuesMap Provide headers
	 * @param ErrorresponseType       Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> postMethod(String url, T requestBody, Class<R> responseType,
			Map<String, String> uriVariables, Map<String, List<String>> multipleHeaderValuesMap,
			Class<ER> ErrorresponseType);

	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 *                          to call
	 * @param responseType      Provide success response pay load type
	 * @param errorresponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> callGetMethod(String url, Class<R> responseType,
			Class<ER> errorresponseType);

	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 *                          to call
	 * @param responseType      Provide success response pay load type RestTemplate
	 *                          Wrapper RestClient P Venkat,M.Sc(cs) Page 3
	 * @param uriVariables      Provide path parameters and query parameters
	 * @param errorresponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> callGetMethod(String url, Class<R> responseType,
			Map<String, String> uriVariables, Class<ER> errorresponseType);

	/**
	 * @param url               Provide URL of the Restful service to whom you want
	 * @param headersMap        Provide headers
	 * @param uriVariables      Provide path parameters and query parameters
	 * @param responseType      Provide success response pay load type
	 * @param errorResponseType Provide Error response pay load type
	 * @return StarHealthRestClientResponse
	 */
	public StarHealthRestClientResponse<R, ER> callGetMethod(String url, Map<String, String> headersMap,
			Map<String, String> uriVariables, Class<R> responseType, Class<ER> errorResponseType);

}

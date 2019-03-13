package com.jd.seed.base.rpc;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * RPC请求基类
 * 
 * appCode String 调用者身份编码
 * serviceCode String 服务接口编码
 * timestamp String 时间戳，当前时间毫秒数
 * params T 具体参数
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 下午1:48:00
 */
@ApiModel(description="RPC请求类")
public class RpcRequest<T> implements Serializable {

	private static final long serialVersionUID = -5421435458975210479L;
	// appCode
	@ApiModelProperty(value="应用授权编码",required=true,notes="由服务提供方提供")
	@NotBlank(message = "the field of appCode is not null")
	private String appCode;
	// serviceCode
	@ApiModelProperty(value="服务授权编码",required=true,notes="由服务提供方提供")
	@NotBlank(message = "the field of serviceCode is not null")
	private String serviceCode;
	// timestamp
	@ApiModelProperty(value="请求时间戳",required=true,notes="由服务调用方提供")
	@Min(value = 1522286908831L, message = "the field of timestamp must be long,and bigger than 1522286908831")
	private String timestamp;
	// sign
	@ApiModelProperty(value="请求签名字符串",required=true,notes="由服务调用方提供")
	@NotBlank(message = "the field of sign is not null")
	private String sign;
	// params
	@ApiModelProperty(value="请求实际参数",required=false,notes="由服务调用方提供")
	@Valid
	private T params;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}

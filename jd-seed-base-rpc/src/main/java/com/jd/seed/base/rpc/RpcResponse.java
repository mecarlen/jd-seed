package com.jd.seed.base.rpc;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * RPC返回基类
 * 
 * state String 状态码
 * errorMsg String 错误信息
 * result T 返回结果
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月29日 下午2:02:26
 */
@ApiModel(description="请求返回类")
public class RpcResponse<T> implements Serializable {

	private static final long serialVersionUID = -4397672856377576733L;
	// state
	@ApiModelProperty(value="状态码",required=true)
	private String state;
	// errorMsg
	@ApiModelProperty(value="错误信息",required=false)
	private String errorMsg;
	// result
	@ApiModelProperty(value="具返回结果",required=false)
	private T result;

	public RpcResponse() {
		this.state = EnumRpcResponseState.SUCCESS.code();
	}

	public String getState() {
		return state;
	}

	private void setState(String state) {
		this.state = state;
	}

	public RpcResponse<T> state(EnumRpcResponseState state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	private void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public RpcResponse<T> errorMsg(String errorMsg) {
		this.setErrorMsg(errorMsg);
		return this;
	}

	public T getResult() {
		return result;
	}

	private void setResult(T result) {
		this.result = result;
	}

	public RpcResponse<T> result(T result) {
		this.setResult(result);
		return this;
	}

	final public boolean isSuccess() {
		return EnumRpcResponseState.SUCCESS.code().equals(state);
	}

}

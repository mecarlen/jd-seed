package com.jd.seed.dictionary.city;

import java.io.Serializable;
import java.util.Date;

import com.jd.seed.base.domain.Entity;
import com.jd.seed.base.rpc.security.encrypt.Encryption;
import com.jd.seed.base.rpc.security.encrypt.EnumEncryptionType;
import com.jd.seed.dictionary.Dictionary;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年3月30日 下午6:13:07
 */
public class CityDTO implements City,Dictionary, Serializable {

	private static final long serialVersionUID = -2786602360598894021L;
	// id 无业务意义
	private Long id;
	// 中文名
	@Encryption(type = EnumEncryptionType.DEFAULT)
	private String zhName;
	// 英文名
	private String enName;
	// 三字码
	@Encryption(type = EnumEncryptionType.ENCRYPT)
	private String unityCode;
	// 中文名全拼
	private String zhFullPin;
	// 中文名简拼
	private String zhShortPin;
	// 优先级
	private int priority;
	// 状态
	private int state;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;

	private CityDTO() {
	}

	private CityDTO(final Builder builder) {
		this.id = builder.id;
		this.zhName = builder.zhName;
		this.enName = builder.enName;
		this.unityCode = builder.unityCode;
		this.zhFullPin = builder.zhFullPin;
		this.zhShortPin = builder.zhShortPin;
		this.priority = builder.priority;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		// id
		private Long id;
		// 中文名
		final private String zhName;
		// 英文名
		private String enName;
		// 三字码
		final private String unityCode;
		// 中文名全拼
		private String zhFullPin;
		// 中文名简拼
		private String zhShortPin;
		// 优先级
		private int priority = -1;
		// 状态
		private State state = State.NORMAL;
		// 创建时间
		private Date createTime;
		// 更新时间
		private Date updateTime;

		public Builder(final String zhName, final String unityCode) {
			this.zhName = zhName;
			this.unityCode = unityCode;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder enName(final String enName) {
			this.enName = enName;
			return this;
		}

		public Builder zhFullPin(final String zhFullPin) {
			this.zhFullPin = zhFullPin;
			return this;
		}

		public Builder zhShortPin(final String zhShortPin) {
			this.zhShortPin = zhShortPin;
			return this;
		}

		public Builder priority(final int priority) {
			this.priority = priority;
			return this;
		}

		public Builder state(final Entity.State state) {
			if (null != state) {
				this.state = state;
			}
			return this;
		}

		public Builder createTime(final Date createTime) {
			if (null != createTime) {
				this.createTime = new Date(createTime.getTime());
			}
			return this;
		}

		public Builder updateTime(final Date updateTime) {
			if (null != updateTime) {
				this.updateTime = new Date(updateTime.getTime());
			}
			return this;
		}

		public CityDTO build() {
			if (null == createTime) {
				this.createTime = new Date();
			}
			return new CityDTO(this);
		}
	}

	// setter
	private void setId(Long id) {
		this.id = id;
	}

	private void setZhName(String zhName) {
		this.zhName = zhName;
	}

	private void setEnName(String enName) {
		this.enName = enName;
	}

	private void setUnityCode(String unityCode) {
		this.unityCode = unityCode;
	}

	private void setZhFullPin(String zhFullPin) {
		this.zhFullPin = zhFullPin;
	}

	private void setZhShortPin(String zhShortPin) {
		this.zhShortPin = zhShortPin;
	}

	private void setPriority(int priority) {
		this.priority = priority;
	}

	private void setState(int state) {
		this.state = state;
	}

	private void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public String getZhName() {
		return zhName;
	}

	public String getEnName() {
		return enName;
	}

	public String getUnityCode() {
		return unityCode;
	}

	public String getZhFullPin() {
		return zhFullPin;
	}

	public String getZhShortPin() {
		return zhShortPin;
	}

	public Integer getPriority() {
		return priority;
	}

	public Integer getState() {
		return state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
	public String getName() {
		return getZhName();
	}

	public CityDTO id(Long id) {
		this.setId(id);
		return this;
	}

	public CityDTO zhName(final String zhName) {
		this.setZhName(zhName);
		return this;
	}

	public CityDTO enName(final String enName) {
		this.setEnName(enName);
		return this;
	}

	public CityDTO zhFullPin(final String zhFullPin) {
		this.setZhFullPin(zhFullPin);
		return this;
	}

	public CityDTO zhShortPin(final String zhShortPin) {
		this.setZhShortPin(zhShortPin);
		return this;
	}

	public CityDTO unityCode(final String unityCode) {
		this.setUnityCode(unityCode);
		return this;
	}

	public CityDTO priority(final int priority) {
		this.setPriority(priority);
		return this;
	}

	public CityDTO state(final Entity.State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	public CityDTO createTime(final Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	public CityDTO updateTime(final Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	final public static CityDTO getInstance(final City city) {
		if (null == city)
			return null;
		return new CityDTO.Builder(city.getZhName(), city.getUnityCode())
				.state(State.getInstance(city.getState())).zhFullPin(city.getZhFullPin())
				.zhShortPin(city.getZhShortPin()).enName(city.getEnName()).priority(city.getPriority())
				.createTime(city.getCreateTime()).updateTime(city.getUpdateTime()).build().id(city.getId());
	}
}

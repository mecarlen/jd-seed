package com.jd.seed.dictionary.domain;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.Entity;
import com.jd.seed.dictionary.Dictionary;
import com.jd.seed.dictionary.city.City;

/**
 * <pre>
 * 城市
 * 
 * </pre>
 *
 * @author mecarlen 2017年11月21日 上午10:28:07
 */
@Document(indexName = "seed", type = "dict_city")
public class CityEntity extends BaseEntity<Long, CityVO> implements City, Dictionary, Entity<Long> {
	// 中文名
	@Field(type = FieldType.Auto, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", store = true)
	private String zhName;
	// 英文名
	private String enName;
	// 三字码
	private String unityCode;
	// 中文名全拼
	private String zhFullPin;
	// 中文名简拼
	private String zhShortPin;
	// 优先级
	private Integer priority;

	private CityEntity() {
	}

	private CityEntity(Builder builder) {
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

	// setter
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

	private void setPriority(Integer priority) {
		this.priority = priority;
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
		private Entity.State state = Entity.State.NORMAL;
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

		public CityEntity build() {
			if (null == createTime) {
				this.createTime = new Date();
			}
			return new CityEntity(this);
		}
	}

	// getter
	public String getZhName() {
		return zhName;
	}

	@Override
	public String getName() {
		return getZhName();
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

	@Override
	public CityEntity id(Long id) {
		this.setId(id);
		return this;
	}

	public CityEntity zhName(final String zhName) {
		this.setZhName(zhName);
		return this;
	}

	public CityEntity enName(final String enName) {
		this.setEnName(enName);
		return this;
	}

	public CityEntity zhFullPin(final String zhFullPin) {
		this.setZhFullPin(zhFullPin);
		return this;
	}

	public CityEntity zhShortPin(final String zhShortPin) {
		this.setZhShortPin(zhShortPin);
		return this;
	}

	public CityEntity unityCode(final String unityCode) {
		this.setUnityCode(unityCode);
		return this;
	}

	public CityEntity priority(final int priority) {
		this.setPriority(priority);
		return this;
	}

	public CityEntity state(final Entity.State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	public CityEntity createTime(final Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	public CityEntity updateTime(final Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	@Override
	public CityVO toVO() {
		return CityVO.getInstance(this);
	}

	final public static CityEntity getInstance(final City city) {
		if (null == city)
			return null;
		return new CityEntity.Builder(city.getZhName(), city.getUnityCode())
				.state(Entity.State.getInstance(city.getState())).zhFullPin(city.getZhFullPin())
				.zhShortPin(city.getZhShortPin()).enName(city.getEnName()).priority(city.getPriority())
				.createTime(city.getCreateTime()).updateTime(city.getUpdateTime()).build().id(city.getId());
	}
}

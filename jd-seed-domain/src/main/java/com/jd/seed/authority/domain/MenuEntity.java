package com.jd.seed.authority.domain;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import com.jd.seed.authority.Menu;
import com.jd.seed.base.domain.BaseEntity;
import com.jd.seed.base.domain.BuildEntityException;

/**
 * <pre>
 * 菜单
 * 
 * </pre>
 * 
 * @author mecarlen 2018年7月7日 下午7:41:56
 */
@Document(indexName = "seed", type = "auth_menu")
public class MenuEntity extends BaseEntity<Long, MenuVO> implements Menu {
	// 名称
	@Field(analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String name;
	// 统一标识
	private String unityCode;
	// 父菜单
	private MenuEntity parent;
	// sn
	private String sn;
	// URL
	private String url;
	// 次序
	private Integer sequence;
	// 备注
	private String descr;
	// 类型,0-菜单,1-功能
	private Integer type;

	private MenuEntity() {
	}

	private MenuEntity(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.unityCode = builder.unityCode;
		this.parent = builder.parent;
		this.sn = builder.sn;
		this.url = builder.url;
		this.sequence = builder.sequence;
		this.descr = builder.descr;
		this.type = builder.type;
		this.state = builder.state.code();
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	// setter
	private void setName(String name) {
		this.name = name;
	}

	private void setUnityCode(String unityCode) {
		this.unityCode = unityCode;
	}

	private void setParent(MenuEntity parent) {
		this.parent = parent;
	}

	private void setSn(String sn) {
		this.sn = sn;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	private void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	private void setDescr(String descr) {
		this.descr = descr;
	}

	private void setType(Integer type) {
		this.type = type;
	}

	public static class Builder {
		// id
		private Long id;
		// 名称
		final private String name;
		// 统一标识
		final private String unityCode;
		// 父菜单
		private MenuEntity parent;
		// sn
		final private String sn;
		// URL
		final private String url;
		// 次序
		private Integer sequence;
		// 备注
		private String descr;
		// 类型父节点,叶子节点,按钮
		final private Integer type;
		// 状态
		private State state = State.NORMAL;
		// 创建时间
		private Date createTime;
		// 更新时间
		private Date updateTime;

		public Builder(String name, String unityCode, String sn, String url, Integer type) {
			if (StringUtils.isAnyEmpty(name, unityCode, sn, url) || null == type) {
				throw new BuildEntityException.RequiredFieldsIsEmptyException();
			}
			this.sn = sn;
			this.name = name;
			this.unityCode = unityCode;
			this.url = url;
			this.type = type;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder parent(Menu parent) {
			if (null != parent) {
				this.parent = MenuEntity.getInstance(parent);
			}
			return this;
		}

		public Builder sequence(Integer sequence) {
			this.sequence = sequence;
			return this;
		}

		public Builder descr(String descr) {
			this.descr = descr;
			return this;
		}

		public Builder state(State state) {
			if (null != state) {
				this.state = state;
			}
			return this;
		}

		public Builder createTime(Date createTime) {
			if (null != createTime) {
				this.createTime = new Date(createTime.getTime());
			}
			return this;
		}

		public Builder updateTime(Date updateTime) {
			if (null != updateTime) {
				this.updateTime = new Date(updateTime.getTime());
			}
			return this;
		}

		public MenuEntity build() {
			if (null == createTime) {
				createTime = new Date();
			}
			return new MenuEntity(this);
		}

	}

	// getter
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUnityCode() {
		return unityCode;
	}

	public MenuEntity getParent() {
		return parent;
	}

	public String getSn() {
		return sn;
	}

	public String getUrl() {
		return url;
	}

	public Integer getSequence() {
		return sequence;
	}

	public String getDescr() {
		return descr;
	}

	public Integer getType() {
		return type;
	}

	@Override
	public MenuEntity id(Long id) {
		this.setId(id);
		return this;
	}

	public MenuEntity name(String name) {
		this.setName(name);
		return this;
	}

	public MenuEntity unityCode(String unityCode) {
		this.setUnityCode(unityCode);
		return this;
	}

	public MenuEntity parent(Menu menu) {
		if (null != menu) {
			this.setParent(MenuEntity.getInstance(menu));
		}
		return this;
	}

	public MenuEntity sn(String sn) {
		this.setSn(sn);
		return this;
	}

	public MenuEntity url(String url) {
		this.setUrl(url);
		return this;
	}

	public MenuEntity sequence(Integer sequence) {
		this.setSequence(sequence);
		return this;
	}

	public MenuEntity type(Integer type) {
		this.setType(type);
		return this;
	}

	public MenuEntity descr(String descr) {
		this.setDescr(descr);
		return this;
	}

	@Override
	public MenuEntity state(State state) {
		if (null != state) {
			this.setState(state.code());
		}
		return this;
	}

	@Override
	public MenuEntity createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public MenuEntity updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	final public static MenuEntity getInstance(Menu menu) {
		if (null == menu)
			return null;
		return new MenuEntity.Builder(menu.getName(), menu.getUnityCode(), menu.getSn(), menu.getUrl(), menu.getType())
				.id(menu.getId()).parent(menu.getParent()).build().sequence(menu.getSequence()).descr(menu.getDescr())
				.state(State.getInstance(menu.getState())).createTime(menu.getCreateTime())
				.updateTime(menu.getUpdateTime());
	}

	@Override
	public MenuVO toVO() {
		return MenuVO.getInstance(this);
	}

}

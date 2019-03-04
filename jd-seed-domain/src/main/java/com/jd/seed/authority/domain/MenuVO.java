package com.jd.seed.authority.domain;

import java.util.Date;

import com.jd.seed.authority.Menu;
import com.jd.seed.base.domain.BaseVO;

/**
 * <pre>
 * 菜单
 * 
 * </pre>
 * 
 * @author mecarlen 2018年7月7日 下午7:43:15
 */
public class MenuVO extends BaseVO<Long, MenuEntity> implements Menu {
	// 名称
	private String name;
	// 统一标识
	private String unityCode;
	// 父菜单
	private MenuVO parent;
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

	private MenuVO() {
	}

	private MenuVO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.unityCode = builder.unityCode;
		this.parent = builder.parent;
		this.sn = builder.sn;
		this.url = builder.url;
		this.sequence = builder.sequence;
		this.descr = builder.descr;
		this.type = builder.type;
		this.state = builder.state;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	public static class Builder {
		// id
		private Long id;
		// 名称
		final private String name;
		// 统一标识
		final private String unityCode;
		// 父菜单
		private MenuVO parent;
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

		public Builder(String name, String unityCode, String sn, String url, Integer type, Menu parent) {
			this.sn = sn;
			this.name = name;
			this.unityCode = unityCode;
			this.url = url;
			this.type = type;
			if (null != parent) {
				this.parent = MenuVO.getInstance(parent);
			}
		}

		public Builder id(Long id) {
			this.id = id;
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

		public MenuVO build() {
			if (null == createTime) {
				createTime = new Date();
			}
			return new MenuVO(this);
		}

	}

	// setter
	private void setName(String name) {
		this.name = name;
	}

	private void setUnityCode(String unityCode) {
		this.unityCode = unityCode;
	}

	private void setParent(MenuVO parent) {
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

	// getter
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUnityCode() {
		return unityCode;
	}

	public MenuVO getParent() {
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
	public MenuVO id(Long id) {
		this.setId(id);
		return this;
	}

	public MenuVO name(String name) {
		this.setName(name);
		return this;
	}

	public MenuVO unityCode(String unityCode) {
		this.setUnityCode(unityCode);
		return this;
	}

	public MenuVO parent(Menu menu) {
		if (null != menu) {
			this.setParent(MenuVO.getInstance(menu));
		}
		return this;
	}

	public MenuVO sn(String sn) {
		this.setSn(sn);
		return this;
	}

	public MenuVO url(String url) {
		this.setUrl(url);
		return this;
	}

	public MenuVO sequence(Integer sequence) {
		this.setSequence(sequence);
		return this;
	}

	public MenuVO type(Integer type) {
		this.setType(type);
		return this;
	}

	public MenuVO descr(String descr) {
		this.setDescr(descr);
		return this;
	}

	@Override
	public MenuVO state(State state) {
		if (null != state) {
			this.setState(state);
		}
		return this;
	}

	@Override
	public MenuVO createTime(Date createTime) {
		if (null != createTime) {
			this.setCreateTime(new Date(createTime.getTime()));
		}
		return this;
	}

	@Override
	public MenuVO updateTime(Date updateTime) {
		if (null != updateTime) {
			this.setUpdateTime(new Date(updateTime.getTime()));
		}
		return this;
	}

	final public static MenuVO getInstance(Menu menu) {
		if (null == menu)
			return null;

		return new MenuVO.Builder(menu.getName(), menu.getUnityCode(), menu.getSn(), menu.getUrl(), menu.getType(),
				menu.getParent()).id(menu.getId()).build().sequence(menu.getSequence()).descr(menu.getDescr())
						.state(State.getInstance(menu.getState())).createTime(menu.getCreateTime())
						.updateTime(menu.getUpdateTime());
	}

	@Override
	public MenuEntity toEntity() {
		return MenuEntity.getInstance(this);
	}
}

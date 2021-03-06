package com.jd.seed.organization.domain;

import java.util.Date;

import com.jd.seed.base.domain.BaseVO;
import com.jd.seed.base.domain.Entity;
import com.jd.seed.organization.Employee;

/**
 * <pre>
 * 员工
 * 
 * </pre>
 * 
 * @author mecarlen 2018年11月5日 下午2:58:21
 */
public class EmployeeVO extends BaseVO<Long, EmployeeEntity> implements Employee, Entity<Long> {
	// 员工号
	private String unityCode;
	// 姓名
	private String fullName;
	// erp账号
	private String erpAccount;
	// 密码
	private String password;
	// 邮箱
	private String email;
	// 手机号
	private String mobile;
	// 座机
	private String phone;
	// sn
	private String sn;
	// 描述
	private String descr;

	private EmployeeVO() {

	}

	private EmployeeVO(Builder builder) {
		this.id = builder.id;
		this.unityCode = builder.unityCode;
		this.fullName = builder.fullName;
		this.erpAccount = builder.erpAccount;
		this.password = builder.password;
		this.sn = builder.sn;
		this.email = builder.email;
		this.mobile = builder.mobile;
		this.phone = builder.phone;
		this.descr = builder.descr;
		this.state = builder.state;
		this.createTime = builder.createTime;
		this.updateTime = builder.updateTime;
	}

	// setter
	private void setUnityCode(String unityCode) {
		this.unityCode = unityCode;
	}

	private void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private void setErpAccount(String erpAccount) {
		this.erpAccount = erpAccount;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private void setPhone(String phone) {
		this.phone = phone;
	}

	private void setSn(String sn) {
		this.sn = sn;
	}

	private void setDescr(String descr) {
		this.descr = descr;
	}

	public static class Builder {
		// id
		private Long id;
		// 员工号
		private String unityCode;
		// 姓名
		final private String fullName;
		// erp账号
		final private String erpAccount;
		// 密码
		final private String password;
		// 邮箱
		final private String email;
		// 手机号
		final private String mobile;
		// 座机
		private String phone;
		// sn
		private String sn;
		// 描述
		private String descr;
		// 状态
		private State state = State.NORMAL;
		// 创建时间
		private Date createTime;
		// 更新时间
		private Date updateTime;

		public Builder(final String fullName, final String erpAccount, final String password, final String email,
				final String mobile) {
			this.fullName = fullName;
			this.erpAccount = erpAccount;
			this.password = password;
			this.email = email;
			this.mobile = mobile;
		}

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder unityCode(String unityCode) {
			this.unityCode = unityCode;
			return this;
		}

		public Builder phone(final String phone) {
			this.phone = phone;
			return this;
		}

		public Builder sn(final String sn) {
			this.sn = sn;
			return this;
		}

		public Builder descr(final String descr) {
			this.descr = descr;
			return this;
		}

		public Builder state(final State state) {
			this.state = state;
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

		public EmployeeVO build() {
			return new EmployeeVO(this);
		}
	}

	// getter
	public String getUnityCode() {
		return unityCode;
	}

	public String getFullName() {
		return fullName;
	}

	public String getErpAccount() {
		return erpAccount;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}

	public String getPhone() {
		return phone;
	}

	public String getSn() {
		return sn;
	}

	public String getDescr() {
		return descr;
	}

	public EmployeeVO id(final Long id) {
		setId(id);
		return this;
	}

	public EmployeeVO unityCode(final String unityCode) {
		setUnityCode(unityCode);
		return this;
	}

	public EmployeeVO fullName(final String fullName) {
		setFullName(fullName);
		return this;
	}

	public EmployeeVO erpAccount(final String erpAccount) {
		setErpAccount(erpAccount);
		return this;
	}

	public EmployeeVO password(final String password) {
		setPassword(password);
		return this;
	}

	public EmployeeVO email(final String email) {
		setEmail(email);
		return this;
	}

	public EmployeeVO mobile(final String mobile) {
		setMobile(mobile);
		return this;
	}

	public EmployeeVO phone(final String phone) {
		setPhone(phone);
		return this;
	}

	public EmployeeVO sn(final String sn) {
		setSn(sn);
		return this;
	}

	public EmployeeVO descr(final String descr) {
		setDescr(descr);
		return this;
	}

	public EmployeeVO state(final State state) {
		if (null != state) {
			setState(state);
		}
		return this;
	}

	public EmployeeVO createTime(final Date createTime) {
		if (null != createTime) {
			this.createTime = new Date(createTime.getTime());
		}
		return this;
	}

	public EmployeeVO updateTime(final Date updateTime) {
		if (null != updateTime) {
			this.updateTime = new Date(updateTime.getTime());
		}
		return this;
	}

	public static EmployeeVO getInstance(Employee employee) {
		if (null == employee)
			return null;
		return new EmployeeVO.Builder(employee.getFullName(), employee.getErpAccount(), employee.getPassword(),
				employee.getEmail(), employee.getMobile()).state(State.getInstance(employee.getState()))
						.phone(employee.getPhone()).build().id(employee.getId()).unityCode(employee.getUnityCode())
						.sn(employee.getSn()).descr(employee.getDescr()).createTime(employee.getCreateTime())
						.updateTime(employee.getUpdateTime());
	}

	@Override
	public EmployeeEntity toEntity() {
		return EmployeeEntity.getInstance(this);
	}

}

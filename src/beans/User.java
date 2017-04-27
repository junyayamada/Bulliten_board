package beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String account;
	private String password;
	private String confirmPassword;
	private int branch_id;
	private int department_id;
//	private String branch_name;
//	private String department_name;
	private int is_stoped;
	private int message_id;
	private Date created_at;
	private Date updated_at;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int getBranchId() {
		return branch_id;
	}
	public void setBranchId(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getDepartmentId() {
		return department_id;
	}
	public void setDepartmentId(int department_id) {
		this.department_id = department_id;
	}

	public int getIsStoped() {
		return is_stoped;
	}
	public void setIsStoped(int is_stoped) {
		this.is_stoped = is_stoped;
	}

	public int getMessageId() {
		return message_id;
	}
	public void setMessageId(int message_id) {
		this.message_id = message_id;
	}

	public Date getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}
	public void setUpdatedAt(Date updated_at) {
		this.updated_at = updated_at;
	}
}
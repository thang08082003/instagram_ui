package wp.zenny.model;

import java.util.Date;
import wp.zenny.dao.MD5Encryptor;

public class User {
	private String username;
	private String password;
	private String fullname;
	private String gender;
	private Date birthday;
	private String phonenumber;
	private String avatar;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getGender() {
		if(gender == null) return "";
		if(gender.equals("1")) return "Nam";
		return "Nữ";
	}
	public int getGenderInt() {
		if(gender == null) return 0;
		return Integer.parseInt(gender);
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public User(String username, String password, String fullname, String gender, Date birthday, String phonenumber,
			String avatar) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
		this.birthday = birthday;
		this.phonenumber = phonenumber;
		this.avatar = avatar;
	}
	
	public User() {}
}

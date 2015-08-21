package com.suxihui.demo_sms;


/**
 *
 */
public class CallLogBean {

	private String number;
	private String cachedName;
	private String type;
	private String date;

	public CallLogBean(String number, String cachedName, String type, String date) {
		this.number = number;
		this.cachedName = cachedName;
		this.type = type;
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCachedName() {
		return cachedName;
	}

	public void setCachedName(String cachedName) {
		this.cachedName = cachedName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CallLogBean{" +
				"number='" + number + '\'' +
				", cachedName='" + cachedName + '\'' +
				", type='" + type + '\'' +
				", date='" + date + '\'' +
				'}';
	}
}

package com.suxihui.demo_sms;

public class SMSBean {

	private String address;
	private String person;
	private String date;
	private String type;
	private String body;

	public SMSBean(String address, String person, String date, String type, String body) {
		this.address = address;
		this.person = person;
		this.date = date;
		this.type = type;
		this.body = body;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "SMSBean{" +
				"address='" + address + '\'' +
				", person='" + person + '\'' +
				", date='" + date + '\'' +
				", type='" + type + '\'' +
				", body='" + body + '\'' +
				'}';
	}
}

package org.concourplus.business.referential.json;

public class AddressJson {
	private long id;
	private String street;
	private String city;
	private String state;
	private String postalCode;
	private CountryJson country;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public CountryJson getCountry() {
		return country;
	}

	public void setCountry(CountryJson country) {
		this.country = country;
	}

}

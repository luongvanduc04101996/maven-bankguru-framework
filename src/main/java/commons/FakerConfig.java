package commons;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerConfig {
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);
	
	public static FakerConfig getFakeData() {
		return new FakerConfig();
	}
	
	public String getFirstname() {
		return faker.address().firstName();
	}
	
	public String getLastname() {
		return faker.address().lastName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getCompanyName() {
		return faker.company().name();
	}
	
	public String getPassword() {
		return faker.internet().password(6, 8);
	}
	
	public String getCityName() {
		return faker.address().cityName();
	}
	
	public String getAddress() {
		return faker.address().streetAddress();
	}
	
	public String getZipCode() {
		return faker.address().zipCode();
	}
	
	public String getPhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}
	
	public String getCreditCardNumber() {
		return faker.business().creditCardNumber();
	}
}

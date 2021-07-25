package pageUIs;

public class BasePageUI {
	public static final String DYNAMIC_LINK_BY_TEXT = "//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_TD_BY_TEXT_AT_NEW_CUSTOMER = "//td[text()='%s']";
	public static final String DYNAMIC_GET_ATTRIBUTE_VALUE_AT_EDIT_CUSTOMER = "//td[text()='%s']/following-sibling::td/input";
	public static final String DYNAMIC_GET_TEXT_AT_UPDATED_CUSTOMER = "//td[text()='%s']/following-sibling::td";
	
	public static final String DYNAMIC_GET_ERROR_MESSAGE_AT_TEXTBOX = "//label[@id='%s']";
}

package exception;

public enum ErrMsg {
    WRONG_EMAIL_OR_PASSWORD("Wrong email or password"),

    COMPANY_NAME_EXISTS("Company name already exists"),

    COMPANY_EMAIL_EXISTS("Company email already exists"),

    CANNOT_UPDATE_COMPANY_NAME("Cannot update company name"),

    COUPON_ALREADY_EXISTS("Coupon already exists"),

    CANNOT_UPDATE_COUPON_COMPANY("Cannot update coupon company id"),

    EMAIL_ALREADY_EXISTS("Email already register"),

    COUPON_EXPIRED("Coupon expired"),

    COUPON_ALREADY_PURCHASE_BY_CUSTOMER("Customer already purchase this coupon"),

    NO_COUPON_LEFT("No coupon left");

    public String getMsg() {
        return msg;
    }

    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }


}

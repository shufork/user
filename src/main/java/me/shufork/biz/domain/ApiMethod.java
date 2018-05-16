package me.shufork.biz.domain;

public enum ApiMethod {

    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

    private final String value;

    ApiMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ApiMethod parseOrDefault(final String value, final ApiMethod defValue) {
        for (ApiMethod o : ApiMethod.values()) {
            if (o.getValue().equals(value)) {
                return o;
            }
        }
        return defValue;
    }

    public static ApiMethod parse(final String value) throws IllegalArgumentException {
        ApiMethod o = parseOrDefault(value, null);
        if (o == null) {
            throw new IllegalArgumentException("Invalid value : " + value);
        }
        return o;
    }

}

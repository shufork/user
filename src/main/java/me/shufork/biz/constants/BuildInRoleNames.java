package me.shufork.biz.constants;

public enum BuildInRoleNames {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String value;

    BuildInRoleNames(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BuildInRoleNames parseOrDefault(final String value, final BuildInRoleNames defValue) {
        for (BuildInRoleNames o : BuildInRoleNames.values()) {
            if (o.getValue().equals(value)) {
                return o;
            }
        }
        return defValue;
    }

    public static BuildInRoleNames parse(final String value) throws IllegalArgumentException {
        BuildInRoleNames o = parseOrDefault(value, null);
        if (o == null) {
            throw new IllegalArgumentException("Invalid value : " + value);
        }
        return o;
    }

}

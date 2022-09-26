package courier;

public class CourierCredentials {
    String login;
    String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public static CourierCredentials getCredentialsWithoutLogin(Courier courier) {
        return new CourierCredentials("", courier.getPassword());
    }

    public static CourierCredentials getCredentialWithoutPassword(Courier courier) {
        return new CourierCredentials(courier.getLogin(), "");
    }

    public static CourierCredentials getCredentialWithoutRequiredFields(Courier courier) {
        return new CourierCredentials("", "");
    }

    public static CourierCredentials getCredentialWithWrongLogin(Courier courier) {
        return new CourierCredentials(courier.getLogin() + "N", courier.getPassword());
    }

    public static CourierCredentials getCredentialWithWrongPassword(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword() + "6");
    }

    public static CourierCredentials getCredentialNotExistUser(Courier courier) {
        return new CourierCredentials("wfwoiwjwfwfw", "35453453453tgtg");
    }
}

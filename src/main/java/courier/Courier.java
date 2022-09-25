package courier;
import config.DataProvider;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

public class Courier {
    String login;
    String password;
    String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public static Courier getRandomCourier() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphabetic(10)
        );
    }
    public static Courier getCourierWithoutLogin(){
        return new Courier(DataProvider.getEmptyField(),
                RandomStringUtils.randomAlphanumeric(10),
                RandomStringUtils.randomAlphabetic(10));
    }
    public static Courier getCourierWithoutPassword(){
        return  new Courier(RandomStringUtils.randomAlphanumeric(10),
                DataProvider.getEmptyField(),
                RandomStringUtils.randomAlphabetic(10));
    }
    public static Courier getCourierWithoutRequiredFields(){
        return  new Courier(DataProvider.getEmptyField(),
                DataProvider.getEmptyField(),
                RandomStringUtils.randomAlphabetic(10));
    }
    public Courier() {
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
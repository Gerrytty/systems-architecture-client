package translator.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/***
 * implementation of MailSenderAuthenticator
 * Class realizing authentication by login and password
 ***/

public class PasswordAuthenticator implements MailSenderAuthenticator {

    // account email from where authentication will take place
    private final String fromEmail;
    // password from this account
    private final String password;

    // store a this class instance
    private static PasswordAuthenticator passwordAuthenticator;

    /***
     * Private constructor so that an instance
     * of a class can be created only within this class
     * @param fromEmail account email from where authentication will take place
     * @param password password from this account
     ***/
    private PasswordAuthenticator(String fromEmail, String password) {
        this.fromEmail = fromEmail;
        this.password = password;
    }

    /***
     * Method for getting singleton
     * Params the same as in constructor
     * @param fromEmail account email from where authentication will take place
     * @param password password from this account
     * @return singleton instance of the this class
     ***/
    public static PasswordAuthenticator getPasswordAuthenticator(String fromEmail, String password) {
        // If the instance has not been created yet, create it and save the link
        if (passwordAuthenticator == null) {
            passwordAuthenticator = new PasswordAuthenticator(fromEmail, password);
        }
        // return an already created instance
        return passwordAuthenticator;
    }

    /***
     * method from MailSenderAuthenticator
     * @return implementation of an authenticator using a password and login
     ***/
    @Override
    public Authenticator getAuthenticator() {

        return new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

    }

}

package translator.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PasswordAuthenficator implements MailSenderAuthenficator {

    private final String fromEmail;
    private final String password;

    private static PasswordAuthenficator passwordAuthenficator;

    private PasswordAuthenficator(String fromEmail, String password) {
        this.fromEmail = fromEmail;
        this.password = password;
    }

    public static PasswordAuthenficator getPasswordAuthenficator(String fromEmail, String password) {
        if (passwordAuthenficator == null) {
            passwordAuthenficator = new PasswordAuthenficator(fromEmail, password);
        }
        return passwordAuthenficator;
    }

    @Override
    public Authenticator getAuthenficator() {

        return new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

    }

}

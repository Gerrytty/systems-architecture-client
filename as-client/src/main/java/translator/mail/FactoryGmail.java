package translator.mail;

/***
 * Implementation of abstract factory inteface
 * Overrides methods for getting interfaces
 * Returns classes implements this interfaces
 */
public class FactoryGmail implements AbstractMailFactory {

    private final MailSmtpInterface mailSmtpInterface;
    private final MailSenderAuthenticator passwordAuth;

    /***
     * Create an implementations of interfaces in abstract factory
     ***/
    public FactoryGmail() {
        mailSmtpInterface = new GmailEmail();
        passwordAuth = PasswordAuthenticator.
                getPasswordAuthenticator("qwertyvhjvhjzcr",
                "mihaylova.yuliyaa@gmail.com");
    }

    /***
     * @return return password authenficator
     ***/
    @Override
    public MailSenderAuthenticator getAuthenticator() {
        return passwordAuth;
    }

    /***
     * @return gmail properties
     ***/
    @Override
    public MailSmtpInterface getSmtp() {
        return mailSmtpInterface;
    }
}

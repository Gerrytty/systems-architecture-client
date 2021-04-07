package translator.mail;

public class FactoryGmail implements AbstractMailFactory {

    private final MailSmtpInterface mailSmtpInterface;
    private final MailSenderAuthenficator passwordAuth;

    public FactoryGmail() {
        mailSmtpInterface = new GmailEmail();
        passwordAuth = PasswordAuthenficator.
                getPasswordAuthenficator("qwertyvhjvhjzcr",
                "mihaylova.yuliyaa@gmail.com");
    }

    @Override
    public MailSenderAuthenficator getAuthenficator() {
        return passwordAuth;
    }

    @Override
    public MailSmtpInterface getSmtp() {
        return mailSmtpInterface;
    }
}

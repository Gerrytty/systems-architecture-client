package translator.mail;

public interface AbstractMailFactory {

    MailSenderAuthenficator getAuthenficator();
    MailSmtpInterface getSmtp();

}

package translator.mail;

/***
 * Implementation of the abstract factory pattern
 ***/
public interface AbstractMailFactory {

    /***
     * @return interface of authenficator
     ***/
    MailSenderAuthenticator getAuthenticator();

    /***
     * @return interface of smtp properties
     ***/
    MailSmtpInterface getSmtp();

}

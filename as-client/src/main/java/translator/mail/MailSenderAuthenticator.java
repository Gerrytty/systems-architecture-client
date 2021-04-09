package translator.mail;

import javax.mail.Authenticator;

/***
 * interface for method of authentication
 ***/

public interface MailSenderAuthenticator {

    /***
     * @return some authenficator
     ***/
    Authenticator getAuthenticator();

}

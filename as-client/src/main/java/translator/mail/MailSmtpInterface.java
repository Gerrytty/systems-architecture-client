package translator.mail;

import java.util.Properties;

/***
 * Interface for getting SMTP properties
 * Different for Gmail, Mail etc
 */

public interface MailSmtpInterface {

    /***
     * @return smtp properties
     ***/
    Properties getProps();
}

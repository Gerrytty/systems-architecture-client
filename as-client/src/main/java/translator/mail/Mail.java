package translator.mail;

import lombok.Builder;
import lombok.Data;

/***
 * DTO for storing info for mail
 */

@Data
@Builder
public class Mail {

    // letter message text
    private String message;

    // letter header
    private String subject;

    // from email
    private String from;

    // to email
    private String to;

    // host where the letter was sent from
    private String host;

}

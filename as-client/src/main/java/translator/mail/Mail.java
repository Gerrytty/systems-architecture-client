package translator.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {

    private String message;
    private String subject;
    private String from;
    private String to;
    private String host;

}

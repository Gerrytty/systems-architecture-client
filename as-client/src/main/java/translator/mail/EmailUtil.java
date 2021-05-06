package translator.mail;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/***
 * Util class for email sending
 ***/
public class EmailUtil {

    private final AbstractMailFactory abstractMailFactory;

    @Getter
    @Setter
    private Mail mail;

    /***
     * @param abstractMailFactory class for creating authenficator and smtp properties
     * @param mail dto object of info about mail
     */
    public EmailUtil(AbstractMailFactory abstractMailFactory, Mail mail) {
        this.mail = mail;
        this.abstractMailFactory = abstractMailFactory;
    }

    public void sendEmail() {

        Session session = Session.getDefaultInstance(abstractMailFactory.getSmtp().getProps(),
                abstractMailFactory.getAuthenticator().getAuthenticator());

        try {
            Transport.send(Objects.requireNonNull(createMessage(session)));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("EMail Sent Successfully!!");
    }

    private MimeMessage createMessage(Session session) {
        try {
            MimeMessage msg = new MimeMessage(session);
            // set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            // set letter header
            msg.setSubject(mail.getSubject(), "UTF-8");

            // set message text
            msg.setText(mail.getMessage(), "UTF-8");

            // set current date
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail.getTo(), false));

            return msg;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package translator.mail;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    private final AbstractMailFactory abstractMailFactory;

    @Getter
    @Setter
    private Mail mail;

    public EmailUtil(AbstractMailFactory abstractMailFactory, Mail mail) {
        this.mail = mail;
        this.abstractMailFactory = abstractMailFactory;
    }

    public void sendEmail() {

        Session session = Session.getDefaultInstance(abstractMailFactory.getSmtp().getProps(),
                abstractMailFactory.getAuthenficator().getAuthenficator());

        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(mail.getSubject(), "UTF-8");

            msg.setText(mail.getMessage(), "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail.getTo(), false));


            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

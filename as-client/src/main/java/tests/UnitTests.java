package tests;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import translator.EnglishToRussiaTranslate;
import translator.LanguageStrategy;
import translator.RussiaToEnglishTranslate;
import translator.TranslateInterface;
import translator.mail.*;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/***
 * UNIT TESTING
 * Class for testing functions realizing our buiseness logic
 ***/

public class UnitTests {

    private final EmailUtil emailUtil;
    private final Session session;
    private final Mail mail;

    public UnitTests() {

        // create a instance of Mail for testing
        mail = Mail.builder()
                .from("some@gmail.com")
                .host("localhost")
                .message("TEST")
                .to("to@gmail.com")
                .subject("TEST SUBJECT")
                .build();

        // get default session
        session = Session.getDefaultInstance(new GmailEmail().getProps());
        // get emailUtil for testing
        emailUtil = new EmailUtil(new FactoryGmail(), mail);
    }

    // Testing singleton realization of PasswordAuthenticator
    @Test
    public void singletonPasswordAuthenticatorTest() {
        MailSenderAuthenticator authenticator1 =
                PasswordAuthenticator.getPasswordAuthenticator("some@gmail.com", "123");

        MailSenderAuthenticator authenticator2 =
                PasswordAuthenticator.getPasswordAuthenticator("some@gmail.com", "123");

        Assert.assertEquals(authenticator1, authenticator2);
    }

    // testing strategy choosing translator
    // testing that by "ru" from and "en" to creating right class
    @Test
    public void strategyTestChooseRuToEn() {
        TranslateInterface translateInterface = new LanguageStrategy().getTranslator("ru", "en");
        Assert.assertTrue(translateInterface instanceof RussiaToEnglishTranslate);
    }

    // testing strategy choosing translator
    // testing that by "en" from and "ru" to creating right class
    @Test
    public void strategyTestChooseEnToRu() {
        TranslateInterface translateInterface = new LanguageStrategy().getTranslator("en", "ru");
        Assert.assertTrue(translateInterface instanceof EnglishToRussiaTranslate);
    }

    // testing strategy choosing translator
    // testing that by incorrect params returns null
    @Test
    public void strategyTestChooseNull() {
        TranslateInterface translateInterface = new LanguageStrategy().getTranslator("aa", "bb");
        Assert.assertNull(translateInterface);
    }

    // test that gmail properties is correct
    @Test
    public void testGmailProps() {
        AbstractMailFactory factory = new FactoryGmail();
        Properties properties = factory.getSmtp().getProps();
        Assert.assertEquals(properties.getProperty("mail.smtp.host"), "smtp.gmail.com");
        Assert.assertEquals(properties.getProperty("mail.smtp.socketFactory.port"), "465");
        Assert.assertEquals(properties.getProperty("mail.smtp.socketFactory.class"),
                "javax.net.ssl.SSLSocketFactory");
        Assert.assertEquals(properties.getProperty("mail.smtp.auth"), "true");
        Assert.assertEquals(properties.getProperty("mail.smtp.port"), "465");
    }

    // test that subject of the message is correct
    @SneakyThrows
    @Test
    public void emailSubjectTest() {
        MimeMessage message = emailUtil.createMessage(session);
        Assert.assertEquals(message.getSubject(), mail.getSubject());
    }

    // test that text of the message is correct
    @SneakyThrows
    @Test
    public void emailTextTest() {
        MimeMessage message = emailUtil.createMessage(session);
        Assert.assertEquals(message.getContent(), mail.getMessage());
    }

}

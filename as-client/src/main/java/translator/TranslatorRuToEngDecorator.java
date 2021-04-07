package translator;

import lombok.Setter;
import translator.mail.EmailUtil;
import translator.mail.FactoryGmail;
import translator.mail.Mail;

public class TranslatorRuToEngDecorator implements TranslateInterface {

    @Setter
    private String userEmail;

    @Override
    public String translate(String word) {

        String translatedWord = new RussiaToEnglishTranslate().translate(word);

        Mail mail = Mail.builder()
                .from("mihaylova.yuliyaa@gmail.com")
                .to(userEmail)
                .host("localhost")
                .subject("Your translated word")
                .message("Введеное слово - " + word + "\nПереведенное слово -  " + translatedWord)
                .build();

       EmailUtil emailUtil = new EmailUtil(new FactoryGmail(), mail);
       emailUtil.sendEmail();

        return translatedWord;
    }
}

package translator;

import java.util.Scanner;

public class Main {

    static String from;
    static String to;
    static Scanner scanner = new Scanner(System.in);
    static TranslateInterface translateInterface;

    public static void main(String[] args) {

        TranslatorRuToEngDecorator translatorRuToEngDecorator = new TranslatorRuToEngDecorator();

        translatorRuToEngDecorator.setUserEmail("yulu.2000@hotmail.com");
        translatorRuToEngDecorator.translate("слово");

        while (true) {

            if (from == null || to == null) {
                System.out.println("Enter lang from: ");
                from = scanner.next();
                System.out.println("Enter lang to: ");
                to = scanner.next();

                translateInterface = new LanguageStrategy().getTranslator(from, to);

                if (translateInterface == null) {
                    System.out.println("Not implemented languages!");
                    continue;
                }

            }

            System.out.println("Enter word: ");
            System.out.println("Translated word = " + translateInterface.translate(scanner.next()));

        }

    }
}

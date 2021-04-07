package translator;

public class LanguageStrategy {

    public TranslateInterface getTranslator(String from, String to) {
        if (from.equals("en") && to.equals("ru")) {
            return new EnglishToRussiaTranslate();
        }

        if (from.equals("ru") && to.equals("en")) {
            return new RussiaToEnglishTranslate();
        }

        return null;
    }

}

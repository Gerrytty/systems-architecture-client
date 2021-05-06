package translator;

/***
 * Class for implementing the language selection strategy
 ***/

public class LanguageStrategy {

    /***
     * @param from language from which we translate
     * @param to the language we translate into
     * @return instance of TranslateInterface
     */

    public TranslateInterface getTranslator(String from, String to) {
        if (from.equals("en") && to.equals("ru")) {
            return new EnglishToRussiaTranslate();
        }

        if (from.equals("ru") && to.equals("en")) {
            return new RussiaToEnglishTranslate();
        }

        // if such language is not supported
        return null;
    }

}

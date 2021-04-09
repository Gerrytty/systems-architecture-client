package translator;

/***
 * Interface for translators
 ***/

public interface TranslatorInterface {

    /***
     * @param input original
     * @return translated word
     ***/
    String translate(String input);

}

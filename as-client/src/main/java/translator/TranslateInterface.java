package translator;

/***
 * Interface for translations
 * by lang to lang
 ***/

public interface TranslateInterface {

    /***
     * @param word original
     * @return translated word
     */
    String translate(String word);

}

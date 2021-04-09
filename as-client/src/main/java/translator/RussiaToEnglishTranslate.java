package translator;

import java.io.File;

/***
 * Class implementation of TranslateInterface
 * Translate russia words to english
 * Override method translate
 * For translation using class FromFileTranslator
 * This class using vocabulary in file for translate words
 ***/
public class RussiaToEnglishTranslate implements TranslateInterface {

    // abstraction of translator
    TranslatorInterface translator;

    /***
     * create instance of translator
     * set path to file with vocabulary
     ***/
    public RussiaToEnglishTranslate() {
        this.translator = new FromFileTranslator(new File("en_ru.txt"), 1, 0);
    }

    /***
     * @param word in english to translate
     * @return word in russian
     ***/
    @Override
    public String translate(String word) {
        return translator.translate(word);
    }

}

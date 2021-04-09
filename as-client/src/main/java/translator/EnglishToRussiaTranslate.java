package translator;

import java.io.File;

/***
 * Class implementation of TranslateInterface
 * Translate english words to russian
 * Override method translate
 * For translation using class FromFileTranslator
 * This class using vocabulary in file for translate words
 ***/
public class EnglishToRussiaTranslate implements TranslateInterface {

    // abstraction of translator
    TranslatorInterface translator;

    /***
     * create instance of translator
     * set path to file with vocabulary
     ***/
    public EnglishToRussiaTranslate() {
        this.translator = new FromFileTranslator(new File("en_ru.txt"), 0, 1);
    }

    /***
     * @param word in russian to translate
     * @return word in english
     ***/
    @Override
    public String translate(String word) {
        return translator.translate(word);
    }
}

package translator;

import java.io.File;

public class EnglishToRussiaTranslate implements TranslateInterface {

    TranslatorInterface translator;

    public EnglishToRussiaTranslate() {
        this.translator = new FromFileTranslator(new File("en_ru.txt"), 0, 1);
    }

    @Override
    public String translate(String word) {
        return translator.translate(word);
    }
}

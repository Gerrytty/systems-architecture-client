package translator;

import java.io.File;

public class RussiaToEnglishTranslate implements TranslateInterface {

    TranslatorInterface translator;

    public RussiaToEnglishTranslate() {
        this.translator = new FromFileTranslator(new File("en_ru.txt"), 1, 0);
    }

    @Override
    public String translate(String word) {
        return translator.translate(word);
    }

}

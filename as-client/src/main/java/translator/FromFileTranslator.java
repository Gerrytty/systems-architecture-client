package translator;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;

/***
 * Class implementation of TranslatorInterface
 * Overrides method of translate
 * For translation using vocabulary in file
 * File drawn up by this rule
 * one_lang_word-second_lang_word
 ***/
public class FromFileTranslator implements TranslatorInterface {

    Scanner scanner;
    File file;
    int fromIndex;
    int toIndex;

    @SneakyThrows
    public FromFileTranslator(File file, int fromIndex, int toIndex) {
        this.file = file;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        scanner = new Scanner(file);
    }

    /***
     * @param word to translate
     * @return translated word
     */
    @Override
    public String translate(String word) {

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            // by rule of file
            String[] ss = s.split("-");

            // when we met appropriate word return translation of this word
            if (ss[fromIndex].equals(word)) {
                return ss[toIndex];
            }
        }

        // if such word dose not exists return null
        return null;

    }

}

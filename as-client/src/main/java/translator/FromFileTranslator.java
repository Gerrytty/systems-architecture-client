package translator;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;

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

    @Override
    public String translate(String word) {

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            String[] ss = s.split("-");

            if (ss[fromIndex].equals(word)) {
                return ss[toIndex];
            }
        }

        return null;

    }

}

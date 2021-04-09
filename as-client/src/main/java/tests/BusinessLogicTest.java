package tests;

import org.junit.Assert;
import org.junit.Test;
import translator.*;

/***
 * BUSINESS LOGIC TESTING
 * Class for testing main functions of translator
 ***/

public class BusinessLogicTest {

    // Testing russia to english translate
    @Test
    public void ruToEngTranslate() {
        TranslateInterface translateInterface = new RussiaToEnglishTranslate();
        Assert.assertEquals(translateInterface.translate("кошка"), "cat");
    }

    // Testing english to russia translate
    @Test
    public void engToRuTranslate() {
        TranslateInterface translateInterface = new EnglishToRussiaTranslate();
        Assert.assertEquals(translateInterface.translate("cat"), "кошка");
    }

    // Testing if word is unknown in russia to english translate returns null
    @Test
    public void ruToEngTranslateNull() {
        TranslateInterface translateInterface = new RussiaToEnglishTranslate();
        Assert.assertNull(translateInterface.translate("абв"));
    }

    // Testing if word is unknown in english to russia translate returns null
    @Test
    public void enToRuTranslateNull() {
        TranslateInterface translateInterface = new RussiaToEnglishTranslate();
        Assert.assertNull(translateInterface.translate("abc"));
    }

}

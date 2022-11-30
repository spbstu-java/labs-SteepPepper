package lab_3;

import java.io.File;

/**
 *
 * @author achigir
 */
public class Main_lab_3 {

    static final File INIT = new File("./src/Lab_3/dictionary_EngtoRus.txt");
    static final File TASK = new File("./src/Lab_3/translate_text.txt");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Translator_lab_3 EnToRu = new Translator_lab_3(INIT);
        StringBuilder textTranslate = EnToRu.fileTranslate(TASK);
        System.out.println(textTranslate);
    }
    
}

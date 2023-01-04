import javax.swing.*;
import java.awt.*;

public class DarkMode {

    // singleton kullanılması için bir tane nesne oluşturuyoruz Data structures'teki node classı gibi kendisini referans ediyor
    private static DarkMode instance;
    private Notepad notepad;
    private boolean enabled;

    // constructoru private yapıyoruzki dışardan çağrılmasın
    private DarkMode(Notepad notepad) {
        this.notepad = notepad;
        this.enabled = false;
    }

    // nesne üretilmiş ise çağırıyoruz üretilmemiş ise üretip instancea eşitliyoruz ve döndürüyoruz
    public static DarkMode getInstance(Notepad notepad) {
        if (instance == null) {
            instance = new DarkMode(notepad);
        }
        return instance;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateLookAndFeel();
    }

    // bu kod textAreanın arkaplanını değiştirmeye yarıyor
    private void updateLookAndFeel() {
        if (enabled) {
            notepad.setBackground(Color.BLACK);
            notepad.getTextArea().setBackground(Color.DARK_GRAY);
            notepad.getTextArea().setForeground(Color.WHITE);
        } else {
            notepad.setBackground(Color.WHITE);
            notepad.getTextArea().setBackground(Color.WHITE);
            notepad.getTextArea().setForeground(Color.BLACK);
        }
    }
}

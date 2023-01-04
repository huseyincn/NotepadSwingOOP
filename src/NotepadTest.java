import org.junit.Test;
import static org.junit.Assert.*;

public class NotepadTest {

    // BAŞLIĞIN değişimini gözlemlemek için yazıldı
    @Test
    public void testStartTimeTitleObserver() throws InterruptedException {
        Notepad notepad = new Notepad();
        TimeTitleObserver timeTitleObserver = new TimeTitleObserver(new UpdateTitleCommand(notepad));
        notepad.setTimeTitleObserver(timeTitleObserver);
        notepad.startTimeTitleObserver(); // bu fonksiyon aslında main ile aynı notepad geliyor mu onu kontrol ediyor
        Thread.sleep(1000);
        assertNotEquals("Java Notepad - ", notepad.getTitle());
    }

    @Test
    public void testGetInstance() { // dark mode 2 aynı nesneyi döndürüyor mu? (Singleton testi)
        Notepad notepad = new Notepad();
        DarkMode darkMode1 = DarkMode.getInstance(notepad);
        DarkMode darkMode2 = DarkMode.getInstance(notepad);
        assertSame(darkMode1, darkMode2);
    }

}

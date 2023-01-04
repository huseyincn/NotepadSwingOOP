import org.junit.Test;
import static org.junit.Assert.*;

public class NotepadTest {


    @Test
    public void testStartTimeTitleObserver() throws InterruptedException {
        Notepad notepad = new Notepad();
        TimeTitleObserver timeTitleObserver = new TimeTitleObserver(new UpdateTitleCommand(notepad));
        notepad.setTimeTitleObserver(timeTitleObserver);
        notepad.startTimeTitleObserver(); // bu fonksiyon aslında main ile aynı notepad geliyor mu onu kontrol ediyor
        Thread.sleep(1000);
        assertNotEquals("Java Notepad - ", notepad.getTitle());
    }


}

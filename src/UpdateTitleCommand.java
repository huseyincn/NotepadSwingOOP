import java.util.Date;
import java.text.SimpleDateFormat;

public class UpdateTitleCommand implements Runnable {

    private Notepad notepad;
    private SimpleDateFormat dateFormat;

    public UpdateTitleCommand(Notepad notepad) {
        this.notepad = notepad;
        this.dateFormat = new SimpleDateFormat("hh:mm:ss");
    }

    public void run() {
        notepad.setTitle("OOP NOTEPAD PROJESİ - " + dateFormat.format(new Date()));
    }
}
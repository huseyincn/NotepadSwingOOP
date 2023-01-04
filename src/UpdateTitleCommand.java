import java.util.Date;
import java.text.SimpleDateFormat;

public class UpdateTitleCommand implements Runnable {

    private Notepad notepad;
    private SimpleDateFormat dateFormat;

    public UpdateTitleCommand(Notepad notepad) {
        this.notepad = notepad;
        this.dateFormat = new SimpleDateFormat("hh:mm:ss");
    }

    // threadde çalışması için runnable yapıldı ilkte hatayla update butonu çağırılıyordu ama main thread swing tarafından
    // kitlendiği için runnable yapılıp threade görev verildi
    public void run() {
        notepad.setTitle("OOP NOTEPAD PROJESİ - " + dateFormat.format(new Date()));
    }
}
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Notepad notepad = NotepadFactory.createNotepad(); // BURASI SETVISION DEDIGINDE MAIN THREADI ALIYOR
        DarkMode darkMode = DarkMode.getInstance(notepad);

        // darkmode time observerin içinde çalışılması için singleton yapıldı fakat eklenemedi
        // singleton örneği olsun diye kendi halinde bırakıldı

        //System.out.println(LocalDateTime.now().getHour());
        if( LocalDateTime.now().getHour()>18 || LocalDateTime.now().getHour()<7 ) // saat 6yı geçmiş veya 7 den önce ise dark mode açık olsun
            darkMode.setEnabled(true);

        //darkMode.setEnabled(true);
        TimeTitleObserver timeTitleObserver = new TimeTitleObserver(new UpdateTitleCommand(notepad));
        notepad.setTimeTitleObserver(timeTitleObserver);
        notepad.startTimeTitleObserver(); // BU ISE EK THREADDE ÇALIŞIYOR tıpkı android retrofit asenkron api call gibi
    }
}

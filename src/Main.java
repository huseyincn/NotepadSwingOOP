public class Main {

    public static void main(String[] args) {
        Notepad notepad = NotepadFactory.createNotepad(); // BURASI SETVISION DEDIGINDE MAIN THREADI ALIYOR
        TimeTitleObserver timeTitleObserver = new TimeTitleObserver(new UpdateTitleCommand(notepad));
        notepad.setTimeTitleObserver(timeTitleObserver);
        notepad.startTimeTitleObserver(); // BU ISE EK THREADDE ÇALIŞIYOR tıpkı android retrofit asenkron api call gibi
    }
}

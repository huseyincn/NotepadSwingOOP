public class Main {

    public static void main(String[] args) {
        Notepad notepad = NotepadFactory.createNotepad();
        TimeTitleObserver timeTitleObserver = new TimeTitleObserver(new UpdateTitleCommand(notepad));
        notepad.setTimeTitleObserver(timeTitleObserver);
        notepad.startTimeTitleObserver();

    }
}

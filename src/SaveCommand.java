public class SaveCommand implements Command {

    private Notepad notepad;

    public SaveCommand(Notepad notepad) {
        this.notepad = notepad;
    }

    public void execute() {
        notepad.save();
    }
}

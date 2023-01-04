public class SaveCommand implements Command {

    private Notepad notepad;

    public SaveCommand(Notepad notepad) {
        this.notepad = notepad;
    }

    public void execute() {
        notepad.save();
    } // sadece save için eklendi direk save yerine save komutu gönderiliyo save komutunun içinden bağımsız çalış demeyi sağlıyor
    // aslında nesnede ne döndüğünü biliyoruz ama save i nesne olarak gönderip kaydet emrini yolluyoruz
}

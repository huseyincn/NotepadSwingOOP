public class NotepadFactory {

    public static Notepad createNotepad() {
        return new Notepad();
    }
} // factory method göstermelik olarak eklendi birden fazla subclassımız yok ama olsaydı alt classlara özelleştirilip
// üretmek için kullanılabilir ilerde darkmode notepad, light mode notepad olursa ve yeni açıldığında yapılırsa
// bu design patternı kullanmak mantıklı olabilir
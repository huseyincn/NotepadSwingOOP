import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class Notepad extends JFrame {

    // Text area
    private JTextArea textArea; // ortaki text yazma bölgesi mobil programlamadaki editText

    // COMMAND YAPISI ICIN KULLANILIYOR
    private SaveCommand saveCommand; // kaydetmek kısmı command design patterndan emir alıyorum

    // COMMAND YAPISI ICIN KULLANILIYOR
    private UpdateTitleCommand updateTitleCommand; // başlığı değiştirme kısmı command design patterndan emir alıyorum
    // ZAMAN ICIN OBSERVER CLASS
    private TimeTitleObserver timeTitleObserver; // zamanı saniye saniye izlemek için observer nesnesi yapıyorum

    // File
    private File currentFile; // kaydedilcek veya açılan dosyayı nesne olarak tutuyorum

    public JTextArea getTextArea() {
        return textArea;
    }

    public Notepad() { // NOTEPAD kısmı internetten alındı basit notepad üzerinde değişiklik yapıldı
        // Set the title and default close operation
        setTitle("OOP NOTEPAD PROJESİ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a text area with the default font
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add the text area to the content pane
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the File menu
        JMenu fileMenu = new JMenu("Dosya");

        // Create the Open menu item
        JMenuItem openMenuItem = new JMenuItem("AÇ");

        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openMenuItem);


        JMenuItem saveAsMenuItem = new JMenuItem("FARKLI KAYDET");
        saveAsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(Notepad.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        saveAs(file);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(Notepad.this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        fileMenu.add(saveAsMenuItem);


        // Create the Save menu item
        JMenuItem saveMenuItem = new JMenuItem("KAYDET");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        fileMenu.add(saveMenuItem);


        JMenuItem exitMenuItem = new JMenuItem("ÇIKIŞ");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        fileMenu.add(exitMenuItem);


        // Add the File menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the window
        setJMenuBar(menuBar);


        // Set the size and show the window
        setSize(800, 600);
        setVisible(true);
    }

    // Bu kod bloğu java swingde gui için dosya açmaya yarıyor .net de openFileDialog gibi
    private void openFile() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try {
                textArea.setText(readFile(currentFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Dosyayı kaydetmek için fonksiyon
    private void saveFile() {
        // Dosya yeni açılmış ise null pointer yani sadece ramde açılmış ama diskte karşılığı olmayabilir
        if (currentFile != null) {
            try {
                writeFile(currentFile, textArea.getText()); // text areada ne yazıyorsa dosyaya kaydediyorum
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JFileChooser fileChooser = new JFileChooser(); // eğer kaydedilme hatası aldıysa yeni dosya olabilir farklı kaydet gibi çalışıyorum
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                try {
                    writeFile(currentFile, textArea.getText()); // diskteki karşılığına yazdırıyorum
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder(); // dosyanın içeriğini okumaya yarayan kod bloğu dosyayı okuyup newline ekliyorum
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }

    // Textareanın içini parametre olarak alıp dosyaya yazıyoruz
    private void writeFile(File file, String text) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file)); // parametre olarak textareanın içeriği geliyor ve dosyaya kaydediyor
        bw.write(text);
        bw.close();
    }

    public void saveAs(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textArea.getText());
        }
    }

    public void exit() {
        System.exit(0);
    }



    /* SİLİNEBİLİR */
    // burası çalışmadığı için referans vermedim boş kod bir önceki versiyonda kullanıldı
    public void setSaveCommand(SaveCommand saveCommand) {
        this.saveCommand = saveCommand;
    }

    // SAVE KOMUTU için çalışıyor
    public void save() {
        saveCommand.execute();
    }

    /* SİLİNEBİLİR */
    public void setUpdateTitleCommand(UpdateTitleCommand updateTitleCommand) {
        this.updateTitleCommand = updateTitleCommand;
    }

    public void setTimeTitleObserver(TimeTitleObserver timeTitleObserver) {
        this.timeTitleObserver = timeTitleObserver;
    }

    public void updateTitle() {
        updateTitleCommand.run();
    }

    // ZAMANI gözlemlemek için çalışıyor
    public void startTimeTitleObserver() {
        timeTitleObserver.update();
    }
}

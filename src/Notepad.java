import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class Notepad extends JFrame {

    // Text area
    private JTextArea textArea;


    private SaveCommand saveCommand;


    // File
    private File currentFile;

    public Notepad() {
        // Set the title and default close operation
        setTitle("Java Notepad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a text area with the default font
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add the text area to the content pane
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the File menu
        JMenu fileMenu = new JMenu("File");

        // Create the Open menu item
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openMenuItem);

        // Create the Save menu item
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        fileMenu.add(saveMenuItem);

        // Add the File menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the window
        setJMenuBar(menuBar);

        // Set the size and show the window
        setSize(800, 600);
        setVisible(true);
    }

    // Open a file
    private void openFile() {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();

        // Show the file chooser and get the user's selection
        int result = fileChooser.showOpenDialog(this);

        // If the user clicked OK (i.e., didn't cancel the operation)
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            currentFile = fileChooser.getSelectedFile();

            // Read the contents of the file and display them in the text area
            try {
                textArea.setText(readFile(currentFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Save a file
    private void saveFile() {
// If a file is currently open
        if (currentFile != null) {
// Save the contents of the text area to the file
            try {
                writeFile(currentFile, textArea.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
// Otherwise, show a file chooser to get the file to save to
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                try {
                    writeFile(currentFile, textArea.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Read the contents of a file and return it as a string
    private String readFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }

    // Write the contents of a string to a file
    private void writeFile(File file, String text) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(text);
        bw.close();
    }

    public void setSaveCommand(SaveCommand saveCommand) {
        this.saveCommand = saveCommand;
    }

    public void save() {
        saveCommand.execute();
    }
}

package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileCompressionApp extends JFrame {

    private final HuffmanCoding codec = new HuffmanCoding();

    public FileCompressionApp() {
        super("File Compression Tool");

        JButton compressButton = new JButton("Compress File");
        compressButton.addActionListener(e -> compressFile());

        JButton decompressButton = new JButton("Decompress File");
        decompressButton.addActionListener(e -> decompressFile());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(new JLabel("Select an operation:"));
        panel.add(compressButton);
        panel.add(decompressButton);

        add(panel);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String readFileContent(File file) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private void compressFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileContent = readFileContent(selectedFile);
            String[] compressedData = codec.encode(fileContent);

            JFileChooser saveFileChooser = new JFileChooser();
            int saveReturnValue = saveFileChooser.showSaveDialog(this);
            if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                File fileToSave = saveFileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));
                    writer.write(compressedData[0]);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void decompressFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileContent = readFileContent(selectedFile);
            String[] decompressedData = codec.decode(fileContent);

            JFileChooser saveFileChooser = new JFileChooser();
            int saveReturnValue = saveFileChooser.showSaveDialog(this);
            if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                File fileToSave = saveFileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));
                    writer.write(decompressedData[0]);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileCompressionApp());
    }
}

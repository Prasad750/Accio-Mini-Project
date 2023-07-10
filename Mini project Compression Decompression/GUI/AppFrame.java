package  GUI;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Compression_Decompression.Compression;
import Compression_Decompression.Decompression;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Dimension;

public class AppFrame extends JFrame implements ActionListener
{

    JButton compressButton;
    JButton deCompressButton;
    Dimension size;


    AppFrame()
    { 
        //getting screen size
        size=new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        
        //creating button for  file compression
        compressButton=new JButton("Select file to compress", null);
        compressButton.setBounds(200,100,200,30);
        compressButton.addActionListener(this);

        //creating button for file Decompression
        deCompressButton=new JButton("Select file to Decompress");
        deCompressButton.setBounds(500, 100, 200, 30);
        deCompressButton.addActionListener(this);

       // creating window
        this.setTitle("File_Compressior_Decompressior");
        this.setLayout(null);
        this.add(compressButton);
        this.add(deCompressButton);
        this.setSize(size);
        this.getContentPane().setBackground(Color.magenta);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==compressButton)
        {
            // for user file selection 
            JFileChooser fileChooser=new JFileChooser(getName(), null);
            int response=fileChooser.showSaveDialog(null);
            if(response==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);

                try
                { //compressing selected  file
                    Compression.method(file);
                }
                catch (Exception ee)
                {
                    JOptionPane.showMessageDialog(null,ee.toString());

                }

            }
        }
        else if(e.getSource()==deCompressButton)
        {
            // for user file selection
            JFileChooser fileChooser=new JFileChooser(getName(), null);
            int response=fileChooser.showSaveDialog(null);
            if(response==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
                try{
                    // decompressing selected file
                    Decompression.method(file);
                }
                catch(Exception ee)
                {
                    JOptionPane.showMessageDialog(null,ee.toString());
                }
            }
        }

    }
}

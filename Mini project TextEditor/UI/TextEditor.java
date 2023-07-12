package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TextEditor implements ActionListener {


    //Initializing obj
    JFrame frame;
    JMenuBar menuBar;
    JMenu file;
    JMenu edit;
    JMenuItem newFile,saveFile,openFile;
    JMenuItem cut,copy,paste,selectAll,closeFile;
    JTextArea textArea;

    TextEditor()
    {
        //creating  frame obj
        frame=new JFrame("Text_Editor ", null);

        //creating  menuBar obj
        menuBar=new JMenuBar();

        //creating TextArea obj
        textArea=new JTextArea();

        //creating menu obj
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //creating menuItem obj

        newFile= new JMenuItem("New_window", 0);
        saveFile=new JMenuItem("Save_File", 0);
        openFile=new JMenuItem("Open_File", 0);

        cut=new JMenuItem("Cut", 0);
        copy=new JMenuItem("Copy", 0);
        paste=new JMenuItem("Paste", 0);
        selectAll=new JMenuItem("Select_All", 0);
        closeFile=new JMenuItem("Close_File", 0);

        // adding ActionListner
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        closeFile.addActionListener(this);


        //adding menuItem in menu
        file.add(newFile);
        file.add(saveFile);
        file.add(openFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(closeFile);


        //adding in menu in menubarr
        menuBar.add(file);
        menuBar.add(edit);

        //adding menuBar in frame
        frame.setJMenuBar(menuBar);

        // creating panel and setting boarder
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0,0));

        //adding textArea to panel
        panel.add(textArea,BorderLayout.CENTER);

        //adding scrolls pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        
        //adding textArea in frame
        frame.add(panel);
       // frame.add(textArea);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setSelectedTextColor(Color.MAGENTA);


        //setting frame dimension
       // frame.setLayout(null);
        frame.setBounds(100, 100, 500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Actionevent  cut
        if(e.getSource()==cut)
        {
            textArea.cut();
        }
        //Actionevent copy
        if(e.getSource()==copy)
        {
            textArea.copy();
        }
        // Action event paste
        if(e.getSource()==paste)
        {
            textArea.paste();
        }
        //Action event Select All
        if(e.getSource()==selectAll)
        {
            textArea.selectAll();
        }
        //Action event close File
        if(e.getSource()==closeFile)
        {
            System.exit(0);
        }
        // Action event open File
        if(e.getSource()==openFile)
        {
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file=fileChooser.getSelectedFile();
                //get file path
                String filePath=file.getPath();

                try{
                    // initialize filrReader

                    FileReader fileReader=new FileReader(filePath);

                    // initializing bufferReader
                    BufferedReader br=new BufferedReader(fileReader);
                    String intermidate="";
                    String output="";

                    while((intermidate=br.readLine())!=null)
                    {
                        output+=intermidate+"\n";
                    }
                    // set output to the textArea
                    textArea.setText(output);
            
                }
                catch(IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }

                
            }
        }
        //Action event Save File
        if(e.getSource()==saveFile)
        {
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showSaveDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    //initializing file writer
                    FileWriter fileWriter=new FileWriter(file);

                    //initializing Buffer writer
                    BufferedWriter bw=new BufferedWriter(fileWriter);

                    // write content of textArea in file
                    textArea.write(bw);
                    bw.close();



                }
                catch(IOException ee)
                {
                    ee.printStackTrace();
                }

            }


        }
        //Action event new File
        if(e.getSource()==newFile)
        {
            TextEditor te=new TextEditor();
        }

    }

    public static void main(String[] args) {
        TextEditor te=new TextEditor();
    }
    
}



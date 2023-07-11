package Compression_Decompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class Decompression {
    public static void main(String[] args) throws IOException {
        File path=new File("");
        method(path);
        
    }

    public static void method(File file) throws IOException{
        String fileDirectory=file.getParent();
        
        FileInputStream fis=new FileInputStream(file); // reding compress file
        GZIPInputStream gzip=new GZIPInputStream(fis); //for reading .gz file
        FileOutputStream fos=new FileOutputStream(fileDirectory+"decompress"); // writing file

        byte[] buffer=new byte[1024];
        int len;

        while((len=gzip.read(buffer))!=-1)
        {
            fos.write(buffer,0,len); // writing data in deCompress file
        }

        fos.close();
        gzip.close();
        fis.close();
    }
}

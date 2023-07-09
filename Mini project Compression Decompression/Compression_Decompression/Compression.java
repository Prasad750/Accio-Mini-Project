package Compression_Decompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Compression {
    
    public static void main(String[] args) throws IOException {
         File path=new File(" ");
         method(path);
    }

   public static void method(File file) throws IOException
   {
      String fileDirectory=file.getParent();

      FileInputStream fis=new FileInputStream(file);  // reading file from path
      FileOutputStream fos=new FileOutputStream(fileDirectory+"compress.gz"); //writing .gz file
      GZIPOutputStream gzip=new GZIPOutputStream(fos); 

      byte[] buffer=new byte[1024]; //creating byte arry to store upto 1024 byte
      int len;
      
      while((len=fis.read(buffer))!=-1)
      {
        gzip.write(buffer,0,len);
      }

      gzip.close();
      fos.close();
      fis.close();
   }
  
   
}

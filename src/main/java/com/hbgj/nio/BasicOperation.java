package com.hbgj.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * java nio 的基础使用
 *
 *
 *
 *
 */
public class BasicOperation {



    public static void main(String[] args){

          copyFile();

    }



    public static void copyFile(){

        FileInputStream fin=null;
        FileOutputStream fout=null;
        FileChannel    fcInput=null;
        FileChannel    fcOutput=null;

       try{

           fin=new FileInputStream(
                   "C:\\Users\\24590\\IdeaProjects\\MutipleThreadStudy\\src\\main\\resources\\log4j.properties");

           fcInput=fin.getChannel();


           ByteBuffer buffer= ByteBuffer.allocate(1024);

           fout=new FileOutputStream(
                   "C:\\Users\\24590\\IdeaProjects\\MutipleThreadStudy" +
                           "\\src\\main\\resources\\log4j.properties2");
           fcOutput=fout.getChannel();


           

           while(true) {

               int result = fcInput.read(buffer);
               if(result==-1)break;
               // flip方法让缓冲区可以将新读入的数据写入另一个通道
               buffer.flip();
               fcOutput.write(buffer);
               // clear方法重设缓冲区，使它可以接受读入的数据
               buffer.clear();
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               if(fin!=null)
                   fin.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
           try {
               if(fout!=null)
                   fout.close();
           } catch (IOException e) {
               e.printStackTrace();
           }


           try {
               if(fcInput!=null)
               fcInput.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
           try {
               if(fcOutput!=null)
                   fcOutput.close();
           } catch (IOException e) {
               e.printStackTrace();
           }

       }



    }
}

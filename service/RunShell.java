
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class RunShell { 
  public static void main(String[] args){ 
    String fileName="test1.tex";
    try{
      FileWriter writer=new FileWriter(fileName);

      writer.write("\\documentclass[12pt]{article}\n\\begin{document}\nHello world!$Hello world!$ %math mode \n \\end{document}");
      writer.close();
    }
    catch (IOException e)
                 {
              e.printStackTrace();
    }


    try { 
      String shpath="xelatex test1.tex"; 
      Process ps = Runtime.getRuntime().exec(shpath); 
      ps.waitFor(); 
      BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream())); 
      StringBuffer sb = new StringBuffer(); 
      String line; 
      while ((line = br.readLine()) != null) { 
        System.out.println(line); 
      } 
    } catch (Exception e) { 
      e.printStackTrace(); 
    } 
    try {
    Process ps = Runtime.getRuntime().exec("open test1.pdf"); 
      ps.waitFor(); 
    }catch (Exception e) { 
      e.printStackTrace(); 
    } 
  } 
} 

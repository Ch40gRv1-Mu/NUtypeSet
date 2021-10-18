
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.util.*;

public class RunShell { 

  public static void main(String[] args){ 
    try { 
      
      String shpath="sh /Users/mcr/Desktop/NUS/orbital/orbital_github/service/note.sh"; 
      Process ps = Runtime.getRuntime().exec(shpath); 
      ps.waitFor(); 
  
      BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream())); 
      StringBuffer sb = new StringBuffer(); 
      String line; 
      while ((line = br.readLine()) != null) { 
        sb.append(line).append("\n"); 
      } 
      String result = sb.toString(); 
      System.out.println(result); 
      }  
    catch (Exception e) { 
      e.printStackTrace(); 
      } 
  } 
} 

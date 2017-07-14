/**
It has now come to my attention that Processing does not support any text in svg files,
which is sort of some b.s. So I have to use an external java library to accomplish this task. 
Namely the package Batik. 
**/
import java.io.*;


PImage img;
PShape shp;

void setup(){
   TeXObject t = new TeXObject("");
}



class TeXObject{
  
  // Configuration
  String TeXTemplate = "/Users/samuelpx2016/Desktop/Math/math-programming/Processing/AnimationEngine/TeXObjects/TEX_TEMPLATE.tex";
  String dir = "/Users/samuelpx2016/Desktop/Math/math-programming/Processing/AnimationEngine/TeXObjects";
  String dviFile = TeXTemplate.replace(".tex",".dvi");
  PShape img;
  
  // Variables
  String code;
  
  
  public TeXObject(String code){
    this.code = code;
    //this.insertCode();
    //this.convertTeXToDVI();
    
    this.convertDVIToSVG();
  }
  
  public void getFileDirectory(){
      
  }
  
  /**
  
  **/
  public void insertCode(){
    
  }
  
  
  public void convertTeXToDVI(){
    String[] cmd = {"/Library/TeX/texbin/latex", "-output-directory=" + dir,
                    TeXTemplate};
    Runtime rt = Runtime.getRuntime(); 
    try{
      // Passes the following commands to the terminal
      Process p = rt.exec(cmd); // Compile LaTeX File to DVI
      
      // The following code prints whats on the commandline to the java
      // command window. 
      InputStream is = p.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      while (true)
      {
          String s = br.readLine();
          if (s == null)
              break;
          System.out.println(s);
      }
      
    } catch (IOException e) {
      e.printStackTrace(); // If there is an error print where it happened
    }
  }
  
  
  public void convertDVIToSVG(){
    System.out.println(dviFile);
    System.out.println("Converting DVI to SVG...");
    String[] cmd = {"/Library/TeX/texbin/dvisvgm", 
                    dviFile};
    Runtime rt = Runtime.getRuntime(); 
    try{
      // Passes the following commands to the terminal
      Process p = rt.exec(cmd); // Compile LaTeX File to DVI
      
      // The following code prints whats on the commandline to the java
      // command window. 
      InputStream is = p.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      while (true)
      {
          String s = br.readLine();
          if (s == null)
              break;
          System.out.println(s);
      }
      
    } catch (IOException e) {
      e.printStackTrace(); // If there is an error print where it happened
    }
    
    System.out.println("Finished");
  }
  
  public void generatePNGImage(){
      
  }
  
  /**
  
  **/
  public void cleanUp(){
    
  }
  
  public void display(){
    
  }
  
  public String getCode(){
     return code; 
  }
  
}
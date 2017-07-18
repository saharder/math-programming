/**
It has now come to my attention that Processing does not support any text in svg files,
which is sort of some b.s. So I have to use an external java library to accomplish this task. 
Namely the package Batik. 
**/
import java.io.*;
import java.net.URL;


PImage img;
PShape shp;

float x = width/2;
float y = height/2;

TeXObject t;

void setup(){
  size(1000,500);  
  pixelDensity(2);
  img = loadImage("TEX_TEMPLATE1.png");
  t = new TeXObject("$\\alpha, \\beta, \\gamma, \\delta$");
  background(255);  
  t.scale(0.25);
}

void draw(){
  background(255);
  x = lerp(x, mouseX, 0.2);
  y = lerp(y, mouseY, 0.2);
  t.display(x,y);
}



class TeXObject{
  
  // Configuration
  URL location;
  String TeXTemplate = "/Users/samuelpx2016/Desktop/Math/math-programming/Processing/AnimationEngine/TeXObjects/TEX_TEMPLATE.tex";
  String dviFile = TeXTemplate.replace(".tex",".dvi");
  String pngFile = TeXTemplate.replace(".tex",".png");
  // This is the folder with the java file. 
  String dir = "/Users/samuelpx2016/Desktop/Math/math-programming/Processing/AnimationEngine/TeXObjects";
  
  // This string of text tells the computer where to insert the LaTeX code. 
  String TEX_TO_REPLACE = "YourTextHere";
  
  // Variables
  String code; // The LaTeX you want rendered
  PImage img; // the actual TeX rendered
  float picWidth, picHeight;
  
  
  public TeXObject(String code){
    this.code = code;
    this.insertCode();
    this.convertTeXToDVI();
    this.convertDVIToPNG();
    
    //this.convertDVIToSVG();
  }
  
  /** work in progress... Until then, manually configure this class to know the location of itself. 
  public void getFileDirectory(){
    File f = new File("Tester");
    System.out.println(f.getAbsolutePath());
  }
  
  /**
  This method inserts the users LaTeX code into the LaTeX Template. 
  It is also used to restore the Template after the png has been created. 
  **/
  public void insertCode(){
      // The name of the file to open.
        String fileName = TeXTemplate;

        // This will reference one line at a time
        String line = null;

        try {
          // writes text files in default encoding  
          FileWriter fileWriter = new FileWriter("temp.tex");
            
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = 
                new BufferedWriter(fileWriter);

            while((line = bufferedReader.readLine()) != null) {
              if(line.equals(TEX_TO_REPLACE)){
                 bufferedWriter.write(code);
              } else {
                bufferedWriter.write(line);  
              }
              bufferedWriter.newLine();
            }   

            // Always close files.
            bufferedReader.close();    
            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
  }
  
  /**
  This class takes a .tex file and converts it to a .dvi file. 
  This is an intermediary step in converting to a png. It does this
  by passing a series of ocmmands to the commandline. 
  **/
  public void convertTeXToDVI(){
    String[] cmd = {"/Library/TeX/texbin/latex", "temp.tex"};
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
  
  public void convertDVIToPNG(){
    System.out.println(dviFile);
    System.out.println("Converting DVI to PNG...");
    String[] cmd = {"/Library/TeX/texbin/dvipng","-D1200", "-bg", "Transparent", 
                    "temp.dvi"};
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
      
      img = loadImage("temp1.png");
      this.picWidth = img.width;
      this.picHeight = img.height;
      
    } catch (IOException e) {
      e.printStackTrace(); // If there is an error print where it happened
    }
    
    System.out.println("Finished Conversion to PNG");      
  }
  
  /**
  This method cleans up all the files which are made 
  while generating the png. This includes some stray dvi's, various latex files, 
  as well as the png itself (once we have loaded it into the program we no longer
  need it to exist outside of it).
  **/
  public void cleanUp(){
    
  }
  
  /** 
  This method displays the rendered TeX on the canvas
  **/
  public void display(float x, float y){
    image(img, x,y, picWidth, picHeight);
  }
  
  /**
  This method scales the image
  **/
  public void scale(float scaleFactor){
    picWidth = picWidth*scaleFactor;
    picHeight = picHeight*scaleFactor;
  }
  
  
  
  /** 
  This method returns the LaTeX sourcecode. 
  **/
  public String getCode(){
     return code; 
  }
  
}
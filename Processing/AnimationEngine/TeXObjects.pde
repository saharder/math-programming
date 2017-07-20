import java.io.*;

/**
 It has now come to my attention that Processing does not support any text in svg files,
 which is sort of some b.s. So I have to use an external java library to accomplish this task. 
 Namely the package Batik. 
 **/
class TeXObject {

  // Configuration
  String TeXTemplate = "/Users/samuelpx2016/Desktop/Math/math-programming/Processing/AnimationEngine/TEX_TEMPLATE.tex";
  String dviFile = TeXTemplate.replace(".tex", ".dvi");
  String pngFile = TeXTemplate.replace(".tex", ".png");
  // This is the folder with the java file. 
  String dir = "/Users/samuelpx2016/Desktop/Math/math-programming/Processing/AnimationEngine/TeXObjects";

  // This string of text tells the computer where to insert the LaTeX code. 
  String TEX_TO_REPLACE = "YourTextHere";

  // This will determine how high quality you want your TeXObject
  String pixDensity = "600";
  float alpha = 255;

  // Variables
  String code; // The LaTeX you want rendered
  PImage img; // the actual TeX rendered
  float picWidth, picHeight;

  // Location Variables
  float x, y;

  public TeXObject(String code) {
    this.code = code;
    this.insertCode();
    this.convertTeXToDVI();
    this.convertDVIToPNG();

    this.scale(0.3);

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
  public void insertCode() {
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

      while ((line = bufferedReader.readLine()) != null) {
        if (line.equals(TEX_TO_REPLACE)) {
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
  public void convertTeXToDVI() {
    String[] cmd = {"/Library/TeX/texbin/pdflatex", "-output-format=dvi", "temp.tex"};
    Runtime rt = Runtime.getRuntime(); 
    try {
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
    } 
    catch (IOException e) {
      e.printStackTrace(); // If there is an error print where it happened
    }
  }

  public void convertDVIToPNG() {
    System.out.println(dviFile);
    System.out.println("Converting DVI to PNG...");
    String[] cmd = {"/Library/TeX/texbin/dvipng", "-D", pixDensity, "-bg", "Transparent", 
      "temp.dvi"};
    Runtime rt = Runtime.getRuntime(); 
    try {
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
    } 
    catch (IOException e) {
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
  public void cleanUp() {
  }

  /** 
   This method displays the rendered TeX on the canvas
   **/
  public void display(float x, float y) {
    // Reset the location
    this.x = x;
    this.y = y;

    tint(255, alpha); // we want the tint to affect the rectangle too

    float frameWidth = 10; // This determines how thick the bounding rectangle is
    fill(255, 150); // We don't want the back rectangle to be super dark
    stroke(255); // make that background the same color as the fill

    // draw box behind
    rect(x - frameWidth, y - frameWidth, picWidth+ 2*frameWidth, picHeight + 2*frameWidth, 5, 5, 5, 5);

    // draw the TeX
    image(img, x, y, picWidth, picHeight);
  }

  /**
   This method scales the image
   **/
  public void scale(float scaleFactor) {
    picWidth = picWidth*scaleFactor;
    picHeight = picHeight*scaleFactor;
  }

  /**
   This method changes the opacity of the image. 
   display() must be called again for it to take effect. 
   **/
  public void setOpacity(int alpha) {
    this.alpha = alpha;
  }

  public float getWidth() {
    return picWidth;
  }

  public float getHeight() {
    return picHeight;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  /** 
   This method returns the LaTeX sourcecode. 
   **/
  public String getCode() {
    return code;
  }
}
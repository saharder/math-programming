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
  String pixDensity = "450";
  
  // Default opacity (on scale of 0-255) of TeXObject
  float alpha = 255;

  // Variables
  String code; // The LaTeX you want rendered
  PImage img; // the actual TeX rendered
  float picWidth, picHeight; // These store the height and width, in pixels, of image

  // Location Variables
  float x, y;

  // Scale Factor
  float scaleFactor = Constants.SCALE_FACTOR;

  // width of bounding box
  float frameWidth = Constants.TEX_FRAME_WIDTH;

  // color of tex object
  int texColor;

  // Toggles background box
  boolean showBackgroundBox = true;

  public TeXObject(String code) {
    this(code, Constants.WHITE);
  }

  public TeXObject(String code, int fontColor){
    // First we want to save the code to a string
    this.code = code;
    // Then insert the code in the template
    this.insertCode();
    // Convert the template to dvi
    this.convertTeXToDVI();
    // And finally convert the dvi to a png we can use
    this.convertDVIToPNG();
    
    // make the text white (MAYBE I WANT TO CHANGE THIS TO THE DISPLAY PORTION)
    //this.setColor(Constants.WHITE); // change this to coloring?

    this.setColor(fontColor);
    
    // The reason for scaling down the rendered TeX is that we render it 
    // at a very high resolution, so by defualt processing will display it
    // quite large. This gets it down to a reasonable size. 
    this.scale(Constants.TEX_SCALE_FACTOR);
  }

  // This method inserts the TeX STring into our TeX Template
  // so it is ready to be compiled and rendered. 
  public void insertCode() {
    // This gives the file name for our TeXTemplate.tex file
    String fileName = TeXTemplate;

    // This is a running reference of each line in the document.
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
      // Same goes for FileWriter and BufferedWriter
      BufferedWriter bufferedWriter = 
        new BufferedWriter(fileWriter);

      // While our current line contains something
      while ((line = bufferedReader.readLine()) != null) {
        // if we have found the TeX to replace we will
        // substitute in our desired code. Otherwise we don't do anything.
        if (line.equals(TEX_TO_REPLACE)) {
          bufferedWriter.write(code);
        } else {
          bufferedWriter.write(line);
        }
        // This allows the written document to be readable (this is mainly for looking for bugs). 
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
      // Or:
      // ex.printStackTrace();
    }
  }

  /**
   This class takes a .tex file and converts it to a .dvi file. 
   This is an intermediary step in converting to a png. This method makes use
   of the system's LaTeX distribution. Specifically the 'latex' compiler. 
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

  /**
  *This method converts the dvi file generated by convertTeXToDVI() into a 
  *png. It makes use of the command line tool called 'dvipng', which should come
  *with most latex distributions. 
  **/
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

  public void makeBackgroundBox(){
    //tint(255, alpha); // this can be included if we want a fade in effect for our text

    fill(0, map(alpha, 0, 255, 0, 150)); // We don't want the back rectangle to be super dark
    noStroke();

    // draw box behind
    rect(x - frameWidth, y - frameWidth, picWidth+ 2*frameWidth, picHeight + 2*frameWidth, 5, 5, 5, 5);
  }

  /** 
   * This method displays the rendered TeX on the canvas
   * the x and y are specified relative to the canvas's grid. 
   **/
  public void display(float x, float y) {
    // Reset the location (is this really necessary?)
    this.x = x;
    this.y = y;

    tint(255,alpha);
    if(showBackgroundBox){
    this.makeBackgroundBox();
    }
    // draw the TeX

    image(img, x, y, picWidth, picHeight);
    tint(255,255);
  }

  public void displayCenter(float x, float y){
    display(x - picWidth/2.0, y - picHeight/2.0);
  }

  //
  // This is a better display function for the tex object
  //
  public void displayCoordinate(float x, float y){
    pushMatrix();
    translate(width/2, height/2);
    this.displayCenter(x*scaleFactor, -y * scaleFactor);
    popMatrix();
  }
  
  // Sets the color of the text (default is white)
  public void setColor(int newColor){
    img.loadPixels();
    for(int i = 0; i < img.width*img.height; i++){
        if(alpha(img.pixels[i]) != 0){ // ignore transparent pixels
          img.pixels[i] = color(red(newColor), green(newColor), blue(newColor), alpha(img.pixels[i]));//color( newColor, alpha(img.pixels[i]));
        }
    }
    img.updatePixels();
  }

  // Toggles Background BOx
  public void hideBackgroundBox(){
    showBackgroundBox = false;
  }

  public void showBackgroundBox(){
    showBackgroundBox = true;
  }


  /**
  *This method scales the image
   **/
  public void scale(float scl) {
    picWidth = picWidth*scl;
    picHeight = picHeight*scl;
  }

  /**
   *This method changes the opacity of the image. 
   *display() must be called again for it to take effect. 
   **/
   
  public void setOpacity(float alpha) {
    this.alpha = alpha;
  }

  public float getWidth() {
    return picWidth;
  }

  // Scales the picture proportionally to some width
  public void setWidth(float newWidth) {
    img.resize((int)newWidth, 0);
    picWidth = img.width;
    picHeight = img.height;
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
   * This method returns the LaTeX sourcecode. 
   **/
  public String getCode() {
    return code;
  }
}
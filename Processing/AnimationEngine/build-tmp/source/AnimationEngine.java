import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.io.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AnimationEngine extends PApplet {

float[] xValues, taylor1, taylor2, taylor3, taylor4, taylor5, taylor6, taylor7, taylor8, yValues, sinValues;

Plot p, sinPlot;

// g is a standard grid
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));

float t = 0; // used for moving animations along

int start,end; // These will track which animations we are stransitioning between

// This will be our title
TeXObject TeX;
float[][] taylor;

// We will use this boolean to toggle animations
boolean animate = false;

public void setup() {
  
  
  background(0);

  g.setColor(255,255,255);
  g.display();
  
  TeX = new TeXObject("Taylor Approximations of $\\sin(x)$");

  xValues = range(-10, 10, 0.1f);

  taylor = new float[7][xValues.length];

  sinValues = new float[xValues.length];

  float x;
  for (int i = 0; i < xValues.length; i++) {
    x = xValues[i];
    sinValues[i] = sin(x);
    taylor[0][i] = x;
    taylor[1][i] = x - pow(x, 3)/6;
    taylor[2][i] = x - pow(x, 3)/6 + pow(x, 5)/120;
    taylor[3][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040;
    taylor[4][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880;
    taylor[5][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880 - pow(x, 11)/39916800;
    taylor[6][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880 - pow(x, 11)/39916800 + pow(x, 13)/6227020800.0f;
  }

  p = new Plot(xValues, taylor[0], 0, 255, 255);
  sinPlot = new Plot(xValues, sinValues,0, 255, 0);
  sinPlot.display();

  start = -1;
  end = 0;

  println("Finished setup.");
}

public void draw() {
  // Make the background black, display the grid, display the sin plot
  background(0);
  g.display();
  sinPlot.display();

  if(animate == true){ 
    if(t == 50){
      animate = false;
    }
    println(t/50);
    p = new Plot(xValues,lerpArray(taylor[start], taylor[end], t/50.0f), 0, 255, 255);
    t += 1;
    t = t % 51;
  }
  p.display();
}

public void keyPressed(){
  // A left key press should revert the animation to a previous state
  if(keyCode == RIGHT){
      animate = true;
      start += 1;
      end += 1;
      println("LEFT");
  }
  // A right key press should push the animation to its next state
  else if(keyCode == LEFT){
      animate = true;

      println("RIGHT");
      int temp = start;
      start = end;
      end = temp;
  }
}

/**
 * This is an experiment in using classes for animations. 
 **/
class TeXObjectAnimation {

  TeXObject TeX;
  float imgWidth;
  float alpha; // opacity on scale of 0 to 255
  float x, y; // Location on screen

  public TeXObjectAnimation(TeXObject t) {
    this(t, width/2, height/2);
  }

  public TeXObjectAnimation(TeXObject t, float x, float y) {
    TeX = t;
    imgWidth = t.getWidth();
    this.x = x;
    this.y = y;
  }

  public void fadeIn(int startFrame, int duration) {
    // Don't do anything until we hit our startFrame 
    if (frameCount < startFrame) {
      return;
    }
    // If we have hit our startFrame, then lets get to work:'
    alpha = ((float)(frameCount - startFrame))/((float)duration) * 255;
    System.out.println(alpha);
    TeX.setOpacity(alpha);
    TeX.display(x, y);
  }
  
  // This is buggy. Not quite sure what's wrong. Error thrown is:
  // "Width(2) and height (0) cannot be <= 0". Will investigate later.
  public void growIn(int startFrame, int duration) {
    if (frameCount <= startFrame) {
      return;
    } else if (frameCount > startFrame && frameCount < startFrame + duration) {
      // If we have hit our startFrame, then lets get to work:'
      float newWidth = ((float)(frameCount - startFrame))/((float)duration) * imgWidth;
      System.out.println(newWidth);
      if (newWidth >= 1) {
        TeX.setWidth(newWidth);
        TeX.display(x, y);
      }
    } else {
      TeX.display(x, y);
    }
  }

  public void fadeOut(int startFrame, int duration) {
  }
}

class PlotAnimation{ 
  boolean isAnimating = false;

  
}
/** 
This class allows us to draw points
**/
class Point{
  float x;
  float y;
  float radius = 5.0f;
  float scaleFactor = Constants.SCALE_FACTOR;
  
  public Point(float x, float y){
      this.x = x;
      this.y = y;
  }
  
  public void set(float x, float y){
      this.x = x;
      this.y = y;
  }
  
  public void display(){
    pushMatrix();
    translate(width/2, height/2);
    fill(0);
    ellipse(scaleFactor * x, scaleFactor* -y,radius,radius);
    popMatrix();
  }
  
}


/**
This class provides methods for drawing lines. 
Still a work in progress. 
**/
class Line{
  float scaleFactor = Constants.SCALE_FACTOR;  // Defaults to 50.00
  float rVal, gVal, bVal;
  float thickness;
  boolean dashed = false;
  
  PVector start; // Starting point 
  PVector end; // Ending point
  
  
  public Line(float x1, float y1, float x2, float y2){
    start = new PVector(x1,-y1);
    end = new PVector(x2,-y2);
    
     // scaleFactor defaults to 50.00 
     // This means that one unit of length corresponds to fifty pixels
     // pixelWeight defaults to 2.00, i.e. all lines are 2 pixels wide
     thickness = 1.00f;
     
    // scale the line
    start.mult(scaleFactor);
    end.mult(scaleFactor);
  }
  
  public Line(float x1, float y1, float x2, float y2, float rVal, float gVal, float bVal){
    this(x1, y1, x2, y2);
    this.rVal = rVal;
    this.gVal = gVal;
    this.bVal = bVal;
  }
  
  // Draws the line on the canvas
  public void display(){
     strokeWeight(thickness); // sets thickness of lines
     stroke(rVal,gVal,bVal);
     pushMatrix(); // start transformation
     
     translate(width/2,height/2); // moves origin to center of screen
     line(start.x, start.y, end.x, end.y); // draws the line
     
     popMatrix(); // end transformation
  }
  
  // Returns the a PVector at the midPoint of 
  public PVector getMidpoint(){
      return new PVector((start.x + end.x)/2, (start.y + end.y)/2);
  }
  
  public void setScale(float scaleFactor){
     // Normalize the vector
     
     // set new scaleFactor and multiply
     this.scaleFactor = scaleFactor;  
  }
  
  // set pixelWeight
  public void setThickness(float thickness){
     this.thickness = thickness;
  }
  
  public void set(float x1, float y1, float x2, float y2){
    start.set(x1,y1);
    end.set(x2,y2);
  }
}

/**
This class builds on the PVector class
**/
class Vector2D{
  PVector vector;
  Line body;
  Point endPoint;
  
  public Vector2D(PVector vector){
     this.vector = vector;
     endPoint = new Point(vector.x, vector.y);
     body = new Line(0,0,vector.x,vector.y);
  }
  
  public Vector2D(float x, float y){
     vector = new PVector(x,y); 
     endPoint = new Point(x,y);
     body = new Line(0,0,x,y);
  }
  
  public void display(){
    // Draw the body of the vector
    body.display();
    // Draw endpoint
    endPoint.display();
  }
  
  public void set(float x, float y){
    vector.set(x,y);  
    endPoint.set(x,y);
    body.set(0,0,x,y);
  }
}

class Matrix{ 
  private int cols;
  private int rows;
  private float[][] array;
  
  public Matrix(float[][] array){
     this.array = array;
     cols = array[0].length;
     rows = array.length;
  }
  
  public int getRows(){
    return rows;
  }
  
  public int getCols(){
    return cols; 
  }
}



class Circle{
   float rad, x, y;
   float scaleFactor = Constants.SCALE_FACTOR;
   
   public Circle(float x, float y, float rad){
      this.rad = rad; 
      this.x = x;
      this.y = y;
   }
   
   public void display(){
     strokeWeight(1);
     stroke(0);
     pushMatrix(); // start transformation
     
     translate(width/2,height/2); // moves origin to center of screen
     ellipse(x*scaleFactor, -y*scaleFactor, 2*scaleFactor*rad, 2*scaleFactor*rad);
     
     popMatrix(); // end transformation
   }
}
/**
 Provides methods for plotting a set of points, as well as connecting them
 Has issues if the domain (the x values) do not always yield y values, specifically
 with the sqrt function and negative values. I'm not quite sure how to address this as of now. 
 **/
class Plot {
  // These contain the main data for our curve
  float[] xCoords;
  float[] yCoords;

  // Self explanatory
  float pointWidth = 6.0f;
  float curveWidth = 1.0f;

  boolean isSmooth = true; // defaults to smooth plot

  // This value should not be changed to maintain
  // consistency between different classes. 
  float scaleFactor = Constants.SCALE_FACTOR;

  // These store the color of the curve
  int c;

  // This is the shape that we display on the screen:
  PShape curve;

  public Plot(float[] xCoords, float[] yCoords) {
    this(xCoords, yCoords, 255,255,255);
  }

  public Plot(float[] xCoords, float[] yCoords, int rVal, int gVal, int bVal) {
    c = color(rVal, gVal, bVal);
    if (xCoords.length != yCoords.length) {
      System.out.println("Not equal number of x and y coordinates"); 
      return;
    }
    this.xCoords = xCoords;
    this.yCoords = yCoords;
    this.generateCurve();
  }

  public void display() {
    if (isSmooth) {
      displaySmooth();
    } else {
      displayNoSmooth(); // displays only points, does not connect them
    }
  }

  public void generateCurve() {
    curve = createShape(); // Instantiates the curve
    curve.beginShape();
    curve.noFill();
    curve.stroke(c);
    curve.strokeWeight(curveWidth); // Sets the width of the curve 
    //curve.vertex(scaleFactor*xCoords[0], -1 * scaleFactor * yCoords[0]);
    for (int i = 0; i < xCoords.length; i++) {
      curve.vertex(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
    }
    curve.endShape();
  }

  public void displaySmooth() {
    pushMatrix();
    translate(width/2, height/2);
    shape(curve);
    popMatrix();
  }

  public void displayNoSmooth() {
    // Plot each point
    strokeWeight(pointWidth);
    pushMatrix();
    translate(width/2, height/2);
    for (int i = 0; i < xCoords.length; i++) {
      point(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
    }
    popMatrix();
  }
}

class Plot3D {
  float[][] surface;

  public Plot3D(float[][] surface) {
    this.surface = surface;
  }

  public void display() {
    pushMatrix();
    rotateX(PI/4);

    for (int y = 0; y < surface.length-1; y++) {
      for (int x = 0; x < surface[0].length-1; x++) {
        fill(255, 0, 0);
        stroke(255, 0, 0);
        beginShape(QUAD_STRIP);
        vertex(x, y, surface[x][y]);
        vertex(x + 1, y, surface[x+1][y+1]);
        vertex(x, y + 1, surface[x][y+1]);
        vertex(x + 1, y + 1, surface[x+1][y+1]);
        endShape();
      }
    }
    popMatrix();
  }
}


class PolarPlot {
  float[] rCoords;
  float[] tCoords;
  float pointWidth = 6.0f;
  float curveWidth = 1.0f;
  boolean isSmooth = true; // defaults to smooth plot
  float scaleFactor = Constants.SCALE_FACTOR;
  int len;

  public PolarPlot(float[] rCoords, float[] tCoords) {
    if (rCoords.length != tCoords.length) {
      System.out.println("Not equal number of x and y coordinates"); 
      return;
    }
    this.rCoords = rCoords;
    this.tCoords = tCoords;
    len = rCoords.length;
  }

  public void display() {
    strokeWeight(curveWidth);
    noFill();
    pushMatrix();
    translate(width/2, height/2);
    beginShape();
    curveVertex(scaleFactor*rCoords[0]*cos(tCoords[0]), 
      -1 * scaleFactor * rCoords[0] * sin(tCoords[0]));
    for (int i = 0; i < len; i++) {
      float x = rCoords[i]*cos(tCoords[i]);
      float y = rCoords[i]*sin(tCoords[i]);
      curveVertex(scaleFactor * x, -1 * scaleFactor * y);
    }
    curveVertex(scaleFactor*rCoords[len-1]*cos(tCoords[len-1]), 
      -1 * scaleFactor * rCoords[len-1] * sin(tCoords[len-1]));
    endShape();
    popMatrix();
  }
}


class Axes {

  float scaleFactor = Constants.SCALE_FACTOR;
  Line xAxis;
  Line yAxis;
  float xStep, yStep;
  float xMin, xMax, yMin, yMax;
  float tickHeight = 0.1f;

  public Axes(float xMin, float xMax, float yMin, float yMax, float xStep, float yStep) {
    xAxis = new Line(xMin, 0, xMax, 0);
    yAxis = new Line(0, yMin, 0, yMax);
    this.xStep = xStep;
    this.yStep = yStep;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }

  public void display() {
    xAxis.display();
    yAxis.display();
    drawTicks();
  }

  private void drawTicks() {
    int xTickMin = (int)(xMin/xStep);
    int xTickMax = (int)(xMax/xStep);
    for (int i = xTickMin; i <= xTickMax; i++) {
      Line tick = new Line(i*xStep, -tickHeight/2, i*xStep, tickHeight/2);
      tick.display();
    }

    int yTickMin = (int)(yMin/yStep);
    int yTickMax = (int)(yMax/yStep);
    for (int i = yTickMin; i <= yTickMax; i++) {
      Line tick = new Line(-tickHeight/2, i*yStep, tickHeight/2, i*yStep);
      tick.display();
    }
  }
}

class Grid {
  PVector iVec, jVec;
  float xMin, xMax, yMin, yMax;
  float axesPixWeight = 2.00f;
  float pixWeight = 1.00f;
  int rVal, gVal, bVal;


  public Grid(PVector i, PVector j) {
    this(i, j, -10, 10, -10, 10);
  }

  public Grid(PVector i, PVector j, float xMin, float xMax, float yMin, float yMax) {
    this.iVec = i;
    this.jVec = j;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }
  
  public void setColor(int r, int g, int b){
    rVal = r;
    gVal = g;
    bVal = b;
  }

  public void newBasis(PVector newI, PVector newJ) {
    iVec = newI;
    jVec = newJ;
  }

  public void display() {
    
    float iX = iVec.x;
    float iY = iVec.y;
    float jX = jVec.x;
    float jY = jVec.y;

    for (int k = (int)xMin; k <= xMax; k++) {
      Line l = new Line(k*iX + (int)yMin*jX, k*iY + (int)yMin*jY, 
        k*iX + (int)yMax*jX, k*iY + (int)yMax*jY, rVal, gVal, bVal); 
      if (k == 0) { // If this is the y axis
        l.setThickness(axesPixWeight); // thicken it some
      }
      l.display();
    }

    for (int k = (int)yMin; k <= yMax; k++) {
      Line l = new Line(k*jX + (int)xMin*iX, k*jY + (int)xMin*iY, 
        k*jX + (int)xMax*iX, k*jY + (int)xMax*iY, rVal, gVal, bVal);
      if (k == 0) { // if this is the x axis
        l.setThickness(axesPixWeight); // thicken it some
      }
      l.display();
    }
  }

  public void apply(float[][] matrix) {
    if (matrix.length != 2 || matrix[0].length != 2) {
      System.out.println("Error: Matrix must be 2x2");
      return;
    }
    iVec = new PVector(iVec.x * matrix[0][0] + iVec.y * matrix[1][0], iVec.x * matrix[0][1] + iVec.y * matrix[1][1]);
    jVec = new PVector(jVec.x * matrix[0][0] + jVec.y * matrix[1][0], jVec.x * matrix[0][1] + jVec.y * matrix[1][1]);
  }

  public void rotate(float theta) {
    theta = radians(theta);
    iVec = new PVector(iVec.x * cos(theta) + iVec.y * -sin(theta), iVec.x * sin(theta) + iVec.y * cos(theta));
    jVec = new PVector(jVec.x * cos(theta) + jVec.y * -sin(theta), jVec.x * sin(theta) + jVec.y * cos(theta));
  }

  public String toString() {
    return "i Vector: " + iVec.x + ", " + iVec.y + " jVector: " + jVec.x + ", " + jVec.y;
  }
}

public float[] range(float min, float max, float step) {
  int size = (int)((max - min)/step);
  float[] array = new float[size];
  for (int i = 0; i < size; i++) {
    array[i] = min;
    min += step;
  }
  return array;
}

public float[] lerpArray(float[] arr1, float[] arr2, float step) {
  float[] temp = new float[arr1.length];  
  for (int i = 0; i < temp.length; i++) {
    temp[i] = arr1[i] + (arr2[i] - arr1[i])*step;
  }
  return temp;
}


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

  public TeXObject(String code) {
    // First we want to save the code to a string
    this.code = code;
    // Then insert the code in the template
    this.insertCode();
    // Convert the template to dvi
    this.convertTeXToDVI();
    // And finally convert the dvi to a png we can use
    this.convertDVIToPNG();
    
    // make the text white
    img.filter(INVERT);
    
    // The reason for scaling down the rendered TeX is that we render it 
    // at a very high resolution, so by defualt processing will display it
    // quite large. This gets it down to a reasonable size. 
    this.scale(0.5f);
  }
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
  This method converts the dvi file generated by convertTeXToDVI() into a 
  png. It makes use of the command line tool called 'dvipng', which should come
  with most latex distributions. 
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

  /** 
   This method displays the rendered TeX on the canvas
   **/
  public void display(float x, float y) {
    // Reset the location
    this.x = x;
    this.y = y;

    tint(255, alpha); // we want the tint to affect the rectangle too

    float frameWidth = 10; // This determines how thick the bounding rectangle is
    fill(0, 150); // We don't want the back rectangle to be super dark
    stroke(0); // make that background the same color as the fill

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
   This method returns the LaTeX sourcecode. 
   **/
  public String getCode() {
    return code;
  }
}
  public void settings() {  size(1200, 800);  pixelDensity(2); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AnimationEngine" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

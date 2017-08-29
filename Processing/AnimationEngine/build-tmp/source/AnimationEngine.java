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

// This animation demonstrates what happens to the determinant
// when we scale it to 0


// Our grid :)
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D i,j, iTarget1, iTarget2, iTarget3, jTarget1, jTarget2, jTarget3;
TeXObject iLabel1, jLabel1, iLabel2, jLabel2, detLabel,bracesLabel, newDetLabel, middleLabel1;
Det2D d; 


// Scene lengths
int[] sceneLengths = {100,50,50,50,50,50};

int[] cues; 

Brace vert, horz;


public void setup(){
  // for viewing on Retina displays we need to up the pixel density
  
  
  // This is 720p
  

  cues = new int[sceneLengths.length];

  for(int i = 0; i < cues.length; i++){
    cues[i] = 0;
    for(int j = 0; j <= i; j++){
      cues[i] += sceneLengths[j] ;
    }
  }


  // start with a black background
  background(Constants.BLACK);

  // initializng our vectors
  // I and J are what we are drawing,
  // then we simply interpolate between the different
  // target vectors 
  i = new Vector2D(2.5f,0.5f, Constants.LIGHT_BLUE);
  j = new Vector2D(1.5f,2, Constants.PINK);
  iTarget1 = new Vector2D(2.5f,0.5f, Constants.LIGHT_BLUE);
  jTarget1 = new Vector2D(0,0, Constants.PINK);
  iTarget2 = new Vector2D(2.5f,0.5f, Constants.LIGHT_BLUE);
  jTarget2 = new Vector2D(1.5f,2, Constants.PINK);
  iTarget3 = new Vector2D(0,0, Constants.LIGHT_BLUE);
  jTarget3 = new Vector2D(1.5f,2, Constants.PINK);

  // vector labels

  iLabel1 = new TeXObject("$\\begin{bmatrix} b \\\\ d \\end{bmatrix}$", Constants.LIGHT_BLUE);
  jLabel1 = new TeXObject("$\\begin{bmatrix} a \\\\ c \\end{bmatrix}$", Constants.PINK);

  iLabel2 = new TeXObject("$\\begin{bmatrix} 0 \\\\ 0 \\end{bmatrix}$", Constants.LIGHT_BLUE);
  jLabel2 = new TeXObject("$\\begin{bmatrix} 0 \\\\ 0 \\end{bmatrix}$", Constants.PINK);

  iLabel2.setOpacity(0);
  jLabel2.setOpacity(0);

  detLabel = new TeXObject("$\\det(A)$", Constants.WHITE);
  bracesLabel = new TeXObject("1");
  newDetLabel = new TeXObject("", Constants.WHITE);

  //iLabel.setOpacity(0);
  //jLabel.setOpacity(0);
  
  detLabel.hideBackgroundBox();

  newDetLabel.setOpacity(0);
  newDetLabel.hideBackgroundBox();

  bracesLabel.hideBackgroundBox();

  // Quad
  d = new Det2D(i,j, Constants.YELLOW);

  // Braces
  horz = new Brace(0, 1, 1, 1);
  horz.flip();
  vert = new Brace(1, 0, 1, 1);

  // Construct a standard grid
  g.display();

  i.display();
  j.display();
}

public void draw(){
	background(Constants.BLACK);

	if(frameCount <= cues[0]){
    i = i.lerp(iTarget1, 0.5f*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5f);
    j = j.lerp(jTarget1, 0.5f*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5f);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, 0, 5, 255,0));

    iLabel1.setOpacity(map(frameCount, 0, 5, 255, 0));
    iLabel2.setOpacity(map(frameCount, 0, cues[0], 0, 255));
	}
	else if(cues[0] < frameCount && frameCount <= cues[1]){
    i = i.lerp(iTarget2, 0.5f*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5f);
    j = j.lerp(jTarget2, 0.5f*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5f);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[1]-20, cues[1], 0,255));

    iLabel2.setOpacity(255 - 255*(0.5f*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5f));
    iLabel1.setOpacity(255*(0.5f*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5f));
	}  
	else if(cues[1] < frameCount && frameCount <= cues[2]){
    i = i.lerp(iTarget3, 0.5f*sin(map(frameCount, cues[1], cues[2], -PI/2, PI/2)) + 0.5f);
    j = j.lerp(jTarget3, 0.5f*sin(map(frameCount, cues[1], cues[2], -PI/2, PI/2)) + 0.5f);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[1], cues[1] + 5, 255,0));

    jLabel1.setOpacity(255 - 255*(0.5f*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5f));
    jLabel2.setOpacity(255*(0.5f*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5f));
	}
	else if(cues[2] < frameCount && frameCount <= cues[3]){
    i = i.lerp(iTarget2, 0.5f*sin(map(frameCount, cues[2], cues[3], -PI/2, PI/2)) + 0.5f);
    j = j.lerp(jTarget2, 0.5f*sin(map(frameCount, cues[2], cues[3], -PI/2, PI/2)) + 0.5f);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[3]-20, cues[3], 0,255));

    jLabel1.setOpacity(255*(0.5f*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5f));
    jLabel2.setOpacity(255 - 255*(0.5f*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5f));
	}
	else if(cues[3] < frameCount && frameCount <= cues[4]){
	}
	else if(cues[4] < frameCount && frameCount <= cues[5]){
	}

  g.display();
  d.display();

  i.display();
  j.display();

  iLabel1.displayCoordinate(i.getX() + 0.4f, i.getY() - 0.2f);
  jLabel1.displayCoordinate(j.getX() - 0.4f, j.getY() + 0.1f);

  iLabel2.displayCoordinate(i.getX() + 0.4f, i.getY() - 0.2f);
  jLabel2.displayCoordinate(j.getX() - 0.4f, j.getY() + 0.1f);

  detLabel.displayCoordinate((i.getX() + j.getX())/2.0f, (i.getY() + j.getY())/2.0f);
  newDetLabel.displayCoordinate((i.getX() + j.getX())/2.0f, (i.getY() + j.getY())/2.0f);

  
  //saveFrame("../Frames/#####.png"); 

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
* This class provides graphical capabilities for drawing points
**/
class Point{
  float x;
  float y;
  float radius = 5.0f;
  float scaleFactor = Constants.SCALE_FACTOR;

  int pointColor;
  
  public Point(float x, float y){
      this(x,y, Constants.WHITE);
  }

  public Point(float x, float y, int pointColor){ 
    this.x = x;
    this.y = y;
    this.pointColor = pointColor;
  }
  
  public void set(float x, float y){
      this.x = x;
      this.y = y;
  }

  public void setColor(int c){
    pointColor = c;
  }
  
  public void display(){
    pushMatrix();
    translate(width/2, height/2);
    fill(pointColor);
    stroke(pointColor);
    ellipse(scaleFactor * x, scaleFactor* -y,radius,radius);
    popMatrix();
  }
  
}


/**
*This class provides methods for drawing lines. 
*Still a work in progress. 
**/
class Line{
  // See constants.java for details
  float scaleFactor = Constants.SCALE_FACTOR; 
  float thickness = Constants.DEFAULT_LINE_THICKNESS;
  float opacity = 256;
  
  int lineColor; // color
  
  PVector start; // Starting point 
  PVector end; // Ending point
  
  
  public Line(float x1, float y1, float x2, float y2){
    start = new PVector(x1,y1);
    end = new PVector(x2,y2);
  }
  
  public Line(float x1, float y1, float x2, float y2, int lineColor){
    this(x1, y1, x2, y2);
    this.lineColor = lineColor;
  }
  
  // Draws the line on the canvas
  public void display(){
     strokeWeight(thickness); // sets thickness of lines
     stroke(lineColor, opacity);

     pushMatrix(); // start transformation
     
     translate(width/2,height/2); // moves origin to center of screen
     line(start.x * scaleFactor, -start.y * scaleFactor, end.x * scaleFactor, -end.y*scaleFactor); // draws the line
     
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

  public void setColor(int c){
    lineColor = c;
  }
  
  // set pixelWeight
  public void setThickness(float thickness){
     this.thickness = thickness;
  }

  public void setOpacity(float opacity){
    this.opacity = opacity;
  }
  
  public void set(float x1, float y1, float x2, float y2){
    start.set(x1,y1);
    end.set(x2,y2);
  }
}

/**
* This class builds on the PVector class, primarily for graphical usage. 
* It implements a vector on the canvas as a line with a point at the end. 
* All the standard manipulations available to PVector objects are also availabel
* for the Vector2D objects. 
**/
class Vector2D{
  // The essential information about the vector is stored
  // in a Pvector object, allowing us to easily use all the PVector methods. 
  PVector vector; 

  // Graphical parts of the vector
  Line body;
  Point endPoint;

  // color. Colors are usually assigned using color constants from the Constants.java file
  int vectorColor;

  // TeX label
  TeXObject t;

  // NOTE: Vector2D inherits its scaling from the Line and Point classes. 
  // I try to minimize the number of instance variables dedicated to scaling,
  // since I don't want it to be easy to choose different scaling factors
  // for different objects (makes for some bad drawings). 
  
  // Constructor: Takes in a PVector object 
  public Vector2D(PVector vector){
     this(vector.x, vector.y);
  }

  public Vector2D(PVector vector, int c){
    this(vector.x, vector.y, c);
  }
  
  // Allows one to not have to make intermediary PVector object
  public Vector2D(float x, float y){
     this(0,0,x,y);
  }

  public Vector2D(float x, float y, int c){
    this(0,0,x,y,c);
  }

  // Allows for anchoring of the vector *not* at the origin. 
  public Vector2D(float x1, float y1, float x2, float y2){
    this(x1,y1,x2,y2,Constants.WHITE);
  }

  // Allows for anchoring of the vector *not* at the origin. 
  public Vector2D(float x1, float y1, float x2, float y2, int c){
    vector = new PVector(x2,y2); 
    endPoint = new Point(x2,y2);
    body = new Line(x1,y1,x2,y2);
    vectorColor = c;
    setColor(vectorColor);
  }
  
  // Display the vector on the canvas
  // scaled by Constants.SCALE_FACTOR
  public void display(){
    // Draw the body of the vector
    body.display();
    // Draw endpoint
    endPoint.display();

    // Label the vector
  }
  
  public void set(PVector newVector){
    this.set(newVector.x, newVector.y);
  }

  // Set the x and y of your vector
  public void set(float x, float y){
    vector.set(x,y);  
    endPoint.set(x,y);
    body.set(0,0,x,y);
  }


  // Set the color of the vector
  public void setColor(int c){
    vectorColor = c;
    endPoint.setColor(c);
    body.setColor(c);
  }

  // returns midpoint of vector
  public PVector getMidpoint(){
    return body.getMidpoint();
  }

  public float getX(){
    return vector.x;
  }

  public float getY(){
    return vector.y;
  }

  public PVector getPVector(){
    return vector; // mutable?
  }

  // Linearly interpolate between one Vector2D and another
  public Vector2D lerp(Vector2D other, float lerpFactor){
    // creates a copy of the vector instance in this class
    // this is necessary since the PVector object is mutable. 
    // we would like to preserve the vector object. 
     PVector copy = vector.get(); 

    copy.lerp(other.getPVector(), lerpFactor);
    return new Vector2D(copy, vectorColor);
  }
}

class Det2D{
  Vector2D v,w;
  float scaleFactor = Constants.SCALE_FACTOR;
  int fillColor;
  float alpha = 255;


  public Det2D(Vector2D v,Vector2D w, int fillColor){
    this.v = v;
    this.w = w;
    this.fillColor = fillColor;
  }

  public Det2D(float x1, float y1, float x2, float y2, int fillColor){
    this(new Vector2D(x1,y1), new Vector2D(x2,y2), fillColor);
  }

  public void setOpacity(float newAlpha){
    alpha = newAlpha;
  }

  public void setVectors(Vector2D newV, Vector2D newW){
    v = newV;
    w = newW;
  }


  public void display(){
    fill(fillColor, map(alpha, 0, 255, 0, 128));
    //stroke(fillColor);
    noStroke();
    pushMatrix();
    translate(width/2, height/2);
    quad(0,0, v.getX()*scaleFactor, -v.getY()*scaleFactor, 
         (v.getX() + w.getX())*scaleFactor, (-v.getY() - w.getY())*scaleFactor,
         w.getX()*scaleFactor, -w.getY()* scaleFactor );
    popMatrix();
  }
}

//
// This is a Brace class which provides methods for drawing a brace over a particular portion
// of a picture. 
//

class Brace{
  float x1, y1, x2,y2;
  int braceColor = Constants.WHITE;
  float scaleFactor = Constants.SCALE_FACTOR;
  float length;
  float braceWidth = Constants.BRACE_WIDTH;
  float braceWeight = Constants.BRACE_WEIGHT;

  TeXObject label;
  boolean hasLabel=false;
  boolean draw = true;

  public Brace(float x1, float y1, float x2, float y2){
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    length = sqrt(pow((x1 - x2),2) + pow((y1 - y2),2));
  }

  public Brace(float x1, float y1, float x2, float y2, String tex){
    this(x1,y1,x2,y2);
    hasLabel=true;
    this.label = new TeXObject(tex);  
  }

  // This is a helper function for the display function below 
  public void drawLabel(){
    if(hasLabel){
      label.display(length/2 * scaleFactor, 20);
      //println("displayed");
    }
  }

  public void hide(){
    draw = false;
  }

  public void show(){
    draw = true;
  }

  public void flip(){
    braceWidth = -braceWidth;
  }

  public void display(){
    if(draw == false){
      return;
    }

    float angle = 0;

    if(x1 == x2){
      if(y1 < y2){
        angle = PI/2.0f;
      }
      else{
        angle = 3*PI/2.0f;
      }
    }
    else{
      angle = atan((y2-y1)/(x2-x1));
    }

    pushMatrix();

    translate(width/2, height/2);
    translate(scaleFactor * x1, -1 * scaleFactor * y1);

    rotate(-angle);
    
    noFill();

    stroke(braceColor);
    strokeWeight(braceWeight);

    bezier(0,0, 0, braceWidth, (scaleFactor * length)/2.0f, 0,   (scaleFactor * length)/2.0f, braceWidth);
    bezier(scaleFactor * length, 0, scaleFactor * length, braceWidth, 
      (scaleFactor * length)/2.0f, 0, 
      (scaleFactor * length)/2.0f, braceWidth);

    drawLabel();

    popMatrix();
  }

  public void set(float newX1, float newY1, float newX2, float newY2){
    x1 = newX1;
    y1 = newY1;
    x2 = newX2;
    y2 = newY2;
    length = sqrt( pow(x1 - x2,2) + pow(y1-y2,2)  );
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




// not really very useful right now
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
* Provides methods for plotting a set of points, as well as connecting them
* Has issues if the domain (the x values) do not always yield y values, specifically
* with the sqrt function and negative values. I'm not quite sure how to address this as of now. 
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
  float pixWeight = 1.0f;

  float opacity = 256;

  int nonAxesColor = Constants.FADED_WHITE;
  int axesColor = Constants.WHITE;

  public Grid(Vector2D i, Vector2D j){
    this(i.getPVector(), j.getPVector());
  }

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
  
  public void setAxesColor(int c){
    axesColor = c;
  }

  public void setNonAxesColor(int c){
    nonAxesColor = c;
  }

  public void setBasis(PVector newI, PVector newJ) {
    iVec = newI;
    jVec = newJ;
  }

  public void setBasis(Vector2D newI, Vector2D newJ){
    this.setBasis(newI.getPVector(), newJ.getPVector());
  }


  public void display() {
    
    float iX = iVec.x;
    float iY = iVec.y;
    float jX = jVec.x;
    float jY = jVec.y;

    for (int k = (int)xMin; k <= xMax; k++) {
      Line l = new Line(k*iX + (int)yMin*jX, k*iY + (int)yMin*jY, 
        k*iX + (int)yMax*jX, k*iY + (int)yMax*jY, nonAxesColor); 

      l.setThickness(pixWeight);
      l.setOpacity(opacity);

      if (k == 0) { // If this is the y axis
        l.setThickness(axesPixWeight); // thicken it some
        l.setColor(axesColor);
      }

      l.display();
    }

    for (int k = (int)yMin; k <= yMax; k++) {
      Line l = new Line(k*jX + (int)xMin*iX, k*jY + (int)xMin*iY, 
        k*jX + (int)xMax*iX, k*jY + (int)xMax*iY, nonAxesColor);

      l.setOpacity(opacity);
      l.setThickness(pixWeight);

      if (k == 0) { // if this is the x axis
        l.setThickness(axesPixWeight); // thicken it some
        l.setColor(axesColor);
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

  public void setOpacity(float opacity){
    this.opacity = opacity;
  }

  // Purely for debugging purposes,
  // used to track the i and j vector of the grid. 
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
    display(x - picWidth/2.0f, y - picHeight/2.0f);
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
  public void settings() {  size(1280,720);  pixelDensity(2); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AnimationEngine" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

import GObjects.GObjects;

float t= 0;
float inc = 0.01;

void setup(){
  size(1200,700);
  pixelDensity(2);
  background(255);
}


void draw(){
  fill(255,30);
  rect(0,0,width,height);
  
  Axes a = new Axes(-10,10,-5,5,1,1);  
  a.display();
  
  // Generating the dataset
  float[] xCoords = range(-10,10,0.01);
  float[] yCoords = new float[xCoords.length];
  t += inc;
  
  for(int i = 0; i < xCoords.length; i++){
    yCoords[i] = 3*tan(t)*sin(0.5*xCoords[i] + t); 
  }
  
  PolarPlot p = new PolarPlot(xCoords,yCoords);
  p.display();
  
  saveFrame("line-######.png");
}


float[] range(float min, float max, float step){
  int size = (int)((max - min)/step);
  float entry = min;
  float[] array = new float[size];
    for(int i = 0; i < size; i++){
      array[i] = entry;
      entry += step;
    }
  return array;
}  

/**
Provides methods for plotting a set of points, as well as connecting them
Has issues if the domain (the x values) do not always yield y values, specifically
with the sqrt function and negative values. I'm not quite sure how to address this as of now. 
**/
class Plot{
  float[] xCoords;
  float[] yCoords;
  float pointWidth = 6.0;
  float curveWidth = 1.0;
  boolean isSmooth = true; // defaults to smooth plot
  float scaleFactor = 50.0;
  
  public Plot(float[] xCoords, float[] yCoords){
     if(xCoords.length != yCoords.length){
        System.out.println("Not equal number of x and y coordinates"); 
        return;
     }
     this.xCoords = xCoords;
     this.yCoords = yCoords;
  }
  
  public void display(){
    if(isSmooth){
       displaySmooth(); 
    }
    else{
      displayNoSmooth(); // displays only points, does not connect them
    }
  }
  
  public void displaySmooth(){
    // Plot each point
    strokeWeight(curveWidth);
    noFill();
    pushMatrix();
    translate(width/2, height/2);
    beginShape();
    curveVertex(scaleFactor*xCoords[0], -1 * scaleFactor * yCoords[0]);
     for(int i = 0; i < xCoords.length; i++){
         curveVertex(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
     }
    curveVertex(scaleFactor*xCoords[xCoords.length-1], -1 * scaleFactor * yCoords[xCoords.length-1]);
    endShape();
    popMatrix();
  }
  
  public void displayNoSmooth(){
     // Plot each point
     strokeWeight(pointWidth);
     pushMatrix();
     translate(width/2, height/2);
     for(int i = 0; i < xCoords.length; i++){
         point(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
     }
     popMatrix();
  }
}

class PolarPlot{
  float[] rCoords;
  float[] tCoords;
  float pointWidth = 6.0;
  float curveWidth = 1.0;
  boolean isSmooth = true; // defaults to smooth plot
  float scaleFactor = 50.0;
  int len;
  
  public PolarPlot(float[] rCoords, float[] tCoords){
     if(rCoords.length != tCoords.length){
        System.out.println("Not equal number of x and y coordinates"); 
        return;
     }
     this.rCoords = rCoords;
     this.tCoords = tCoords;
     len = rCoords.length;
     
  }
  
  public void display(){
    strokeWeight(curveWidth);
    noFill();
    pushMatrix();
    translate(width/2, height/2);
    beginShape();
    curveVertex(scaleFactor*rCoords[0]*cos(tCoords[0]),
                -1 * scaleFactor * rCoords[0] * sin(tCoords[0]));
     for(int i = 0; i < len; i++){
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

/**
This class provides methods for drawing lines. 
Still a work in progress. 
**/
class Line{
  float scaleFactor;  // 
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
     scaleFactor = 50.00; 
     thickness = 1.00;
     
    // scale the line
    start.mult(50);
    end.mult(50);
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
}

class Axes{
  
   float scaleFactor = 50.0;
   Line xAxis;
   Line yAxis;
   float xStep, yStep;
   float xMin, xMax, yMin, yMax;
   float tickHeight = 0.1;

   public Axes(float xMin, float xMax, float yMin, float yMax, float xStep, float yStep){
     xAxis = new Line(xMin, 0, xMax, 0);
     yAxis = new Line(0, yMin, 0, yMax);
     this.xStep = xStep;
     this.yStep = yStep;
     this.xMin = xMin;
     this.xMax = xMax;
     this.yMin = yMin;
     this.yMax = yMax;
   }
   
   public void display(){
     xAxis.display();
     yAxis.display();
     drawTicks();
   }
   
   private void drawTicks(){
      int xTickMin = (int)(xMin/xStep);
      int xTickMax = (int)(xMax/xStep);
      for(int i = xTickMin; i <= xTickMax; i++){
         Line tick = new Line(i*xStep, -tickHeight/2 , i*xStep, tickHeight/2);
         tick.display();
      }
      
      int yTickMin = (int)(yMin/yStep);
      int yTickMax = (int)(yMax/yStep);
      for(int i = yTickMin; i <= yTickMax; i++){
         Line tick = new Line(-tickHeight/2,i*yStep,tickHeight/2,i*yStep);
         tick.display();
      }
   }
  
  
}
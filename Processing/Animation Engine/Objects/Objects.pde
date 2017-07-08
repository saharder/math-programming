//TESTER
float t = 0;

void setup(){
  size(1000,500);
  pixelDensity(2);
}

void draw(){
   t+= 0.2;
   System.out.println(t);
   Line l = new Line(0,0,cos(t), sin(t));
   l.display();
}



/**
This class provides methods for drawing lines. 
**/
class Line{
  PVector start; // Starting point 
  PVector end; // Ending point
  float scaleFactor;  // 
  boolean dashed = false;
  
  public Line(float x1, float y1, float x2, float y2){
    start = new PVector(x1,-y1);
    end = new PVector(x2,-y2);
    
     // scaleFactor defaults to 50.00 
     // This means that one unit corresponds to fifty pixels
     scaleFactor = 50.00; 
     
    start.mult(50);
    end.mult(50);
  }
  
  public void display(){
     pushMatrix();
     translate(width/2,height/2);
     line(start.x, start.y, end.x, end.y);
     // If the line is dashed
     if(!dashed){  
     }
     popMatrix();
  }
  
  // Returns the a PVector at the midPoint of 
  public PVector getMidpoint(){
      return new PVector((start.x + end.x)/2, (start.y + end.y)/2);
  }
  
  public void setScale(float scaleFactor){
     this.scaleFactor = scaleFactor;
  }
}

class Vector extends PVector{
  
}


/**
The Grid Class provides functionality for drawing a coordinate grid
in 2D based on two vectors. 
**/
class Grid{
  // constants
  int sFact = 50; // scaleFactor
  // minumum and maximum x,y values on grid
  float xMin, yMin, xMax, yMax; 
  
  // basis vectors 
  PVector iVec; // i vector
  PVector jVec; // j vector

  public Grid(PVector iVec, PVector jVec){
    this.iVec = iVec;
    this.jVec = jVec;
    drawAxes();
  }
  
  public void drawGrid(){
    
  }
  
  public void drawAxes(){
  }
}
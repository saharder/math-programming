//TESTER
void setup(){
  size(600,600);
  PVector iVec = new PVector(1,0);
  PVector jVec = new PVector(0,1);
  Grid g = new Grid(iVec,jVec);
}

void draw(){
}


class Line{
  PVector startPoint;
  PVector endPoint;
  
  public Line(PVector startPoint, PVector endPoint){
     this.startPoint = startPoint;
     this.endPoint = endPoint;
  }
  
  public void display(){
     line(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
  }
  
  public void scale(float scaleFactor){
  }
  
  public PVector getMidpoint(){
      return new PVector((startPoint.x + endPoint.x)/2, (startPoint.y + endPoint.y)/2);
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
  int SCALE_FACTOR = 50; // scales the lines
  int BREADTH = 20; // determines size of grid
  
  // basis vectors 
  PVector iVec; // i vector
  PVector jVec; // j vector

  public Grid(PVector iVec, PVector jVec){
    this.iVec = iVec;
    this.jVec = jVec;
    drawAxes();
  }
  
  public void drawLines(){
    
  }
  
  public void drawAxes(){
    // Thickens lines and paints them white
    strokeWeight(2);
    stroke(255);
    
    
    pushMatrix();
    // moves to center of screen
    translate(width/2, height/2);
    
    // Draws y and x axes
    // X axis
    line(BREADTH * SCALE_FACTOR * -iVec.x, 
        BREADTH * SCALE_FACTOR * -iVec.y,
        BREADTH * SCALE_FACTOR * iVec.x,
        BREADTH * SCALE_FACTOR * iVec.x);
    // Y Axis
     line(BREADTH * SCALE_FACTOR * -jVec.x, 
        BREADTH * SCALE_FACTOR * -jVec.y,
        BREADTH * SCALE_FACTOR * jVec.x,
        BREADTH * SCALE_FACTOR * jVec.x);   
        
     popMatrix();
  }
}
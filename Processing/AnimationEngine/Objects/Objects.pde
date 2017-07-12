//TESTER
float t = 0; //theta
float inc = 0.1; // space between points on sin curve

void setup(){
  size(1000,500);
  pixelDensity(2); // for retina displays
}

void draw(){
   background(255);
   Circle c = new Circle(-2.0,0.0,2.0);
   c.display();
   for(float i = 0; i <= 7; i += inc){
      Line l = new Line(i,2*sin(i+t),i + inc,2*sin(i + inc + t)); 
      l.display();
   }
   Line k = new Line(-2,0,2*cos(t) - 2, 2*sin(t),255,0,0);
   Line m = new Line(2*cos(t) - 2, 2*sin(t), 0, 2*sin(t));
   k.display();
   m.display();
   
   t -=0.01;
   
   
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

class Circle{
   float rad, x, y;
   float scaleFactor = 50;
   
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
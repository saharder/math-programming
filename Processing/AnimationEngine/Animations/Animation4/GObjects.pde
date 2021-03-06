/** 
This class allows us to draw points
**/
class Point{
  float x;
  float y;
  float radius = 5.0;
  float scaleFactor = 100.0;
  
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
  float scaleFactor;  // Defaults to 100.00
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
     scaleFactor = 100.00; 
     thickness = 1.00;
     
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
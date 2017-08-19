/** 
*This class allows us to draw points
**/
class Point{
  float x;
  float y;
  float radius = 5.0;
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
  float scaleFactor = Constants.SCALE_FACTOR;  // Defaults to 50.00
  float thickness;
  boolean dashed = false;
  
  int lineColor; // color
  
  PVector start; // Starting point 
  PVector end; // Ending point
  
  
  public Line(float x1, float y1, float x2, float y2){
    start = new PVector(x1,y1);
    end = new PVector(x2,y2);
    
     // scaleFactor defaults to 50.00 
     // This means that one unit of length corresponds to fifty pixels
     // pixelWeight defaults to 2.00, i.e. all lines are 2 pixels wide
     thickness = 2.00;
  }
  
  public Line(float x1, float y1, float x2, float y2, int lineColor){
    this(x1, y1, x2, y2);
    this.lineColor = lineColor;
  }
  
  // Draws the line on the canvas
  public void display(){
     strokeWeight(thickness); // sets thickness of lines
     stroke(lineColor);

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

  // color
  int vectorColor;

  // NOTE: Vector2D inherits its scaling from the Line and Point classes. 
  // I try to minimize the number of instance variables dedicated to scaling,
  // since I don't want it to be easy to choose different scaling factors
  // for different objects (makes for some bad drawings). 
  
  // Constructor: Takes in a PVector object 
  public Vector2D(PVector vector){
     this(vector.x, vector.y);
  }
  
  // Allows one to not have to make intermediary PVector object
  public Vector2D(float x, float y){
     this(0,0,x,y);
  }

  // Allows for anchoring of the vector *not* at the origin. 
  public Vector2D(float x1, float y1, float x2, float y2){
    vector = new PVector(x2,y2); 
    endPoint = new Point(x2,y2);
    body = new Line(x1,y1,x2,y2);
  }
  
  // Display the vector on the canvas
  // scaled by Constants.SCALE_FACTOR
  public void display(){
    // Draw the body of the vector
    body.display();
    // Draw endpoint
    endPoint.display();
  }
  
  // Set the x and y of your vector
  public void set(float x, float y){
    vector.set(x,y);  
    endPoint.set(x,y);
    body.set(0,0,x,y);
  }

  // Set the color of the vector
  public void setColor(int c){
    endPoint.setColor(c);
    body.setColor(c);
  }

  // Linearly interpolate between one PVector and another
  public PVector lerp(PVector other, float lerpFactor){
    return this.lerp(other, lerpFactor);
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
/**
This class provides methods for drawing lines. 
Still a work in progress. 
**/
class Line{
  float scaleFactor;  // Defaults to 50.00
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

class Grid{
    PVector i,j;
    float xMin, xMax, yMin, yMax;
    
    
    public Grid(PVector i, PVector j){
      this(i, j, -5, 5, -5, 5);
    }
    
    public Grid(PVector i, PVector j, float xMin, float xMax, float yMin, float yMax){
      this.i = i;
      this.j = j;
      this.xMin = xMin;
      this.xMax = xMax;
      this.yMin = yMin;
      this.yMax = yMax;
    }
    
    public void newBasis(PVector newI, PVector newJ){
       i = newI;
       j = newJ;
    }
    
    public void drawAxes(){
      
    }
    
    public void display(){
      
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
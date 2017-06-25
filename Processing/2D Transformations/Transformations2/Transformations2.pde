PVector i = new PVector(1,0);
PVector j = new PVector(0,1);
PVector targetI = new PVector(0,1);
PVector targetJ = new PVector(1,0);
float lerpFactor = 0.01;
Arrow2D iArrow;
Arrow2D jArrow;

void setup(){
  size(480,480);
}

void draw(){ 
  // Clear the canvas
  background(0);
  
  // iVector 
  i = i.lerp(targetI, lerpFactor);
  iArrow = new Arrow2D(i.x, i.y, 200, 0, 0);
  
  // jVector
  j = j.lerp(targetJ, lerpFactor);
  jArrow = new Arrow2D(j.x, j.y, 0, 0, 200);
  
  // Grid of points
  Grid g = new Grid(i,j);
  //saveFrame("frames/grid_####.png");
}

class Grid{
  PVector iVec; // i vector
  PVector jVec; // j vector
  float unitLength; // Gives number of pixels for unit (length of 1)
  
  // Initializes Grid, assigns variables
  public Grid(PVector iVec, PVector jVec){
    this.iVec = iVec;
    this.jVec = jVec;
    this.display();
  }
  
  // Displays the Grid
  // Format of the grid is points at integer linear combinations
  // of the two basis vectors, lines connecting them (in basis vector directions). 
  // BUG: Note that PVectors are mutable!
  void display(){    
    PVector point;
    PVector mPoint;
    PVector iVecCopy;
    PVector jVecCopy;
    for(int i = -5; i <= 5; i++){
       for(int j = -5; j <= 5; j++){
         // Deals with mutability
         iVecCopy = iVec.copy();
         jVecCopy = jVec.copy();
         
         // actual drawing
         point = iVecCopy.mult(i).add(jVecCopy.mult(j)); // p = i*iVec + j*jVec
         mPoint = new PVector(map(point.x, -5.2,5.2, 0, width), map(point.y, -5.2,5.2, height,0));
         ellipse(mPoint.x, mPoint.y, 3, 3);
         System.out.println(point.x + ", " + point.y);
       }
    }
  }
}

class Arrow2D{
   float x;
   float y;
   float rVal;
   float gVal;
   float bVal;
   
   public Arrow2D(float x, float y, float rVal, float gVal, float bVal){
       this.x = x;
       this.y = y;
       this.rVal = rVal;
       this.gVal = gVal;
       this.bVal = bVal;
       this.display();
   }
   
   public void display(){
     
      float mO = map(0,-5.2,5.2,0,width);
      float mX = map(x,-5.2,5.2,0,width);
      float mY = map(y,-5.2,5.2,height,0);
      stroke(rVal,gVal,bVal); 
      fill(rVal,gVal,bVal);
      // draw a triangle at (x2, y2)
      pushMatrix();
        line(mO,mO,mX,mY);
        rotate(atan2(y, x));
        translate(mX, mY);
        triangle(0, 0, -10, 5, -10, -5);
      popMatrix(); 
      noStroke();
   }
}
PVector i = new PVector(1,0);
PVector j = new PVector(0,1);
PVector targetI = new PVector(0,1);
PVector targetJ = new PVector(1,0);
float lerpFactor = 0.1;

void setup(){
  size(480,480);
}

void draw(){ 
  background(0);
  i = i.lerp(targetI, 0.1);
  j = j.lerp(targetJ, 0.1);
  Grid g = new Grid(i,j);
  saveFrame("frames/grid_####.png");
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
         ellipse(mPoint.x, mPoint.y, 10, 10);
         System.out.println(point.x + ", " + point.y);
       }
    }
  }
}
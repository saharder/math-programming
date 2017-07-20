
PVector iVec = new PVector(1,0);
PVector jVec = new PVector(0,1);

void setup(){
  size(1000,500);
  pixelDensity(2);
  
  Grid g = new Grid(iVec, jVec);
  g.display();
}


void draw(){
   Grid l = new Grid(iVec.mult(1.01),jVec.mult(1.02));
   l.display();
}
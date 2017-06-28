int t = 0;

void setup(){
  size(1000,1000);
}

void draw(){
  t+= 10;
  float rad = radians(t);
  Arrow2D a = new Arrow2D(cos(rad),sin(rad));
}

class Arrow2D{
   PVector vector;
   float len;
   float SCALE_FACTOR = 50;
   
   
   public Arrow2D(float x, float y){
       vector = new PVector(x,y); // vectors
       len = sqrt(x*x + y*y); // length of vector
       this.display();
   }
   
   public void display(){
      // points of the triangle
      PVector triP1 = new PVector(-0.1,0.1);
      PVector triP2 = new PVector(0,0);
      PVector triP3 = new PVector(-0.1,0.1);
     
     
      pushMatrix();
        // move origin to center of screen
        // blow up screen 3 times
        translate(width/2,height/2);
        
        // draw the line
        // negative on y gives correct orientation
        vector.mult(SCALE_FACTOR);
        line(0,0,vector.x, -vector.y); 
        
      popMatrix();
   }
     
}
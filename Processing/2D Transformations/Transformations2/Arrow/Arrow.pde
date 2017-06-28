int t = 0;

void setup(){
  size(480,480);
}

void draw(){
  t++;
  Arrow2D a = new Arrow2D(cos(t),sin(t),255,0,0);
  a.display(); 
}

class Arrow2D{
   PVector vector;
   float len;
   
   
   public Arrow2D(float x, float y){
       vector = new PVector(x,y); // vectors
       len = sqrt(x*x + y*y); // length of vector
       this.display();
   }
   
   public void display(){
     
      float mX = map(vector.x,-5.2,5.2,0,width);
      float mY = map(vector.y,-5.2,5.2,height,0);
      
      // draw a triangle at (x2, y2)
      pushMatrix();
        stroke(3);
        translate(mX,mY);
        rotate(atan(vector.x/vector.y));
        line(0,0,len,0); // horizontal vector with length len
      popMatrix();
   }
}
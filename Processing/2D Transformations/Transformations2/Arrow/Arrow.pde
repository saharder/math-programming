void setup(){
  size(480,480);
}

void draw(){
   Arrow2D a = new Arrow2D(1,0,255,0,0); 
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
      
      // draw a triangle at (x2, y2)
      pushMatrix();
        stroke(rVal,gVal,bVal); 
        fill(rVal,gVal,bVal);
        line(mO,mO,mX,mY);
        rotate(atan2(y, x));
        translate(mX, mY);
        triangle(0, 0, -10, 5, -10, -5);
      popMatrix(); 
   }
}
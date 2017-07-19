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
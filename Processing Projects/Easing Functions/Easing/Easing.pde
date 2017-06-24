int w = 640;
int h = 400;
float x; 
float y;

void setup(){
   size(640,400); // make display
   
   // Make stroke thicker
   strokeWeight(3);
   point(0.25*w, 0.5*h); // starting point
   point(0.75*w, 0.5*h); // ending point
}

void draw() { 
  background(51);
  
  // lerp() calculates a number between two numbers at a specific increment. 
  // The amt parameter is the amount to interpolate between the two values 
  // where 0.0 equal to the first point, 0.1 is very near the first point, 0.5 
  // is half-way in between, etc.  
  
  // Here we are moving 5% of the way to the mouse location each frame
  x = lerp(x, mouseX, 0.05);
  y = lerp(y, mouseY, 0.05);
  
  fill(255);
  stroke(255);
  ellipse(x, y, 66, 66);
}
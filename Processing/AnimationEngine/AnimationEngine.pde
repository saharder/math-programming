//float[] xCoords = range(-5,5,0.05);
//float[] yCoords = new float[xCoords.length];
//Plot p;

//void setup(){
//  size(1000,500);
//  pixelDensity(2);
//  background(255);
//  for(int i = 0; i < xCoords.length; i++){
//    yCoords[i] = sin(xCoords[i]); 
//  }
//  p = new Plot(xCoords, yCoords);
//}

//void draw(){ 
//  background(255);
//  p.display();
//}

//void keyPressed(){
//  if(keyCode == DOWN){
//     println("down"); 
//     background(0);
//  }
//  if(keyCode == UP){
//    background(255);  
//  }
//}

float[] xValues, taylor1, taylor2, taylor3, taylor4, taylor5, taylor6, taylor7, taylor8, yValues, sinValues;

Plot p, sinPlot;
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
float t = 0;
TeXObject TeX;

void setup() {
  size(1200, 800);
  background(0);
  g.setColor(255,255,255);
  pixelDensity(2);
  background(255);
  g.display();
  
  TeX = new TeXObject("Taylor Approximations of $\\sin(x)$");

  xValues = range(-10, 10, 0.05);
  sinValues = new float[xValues.length];
  taylor1 = new float[xValues.length];
  taylor2 = new float[xValues.length];
  taylor3 = new float[xValues.length];
  taylor4 = new float[xValues.length];
  taylor5 = new float[xValues.length];
  taylor6 = new float[xValues.length];
  taylor7 = new float[xValues.length];
  taylor8 = new float[xValues.length];

  float x;
  for (int i = 0; i < xValues.length; i++) {
    x = xValues[i];
    sinValues[i] = sin(x);
    taylor1[i] = x;
    taylor2[i] = x - pow(x, 3)/6;
    taylor3[i] = x - pow(x, 3)/6 + pow(x, 5)/120;
    taylor4[i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040;
    taylor5[i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880;
    taylor6[i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880 - pow(x, 11)/39916800;
    taylor7[i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880 - pow(x, 11)/39916800 + pow(x, 13)/6227020800.0;
  }
  
  sinPlot = new Plot(xValues, sinValues,0, 255, 0);
}

void draw() {
  //saveFrame("../frames/####.png");
  
  background(0);
  g.display();
  if (t < 100) {
    yValues = lerpArray(taylor1, taylor2, t/100);
  }
  if (t >= 100 && t < 200) {
    yValues = lerpArray(taylor2, taylor3, (t % 100)/100);
  }
  if (t >= 200 && t < 300) {
    yValues = lerpArray(taylor3, taylor4, (t % 100)/100);
  }
  if (t >= 300 && t < 400) {
    yValues = lerpArray(taylor4, taylor5, (t % 100)/100);
  }
  if (t >= 400 && t < 500) {
    yValues = lerpArray(taylor5, taylor6, (t % 100)/100);
  }
  if (t >= 500 && t < 600) {
    yValues = lerpArray(taylor6, taylor7, (t % 100)/100);
  }
  if (t >= 600) {
    yValues = taylor7;
  }

  sinPlot.display();
  p = new Plot(xValues, yValues, 255, 0, 255);
  p.display();
  TeX.display(1000 - TeX.picWidth - 20, 50);

  t++;
  
  println(frameRate);
}
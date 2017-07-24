float[] xValues, taylor1, taylor2, taylor3, taylor4, taylor5, taylor6, taylor7, taylor8, yValues, sinValues;

Plot p, sinPlot;

// g is a standard grid
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));

float t = 0; // used for moving animations along

int start,end; // These will track which animations we are stransitioning between

// This will be our title
TeXObject TeX;
float[][] taylor;

// We will use this boolean to toggle animations
boolean animate = false;

void setup() {
  size(1200, 800);
  pixelDensity(2);
  background(0);

  g.setColor(255,255,255);
  g.display();
  
  TeX = new TeXObject("Taylor Approximations of $\\sin(x)$");

  xValues = range(-10, 10, 0.1);

  taylor = new float[7][xValues.length];

  sinValues = new float[xValues.length];

  float x;
  for (int i = 0; i < xValues.length; i++) {
    x = xValues[i];
    sinValues[i] = sin(x);
    taylor[0][i] = x;
    taylor[1][i] = x - pow(x, 3)/6;
    taylor[2][i] = x - pow(x, 3)/6 + pow(x, 5)/120;
    taylor[3][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040;
    taylor[4][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880;
    taylor[5][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880 - pow(x, 11)/39916800;
    taylor[6][i] = x - pow(x, 3)/6 + pow(x, 5)/120 - pow(x, 7)/5040 + pow(x, 9)/362880 - pow(x, 11)/39916800 + pow(x, 13)/6227020800.0;
  }

  p = new Plot(xValues, taylor[0], 0, 255, 255);
  sinPlot = new Plot(xValues, sinValues,0, 255, 0);
  sinPlot.display();

  start = -1;
  end = 0;

  println("Finished setup.");
}

void draw() {
  // Make the background black, display the grid, display the sin plot
  background(0);
  g.display();
  sinPlot.display();

  if(animate == true){ 
    if(t == 50){
      animate = false;
    }
    println(t/50);
    p = new Plot(xValues,lerpArray(taylor[start], taylor[end], t/50.0), 0, 255, 255);
    t += 1;
    t = t % 51;
  }
  p.display();
}

public void keyPressed(){
  // A left key press should revert the animation to a previous state
  if(keyCode == RIGHT){
      animate = true;
      start += 1;
      end += 1;
      println("LEFT");
  }
  // A right key press should push the animation to its next state
  else if(keyCode == LEFT){
      animate = true;

      println("RIGHT");
      int temp = start;
      start = end;
      end = temp;
  }
}


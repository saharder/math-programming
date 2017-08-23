/**
* Provides methods for plotting a set of points, as well as connecting them
* Has issues if the domain (the x values) do not always yield y values, specifically
* with the sqrt function and negative values. I'm not quite sure how to address this as of now. 
 **/

class Plot {
  // These contain the main data for our curve
  float[] xCoords;
  float[] yCoords;

  // Self explanatory
  float pointWidth = 6.0;
  float curveWidth = 1.0;

  boolean isSmooth = true; // defaults to smooth plot

  // This value should not be changed to maintain
  // consistency between different classes. 
  float scaleFactor = Constants.SCALE_FACTOR;

  // These store the color of the curve
  color c;

  // This is the shape that we display on the screen:
  PShape curve;

  public Plot(float[] xCoords, float[] yCoords) {
    this(xCoords, yCoords, 255,255,255);
  }

  public Plot(float[] xCoords, float[] yCoords, int rVal, int gVal, int bVal) {
    c = color(rVal, gVal, bVal);
    if (xCoords.length != yCoords.length) {
      System.out.println("Not equal number of x and y coordinates"); 
      return;
    }
    this.xCoords = xCoords;
    this.yCoords = yCoords;
    this.generateCurve();
  }

  public void display() {
    if (isSmooth) {
      displaySmooth();
    } else {
      displayNoSmooth(); // displays only points, does not connect them
    }
  }

  public void generateCurve() {
    curve = createShape(); // Instantiates the curve
    curve.beginShape();
    curve.noFill();
    curve.stroke(c);
    curve.strokeWeight(curveWidth); // Sets the width of the curve 
    //curve.vertex(scaleFactor*xCoords[0], -1 * scaleFactor * yCoords[0]);
    for (int i = 0; i < xCoords.length; i++) {
      curve.vertex(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
    }
    curve.endShape();
  }

  public void displaySmooth() {
    pushMatrix();
    translate(width/2, height/2);
    shape(curve);
    popMatrix();
  }

  public void displayNoSmooth() {
    // Plot each point
    strokeWeight(pointWidth);
    pushMatrix();
    translate(width/2, height/2);
    for (int i = 0; i < xCoords.length; i++) {
      point(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
    }
    popMatrix();
  }
}

class Plot3D {
  float[][] surface;

  public Plot3D(float[][] surface) {
    this.surface = surface;
  }

  public void display() {
    pushMatrix();
    rotateX(PI/4);

    for (int y = 0; y < surface.length-1; y++) {
      for (int x = 0; x < surface[0].length-1; x++) {
        fill(255, 0, 0);
        stroke(255, 0, 0);
        beginShape(QUAD_STRIP);
        vertex(x, y, surface[x][y]);
        vertex(x + 1, y, surface[x+1][y+1]);
        vertex(x, y + 1, surface[x][y+1]);
        vertex(x + 1, y + 1, surface[x+1][y+1]);
        endShape();
      }
    }
    popMatrix();
  }
}


class PolarPlot {
  float[] rCoords;
  float[] tCoords;
  float pointWidth = 6.0;
  float curveWidth = 1.0;
  boolean isSmooth = true; // defaults to smooth plot
  float scaleFactor = Constants.SCALE_FACTOR;
  int len;

  public PolarPlot(float[] rCoords, float[] tCoords) {
    if (rCoords.length != tCoords.length) {
      System.out.println("Not equal number of x and y coordinates"); 
      return;
    }
    this.rCoords = rCoords;
    this.tCoords = tCoords;
    len = rCoords.length;
  }

  public void display() {
    strokeWeight(curveWidth);
    noFill();
    pushMatrix();
    translate(width/2, height/2);
    beginShape();
    curveVertex(scaleFactor*rCoords[0]*cos(tCoords[0]), 
      -1 * scaleFactor * rCoords[0] * sin(tCoords[0]));
    for (int i = 0; i < len; i++) {
      float x = rCoords[i]*cos(tCoords[i]);
      float y = rCoords[i]*sin(tCoords[i]);
      curveVertex(scaleFactor * x, -1 * scaleFactor * y);
    }
    curveVertex(scaleFactor*rCoords[len-1]*cos(tCoords[len-1]), 
      -1 * scaleFactor * rCoords[len-1] * sin(tCoords[len-1]));
    endShape();
    popMatrix();
  }
}


class Axes {

  float scaleFactor = Constants.SCALE_FACTOR;
  Line xAxis;
  Line yAxis;
  float xStep, yStep;
  float xMin, xMax, yMin, yMax;
  float tickHeight = 0.1;

  public Axes(float xMin, float xMax, float yMin, float yMax, float xStep, float yStep) {
    xAxis = new Line(xMin, 0, xMax, 0);
    yAxis = new Line(0, yMin, 0, yMax);
    this.xStep = xStep;
    this.yStep = yStep;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }

  public void display() {
    xAxis.display();
    yAxis.display();
    drawTicks();
  }

  private void drawTicks() {
    int xTickMin = (int)(xMin/xStep);
    int xTickMax = (int)(xMax/xStep);
    for (int i = xTickMin; i <= xTickMax; i++) {
      Line tick = new Line(i*xStep, -tickHeight/2, i*xStep, tickHeight/2);
      tick.display();
    }

    int yTickMin = (int)(yMin/yStep);
    int yTickMax = (int)(yMax/yStep);
    for (int i = yTickMin; i <= yTickMax; i++) {
      Line tick = new Line(-tickHeight/2, i*yStep, tickHeight/2, i*yStep);
      tick.display();
    }
  }
}

class Grid {
  PVector iVec, jVec;
  float xMin, xMax, yMin, yMax;
  float axesPixWeight = 2.00;
  float pixWeight = 1.0;

  float opacity = 256;

  int nonAxesColor = Constants.FADED_WHITE;
  int axesColor = Constants.WHITE;

  public Grid(Vector2D i, Vector2D j){
    this(i.getPVector(), j.getPVector());
  }

  public Grid(PVector i, PVector j) {
    this(i, j, -10, 10, -10, 10);
  }

  public Grid(PVector i, PVector j, float xMin, float xMax, float yMin, float yMax) {
    this.iVec = i;
    this.jVec = j;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
  }
  
  public void setAxesColor(int c){
    axesColor = c;
  }

  public void setNonAxesColor(int c){
    nonAxesColor = c;
  }

  public void setBasis(PVector newI, PVector newJ) {
    iVec = newI;
    jVec = newJ;
  }

  public void setBasis(Vector2D newI, Vector2D newJ){
    this.setBasis(newI.getPVector(), newJ.getPVector());
  }


  public void display() {
    
    float iX = iVec.x;
    float iY = iVec.y;
    float jX = jVec.x;
    float jY = jVec.y;

    for (int k = (int)xMin; k <= xMax; k++) {
      Line l = new Line(k*iX + (int)yMin*jX, k*iY + (int)yMin*jY, 
        k*iX + (int)yMax*jX, k*iY + (int)yMax*jY, nonAxesColor); 

      l.setThickness(pixWeight);
      l.setOpacity(opacity);

      if (k == 0) { // If this is the y axis
        l.setThickness(axesPixWeight); // thicken it some
        l.setColor(axesColor);
      }

      l.display();
    }

    for (int k = (int)yMin; k <= yMax; k++) {
      Line l = new Line(k*jX + (int)xMin*iX, k*jY + (int)xMin*iY, 
        k*jX + (int)xMax*iX, k*jY + (int)xMax*iY, nonAxesColor);

      l.setOpacity(opacity);
      l.setThickness(pixWeight);

      if (k == 0) { // if this is the x axis
        l.setThickness(axesPixWeight); // thicken it some
        l.setColor(axesColor);
      }

      l.display();
    }
  }

  public void apply(float[][] matrix) {
    if (matrix.length != 2 || matrix[0].length != 2) {
      System.out.println("Error: Matrix must be 2x2");
      return;
    }
    iVec = new PVector(iVec.x * matrix[0][0] + iVec.y * matrix[1][0], iVec.x * matrix[0][1] + iVec.y * matrix[1][1]);
    jVec = new PVector(jVec.x * matrix[0][0] + jVec.y * matrix[1][0], jVec.x * matrix[0][1] + jVec.y * matrix[1][1]);
  }

  public void rotate(float theta) {
    theta = radians(theta);
    iVec = new PVector(iVec.x * cos(theta) + iVec.y * -sin(theta), iVec.x * sin(theta) + iVec.y * cos(theta));
    jVec = new PVector(jVec.x * cos(theta) + jVec.y * -sin(theta), jVec.x * sin(theta) + jVec.y * cos(theta));
  }

  public void setOpacity(float opacity){
    this.opacity = opacity;
  }

  // Purely for debugging purposes,
  // used to track the i and j vector of the grid. 
  public String toString() {
    return "i Vector: " + iVec.x + ", " + iVec.y + " jVector: " + jVec.x + ", " + jVec.y;
  }
}

public float[] range(float min, float max, float step) {
  int size = (int)((max - min)/step);
  float[] array = new float[size];
  for (int i = 0; i < size; i++) {
    array[i] = min;
    min += step;
  }
  return array;
}

public float[] lerpArray(float[] arr1, float[] arr2, float step) {
  float[] temp = new float[arr1.length];  
  for (int i = 0; i < temp.length; i++) {
    temp[i] = arr1[i] + (arr2[i] - arr1[i])*step;
  }
  return temp;
}
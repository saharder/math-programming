/**
Provides methods for plotting a set of points, as well as connecting them
Has issues if the domain (the x values) do not always yield y values, specifically
with the sqrt function and negative values. I'm not quite sure how to address this as of now. 
**/
class Plot{
  float[] xCoords;
  float[] yCoords;
  float pointWidth = 6.0;
  float curveWidth = 1.0;
  boolean isSmooth = true; // defaults to smooth plot
  float scaleFactor = 50.0;
  
  public Plot(float[] xCoords, float[] yCoords){
     if(xCoords.length != yCoords.length){
        System.out.println("Not equal number of x and y coordinates"); 
        return;
     }
     this.xCoords = xCoords;
     this.yCoords = yCoords;
  }
  
  public void display(){
    if(isSmooth){
       displaySmooth(); 
    }
    else{
      displayNoSmooth(); // displays only points, does not connect them
    }
  }
  
  public void displaySmooth(){
    // Plot each point
    strokeWeight(curveWidth);
    noFill();
    pushMatrix();
    translate(width/2, height/2);
    beginShape();
    curveVertex(scaleFactor*xCoords[0], -1 * scaleFactor * yCoords[0]);
     for(int i = 0; i < xCoords.length; i++){
         curveVertex(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
     }
    curveVertex(scaleFactor*xCoords[xCoords.length-1], -1 * scaleFactor * yCoords[xCoords.length-1]);
    endShape();
    popMatrix();
  }
  
  public void displayNoSmooth(){
     // Plot each point
     strokeWeight(pointWidth);
     pushMatrix();
     translate(width/2, height/2);
     for(int i = 0; i < xCoords.length; i++){
         point(scaleFactor * xCoords[i], -1 * scaleFactor * yCoords[i]);
     }
     popMatrix();
  }
}

class PolarPlot{
  float[] rCoords;
  float[] tCoords;
  float pointWidth = 6.0;
  float curveWidth = 1.0;
  boolean isSmooth = true; // defaults to smooth plot
  float scaleFactor = 50.0;
  int len;
  
  public PolarPlot(float[] rCoords, float[] tCoords){
     if(rCoords.length != tCoords.length){
        System.out.println("Not equal number of x and y coordinates"); 
        return;
     }
     this.rCoords = rCoords;
     this.tCoords = tCoords;
     len = rCoords.length;
     
  }
  
  public void display(){
    strokeWeight(curveWidth);
    noFill();
    pushMatrix();
    translate(width/2, height/2);
    beginShape();
    curveVertex(scaleFactor*rCoords[0]*cos(tCoords[0]),
                -1 * scaleFactor * rCoords[0] * sin(tCoords[0]));
     for(int i = 0; i < len; i++){
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
  
class Axes{
  
   float scaleFactor = 50.0;
   Line xAxis;
   Line yAxis;
   float xStep, yStep;
   float xMin, xMax, yMin, yMax;
   float tickHeight = 0.1;

   public Axes(float xMin, float xMax, float yMin, float yMax, float xStep, float yStep){
     xAxis = new Line(xMin, 0, xMax, 0);
     yAxis = new Line(0, yMin, 0, yMax);
     this.xStep = xStep;
     this.yStep = yStep;
     this.xMin = xMin;
     this.xMax = xMax;
     this.yMin = yMin;
     this.yMax = yMax;
   }
   
   public void display(){
     xAxis.display();
     yAxis.display();
     drawTicks();
   }
   
   private void drawTicks(){
      int xTickMin = (int)(xMin/xStep);
      int xTickMax = (int)(xMax/xStep);
      for(int i = xTickMin; i <= xTickMax; i++){
         Line tick = new Line(i*xStep, -tickHeight/2 , i*xStep, tickHeight/2);
         tick.display();
      }
      
      int yTickMin = (int)(yMin/yStep);
      int yTickMax = (int)(yMax/yStep);
      for(int i = yTickMin; i <= yTickMax; i++){
         Line tick = new Line(-tickHeight/2,i*yStep,tickHeight/2,i*yStep);
         tick.display();
      }
   }
  
  
}
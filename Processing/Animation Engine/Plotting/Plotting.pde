float h = 1;

void setup(){
  size(800,600);
  pixelDensity(2);
}

void draw(){
  background(255);
  h+=0.1;
  float[] xCoords = new float[10000];
  float[] yCoords = new float[10000];
  for(int i = 0; i < xCoords.length; i++){
     float t = 0.01*i;
     xCoords[i] = t;
     yCoords[i] = tan(t);
  }  
  
  Plot p = new Plot(xCoords, yCoords);
  p.displaySmooth();
}

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
  
  public void displaySmooth(){
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
   
   public Axes(float xMin, float xMax, float yMin, float yMax, float xStep, float yStep){
      
   }
  
  
}
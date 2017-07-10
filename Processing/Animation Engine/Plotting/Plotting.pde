float t = 0;
float h = 1;

void setup(){
  size(1000,500);
  pixelDensity(2);
}

void draw(){
  fill(255);
  rect(0,0,width,height);
  
  t += 0.1;
  h = 4;
  float[] xCoords = new float[100];
  float[] yCoords = new float[100];
  for(int i = 0; i < xCoords.length; i++){
     xCoords[i] = 0.1*(i-50); 
     yCoords[i] = h*sin(0.1*(i-50));
  }  
  
  Plot p = new Plot(xCoords, yCoords);
  p.displayNoSmooth();
  p.displaySmooth();
}


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
import peasy.*;
import peasy.org.apache.commons.math.*;
import peasy.org.apache.commons.math.geometry.*;

TeXObject t;
TeXObjectAnimation Anim;
int count = 1;


import peasy.*;

PeasyCam cam;
Plot3D plot;

void setup() {
  size(800,800,P3D);
  cam = new PeasyCam(this, 100);
  pixelDensity(1);
  float[][] surface = new float[100][100];
  for(int i = 0; i < surface.length; i++){
    for(int j = 0; j < surface[0].length; j++){
       float x = map(i,0,surface.length, -10,10);
       float y = map(j,0,surface.length, -10,10);
       surface[i][j] = 0.1*(x*x - y*y);
    }
  }
  plot =  new Plot3D(surface);
}

void draw() { 
  background(255);
  plot.display();
  println(frameRate);
}
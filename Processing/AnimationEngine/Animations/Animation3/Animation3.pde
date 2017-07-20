//import java.io.*;
//import java.net.URL;


//PImage img;
//PShape shp;

// stage 1 Rotate to x axis
PVector iVec = new PVector(1, 0);
PVector jVec = new PVector(0, 1);

float theta = 90.00;

PVector iVec1 = new PVector(cos(radians(-theta)), sin(radians(-theta)));
PVector jVec1 = new PVector(-sin(radians(-theta)), cos(radians(-theta)));

PVector iVec2 = new PVector(cos(radians(-theta)), -sin(radians(-theta)));
PVector jVec2 = new PVector(-sin(radians(-theta)), -cos(radians(-theta)));

PVector diffI = iVec2.sub(iVec1).mult(0.01);
PVector diffJ = jVec2.sub(jVec1).mult(0.01);

int TeXHeight = 50;


TeXObject t1, t2, t3;
Grid g;
Grid l; // This is sort of ridiculous but it works :/
int count = 0;

void setup() {
  background(255);
  size(1000, 500);
  pixelDensity(2);

  g = new Grid(iVec, jVec);
  t1 = new TeXObject("$\\begin{bmatrix} \\cos(-60) & -\\sin(-60) \\\\ \\sin(-60) & \\cos(-60) \\end{bmatrix}$");
  t2 = new TeXObject("$\\begin{bmatrix} 1 & 0 \\\\ 0 & -1 \\end{bmatrix}$");
  t3 = new TeXObject("$\\begin{bmatrix} \\cos(60) & -\\sin(60) \\\\ \\sin(60) & \\cos(60) \\end{bmatrix}$");
  t1.scale(0.3);
  t2.scale(0.3);
  t3.scale(0.3);

  g.display();
  filter(INVERT);
}


void draw() {
  background(255);
  if (frameCount <= 100) {
    g.rotate(-theta/100.0);
    g.display();
    t1.display(950-t1.picWidth, TeXHeight);
  }
  if (frameCount > 100 && frameCount <= 200) {
    iVec1.add(diffI);
    jVec1.add(diffJ);
    g = new Grid(iVec1, jVec1);
    g.display();
    t1.display(950 - t1.picWidth,TeXHeight);
    t2.display(950 - t1.picWidth - 20 - t2.picWidth, TeXHeight);
  }
  if (frameCount > 200 && frameCount <= 300) {
    g.rotate(theta/100);
    g.display();
    t1.display(950 - t1.picWidth, TeXHeight);
    t2.display(950 - t1.picWidth - 20 - t2.picWidth, TeXHeight);
    t3.display(950 - t1.picWidth - 20 - t2.picWidth - 20 - t3.picWidth, TeXHeight);
  }
  if (frameCount > 300) {
    g.display();
    t1.display(950 - t1.picWidth, TeXHeight);
    t2.display(950 - t1.picWidth - 20 - t2.picWidth, TeXHeight);
    t3.display(950 - t1.picWidth - 20 - t2.picWidth - 20 - t3.picWidth, TeXHeight);
  }
  filter(INVERT);
  
  saveFrame("../frames/###.tiff");
}
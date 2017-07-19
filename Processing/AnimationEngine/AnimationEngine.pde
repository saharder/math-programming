import java.io.*;
import java.net.URL;


PImage img;
PShape shp;

float x,y;
int time = 0;

TeXObject t;

String code1 = "$\\boxed{a^2 + b^2 = c^2}$";
String code2 = "\\qquad \\parbox{3cm}{If $a,b$ are the legs of a right triangle and $c$ is its hypotenuse.}";
String code3 = "$ \\begin{bmatrix} 1 & 2  \\\\ 3 & 4 \\end{bmatrix} $";
String code4 = "$\\displaystyle \\lim_{x \\to h} \\frac{f(x) - f(h)}{x-h}$";

void setup(){
  size(1000,500);
  pixelDensity(2);
  background(0); 
  frameRate(60);
  
  t = new TeXObject(code4);
  
  x = 100;
  y = 100;
  
  t.scale(0.5);
  t.setOpacity(0);
}

void draw(){
  background(0);
  x = lerp(x,mouseX,0.2);
  y = lerp(y,mouseY,0.2);
  t.setOpacity(frameCount*4);
  t.display(x,y);
}
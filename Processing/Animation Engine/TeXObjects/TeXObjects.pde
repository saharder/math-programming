/**
It has now come to my attention that Processing does not support any text in svg files,
which is sort of some b.s. So I have to use an external java library to accomplish this task. 
Namely the package Batik. 
**/
import java.io.*;


PImage img;
PShape shp;

void setup(){
   size(1000,500);
   pixelDensity(2);
   img = loadImage("Untitled.png");
   shp = loadShape("TEX_TEMPLATE2.svg");
   shape(shp,30,30);
}

void draw(){
}


class TeXObject{
  
  String code;
  PShape img;
  String TeXFileName = "TEX_TEMPLATE.tex";
  
  
  public TeXObject(String code, String imgType){
    this.code = code;
    this.insertCode();
    if(imgType.equals("png")){
      this.generatePNGImage();
    } else {
      this.generateSVGImage();
    }
  }
  
  public void insertCode(){
    
  }
  
  
  /**
  Here's the basic idea. I want a high quality (vector graphic) render
  of the LaTeX code which is entered into the constructor of this class.
  To accomplish this I am using the same basic technique that 3blue1brown
  uses in his videos. I have a TEX_TEMPLATE.tex file which I can edit and
  insert my code into. This renders into a pdf which includes a white 
  background. To remove this background (make it transparent) I convert
  it to a dvi file, then to an svg file. 
  
  NOTE: FOR THE TIME BEING THIS IS DISCONTINUED BECAUSE OF ISSUES WITH
  PROCESSING'S HANDLING OF SVG IMAGES. 
  **/
  public void generateSVGImage(){
    
  }
  
  public void generatePNGImage(){
      
  }
  
  public void display(){
    
  }
  
}
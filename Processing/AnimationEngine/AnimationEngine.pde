// Our grid :)
Grid backG = new Grid(new PVector(1,0), new PVector(0,1));
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D v,w;

// counter
float t = 0.0;
Det2D d;

PImage bg;

// vectors
Vector2D i = new Vector2D(1,0, Constants.RED);

Vector2D j = new Vector2D(0,1); 
Vector2D targetJ = new Vector2D(1,1);


TeXObject label;

void setup(){
  pixelDensity(2);
  size(1000,500);

  background(Constants.BLACK);
  backG.setOpacity(56);
  backG.display();
  bg = get();


  i.display();
  j.display();

   d = new Det2D(i,j, Constants.YELLOW);
   d.display();

  label = new TeXObject();

}

void draw(){
  background(bg);

  if(t < 1){
    t += 0.01;
  }

  float lerpProgress = t;

  Vector2D lerpJ = j.lerp(targetJ, lerpProgress);
  lerpJ.setColor(Constants.LIGHT_BLUE);

  // v.set(cos(t)*4,0);
  g.setBasis(i,lerpJ);
 d = new Det2D(i,lerpJ, Constants.YELLOW);


 g.display();
 d.display();

  i.display();
  lerpJ.display();


}






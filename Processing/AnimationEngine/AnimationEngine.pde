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
Vector2D targetJ = new Vector2D(2,-1);

Brace b;


TeXObject label;
TeXObject vectorLabel;

void setup(){
  pixelDensity(2);
  size(1000,500);

  label=new TeXObject("$ \\det \\left( \\begin{bmatrix} a & b \\\\ c & d \\end{bmatrix} \\right) = ad - bc.   $");

  background(Constants.BLACK);
  backG.setOpacity(56);
  backG.display();
  bg = get();


  b = new Brace(0,0,i.getX(), i.getY());

  i.display();
  j.display();

   d = new Det2D(i,j, Constants.YELLOW);
   d.display();
   b.display();

   vectorLabel = new TeXObject("$\\det(A)$");

}

void draw(){
  background(bg);

  if(t < 1){
    t = round((t + 0.01)*100)/100.0; // noticed some weird accuracy issues with floating point numbers
    //println(t);
  }

  float lerpProgress = t;

  Vector2D lerpJ = j.lerp(targetJ, lerpProgress);
  lerpJ.setColor(Constants.LIGHT_BLUE);

  // v.set(cos(t)*4,0);
  g.setBasis(i,lerpJ);
  d = new Det2D(i,lerpJ, Constants.YELLOW);
  b = new Brace(0,0,lerpJ.getX(), lerpJ.getY());

  g.display();
  d.display();
  i.display();

  b.display();
  lerpJ.display();


  label.displayCoordinate(3,1);
  vectorLabel.displayCoordinate((lerpJ.getX()+ i.getX())/2, (lerpJ.getY() +i.getY())/2);
}






// Our grid :)
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D v,w;
float t = 0.0;

void setup(){
  pixelDensity(2);
  size(1000,500);

  background(Constants.BLACK);


  g.setColor(Constants.WHITE);
  g.display();

   v = new Vector2D(1,0);
   v.setColor(Constants.RED);
   v.display();
   w = new Vector2D(0,1);
   w.setColor(Constants.LIGHT_BLUE);
   w.display();
}

void draw(){
  t += 0.01;

  background(Constants.BLACK);
  g.display();

  v.set(cos(t),sin(t));
   w.set(-sin(t), cos(t));

  v.display();
  w.display();


}




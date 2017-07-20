Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D v;
Point p;
float t = 0;
float r = 0;

void setup() {
  size(1000, 500);
  pixelDensity(2);
  v = new Vector2D(1,1);
  v.display();
  v.set(1,2);
  v.display();
}
// Our grid :)
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D i,j, iTarget, jTarget;
TeXObject iLabel, jLabel, newILabel, newJLabel, detLabel,bracesLabel, newDetLabel;
Det2D d;

int[] cues = {15,50,450,1,1,1,1};

Brace vert, horz;


void setup(){
  // for viewing on Retina displays we need to up the pixel density
  pixelDensity(2);
  
  // This is 720p
  size(1280,720);

  // start with a black background
  background(Constants.BLACK);

  // initializng our vectors
  i = new Vector2D(1,0, Constants.LIGHT_BLUE);
  j = new Vector2D(0,1, Constants.PINK);
  iTarget = new Vector2D(2.5,0.5, Constants.LIGHT_BLUE);
  jTarget = new Vector2D(1.5,2, Constants.PINK);

  // TeX Objects

  // vector labels, before transformation
  iLabel = new TeXObject("$\\vec{i}$", Constants.LIGHT_BLUE);
  jLabel = new TeXObject("$\\vec{j}$", Constants.PINK);

  newILabel = new TeXObject("$\\begin{bmatrix} a \\\\ c \\end{bmatrix}$", Constants.LIGHT_BLUE);
  newJLabel = new TeXObject("$\\begin{bmatrix} b \\\\ d \\end{bmatrix}$", Constants.PINK);

  newILabel.setOpacity(0);
  newJLabel.setOpacity(0);


  detLabel = new TeXObject("$1$", Constants.WHITE);
  bracesLabel = new TeXObject("1");
  newDetLabel = new TeXObject("$\\det(A)$", Constants.WHITE);

  //iLabel.setOpacity(0);
  //jLabel.setOpacity(0);
  
  detLabel.hideBackgroundBox();

  newDetLabel.setOpacity(0);
  newDetLabel.hideBackgroundBox();

  bracesLabel.hideBackgroundBox();

  // Quad
  d = new Det2D(i,j, Constants.YELLOW);

  // Braces
  horz = new Brace(0, 1, 1, 1);
  horz.flip();
  vert = new Brace(1, 0, 1, 1);

  // Construct a standard grid
  g.display();

  i.display();
  j.display();
}

void draw(){
	background(Constants.BLACK);

	if(frameCount <= cues[0]){
    horz.set(map(frameCount, 0, cues[0], 0, 0.5),1,map(frameCount, 0, cues[0], 1, 0.5), 1);
    vert.set(1,map(frameCount, 0, cues[0], 0, 0.5),1,map(frameCount, 0, cues[0], 1, 0.5));
    bracesLabel.setOpacity(map(frameCount, 0, cues[0], 255, 0));
    if(frameCount == cues[0]){
      horz.hide();
      vert.hide();
    }



	}
	else if(cues[0] < frameCount && frameCount <= cues[1]){
    i = i.lerp(iTarget, 0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5);
    j = j.lerp(jTarget, 0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[0], cues[0] + 5, 255,0));
    newDetLabel.setOpacity(map(frameCount, cues[0], cues[1] - 5,0, 255));

    iLabel.setOpacity(map(frameCount, cues[0], cues[0] + 5, 255,0));
    newILabel.setOpacity(map(frameCount, cues[0], cues[1] - 5,0, 255));
    jLabel.setOpacity(map(frameCount, cues[0], cues[0] + 5, 255,0));
    newJLabel.setOpacity(map(frameCount, cues[0], cues[1] - 5,0, 255));
	}  
	else if(cues[1] < frameCount && frameCount <= cues[2]){
	}
	else if(cues[2] < frameCount && frameCount <= cues[3]){
	}
	else if(cues[3] < frameCount && frameCount <= cues[4]){
	}
	else if(cues[4] < frameCount && frameCount <= cues[5]){
	}

  g.display();
  d.display();

  i.display();
  j.display();

  iLabel.displayCoordinate(i.getX() + 0.2, i.getY() - 0.1);
  jLabel.displayCoordinate(j.getX() - 0.2, j.getY() + 0.1);

  newILabel.displayCoordinate(i.getX() + 0.4, i.getY() - 0.1);
  newJLabel.displayCoordinate(j.getX() - 0.4, j.getY() + 0.1);

  detLabel.displayCoordinate((i.getX() + j.getX())/2.0, (i.getY() + j.getY())/2.0);
  newDetLabel.displayCoordinate((i.getX() + j.getX())/2.0, (i.getY() + j.getY())/2.0);

  horz.display();
  vert.display();

  bracesLabel.displayCoordinate(0.5, 1.3);
  bracesLabel.displayCoordinate(1.25, 0.5);

  
  //saveFrame("../Frames/#####.png"); 

}







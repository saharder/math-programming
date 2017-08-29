// This animation demonstrates what happens to the determinant
// when we scale it to 0


// Our grid :)
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D i,j, iTarget1, iTarget2, iTarget3, jTarget1, jTarget2, jTarget3;
TeXObject iLabel1, jLabel1, iLabel2, jLabel2, detLabel,bracesLabel, newDetLabel, middleLabel1;
Det2D d; 


// Scene lengths
int[] sceneLengths = {100,50,50,50,50,50};

int[] cues; 

Brace vert, horz;


void setup(){
  // for viewing on Retina displays we need to up the pixel density
  pixelDensity(2);
  
  // This is 720p
  size(1280,720);

  cues = new int[sceneLengths.length];

  for(int i = 0; i < cues.length; i++){
    cues[i] = 0;
    for(int j = 0; j <= i; j++){
      cues[i] += sceneLengths[j] ;
    }
  }


  // start with a black background
  background(Constants.BLACK);

  // initializng our vectors
  // I and J are what we are drawing,
  // then we simply interpolate between the different
  // target vectors 
  i = new Vector2D(2.5,0.5, Constants.LIGHT_BLUE);
  j = new Vector2D(1.5,2, Constants.PINK);
  iTarget1 = new Vector2D(2.5,0.5, Constants.LIGHT_BLUE);
  jTarget1 = new Vector2D(0,0, Constants.PINK);
  iTarget2 = new Vector2D(2.5,0.5, Constants.LIGHT_BLUE);
  jTarget2 = new Vector2D(1.5,2, Constants.PINK);
  iTarget3 = new Vector2D(0,0, Constants.LIGHT_BLUE);
  jTarget3 = new Vector2D(1.5,2, Constants.PINK);

  // vector labels

  iLabel1 = new TeXObject("$\\begin{bmatrix} b \\\\ d \\end{bmatrix}$", Constants.LIGHT_BLUE);
  jLabel1 = new TeXObject("$\\begin{bmatrix} a \\\\ c \\end{bmatrix}$", Constants.PINK);

  iLabel2 = new TeXObject("$\\begin{bmatrix} 0 \\\\ 0 \\end{bmatrix}$", Constants.LIGHT_BLUE);
  jLabel2 = new TeXObject("$\\begin{bmatrix} 0 \\\\ 0 \\end{bmatrix}$", Constants.PINK);

  iLabel2.setOpacity(0);
  jLabel2.setOpacity(0);

  detLabel = new TeXObject("$\\det(A)$", Constants.WHITE);
  bracesLabel = new TeXObject("1");
  newDetLabel = new TeXObject("", Constants.WHITE);

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
    // change our i and j vectors along with the parallelogram
    i = i.lerp(iTarget1, 0.5*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5);
    j = j.lerp(jTarget1, 0.5*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, 0, 5, 255,0));

    iLabel1.setOpacity(map(frameCount, 0, 5, 255, 0));
    iLabel2.setOpacity(map(frameCount, 0, cues[0], 0, 255));
	}
	else if(cues[0] < frameCount && frameCount <= cues[1]){
    // change our i and j vectors along with the parallelogram
    i = i.lerp(iTarget2, 0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5);
    j = j.lerp(jTarget2, 0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[1]-20, cues[1], 0,255));

    iLabel2.setOpacity(255 - 255*(0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5));
    iLabel1.setOpacity(255*(0.5*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5));
	}  
	else if(cues[1] < frameCount && frameCount <= cues[2]){
    // change our i and j vectors along with the parallelogram
    i = i.lerp(iTarget3, 0.5*sin(map(frameCount, cues[1], cues[2], -PI/2, PI/2)) + 0.5);
    j = j.lerp(jTarget3, 0.5*sin(map(frameCount, cues[1], cues[2], -PI/2, PI/2)) + 0.5);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[1], cues[1] + 5, 255,0));

    jLabel1.setOpacity(255 - 255*(0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5));
    jLabel2.setOpacity(255*(0.5*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5));
	}
	else if(cues[2] < frameCount && frameCount <= cues[3]){
    // change our i and j vectors along with the parallelogram
    i = i.lerp(iTarget2, 0.5*sin(map(frameCount, cues[2], cues[3], -PI/2, PI/2)) + 0.5);
    j = j.lerp(jTarget2, 0.5*sin(map(frameCount, cues[2], cues[3], -PI/2, PI/2)) + 0.5);
    d.setVectors(i,j);
    detLabel.setOpacity(map(frameCount, cues[3]-20, cues[3], 0,255));

    jLabel1.setOpacity(255*(0.5*sin(map(frameCount, 0, cues[0], -PI/2, PI/2)) + 0.5));
    jLabel2.setOpacity(255 - 255*(0.5*sin(map(frameCount, cues[0], cues[1], -PI/2, PI/2)) + 0.5));
	}
	else if(cues[3] < frameCount && frameCount <= cues[4]){
	}
	else if(cues[4] < frameCount && frameCount <= cues[5]){
	}


  // Layering and displaying everything after we've computed everything
  // the ordering IS IMPORTANT
  g.display();
  d.display();

  i.display();
  j.display();

  iLabel1.displayCoordinate(i.getX() + 0.4, i.getY() - 0.2);
  jLabel1.displayCoordinate(j.getX() - 0.4, j.getY() + 0.1);

  iLabel2.displayCoordinate(i.getX() + 0.4, i.getY() - 0.2);
  jLabel2.displayCoordinate(j.getX() - 0.4, j.getY() + 0.1);

  detLabel.displayCoordinate((i.getX() + j.getX())/2.0, (i.getY() + j.getY())/2.0);
  newDetLabel.displayCoordinate((i.getX() + j.getX())/2.0, (i.getY() + j.getY())/2.0);

  
  //saveFrame("../Frames/#####.png"); 

}







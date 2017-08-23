// Our grid :)
Grid g = new Grid(new PVector(1, 0), new PVector(0, 1));
Vector2D i,j;
TeXObject iLabel, jLabel, detLabel,bracesLabel, newDetLabel;
Det2D d;

int[] cues = {25,75,125,140,150,160};

Brace vert, horz;


void setup(){
  // for viewing on Retina displays we need to up the pixel density
  pixelDensity(2);
  
  // This is 720p
  size(1280,720);

  // start with a black background
  background(Constants.BLACK);

  // initializng our vectors
  i = new Vector2D(0,0, Constants.LIGHT_BLUE);
  j = new Vector2D(0,0, Constants.PINK);

  // TeX Objects
  iLabel = new TeXObject("$\\vec{i}$", Constants.LIGHT_BLUE);
  jLabel = new TeXObject("$\\vec{j}$", Constants.PINK);
  detLabel = new TeXObject("$\\det(I)$", Constants.WHITE);
  bracesLabel = new TeXObject("1");
  newDetLabel = new TeXObject("1");

  iLabel.setOpacity(0);
  jLabel.setOpacity(0);
  
  detLabel.setOpacity(0);
  detLabel.hideBackgroundBox();

  newDetLabel.setOpacity(0);
  newDetLabel.hideBackgroundBox();


  bracesLabel.setOpacity(0);
  bracesLabel.hideBackgroundBox();

  // Quad
  d = new Det2D(i,j, Constants.YELLOW);
  d.setOpacity(0.0);

  // Braces
  horz = new Brace(0.5, 1, 0.5, 1);
  horz.hide();
  horz.flip();
  vert = new Brace(1, 0.5, 1, 0.5);
  vert.hide();

  // Construct a standard grid
  g.display();

  i.display();
  j.display();
}

void draw(){
	background(Constants.BLACK);

	if(frameCount <= cues[0]){
		i.set((float)frameCount/cues[0], 0);
		j.set(0, (float)frameCount/cues[0]);
	}
	else if(cues[0] < frameCount && frameCount <= cues[1]){
		iLabel.setOpacity( map(frameCount, cues[0], cues[1], 0, 255  ));
		jLabel.setOpacity( map(frameCount, cues[0], cues[1], 0, 255  ));
	}
	else if(cues[1] < frameCount && frameCount <= cues[2]){
		d.setOpacity( map(frameCount, cues[1], cues[2], 0, 255  ));
		detLabel.setOpacity(map(frameCount, cues[2] - 20, cues[2], 0, 255));
	}
	else if(cues[2] < frameCount && frameCount <= cues[3]){
		horz.show();
		vert.show();
		horz.set(map(frameCount, cues[2], cues[3], 0.5, 0),
			1,map(frameCount, cues[2], cues[3], 0.5, 1) ,1);
		vert.set(1,map(frameCount, cues[2], cues[3], 0.5, 0)
			,1, map(frameCount, cues[2], cues[3], 0.5, 1));
	}
	else if(cues[3] < frameCount && frameCount <= cues[4]){
		bracesLabel.setOpacity(map(frameCount, cues[3], cues[4], 0, 255));
	}
	else if(cues[4] < frameCount && frameCount <= cues[5]){
		detLabel.setOpacity(map(frameCount, cues[4], cues[5], 255, 0));
		detLabel.setWidth(lerp(detLabel.getWidth(), newDetLabel.getWidth(), 0.05));
		newDetLabel.setOpacity(map(frameCount, cues[4], cues[5], 0, 255));
	}

  g.display();
  d.display();

  i.display();
  j.display();

  iLabel.displayCoordinate(i.getX() + 0.2, i.getY() - 0.1);
  jLabel.displayCoordinate(j.getX() - 0.2, j.getY() + 0.1);
  detLabel.displayCoordinate((i.getX() + j.getX())/2.0, (i.getY() + j.getY())/2.0);
  newDetLabel.displayCoordinate((i.getX() + j.getX())/2.0, (i.getY() + j.getY())/2.0);

  horz.display();
  vert.display();

  bracesLabel.displayCoordinate(0.5, 1.3);
  bracesLabel.displayCoordinate(1.25, 0.5);

  saveFrame("../Frames/#####.png"); 

}







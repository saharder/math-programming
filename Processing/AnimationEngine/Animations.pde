/**
 This is an experiment in using classes for animations. 
 **/
class TeXObjectAnimation {

  TeXObject TeX;
  float imgWidth;
  float alpha; // opacity on scale of 0 to 255
  float x, y; // Location on screen

  public TeXObjectAnimation(TeXObject t) {
    this(t, width/2, height/2);
  }

  public TeXObjectAnimation(TeXObject t, float x, float y) {
    TeX = t;
    imgWidth = t.getWidth();
    this.x = x;
    this.y = y;
  }

  public void fadeIn(int startFrame, int duration) {
    // Don't do anything until we hit our startFrame 
    if (frameCount < startFrame) {
      return;
    }
    // If we have hit our startFrame, then lets get to work:'
    alpha = ((float)(frameCount - startFrame))/((float)duration) * 255;
    System.out.println(alpha);
    TeX.setOpacity(alpha);
    TeX.display(x, y);
  }
  
  // This is buggy. Not quite sure what's wrong. Error thrown is:
  // "Width(2) and height (0) cannot be <= 0". Will investigate later.
  public void growIn(int startFrame, int duration) {
    if (frameCount <= startFrame) {
      return;
    } else if (frameCount > startFrame && frameCount < startFrame + duration) {
      // If we have hit our startFrame, then lets get to work:'
      float newWidth = ((float)(frameCount - startFrame))/((float)duration) * imgWidth;
      System.out.println(newWidth);
      if (newWidth >= 1) {
        TeX.setWidth(newWidth);
        TeX.display(x, y);
      }
    } else {
      TeX.display(x, y);
    }
  }

  public void fadeOut(int startFrame, int duration) {
  }
}
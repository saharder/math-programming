import processing.core.*;
import processing.data.*;


public final class Constants{
	private Constants(){}

	// This determines how big to make everything on the screen. 
	// We have to scale because by default Processing interprets points
	// as pixels. This doesn't allow us to show the detail we would like to.
	// The literal meaning of the scale factor is that one unit on our graph
	// or curve corresponds to SCALE_FACTOR pixels when displayed. 
	public static final float SCALE_FACTOR = 125.0f; 

	// LINE CONSTANTS
	public static final float DEFAULT_LINE_THICKNESS = 3.00f;

	// BRACE CONSTANTS
	public static final float BRACE_WIDTH = 20.0f; // determines the width of brace
	public static final float BRACE_WEIGHT = 1.5f; // determines the thickness of brace (in pixels)

	// TEX CONSTANTS
	public static final float TEX_FRAME_WIDTH = 5.0f; // seperation between tex and border of box
	public static final float TEX_SCALE_FACTOR = 0.5f; 

	// Colors!!! 
	// Note that I am not using 
	public static final int WHITE = 0xFFFFFFFF;
	public static final int FADED_WHITE = 0x80FFFFFF;
	public static final int BLACK = 0xFF000000;
	public static final int RED = 0xFFFF0000;
	public static final int LIGHT_BLUE = 0xFF00FFFF;	
	public static final int YELLOW = 0xFFFFFF00;
	public static final int PINK = 0xFFFF00FF;
}
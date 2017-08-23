
public final class Constants{
	private Constants(){}

	// This determines how big to make everything on the screen. 
	// We have to scale because by default Processing interprets points
	// as pixels. This doesn't allow us to show the detail we would like to.
	// The literal meaning of the scale factor is that one unit on our graph
	// or curve corresponds to SCALE_FACTOR pixels when displayed. 
	public static final float SCALE_FACTOR = 75.0f; 
}
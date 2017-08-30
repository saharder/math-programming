public void easeOutLerp(int value, int low1, int high1, int low2, int high2){

}

public float easeInQuad(int value, int low1, int high1, int low2, int high2){
	if(value < low1){
		return low2;
	}
	else if(value > high1){
		return high2;
	}

	float x = map(value, low1, high1, 0,1);
	return (high2 - low2)*( - pow(x-1,2) + 1) + low2;
}

public float easeOutQuad(int value, int low1, int high1, int low2, int high2){
	if(value < low1){
		return high2;
	}
	else if(value > high1){
		return low2;
	}

	float x = map(value, low1, high1, 0,1);
	return (high2 - low2)*( - pow(x,2) + 1) + low2;
}


public float easeInSin(int value, int low1, int high1, int low2, int high2){
	if(value < low1){
		return low2;
	}
	else if(value > high1){
		return high2;
	}

	float x = map(value, low1, high1, -PI/2, PI/2);
	float output = (high2 -low2)*(0.5*sin(x) + 0.5) + low2;
	return output;
}

public float easeOutSin(int value, int low1, int high1, int low2, int high2){
	return (high2+low2) - easeInSin(value, low1, high1, low2, high2);
}
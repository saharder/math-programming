float 

void setup(){
  size(1000,500);
  background(255);
}

void draw(){ 
  background(255);
}

void keyPressed(){
  if(keyCode == DOWN){
     println("down"); 
     background(0);
  }
  if(keyCode == UP){
    background(255);  
  }
}
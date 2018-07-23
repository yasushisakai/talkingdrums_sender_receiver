import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sender extends PApplet {



Serial port;
byte n;

public void setup() {
  

  port = new Serial(this, Serial.list()[Serial.list().length-1], 11520);

  n = (byte) 0;
  
  // super slow 
  frameRate(1);
}

public void draw() {
  int m = (n & 0xFF);
  background(m);

  String binStr = String.format("%8s", Integer.toBinaryString(n & 0xFF)).replace(' ','0');
  String message = "L: r=" + binStr;
  port.write(message);
  println(message);
  
  n--;
}
  public void settings() {  size(1068, 768); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sender" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

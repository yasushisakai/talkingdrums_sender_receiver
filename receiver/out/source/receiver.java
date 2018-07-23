import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
import http.requests.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class receiver extends PApplet {





Serial port;
byte n;
int i;

public void setup() {
  

  port = new Serial(this, Serial.list()[Serial.list().length-1], 115200);

  n = (byte) 0;
  
}

public void draw() {

  if(port.available() > 0) {
    
    String incoming = port.readString();
    background(255,0,0);

    if(incoming != null && incoming.length() > 13){
      String b = incoming.substring(5,13);
      i = Integer.parseInt(b, 2);
      background(i);
      println(b + ":" + i);

      // get request
      GetRequest get = new GetRequest("https://cityio.media.mit.edu/talkingdrums/" + i);
      get.send();

    }
  }


}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "receiver" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

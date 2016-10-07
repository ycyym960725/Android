package tutorials.androidstudio.fragmentsv4;

import processing.core.PApplet;

public class Sketch extends PApplet {
  public void settings() {
    size(600, 600);
  }
//
  public void setup() { }

  float num;
  int backgroundColor = 0;
  boolean doOnce = true;

//  public void setup() {
////fullScreen();
//    stroke(0, 150, 255);
//    orientation(LANDSCAPE);//防止画面旋转重启应用
//  }

  public void draw() {
    background(backgroundColor);

    float maxX = (float)180/width*mouseX;
    float maxY = (float)180/height*mouseX;
    float len  = (float)mouseY/4;

    translate(width/2, height/2);
    for (int i = 0; i < 360; i+=5) {
      float x = sin(radians(i)) * maxX;
      float y = cos(radians(i)) * maxY;

      float x2 = sin(radians(i+len)) * maxX;
      float y2 = cos(radians(i+len)) * maxY;

      pushMatrix();
      translate(x, y);
      rotate(radians(-i+num));
      line(x, y, x2, y2);
      line(-x, -y, -x2, -y2);
      line(-y, x, -y2, x2);
      line(y, -x, y2, -x2);
      popMatrix();
    }
    num += 0.3;
  }

  public void mousePressed() {
    if (doOnce && backgroundColor == 255) {
      backgroundColor = 0;
      doOnce = false;
    }
    if (doOnce && backgroundColor == 0) {
      backgroundColor = 255;
      doOnce = false;
    }
  }

  public void mouseReleased() {
    doOnce = true;
  }

}
package tutorials.androidstudio.fragmentsv3;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Sketch extends PApplet {
  public void settings() {
    //size put in here
    fullScreen();
  }

  PGraphics pg;

  public void setup() {
    pg = createGraphics(800, 800);
  }

  public void draw() {
    fill(0, 12);
    rect(0, 0, width, height);
    fill(255);
    noStroke();
    ellipse(mouseX, mouseY, 60, 60);

    pg.beginDraw();
    pg.background(51);
    pg.noFill();
    pg.stroke(255);
    pg.ellipse(mouseX-120, mouseY-60, 60, 60);
    pg.endDraw();

    // Draw the offscreen buffer to the screen with image()
    image(pg, 120, 60);
  }

}
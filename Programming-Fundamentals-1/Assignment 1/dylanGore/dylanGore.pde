import javax.swing.*;

/*
 * Author: Dylan Gore
 * Student Number: 20081224
 * Programme Name: Internet of Things
 *
 * Brief description of the animation achieved: A simple paint program that allows the user to draw on the screen using a variery of shapes and colors which can be changed by pressing different keys.
 *
 * Known bugs/problems:
 * - You can draw outside of some of the outer lines of the canvas
 *
 * Any sources referred to during the development of the assignment (no need to reference lecture/lab materials)::
 *  - https://processing.org/reference/
 */

/* Button */
boolean btnFocused = false;
int btnSize = 50;
int btnX = 20;
int btnY = 20;

/* Canvas */
boolean canFocused = false;
int canWidth = 0;
int canHeight = 0;
int canX = 20;
int canY = 100;

/* Colors */
int red = 0;
int green = 0;
int blue = 0;

/* Misc */
boolean debugMode = true;
boolean firstRun = true;
int drawMode = 1;
int size = 1;

/* Processing setup function */
void setup(){
  size(800, 800);
  fill(200);
  strokeWeight(1);
  
  canWidth = width - 40;
  canHeight = height - 120;
}

/* Processing draw funtion */
void draw(){
  firstLoad();
  checkCursor();
  
  checkKeyPress();
  drawOnCanvas();
  
  drawColorInfo();
  createButton(btnX, btnY);
  checkIfInFocus();
  
  if(btnFocused == true && mousePressed && (mouseButton == LEFT)){
    log("Button pressed");
    red = 0;
    green = 0;
    blue = 0;
    delay(500); //this short delay means that this is only recorded once.
  }
}

/* Runs different functions depending on which key is pressed */
void checkKeyPress(){
  if(keyPressed){
    switch(key){
      case '1':{
        drawMode = 1;
        break;
      }
      case '2':{
        drawMode = 2;
        break;
      }
      case '3':{
        drawMode = 3;
        break;
      }
      case '4':{
        drawMode = 4;
        break;
      }
      case '5':{
        drawMode = 5;
        break;
      }
      case '0':{
        reset();
        break;
      }
      //Red
      case 'r':{
        if(red < 255){
          red++;
        }
        break;
      }
      case 'R':{
        if(red > 0){
          red--;
        }
        break;
      }
      //Green
      case 'g':{
        if(green < 255){
          green++;
        }
        break;
      }
      case 'G':{
        if(green > 0){
          green--;
        }
        break;
      }
      //Blue
      case 'b':{
        if(blue < 255){
          blue++;
        }
        break;
      }
      case 'B':{
        if(blue > 0){
          blue--;
        }
        break;
      }
      case 'h':{
        help();
        break;
      }
      default:{
        log("Unknown Key '" + key +"' Pressed!");
        break;
      }
    }
  }
}

/* Creates a square button at the given x and y coords */
void createButton(int x, int y){
  if(btnFocused){
    fill(0);
  }else{
    fill(red, green, blue);
  }
  rect(x, y, btnSize, btnSize);
}

/* Checks if the mouse cursor is hovering over the button */
void checkIfInFocus(){
    if((mouseX > btnX && mouseX < btnX + btnSize) && (mouseY > btnY && mouseY < btnY + btnSize)){
      btnFocused = true;
    }else{
      btnFocused = false;
    }
}

/* Displays the current values for red, green and blue in the top right of the window */
void drawColorInfo(){
  fill(200);
  rect(width - 150, 0, 150, 100);
  
  fill(100);
  textSize(12);
  text("R: " + red, width - 100, height - height + 30 );
  text("G: " + green, width - 100, height - height + 50 );
  text("B: " + blue, width - 100, height - height + 70 );
}

/* Checks if mouse cursor is over canvas and draws depending on drawMode */
void drawOnCanvas(){
  //Checks if mouse cursor is within canvas
  if((mouseX > canX && mouseX < canX + canWidth) && (mouseY > canY && mouseY < canY + canHeight)){
    canFocused = true;
    //Only draws on canvas when button is pressed
    if(mousePressed && mouseButton == LEFT){
      fill(red, green, blue);
      stroke(red, green, blue);
      switch(drawMode){
        case 1:{
          rect(mouseX, mouseY, 5, 5);
          break;
        }
        case 2:{
          ellipse(mouseX, mouseY, 5, 5);
          break;
        }
        case 3:{
          line(20, mouseY, width-20, mouseY);
          break;
        }
        case 4:{
          
          line(mouseX, 100 , mouseX, height-20);
          break;
        }
        case 5:{
          
          line(20, mouseY, width - 20, mouseY);
          line(mouseX, 100 , mouseX, height-20);
          break;
        }
      }
      
    }
  }else{
    canFocused = false;
  }
}

/* Changes the cursor if either the canvas or button are in focus */
void checkCursor(){
  
  if(btnFocused){
    cursor(HAND);
  }else if(canFocused){
    cursor(CROSS);
  }else{
    cursor(ARROW); //Default Cursor
  }
  
}

/* Runs only when the sketch is first run */
void firstLoad(){
  if(firstRun){
    log("First Load");
    drawCheck();
    help();
    drawCanvas();
    firstRun = false;
  }
}

/* Creates white box on screen for drawing in */
void drawCanvas(){
  //Draw Canvas
  fill(255);
  rect(canX, canY, canWidth, canHeight);
}

/* Reset values to their defaults and re-draw the canvas */
void reset(){
  red = 0;
  green = 0;
  blue = 0;
  delay(1000);
  drawCanvas();
}

/* Draw checkboard background */
void drawCheck(){
  for (int x = 0; x < width; x += 50) { //x value
    for (int y = 0; y < height; y += 50) { //y value
      if ((x / 10 + y / 10 + 1) % 2 == 0) {
        fill(200);
      } else {
        fill(100);
      }
      rect(x, y, 50, 50);
    }
  }
}

/* Shows help dialog with keybinds */
void help(){
  JOptionPane.showMessageDialog(null, "To draw, hold down the left mouse button while hovering over the white area. \n\n Keys: \n R - increase red value \n Shift + R - decrease red value \n \n G - increase green value \n Shift + G - decrease green value \n \n B - increase blue value \n Shift + B - decrease blue value \n\n Click the colored square in the top left to reset color to black \n\n 1 - draw using a square \n 2 - draw using a circle \n 3 - draw a horizontal line \n 4 - draw a vertical line \n 5 - draw both lines \n\n 0 - reset canvas \n H - show this window");
}

/* Simple log function to print debug info to conosole depending on the debugMode variable */
void log(String msg){
  if(debugMode){
    println("[DEBUG] " + msg);
  }
}
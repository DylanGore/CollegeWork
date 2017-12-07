//Needed for dialog boxes
import javax.swing.*;

//player names
String[] players;

int p1Score = 0;
int p2Score = 0;

//default number of players
int noOfPlayers = 2;

int hCount = 0;
int sCount = 0;

boolean hideShapes = false;
boolean p1Allowed = false;
boolean p2Allowed = false;
boolean debugMode = false;

Shape player1;
Shape player2;

void setup() {
  //Setting default size and color
  size(500, 500);
  resetGame();
}

void draw() {
  drawScoreboard();
  if (hideShapes == true) {
    background(0);
    hideShapes = false;
  }

  //If scores have already been calculated, wait 5 seconds and end game
  if (sCount > 0) {
    delay(5000);
    endGame();
  }

  //If original shapes have been hiddent and both players have made an attempt, calculate scores
  if (hCount > 0 && sCount == 0 && p1Allowed == false && p2Allowed == false) {
    calculateScores(1, player1);
    calculateScores(2, player2);
  }

  drawScoreboard();
}

//Resets all variables and screen for new game to be played
void resetGame() {
  background(0);
  fill(255);
  hideShapes = false;
  hCount = 0;
  sCount = 0;
  p1Score = 0;
  p2Score = 0;
  p1Allowed = false;
  p2Allowed = false;
  createNewGame();
}

//Ends game when called, calculates final scores and asks if user wants to play again
void endGame() {
  String msg = "";
  int again = 0;

  //Check which player won (lowest score wins)
  if (p1Score < p2Score) {
    //P1 Wins
    msg = ("Player 1 wins!");
  } else if (p1Score == p2Score) {
    msg = ("Draw!");
  } else {
    //P2 Wins
    msg = ("Player 2 Wins!");
  }

  if (msg == "") {
    println("Something has gone wrong with scores");
  }

  //Show who won
  JOptionPane.showMessageDialog(null, msg);

  //Ask if playing again 
  again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
  if(again == 0){
    resetGame();
  }else{
    exit();
  }
}

//Runs when mouse pressed
void mousePressed() {
  if (mouseButton == LEFT && p1Allowed == true) {
    fill(255);
    rect(mouseX, mouseY, 50, 50);
    p1Allowed = false;
    player1.setXAnswer(1, mouseX);
    player1.setYAnswer(1, mouseY);
  }
  if (mouseButton == RIGHT && p2Allowed == true && p1Allowed == false) {
    fill(255);
    ellipse(mouseX, mouseY, 50, 50);
    p2Allowed = false;
    player2.setXAnswer(2, mouseX);
    player2.setYAnswer(2, mouseY);
  }
}

//Runs when any key is pressed
void keyPressed() {
  switch(key) {
  case 'h':
    if (hCount == 0) {
      hideShapes = true;
      p1Allowed = true;
      p2Allowed = true;
      hCount++; //makes it so this is only run once per game
    }
    break;
  case 'R':
    println("Resetting Game...");
    resetGame();
    break;
  default:
    println("invalid key '" + key + "' has been pressed!");
    break;
  }
}

//Sets up player names and calls function initPlayers() to create shapes
void createNewGame() {
  players = new String[noOfPlayers];

  for (int i = 0; i < players.length; i++) {
    //Show input dialog to get a name for each player
    players[i] = JOptionPane.showInputDialog("Enter name for \nPlayer " + (i + 1));
  }

  println("Number of players in this game: " + players.length);
  initPlayers();
}

//Draws the scoreboard on the screen
void drawScoreboard() {
  fill(0);
  rect(400, 0, 100, 40);
  fill(255);
  textAlign(LEFT);
  text(players[0], 400, 20);
  text(players[1], 450, 20);
  text(p1Score, 400, 40);
  text(p2Score, 450, 40);
}

//Draws shapes in random locations, 1 for each player
void initPlayers() {
  player1 = new Shape(players[0], 1, 0, 255, 0);
  player2 = new Shape(players[1], 2, 255, 0, 0);
}

//CaLculates scores for each player
void calculateScores(int playerNum, Shape player) {
  int x1 = player.getXPos(playerNum);
  int y1 = player.getYPos(playerNum);

  int xAns = player.getXAnswer(playerNum);
  int yAns = player.getYAnswer(playerNum);

  int scoreX = 0;
  int scoreY = 0;

  //X Coords
  if (x1 > xAns) {
    scoreX = x1 - xAns;
  } else {
    scoreX = xAns - x1;
  }

  //Y Coords
  if (y1 > yAns) {
    scoreY = y1 - yAns;
  } else {
    scoreY = yAns;
  }

  //Set score for correct player
  if (playerNum == 1) {
    p1Score = scoreX + scoreY;
  } else {
    p2Score = scoreX + scoreY;
  }

  //If debugMode is enabled, show specific stats on placement
  if (debugMode) {
    JOptionPane.showMessageDialog(null, "Player " + playerNum + "\n Actual Position: " + x1 + "," + y1 + "\n Answered Position: " + xAns + "," + yAns);
  }

  //makes sure that scores are only calculated once
  sCount = 1;
}
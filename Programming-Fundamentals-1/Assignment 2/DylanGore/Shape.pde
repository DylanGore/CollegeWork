class Shape {

  private int[] xPositions = new int[2];
  private int[] yPositions = new int[2];
  
  private int[] xAnswers = new int[2];
  private int[] yAnswers = new int[2];
  
  private int size = 50; //default shape size
  private String initial; //player initial


  //Constructor - creates shape for each player
  public Shape(String playerName, int playerNum, int red, int green, int blue) {
    this.initial = getInitialFromName(playerName);
    switch(playerNum) {
    case 1:
      xPositions[0] = (int)random(1,500);
      yPositions[0] = (int)random(1,500);
      drawSquare(this.initial, xPositions[0], yPositions[0], red, green, blue);
      break;
    case 2:
      xPositions[1] = (int)random(1,500);
      yPositions[1] = (int)random(1,500);
      drawCircle(this.initial, xPositions[1], yPositions[1], red, green, blue);
      break;
    default:
      println("Invalid shape selected!");
      break;
    }
  }

  //Creates shape - circle
  private void drawCircle(String initial, int xPos, int yPos, int red, int green, int blue) {
    fill(red, green, blue);
    ellipse(xPos, yPos, size, size);
    fill(255);
    textAlign(CENTER);
    text(initial, xPos, yPos);
    textAlign(LEFT);
  }

  //Creates shape - square
  private void drawSquare(String initial, int xPos, int yPos, int red, int green, int blue) {
    fill(red, green, blue);
    rect(xPos, yPos, size, size);
    fill(255);
    textAlign(CENTER);
    text(initial, xPos + size / 2, yPos + size / 2);
    textAlign(LEFT);
  }
  
  //Returns xPos for shape
  public int getXPos(int playerNum){
    int xPos = 0;
    
    if(playerNum == 1){
      xPos = xPositions[0];
    }else if(playerNum == 2){
      xPos = xPositions[1];
    }
    
    return xPos;
  }
  
  
  //Returns  yPos for shape
  public int getYPos(int playerNum){
    int yPos = 0;
    
    if(playerNum == 1){
      yPos = yPositions[0];
    }else if(playerNum == 2){
      yPos = yPositions[1];
    }else{
      println("yPos does not exist for player " + playerNum);
    }
    
    return yPos;
  }
  
  //Returns xAnswer
  public int getXAnswer(int playerNum){
    return xAnswers[playerNum - 1];
  }
  
  //Sets xAnswer
  public void setXAnswer(int playerNum, int ans){
    xAnswers[playerNum - 1] = ans;  
  }
  
  //Returns yAnswer
  public int getYAnswer(int playerNum){
    return yAnswers[playerNum -1];
  }
  
  //Sets yAnswer
  public void setYAnswer(int playerNum, int ans){
    yAnswers[playerNum - 1] = ans;  
  }
  
  //Gets intital from name and converts it to uppercase
  private String getInitialFromName(String name){
    String initial = (name.substring(0,1)).toUpperCase();
    return initial;
  }
}
public abstract class GamePiece {
     String name;
     int healthPoints;
     int baseHealthPoints;
     int block;
     int strength;
     int dexterity;
     int actionPoints;
     int poison;
     int bleed;
     int weak;
     int vulnerable;
     int burn;
     int handSize;
     int baseHandSize;
     int parry;
     boolean isPlayer;

     public void setName(String name) {
          this.name = name;
     }

     public int getParry() {
          return parry;
     }

     public void setParry(int parry) {
          this.parry = parry;
     }

     public int getBurn() {
          return burn;
     }

     public void setBurn(int burn) {
          this.burn = burn;
     }

     public int getPoison() {
          return poison;
     }

     public void setPoison(int poison) {
          this.poison = poison;
     }

     public int getBleed() {
          return bleed;
     }

     public void setBleed(int bleed) {
          this.bleed = bleed;
     }

     public int getWeak() {
          return weak;
     }

     public void setWeak(int weak) {
          this.weak = weak;
     }

     public int getVulnerable() {
          return vulnerable;
     }

     public void setVulnerable(int vulnerable) {
          this.vulnerable = vulnerable;
     }

     public String getName() {
          return name;
     }

     public int getHealthPoints() {
          return healthPoints;
     }

     public void setHealthPoints(int healthPoints) {
          this.healthPoints = healthPoints;
     }

     public int getBaseHealthPoints() {
          return baseHealthPoints;
     }

     public void setBaseHealthPoints(int baseHealthPoints) {
          this.baseHealthPoints = baseHealthPoints;
     }

     public int getBlock() {
          return block;
     }

     public void setBlock(int block) {
          this.block = block;
     }

     public int getStrength() {
          return strength;
     }

     public void setStrength(int strength) {
          this.strength = strength;
     }

     public int getDexterity() {
          return dexterity;
     }

     public void setDexterity(int dexterity) {
          this.dexterity = dexterity;
     }

     public int getActionPoints() {
          return actionPoints;
     }

     public int getHandSize() {
          return handSize;
     }

     public void setHandSize(int handSize) {
          this.handSize = handSize;
     }

     public int getBaseHandSize() {
          return baseHandSize;
     }

     public void setBaseHandSize(int baseHandSize) {
          this.baseHandSize = baseHandSize;
     }

     public void setActionPoints(int actionPoints) {
          this.actionPoints = actionPoints;
     }

     public boolean isPlayer() {
          return isPlayer;
     }

     public String showGamePieceStats() {
          /*
           * vulnerable has some issues with timing involving the players turn and when the attack phase happens.
           * This is why there is some bullshit going on here
           * */
          String status;

          String healthString = name + " [HP:" + healthPoints + "/" + baseHealthPoints + "]";
          String blockString = "[BLOCK: " + block + "]";
          String strString = "[STR: " + strength + "]";
          String dexString = "[DEX " + dexterity + "]";
          String poisonString = "[POISON " + +poison + "]";
          String vulnerableString = "[VULNERABLE " + (vulnerable -1) + "]";
          String weakString = "[WEAK " + weak + "]";
          String parryString = "[PARRY " + parry + "]";

          status = healthString;
          if (block != 0) {
               status += blockString;
          }
          if (strength != 0) {
               status += strString;
          }
          if (dexterity != 0) {
               status += dexString;
          }
          if (vulnerable != 0 && vulnerable != 1 ) {
               status += vulnerableString;
          }
          if (weak != 0) {
               status += weakString;
          }
          if (poison != 0) {
               status += poisonString;
          }
          if(parry != 0){
               status += parryString;
          }

          return status;
     }

     public static void decayPoison(GamePiece gamePiece) {
          if (gamePiece.getPoison() > 0) {
               gamePiece.setPoison(gamePiece.getPoison() - 1);
          }
     }

     public static void decayVulnerable(GamePiece gamePiece) {
          if (gamePiece.getVulnerable() > 0) {
               gamePiece.setVulnerable(gamePiece.getVulnerable() - 1);
          }
     }

     public static void decayWeak(GamePiece gamePiece) {
          if (gamePiece.getWeak() > 0) {
               gamePiece.setWeak(gamePiece.getWeak() - 1);
          }
     }


}

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player extends GamePiece{

    private int actionPoints;
    private int baseActionPoints;
    private int playerLevel;

    private int gold;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.healthPoints = 100;
        this.block = 0;
        this.actionPoints = 3;
        this.strength = 0;
        this.dexterity = 0;
        this.handSize = 5;
        this.playerLevel = 1;
        this.baseHealthPoints = healthPoints;
        this.baseActionPoints = actionPoints;
        this.baseHandSize = handSize;
        this.gold = 100;
        this.isPlayer = true;
    }
    // Load saved player constructor
    public Player(String name, int healthPoints, int baseHealthPoints, int playerLevel,int gold) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.baseHealthPoints = baseHealthPoints;
        this.actionPoints = 3;
        this.baseActionPoints = actionPoints;
        this.strength = 0;
        this.dexterity = 0;
        this.block = 0;
        this.handSize = 5;
        this.baseHandSize = handSize;
        this.playerLevel = playerLevel;
        this.gold = gold;
        this.isPlayer = true;

    }

    public static Player makePlayer() {
        System.out.print("Enter player name: ");
        String playerName = Game.getInput();
        System.out.println();
        Player player = new Player(playerName);
        return player;
    }

    public static void savePlayer(Player player) {
        try (FileWriter playerSav = new FileWriter("PlayerSave.txt")) {
            playerSav.write(player.playerValueDump());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Player: " + player.getName() + " was saved");
    }

    public static Player loadPlayer() {
        try {
            Scanner scanner = new Scanner(new FileReader("PlayerSave.txt"));
            scanner.useDelimiter(",");
            String name = scanner.next();
            int healthPoints = scanner.nextInt();
            int baseHealthPoints = scanner.nextInt();
            int playerLevel = scanner.nextInt();
            int gold = scanner.nextInt();
            scanner.close();
            System.out.println(name + " level: "+ playerLevel + " was loaded" );
            return new Player(name, healthPoints, baseHealthPoints, playerLevel, gold);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Error loading save, please create a new Character");
        return makePlayer();
    }

    // returns values needed to load a previous save
    public String playerValueDump() {
        return this.name + "," + this.healthPoints + "," + this.baseHealthPoints + "," + this.playerLevel + "," + this.gold;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public int getBaseActionPoints() {
        return baseActionPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getHandSize() {
        return handSize;
    }

    public int getBaseHealthPoints() {
        return baseHealthPoints;
    }

    public int getBlock() {
        return block;
    }

    public int getBaseHandSize() {
        return baseHandSize;
    }

    public int getGold(){
        return gold;
    }



    //Setters
    public void setBaseHandSize(int baseHandSize) {
        this.baseHandSize = baseHandSize;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints > baseHealthPoints){
            healthPoints = baseHealthPoints;
        }
        this.healthPoints = healthPoints;
    }

    public void setBaseHealthPoints(int baseHealthPoints) {
        this.baseHealthPoints = baseHealthPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setHandSize(int handSize) {
        this.handSize = handSize;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public void levelUp(){
        this.setPlayerLevel(this.playerLevel+= 1);
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void setGold(int gold){
        if(gold <0){
            gold = 0;
        }
        this.gold = gold;
    }

}

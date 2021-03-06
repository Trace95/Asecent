import java.util.Random;

public class Enemy extends GamePiece {

    // Constructor
    private Enemy(String name, int healthPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.baseHealthPoints = healthPoints;
        this.block = 0;
        this.strength = 0;
        this.dexterity = 0;
    }

    // Custom methods
    public static Enemy makeEnemy(int playerLevel) {
        int enemyHP = 50 * playerLevel / 2;
        String enemyName;

        // Array of EnemyNames
        String[] names = new String[5];
        names[0] = "Mobu";
        names[1] = "Wulf";
        names[2] = "Lo-ken";
        names[3] = "Dimitri";
        names[4] = "Sigfried";

        Random rand = new Random(); //instance of random class
        int upperbound = names.length;
        //generate random values equal to length of the name array
        int randomValue = rand.nextInt(upperbound);

        switch (randomValue) {
            case 0:
                enemyName = names[0];
                break;
            case 1:
                enemyName = names[1];
                break;
            case 2:
                enemyName = names[2];
                break;
            case 3:
                enemyName = names[3];
                break;
            case 4:
                enemyName = names[4];
                break;
            default:
                enemyName = "Error in Enemy.makeEnemy()" + randomValue;
                break;
        }
        Enemy enemy = new Enemy(enemyName, enemyHP);
        return enemy;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getBaseHealthPoints() {
        return baseHealthPoints;
    }

    public int getBlock() {
        return block;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setBaseHealthPoints(int baseHealthPoints) {
        this.baseHealthPoints = baseHealthPoints;
    }

    public void setBlock(int block) {
        if (block < 0) { // prevent negative block value
            block = 0;
        }
        this.block = block;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public String getIntent(Enemy enemy, Deck deck){
        String intent = "";
        deck.drawHand(1);



        if(deck.getHandPile().get(0).getType().equals("Attack")){
            intent += enemy.getName() + " is attacking for " + deck.getHandPile().get(0).getAttack();
            if(enemy.getStrength() != 0){
                intent += " is attacking for *" + deck.getHandPile().get(0).getAttack() + enemy.getStrength();
            }

            if(deck.getHandPile().get(0).getHits() > 1){
                intent += ", "+deck.getHandPile().get(0).getHits() + " times";
            }
        }else{
            intent = enemy.getName() + " is going to use a skill";
        }

        return intent;
    }
}

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
        this.isPlayer = false;
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

        enemyName = switch (randomValue) {
            case 0 -> names[0];
            case 1 -> names[1];
            case 2 -> names[2];
            case 3 -> names[3];
            case 4 -> names[4];
            default -> "Error in Enemy.makeEnemy()" + randomValue;
        };
        return new Enemy(enemyName, enemyHP);
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

    public String getIntent(Enemy enemy, Deck deck, Player player) {
        String intent = "";
        deck.drawHand(1);
        Card card = deck.getHandPile().get(0);
        boolean playerVulnerable = false;
        boolean isWeak = false;


        if (player.getVulnerable() > 0) {
            playerVulnerable = true;
        }
        if (enemy.getWeak() > 0) {
            isWeak = true;
        }

        int cardDamage = card.getAttack();
        int attack = cardDamage + enemy.getStrength();
        int damage = attack * card.getHits();

        if(isWeak){
            damage = (int) Math.round(damage * 0.75);
        }

        if (playerVulnerable){
            damage = (int) Math.round(damage * 1.25);
        }

        if (card.getType().equals(CardType.Attack) && card.getHits() <= 1) {
            intent += enemy.getName() + " is attacking for " + damage + " damage";
        } else if (card.getType().equals(CardType.Attack) && card.getHits() > 1) {
            intent += enemy.getName() + " is attacking for " + (card.getAttack() + enemy.getStrength()) + " damage " + card.getHits() + " times";
        } else {
            intent = enemy.getName() + " is going to use a skill";
        }
        return intent;
    }
}

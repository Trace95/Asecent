public class Card {
    private String name;
    private int cost;
    private String type;
    private int attack;
    private int block;
    private String effect;
    private int hits;
    private int draw;
    private int goldCost;
    private int poison;
    private int vulnerable;
    private int weak;
    private int parry;
    private int strength;
    private int dexterity;
    private boolean exhaust;

    //Constructor


    public Card(String name, int cost, String type, int attack, int block, String effect, int hits, int draw, int goldCost, int poison, int vulnerable, int weak, int parry, boolean exhaust, int strength, int dexterity) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.attack = attack;
        this.block = block;
        this.effect = effect;
        this.hits = hits;
        this.draw = draw;
        this.goldCost = goldCost;
        this.poison = poison;
        this.vulnerable = vulnerable;
        this.weak = weak;
        this.parry = parry;
        this.exhaust = exhaust;
        this.strength = strength;
        this.dexterity = dexterity;
    }

    public String getCardDisplay() {
        String cardDisplay = name + " (Type:" + type + ")(Cost: " + cost + ") \n" + "Effect: " + effect ;
        if(exhaust){
            cardDisplay = cardDisplay + ". Exhausts";
        }

        return cardDisplay;
    }

    // Base attribute getters
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getBlock() {
        return block;
    }

    public String getEffect() {
        return effect;
    }

    public int getHits() {
        return hits;
    }

    public int getDraw() {
        return draw;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public int getPoison() {
        return poison;
    }

    public int getVulnerable() {
        return vulnerable;
    }

    public int getWeak() {
        return weak;
    }

    public int getParry() {
        return parry;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public boolean isExhaust() {
        return exhaust;
    }

    @Override
    public String toString() {
        return name + "," + cost + "," + type + "," + attack + "," + block + "," + effect + "," + hits + "," + draw + "," + goldCost + "," + poison + "," + vulnerable + "," + weak + "," + parry + "," + strength + "," + dexterity + "," + exhaust;
    }

}


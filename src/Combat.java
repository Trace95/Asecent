public class Combat {

    public static boolean playCard(GamePiece attacker, GamePiece defender, Card card, Deck deck) {

        //check is player can pay for cost
        if (attacker.getActionPoints() < card.getCost()) {
            System.out.println("Insufficient ability points");
            return false;
        }
        // pay card cost
        attacker.setActionPoints(attacker.getActionPoints() - card.getCost());
        // DrawStep
        drawStep(card.getDraw(), deck, attacker);
        // make variables
        int realAttack = card.getAttack() + attacker.getStrength(); // attack + strength modifier
        int damage = (realAttack * (card.getHits() - defender.getParry())) - defender.getBlock(); // damage is the value dealt to health
        int blockedDamage = realAttack * card.getHits() - damage; // total damage blocked
        int realBlock = card.getBlock() + attacker.getDexterity();


        // edit damage due to status effects
        if (attacker.getWeak() > 0) {
            damage = (int) Math.round(damage * 0.70);
        }
        if (defender.getVulnerable() > 0) {
            damage = (int) Math.round(damage * 1.30);
        }

        if (damage < 0) { // prevent negative damage affecting calculations
            damage = 0;
        }
        // set status effects
        attacker.setStrength(attacker.getStrength() + card.getStrength());
        attacker.setDexterity(attacker.getDexterity() + card.getDexterity());
        defender.setPoison(defender.getPoison() + card.getPoison());
        defender.setWeak(defender.getWeak() + card.getWeak());
        defender.setVulnerable(defender.getVulnerable() + card.getVulnerable());
        defender.setParry(defender.getParry() + card.getParry());

        //set defender values
        defender.setBlock(defender.getBlock() - blockedDamage); // remove block
        defender.setHealthPoints(defender.getHealthPoints() - damage);

        // prints the combat log
        combatDisplay(damage, realAttack, blockedDamage, realBlock, attacker, defender, card);
        return true;
    }

    public static void poisonStep(GamePiece defender) {
        if (defender.getPoison() > 0) {
            System.out.println(defender.getName() + " takes " + defender.getPoison() + " poison damage");
            defender.setHealthPoints(defender.getHealthPoints() - defender.getPoison());
        }
    }

    private static void drawStep(int drawAmount, Deck deck, GamePiece attacker) {
        for (int i = 0; i < drawAmount; i++) {
            deck.getHandPile().add(deck.draw());
            System.out.println(attacker.getName() + " draws " + drawAmount +"cards");
        }
        attacker.setHandSize(attacker.getHandSize() + drawAmount);
    }

    // CardUI
    public static void combatDisplay(int damage, int realAttack, int blockedDamage, int realBlock, GamePiece attacker, GamePiece defender, Card card) {
        System.out.println("************************\uD835\uDC02\uD835\uDC0E\uD835\uDC0C\uD835\uDC01\uD835\uDC00\uD835\uDC13************************"); // combat
        // declare player uses card

        if(attacker.isPlayer){
            System.out.println(attacker.getName() + " pays " + card.getCost() + " AP and uses " + card.getName() + ".");
        }else{
            System.out.println(attacker.getName() + " uses " + card.getName() + ".");
        }

        if (card.getAttack() > 0 && card.getHits() <= 1) { // make sure the card has an base card.getAttack value above 0.
            System.out.println(card.getName() + " deals " + realAttack + " damage. ");
        } else if (card.getAttack() > 0 && card.getHits() > 1) {
            System.out.println(card.getName() + " Attacks for " + realAttack + " damage " + card.getHits() + " times");
        }
        if (damage > 0 && defender.getBlock() == 0) {// no block
            System.out.println(defender.getName() + " loses " + damage + " health points");
        } else if (damage > 0 && defender.getBlock() > 0) { //some blocked
            System.out.print(defender.getName() + " blocks " + blockedDamage + " damage ");
            System.out.println("and loses " + damage + " HP");
        } else if (card.getAttack() > 0 && damage <= 0) { // all blocked
            System.out.println(defender.getName() + "s block absorbed the damage");
        }
        if (card.getBlock() > 0) {
            int thisTurnsBlock = card.getBlock() + attacker.getDexterity();
            attacker.setBlock(thisTurnsBlock + attacker.getBlock());
            System.out.println(card.getName() + " increased " + attacker.getName() + "s block by " + realBlock);
        }

        // show status effects being applied
        if (card.getPoison() > 0) {
            System.out.println(defender.getName() + " received " + card.getPoison() + " points of poison");
        }
        if (card.getStrength() > 0) {
            System.out.println(card.getName() + " applied " + card.getStrength() + " points of strength");
        }
        if (card.getDexterity() > 0) {
            System.out.println(card.getName() + " applied " + card.getDexterity() + " points of dexterity");
        }
        if (card.getVulnerable() > 0) {
            System.out.println(defender.getName() + " received " + card.getVulnerable()   + " points of vulnerability");
        }
        if (card.getWeak() > 0) {
            System.out.println(defender.getName() + " received " + card.getWeak() + " points of weakness");
        }
        if (card.getParry() > 0) {
            System.out.println(attacker.getName() + " received " + card.getParry() + " points of parry");
        }

        // cards drawn
        if (card.getDraw() > 0) {
            System.out.println(attacker.getName() + " drew " + card.getDraw() + " cards ");
        }
        System.out.println("************************\uD835\uDC02\uD835\uDC0E\uD835\uDC0C\uD835\uDC01\uD835\uDC00\uD835\uDC13************************");
    }


}

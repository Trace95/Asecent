import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Deck {
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;
    private ArrayList<Card> handPile;
    private ArrayList<Card> exhaustPile;

    // Constructor
    public Deck() {
        this.drawPile = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.handPile = new ArrayList<>();
        this.exhaustPile = new ArrayList<>();
    }

    // HandPile
    public Card draw() {// draw a single Card
        Card card;
        if (drawPile.size() == 0) {
            drawPile = getDiscardPile();
            discardPile = new ArrayList<>();
            shuffle();
        }
        card = drawPile.get(0);
        drawPile.remove(0);
        return card;
    }

    public void drawHand(int handSize) {

        for (int i = 0; i < handSize; i++) {
            handPile.add(draw());
        }
    }

    public void showHand() {
        int position = 1; // position in hand
        System.out.println("*************************\uD835\uDC07\uD835\uDC00\uD835\uDC0D\uD835\uDC03*************************");
        for (Card card : handPile) {
            System.out.println(position + "." + card.getCardDisplay() + "\n");
            position++;
        }
        showNonCombatOptions();
        System.out.println("*************************\uD835\uDC07\uD835\uDC00\uD835\uDC0D\uD835\uDC03*************************");
    }

    //DiscardPile
    public void showDiscard() {
        int position = 1;

        System.out.println("*********************\uD835\uDC03\uD835\uDC08\uD835\uDC12\uD835\uDC02\uD835\uDC00\uD835\uDC11\uD835\uDC03 \uD835\uDC0F\uD835\uDC08\uD835\uDC0B\uD835\uDC04*********************");
        if (getDiscardPileCount() == 0) {
            System.out.println("                        <EMPTY>");
        } else {
            for (int i = 0; i < getDiscardPileCount(); i++) {
                Card card = discardPile.get(i);
                System.out.println(position + "." + card.getCardDisplay() + "\n");
                position++;
            }
        }
        System.out.println("*********************\uD835\uDC03\uD835\uDC08\uD835\uDC12\uD835\uDC02\uD835\uDC00\uD835\uDC11\uD835\uDC03 \uD835\uDC0F\uD835\uDC08\uD835\uDC0B\uD835\uDC04*********************");
    }

    public void discard(int cardPosition) {
        if (cardPosition > -1) {
            discardPile.add(handPile.get(cardPosition)); // add card into discard pile
            handPile.remove(cardPosition);// remove card from hand
        }
    }

    public void discardHand() {
        discardPile.addAll(handPile);
        handPile.clear();
    }

    public int getDiscardPileCount() {

        if (discardPile == null) {
            return 0;
        } else {
            return discardPile.size();
        }
    }

    // ExhaustPile
    public void exhaustCard(int cardPosition) {
        if (cardPosition > -1) {
            exhaustPile.add(handPile.get(cardPosition)); // add card into exhaust pile
            handPile.remove(cardPosition);// remove card from hand
        }
    }

    public void showExhaust() {
        int position = 1;
        System.out.println("*********************\uD835\uDC04\uD835\uDC17\uD835\uDC07\uD835\uDC00\uD835\uDC14\uD835\uDC12\uD835\uDC13 \uD835\uDC0F\uD835\uDC08\uD835\uDC0B\uD835\uDC04*********************");
        if (getExhaustPile().size() == 0) {
            System.out.println("                        <EMPTY>");
        } else {
            for (int i = 0; i < exhaustPile.size(); i++) {
                Card card = exhaustPile.get(i);
                System.out.println(position + "." + card.getCardDisplay() + "\n");
                position++;
            }
        }
        System.out.println("*********************\uD835\uDC04\uD835\uDC17\uD835\uDC07\uD835\uDC00\uD835\uDC14\uD835\uDC12\uD835\uDC13 \uD835\uDC0F\uD835\uDC08\uD835\uDC0B\uD835\uDC04*********************");
    }

    public int getExhaustPileCount() {
        if (exhaustPile == null) {
            return 0;
        }
        return exhaustPile.size();
    }


    //DeckPile
    public void packUpDeck() {
        drawPile.addAll(discardPile);
        discardPile.clear();

        drawPile.addAll(handPile);
        handPile.clear();

        drawPile.addAll(exhaustPile);
        exhaustPile.clear();
    }

    public void shuffle() {
        Collections.shuffle(drawPile);
    }

    public void showDrawPile(Deck deck) {
        int position = 1;
        // have to sort this into alphabetical order so user cant see whats coming next in their deck
        ArrayList<Card> sortedDeck = sortDeckByCardName(deck.handPile);
        System.out.println("*************************\uD835\uDC03\uD835\uDC04\uD835\uDC02\uD835\uDC0A*************************");
        for (int i = 0; i < sortedDeck.size(); i++) {
            Card card = sortedDeck.get(i);
            if (position == sortedDeck.size()) {
                System.out.println(position + "." + card.getCardDisplay());
            } else {
                System.out.println(position + "." + card.getCardDisplay() + "\n");
                position++;
            }
        }
        System.out.println("*************************\uD835\uDC03\uD835\uDC04\uD835\uDC02\uD835\uDC0A*************************");
    }

    public void showDeckStatusUI(int actionPoints) {
        System.out.println("ActionPoints: " + actionPoints + " Deck: " + getDrawPile().size() + "   Discard: " + getDiscardPileCount() + " Exhaust: " + getExhaustPileCount());
    }

    public static void showNonCombatOptions() {
        System.out.println("End turn: E Quit: Q Deck: D Discard: Z Exhaust: X");
    }

    public ArrayList<Card> sortDeckByCardName(ArrayList<Card> deck) {
        Comparator<Card> cardNameComparator = Comparator.comparing(Card::getName);
        deck.sort(cardNameComparator);
        return deck;
    }


    // Deck loading & Saving
    public static Deck makeBaseDeck() { //  makes base deck
        Deck deck = new Deck();
        Card strike = new Card("Strike", 1, "Attack", 6, 0, "Deal 6 damage.", 1, 0, 0, 0, 0, 0, 0, false, 0, 0);
        Card defend = new Card("Defend", 1, "Skill", 0, 5, "Block 5 damage.", 0, 0, 0, 0, 0, 0, 0, false, 0, 0);
        Card neutralize = new Card("Neutralize", 0, "Attack", 3, 0, "Deal 3 damage. Apply 1 Weak", 1, 0, 45, 0, 0, 1, 0, true, 0, 0);
        Card backFlip = new Card("Backflip", 1, "Skill", 0, 5, "Gain 5 block. Draw 2 cards", 0, 2, 66, 0, 0, 0, 0, false, 0, 0);
        Card deadlyPoison = new Card("Deadly Poison", 1, "Skill", 0, 0, "Apply 5 poison", 0, 0, 80, 5, 0, 0, 0, false, 0, 0);
        Card deflect = new Card("Deflect", 0, "Skill", 0, 4, "Gain 4 block.", 0, 0, 45, 0, 0, 0, 0, false, 0, 0);
        Card poisonStab = new Card("Poison Stab", 1, "Attack", 6, 0, "Deal 6 damage. Apply 3 poison.", 1, 0, 70, 3, 0, 0, 0, false, 0, 0);
        Card dash = new Card("Dash", 2, "Attack", 6, 6, "Deal 6 damage. Gain 6 block.", 1, 0, 0, 0, 0, 0, 0, true, 0, 0);
        Card powerUp = new Card("Power-Up", 1, "Skill", 0, 0, "Gain 5 strength", 0, 0, 50, 0, 0, 0, 0, true, 5, 0);


        deck.drawPile.add(strike);
        deck.drawPile.add(strike);
        deck.drawPile.add(strike);
        deck.drawPile.add(strike);
        deck.drawPile.add(defend);
        deck.drawPile.add(defend);
        deck.drawPile.add(defend);
        deck.drawPile.add(defend);


        deck.drawPile.add(neutralize);
        deck.drawPile.add(backFlip);
        deck.drawPile.add(deadlyPoison);
        deck.drawPile.add(poisonStab);
        deck.drawPile.add(powerUp);
        deck.drawPile.add(dash);
        deck.drawPile.add(deflect);


        return deck;
    }

    public static Deck makeCardLibrary() {
        Deck cardLibrary = new Deck();
        cardLibrary =loadDeck("CardLibrary");
        return cardLibrary;

    }

    public static Deck makeEnemyDeck(Enemy enemy) {
        Deck deck = new Deck();
        // Make sure to make vulnerable values 1 higher than what they should be

        File file = new File("D:/Programming/JAVA/Gladiator 0.7/" + enemy.getName() + ".txt");
        if (file.exists()) {
            return loadDeck(enemy.getName());
        }

        //Default implementation
        Card heavyStrike = new Card("Heavy strike", 0, "Attack", 12, 0, "Deals 12 damage and applies 1 point of vulnerable", 1, 0, 0, 0, 2, 0, 0, false, 0, 0);
        Card sting = new Card("Sting", 0, "Attack", 5, 0, "Deals 5 damage and applies 5 point of poison", 1, 0, 0, 5, 0, 0, 0, false, 0, 0);
        Card leer = new Card("Leer", 0, "Skill", 0, 10, "Gain 10 block and apply 1 weak and 1 vulnerable", 0, 0, 0, 0, 2, 1, 0, false, 0, 0);
        Card furySwipes = new Card("Fury Swipes", 0, "Attack", 3, 0, "deals 3 damage 5 times", 5, 0, 0, 0, 0, 0, 0, false, 0, 0);
        Card enrage = new Card("Enrage", 0, "Skill", 0, 0, "Gains 1 parry and gains 3 strength", 5, 0, 0, 0, 0, 0, 0, false, 3, 0);
        Card defenciveStance = new Card("Defencive Stance", 0, "Skill", 0, 0, "Gains 1 parry, 5 block and 3 dexterity", 0, 0, 0, 0, 0, 0, 1, false, 0, 3);
        deck.drawPile.add(heavyStrike);
        deck.drawPile.add(sting);
        deck.drawPile.add(leer);
        deck.drawPile.add(furySwipes);
        deck.drawPile.add(enrage);
        deck.drawPile.add(defenciveStance);

        deck.shuffle();


        return deck;
    }

    public static Deck loadDeck(String deckName) {
        Deck deck = new Deck();
        int cardCount = 0;
        try {
            Scanner scanner = new Scanner(new FileReader(deckName + ".txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                String name = scanner.next();
                int cost = scanner.nextInt();
                String type = scanner.next();
                int attack = scanner.nextInt();
                int block = scanner.nextInt();
                String effect = scanner.next();
                int hits = scanner.nextInt();
                int draw = scanner.nextInt();
                int goldCost = scanner.nextInt();
                int poison = scanner.nextInt();
                int vulnerable = scanner.nextInt();
                int weak = scanner.nextInt();
                int parry = scanner.nextInt();
                int strength = scanner.nextInt();
                int dexterity = scanner.nextInt();
                boolean exhaust = scanner.nextBoolean();


                Card card = new Card(name, cost, type, attack, block, effect, hits, draw, goldCost, poison, vulnerable, weak, parry, exhaust, strength, dexterity);
                deck.drawPile.add(card);
                cardCount++;

            }
            scanner.close();
            System.out.println("Deck of " + cardCount + " cards was loaded");
            return deck;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Error loading saved deck, please create new deck");
        return makeBaseDeck();
    }

    public static void saveDeck(Deck deck, String name) {
        try (FileWriter playerSav = new FileWriter(name + ".txt")) {
            playerSav.write(deckValueDump(deck));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String deckValueDump(Deck deck) { // returns deck values as a string separated by a ","
        Card card;
        StringBuilder deckValueDump = new StringBuilder();
        for (int i = 0; i < deck.drawPile.size(); i++) {
            card = deck.drawPile.get(i);
            String cardDesc = card.toString();
            if (i == deck.drawPile.size() - 1) {
                deckValueDump.append(cardDesc);
            } else {
                deckValueDump.append(cardDesc).append(",");
            }

        }
        return deckValueDump.toString();
    }


    //Getters
    public ArrayList<Card> getDrawPile() {
        return drawPile;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    public ArrayList<Card> getHandPile() {
        return handPile;
    }

    public ArrayList<Card> getExhaustPile() {
        return exhaustPile;
    }
}

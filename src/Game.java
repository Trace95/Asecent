import java.io.File;
import java.util.Random;
import java.util.Scanner;


public class Game {
    private static final String playerSaveLocation = "D:/Programming/JAVA/Gladiator 0.7/PlayerSave.txt";
    private static final String playerDeckLocation = "D:/Programming/JAVA/Gladiator 0.7/PlayerDeck.txt";

    public static void loadGameMenu() {
        File file = new File(playerSaveLocation);
        int loadSave = 0;
        if (file.exists()) {
            System.out.println("Continue previous save?\n1.Yes  2.No");
            loadSave = getIntInput(1, 2);
        }

        Player player;
        Deck deck;

        if (loadSave == 1) {
            player = Player.loadPlayer(); // loads player
            deck = Deck.loadDeck("PlayerDeck");      // loads deck
        } else{
            player = Player.makePlayer(); // makes new player
            deck = Deck.makeBaseDeck();  // makes new deck
        }
        gameStart(player, deck);
    }

    public static void gameStart(Player player, Deck deck) {
        Enemy enemy = Enemy.makeEnemy(player.getPlayerLevel());
        Deck enemyDeck = Deck.makeEnemyDeck(enemy);
        TurnFlow.combat(player, enemy, deck, enemyDeck); // starts fighting sim
        continueMenu(player, deck);
    }

    private static void continueMenu(Player player, Deck deck) {
        postCombatFlow(deck, player);
        System.out.println("1.Continue");
        System.out.println("2.Save & Quit");

        while (true) {
            switch (getIntInput(1, 2)) {
                case 1 -> gameStart(player, deck);
                case 2 -> {
                    Player.savePlayer(player);
                    Deck.saveDeck(deck, "PlayerDeck");
                    System.exit(3);
                }
                default -> System.out.println("Please enter 1 or 2");

            }
        }


    }

    private static void postCombatFlow(Deck deck, Player player) {
        rewardMenu(deck);
        Shop.shopMenu(player, deck);
    }

    public static void rewardMenu(Deck deck) {
        Deck cardLibrary = Deck.makeCardLibrary();
        cardLibrary.shuffle();
        int index = 1;
        cardLibrary.drawHand(3);
        boolean loop = true;
        System.out.println("********************Reward***************************");
        System.out.println("Select card to add to deck \n");
        for (Card card : cardLibrary.getHandPile()) {
            System.out.println(index + "." + cardLibrary.getHandPile().get(index - 1).getCardDisplay() + "\n");
            index++;
        }
        System.out.println("S = Skip adding card");
        System.out.println("********************Reward***************************");

        while (loop) {
            String input = getStringInput();
            switch (input) {
                case "1", "2", "3" -> {
                    deck.getDrawPile().add(cardLibrary.getHandPile().get(Integer.parseInt(input) - 1));
                    System.out.println(cardLibrary.getHandPile().get(Integer.parseInt(input) - 1).getName() + " was added to the deck");
                    loop = false;
                }
                case "s", "S" -> loop = false;
                default -> System.out.println(input + " is not a valid input.\nPlease Enter a valid input");
            }
        }


    }

    public static void rest(Player player) {
        System.out.println("*********************Rest****************************");
        int hp = player.getHealthPoints();
        int recover = player.getBaseHealthPoints() / 3;
        player.setHealthPoints(player.getHealthPoints() + recover);
        int recovered = hp - player.getHealthPoints();

        System.out.println(player.getName() + " takes a rest and recovered " + recovered + " hp");
        System.out.println("*********************Rest****************************");
    }

    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getIntInput(int lowerBound, int upperbound) {
        Scanner scanner = new Scanner(System.in);
        int input;
        try {
             input = scanner.nextInt();
            if (input >= lowerBound && input <= upperbound) {
                return input;
            }else {
                System.out.println("Please enter a number between " + lowerBound + " and " + upperbound);
            }
        } catch (Exception e) {
            System.out.println("Please only enter numbers");
        }
        scanner.reset();
        return getIntInput(lowerBound,upperbound);
    }

    public static void deleteSave() {
        File playerSave = new File(playerSaveLocation);
        File playerDeck = new File(playerDeckLocation);
        if (playerSave.exists()) {
            playerSave.delete();
            System.out.println("Player deleted");
        }
        if (playerDeck.exists()) {
            playerDeck.delete();
            System.out.println("Deck deleted");
        }

    }

    public static int getRandomValue(int upperBound) {
        Random rand = new Random(); //instance of random class
        return rand.nextInt(upperBound);

    }

    public static Boolean getConfirmation() {
        System.out.println("1.Yes 2.No");

        switch (Game.getStringInput()) {
            case "1" -> {
                return true;
            }
            case "2" -> {
                return false;
            }
            default -> {
                System.out.println("Choose 1 or 2");
                return getConfirmation();
            }
        }
    }
}


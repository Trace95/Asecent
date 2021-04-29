import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Game {


    private static Scanner scanner = new Scanner(System.in);

    public static void loadGameMenu() {
        File file = new File("D:/Programming/JAVA/Gladiator 0.7/PlayerSave.txt");
        String loadSave;
        if (file.exists()) {
            System.out.println("Continue previous save?\n1.Yes  2.No");
            loadSave = getInput();
        } else {
            loadSave = "2";
        }

        Player player = null;
        Deck deck = null;

        if (loadSave.equals("1")) {
            player = Player.loadPlayer(); // loads player
            deck = Deck.loadDeck("PlayerDeck");      // loads deck
        } else if (loadSave.equals("2")) {
            player = Player.makePlayer(); // makes new player
            deck = Deck.makeBaseDeck();  // makes new deck
        } else {
            System.out.println("Please enter 1 or 2 \n");
            loadGameMenu();
        }
        gameStart(player, deck);
    }

    public static void gameStart(Player player, Deck deck) {
        Enemy enemy = Enemy.makeEnemy(player.getPlayerLevel());
        Deck enemyDeck = Deck.makeEnemyDeck(enemy);
        TurnFlow.combat(player, enemy, deck,enemyDeck); // starts fighting sim
        continueMenu(player, deck);
    }

    private static void continueMenu(Player player, Deck deck) {
        postMatchFlow(deck, player);
        System.out.println("1.Continue");
        System.out.println("2.Save & Quit");

        while (true) {
            String input = getInput();
            switch (input) {
                case "1" -> {
                    gameStart(player, deck);
                    break;
                }
                case "2" -> {
                    Player.savePlayer(player);
                    Deck.saveDeck(deck);
                    System.exit(3);
                    break;
                }
                default -> System.out.println("Please enter 1 or 2");

            }
        }


    }

    private static void postMatchFlow(Deck deck, Player player) {
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
            String input = getInput();
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


    public static String getInput() {
        return scanner.nextLine();
    }

    public static void deleteSave() {
        File playerSave = new File("D:/Programming/JAVA/Gladiator 0.7/PlayerSave.txt");
        File playerDeck = new File("D:/Programming/JAVA/Gladiator 0.7/PlayerDeck.txt");
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


}

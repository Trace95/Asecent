import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Game {


    private static Scanner scanner = new Scanner(System.in);

    public static void loadGameMenu() {
        File file = new File("E:/Programming/JAVA/Gladiator 0.7/PlayerSave.txt");
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
            player = Player.loadPlayer(); // makes player
            deck = Deck.loadDeck();      // makes deck
        } else if (loadSave.equals("2")) {
            player = Player.makePlayer(); // makes player
            deck = deck.makeBaseDeck();  // makes deck
        } else {
            System.out.println("Please enter 1 or 2 \n");
            loadGameMenu();
        }
        gameStart(player, deck);
    }

    public static void gameStart(Player player, Deck deck) {
        Enemy enemy = Enemy.makeEnemy(player.getPlayerLevel());
        TurnFlow.combat(player, enemy, deck); // starts fighting sim
        continueMenu(player, deck);
    }

    private static void continueMenu(Player player, Deck deck) {
        postMatchFlow(deck, player);
        System.out.println("1.Continue");
        System.out.println("2.Save & Quit");
        String input = getInput();
        switch (input) {
            case "1":
                gameStart(player, deck);
                break;
            case "2":
                Player.savePlayer(player);
                Deck.saveDeck(deck);
                System.exit(3);
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

        while (loop == true) {
            String input = getInput();
            switch (input) {
                case "1", "2", "3":
                    deck.getDrawPile().add(cardLibrary.getHandPile().get(Integer.parseInt(input) - 1));
                    System.out.println(cardLibrary.getHandPile().get(Integer.parseInt(input) - 1).getName() + " was added to the deck");
                    loop = false;
                    break;
                case "s", "S":
                    loop = false;
                    break;
                default:
                    System.out.println(input + " is not a valid input.\nPlease Enter a valid input");
                    break;
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
        String input = scanner.nextLine();

        return input;
    }

    public static void deleteSave() {
        File playerSave = new File("E:/Programming/Gladiator 0.7/PlayerSave.txt");
        File playerDeck = new File("E:/Programming/Gladiator 0.7/PlayerDeck.txt");
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
        int value = rand.nextInt(upperBound);
        return value;

    }


}

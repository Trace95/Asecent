
public class TurnFlow {

    public static void combat(Player player, Enemy enemy, Deck deck) {
        // shuffle the deck
        deck.shuffle();
        // make turn counter
        int turn = 1;
        //Print out Player name vs Enemy name
        System.out.println(player.getName() + " Versus " + enemy.getName());
        // make enemy deck
        Deck enemyDeck = Deck.makeEnemyDeck();

        while (enemy.getHealthPoints() > 0) {
            System.out.println("\nTurn: " + turn + " Level: " + player.getPlayerLevel());
            //turn counter
            turn++;
            System.out.print(enemy.showGamePieceStats() + " ");
            System.out.println(player.showGamePieceStats());
            // Show enemy intent
            System.out.println(enemy.getIntent(enemy, enemyDeck));
            // Make a hand of cards
            deck.drawHand(player.getHandSize());
            // Show hand of cards
            deck.showHand();
            //show UI
            deck.showDeckStatusUI(player.getActionPoints());
            playerIntent(deck, player, enemy, enemyDeck);
            resetPlayer(player, false);
            resetEnemy(enemy);
        }
        deck.packUpDeck();
        resetPlayer(player, true);
    }

    public static void playerIntent(Deck deck, Player player, Enemy enemy, Deck enemyDeck) {
        boolean endTurn = false;
        int cardPosition;
        boolean cardPlaySuccess;

        while (!endTurn) {

            // get input
            String input = Game.getInput();
            // check player isn't dead
            if (player.getHealthPoints() <= 0) {
                System.out.println(player.getName() + " died at level" + player.getPlayerLevel());
                input = "q";
            }

            /* Control flow
            changes cardPosition to an int and decrements by 1 so that it refers to the correct position
            in the array, as the card in position 1 is in the array location 0.
            */

            switch (input) {
                // Quit
                case "Q", "q" -> {
                    Main.exitMessage();
                    System.exit(0);
                }
                // Show Deck
                case "D", "d" -> deck.showDrawPile(deck.getDrawPile());
                // Show Discard
                case "V", "v" -> deck.showDiscard();
                // show exhaust
                case "X", "x" -> deck.showExhaust();
                // EndTurn
                case "E", "e" -> {
                    endTurn(deck, player, enemy, enemyDeck);
                    endTurn = true;
                }
                // Testing
                case "kill" -> {
                    enemy.setHealthPoints(0);
                    endTurn = true;
                }
                // Testing
                case "die" -> player.setHealthPoints(0);
                // Card selection
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> {
                    cardPosition = Integer.parseInt(input.trim()) - 1;
                    if (deck.getHandPile().size() <= cardPosition) {
                        System.out.println("You do not have a card in your hand at position " + input);
                        break;
                    }
                    if (cardPosition != -1) {
                        cardPlaySuccess = Combat.playCard(player, enemy, deck.getHandPile().get(cardPosition), deck);

                        if (cardPlaySuccess && enemy.getHealthPoints() > 0) {
                            System.out.println();
                            player.setHandSize(player.getHandSize() - 1);

                            if(deck.getHandPile().get(cardPosition).isExhaust()){
                                deck.exhaustCard(cardPosition);
                            }else{
                                deck.discard(cardPosition);
                            }
                            System.out.print(enemy.showGamePieceStats() + " ");
                            System.out.println(player.showGamePieceStats());
                            // show enemy intent
                            System.out.println(enemy.getIntent(enemy, enemyDeck));

                            deck.showHand();
                            deck.showDeckStatusUI(player.getActionPoints());
                        }
                    }
                    if (enemy.getHealthPoints() <= 0) {
                        System.out.println(enemy.getName() + " was defeated");
                        player.levelUp();
                        System.out.println(player.getName() + " is now level " + player.getPlayerLevel() + "\n");
                        endTurn = true;
                    }
                }
                default -> System.out.println(input + " is not a valid input at this time");
            }
        }
    }

    public static void endTurn(Deck deck, Player player, Enemy enemy, Deck enemyDeck) {

        // discard remaining cards in players hand
        deck.discardHand();
        //Set player AP/hand back to default
        player.setActionPoints(player.getBaseActionPoints());

        // decay
        GamePiece.decayWeak(player);
        GamePiece.decayVulnerable(player);

        // status effect changes
        Combat.poisonStep(player);
        Combat.poisonStep(enemy);
        // Enemy turn
        Combat.playCard(enemy, player, enemyDeck.getHandPile().get(0), enemyDeck);
        enemyDeck.discardHand();

        // status decay
        GamePiece.decayPoison(player);
        GamePiece.decayPoison(enemy);

        player.setHandSize(player.getBaseHandSize()); // should be changed to getBaseHandSize()


    }

    public static void resetPlayer(Player player, boolean endMatch) {
        player.setBaseHandSize(5);
        player.setHandSize(player.getBaseHandSize());
        player.setActionPoints(3);
        player.setBlock(0);
        player.setParry(0);

        if (endMatch) {
            player.setPoison(0);
            player.setWeak(0);
            player.setStrength(0);
            player.setDexterity(0);
            player.setVulnerable(0);
            player.setBleed(0);

        }


    }

    public static void resetEnemy(Enemy enemy) {
        enemy.setBlock(0);
        enemy.setParry(0);
    }


}




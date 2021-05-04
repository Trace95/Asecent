public class Shop {

    public static void shopMenu(Player player, Deck deck) {
        Deck cardLibrary = Deck.makeCardLibrary();
        boolean shopVisted = false;
        cardLibrary.shuffle();
        int cost = -1; //test value

        cardLibrary.drawHand(6);

        boolean loop = true;
        while (loop) {

            int count = 1;
            System.out.println("********************SHOP***************************");
            System.out.println("You may buy as many cards as you can afford! \n");
            for (Card card : cardLibrary.getHandPile()) {
                System.out.println(count + "." + card.getCardDisplay() + " Cost:" + cost + "\n");
                count++;
            }
            System.out.print("Q = Leave the shop              ");
            if (!shopVisted) {
                System.out.println("R = Card removal service (costs: 75 gold)");
            } else {
                System.out.println();
            }
            System.out.println("********************SHOP***************************");

            String input = Game.getStringInput();

            switch (input) {
                case "1", "2", "3", "4", "5", "6":
                    int intInput = Integer.parseInt(input) - 1; // makes input correspond with array element
                    if (intInput > cardLibrary.getHandPile().size()) {
                        System.out.println(input + " is not a valid input.\nPlease Enter a valid input");
                        break;
                    }
                    deck.getDrawPile().add(cardLibrary.getHandPile().get(intInput));
                    System.out.println(cardLibrary.getHandPile().get(intInput).getName() + " was added to the deck");
                    cardLibrary.getHandPile().remove(intInput);
                    break;
                case "q", "Q":
                    loop = false;
                    break;
                case "r", "R":
                    if (!shopVisted) {
                        if (cardRemoval(deck)) {
                            shopVisted = true;
                        }

                    }

                    break;
                default:
                    System.out.println(input + " is not a valid input.\nPlease Enter a valid input");
                    break;
            }
            if (cardLibrary.getHandPile().size() == 0) {
                loop = false;
            }
        }
    }

    public static boolean cardRemoval(Deck deck) {

        boolean loop = true;
        while (loop) {


            int count = 1;
            System.out.println("*************************\uD835\uDC03\uD835\uDC04\uD835\uDC02\uD835\uDC0A*************************");
            System.out.println("You may only remove one card per shop visit \n");
            for (Card card : deck.getDrawPile()) {
                System.out.println(count + "." + card.getCardDisplay() + "\n");
                count++;
            }
            System.out.println("B = Back to shop");
            System.out.println("*************************\uD835\uDC03\uD835\uDC04\uD835\uDC02\uD835\uDC0A*************************");
            String input = Game.getStringInput();
            if (input.equals("b") || input.equals("B")) {
                loop = false;
            } else {
                try {
                    if (Integer.parseInt(input) - 1 > deck.getDrawPile().size() || Integer.parseInt(input) - 1 < 0) {
                        System.out.println("There is no card in the deck at position " + input + ". \nPlease enter another value");
                    } else {
                        System.out.println(deck.getDrawPile().get(Integer.parseInt(input) - 1).getName() + " was removed from the deck");
                        deck.getDrawPile().remove(Integer.parseInt(input) - 1);
                        return true;

                    }
                } catch (NumberFormatException e) {
                    System.out.println(input + " is not a valid entry");
                }

            }

        }
        return false;
    }
}


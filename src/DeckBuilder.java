public class DeckBuilder {

    public static void deckBuilderMenu() {
        boolean loop = true;
        while (loop) {
            System.out.println("1.Create a card");
            System.out.println("2.Create a deck");
            System.out.println("3.Delete a card");
            System.out.println("4.Quit");

            switch (Game.getIntInput(1, 5)) {
                case 1 -> addCardToCardLibrary();
                case 2 -> buildDeck();
                case 3 -> deleteCardFromCardLibrary();
                case 4 -> loop = false;
            }
        }
    }

    private static void buildDeck() {
        Deck newDeck = new Deck();
        System.out.println("Enter deck name");
        String name = Game.getStringInput();
        int count = 1;
        Deck cardLibrary = Deck.makeCardLibrary();

        for (Card card : cardLibrary.getDrawPile()) {
            System.out.println(count + "." + card.getCardDisplay() + "\n");
            count++;
        }
        System.out.println("Q = to leave     S = Save Deck");

        boolean loop = true;

        while (loop) {
            String input = Game.getStringInput();

            if (input.equals("s") || input.equals("S")) {
                System.out.println("Deck saved");
                Deck.saveDeck(newDeck, name);

            } else if (input.equals("q") || input.equals("Q")) {
                loop = false;
            } else if (Integer.parseInt(input) <= cardLibrary.getDrawPile().size() || Integer.parseInt(input) >= cardLibrary.getDrawPile().size()) {
                int intInput = Integer.parseInt(input) - 1; // makes input correspond with array element
                newDeck.getDrawPile().add(cardLibrary.getDrawPile().get(intInput));
                System.out.println(cardLibrary.getDrawPile().get(intInput).getName() + " was added to the deck");
            } else {
                System.out.println("Please enter a valid input");
            }
        }
        Deck.saveDeck(newDeck, name);

    }

    private static void addCardToCardLibrary() {
        Card card = createCard();
        System.out.println("Save card?");
        if (Game.getConfirmation()) {
            Deck cardLibrary = Deck.makeCardLibrary();
            cardLibrary.getDrawPile().add(card);
            Deck.saveDeck(cardLibrary, "CardLibrary");
        } else {
            System.out.println(card.getName() + "was not saved");
        }

    }

    private static Card createCard() {
        String name;
        int cost;
        CardType type;
        int attack = 0;
        int block;
        String effect;
        int hits = 0;
        int draw;
        int goldCost;
        int poison;
        int vulnerable;
        int weak;
        int parry;
        boolean exhaust;
        int strength;
        int dexterity;


        System.out.println("Please enter card name");
        name = Game.getStringInput();

        System.out.println("Please enter card type:\n1.Attack\n2.Skill");
        if (Game.getIntInput(1, 2) == 1) {
            type = CardType.Attack;
        } else {
            type = CardType.Skill;
        }

        if (type.equals(CardType.Attack)) {
            System.out.println("Please enter card attack");
            attack = Game.getIntInput(0, 1000);
        }

        System.out.println("Please enter card block");
        block = Game.getIntInput(0, 1000);

        if (type.equals(CardType.Attack)) {
            System.out.println("Please enter card hits");
            hits = Game.getIntInput(0, 1000);
        }
        System.out.println("Please enter card draw");
        draw = Game.getIntInput(0, 100);

        System.out.println("Please enter card goldCost");
        goldCost = Game.getIntInput(0, 1000);

        System.out.println("Please enter card poison");
        poison = Game.getIntInput(0, 100);

        System.out.println("Please enter card vulnerable");
        vulnerable = Game.getIntInput(0, 100);

        System.out.println("Please enter card weakness");
        weak = Game.getIntInput(0, 100);

        System.out.println("Please enter card parry");
        parry = Game.getIntInput(0, 100);

        System.out.println("Exhaust?");
        exhaust = Game.getConfirmation();

        System.out.println("Please enter card strength");
        strength = Game.getIntInput(0, 100);
        System.out.println("Please enter card dexterity");
        dexterity = Game.getIntInput(0, 100);

        System.out.println("Please enter card effect");
        effect = Game.getStringInput();

        System.out.println("Please enter card Cost");
        cost = Game.getIntInput(0, 100);


        Card card = new Card(name, cost, type, attack, block, effect, hits, draw, goldCost, poison, vulnerable, weak, parry, exhaust, strength, dexterity);
        System.out.println(card.getCardDisplay());
        return card;
    }

    private static void deleteCardFromCardLibrary() {
        boolean loop = true;
        while (loop) {
            int count = 1;
            Deck cardLibrary = Deck.makeCardLibrary();
            for (Card card : cardLibrary.getDrawPile()) {
                System.out.println(count + "." + card.getCardDisplay() + "\n");
                count++;
            }
            System.out.println("Q = to leave     # = remove card ");


            String input = Game.getStringInput();

            if (input.equals("q") || input.equals("Q")) {
                loop = false;
            } else if (Integer.parseInt(input) <= cardLibrary.getDrawPile().size() || Integer.parseInt(input) >= cardLibrary.getDrawPile().size()) {
                int intInput = Integer.parseInt(input) - 1; // makes input correspond with array element
                System.out.println("Remove " + cardLibrary.getDrawPile().get(intInput).getName() + " from the library?");
                if (Game.getConfirmation()) {
                    System.out.println(cardLibrary.getDrawPile().get(intInput).getName() + " was removed from the deck");
                    cardLibrary.getDrawPile().remove(cardLibrary.getDrawPile().get(intInput));
                }
            } else {
                System.out.println("Please enter a valid input");
            }
            Deck.saveDeck(cardLibrary, "CardLibrary");
        }

    }

}



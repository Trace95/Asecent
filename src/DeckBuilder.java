public class DeckBuilder {

    public static void deckBuilderMenu(){

        while(true){
            System.out.println("1.Add a new card to the CardLibrary");
            System.out.println("2.Create a new Deck");
            switch (Game.getInput()){
                case "1" -> addCardToCardLibrary();
                case "2" -> buildDeckFromCardLibrary();
                default -> System.out.println("Please enter a valid input");
            }
        }

    }



    public static void buildDeckFromCardLibrary(){
        Deck newDeck = new Deck();
        System.out.println("Enter deck name");
        String name = Game.getInput();
        int count = 1;

        Deck cardLibrary = Deck.makeCardLibrary();

        for (Card card : cardLibrary.getDrawPile()) {
            System.out.println(count + "." + card.getCardDisplay() + "\n");
            count++;
        }
        System.out.println("Q = to leave     S = Save Deck");

        boolean loop = true;

        while (loop) {
            String input = Game.getInput();

            if(input.equals("s") || input.equals("S")){
                System.out.println("Deck saved");
                Deck.saveDeck(newDeck,name);

            }else if(input.equals("q") || input.equals("Q")){
                loop = false;
            }else if(Integer.parseInt(input) <= cardLibrary.getDrawPile().size() || Integer.parseInt(input) >= cardLibrary.getDrawPile().size() ){
                int intInput = Integer.parseInt(input) - 1; // makes input correspond with array element
                newDeck.getDrawPile().add(cardLibrary.getDrawPile().get(intInput));
                System.out.println(cardLibrary.getDrawPile().get(intInput).getName() + " was added to the deck");
            }else{
                System.out.println("Please enter a valid input");
            }
        }




        Deck.saveDeck(newDeck,name);

    }

    public static void addCardToCardLibrary(){
        Card card = createCard();
        Deck cardLibrary = Deck.makeCardLibrary();
        cardLibrary.getDrawPile().add(card);
        Deck.saveDeck(cardLibrary,"CardLibrary");
    }

    public static Card createCard(){
        String name;
        int cost;
        String type = null;
        int attack;
        int block;
        String effect;
        int hits;
        int draw;
        int goldCost;
        int poison;
        int vulnerable;
        int weak;
        int parry;
        boolean exhaust = false;
        int strength;
        int dexterity;



        System.out.println("Please enter card name");
        name = Game.getInput();

        boolean loop = true;
        while(loop){
            System.out.println("Please enter card type:\n1.Attack\n2.Skill");
            switch (Game.getInput()){
                case "1" -> {
                    type = "Attack";
                    loop = false;
                }
                case "2"-> {
                    type = "Skill";
                    loop = false;

                }
                default -> System.out.println("Please enter 1 or 2");
            }

        }


        if(type.equals("Attack")){
            System.out.println("Please enter card attack");
            attack = Integer.parseInt(Game.getInput());
        }else {
            attack = 0;
        }

        System.out.println("Please enter card block");
        block = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card hits");
        hits = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card draw");
        draw = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card goldCost");
        goldCost = Integer.parseInt(Game.getInput());


        System.out.println("Please enter card poison");
        poison = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card vulnerable");
        vulnerable = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card weakness");
        weak = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card parry");
        parry = Integer.parseInt(Game.getInput());


        loop = true;
        while(loop){
            System.out.println("Please enter card exhaust. Y OR N");
            switch (Game.getInput()){
                case "Y", "y" -> {
                    exhaust = true;
                    loop = false;
                }
                case "N", "n" -> {
                    loop = false;

                }
                default -> System.out.println("Please enter Y or N ");
            }

        }

        System.out.println("Please enter card strength");
        strength= Integer.parseInt(Game.getInput());
        System.out.println("Please enter card dexterity");
        dexterity = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card effect");
        effect = Game.getInput();

        System.out.println("Please enter card Cost");
        cost = Integer.parseInt(Game.getInput());


        Card card = new Card(name, cost, type, attack, block, effect, hits, draw, goldCost, poison, vulnerable, weak, parry, exhaust, strength, dexterity);
        System.out.println(card.getCardDisplay());
        System.out.println("Save card? \n 1.Yes\n 2.No");

        if(Game.getInput().equals("1")){
            return card;
        }else {
            System.out.println("invalid input");
            return createCard();
        }
    }

}

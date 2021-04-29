public class DeckBuilder {


    public static void deckBuilderMenu(){

    }

    public static Card createCard(){
        String name;
        int cost;
        String type;
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
        System.out.println("Please enter card Cost");
        cost = Integer.parseInt(Game.getInput());
        System.out.println("Please enter card type");
        type = Game.getInput();
        if(type == "Attack"){
            System.out.println("Please enter card attack");
            attack = Integer.parseInt(Game.getInput());
        }else {
            attack = 0;
        }
        System.out.println("Please enter card block");
        block = Integer.parseInt(Game.getInput());

        System.out.println("Please enter card effect");
        effect = Game.getInput();

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

        boolean loop = true;
        while(loop){
            System.out.println("Please enter card exhaust. Y OR N");
            switch (Game.getInput()){
                case "Y", "y" -> {
                    exhaust = true;
                    loop = false;
                    break;
                }
                case "N", "n" -> {
                    exhaust = false;
                    loop = false;
                    break;

                }
                default -> System.out.println("Please enter Y or N ");
            }

        }


        System.out.println("Please enter card strength");
        strength= Integer.parseInt(Game.getInput());
        System.out.println("Please enter card dexterity");
        dexterity = Integer.parseInt(Game.getInput());


        Card card = new Card(name, cost, type, attack, block, effect, hits, draw, goldCost, poison, vulnerable, weak, parry, exhaust, strength, dexterity);
        System.out.println("Save card? \n 1. Yes\n 2.No");
        System.out.println(card.getCardDisplay());
        if(Game.getInput() == "1"){
            return card;
        }else {
            return createCard();
        }
    }

}


public class Main {


    public static void main(String[] args) {
        printGraphic();
        while(true){
            System.out.println("1.Play Game \n2.Create Cards and Decks");
            switch (Game.getInput()){
                case "1" -> Game.loadGameMenu();
                case "2" -> DeckBuilder.deckBuilderMenu();
                case "3" -> System.exit(1);
                default -> System.out.println("Please enter 1 or 2");
            }
        }
    }
    public static void loseMessage() {
        printLoseGraphic();
        Game.deleteSave();
    }

    public static void printGraphic(){
        System.out.println(" █████╗ ███████╗███████╗ ██████╗███████╗███╗   ██╗████████╗    ");
        System.out.println("██╔══██╗██╔════╝██╔════╝██╔════╝██╔════╝████╗  ██║╚══██╔══╝    ");
        System.out.println("███████║███████╗█████╗  ██║     █████╗  ██╔██╗ ██║   ██║       ");
        System.out.println("██╔══██║╚════██║██╔══╝  ██║     ██╔══╝  ██║╚██╗██║   ██║       ");
        System.out.println("██║  ██║███████║███████╗╚██████╗███████╗██║ ╚████║   ██║       ");
        System.out.println("╚═╝  ╚═╝╚══════╝╚══════╝ ╚═════╝╚══════╝╚═╝  ╚═══╝   ╚═╝       ");
        System.out.println("                  Combat Deck builder\n");
//http://patorjk.com/software/taag/#p=display&h=0&v=1&f=ANSI%20Shadow&t=Asecent%20
    }

    public static void printLoseGraphic(){
        System.out.println("" +
                " ██████╗  █████╗ ███╗   ███╗███████╗ ██████╗ ██╗   ██╗███████╗██████╗ \n" +
                "██╔════╝ ██╔══██╗████╗ ████║██╔════╝██╔═══██╗██║   ██║██╔════╝██╔══██╗\n" +
                "██║  ███╗███████║██╔████╔██║█████╗  ██║   ██║██║   ██║█████╗  ██████╔╝\n" +
                "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n" +
                "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n" +
                " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝ ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝\n" +
                "                                                                      ");
    }





}



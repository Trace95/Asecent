
public class Main {


    public static void main(String[] args) {
        printGraphic();
        System.out.println("1.Play Game \n2.Edit Cards and Decks");
        while(true){
            switch (Game.getInput()){
                case "1" -> Game.loadGameMenu();
                case "2" -> DeckBuilder.deckBuilderMenu();
                default -> System.out.println("Please enter 1 or 2");
            }
        }
    }
    public static void exitMessage() {
        System.out.println("GameOver");
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
}



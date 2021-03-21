
public class Main {


    public static void main(String[] args) {
        printGraphic();
        Game.loadGameMenu();
        exitMessage();
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



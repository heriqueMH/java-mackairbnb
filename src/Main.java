import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Instancia.instancia();
        Menu menu = new Menu();
        Menu.setScanner(sc);
        Hospede.setScanner(sc);
        Proprietario.setScanner(sc);
        menu.exibirMenu();
        sc.close();
    }
}

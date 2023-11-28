import java.util.Scanner;

public class Menu {
  private static Scanner scanner;

  public Menu() {
  }

  public static void setScanner(Scanner sc) {
    scanner = sc;
  }

  public void exibirMenu() throws Exception {
    int opcaoMenu = 0;
    do {
      System.out.println("");
      System.out.println("----- MENU -----");
      System.out.println("1. Usuário já cadastrado");
      System.out.println("2. Cadastrar novo usuário");
      System.out.println("3. Excluir usuário");
      System.out.println("4. Sair do programa");
      System.out.print("Escolha uma opção: ");
      opcaoMenu = scanner.nextInt();
      scanner.nextLine();
      switch (opcaoMenu) {
        case 1:
          Usuario user = autenticarUsuario();
          if (user == null) {
            System.out.println("");
            System.out.println("!!!!! Falha na autenticação. !!!!!");
          } else {
            user.exibirMenu();
          }
          break;
        case 2:
          exibirSubMenuCadastro();
          break;
        case 3:
          excluirUsuario();
          break;
      }
    } while (opcaoMenu != 4);
  }

  public static Usuario autenticarUsuario() {
    Usuario usuarioAutenticado = null;
    boolean autenticado = false;

    while (!autenticado) {
      System.out.print("Digite o nome do usuário: ");
      String nome = scanner.nextLine();
      System.out.print("Digite a senha do usuário: ");
      String senha = scanner.nextLine();

      usuarioAutenticado = Usuario.buscarUsuario(nome, senha);

      if (usuarioAutenticado != null) {
        autenticado = true;
        System.out.println("");
        System.out.println("Usuário autenticado! Bem-vindo, " + usuarioAutenticado.getNome() + ".");
      } else {
        System.out.println("");
        System.out.println("Usuário ou senha incorretos. Deseja tentar novamente? (S/N)");
        String resposta = scanner.nextLine().toUpperCase();

        if (!resposta.equals("S")) {
          autenticado = true;
        }
      }
    }
    return usuarioAutenticado;
  }

  public void exibirSubMenuCadastro() throws Exception {
    System.out.println("");
    System.out.println("----- SUBMENU DE CADASTRO -----");
    System.out.println("1. Cadastrar Hóspede");
    System.out.println("2. Cadastrar Proprietário");
    System.out.println("3. Voltar para o menu inicial");
    System.out.print("Escolha uma opção: ");
    int opcaoSubMenu = scanner.nextInt();
    scanner.nextLine();

    switch (opcaoSubMenu) {
      case 1:
        Hospede.menuDeCadastro();
        break;
      case 2:
        Proprietario.menuDeCadastro();
        break;
      case 3:
        System.out.println("");
        exibirMenu();
        break;
      default:
        System.out.println("Opção inválida.");
        break;
    }
  }

  private void excluirUsuario() {
    Usuario user = autenticarUsuario();
    if (user == null) {
      System.out.println("");
      System.out.println("!!!!! Falha na autenticação. !!!!!");
    } else {
      Usuario.excluirUsuario(user.getNome());
    }
  }
}

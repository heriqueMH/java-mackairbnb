import java.util.Scanner;

public class Menu {
  private Scanner scanner;

  public Menu() {
    this.scanner = new Scanner(System.in);
  }

  public void exibirMenu() throws Exception {
    int opcaoMenu = 0;
    do {
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
            user.exibirMenu();
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

  public Usuario autenticarUsuario() {
    Usuario usuarioAutenticado =null;
    while (usuarioAutenticado == null) {
      System.out.print("Digite o nome do usuário: ");
      String nome = scanner.next();
      System.out.print("Digite a senha do usuário: ");
      String senha = scanner.next();

      usuarioAutenticado = Usuario.buscarUsuario(nome, senha);
      if (usuarioAutenticado != null) {
          System.out.println("Usuário autenticado! Bem-vindo, " + usuarioAutenticado.getNome() + ".");
          return usuarioAutenticado;
      } else {
          System.out.println("Usuário ou senha incorretos.");
          return null;
      }
    }
    return usuarioAutenticado;
  }

  public void exibirSubMenuCadastro() throws Exception{
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
    System.out.print("Digite o nome do usuário a ser excluído: ");
    String nome = scanner.next();
    Usuario.excluirUsuario(nome);
  }
}

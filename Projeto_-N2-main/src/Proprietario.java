import java.util.List;
import java.util.Scanner;

public class Proprietario extends Usuario {
  private Scanner scanner;

  public Proprietario(String nome, String cpf, String email, String endereco, String senha) {
    super(nome, cpf, email, endereco, senha);
    this.scanner = new Scanner(System.in);
  }

  public void exibirPropriedades() {
    MackAirbnb gerenciador = MackAirbnb.getInstancia();
    List<Propriedade> minhasPropriedades = gerenciador.getPropriedadesPorProprietario(this);

    if (minhasPropriedades.isEmpty()) {
      System.out.println("Você não possui propriedades cadastradas.");
    } else {
      System.out.println("");
      System.out.println("----- SUAS PROPRIEDADES -----");
      for (Propriedade propriedade : minhasPropriedades) {
        System.out.println("ID: " + propriedade.getId());
        System.out.println("Título: " + propriedade.getTitulo());
        System.out.println("Descrição: " + propriedade.getDescricao());
        System.out.println("Localização: " + propriedade.getLocalizacao());
        System.out.println("Capacidade: " + propriedade.getCapacidade());
        System.out.println("Preço por noite: " + propriedade.getPrecoPorNoite());
        System.out.println("------------------------------");
      }
    }
  }

  public static void menuDeCadastro() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o nome do proprietário: ");
    String nome = scanner.nextLine();
    System.out.print("Digite a senha do proprietário: ");
    String senha = scanner.nextLine();
    System.out.print("Digite o CPF do proprietário: ");
    String cpf = scanner.nextLine();
    System.out.print("Digite o email do proprietário: ");
    String email = scanner.nextLine();
    System.out.print("Digite o endereço do proprietário: ");
    String endereco = scanner.nextLine();

    Proprietario proprietario = new Proprietario(nome, cpf, email, endereco, senha);
    cadastrarNovoUsuario(proprietario);

  }

  public static Proprietario cadastrarProprietario(String nome, String cpf, String email, String endereco,
      String senha) {
    Proprietario novoProprietario = new Proprietario(nome, cpf, email, endereco, senha);
    return novoProprietario;
  }

  public void cadastrarNovaPropriedade(Usuario proprietario, String titulo, String descricao, String localizacao,
      int capacidade, double precoPorNoite) {
    Propriedade propriedade = new Propriedade(titulo, descricao, localizacao, capacidade, precoPorNoite);
    MackAirbnb.getInstancia().cadastrarPropriedade(propriedade, proprietario);
  }

  public void excluirPropriedade() {
    System.out.println("----- EXCLUSÃO DE PROPRIEDADE -----");
    System.out.print("Digite o ID da propriedade a ser excluída: ");
    int idPropriedade = scanner.nextInt();
    scanner.nextLine();
    MackAirbnb.getInstancia().excluirPropriedade(idPropriedade);
  }

  @Override
  public void exibirMenu() {
    int opcaoMenu;
    do {
      System.out.println("");
      System.out.println("----- MENU DO PROPRIETÁRIO -----");
      System.out.println("1. Consultar suas Propriedades");
      System.out.println("2. Cadastrar nova Propriedade");
      System.out.println("3. Excluir uma Propriedade");
      System.out.println("4. Sair do programa");
      System.out.print("Escolha uma opção: ");
      opcaoMenu = scanner.nextInt();
      scanner.nextLine();

      switch (opcaoMenu) {
        case 1:
          exibirPropriedades();
          break;
        case 2:
          System.out.println("----- CADASTRO DE PROPRIEDADE -----");
          System.out.print("Título da propriedade: ");
          String titulo = scanner.nextLine();
          System.out.print("Descrição da propriedade: ");
          String descricao = scanner.nextLine();
          System.out.print("Localização da propriedade: ");
          String localizacao = scanner.nextLine();
          System.out.print("Capacidade da propriedade: ");
          int capacidade = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Preço por noite: ");
          double precoPorNoite = scanner.nextDouble();
          scanner.nextLine();

          cadastrarNovaPropriedade(Usuario.getUsuario(this), titulo, descricao, localizacao, capacidade, precoPorNoite);
          break;
        case 3:
          excluirPropriedade();
          break;
        case 4:
          System.out.println("Saindo do programa...");
          break;
        default:
          System.out.println("Opção inválida.");
          break;
      }
    } while (opcaoMenu != 4);
  }

}
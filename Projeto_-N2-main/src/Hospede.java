import java.time.LocalDate;
import java.util.Scanner;

public class Hospede extends Usuario {
  private int numeroReserva;
  private static Scanner scanner;

  public Hospede(String nome, String cpf, String email, String endereco, String senha) {
    super(nome, cpf, email, endereco, senha);
  }

  public static void setScanner(Scanner sc) {
    scanner = sc;
  }

  public void cadastrarNovaReserva() {
    int opcaoSubMenu;
    do {
      System.out.println("----- CADASTRAR NOVA RESERVA -----");
      System.out.println("1. Lista Propriedades");
      System.out.println("2. Selecionar propriedade");
      System.out.println("3. Sair do Programa");
      System.out.print("Escolha uma opção: ");
      opcaoSubMenu = scanner.nextInt();
      scanner.nextLine();

      switch (opcaoSubMenu) {
        case 1:
          MackAirbnb.exibirTodasPropriedades();
          System.out.println("");
          System.out.println("Digite o ID da propriedade desejada");
          int id = scanner.nextInt();
          setPropriedade(id);
          exibirMenu();
          break;
        case 2:
          System.out.println("Digite o Id da propriedade.");
          int idEscolhido = scanner.nextInt();
          setPropriedade(idEscolhido);
          exibirMenu();
          break;
        case 3:
          break;
        default:
          System.out.println("Opção inválida.");
          break;

      }
    } while (opcaoSubMenu != 3);
  }

  public void setPropriedade(int id) {
    Propriedade propriedadeEscolhida = MackAirbnb.getPropriedadeId(id);
    if (propriedadeEscolhida != null) {
      System.out.println("Qual a Data de check-in (YYYY-MM-DD):");
      String checkInStr = scanner.nextLine();
      LocalDate checkIn = LocalDate.parse(checkInStr);

      System.out.println("Qual a Data de check-out (YYYY-MM-DD):");
      String checkOutStr = scanner.nextLine();
      LocalDate checkOut = LocalDate.parse(checkOutStr);

      Reserva novaReserva = new Reserva(propriedadeEscolhida, this);
      novaReserva.setId(numeroReserva++);
      novaReserva.setDataCheckIn(checkIn);
      novaReserva.setDataCheckOut(checkOut);

      MackAirbnb.reservas.add(novaReserva);
      System.out.println("");
      System.out.println("!!!!! Reserva cadastrada com sucesso. !!!!!");
    }
  }

  public void excluirReserva() {
    System.out.print("Digite o número da reserva a ser excluída: ");
    int numReserva = scanner.nextInt();
    scanner.nextLine();
    System.out.println("");
    System.out.print("Digite a senha para confirmar a exclusão da reserva: ");
    System.out.println("");
    String senha = scanner.nextLine();
    Reserva reservaParaExcluir = null;

    if (senha.equals(getSenha())) {
      for (Reserva reserva : MackAirbnb.reservas) {
        if (reserva.getReserva() == numReserva) {
          reservaParaExcluir = reserva;
          break;
        }
      }
      if (reservaParaExcluir != null) {
        MackAirbnb.reservas.remove(reservaParaExcluir);
        System.out.println("Reserva excluída com sucesso.");
      } else {
        System.out.println("Reserva não encontrada.");
      }
    } else {
      System.out.println("Senha incorreta. A exclusão da reserva não foi realizada.");
    }
  }

  public Reserva getReserva(int numeroReserva) {
    for (Reserva reserva : MackAirbnb.reservas) {
      if (reserva.getReserva() == numeroReserva) {
        return reserva;
      }
    }
    return null;
  }

  public void consultarReservas() {
    if (MackAirbnb.reservas.isEmpty()) {
      System.out.println("Você não possui reservas.");
    } else {
      boolean reservaPendente = false;
      System.out.println("");
      System.out.println("----- SUAS RESERVAS -----");
      for (Reserva reserva : MackAirbnb.reservas) {
        System.out.println("Número da reserva: " + reserva.getReserva());
        System.out.println("Propriedade: " + reserva.getPropriedade().getTitulo());
        System.out.println("Data de Check-in: " + reserva.getDataCheckIn());
        System.out.println("Data de Check-out: " + reserva.getDataCheckOut());
        System.out.println("Confirmação de Reserva: " + (reserva.isConfirmacaoReserva() ? "Confirmada" : "Pendente"));
        System.out.println("---------------------------------");

        if (!reserva.isConfirmacaoReserva()) {
          reservaPendente = true;
        }
      }

      if (reservaPendente) {
        System.out.println("Deseja confirmar alguma reserva pendente? (S/N)");
        String resposta = scanner.nextLine().toUpperCase();
        if (resposta.equals("S")) {
          System.out.print("Digite o número da reserva a ser confirmada: ");
          int numReserva = scanner.nextInt();
          confirmarReservaPendente(numReserva);
        } else {
          exibirMenu();
        }
      }
    }
  }

  public void confirmarReservaPendente(int numReserva) {
    for (Reserva reserva : MackAirbnb.reservas) {
      if (reserva.getReserva() == numReserva && !reserva.isConfirmacaoReserva()) {
        reserva.confirmarReserva(numReserva);
        return;
      }
    }
    System.out.println("Reserva não encontrada ou já confirmada.");
  }

  public void avaliarPropriedade(int idReserva, Avaliacao avaliacao) {
    Reserva reserva = getReserva(idReserva);

    if (reserva != null) {
      if (reserva.estadiaConcluida()) {
        System.out.println("Que nota você daria para sua experiência nesta propriedade?");
        int notaDeAvaliacao = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Se desejar, inclua um comentário:");
        String comentario = scanner.nextLine();

        if (comentario != null) {
          avaliacao = new Avaliacao(notaDeAvaliacao, comentario);
        } else {
          avaliacao = new Avaliacao(notaDeAvaliacao);
        }
        reserva.getPropriedade().receberAvaliacao(avaliacao);
      } else {
        System.out.println("!!!!! Avaliação disponível somente após o término da estadia. !!!!!");
      }
    } else {
      System.out.println("!!!!! Reserva não encontrada. !!!!!");
    }
  }

  public static void menuDeCadastro() {
    setScanner(scanner);
    System.out.print("Digite o nome do hospede: ");
    String nome = scanner.nextLine();
    System.out.print("Digite a senha do hospede: ");
    String senha = scanner.nextLine();
    System.out.print("Digite o CPF do hospede: ");
    String cpf = scanner.nextLine();
    System.out.print("Digite o email do hospede: ");
    String email = scanner.nextLine();
    System.out.print("Digite o endereço do hospede: ");
    String endereco = scanner.nextLine();

    Hospede hospede = new Hospede(nome, cpf, email, endereco, senha);
    cadastrarNovoUsuario(hospede);
  }

  public static Hospede cadastrarHospede(String nome, String cpf, String email, String endereco, String senha) {
    Hospede novoHospede = new Hospede(nome, cpf, email, endereco, senha);
    return novoHospede;
  }

  @Override
  public void exibirMenu() {
    int opcaoMenu;
    do {
      System.out.println("");
      System.out.println("-------- MENU DO HÓSPEDE --------");
      System.out.println("1. Consultar Lista de Propriedades");
      System.out.println("2. Consultar Reservas");
      System.out.println("3. Cadastrar Nova Reserva");
      System.out.println("4. Excluir Reserva");
      System.out.println("5. Fazer logoff");
      System.out.print("Escolha uma opção: ");
      opcaoMenu = scanner.nextInt();
      scanner.nextLine();

      switch (opcaoMenu) {
        case 1:
          MackAirbnb.exibirTodasPropriedades();
          break;
        case 2:
          consultarReservas();
          break;
        case 3:
          cadastrarNovaReserva();
          break;
        case 4:
          excluirReserva();
          break;
        case 5:
          System.out.println("!!!!! Saindo do programa... !!!!!");
          break;
        default:
          System.out.println("Opção inválida.");
          break;
      }
    } while (opcaoMenu != 5);
  }

}

import java.util.ArrayList;
import java.util.List;

public class MackAirbnb {
  public static MackAirbnb instancia = null;
  public static List<Usuario> userCadastrado = new ArrayList<>();
  public static List<Propriedade> propriedades = new ArrayList<>();
  public static List<Reserva> reservas = new ArrayList<>();

  public MackAirbnb() {
  }

  public static MackAirbnb getInstancia() {
    if (instancia == null) {
      instancia = new MackAirbnb();
    }
    return instancia;
  }

  public void cadastrarPropriedade(Propriedade propriedade, Usuario proprietario) {
    propriedade.setProprietario(proprietario);
    propriedades.add(propriedade);
    System.out.println("");
    System.out.println("!!!!! Propriedade cadastrada com sucesso! !!!!!");
  }

  public static Propriedade getPropriedadeId(int id) {
    for (Propriedade propriedade : propriedades) {
      if (propriedade.getId() == id) {
        return propriedade;
      }
    }
    return null;
  }

  public static void exibirTodasPropriedades() {
    System.out.println("----- LISTA DE PROPRIEDADES -----");
    for (Propriedade propriedade : propriedades) {
      System.out.println("ID: " + propriedade.getId());
      System.out.println("Dono do Imóvel: " + propriedade.getProprietario());
      System.out.println("Título: " + propriedade.getTitulo());
      System.out.println("Descrição: " + propriedade.getDescricao());
      System.out.println("Localização: " + propriedade.getLocalizacao());
      System.out.println("Capacidade: " + propriedade.getCapacidade());
      System.out.println("Preço por Noite: " + propriedade.getPrecoPorNoite());
      System.out.println("Fotos da propriedade: " + propriedade.getFotos());
      System.out.println("Avaliações:");
      for (Avaliacao avaliacao : propriedade.getAvaliacoes()) {
        System.out.println(avaliacao);
      }
      System.out.println("------------------------------------");
    }
  }

  public List<Propriedade> getPropriedadesPorProprietario(Proprietario proprietario) {
    List<Propriedade> propriedadesDoProprietario = new ArrayList<>();
    for (Propriedade propriedade : propriedades) {
      if (proprietario.getNome().equals(propriedade.getProprietario())) {
        propriedadesDoProprietario.add(propriedade);
      }
    }
    return propriedadesDoProprietario;
  }

  public static List<Reserva> getReservasPorPropriedade(Propriedade propriedade) {
    List<Reserva> reservasDaPropriedade = new ArrayList<>();
    for (Reserva reserva : reservas) {
      if (reserva.getPropriedade().equals(propriedade)) {
        reservasDaPropriedade.add(reserva);
      }
    }
    return reservasDaPropriedade;
  }

  public static boolean propriedadeExiste(int id) {
    for (Propriedade propriedade : propriedades) {
      if (propriedade.getId() == id) {
        return true;
      }
    }
    return false;
  }
}

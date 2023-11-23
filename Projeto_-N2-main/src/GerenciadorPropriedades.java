import java.util.ArrayList;
import java.util.List;

public class GerenciadorPropriedades {
  private static GerenciadorPropriedades instancia = null;
  private static List<Propriedade> propriedades;
  public int proximoId;

  private GerenciadorPropriedades() {
    propriedades = new ArrayList<>();
    this.proximoId = 1;
  }

  public static GerenciadorPropriedades getInstancia() {
    if (instancia == null) {
      instancia = new GerenciadorPropriedades();
    }
    return instancia;
  }

  public void cadastrarPropriedade(Propriedade propriedade, Usuario proprietario) {
    propriedade.setId(proximoId++);
    propriedade.setProprietario(proprietario);
    propriedades.add(propriedade);
    System.out.println("Propriedade cadastrada com sucesso!");
  }

  public static Propriedade getPropriedadeId(int id) {
    for (Propriedade propriedade : propriedades) {
      if (propriedade.getId() == id) {
        return propriedade;
      }
    }
    return null;
  }

  public void excluirPropriedade(int idPropriedade) {
    Propriedade propriedadeParaExcluir = null;

    for (Propriedade propriedade : propriedades) {
      if (propriedade.getId() == idPropriedade) {
        propriedadeParaExcluir = propriedade;
        break;
      }
    }

    if (propriedadeParaExcluir != null) {
      propriedades.remove(propriedadeParaExcluir);
      System.out.println("Propriedade excluída com sucesso!");
    } else {
      System.out.println("Propriedade não encontrada com o ID fornecido.");
    }
  }

  public static void exibirTodasPropriedades() {
    System.out.println("----- LISTA DE PROPRIEDADES -----");
    for (Propriedade propriedade : propriedades) {
      System.out.println("ID: " + propriedade.getId());
      System.out.println("Título: " + propriedade.getTitulo());
      System.out.println("Descrição: " + propriedade.getDescricao());
      System.out.println("Localização: " + propriedade.getLocalizacao());
      System.out.println("Capacidade: " + propriedade.getCapacidade());
      System.out.println("Preço por Noite: " + propriedade.getPrecoPorNoite());
      System.out.println("---------------------------------");
    }
  }

  public List<Propriedade> getPropriedadesPorProprietario(Proprietario proprietario) {
    List<Propriedade> propriedadesDoProprietario = new ArrayList<>();
    for (Propriedade propriedade : propriedades) {
      if (proprietario.equals(propriedade.getProprietario())) {
        propriedadesDoProprietario.add(propriedade);
      }
    }
    return propriedadesDoProprietario;
  }
}
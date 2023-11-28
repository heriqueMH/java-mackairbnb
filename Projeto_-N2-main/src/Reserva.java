import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
  private int numReserva;
  private static int count = 0;
  private Propriedade propriedade;
  private Hospede hospede;
  private LocalDate dataCheckIn;
  private LocalDate dataCheckOut;
  private LocalDate dataSaidaReal;
  private boolean confirmacaoReserva;
  private List<Avaliacao> avaliacoes;

  public Reserva(Propriedade propriedade, Hospede hospede) {
    this.propriedade = propriedade;
    this.hospede = hospede;
    this.avaliacoes = new ArrayList<>();
    this.confirmacaoReserva = false;
    this.numReserva = ++count;
  }

  public int getReserva() {
    return numReserva;
  }

  public void receberAvaliacao(Avaliacao avaliacao) {
    avaliacoes.add(avaliacao);
  }

  public Propriedade getPropriedade() {
    return propriedade;
  }

  public Hospede getHospede() {
    return hospede;
  }

  public String getNome() {
    return hospede.getNome();
  }

  public boolean isConfirmacaoReserva() {
    return confirmacaoReserva;
  }

  public LocalDate getDataCheckIn() {
    return dataCheckIn;
  }

  public void setDataCheckIn(LocalDate dataCheckIn) {
    this.dataCheckIn = dataCheckIn;
  }

  public LocalDate getDataCheckOut() {
    return dataCheckOut;
  }

  public void setDataCheckOut(LocalDate dataCheckOut) {
    this.dataCheckOut = dataCheckOut;
  }

  public void confirmarReserva(int numReserva) {
    if (this.getReserva() == numReserva) {
      Propriedade propriedade = this.getPropriedade();
      if (propriedade != null && MackAirbnb.propriedadeExiste(propriedade.getId())) {
        if (!this.isConfirmacaoReserva()) {
          if (this.realizarPagamento()) {
            this.confirmacaoReserva = true;
            System.out.println("Pagemnto Realizado");
            System.out.println("Reserva " + numReserva + " confirmada com sucesso.");
          } else {
            System.out.println("Falha no pagamento. Reserva não confirmada.");
          }
        } else {
          System.out.println("A reserva " + numReserva + " já foi confirmada anteriormente.");
        }
      } else {
        System.out.println("Esta propriedade não está mais disponível para reserva.");
      }
    }
  }

  private boolean realizarPagamento() {
    return true;
  }

  public void realizarCheckout(LocalDate dataSaidaReal) {
    this.dataSaidaReal = dataSaidaReal;
  }

  public LocalDate getDataSaidaReal() {
    return dataSaidaReal;
  }

  public boolean estadiaConcluida() {
    if (dataSaidaReal != null) {
      LocalDate hoje = LocalDate.now();
      return hoje.isAfter(dataSaidaReal) || hoje.isEqual(dataSaidaReal);
    }
    return false;
  }
}

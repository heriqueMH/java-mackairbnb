import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
  private int numReserva = 0;
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
  }

  public int getReserva() {
    return numReserva;
  }

  public void setId(int id) {
    this.numReserva = id;
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
      if (!this.isConfirmacaoReserva()) {
        if (this.realizarPagamento()) {
          this.confirmacaoReserva = true;
          System.out.println("Reserva " + numReserva + " confirmada com sucesso.");
        } else {
          System.out.println("Falha no pagamento. Reserva não confirmada.");
        }
      } else {
        System.out.println("A reserva " + numReserva + " já foi confirmada anteriormente.");
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

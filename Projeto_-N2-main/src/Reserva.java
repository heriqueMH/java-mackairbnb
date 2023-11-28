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

  public boolean dataDisponivel() {
    for (Reserva reserva : MackAirbnb.reservas) {
      if (reserva != this && reserva.isConfirmacaoReserva() && reserva.getPropriedade().equals(this.getPropriedade())) {
        if (reserva.getDataCheckOut().isBefore(this.getDataCheckIn()) ||
            reserva.getDataCheckIn().isAfter(this.getDataCheckOut())) {
          continue;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  public boolean confirmarReserva(int numReserva) {
    for (Reserva reserva : MackAirbnb.reservas) {
      if (reserva.getReserva() == numReserva && reserva.getPropriedade().equals(this.getPropriedade())) {
        if (reserva.isConfirmacaoReserva()) {
          System.out.println("A reserva " + numReserva + " já foi confirmada anteriormente.");
          return false;
        } else {
          if (reserva.realizarPagamento()) {
            if (reserva.dataDisponivel()) {
              reserva.confirmacaoReserva = true;
              System.out.println("Pagamento Realizado");
              System.out.println("Reserva " + numReserva + " confirmada com sucesso.");
              return true;
            } else {
              System.out.println("A reserva " + numReserva +
                  " não pode ser confirmada pois a propriedade não está disponível para as datas solicitadas.");
              return false;
            }
          } else {
            System.out.println("Falha no pagamento. Reserva não confirmada.");
            return false;
          }
        }
      }
    }
    return false;
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

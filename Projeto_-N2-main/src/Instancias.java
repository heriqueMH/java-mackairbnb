import java.time.LocalDate;

public class Instancias {
  public Instancias() {
    Hospede hospede = new Hospede("Maria", "987654321", "maria@example.com", "Rua B", "senhaHospede");
    Usuario.cadastrarNovoUsuario(hospede);

    Hospede hospede1 = new Hospede("João", "12345678900", "joao@email.com", "Rua A, 123", "senha123");
    Usuario.cadastrarNovoUsuario(hospede1);

    Hospede hospede2 = new Hospede("Maria", "98765432100", "maria@email.com", "Rua B, 456", "senha456");
    Usuario.cadastrarNovoUsuario(hospede2);

    Proprietario proprietario = new Proprietario("João", "123456789", "joao@example.com", "Rua A", "senhaProprietario");
    Usuario.cadastrarNovoUsuario(proprietario);

    Proprietario proprietario1 = new Proprietario("Carlos", "11122233344", "carlos@email.com", "Rua C, 789","senha789");
    Usuario.cadastrarNovoUsuario(proprietario1);

    Proprietario proprietario2 = new Proprietario("Ana", "44455566677", "ana@email.com", "Rua D, 1011", "senha1011");
    Usuario.cadastrarNovoUsuario(proprietario2);

    Propriedade propriedade1 = new Propriedade("Casa na Praia", "Casa aconchegante à beira-mar", "Praia Grande", 5,200.0);

    Propriedade propriedade2 = new Propriedade("Chácara das Flores", "Chácara com piscina e jardim", "Interior", 10,300.0);

    Propriedade propriedade3 = new Propriedade("Apartamento Central", "Ótima localização no centro da cidade", "Centro",3, 150.0);

    Propriedade propriedade4 = new Propriedade("Casa na Montanha", "Vista panorâmica da montanha", "Serra", 8, 250.0);

    Propriedade propriedade5 = new Propriedade("Chalé na Praia", "Chalé aconchegante a poucos metros da praia", "Praia",4, 180.0);

    propriedade1.getFotos().add("foto1.jpg");
    propriedade1.getFotos().add("foto2.jpg");
    propriedade2.getFotos().add("foto3.jpg");
    propriedade3.getFotos().add("foto4.jpg");
    propriedade4.getFotos().add("foto5.jpg");
    propriedade5.getFotos().add("foto6.jpg");

    Reserva reserva1 = new Reserva(propriedade1, hospede);
    reserva1.setDataCheckIn(LocalDate.of(2023, 11, 25));
    reserva1.setDataCheckOut(LocalDate.of(2023, 11, 30));

    Reserva reserva2 = new Reserva(propriedade2, hospede2);
    reserva2.setDataCheckIn(LocalDate.of(2023, 12, 5));
    reserva2.setDataCheckOut(LocalDate.of(2023, 12, 10));

    Reserva reserva3 = new Reserva(propriedade3,hospede1);
    reserva3.setDataCheckIn(LocalDate.of(2023, 12, 12));
    reserva3.setDataCheckOut(LocalDate.of(2023, 12, 20));

    Reserva reserva4 = new Reserva(propriedade4,hospede2);
    reserva4.setDataCheckIn(LocalDate.of(2023, 12, 1));
    reserva4.setDataCheckOut(LocalDate.of(2023, 12, 7));

    Reserva reserva5 = new Reserva(propriedade5,hospede);
    reserva5.setDataCheckIn(LocalDate.of(2023, 12, 21));
    reserva5.setDataCheckOut(LocalDate.of(2023, 12, 25));

    MackAirbnb.reservas.add(reserva1);
    MackAirbnb.reservas.add(reserva2);
    MackAirbnb.reservas.add(reserva3);
    MackAirbnb.reservas.add(reserva4);
    MackAirbnb.reservas.add(reserva5);

  }
}
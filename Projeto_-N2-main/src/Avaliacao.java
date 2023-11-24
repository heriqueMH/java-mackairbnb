public class Avaliacao {
  private int pontuacao;
  private String comentario;

  public Avaliacao(int pontuacao, String comentario) {
    this.pontuacao = pontuacao;
    this.comentario = comentario;
  }

  public Avaliacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public String toString() {
    if (comentario != null) {
      return "Nota: " + this.pontuacao + ", Comentário: " + this.comentario;
    } else {
      return "Nota: " + this.pontuacao;
    }
  }
}

package dominio;


public class BancaAvaliadora {

    private Integer id_banca;
    private Projeto projeto;
    private Professor avaliador1;
    private Professor avaliador2;
    private Situacao situacao;
    private Avaliacao avaliacao;

    public Integer getId_banca() {
        return id_banca;
    }

    public void setId_banca(Integer id_banca) {
        this.id_banca = id_banca;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Professor getAvaliador1() {
        return avaliador1;
    }

    public void setAvaliador1(Professor avaliador1) {
        this.avaliador1 = avaliador1;
    }

    public Professor getAvaliador2() {
        return avaliador2;
    }

    public void setAvaliador2(Professor avaliador2) {
        this.avaliador2 = avaliador2;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    
    
}

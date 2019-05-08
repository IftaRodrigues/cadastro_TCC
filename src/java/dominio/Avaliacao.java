package dominio;

import java.sql.Date;

public class Avaliacao {

    private Integer id_avaliacao;
    private float notaAvaliador1;
    private float notaAvaliador2;
    private float notaOrientador;
    private Date dataRealizacao= new Date(0);
    private Resultado resultado;

    public Integer getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(Integer id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public float getNotaAvaliador1() {
        return notaAvaliador1;
    }

    public void setNotaAvaliador1(float notaAvaliador1) {
        this.notaAvaliador1 = notaAvaliador1;
    }

    public float getNotaAvaliador2() {
        return notaAvaliador2;
    }

    public void setNotaAvaliador2(float notaAvaliador2) {
        this.notaAvaliador2 = notaAvaliador2;
    }

    public float getNotaOrientador() {
        return notaOrientador;
    }

    public void setNotaOrientador(float notaOrientador) {
        this.notaOrientador = notaOrientador;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    
    
    
}

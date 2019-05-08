
package dominio;

public class Projeto {
    private Integer id_projeto;
    private String titulo;
    private Aluno aluno;
    private Curso curso;
    private TipoProjeto tipoProjeto;
    private AreaPesquisa areaPesquisa;
    private Professor orientador;


    public Integer getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(Integer id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    public AreaPesquisa getAreaPesquisa() {
        return areaPesquisa;
    }

    public void setAreaPesquisa(AreaPesquisa areaPesquisa) {
        this.areaPesquisa = areaPesquisa;
    }

    public Professor getOrientador() {
        return orientador;
    }

    public void setOrientador(Professor orientador) {
        this.orientador = orientador;
    }
    
    
    
}

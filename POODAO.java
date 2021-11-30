import java.util.List;

public interface POODAO {
    void adicionarAluno(POOaluno aluno);
    List<POOaluno> pesquisarPorNomeAluno(String nome);
    void removerAluno(long RA);
    void atualizarAluno(long RA, POOaluno aluno);

    void adicionarMateria(POOmateria materia);
    List<POOmateria> pesquisarPorNomeMateria(String nome);
    void removerMateria(long Codigo);
    void atualizarMateria(long Codigo, POOmateria materia);
}

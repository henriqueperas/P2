import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class POOalunoControl {
    LongProperty RA = new SimpleLongProperty(0);
    StringProperty Nome = new SimpleStringProperty("");
    StringProperty Sobrenome = new SimpleStringProperty("");
    ObjectProperty nasimento = new SimpleObjectProperty(LocalDate.now());
    IntegerProperty CPF = new SimpleIntegerProperty(0);

    private POODAO POODAO = new POODAOImpl();

    //private List<POOaluno> alunosGeral = new ArrayList<>();
    private ObservableList<POOaluno> alunos = FXCollections.observableArrayList();

    public void adicionarAluno(){
        //POOaluno aluno = toEntity();
        //aluno.setRA(counter+1);
        //alunos.add(aluno);
        //fromEntity(new POOaluno());

        POOaluno aluno = new POOaluno();
        fromEntity(aluno);
    }

    public void pesquisarAluno(){
        alunos.clear();
        List<POOaluno> encontrados = POODAO.pesquisarPorNomeAluno(Nome.get());
        alunos.addAll(encontrados);

        if(!alunos.isEmpty()){
            fromEntity(alunos.get(0));
        }
    }

    public void atualizarAluno(){
        POOaluno aluno = toEntity();
        if(aluno.getRA() == 0){
            POODAO.adicionarAluno(aluno);
        }else{
            POODAO.atualizarAluno(RA.get(), aluno);
        }
    }

    public ObservableList<POOaluno> getListaAluno() {
        return alunos;
    }

    private POOaluno toEntity() {
        POOaluno aluno = new POOaluno();
        aluno.setRA(RA.get());
        aluno.setNome(Nome.get());
        aluno.setSobrenome(Sobrenome.get());
        aluno.setCPF(CPF.get());
        aluno.setNasimento((LocalDate)nasimento.get());
        return aluno;
    }

    private void fromEntity(POOaluno aluno) {
        RA.set(aluno.getRA());
        Nome.set(aluno.getNome());
        Sobrenome.set(aluno.getSobrenome());
        nasimento.set(aluno.getNasimento());
        CPF.set(aluno.getCPF());
    }

}

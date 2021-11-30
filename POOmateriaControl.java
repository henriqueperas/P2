import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class POOmateriaControl {
    LongProperty Codigo = new SimpleLongProperty(0);
    StringProperty NomeMateria = new SimpleStringProperty("");
    StringProperty Duracao = new SimpleStringProperty("");
    StringProperty Truno = new SimpleStringProperty("");

    private POODAO POODAO = new POODAOImpl();

    //private List<POOaluno> alunosGeral = new ArrayList<>();
    private ObservableList<POOmateria> materias = FXCollections.observableArrayList();

    public void adicionarMateria(){

        POOmateria materia = new POOmateria();
        fromEntity(materia);
    }

    public void pesquisarMateria(){
        materias.clear();
        List<POOmateria> encontrados = POODAO.pesquisarPorNomeMateria(NomeMateria.get());
        materias.addAll(encontrados);

        if(!materias.isEmpty()){
            fromEntity(materias.get(0));
        }
    }

    public void atualizarMateria(){
        POOmateria materia = toEntity();
        if(materia.getCodigo() == 0){
            POODAO.adicionarMateria(materia);
        }else{
            POODAO.atualizarMateria(Codigo.get(), materia);
        }
    }

    public ObservableList<POOmateria> getListaMateria() {
        return materias;
    }

    private POOmateria toEntity() {
        POOmateria materia = new POOmateria();
        materia.setCodigo(Codigo.get());
        materia.setNome(NomeMateria.get());
        materia.setDuracao(Duracao.get());
        materia.setTurno(Truno.get());
        return materia;
    }

    private void fromEntity(POOmateria materia) {
        Codigo.set(materia.getCodigo());
        NomeMateria.set(materia.getNome());
        Duracao.set(materia.getDuracao());
        Truno.set(materia.getTurno());
    }

}

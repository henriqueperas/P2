import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class POOAlunoBoundary{
    private TextField txtRA = new TextField();
    private TextField txtNomeAluno = new TextField();
    private TextField txtSobrenome = new TextField();
    private TextField txtNasimento = new TextField();
    private TextField txtCPF = new TextField();

    private Button btnAdicionarAluno = new Button("AdicionarAluno");
    private Button btnPesquisarAluno = new Button("PesquisaAluno");
    private Button btnAtualizarAluno = new Button("AtualizarAluno");

    private POOalunoControl alunoControl = new POOalunoControl();

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TableView<POOaluno> tablealuno =  new TableView<>();

    private void criarTabelaAluno(){
        TableColumn<POOaluno, Long> col1 = new TableColumn<>("RA");
        col1.setCellValueFactory(
                new PropertyValueFactory<POOaluno, Long>("RA")
        );
        TableColumn<POOaluno, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(
                new PropertyValueFactory<POOaluno, String>("nome")
        );
        TableColumn<POOaluno, String> col3 = new TableColumn<>("Sobrenome");
        col3.setCellValueFactory(
                new PropertyValueFactory<POOaluno, String>("sobrenome")
        );
        TableColumn<POOaluno, String> col4 = new TableColumn<>("Nasimento");
        col4.setCellValueFactory( (item) -> {
                    LocalDate d = item.getValue().getNasimento();
                    return new ReadOnlyStringWrapper(d.format(fmt));
                }
        );
        TableColumn<POOaluno, Integer> col5 = new TableColumn<>("CPF");
        col5.setCellValueFactory(
                new PropertyValueFactory<POOaluno, Integer>("CPF")
        );
        tablealuno.getColumns().addAll(col1, col2, col3, col4, col5);
        tablealuno.setItems(alunoControl.getListaAluno());
    }

    public Pane renderAluno() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(tablealuno);

        criarTabelaAluno();

        txtRA.setEditable(false);

        Bindings.bindBidirectional(txtRA.textProperty(), alunoControl.RA, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeAluno.textProperty(), alunoControl.Nome);
        Bindings.bindBidirectional(txtSobrenome.textProperty(), alunoControl.Sobrenome);
        Bindings.bindBidirectional(txtNasimento.textProperty(), alunoControl.nasimento, new LocalDateStringConverter());
        Bindings.bindBidirectional(txtCPF.textProperty(), alunoControl.CPF, new NumberStringConverter());

        panCampos.add(new Label("RA"),0,0);
        panCampos.add(txtRA,1,0);
        panCampos.add(new Label("Nome"),0,1);
        panCampos.add(txtNomeAluno,1,1);
        panCampos.add(new Label("Sobrenome"),0,2);
        panCampos.add(txtSobrenome,1,2);
        panCampos.add(new Label("Data de nascimento"),0,3);
        panCampos.add(txtNasimento,1,3);
        panCampos.add(new Label("CPF"),0,4);
        panCampos.add(txtCPF,1,4);

        panCampos.add(btnAdicionarAluno,0,5);
        panCampos.add(btnPesquisarAluno,1,5);
        panCampos.add(btnAtualizarAluno,2,5);

        btnAdicionarAluno.setOnAction((e) -> {
            alunoControl.adicionarAluno();
            new Alert(Alert.AlertType.INFORMATION, "aluno adicionado com sucesso").showAndWait();
        });

        btnPesquisarAluno.setOnAction((e) -> {
            alunoControl.pesquisarAluno();
        });

        btnAtualizarAluno.setOnAction((e) -> {
            alunoControl.atualizarAluno();
        });

        return panPrincipal;
    }

}

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class POOMateriaBoundary {
    private TextField txtCodigo = new TextField();
    private TextField txtNomeMateria = new TextField();
    private TextField txtDuracao = new TextField();
    private TextField txtTurno = new TextField();

    private Button btnAdicionarMateria = new Button("AdicionarMateria");
    private Button btnPesquisarMateria = new Button("PesquisarMateria");
    private Button btnAtualizarMateria = new Button("AtualizarMateria");

    private POOmateriaControl materiaControl = new POOmateriaControl();

    private TableView<POOmateria> tablemateria =  new TableView<>();

    private void criarTabelaMateria(){
        TableColumn<POOmateria, Long> col1 = new TableColumn<>("Codigo");
        col1.setCellValueFactory(
                new PropertyValueFactory<POOmateria, Long>("codigo")
        );
        TableColumn<POOmateria, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(
                new PropertyValueFactory<POOmateria, String>("nome")
        );
        TableColumn<POOmateria, String> col3 = new TableColumn<>("Duracao");
        col3.setCellValueFactory(
                new PropertyValueFactory<POOmateria, String>("duracao")
        );
        TableColumn<POOmateria, String> col4 = new TableColumn<>("Turno");
        col4.setCellValueFactory(
                new PropertyValueFactory<POOmateria, String>("turno")
        );
        tablemateria.getColumns().addAll(col1, col2, col3, col4);
        tablemateria.setItems(materiaControl.getListaMateria());
    }

    public Pane renderMateria() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(tablemateria);

        criarTabelaMateria();

        txtCodigo.setEditable(false);

        Bindings.bindBidirectional(txtCodigo.textProperty(), materiaControl.Codigo, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeMateria.textProperty(), materiaControl.NomeMateria);
        Bindings.bindBidirectional(txtDuracao.textProperty(), materiaControl.Duracao);
        Bindings.bindBidirectional(txtTurno.textProperty(), materiaControl.Truno);

        panCampos.add(new Label("Codigo"),0,0);
        panCampos.add(txtCodigo,1,1);
        panCampos.add(new Label("Nome"),0,2);
        panCampos.add(txtNomeMateria,1,2);
        panCampos.add(new Label("Duração"),0,3);
        panCampos.add(txtDuracao,1,3);
        panCampos.add(new Label("Turno"),0,4);
        panCampos.add(txtTurno,1,4);

        panCampos.add(btnAdicionarMateria,0,5);
        panCampos.add(btnPesquisarMateria,1,5);
        panCampos.add(btnAtualizarMateria,2,5);

        btnAdicionarMateria.setOnAction((e) -> {
            materiaControl.adicionarMateria();
            new Alert(Alert.AlertType.INFORMATION, "materia adicionado com sucesso").showAndWait();
        });

        btnPesquisarMateria.setOnAction((e) -> {
            materiaControl.pesquisarMateria();
        });

        btnAtualizarMateria.setOnAction((e) -> {
            materiaControl.atualizarMateria();
        });

        return panPrincipal;

    }
}

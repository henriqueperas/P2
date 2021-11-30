import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;

public class PrincipalBoundary extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane bp = new BorderPane();
        Scene scn = new Scene(bp, 1024,768);

        MenuBar menuBar = new MenuBar();

        Menu mnuCadastro = new Menu("Cadastro");
        Menu mnuAjuda = new Menu("Ajuda");

        menuBar.getMenus().addAll(mnuCadastro, mnuAjuda);

        MenuItem mnuAluno = new MenuItem("Aluno");
        mnuAluno.setOnAction((e) ->{
            POOAlunoBoundary alunoBoundary = new POOAlunoBoundary();
            bp.setCenter(alunoBoundary.renderAluno());
        });
        MenuItem mnuMateria = new MenuItem("Materia");
        mnuMateria.setOnAction((e) ->{
            POOMateriaBoundary materiaBoundary = new POOMateriaBoundary();
            bp.setCenter(materiaBoundary.renderMateria());
        });

        mnuCadastro.getItems().addAll(mnuAluno, mnuMateria);

        bp.setTop(menuBar);

        stage.setScene(scn);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch(PrincipalBoundary.class, args);
    }
}

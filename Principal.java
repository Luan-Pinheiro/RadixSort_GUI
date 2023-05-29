import controller.Controlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import  javafx.stage.Stage;
import model.RadixSort;

public class Principal extends Application{
  private static Scene startTelaOrdena;
  //instancia do controller
  Controlador controleRadix = new Controlador();
  RadixSort rS = new RadixSort();
  //classe principal 
  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception{
    try {
      //definicao de icone e localizando no diretorio
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./view/assets/icon.png")));
      //setando o nome da aplicacao
      primaryStage.setTitle("Ordenar por RadixSort");
      //impedindo a mudanca na resolucao das janelas, travando redimencionamento
      primaryStage.resizableProperty().setValue(false);

      //carregando os arquivos da tela e gerando novas cenas
      Parent telaOrdena = FXMLLoader.load(getClass().getResource("./view/RadixSort.fxml"));
      startTelaOrdena = new Scene(telaOrdena);

      //atribuindo o aquivo xml da cena inicial e exibindo a cena na janela
      primaryStage.setScene(startTelaOrdena);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();// mensagem padrão do java em caso de erro 
    }
  }
}
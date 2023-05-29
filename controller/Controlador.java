package controller;

import model.RadixSort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controlador implements Initializable{
  Alert alerta = new  Alert(AlertType.ERROR);
  private boolean wasClicked = false;
  private boolean wasCreated = false;
  int[] vetor;
  @FXML
  private Button btnOrdenar;
  @FXML
  private Button btnRandomico;
  @FXML
  private Button btnDuvida;
  @FXML
  private TextField txtTamVet;
  @FXML
  private Button btnInserir;
  @FXML
  private Label lblTempo;
  @FXML
  private ImageView imgExplicacao;
  RadixSort rS;
  
  @FXML
  void OnClickBtnInserir(ActionEvent event) {
    String strAux = getTxtTamVet();
    if((!getTxtTamVet().isEmpty()) && strAux.matches("[+-]?\\d*(\\.\\d+)?") && Long.parseLong(strAux) <= 100000){
      vetor = new int[Integer.parseInt(strAux)];
      wasCreated = true;
      System.out.println("VETOR INSERIDO!\nTamanho do vetor: " + vetor.length + "\n");
    }else if(strAux.matches("[+-]?\\d*(\\.\\d+)?") && (!getTxtTamVet().isEmpty())){
      alerta.setTitle("Erro na criacao");
      alerta.setHeaderText("Vetor grande demais!");
      alerta.setContentText("Preencha o vetor com ate 100000 de tamanho!");
      alerta.showAndWait();
    }else if(strAux.matches("[+-]?\\d*(\\.\\d+)?")){
      alerta.setTitle("Erro na criacao");
      alerta.setHeaderText("Entrada vazia!");
      alerta.setContentText("Preencha o vetor!");
      alerta.showAndWait();
    }else{
      alerta.setTitle("Erro na criacao");
      alerta.setHeaderText("Entrada não eh numerica!");
      alerta.setContentText("Preencha o vetor com apenas caracteres numericos");
      alerta.showAndWait();
    }
  }

  @FXML
  void OnClickOrdenar(ActionEvent event) {
    if(!wasClicked){
      alerta.setTitle("Erro na ordenacao");
      alerta.setHeaderText("Opcao vazia ou inexistente!");
      alerta.setContentText("Preencha o vetor antes de tentar ordena-lo!");
      alerta.showAndWait();
      wasClicked = false;
    }else{
      long inicio = System.currentTimeMillis();
      rS = new RadixSort(this);
      rS.print(vetor);
      long fim = System.currentTimeMillis();
      long total = fim -inicio;
      lblTempo.setText((String.valueOf(total)+"ms"));
      System.out.println("Tempo total: " + total + "ms");
    }
  }

  @FXML
  void OnClickRandomico(ActionEvent event) {
    if(!wasCreated){
      alerta.setTitle("Erro na geração de randomico");
      alerta.setHeaderText("Opcao vazia ou inexistente!");
      alerta.setContentText("Defina o tamanho do vetor antes de\ntentar gerar os seus valores!");
      alerta.showAndWait();
      wasClicked = false;
      wasCreated = false;
    }else{
      wasClicked = true;
      int infLim = 1;
      int supLim = 10000;
      Random randomNUmber = new Random();

      System.out.print("Vetor[" + vetor.length + "] original: [");
      for(int i = 0 ; i < vetor.length; i++){
        vetor[i] = randomNUmber.nextInt(supLim) + infLim;
        if(i==vetor.length - 1){
          System.out.print(vetor[i] + "");
        } else {
          System.out.print(vetor[i] + ", ");
        }
      }
      System.out.print("]");
    }
  }
  
  
  //Getters e Setters
  public String getTxtTamVet() {
    return txtTamVet.getText();
  }
  public boolean getWasClicked() {
    return wasClicked;
  }
  public boolean getWasCreated() {
    return wasCreated;
  }
  public void setTxtTamVet(TextField txtTamVet) {
    this.txtTamVet = txtTamVet;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    txtTamVet.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > 6) {
        txtTamVet.setText(oldValue);
      }
    });
    imgExplicacao.setVisible(false);
    btnDuvida.setOnMouseEntered(event ->{
      imgExplicacao.setVisible(true);
    });
    btnDuvida.setOnMouseExited(event ->{
      imgExplicacao.setVisible(false);
    });
  }

}

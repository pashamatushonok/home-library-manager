package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerAdd {

    @FXML
    public TextField txtAuthor;

    @FXML
    public TextField txtName;

    @FXML
    public TextField txtPages;

    @FXML
    public Button btnCancel;

    @FXML
    public Button btnOk;

    Book book = new Book();


    public void btnOkClick(ActionEvent actionEvent) {
        save();
        btnCancelClick(actionEvent);
    }

    public void setbook(Book book){
        this.book = book;
        txtAuthor.setText("");
        txtName.setText("");
        txtPages.setText("");

    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void save(){
        book.setAuthor(txtAuthor.getText());
        book.setName(txtName.getText());
        if (checkstr(txtPages.getText()))
            book.setTotalpage(Integer.parseInt(txtPages.getText()));
        else
            book.setTotalpage(1);
    }

    private boolean checkstr(String s){
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) < '0' || s.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }
}

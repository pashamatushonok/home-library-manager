package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerEdit {
    @FXML
    public TextField txtAuthor;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtTotalPage;
    @FXML
    public TextField txtUserPage;
    @FXML
    public CheckBox cbFavorite;
    @FXML
    public CheckBox cbInprocess;
    @FXML
    public CheckBox cbIsfifnished;
    @FXML
    public TextArea txtNotes;

    private Book book;

    public void setbook(Book book){
        this.book = book;
        txtAuthor.setText(book.getAuthor());
        txtName.setText(book.getName());
        if (book.getTotalpage() > 1)
            txtTotalPage.setText(String.valueOf(book.getTotalpage()));
        else
            txtTotalPage.setText("");
        if (book.getUserpage() > 1)
            txtUserPage.setText(String.valueOf(book.getUserpage()));
        else
            txtUserPage.setText("");
        if (book.getFavorite())
            cbFavorite.setSelected(true);
        else
            cbFavorite.setSelected(false);
        if (book.getInprocess())
            cbInprocess.setSelected(true);
        else
            cbInprocess.setSelected(false);
        if (book.getIsfinished())
            cbIsfifnished.setSelected(true);
        else
            cbIsfifnished.setSelected(false);
        txtNotes.setText(book.getNotes());
    }


    public void btnOkClick(ActionEvent actionEvent) {
        savechanges();
        btnCancelclick(actionEvent);
    }

    public void btnCancelclick(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    private void savechanges() {
        book.setAuthor(txtAuthor.getText());
        book.setName(txtName.getText());
        book.setNotes(txtNotes.getText());
        if (!txtUserPage.getText().equals("") && checkstr(txtUserPage.getText())) {
            book.setUserpage(Integer.parseInt(txtUserPage.getText()));
            if (Integer.parseInt(txtUserPage.getText()) != 0) {
                book.setInprocess(true);
                cbInprocess.setSelected(true);
            }
            else{
                book.setInprocess(false);
                cbInprocess.setSelected(false);
            }
        }
        if (!txtTotalPage.getText().equals("") && checkstr(txtTotalPage.getText()))
                book.setTotalpage(Integer.parseInt(txtTotalPage.getText()));
        if (txtTotalPage.getText().equals(txtUserPage.getText()) && !txtTotalPage.getText().equals("")) {
            book.setInprocess(false);
            cbInprocess.setSelected(false);
            book.setIsfinished(true);
            cbIsfifnished.setSelected(true);
        }
        if (cbFavorite.isSelected()) {
            book.setFavorite(true);
            book.setChFavorite((char) 9733);
        } else {
            book.setFavorite(false);
            book.setChFavorite(' ');
        }
        if (cbIsfifnished.isSelected()) {
            book.setIsfinished(true);
        } else {
            book.setIsfinished(false);
        }
        if (cbInprocess.isSelected()) {
            book.setInprocess(true);
        } else {
            book.setInprocess(false);
        }
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

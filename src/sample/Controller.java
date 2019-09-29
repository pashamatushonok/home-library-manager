package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Controller {

    public CollectionBooks booksimpl = new CollectionBooks();

    @FXML
    public Button btnSave;

    @FXML
    public Button btnPresearch;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    public Button btnDetails;

    @FXML
    private TextField edtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView tblBooks;

    @FXML
    private TableColumn<Book, String> colAuthor;

    @FXML
    private TableColumn<Book, String> colBook;

    @FXML
    private TableColumn<Book, Character> colFavorite;

    @FXML
    private Label lblAmount;


    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FXMLLoader fxmlLoaderadd = new FXMLLoader();
    private FXMLLoader fxmlLoadermore = new FXMLLoader();
    private ControllerEdit controllerEdit;
    private Stage editStage;

    private Parent fxmlAdd;
    private ControllerAdd controllerAdd;
    private Stage addStage;

    private Parent fxmlMore;
    private ControllerMore controllerMore;
    private Stage morestage;

    @FXML
    private void initialize(){


        booksimpl.getBookList().addListener(new ListChangeListener<Book>() {
            @Override
            public void onChanged(Change<? extends Book> c) {
                updatelblamount();
            }
        });

        booksimpl.fread();
        filltable();


        try {
            fxmlLoader.setLocation(getClass().getResource("edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            controllerEdit = fxmlLoader.getController();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            fxmlLoaderadd.setLocation(getClass().getResource("add.fxml"));
            fxmlAdd = fxmlLoaderadd.load();
            controllerAdd = fxmlLoaderadd.getController();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            fxmlLoadermore.setLocation(getClass().getResource("more.fxml"));
            fxmlMore = fxmlLoadermore.load();
            controllerMore = fxmlLoadermore.getController();
        }catch (IOException e){
            e.printStackTrace();
        }
        edtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean flag = true;
                if (newValue.length() == 0) {
                    btnPresearch.setVisible(false);
                    btnPresearch.setText("");
                } else {
                    int i = 0;
                    while (flag && i < booksimpl.getsize()) {
                        if (booksimpl.partequal(booksimpl.getbook(i), newValue, newValue.length())) {
                            btnPresearch.setVisible(true);
                            btnPresearch.setText(booksimpl.getbook(i).getName());
                            flag = false;
                        } else {
                            btnPresearch.setVisible(false);
                            btnPresearch.setText("");
                        }
                        i++;
                    }
                }
            }
        });
    }

    private void filltable(){
        colFavorite.setText((char) 9733+"");
        colAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        colBook.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        colFavorite.setCellValueFactory(new PropertyValueFactory<Book, Character>("chFavorite"));

        tblBooks.setItems(booksimpl.getBookList());


    }

    public void btnAddClick(ActionEvent actionEvent) {

        Book newbook = new Book();

        controllerAdd.setbook(newbook);

        if (addStage == null){
            addStage = new Stage();
            addStage.setTitle("Добавить книгу");
            addStage.setScene(new Scene(fxmlAdd));
        }
        addStage.showAndWait();
        booksimpl.add(newbook);
    }

    public void btnEditClick(ActionEvent actionEvent) {

        Book selectedbook = (Book) tblBooks.getSelectionModel().getSelectedItem();
        if (selectedbook != null) {
            controllerEdit.setbook(selectedbook);

            if (editStage == null) {
                editStage = new Stage();
                editStage.setTitle("Изменить книгу");
                editStage.setScene(new Scene(fxmlEdit));
            }
            editStage.showAndWait();
        }
        tblBooks.refresh();
    }

    public void updatelblamount(){
        lblAmount.setText("Количество книг " + booksimpl.getsize());
    }

    public void btnMoreClick(ActionEvent actionEvent) {
        Book selectedbook = (Book) tblBooks.getSelectionModel().getSelectedItem();
        if (selectedbook != null) {
            controllerMore.setbook(selectedbook);

            if (morestage == null){
                morestage = new Stage();
                morestage.setTitle("Подробная информация");
                morestage.setScene(new Scene(fxmlMore));
            }
            morestage.show();
        }
    }

    public void btnDeleteClick(ActionEvent actionEvent) {
        Book selectedbook = (Book) tblBooks.getSelectionModel().getSelectedItem();
        booksimpl.delete(selectedbook);
    }

    public void btnSearchClick(ActionEvent actionEvent) {
        String searchbook = edtSearch.getText();
        if (!searchbook.equals("")){
            int i = 0;
            boolean f = true;
            while (f && i < booksimpl.getsize()){
                Book tempbook = new Book();
                tempbook = booksimpl.getbook(i);
                if (tempbook.getName().equals(searchbook)) {
                    controllerMore.setbook(tempbook);

                    if (morestage == null){
                        morestage = new Stage();
                        morestage.setTitle("Подробная информация");
                        morestage.setScene(new Scene(fxmlMore));
                    }
                    morestage.show();
                }
                i++;
                }
            }
        }

    public void btnSaveclick(ActionEvent actionEvent) {
        booksimpl.fwrite();
    }


    public void PresearchClick(ActionEvent actionEvent) {
        edtSearch.setText(btnPresearch.getText());
        btnPresearch.setVisible(false);
        btnPresearch.setText("");
    }
}


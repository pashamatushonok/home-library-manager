package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class ControllerMore {

    @FXML
    public ProgressBar progressbar;
    @FXML
    public Label lblProgress;
    @FXML
    public Label lblAuthor;
    @FXML
    public Label lblName;
    @FXML
    public RadioButton rbFavorite;
    @FXML
    public RadioButton rbIsFinished;
    @FXML
    public RadioButton rbInProcess;
    @FXML
    public TextArea txtNotes;
    @FXML
    public Label lblTotalPage;
    @FXML
    public Label lblUserPage;

    Book book = new Book();
    public void setbook(Book book){
        this.book = book;
        lblAuthor.setText(book.getAuthor());
        lblName.setText(book.getName());
        txtNotes.setText(book.getNotes());
        if (book.getTotalpage() > 1)
            lblTotalPage.setText(String.valueOf(book.getTotalpage()));
        //if (book.getUserpage() > 1)
            lblUserPage.setText(String.valueOf(book.getUserpage()));
        if (book.getFavorite())
            rbFavorite.setSelected(true);
        else
            rbFavorite.setSelected(false);
        if (book.getInprocess())
            rbInProcess.setSelected(true);
        else
            rbInProcess.setSelected(false);
        if (book.getIsfinished())
            rbIsFinished.setSelected(true);
        else
            rbIsFinished.setSelected(false);
        if (book.getTotalpage() > 0){
            float readprogress;
            int upage,tpage;
            upage = book.getUserpage();
            tpage = book.getTotalpage();
            readprogress = (float)upage/tpage;
            progressbar.setProgress((double)readprogress);
            readprogress *= 100;
            String strprogress = String.format("%.1f",readprogress);
            lblProgress.setText("Прогресс прочтения "+strprogress+"%");
        }
    }
}

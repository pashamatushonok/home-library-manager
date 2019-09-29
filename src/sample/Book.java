package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Book implements Serializable{
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty author = new SimpleStringProperty("");
    private int totalpage;
    private int userpage;
    private String notes;
    private Boolean favorite;
    private char chFavorite;
    private Boolean inprocess;
    private Boolean isfinished;

    Book(){
        this.name.set("");
        this.author.set("");
        this.favorite = false;
        this.totalpage = 1;
        this.userpage = 0;
        this.notes = "";
        this.inprocess = false;
        this.isfinished = false;
        this.chFavorite = ' ';
    }

    Book(String author, String name){
        this.name.set(name);
        this.author.set(author);
        this.favorite = false;
        this.totalpage = 1;
        this.userpage = 0;
        this.notes = "";
        this.inprocess = false;
        this.isfinished = false;
        this.chFavorite = ' ';
    }

    Book(String author, String name, int pages){
        this.name.set(name);
        this.author.set(author);
        this.favorite = false;
        this.totalpage = pages;
        this.userpage = 0;
        this.notes = "";
        this.inprocess = false;
        this.isfinished = false;
        this.chFavorite = ' ';
    }

    Book(SavedBook book){
        this.name.set(book.name);
        this.author.set(book.author);
        favorite = book.favorite;
        totalpage = book.totalpage;
        userpage = book.userpage;
        notes = book.notes;
        inprocess = book.inprocess;
        isfinished = book.isfinished;
        chFavorite = book.chFavorite;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) { this.name.set(name); }

    public SimpleStringProperty nameProperty(){return name;}

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public SimpleStringProperty authorProperty(){ return author; }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getUserpage() {
        return userpage;
    }

    public void setUserpage(int userpage) {
        this.userpage = userpage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getInprocess() {
        return inprocess;
    }

    public void setInprocess(Boolean inprocess) {
        this.inprocess = inprocess;
    }

    public Boolean getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Boolean isfinished) {
        this.isfinished = isfinished;
    }

    public void setChFavorite(char c){ this.chFavorite = c; }

    public char getChFavorite(){ return chFavorite;}
}

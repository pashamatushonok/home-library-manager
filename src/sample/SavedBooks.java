package sample;

import java.io.Serializable;

class SavedBook implements Serializable {
    String name;
    String author;
    int totalpage;
    int userpage;
    String notes;
    Boolean favorite;
    char chFavorite;
    Boolean inprocess;
    Boolean isfinished;

    SavedBook (Book book){
        this.name = book.getName();
        this.author = book.getAuthor();
        this.totalpage = book.getTotalpage();
        this.userpage = book.getUserpage();
        this.notes = book.getNotes();
        this.favorite = book.getFavorite();
        this.chFavorite = book.getChFavorite();
        this.inprocess = book.getInprocess();
        this.isfinished = book.getIsfinished();
    }
}

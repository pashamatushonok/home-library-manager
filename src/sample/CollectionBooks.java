package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;


public class CollectionBooks implements Books{

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @Override
    public void add(Book book){
       bookList.add(book);
    }

    @Override
    public void update(Book book){ }

    @Override
    public void delete(Book book){
        bookList.remove(book);
    }

    public ObservableList <Book> getBookList(){
        return bookList;
    }

    public int getsize(){return bookList.size();}

    public void print(){
        for (Book book : bookList){
            System.out.println("author "+book.getAuthor()+" name "+book.getName());
        }
    }

    public void fillTestData(){
        bookList.add(new Book("А.С. Пушкин", "Евгений Онегин"));
        bookList.add(new Book("Л.Н. Толстой", "Война и мир"));
        bookList.add(new Book("Ф.М. Достоевский", "Преступление и наказание"));
    }

    public Book getbook(int i){
        return bookList.get(i);
    }

    public void fwrite(){
        ArrayList<SavedBook> savedbooks = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++){
            SavedBook tempbook = new SavedBook(bookList.get(i));
            savedbooks.add(tempbook);
        }
        try {
            FileOutputStream fos = new FileOutputStream("mybooks.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(savedbooks);
            oos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void fread(){
        ArrayList<SavedBook> savedBooks = new ArrayList<>();

        try {
            try {
                FileInputStream fileInputStream = new FileInputStream("mybooks.bin");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                savedBooks = (ArrayList<SavedBook>) objectInputStream.readObject();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < savedBooks.size(); i++){
            Book tempbook = new Book(savedBooks.get(i));
            bookList.add(tempbook);
        }
    }

    public boolean partequal(Book book, String s, int n){
        for (int i = 0; i < n; i++){
            if (book.getName().charAt(i) != s.charAt(i))
                return false;
        }
        return true;
    }
}


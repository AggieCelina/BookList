package aggiecelina.booklistingapp;

/**
 * Created by aggiecelina on 15.07.17.
 */

public class Book {

    private String author;
    private String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}

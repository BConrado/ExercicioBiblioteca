public class Book {

    private int number;
    private String bookName;
    private String writer;
    private int year;
    private String borrowedTo;
    private BookStatus status;

    public Book(){}

    public Book(int number, String bookName, String writer, 
                int year, BookStatus status, String borrowedTo) {
        this.number = number;
        this.bookName = bookName;
        this.writer = writer;
        this.year = year;
        this.status = status;
        this.borrowedTo = borrowedTo;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBookName() {
        return this.bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriter() {
        return this.writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BookStatus getStatus() {
        return this.status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getBorrowedTo() {
        return this.borrowedTo;
    }

    public void setBorrowedTo(String borrowedTo) {
        this.borrowedTo = borrowedTo;
    }


    @Override
    public String toString() {
        return "{\n" +
            "Numero='" + getNumber() + "'" +
            ", \nTitulo='" + getBookName() + "'" +
            ", \nAutor='" + getWriter() + "'" +
            ", \nAno='" + getYear() + "'" +
            ", \nStatus='" + getStatus() + "'" +
            ", \nEmprestado para='" + getBorrowedTo() + "'\n" +
            "}";
    }
    
    

    
}
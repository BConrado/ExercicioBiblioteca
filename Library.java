import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    
    private static ArrayList<Book> list;
    //it`s called when to program starts to get all the books from the "Database".
    public static ArrayList<Book> read() throws FileNotFoundException{
        ArrayList list = new ArrayList<Book>();
        File file = new File("database.txt");
        Scanner in = new Scanner(file);
        int bookqnt = Integer.parseInt(in.nextLine());
        int number;
        String bookName;
        String writer;
        int year;
        BookStatus status;
        String borrowedTo;
        System.out.println("Quantidade de livros " + bookqnt);
        //System.out.println("Comecou a cadastrar os livros");
        for(int i = 0; i < bookqnt; i++){
            //System.out.println("numero do livro");
            String [] line = in.nextLine().split(":");
            //System.out.println(line[0]);
            //System.out.println(line[1]);
            number = Integer.parseInt(line[1].trim());
            //System.out.println(number);

           // System.out.println("nome do livro");
            line = in.nextLine().split(":");
            bookName = line[1].trim();            
           // System.out.println(bookName);

          //  System.out.println("nome do escritor");
            line = in.nextLine().split(":");
            writer = line[1].trim();
           // System.out.println(writer);

           // System.out.println("ano do livro");
            line = in.nextLine().split(":");
            year = Integer.parseInt(line[1].trim());
           // System.out.println(year);

           // System.out.println("status do livro");
            line = in.nextLine().split(":");
            if(line[1].trim().equals("Disponivel")){
                status = BookStatus.Available;
            }else{
                status = BookStatus.Unavailable;
            }
           // System.out.println(status);
             
          //  System.out.println("emprestado para");
            line = in.nextLine().split(":");
            borrowedTo = line[1].trim();
          //  System.out.println(borrowedTo);
            if(borrowedTo.equals("Ninguem")){
                borrowedTo ="";
            }
            Book b = new Book(number,bookName,writer,year,status,borrowedTo); 
            
            in.nextLine(); // empty line
            list.add(b); 
        }
        
        return list;
    }

    //this method print all the books in the library
    public static void print(){
        for (Book b : list) {
            System.out.println(b);
        }
        
    }
    
    //add a book to the library
    public static Book addBook(){
        Scanner sc = new Scanner(System.in);
        int bookNumber = list.get(list.size()-1).getNumber()+1;
        System.out.println("Digite o nome do livro");
        String bookName = sc.nextLine();
        System.out.println("Digite o nome do autor");
        String writer = sc.nextLine();
        System.out.println("Digite o ano do livro");
        int year = sc.nextInt();
        String borrowedTo = "Ninguem";
        BookStatus status = BookStatus.Available;
        Book b = new Book(bookNumber,bookName,writer,year,status,borrowedTo);
       
        System.out.println("Livro Adicionado Com Sucesso!");
        return b;
    }
    
    //show all available books in the library
    public static void printAvailableBooks(){
        for (Book book : list) {
            if(book.getStatus().equals(BookStatus.Available)){
                System.out.println(book);
            }
        }
    }

    //method called at the end of the program, it saves all the books in the database
    public static void save()throws FileNotFoundException{
        PrintWriter out = new PrintWriter("database.txt");
        out.println(list.size());
        for (Book book : list) { 
            out.println("Numero : "+book.getNumber());
            out.println("Titulo : "+book.getBookName());
            out.println("Autor : "+book.getWriter());
            out.println("Ano : "+book.getYear());
            
            if(book.getStatus().equals(BookStatus.Available)){
                out.println("Status : Disponivel");
            }else{
                out.println("Status : Indisponivel");
            }
            out.println("Emprestado para : "+book.getBorrowedTo());
            out.println("");
        }
        
        out.close();
    }

    //print library options
    public static int printOps(){
        Scanner in = new Scanner (System.in);
        System.out.println("Para sair digite 0");
        System.out.println("Para retirar um livro digite 1");
        if(!libraryCheck()){
            System.out.println("Para devolver um livro digite 2");
        }       
        System.out.println("Para doar um livro digite 3");
        System.out.println("Para ver todos os livros 4");
        int key = in.nextInt();
        return key;
    }

    //check if all the books are in the library
    public static boolean libraryCheck(){
        for (Book b : list) {
            if(b.getStatus().equals(BookStatus.Unavailable)){
                return false;
            }           
        }
        return true;
    }

    //method dedicated to rent the book in the library
    public static boolean rentBook(int i){
        for (Book b : list) {
            if(b.getNumber() == i && b.getStatus().equals(BookStatus.Available)){
                b.setStatus(BookStatus.Unavailable);
                return true;
            }
            return false; 
        }
        return false;
    }
    
    //method dedicated to return the book to the library
    public static boolean returnBook(int i){
        for (Book b : list) {
            if(b.getNumber() == i && b.getStatus().equals(BookStatus.Unavailable)){
                b.setStatus(BookStatus.Available);
                return true;
            }
            return false; 
        }
        return false;
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        try{
            list = read();  
        }catch(Exception e){
            System.out.println("Erro ao carregar livros "+ e);
        }
       boolean sair = false;
        print();  
        while(!sair){
            switch(printOps()){
                case 0:
                    System.out.println("Obrigado por utilizar a biblioteca local\n");
                    try {
                        save();
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar livros "+e);
                    }
                    sair = true;
                break;
                case 1:
                    System.out.println("Digite o numero do livro que deseja retirar");
                    int n = in.nextInt();
                    if(rentBook(n)){
                        System.out.println("Livro retirado com sucesso\n");                   
                    }else{
                        System.out.println("Erro na retirada do livro, por favor selecione um livro que esteja disponivel\n");
                    }
                break;
                case 2:
                    printAvailableBooks();
                    System.out.println("Digite o numero do livro que deseja devolver");
                    int j = in.nextInt();
                    if(returnBook(j)){
                        System.out.println("Livro devolvido com sucesso");                   
                    }else{
                        System.out.println("Erro na devolucao do livro, por favor selecione um livro que esteja indisponivel");
                    }
                break;
                case 3:
                    Book b = addBook();
                    list.add(b);                   
                break;
                case 4:
                    print();
               
                default: 
                    System.out.println("Numero Invalido, tente novamente");
            }
            
        }
        
       // System.out.println("funcionou o read");  
       /* for (Book b : list) {
            System.out.println(b);
       }*/
    
    }
}
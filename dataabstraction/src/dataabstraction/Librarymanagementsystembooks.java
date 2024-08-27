package dataabstraction;

import javax.swing.JOptionPane;

public class Librarymanagementsystembooks {
    public static void main(String[] args) {
        Library library = new Library(10);

        while (true) {
            //menu
            String menu = "Library Management System\n" +
                    "1. Add a book\n" +
                    "2. Insert a book at a specified index\n" +
                    "3. Remove a book at a specified index\n" +
                    "4. Get a book at a specified index\n" +
                    "5. Get the number of books in the library\n" +
                    "6. Check if the library is empty\n" +
                    "7. Display all books\n" +
                    "8. Exit"; // hello

            String choice = JOptionPane.showInputDialog(menu);
           
            int choiceInt = Integer.parseInt(choice);
            switch (choiceInt) {
                case 1:
                    String title = JOptionPane.showInputDialog("Enter book title:");
                    String author = JOptionPane.showInputDialog("Enter book author:");
                    library.add(new Book(title, author));
                    JOptionPane.showMessageDialog(null, "Book added successfully.");
                    break;
                case 2:
                    String indexStr = JOptionPane.showInputDialog("Enter index:");
                    int index = Integer.parseInt(indexStr);
                    title = JOptionPane.showInputDialog("Enter book title:");
                    author = JOptionPane.showInputDialog("Enter book author:");
                    try {
                        library.insert(index, new Book(title, author));
                        JOptionPane.showMessageDialog(null, "Book inserted successfully at index " + index + ".");
                    } catch (IndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(null, "Invalid index! Book insertion failed.");
                    }
                    break;
                case 3:
                    indexStr = JOptionPane.showInputDialog("Enter index:");
                    index = Integer.parseInt(indexStr);
                    try {
                        library.remove(index);
                        JOptionPane.showMessageDialog(null, "Book removed successfully from index " + index + ".");
                    } catch (IndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(null, "Invalid index! Book removal failed.");
                    }
                    break;
                case 4:
                    indexStr = JOptionPane.showInputDialog("Enter index:");
                    index = Integer.parseInt(indexStr);
                    try {
                        Book book = library.get(index);
                        String bookInfo = "Book at index " + index + ":\n" +
                                "Title: " + book.getTitle() + "\n" +
                                "Author: " + book.getAuthor();
                        JOptionPane.showMessageDialog(null, bookInfo);
                    } catch (IndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(null, "Invalid index! Cannot retrieve book.");
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Number of books: " + library.size());
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Is library empty? " + library.isEmpty());
                    break;
                case 7:
                    if (library.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The library is empty.");
                    } else {
                        StringBuilder bookList = new StringBuilder();
                        for (int i = 0; i < library.size(); i++) {
                            Book b = library.get(i);
                            bookList.append(i).append(": ").append(b.getTitle()).append(" by ").append(b.getAuthor()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Books in the library:\n" + bookList.toString());
                    }
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Exiting Library Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice! Please try again.");
            }
        }
    }
}

// Define the Book class
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

// Define the Library ADT
class Library {
    private Book[] books;
    private int size;

    public Library(int initialCapacity) {
        books = new Book[initialCapacity];
        size = 0;
    }

    // Get a book at a specified index
    public Book get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return books[index];
    }

    // Get the number of books in the library
    public int size() {
        return size;
    }

    // Check if the library is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Add a book to the end of the array
    public void add(Book book) {
        if (size == books.length) {
            expandCapacity();
        }
        books[size] = book;
        size++;
    }

    // Insert a book at a specified index
    public void insert(int index, Book book) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == books.length) {
            expandCapacity();
        }
        for (int i = size; i > index; i--) {
            books[i] = books[i - 1];
        }
        books[index] = book;
        size++;
    }

    // Remove a book at a specified index
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            books[i] = books[i + 1];
        }
        books[size - 1] = null; // Clear the last book reference
        size--;
    }

    // Expand the capacity of the books array
    private void expandCapacity() {
        Book[] newBooks = new Book[books.length * 2];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        books = newBooks;
    }
}
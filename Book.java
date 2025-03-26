import java.util.*;

// Book class to represent individual book details
class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    // Constructor
    public Book(String bookId, String title, String author, String genre, String availabilityStatus) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    // Getters and setters
    public String getBookId() 
    { 
        return bookId; 
    }
    public void setBookId(String bookId)
     { 
        this.bookId = bookId; 
    }

    public String getTitle()
     { 
        return title; 
    }
    public void setTitle(String title)
     { 
        this.title = title;
     }

    public String getAuthor() 
    { 
        return author; 
    }
    public void setAuthor(String author)
     {
         this.author = author;
         }

    public String getGenre()
     {
         return genre; 
        }
    public void setGenre(String genre)
     { 
        this.genre = genre;
     }

    public String getAvailabilityStatus()
     { 
        return availabilityStatus; 
    }
    public void setAvailabilityStatus(String availabilityStatus)
     { 
        this.availabilityStatus = availabilityStatus;
     }

    @Override
    public String toString() {
        return "Book ID: " + bookId + 
               ", Title: " + title + 
               ", Author: " + author + 
               ", Genre: " + genre + 
               ", Status: " + availabilityStatus;
    }
}

// Library Management System class
class LibraryManagementSystem {
    private List<Book> bookCollection;
    private Scanner scanner;

    // Constructor
    public LibraryManagementSystem() {
        bookCollection = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a book
    public void addBook() {
        System.out.println("\n--- Add New Book ---");
        
        // Validate Book ID
        String bookId;
        while (true) {
            System.out.print("Enter Book ID: ");
            bookId = scanner.nextLine().trim();
            if (isBookIdUnique(bookId)) {
                break;
            }
            System.out.println("Error: Book ID must be unique.");
        }

        // Validate Title
        String title;
        while (true) {
            System.out.print("Enter Title: ");
            title = scanner.nextLine().trim();
            if (!title.isEmpty()) {
                break;
            }
            System.out.println("Error: Title cannot be empty.");
        }

        // Validate Author
        String author;
        while (true) {
            System.out.print("Enter Author: ");
            author = scanner.nextLine().trim();
            if (!author.isEmpty()) {
                break;
            }
            System.out.println("Error: Author cannot be empty.");
        }

        // Get Genre
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();

        // Validate Availability Status
        String availabilityStatus;
        while (true) {
            System.out.print("Enter Availability Status (Available/Checked Out): ");
            availabilityStatus = scanner.nextLine().trim();
            if (availabilityStatus.equalsIgnoreCase("Available") || 
                availabilityStatus.equalsIgnoreCase("Checked Out")) {
                break;
            }
            System.out.println("Error: Status must be 'Available' or 'Checked Out'.");
        }

        // Create and add book
        Book newBook = new Book(bookId, title, author, genre, availabilityStatus);
        bookCollection.add(newBook);
        System.out.println("Book added successfully!");
    }

    // Method to check if Book ID is unique
    private boolean isBookIdUnique(String bookId) {
        return bookCollection.stream()
                .noneMatch(book -> book.getBookId().equals(bookId));
    }

    // Method to view all books
    public void viewAllBooks() {
        System.out.println("\n--- All Books ---");
        if (bookCollection.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        
        for (Book book : bookCollection) {
            System.out.println(book);
        }
    }

    // Method to search book by ID or Title
    public void searchBook() {
        System.out.println("\n--- Search Book ---");
        System.out.print("Enter Book ID or Title: ");
        String searchTerm = scanner.nextLine().trim();

        List<Book> foundBooks = bookCollection.stream()
            .filter(book -> 
                book.getBookId().equalsIgnoreCase(searchTerm) || 
                book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
            .toList();

        if (foundBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Found Books:");
            foundBooks.forEach(System.out::println);
        }
    }

    // Method to update book details
    public void updateBookDetails() {
        System.out.println("\n--- Update Book Details ---");
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine().trim();

        Book bookToUpdate = bookCollection.stream()
            .filter(book -> book.getBookId().equals(bookId))
            .findFirst()
            .orElse(null);

        if (bookToUpdate == null) {
            System.out.println("Book not found.");
            return;
        }

        while (true) {
            System.out.println("\nSelect what to update:");
            System.out.println("1. Title");
            System.out.println("2. Author");
            System.out.println("3. Genre");
            System.out.println("4. Availability Status");
            System.out.println("5. Exit Update");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter new Title: ");
                    String newTitle = scanner.nextLine().trim();
                    if (!newTitle.isEmpty()) {
                        bookToUpdate.setTitle(newTitle);
                        System.out.println("Title updated successfully.");
                    } else {
                        System.out.println("Title cannot be empty.");
                    }
                    break;
                case "2":
                    System.out.print("Enter new Author: ");
                    String newAuthor = scanner.nextLine().trim();
                    if (!newAuthor.isEmpty()) {
                        bookToUpdate.setAuthor(newAuthor);
                        System.out.println("Author updated successfully.");
                    } else {
                        System.out.println("Author cannot be empty.");
                    }
                    break;
                case "3":
                    System.out.print("Enter new Genre: ");
                    String newGenre = scanner.nextLine().trim();
                    bookToUpdate.setGenre(newGenre);
                    System.out.println("Genre updated successfully.");
                    break;
                case "4":
                    System.out.print("Enter new Availability Status (Available/Checked Out): ");
                    String newStatus = scanner.nextLine().trim();
                    if (newStatus.equalsIgnoreCase("Available") || 
                        newStatus.equalsIgnoreCase("Checked Out")) {
                        bookToUpdate.setAvailabilityStatus(newStatus);
                        System.out.println("Availability Status updated successfully.");
                    } else {
                        System.out.println("Invalid status. Must be 'Available' or 'Checked Out'.");
                    }
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to delete a book record
    public void deleteBookRecord() {
        System.out.println("\n--- Delete Book Record ---");
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine().trim();

        boolean removed = bookCollection.removeIf(book -> book.getBookId().equals(bookId));

        if (removed) {
            System.out.println("Book record deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Main menu method
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Digital Library Book Management System ---");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete Book Record");
            System.out.println("6. Exit System");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    viewAllBooks();
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    updateBookDetails();
                    break;
                case "5":
                    deleteBookRecord();
                    break;
                case "6":
                    System.out.println("Exiting Library Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        librarySystem.displayMenu();
    }
}
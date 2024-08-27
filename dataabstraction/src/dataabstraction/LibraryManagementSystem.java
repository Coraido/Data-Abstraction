package dataabstraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem {
    private Library library;
    private JFrame frame;
    private JPanel panel;
    private JButton addButton;
    private JButton insertButton;
    private JButton removeButton;
    private JButton getButton;
    private JButton sizeButton;
    private JButton isEmptyButton;
    private JButton displayButton;
    private JButton exitButton;

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }

    public LibraryManagementSystem() {
        library = new Library(10);
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        addButton = new JButton("Add a book");
        addButton.addActionListener(new AddButtonListener());
        panel.add(addButton);

        insertButton = new JButton("Insert a book at a specified index");
        insertButton.addActionListener(new InsertButtonListener());
        panel.add(insertButton);

        removeButton = new JButton("Remove a book at a specified index");
        removeButton.addActionListener(new RemoveButtonListener());
        panel.add(removeButton);

        getButton = new JButton("Get a book at a specified index");
        getButton.addActionListener(new GetButtonListener());
        panel.add(getButton);

        sizeButton = new JButton("Get the number of books in the library");
        sizeButton.addActionListener(new SizeButtonListener());
        panel.add(sizeButton);

        isEmptyButton = new JButton("Check if the library is empty");
        isEmptyButton.addActionListener(new IsEmptyButtonListener());
        panel.add(isEmptyButton);

        displayButton = new JButton("Display all books");
        displayButton.addActionListener(new DisplayButtonListener());
        panel.add(displayButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = JOptionPane.showInputDialog("Enter book title:");
            String author = JOptionPane.showInputDialog("Enter book author:");
            library.add(new Book(title, author));
            JOptionPane.showMessageDialog(null, "Book added successfully.");
        }
    }

    private class InsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String indexStr = JOptionPane.showInputDialog("Enter index:");
            int index = Integer.parseInt(indexStr);
            String title = JOptionPane.showInputDialog("Enter book title:");
            String author = JOptionPane.showInputDialog("Enter book author:");
            try {
                library.insert(index, new Book(title, author));
                JOptionPane.showMessageDialog(null, "Book inserted successfully at index " + index + ".");
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Invalid index! Book insertion failed.");
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String indexStr = JOptionPane.showInputDialog("Enter index:");
            int index = Integer.parseInt(indexStr);
            try {
                library.remove(index);
                JOptionPane.showMessageDialog(null, "Book removed successfully from index " + index + ".");
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Invalid index! Book removal failed.");
            }
        }
    }

    private class GetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String indexStr = JOptionPane.showInputDialog("Enter index:");
            int index = Integer.parseInt(indexStr);
            try {
                Book book = library.get(index);
                String bookInfo = "Book at index " + index + ":\n" +
                        "Title: " + book.getTitle() + "\n" +
                        "Author: " + book.getAuthor();
                JOptionPane.showMessageDialog(null, bookInfo);
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Invalid index! Cannot retrieve book.");
            }
        }
    }

    private class SizeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Number of books: " + library.size());
        }
    }

    private class IsEmptyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Is library empty? " + library.isEmpty());
        }
    }

    private class DisplayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (library.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The library is empty.");
            } else {
                StringBuilder bookList = new StringBuilder();
                for (int i = 0; i < library.size(); i++) {
                    bookList.append(i).append(": ").append(library.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(null, "Books in the library:\n" + bookList.toString());
            }
        }
    }

    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
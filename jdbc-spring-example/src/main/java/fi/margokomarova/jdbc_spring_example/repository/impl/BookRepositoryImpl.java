package fi.margokomarova.jdbc_spring_example.repository.impl;

import fi.margokomarova.jdbc_spring_example.model.Book;
import fi.margokomarova.jdbc_spring_example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private DataSource dataSource;

    public BookRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> result = new ArrayList<>();
        String SQL_findAllBooks = "SELECT * from books;";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_findAllBooks)) {
            while (resultSet.next()) {
                Book book = converRowToBook(resultSet);
                result.add(book);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    private Book converRowToBook(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Book(id, name);
    }

    @Override
    public Book findBookById(Long id) {
        Book result = new Book();
        String SQL_findBookById = "SELECT * from books WHERE id=" + id;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_findBookById)) {
            while (resultSet.next()) {
                result = converRowToBook(resultSet);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }
}

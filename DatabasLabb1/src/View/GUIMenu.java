package View;

import Controller.LowerPane;
import Controller.RightPane;
import Model.Book;
import Model.CollectionOfBooks;
import Model.Author;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Controller.TopPane;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author John,Ahmed
 */
public class GUIMenu extends Application {

    private TableView<Book> table;
    private ArrayList<Author> authors = new ArrayList<>();
    private BorderPane layout;
    private CollectionOfBooks COBooks = new CollectionOfBooks();
    private LowerPane lowerPane = new LowerPane();
    private RightPane rightPane = new RightPane();
    private TopPane topPane = new TopPane();
    private User user = new User();

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        String database = "library";
        //String server = "jdbc:mysql://localhost:3306/" + database + "?UseClientEnc=UTF8";
        String server = "jdbc:mysql://192.168.0.11:3306/" + database + "?UseClientEnc=UTF8";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "john";
            con = DriverManager.getConnection(server, "John", "john");
            System.out.println("Connected!");

        } catch (Exception e) {
            System.out.println("Database error, " + e.toString());
        }

        //title
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(15);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        //genre
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(15);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        //Price
        TableColumn<Book, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(15);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //isbn
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setMinWidth(15);
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        //edition
        TableColumn<Book, Integer> editionColumn = new TableColumn<>("Edition");
        editionColumn.setMinWidth(15);
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));

        table = new TableView<>();

        table.getColumns().addAll(titleColumn, genreColumn, priceColumn, isbnColumn);

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    System.out.println(table.getSelectionModel().getSelectedItem());
                    //GÖR NYTT FÖNSTER SOM PRINTAR UT ALLA AUTHORS
                }
            }
            
        });

        // putting on top
        table.setPlaceholder(new Label("No books found"));
        layout = new BorderPane();

        layout.setTop(topPane.initTopPane(primaryStage, table, COBooks));

        table.setMaxWidth(400);

        layout.setCenter(table);
        layout.setRight(rightPane.initRightPane(table, COBooks, user, con));
        layout.setBottom(lowerPane.initLowerPane(table, COBooks, user, con));

        Scene scene = new Scene(layout, 750, 800);

        primaryStage.setTitle("Book Handler 2000!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

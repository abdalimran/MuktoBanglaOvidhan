package dictionary.muktobangla.controller;


import dictionary.muktobangla.model.DatabaseManager;
import dictionary.muktobangla.model.OvidhanMeaning;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {

    DatabaseManager db=new DatabaseManager();
    ObservableList<OvidhanMeaning> MeaningList;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView searchIcon;

    @FXML
    private ImageView aboutIcon;

    @FXML
    private TableView<OvidhanMeaning> ovidhanTable;

    @FXML
    private TableColumn<OvidhanMeaning, String> englishCol;

    @FXML
    private TableColumn<OvidhanMeaning, String> banglaCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadResources();

        db.Connect("jdbc:sqlite::resource:ovidhandb/muktobangladb.sqlite3");
//        db.Connect("jdbc:sqlite::resource:ovidhandb/Ankur_DB.db");
        ResultSet rs = db.GetResult("select en_word,bn_word from words");
        MeaningList=FXCollections.observableArrayList();
        try {
            while(rs.next())
            {
                String enword = rs.getString("en_word");
                String bnword = rs.getString("bn_word");

                MeaningList.add(new OvidhanMeaning(enword,bnword));

                englishCol.setCellValueFactory(new PropertyValueFactory<OvidhanMeaning, String>("enword"));
                banglaCol.setCellValueFactory(new PropertyValueFactory<OvidhanMeaning, String>("bnword"));
                ovidhanTable.setItems(MeaningList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(MeaningList.size());

        //Filtering
        FilteredList<OvidhanMeaning> filteredData = new FilteredList<>(MeaningList, s -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(meaning -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (meaning.getenword().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (meaning.getbnword().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<OvidhanMeaning> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ovidhanTable.comparatorProperty());
        ovidhanTable.setItems(sortedData);
    }

    private void loadResources()
    {
        Image search = new Image(getClass().getResource("/images/search.png").toString(), true);
        Image about = new Image(getClass().getResource("/images/about.png").toString(), true);

        Font.loadFont(getClass().getResourceAsStream("/fonts/Siyamrupali.ttf"), 14.0);
        Font.loadFont(getClass().getResourceAsStream("/fonts/TimesNewRoman.ttf"), 18.0);

        searchIcon.setImage(search);
        aboutIcon.setImage(about);
    }

    public void aboutAction(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Mukto Bangla Ovidhan © MIT License");
        alert.setContentText("This project is opensource and " +
                            "\nbuild under MIT License." +
                            "\nDevelopped by Abdullah Al Imran"+
                            "\nFacebook: fb.com/abdalimran");
        alert.showAndWait();
    }
}
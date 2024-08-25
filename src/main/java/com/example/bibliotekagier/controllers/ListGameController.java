package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
import com.example.bibliotekagier.database.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListGameController implements Initializable {

    private Database database;
    private Parent root;
    private List<Profil> profile;
    private List<Posiadane> posiadane;
    private List<ListGame> listGames;
    private SteamAPI steamAPI;

    private int indexListViewProfile;
    @javafx.fxml.FXML
    private TableColumn tableColumnSteam;
    @javafx.fxml.FXML
    private TableColumn tableColumnXbox;
    @javafx.fxml.FXML
    private TableColumn tableColumnPhysical;
    @javafx.fxml.FXML
    private TableColumn tableColumnGOG;
    @javafx.fxml.FXML
    private TableColumn tableColumnPlayStation;
    @javafx.fxml.FXML
    private TableColumn tableColumnTitle;
    @javafx.fxml.FXML
    private TableColumn tableColumnEpic;
    @javafx.fxml.FXML
    private TableColumn tableColumnStatus;
    @javafx.fxml.FXML
    private TableView tableColumn;

    public ListGameController(Database database, List<Profil> profile, int index) {
        this.database = database;
        this.profile = profile;
        indexListViewProfile = index;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        posiadane = database.getPosiadane((long) indexListViewProfile+1);

        /*
        String title = posiadane.get(0).getId_gry().getTytul_gry();
        String platforma = posiadane.get(0).getId_platformy().getNazwa_platformy();

        //tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("id_gry"));
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("id_gry"));

        tableColumnTitle.setCellFactory(column -> {
            return new TableCell<Posiadane, Gry>() {
                @Override
                protected void updateItem(Gry item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getTytul_gry()); // Załóżmy, że Title ma metodę getNazwa()
                    }
                }
            };
        });

        //tableColumnTitle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId_gry().getTytul_gry()));

        tableColumn.setItems(FXCollections.observableArrayList(posiadane));
        */
        setupListGame();
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tableColumnGOG.setCellValueFactory(new PropertyValueFactory<>("GOG"));
        tableColumnEpic.setCellValueFactory(new PropertyValueFactory<>("Epic"));
        tableColumnPlayStation.setCellValueFactory(new PropertyValueFactory<>("PlayStation"));
        tableColumnXbox.setCellValueFactory(new PropertyValueFactory<>("Xbox"));
        tableColumnPhysical.setCellValueFactory(new PropertyValueFactory<>("FizycznaKopia"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tableColumn.setItems(FXCollections.observableArrayList(listGames));

    }

    private void setupListGame(){
        listGames = new ArrayList<ListGame>();
        Long before = (long) -1;
        ListGame game = new ListGame();
        boolean checkEqual;
        for (Posiadane p : posiadane){
            if (p.getId_gry().getId_gry() != before){
                game = new ListGame();
                checkEqual = false;
            }
            else {
                checkEqual = true;
            }
            String platforma = p.getId_platformy().getNazwa_platformy();
            before = p.getId_gry().getId_gry();

            switch (platforma){
                case "Steam":
                    game.setSteam("Tak");
                    break;
                case "GOG":
                    game.setGOG("Tak");
                    break;
                case "Epic":
                    game.setEpic("Tak");
                    break;
                case "PlayStation":
                    game.setPlayStation("Tak");
                    break;
                case "Xbox":
                    game.setXbox("Tak");
                    break;
                case "Fizyczna_kopia":
                    game.setFizycznaKopia("Tak");
                    break;
            }

            game.setTitle(p.getId_gry().getTytul_gry());
            game.setStatus(p.getStatus());

            if (checkEqual){
                listGames.removeLast();
            }
            listGames.add(game);
        }
    }


}
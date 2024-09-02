package com.example.bibliotekagier.controllers;

import com.example.bibliotekagier.SteamAPI;
import com.example.bibliotekagier.database.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class RateController implements Initializable {

    private Database database;
    private Parent root;

    private int indexListViewProfile;
    @FXML
    private TextField textFieldFindTitle;
    @FXML
    private ListView listViewListaTitle;
    @FXML
    private Spinner spinnerGrades;
    @FXML
    private TextArea textAreaFindComent;
    @FXML
    private Label labelResult;
    @FXML
    private Pane panePopUp;
    @FXML
    private Button nie;
    @FXML
    private Button tak;
    @FXML
    private TableColumn tableColumnAvgRate;
    @FXML
    private TableColumn tableColumnRate;
    @FXML
    private TextField textFieldFindRate;
    @FXML
    private TableColumn tableColumnComment;
    @FXML
    private TableColumn tableColumnTitle;
    @FXML
    private TableView tableColumn;

    public RateController(Database database, int indexListViewProfile ) {
        this.database = database;
        this.indexListViewProfile = indexListViewProfile;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableView();
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spinnerGrades.setValueFactory(valueFactory);
        textAreaFindComent.setWrapText(true);
        textFieldFindTitleAction(null);
        panePopUp.setVisible(false);

    }

    @FXML
    public void textFieldFindTitleAction(Event event) {
        String title = textFieldFindTitle.getText().toUpperCase();

        List<Posiadane> query = database.getPosiadane(Long.valueOf(indexListViewProfile+1), title);

        List<Gry> listaGry= query.stream().map(Posiadane::getId_gry).collect(Collectors.toList());

        Set<String> setTitle = new HashSet<>();

        String temp;

        for (Gry g : listaGry) {
            temp = g.getTytul_gry();
            setTitle.add(temp);
        }
        List<String> listaTitle = new ArrayList<>(setTitle);

        ObservableList<String> observablePlatformName = FXCollections.observableList(listaTitle);
        listViewListaTitle.setItems(observablePlatformName);
    }

    @FXML
    public void buttonAdd(ActionEvent actionEvent) {
        String title = "";
        ObservableList temp = listViewListaTitle.getSelectionModel().getSelectedItems();
        if (!temp.isEmpty()) {
            title = temp.get(0).toString();
        }

        int ocena = (int) spinnerGrades.getValue();
        String komentarz = textAreaFindComent.getText();

        if (!title.isEmpty()){

            try {
                database.addOcenyGier(title, ocena, komentarz, (long) (indexListViewProfile+1));
            }
            catch (IllegalArgumentException e){
                //labelResult.setText(e.getMessage());
                panePopUp.setVisible(true);
                return;
            }
            labelResult.setText("Pomyśnie dodano");

            Runnable opoznienie = () -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> labelResult.setText(""));
            };
            new Thread(opoznienie).start();

            setupTableView();

        }
    }

    @FXML
    public void changeChoice(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();

        String title = "";
        ObservableList temp = listViewListaTitle.getSelectionModel().getSelectedItems();
        if (!temp.isEmpty()) {
            title = temp.get(0).toString();
        }

        int ocena = (int) spinnerGrades.getValue();
        String komentarz = textAreaFindComent.getText();

        if (buttonId.equals("tak")){
            database.updateOcenyGier(title, ocena, komentarz, (long) (indexListViewProfile+1));
            labelResult.setText("Pomyśnie dodano");

            Runnable opoznienie = () -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> labelResult.setText(""));
            };
            setupTableView();
            new Thread(opoznienie).start();

        }
        panePopUp.setVisible(false);


    }

    @FXML
    public void textFieldFindRateAction(Event event) {
    }

    private void setupTableView(){

        List<OcenyGierView> ocenyGierViews = setupOcenyGierView();
        tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tableColumnAvgRate.setCellValueFactory(new PropertyValueFactory<>("SrOcena"));
        tableColumnRate.setCellValueFactory(new PropertyValueFactory<>("Ocena"));
        tableColumnComment.setCellValueFactory(new PropertyValueFactory<>("Komentarz"));
        tableColumn.setItems(FXCollections.observableArrayList(ocenyGierViews));
    }

    private List<OcenyGierView> setupOcenyGierView(){
        List<OcenyGier> ocenyGierList = database.getOcenyGier((long) (indexListViewProfile+1));
        List<OcenyGierView> ocenyGierViewsList = new ArrayList<>();



        for (OcenyGier oceny : ocenyGierList){
            OcenyGierView ocenyGierView = new OcenyGierView();
            double avgRate = database.getAvgOcenyGier(oceny.getId_gry().getId_gry());

            ocenyGierView.setTitle(oceny.getId_gry().getTytul_gry());
            ocenyGierView.setSrOcena(avgRate);
            ocenyGierView.setOcena(oceny.getOcena_uzytkownika());
            ocenyGierView.setKomentarz(oceny.getKomentarz());

            ocenyGierViewsList.add(ocenyGierView);
        }

        return ocenyGierViewsList;
    }
}

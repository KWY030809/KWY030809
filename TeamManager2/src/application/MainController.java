package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class MainController {

    @FXML
    private TextField teamTextField;

    @FXML
    private Button deleteButton;
    @FXML
    private Button increaseWinButton;
    @FXML
    private Button decreaseWinButton;
    @FXML
    private Button increaseDrawButton;
    @FXML
    private Button decreaseDrawButton;
    @FXML
    private Button increaseLoseButton;
    @FXML
    private Button decreaseLoseButton;
    @FXML
    private Button increasePointsButton;
    @FXML
    private Button decreasePointsButton;

    @FXML
    private Button increaseGoalsScoredButton;
    @FXML
    private Button decreaseGoalsScoredButton;
    @FXML
    private Button increaseGoalsConcededButton;
    @FXML
    private Button decreaseGoalsConcededButton;

    @FXML
    private TableView<Team> teamTableView;

    @FXML
    private TableColumn<Team, String> teamColumn;
    @FXML
    private TableColumn<Team, Integer> winColumn;
    @FXML
    private TableColumn<Team, Integer> drawColumn;
    @FXML
    private TableColumn<Team, Integer> loseColumn;
    @FXML
    private TableColumn<Team, Integer> pointColumn;
    @FXML
    private TableColumn<Team, Integer> goalsScoredColumn;
    @FXML
    private TableColumn<Team, Integer> goalsConcededColumn;
    @FXML
    private TableColumn<Team, Integer> goalDifferenceColumn;

    private ObservableList<Team> teamList;

    @FXML
    private TextField playerNameTextField;

    @FXML
    private TableView<Player> playerTableView;

    @FXML
    private TableColumn<Player, String> playerNameColumn;

    @FXML
    private TableColumn<Player, Integer> goalsColumn;

    @FXML
    private TableColumn<Player, Integer> assistsColumn;

    @FXML
    private Label topScorerLabel;

    @FXML
    private Label topAssistProviderLabel;

    private ObservableList<Player> playerList;

    @FXML
    private TextField goalsTextField;

    @FXML
    private TextField assistsTextField;

    @FXML
    public void initialize() {
        List<Team> loadedTeams = DataManager.loadData();
        teamList = FXCollections.observableArrayList(loadedTeams);
        teamTableView.setItems(teamList);

        teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamNameProperty());
        winColumn.setCellValueFactory(cellData -> cellData.getValue().winsProperty().asObject());
        drawColumn.setCellValueFactory(cellData -> cellData.getValue().drawsProperty().asObject());
        loseColumn.setCellValueFactory(cellData -> cellData.getValue().lossesProperty().asObject());
        pointColumn.setCellValueFactory(cellData -> cellData.getValue().pointsProperty().asObject());
        goalsScoredColumn.setCellValueFactory(cellData -> cellData.getValue().goalsScoredProperty().asObject());
        goalsConcededColumn.setCellValueFactory(cellData -> cellData.getValue().goalsConcededProperty().asObject());
        goalDifferenceColumn.setCellValueFactory(cellData -> cellData.getValue().goalDifferenceProperty().asObject());

        teamTableView.setItems(teamList);

        playerList = FXCollections.observableArrayList();
        playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        goalsColumn.setCellValueFactory(cellData -> cellData.getValue().goalsProperty().asObject());
        assistsColumn.setCellValueFactory(cellData -> cellData.getValue().assistsProperty().asObject());

        playerTableView.setItems(playerList);
    }

    @FXML
    void handleAddTeam() {
        String teamName = teamTextField.getText().trim();
        if (!teamName.isEmpty()) {
            Team team = new Team(teamName);
            teamList.add(team);
            teamTextField.clear();
            saveData();
        }
    }

    @FXML
    void handleDraw() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setDraws(selectedTeam.getDraws() + 1);
            calculatePoints(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleLose() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setLosses(selectedTeam.getLosses() + 1);
            calculatePoints(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleWin() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setWins(selectedTeam.getWins() + 1);
            calculatePoints(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleDeleteTeam() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            teamList.remove(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleIncreaseWin() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setWins(selectedTeam.getWins() + 1);
            calculatePoints(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleDecreaseWin() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            int currentWins = selectedTeam.getWins();
            if (currentWins > 0) {
                selectedTeam.setWins(currentWins - 1);
                calculatePoints(selectedTeam);
                saveData();
            }
        }
    }

    @FXML
    void handleIncreaseDraw() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setDraws(selectedTeam.getDraws() + 1);
            calculatePoints(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleDecreaseDraw() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            int currentDraws = selectedTeam.getDraws();
            if (currentDraws > 0) {
                selectedTeam.setDraws(currentDraws - 1);
                calculatePoints(selectedTeam);
                saveData();
            }
        }
    }

    @FXML
    void handleIncreaseLose() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setLosses(selectedTeam.getLosses() + 1);
            calculatePoints(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleDecreaseLose() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            int currentLosses = selectedTeam.getLosses();
            if (currentLosses > 0) {
                selectedTeam.setLosses(currentLosses - 1);
                calculatePoints(selectedTeam);
                saveData();
            }
        }
    }

    @FXML
    void handleIncreasePoints() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setPoints(selectedTeam.getPoints() + 1);
            saveData();
        }
    }

    @FXML
    void handleDecreasePoints() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            int currentPoints = selectedTeam.getPoints();
            if (currentPoints > 0) {
                selectedTeam.setPoints(currentPoints - 1);
                saveData();
            }
        }
    }

    @FXML
    void handleIncreaseGoalsScored() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setGoalsScored(selectedTeam.getGoalsScored() + 1);
            calculateGoalDifference(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleDecreaseGoalsScored() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null && selectedTeam.getGoalsScored() > 0) {
            selectedTeam.setGoalsScored(selectedTeam.getGoalsScored() - 1);
            calculateGoalDifference(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleIncreaseGoalsConceded() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            selectedTeam.setGoalsConceded(selectedTeam.getGoalsConceded() + 1);
            calculateGoalDifference(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleDecreaseGoalsConceded() {
        Team selectedTeam = teamTableView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null && selectedTeam.getGoalsConceded() > 0) {
            selectedTeam.setGoalsConceded(selectedTeam.getGoalsConceded() - 1);
            calculateGoalDifference(selectedTeam);
            saveData();
        }
    }

    @FXML
    void handleAddPlayer() {
        String playerName = playerNameTextField.getText().trim();
        String goalsText = goalsTextField.getText().trim();
        String assistsText = assistsTextField.getText().trim();

        if (!playerName.isEmpty() && isNumeric(goalsText) && isNumeric(assistsText)) {
            int goals = Integer.parseInt(goalsText);
            int assists = Integer.parseInt(assistsText);
            Player player = new Player(playerName, goals, assists);
            playerList.add(player);
            playerNameTextField.clear();
            goalsTextField.clear();
            assistsTextField.clear();
            updateTopScorerAndAssistProvider();
            saveData();
        } else {
            // Handle invalid input (goalsText or assistsText are not numeric or empty)
        }
    }

    @FXML
    void handleDeletePlayer() {
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            playerList.remove(selectedPlayer);
            updateTopScorerAndAssistProvider();
            saveData();
        }
    }

    @FXML
    void handleIncreasePlayerGoals() {
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            selectedPlayer.setGoals(selectedPlayer.getGoals() + 1);
            updateTopScorerAndAssistProvider();
            saveData();
        }
    }

    @FXML
    void handleDecreasePlayerGoals() {
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null && selectedPlayer.getGoals() > 0) {
            selectedPlayer.setGoals(selectedPlayer.getGoals() - 1);
            updateTopScorerAndAssistProvider();
            saveData();
        }
    }

    @FXML
    void handleIncreasePlayerAssists() {
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            selectedPlayer.setAssists(selectedPlayer.getAssists() + 1);
            updateTopScorerAndAssistProvider();
            saveData();
        }
    }

    @FXML
    void handleDecreasePlayerAssists() {
        Player selectedPlayer = playerTableView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null && selectedPlayer.getAssists() > 0) {
            selectedPlayer.setAssists(selectedPlayer.getAssists() - 1);
            updateTopScorerAndAssistProvider();
            saveData();
        }
    }

    private void updateTopScorerAndAssistProvider() {
        Player topScorer = playerList.stream().max((p1, p2) -> Integer.compare(p1.getGoals(), p2.getGoals()))
                .orElse(null);
        Player topAssistProvider = playerList.stream()
                .max((p1, p2) -> Integer.compare(p1.getAssists(), p2.getAssists())).orElse(null);

        topScorerLabel.setText(topScorer != null ? topScorer.getName() : "N/A");
        topAssistProviderLabel.setText(topAssistProvider != null ? topAssistProvider.getName() : "N/A");
    }

    private void saveData() {
        DataManager.saveData(teamList, playerList);
    }

    public void shutdown() {
        saveData();
    }

    private void calculateGoalDifference(Team team) {
        team.setGoalDifference(team.getGoalsScored() - team.getGoalsConceded());
    }

    private void calculatePoints(Team team) {
        team.setPoints(team.getWins() * 3 + team.getDraws());
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

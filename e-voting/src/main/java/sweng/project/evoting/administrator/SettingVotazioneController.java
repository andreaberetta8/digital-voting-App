package sweng.project.evoting.administrator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sweng.project.evoting.votazione.VotazioneCategorica;

public class SettingVotazioneController {
	private String id;
	private VotazioneCategorica v;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteVote;

    @FXML
    private Button goSummaryButton;

    @FXML
    private Button insertAnother;

    @FXML
    private AnchorPane pane;
    
    public void setId(final String id) {
    	this.id = id;
    }
    
    public void setVotazione(final VotazioneCategorica v) {
    	this.v = v;
    }

    @FXML
    void handleDeleteVote(ActionEvent event) throws IOException {
    	v.deleteVotazione();
    	AnchorPane next = FXMLLoader.load(getClass().getResource("..//administrator//voteDeletedWindow.fxml"));
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(next);
    }

    @FXML
    void handleGoSummary(ActionEvent event) {

    }

    @FXML
    void handleInsertAnother(ActionEvent event) throws IOException {
    	FXMLLoader next = new FXMLLoader(getClass().getResource("..//administrator//inserimentoCandidatiWindow.fxml"));
    	Parent root = next.load();
    	InserimentoCandidatiController icc = next.getController();
    	icc.setId(id);
    	icc.setVotazione(v);
    	pane.getChildren().removeAll();
    	pane.getChildren().setAll(root);
    }

    @FXML
    void initialize() {
        assert deleteVote != null : "fx:id=\"deleteVote\" was not injected: check your FXML file 'settingVotazioneWindow.fxml'.";
        assert goSummaryButton != null : "fx:id=\"goSummaryButton\" was not injected: check your FXML file 'settingVotazioneWindow.fxml'.";
        assert insertAnother != null : "fx:id=\"insertAnother\" was not injected: check your FXML file 'settingVotazioneWindow.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'settingVotazioneWindow.fxml'.";

    }

}

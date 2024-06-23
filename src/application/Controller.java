package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Controller implements Initializable{

	@FXML 
	private MediaView mediaView;
	@FXML
	private Button pauseButton,playButton,resetButton;
	@FXML
	private Slider volumeSlider;
	@FXML
    private Slider progressSlider;

	private File file;
	private Media media;
	private MediaPlayer mediaplayer;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		file=new File("C:\\Users\\Saxen\\Downloads\\video.mp4");
		media= new Media(file.toURI().toString());
		mediaplayer=new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaplayer);
		
		// Update the slider as the media plays
        mediaplayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double progress = newValue.toSeconds() / media.getDuration().toSeconds();
            progressSlider.setValue(progress);
        });

        // Handle slider value changes
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (progressSlider.isValueChanging()) {
                double newTime = newValue.doubleValue() * media.getDuration().toSeconds();
                mediaplayer.seek(Duration.seconds(newTime));
            }
        });
		
	}
	
	public void playMedia()
	{
		mediaplayer.play();
	}
	public void pauseMedia()
	{
		mediaplayer.pause();
	}
	
	public void resetMedia()
	{
		
		mediaplayer.seek(Duration.seconds(0.0));
	}
	@FXML
	private void onVolumeSliderChanged() {
	   double volume = volumeSlider.getValue();
	    mediaplayer.setVolume(volume);
	}

}

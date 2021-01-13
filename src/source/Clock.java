package source;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Clock implements Runnable{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "MM/dd/yyyy 'at' hh:mm:ss a" );
    private Label clockLabel;

    public Clock(Label clockLabel) {
        this.clockLabel = clockLabel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ZonedDateTime zdt = ZonedDateTime.now();
            final String time = zdt.format(formatter);
            Platform.runLater( () -> {
                clockLabel.setText(time);
            });
        }
    }
}

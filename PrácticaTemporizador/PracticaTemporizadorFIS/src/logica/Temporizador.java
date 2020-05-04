package logica;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javax.swing.JLabel;

public class Temporizador {

    private int hour;
    private int minute;
    private int second;
    private Timer timer;
    private boolean isTimerRunning;
    private JLabel display;
 
    public Temporizador(int min,JLabel mostrar) {
        timer = new Timer();
        convertirHYM(min);
        display = mostrar;
        timer.schedule(task, 0, 1000);
    }
    
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isTimerRunning = true;
            if(second > 0) {
                second--;
            } else {
                second = 59;
                if(minute > 0) minute--;
                else {
                    minute = 59;
                    if(hour > 0) hour--;
                    // si segundo = 0, minuto = 0 y hora = 0,
                    // cancelamos el timer
                    else {
                        isTimerRunning = false;
                        timer.cancel();
                        timer.purge();
                    }
                }
            }
            if(isTimerRunning)
                display.setText(hour + " : " +  minute + " : " +  second);
        }
    };

    public void convertirHYM(int min){
        hour = (int) min / 60;
        minute = min % 60;
        second = 0;
    }
    
}

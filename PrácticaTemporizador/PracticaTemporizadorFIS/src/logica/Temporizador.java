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
    private int tiempoT;
    private int contadorT;
    private int tiempoD;
    private int contadorD;
    private Timer timer;
    private boolean isTimerRunning;
    private JLabel display;
    private JLabel estado;

    public Temporizador(int min, int TT, int TD, JLabel mostrar, JLabel est) {
        timer = new Timer();
        convertirHYM(min);
        display = mostrar;
        estado = est;
        tiempoT = TT;
        tiempoD = TD;
        contadorT = TT;
        contadorD = TD;
        timer.schedule(crearTarea(), 0, 1000);
    }

    public TimerTask crearTarea() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isTimerRunning = true;
                if (second > 0) {
                    second--;
                } else {
                    second = 59;
                    if (minute > 0) {
                        minute--;
                    } else {
                        minute = 59;
                        if (hour > 0) {
                            hour--;
                        } // si segundo = 0, minuto = 0 y hora = 0,
                        // cancelamos el timer
                        else {
                            isTimerRunning = false;
                            timer.cancel();
                            timer.purge();
                        }
                    }
                }
                if (isTimerRunning) {
                    comprobarEstado();
                    String tiempo = "";
                    if(hour < 10){
                        tiempo += "0" + hour;
                    }else{
                        tiempo += hour;
                    }
                    
                    if(minute < 10){
                        tiempo += " : 0" + minute;
                    }else{
                        tiempo += " : " + minute;
                    }
                    
                    if(second < 10){
                        tiempo += " : 0" + second;
                    }else{
                        tiempo += " : " + second;
                    }
                    
                    display.setText(tiempo);
                }
            }
        };
        
        return task;
    }

    public void convertirHYM(int min) {
        hour = (int) min / 60;
        minute = min % 60;
        second = 0;
    }

    public void pausar() {
        if (isTimerRunning) {
            isTimerRunning = false;
            timer.cancel();
            timer.purge();
        }
    }

    public void reanudar() {
        timer = new Timer();
        timer.schedule(crearTarea(), 0, 1000);
    }

    public void detener() {
        pausar();
        display.setText("00 : 00 : 00");
        estado.setForeground(new java.awt.Color(255, 0, 0));
        estado.setText("Tiempo de trabajo!!");
        hour = 0;
        minute = 0;
        second = 0;
    }

    public void comprobarEstado() {
        if (estado.getText().equals("Tiempo de trabajo!!")) {
            contadorT--;
        } else {
            contadorD--;
        }
        if (contadorT == 0) {
            estado.setForeground(new java.awt.Color(0, 255, 51));
            estado.setText("Tiempo de descanso");
            contadorT = tiempoT;
        }
        if (contadorD == 0) {
            estado.setForeground(new java.awt.Color(255, 0, 0));
            estado.setText("Tiempo de trabajo!!");
            contadorD = tiempoD;
        }
    }

    // Metodos accesores y modificadores
    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

}

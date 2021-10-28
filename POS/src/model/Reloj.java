package model;
//CLASE DONDE SE ENCUENTRA EL MODELO DE LA FECHA Y HORA
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;

public class Reloj implements Runnable{
    private final boolean mientras;
    private final JLabel tiempo, epoca;
    private final DateTimeFormatter horario, fecha;
    
    public Reloj(JLabel tiempo, JLabel epoca){
        mientras = true;
        this.tiempo = tiempo;
        this.epoca = epoca;
        horario = DateTimeFormatter.ofPattern("HH:mm:ss");
        fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public void run() {
        epoca.setText(fecha.format(LocalDateTime.now()));
        while (mientras) {
            try {
                Thread.sleep(500);
                tiempo.setText(horario.format(LocalDateTime.now()));
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            }
        }
    }
}


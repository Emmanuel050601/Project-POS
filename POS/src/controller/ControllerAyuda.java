package controller;
//CLASE CONTROLADORA DE BOTONES AYUDA

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class ControllerAyuda implements ActionListener{
    private String ex;
    
    private void internet(String boton){
        try{
            Desktop.getDesktop().browse(new URI(boton));
        }catch(Exception e){ }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        ex=e.getActionCommand();
        if(ex.equals("Descargar Manuales"))
            internet("https://drive.google.com/drive/"
                    + "folders/1tqmBrzwkOC6KTV7gGV-QYog39UAXoCrg?usp=sharing");
        else if(ex.equals("Visitar canal de Youtube"))
            internet("https://www.youtube.com/");
    }
    
}

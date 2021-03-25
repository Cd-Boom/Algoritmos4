import Arboles.*;

import javafx.application.Application;
import javafx.stage.Stage;
import Arboles.Arbol;

import javax.swing.*;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String NodoDato = "0";
        String NodoPadre = "0";
        String Condicion = "0";
        Arbol _Arbol =  new Arbol();
        do {
            NodoDato = JOptionPane.showInputDialog("Ingreso un nodo").toString();
            NodoPadre = JOptionPane.showInputDialog("Ingreso el nodo padre del nodo anterior").toString();
            _Arbol.InsertarNodo(NodoDato,NodoPadre);
            Condicion = JOptionPane.showInputDialog("Desea ingresar un nuevo nodo al arbol, si o no?").toString();
        } while (Condicion.equals("si"));
        NodoDato = JOptionPane.showInputDialog("Eliminar un nodo").toString();
        //Cambios
        JOptionPane.showMessageDialog(null,"Hola");

    }
}

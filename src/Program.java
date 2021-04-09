import Arboles.*;

import javafx.application.Application;
import javafx.stage.Stage;
import Arboles.Arbol;

import javax.swing.*;

public class Program extends Application {
    private Arbol _Arbol = null;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String Mensaje = "===========Menu============== \n"  +
                "1.) Crear nuevo arbol \n" +
                "2.) Insertar nodo en el arbol \n" +
                "3.) Mostrar Arbol \n" +
                "4.) Eliminar Nodo \n" +
                "5.) Buscar Nodo \n" +
                "6.) Mostrar raices \n" +
                "7.) Contar hojas de un arbol \n" +
                "8.) Mostrar hojas de un arbol \n" +
                "9.) Mostrar grado arbol \n" +
                "10.) Mostrar altura arbol \n" +

                "0.) salir \n";

        String Condicion = "0";
        Condicion = JOptionPane.showInputDialog(Mensaje).toString();
        do {
            Menu(Condicion);
            Condicion = JOptionPane.showInputDialog(Mensaje).toString();
        } while (Condicion != "0");

    }


    private void  Menu(String condicion) {

        switch (condicion) {
            case "1":
                _Arbol =  new Arbol();
                break;
            case "2":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                InsertarNodo();
                break;
            case "3":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                _Arbol.Mostrar(_Arbol.Raiz());
                break;
            case "4":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                EliminarNodo();
                break;
            case "5":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                BuscarNodo();
                break;
            case "6":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                _Arbol.MostrarRaices(_Arbol.Raiz());
                break;
            case "7":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                int NumHojas = _Arbol.ContarHojasNodo(_Arbol.Raiz());
                JOptionPane.showMessageDialog(null,"El arbol tiene  " + NumHojas + " hojas");
                break;
            case "8":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                _Arbol.MostrarHojasNodo(_Arbol.Raiz());
                break;
            case "9":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                int GradoArbol = _Arbol.MostrarGradoArbol(_Arbol.Raiz(),0);
                JOptionPane.showMessageDialog(null,"El arbol tiene un grado de  " + GradoArbol);
                break;
            case "10":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                int AlturaArbol = _Arbol.MostrarAlturaArbol(_Arbol.Raiz(),0);
                JOptionPane.showMessageDialog(null,"El arbol tiene una altura de " + AlturaArbol);
                break;
            case "11":
                if(_Arbol == null) {
                    JOptionPane.showMessageDialog(null,"El arbol esta vacio");
                    return;
                }
                this.MostrarPadreDatoDado();
                break;

        }
    }

    private void EliminarNodo() {

        String Condicion = "0";
        do {
            String NodoELiminar =  JOptionPane.showInputDialog("Nodo a eliminar").toString();
            _Arbol.EliminarNodo(NodoELiminar,null);
            Condicion = JOptionPane.showInputDialog("Desea eliminar un nuevo nodo, si o no?").toString();
        } while (Condicion.equals("si"));

    }
    private void InsertarNodo() {
        String NodoDato = "0";
        String NodoPadre = "0";
        String Condicion = "0";
        Condicion = JOptionPane.showInputDialog("Desea ingresar un nuevo nodo al arbol, si o no?").toString();
        do {
            NodoDato = JOptionPane.showInputDialog("Ingreso un nodo").toString();
            NodoPadre = JOptionPane.showInputDialog("Ingreso el nodo padre del nodo anterior").toString();
            _Arbol.InsertarNodo(NodoDato,NodoPadre,_Arbol.Raiz());
            Condicion = JOptionPane.showInputDialog("Desea ingresar un nuevo nodo al arbol, si o no?").toString();
        } while (Condicion.equals("si"));
    }

    private void BuscarNodo() {
        String NodoDato = "0";
        String Condicion = "0";
        Condicion = JOptionPane.showInputDialog("Desea buscar un nodo , si o no?").toString();
        do {
            NodoDato = JOptionPane.showInputDialog("Nodo a Buscar").toString();

            _Arbol.BuscarDato(_Arbol.Raiz(), NodoDato);

            Condicion = JOptionPane.showInputDialog("Desea buscar un nodo, si o no?").toString();
        } while (Condicion.equals("si"));
    }

    private void MostrarPadreDatoDado() {
        String NodoDato = "0";
        String Condicion = "0";
        Condicion = JOptionPane.showInputDialog("Desea ver raices de un nodo , si o no?").toString();
        do {
            NodoDato = JOptionPane.showInputDialog("Nodo a Buscar").toString();

            _Arbol.MostrarPadreDatoDado(_Arbol.Raiz(), _Arbol.Raiz(),false,NodoDato);

            Condicion = JOptionPane.showInputDialog("Desea ver raices de un nodo , si o no?").toString();
        } while (Condicion.equals("si"));
    }

}

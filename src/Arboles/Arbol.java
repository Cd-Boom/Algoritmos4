package Arboles;

import javafx.application.Application;

import javax.swing.*;

public class Arbol {
    //Variables

    private Nodo Raiz;

    // Constructores
    public Arbol()
    {
        this.Raiz = null;
    }

    // Obtenemos el nodo raíz
    public Nodo Raiz() {return this.Raiz;}


    //Insertamos un dato al arbol nario
    public void InsertarNodo(String NodoDato,String NodoPadreDato ) {

        Nodo nodo = new Nodo();
        nodo.Ingresar_Dato(NodoDato);
        nodo.Ingresar_EsNodoRaiz(false);
        if (this.Raiz == null) {
            this.Raiz = nodo;
        } else {
            Nodo NodoSiguiente = null;
            Nodo NodoAnterior = null;

            if (this.Raiz.Dato().equals(NodoPadreDato) ) {
                NodoSiguiente = this.Raiz;
                while (NodoSiguiente != null) {
                    NodoAnterior = NodoSiguiente;
                    NodoSiguiente = NodoSiguiente.Siguiente();
                }
                NodoAnterior.Ingresar_Siguiente(nodo);

            }
            else {
                NodoAnterior = this.Raiz;
                NodoSiguiente = this.Raiz.Siguiente();
                do {

                    if (NodoSiguiente.Dato().equals(NodoPadreDato)) {
                        break;
                    }
                    NodoAnterior = NodoSiguiente;
                    NodoSiguiente =   (NodoSiguiente.EsNodoRaiz() && _BuscarDato(NodoSiguiente.Arbol(),NodoPadreDato) != null) ?
                            _BuscarDato(NodoSiguiente.Arbol(),NodoPadreDato) : NodoSiguiente.Siguiente();;

                } while(NodoSiguiente != null);

                if (NodoAnterior.EsNodoRaiz()) {

                    while (NodoSiguiente != null) {
                        NodoSiguiente = NodoSiguiente.Siguiente();
                    }
                    NodoSiguiente.Ingresar_Siguiente(nodo);
                    NodoSiguiente.Ingresar_EsNodoRaiz(false);


                } else {
                    Nodo NodoRaiz  = new Nodo();
                    NodoRaiz.Ingresar_EsNodoRaiz(true);
                    NodoRaiz.Ingresar_Siguiente(NodoSiguiente.Siguiente());
                    NodoRaiz.Ingresar_Arbol(NodoAnterior.Siguiente());
                    NodoAnterior.Ingresar_Siguiente(NodoRaiz);

                    NodoSiguiente.Ingresar_Siguiente(nodo);
                    NodoSiguiente.Ingresar_EsNodoRaiz(false);
                }
            }



        }
    }


    //Eliminamos un dato al arbol nario
    public void EliminarNodo(String NodoDatoEliminar) {
        Nodo NodoSiguiente = this.Raiz;
        Nodo NodoAnterior = NodoSiguiente;
        do {
            NodoAnterior = NodoSiguiente;
            if (NodoSiguiente.Dato().equals(NodoDatoEliminar)) {
                break;
            }
            NodoAnterior = NodoSiguiente;
            NodoSiguiente =   (NodoSiguiente.EsNodoRaiz() && _BuscarDato(NodoSiguiente.Arbol(),NodoDatoEliminar) != null) ?
                    _BuscarDato(NodoSiguiente.Arbol(),NodoDatoEliminar) : NodoSiguiente.Siguiente();;
        } while (NodoSiguiente !=  null);
        if (NodoSiguiente != null && NodoSiguiente == this.Raiz && NodoSiguiente.Siguiente().EsNodoRaiz()) {
            Nodo Aux = NodoSiguiente.Siguiente().Arbol();
            NodoSiguiente.Siguiente().Ingresar_Arbol(Aux.Siguiente());
            Aux.Ingresar_Arbol(NodoSiguiente.Siguiente());
            this.Raiz = Aux;
        } else if (NodoSiguiente != null && NodoSiguiente == this.Raiz) {
            this.Raiz = NodoSiguiente;
        } else if (NodoSiguiente != null && NodoAnterior.EsNodoRaiz() && NodoSiguiente.Siguiente() != null) {
            NodoAnterior.Ingresar_Arbol(NodoSiguiente.Siguiente());
        } else {
            NodoAnterior.Ingresar_Siguiente(NodoSiguiente.Siguiente());
        }


    }


    //Buscamos un dato en el arbol nario
    public void BuscarDato(Nodo nodo, String dato) {

        while (nodo != null) {
            if (nodo.Dato() == dato) {
                JOptionPane.showMessageDialog(null,"Se encontro el dato");
                nodo = null;
            } else  if (nodo.EsNodoRaiz()) {
                BuscarDato(nodo.Arbol(),dato);
                nodo = nodo.Siguiente();
            }
            else {
                nodo = nodo.Siguiente();

            }
        }


    }


    //Buscamos un dato en el arbol nario
    public Nodo _BuscarDato(Nodo nodo, String dato) {
        Nodo NodoRetorno = null;
        while (nodo != null) {
            if (nodo.Dato() == dato) {
                JOptionPane.showMessageDialog(null,"Se encontro el dato");
                NodoRetorno = nodo;
                nodo = null;
            } else  if (nodo.EsNodoRaiz()) {
                NodoRetorno = _BuscarDato(nodo.Arbol(),dato);
                nodo = nodo.Siguiente();
            }
            else {
                nodo = nodo.Siguiente();

            }
        }

        return NodoRetorno;


    }


    //Mostramos la raiz de un nodo
    public void MostrarRaices(Nodo nodo, Nodo raiz, boolean esRaiz) {

        while (nodo != null ) {
            if (nodo == this.Raiz) {
                raiz = nodo;
                JOptionPane.showMessageDialog(null, nodo.Dato() +",es el nodo principal");
            } else if (nodo.EsNodoRaiz()) {
                MostrarRaices(nodo.Arbol(),raiz,true);
            } else if (esRaiz)  {
                JOptionPane.showMessageDialog(null, "La raiz del nodo, " + nodo.Dato() + " es " + raiz.Dato() );
                raiz = nodo;
                esRaiz = false;
            } else {
                JOptionPane.showMessageDialog(null, "La raiz del nodo, " + nodo.Dato() + " es " + raiz.Dato() );

            }
            nodo = nodo.Siguiente();
        }

    }

    //Mostramos hojas de  un nodo
    public void MostrarHojas (Nodo nodo, boolean esRaiz) {
        while (nodo != null ) {
            if (esRaiz) {
                esRaiz = false;
            }
            else if (nodo.EsNodoRaiz()) {
                MostrarHojas(nodo.Arbol(),true);
            } else if(!esRaiz) {
                JOptionPane.showMessageDialog(null, "El nodo " + nodo.Dato() + " es una hoja");

            }
            nodo = nodo.Siguiente();
        }


    }
    //Contamos la cantidad de hojas del nodo
    public int ContarHojasNodo (Nodo nodo, boolean esRaiz) {
        int CantidadHojas = 0;
        while (nodo != null ) {
            if (esRaiz) {
                esRaiz = false;
            }
            else if (nodo.EsNodoRaiz()) {
                CantidadHojas += ContarHojasNodo(nodo.Arbol(),true);
            } else if(!esRaiz) {
                JOptionPane.showMessageDialog(null, "El nodo " + nodo.Dato() + " es una hoja");

            }
            nodo = nodo.Siguiente();
        }

        return CantidadHojas;
    }

    //Mostramos el grado de un arbol
    public int MostrarGradoArbol(Nodo nodo,boolean esRaiz, int Grado_Anterior) {
        int Grado = 0;
        while (nodo != null ) {
            if (esRaiz) {
                esRaiz = false;
            }
            else if (nodo.EsNodoRaiz()) {

                Grado_Anterior = MostrarGradoArbol(nodo.Arbol(),true,Grado) ;
                Grado +=1;
            } else if(!esRaiz) {
                Grado +=1;
            }
            nodo = nodo.Siguiente();
        }
        //Grado_Anterior
        return (Grado_Anterior >= Grado) ? Grado_Anterior :  Grado;
    }

    // Mostramos el grado de un nodo por el dato
    public void MostrarGradoPorDato(int dato) {}

    //Mostramos los hijos de un dato
    public void MostrarHijosPorDato(Nodo nodo, int dato) {}

    // Mostramos el nivel de un nodo por un dato dato
    public void MostrarNivelPorDato(Nodo nodo, int dato) {}

    //Mostrar altura nodo
    public int MostrarAltura(Nodo nodo, int nivel){

        while (nodo != null) {

            if (nodo.EsNodoRaiz()) {
                nivel += MostrarAltura(nodo,1);
            }
            nodo = nodo.Siguiente();

        }
        return nivel;
    }


    //Mostramos el arbol
    public void Mostrar(Nodo nodo) {

        Nodo _nodo = nodo;
        while (_nodo != null) {

            if (_nodo.EsNodoRaiz()) {
                //Mostramos la informacion del nodo
                JOptionPane.showMessageDialog(null,_nodo.Dato());

            }  else {
                //Muestra el nodo siguiente
                JOptionPane.showMessageDialog(null,_nodo.Siguiente());

            }
            _nodo = _nodo.Siguiente();

        }

    }


}

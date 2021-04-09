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
    public Arbol(Nodo raiz)
    {
        this.Raiz =raiz;
    }

    // Obtenemos el nodo raíz
    public Nodo Raiz() {return this.Raiz;}

    private Nodo CrearNodo(String NodoDato,boolean EsRaiz, Nodo Siguiente, Arbol Arbol) {
        Nodo nodo = new Nodo();
        nodo.Ingresar_Dato(NodoDato);
        nodo.Ingresar_EsNodoRaiz(EsRaiz);
        nodo.Ingresar_Siguiente(Siguiente);
        nodo.Ingresar_Arbol(Arbol);
        return nodo;
    }

    public void InsertarNodo(String NodoDato,String NodoPadreDato, Nodo nodo)
    {
        Nodo p = nodo;
        if(this.Raiz == null) {
            this.Raiz = CrearNodo(NodoDato,false,null,null);
        } else if( this.Raiz.equals(nodo)  && this.Raiz.Dato().equals(NodoPadreDato)) {
            while(p.Siguiente() != null) {
                p = p.Siguiente();
            }
            p.Ingresar_Siguiente(this.CrearNodo(NodoDato,false,null,null));
        } else {
            while (p != null) {
                 if (p.EsNodoRaiz()) {
                     p.Arbol().InsertarNodo(NodoDato,NodoPadreDato,p.Arbol().Raiz());
                } else if (p.Dato().equals(NodoPadreDato)) {
                     Nodo NuevaRaiz = this.CrearNodo(p.Dato(),false,null,null);
                     Arbol _arbol = new Arbol(NuevaRaiz);
                     p.Ingresar_Dato(null);
                     p.Ingresar_EsNodoRaiz(true);
                     p.Ingresar_Arbol(_arbol);
                     _arbol.InsertarNodo(NodoDato,NodoPadreDato,NuevaRaiz);
                 }
                p = p.Siguiente();
            }
        }
    }


    //Eliminamos un dato al arbol nario
    public void EliminarNodo(String NodoDatoEliminar, Nodo anterior_aux) {
        if (this.Raiz.Dato().equals(NodoDatoEliminar) && this.Raiz.Siguiente() == null) {
            this.Raiz = null;
        }
        else if (this.Raiz.Dato().equals(NodoDatoEliminar) && !this.Raiz.Siguiente().EsNodoRaiz()) {
            if (anterior_aux == null || this.Raiz.Siguiente().Siguiente() != null) {
                this.Raiz = this.Raiz.Siguiente();
            } else if (this.Raiz.Siguiente().Siguiente() == null) {
                anterior_aux.Ingresar_Dato(this.Raiz.Siguiente().Dato());
                anterior_aux.Ingresar_Arbol(null);
                anterior_aux.Ingresar_EsNodoRaiz(false);
            }


        }
        else if (this.Raiz.Dato().equals(NodoDatoEliminar) && this.Raiz.Siguiente().EsNodoRaiz()) {

            Nodo Aux = this.Raiz.Siguiente();
            Arbol ArbolAuxRaiz = Aux.Arbol();
            Nodo NodoAuxRaiz = Aux.Arbol().Raiz();
            Aux.Ingresar_EsNodoRaiz(true);
            ArbolAuxRaiz.EliminarNodo(ArbolAuxRaiz.Raiz().Dato(),this.Raiz.Siguiente());
            NodoAuxRaiz.Ingresar_Siguiente(Aux);
            this.Raiz = NodoAuxRaiz;
        } else  {
            Nodo Siguiente = this.Raiz.Siguiente();
            Nodo Anterior = this.Raiz;
            while(Siguiente != null) {
                if(!Siguiente.EsNodoRaiz() && Siguiente.Dato().equals(NodoDatoEliminar) && anterior_aux == null) {
                    Anterior.Ingresar_Siguiente(Siguiente.Siguiente());
                    break;
                }
                else if(!Siguiente.EsNodoRaiz() && Siguiente.Dato().equals(NodoDatoEliminar) && anterior_aux != null ) {

                    if (this.Raiz.Siguiente().Siguiente() != null) {
                        Anterior.Ingresar_Siguiente(Siguiente.Siguiente());
                    } else if (this.Raiz.Siguiente().Siguiente() == null) {
                        anterior_aux.Ingresar_Dato(Anterior.Dato());
                        anterior_aux.Ingresar_Arbol(null);
                        anterior_aux.Ingresar_EsNodoRaiz(false);
                    }



                }
                else if (Siguiente.EsNodoRaiz()) {
                    Siguiente.Arbol().EliminarNodo(NodoDatoEliminar,Siguiente);
                }
                Anterior = Siguiente;
                Siguiente = Siguiente.Siguiente();
            }



        }


    }


        //Buscamos un dato en el arbol nario
        public void BuscarDato(Nodo nodo, String dato) {

            Nodo _nodo = nodo;
            while (_nodo != null) {
                if (_nodo.Dato().equals(dato)) {
                    JOptionPane.showMessageDialog(null, "Se encontro el dato: "+ dato);
                    break;
                }
                else if (_nodo.EsNodoRaiz()) {
                    _nodo.Arbol().Mostrar(_nodo.Arbol().Raiz());

                }
                _nodo = _nodo.Siguiente();

            }


        }


    //Mostramos la raiz de un nodo
    public void MostrarRaices(Nodo nodo) {

        while (nodo != null ) {
            if (nodo == this.Raiz) {
                JOptionPane.showMessageDialog(null, this.Raiz.Dato());

            } else if (nodo.EsNodoRaiz()) {
                nodo.Arbol().MostrarRaices(nodo.Arbol().Raiz);
            }
            nodo = nodo.Siguiente();
        }

    }

          //Mostramos la raiz de un nodo
          public void MostrarPadreDatoDado(Nodo nodo, Nodo raiz, boolean esRaiz, String DatoBuscar) {

              while (nodo != null ) {
                  if (nodo == this.Raiz) {

                      if (nodo.Dato().equals(DatoBuscar))
                      {
                          JOptionPane.showMessageDialog(null, raiz.Dato() +",es el nodo padre");
                          break;
                      }

                  } else if (nodo.EsNodoRaiz()) {
                      nodo.Arbol().MostrarPadreDatoDado(nodo.Arbol().Raiz,this.Raiz,true,DatoBuscar);
                  } else if (esRaiz)  {

                      if (nodo.Dato().equals(DatoBuscar))
                      {
                          JOptionPane.showMessageDialog(null, raiz.Dato() +",es el nodo padre");
                          break;
                      }
                      raiz = nodo;
                      esRaiz = false;
                  } else {
                      if (nodo.Dato().equals(DatoBuscar))
                      {
                          JOptionPane.showMessageDialog(null, raiz.Dato() +",es el nodo padre");
                          break;
                      }

                  }
                  nodo = nodo.Siguiente();
              }

          }


        //Mostramos el arbol
        public void Mostrar(Nodo nodo) {

            Nodo _nodo = nodo;
            while (_nodo != null) {

                if (_nodo.EsNodoRaiz()) {
                    _nodo.Arbol().Mostrar(_nodo.Arbol().Raiz());

                }  else {
                    //Mostramos la informacion del nodo
                    JOptionPane.showMessageDialog(null,_nodo.Dato());

                }
                _nodo = _nodo.Siguiente();

            }

        }


    //Contamos la cantidad de hojas del nodo
    public int ContarHojasNodo (Nodo nodo) {
        int CantidadHojas = 0;
        while (nodo != null ) {
            if (this.Raiz != nodo && !nodo.EsNodoRaiz()) {
                CantidadHojas += 1;
            }
            else if (nodo.EsNodoRaiz()) {
                CantidadHojas += nodo.Arbol().ContarHojasNodo(nodo.Arbol().Raiz);
            }
            nodo = nodo.Siguiente();
        }

        return CantidadHojas;
    }

    //Contamos la cantidad de hojas del nodo
    public void MostrarHojasNodo (Nodo nodo) {
        while (nodo != null ) {
            if (this.Raiz != nodo && !nodo.EsNodoRaiz()) {
                JOptionPane.showMessageDialog(null,nodo.Dato());
            }
            else if (nodo.EsNodoRaiz()) {
                nodo.Arbol().MostrarHojasNodo(nodo.Arbol().Raiz);
            }
            nodo = nodo.Siguiente();
        }

    }


    public int MostrarAlturaArbol(Nodo nodo, int Altura_Anterior) {
        int Altura = 1;
        while (nodo != null ) {
            if (nodo.EsNodoRaiz()) {
                Altura_Anterior += nodo.Arbol().MostrarAlturaArbol(nodo.Arbol().Raiz,Altura) ;
            }
            nodo = nodo.Siguiente();
        }
        //Grado_Anterior
        return (Altura_Anterior >= Altura) ? Altura_Anterior :  Altura;
    }

    //Mostramos el grado de un arbol
    public int MostrarGradoArbol(Nodo nodo, int Grado_Anterior) {
        int Grado = 0;
        while (nodo != null ) {
            if (this.Raiz != nodo && !nodo.EsNodoRaiz()) {
                Grado = Grado +1;
            }
            else if (nodo.EsNodoRaiz()) {
                Grado_Anterior += nodo.Arbol().MostrarGradoArbol(nodo.Arbol().Raiz,Grado) ;
            }
            nodo = nodo.Siguiente();
        }
        //Grado_Anterior
        return (Grado_Anterior >= Grado) ? Grado_Anterior :  Grado;
    }


    //Mostramos el grado de un arbol
    public void MostrarGradoADato(String Dato) {
        int Grado = 0;
        boolean bandera = false;
        Nodo nodo = this.Raiz;
        while (nodo != null ) {
            if (nodo.Dato().equals(Dato)) {
                bandera = true;
            }
            if (this.Raiz != nodo && !nodo.EsNodoRaiz()) {
                Grado = Grado +1;
            }
            else if (nodo.EsNodoRaiz()) {
                nodo.Arbol().MostrarGradoADato(Dato) ;
            }
            nodo = nodo.Siguiente();
        }

        if (bandera) {
            JOptionPane.showMessageDialog(null, "El grado del nodo"+ Dato + " es, " + Grado);
        }
        //Grado_Anterior

    }
    /*

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

*/



}

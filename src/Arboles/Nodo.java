package Arboles;

public class Nodo  {

    //Variables
    private Nodo Siguiente;
    private String Dato;
    private boolean EsNodoRaiz;
    private Arbol Arbol;

    //Contructor
    public Nodo() {
        this.Siguiente = null;
        this.Dato = null;
        this.EsNodoRaiz = false;
        this.Arbol = null;

    }

    public void Ingresar_Siguiente( Nodo Siguiente) {        this.Siguiente = Siguiente;    }


    public void Ingresar_Dato(String Dato)
    {
        this.Dato = Dato;
    }

    public void Ingresar_EsNodoRaiz(boolean EsNodoRaiz)
    {
        this.EsNodoRaiz = EsNodoRaiz;
    }

    public void Ingresar_Arbol(Arbol Arbol)
    {
        this.Arbol = Arbol;
    }


    public Nodo Siguiente()
    {
        return this.Siguiente;
    }

    public String Dato()
    {
        return this.Dato;
    }

    public boolean  EsNodoRaiz()
    {
        return this.EsNodoRaiz;
    }

    public Arbol  Arbol()
    {
        return this.Arbol;
    }








}

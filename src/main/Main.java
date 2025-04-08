package main;

import controlador.ControladorJuego;
import modelo.Tablero;
import vista.VentanaJuego;

public class Main {

    public static void main(String[] args) {
        int tamañoInicial = 5;

        Tablero tablero = new Tablero(tamañoInicial);
        ControladorJuego controlador = new ControladorJuego(tablero);
        VentanaJuego vista = new VentanaJuego(controlador, tamañoInicial);

        controlador.colocarVista(vista);
        vista.mostrar();
    }
}

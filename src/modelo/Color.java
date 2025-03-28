package modelo;

import java.util.Random;

public enum Color {

	GRIS, ROJO, VERDE, AZUL, AMARILLO, NARANJA, VIOLETA;

	private static Random RAND = new Random();

	public static Color obtenerColor() {
		Color[] colores = Color.values();
		int valor = RAND.nextInt(colores.length);
		return colores[valor];
	}

}
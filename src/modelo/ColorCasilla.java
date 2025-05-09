package modelo;

import java.awt.Color;
import java.util.Random;

public enum ColorCasilla {

	ROJO(Color.RED),
	VERDE(Color.GREEN),
	AZUL(Color.BLUE),
	AMARILLO(Color.YELLOW),
	NARANJA(Color.ORANGE),
	VIOLETA(Color.MAGENTA),
	GRIS(Color.LIGHT_GRAY);

	private Color color;
	private static Random RAND = new Random();

	ColorCasilla(Color color) {
		this.color = color;
	}

	public static ColorCasilla cambiarColor() {
		ColorCasilla[] colores = ColorCasilla.values();
		int valor = RAND.nextInt(colores.length - 1); // Todos los colores menos el GRIS
		return colores[valor];
	}

	public Color obtenerColor() {
		return this.color;
	}
}

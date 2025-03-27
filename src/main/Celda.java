package main;

public class Celda {
	public enum Color {
		ROJO, VERDE, AZUL, AMARILLO, NARANJA, VIOLETA;
	
		public static Color colorSeleccionado() {
			return values()[(int) (Math.random() * values().length)];
		}
	}

	private Color color;

	public Celda() {
        this.color = null;
    }
	
	
	public void cambiarColor() {
        this.color = Color.colorSeleccionado();
    }

	public Color getColor() {
        return color;
    }

	

}

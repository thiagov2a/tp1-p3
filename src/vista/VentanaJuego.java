package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuego {

	private JFrame frame;
	private JPanel panelTablero;
	private JButton[][] botonesCasillas;
	private final int TAMAÑO = 5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego window = new VentanaJuego();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaJuego() {
		inicializar();
	}

	private void inicializar() {
		frame = new JFrame("Locura Cromática");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelTablero = new JPanel();
		panelTablero.setLayout(new GridLayout(TAMAÑO, TAMAÑO));
		botonesCasillas = new JButton[TAMAÑO][TAMAÑO];
	}

	private Color Colorear(modelo.Color color){
        switch(color){
            case ROJO: return Color.RED;
            case VERDE: return Color.GREEN;
            case AZUL: return Color.BLUE;
            case AMARILLO: return Color.YELLOW;
            case NARANJA: return Color.ORANGE;
            case VIOLETA: return new Color(76, 40, 130);
            default: return Color.LIGHT_GRAY;
        }
    }


	

}

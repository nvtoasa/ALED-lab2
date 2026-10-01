package es.upm.aled.lab2.gui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import es.upm.aled.lab2.kinematics.*;

/**
 * Draws on a window an exoskeleton represented as a tree of Nodes.
 * 
 * @author rgarciacarmona
 */
public class SkeletonPanel extends JPanel {

	private static final long serialVersionUID = 8724301078499171545L;
	private Segment root;

	/**
	 * Shows a window containing a 2D representation of an exoskeleton. Each joint
	 * in the kinematic chain is represented with a dot, and each segment with a
	 * line that connects two dots together. The end effector is also represented as
	 * a dot. It can be animated to show the exoskeleton's movement by calling the
	 * repaint() method after modifying the attributes of the kinematic chain's
	 * elements.
	 * 
	 * This class uses ForwardKinematics to compute the positions of every element
	 * in the kinematic chain.
	 * 
	 * @param root  The first Segment of the kinematic chain.
	 * @param sizeX The width of the window.
	 * @param sizeX The height of the window.
	 */
	public SkeletonPanel(Segment root, int sizeX, int sizeY) {
		this.root = root;
		// Setup Swing window
		JFrame frame = new JFrame("Recursive Skeleton - Forward Kinematics");
		frame.add(this);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Draws the window
		super.paintComponent(g);
		float originX = getWidth() / 2;
		float originY = getHeight() / 2;

		// Computes the full tree of positions
		Node nodeRoot = ForwardKinematics.computePositions(root, originX, originY);

		// Draw the tree of Nodes recursively
		drawSkeleton(g, originX, originY, nodeRoot);
	}

	private void drawSkeleton(Graphics g, double parentX, double parentY, Node node) {

		g.fillOval((int) node.getX() - 4, (int) node.getY() - 4, 8, 8);
		
		/**
		 * Ten en cuenta que si un segmento hijo tiene otros
		 * segmentos hijos, al volver a ejecutarse drawSkeleton() se mete a otro
		 * for con los hijos del hijo. Esto significa que cada drawSkeleton() solo
		 * retornará cuando lo hayan hecho TODOS los drawSkeleton que este ha llamado
		 * a su vez.
		 */
		
		// Código general
		
		g.drawLine((int) parentX, (int) parentY, (int) node.getX(), (int) node.getY());
		
		//Si el nodo no tiene hijos, no devuelve nada (nada que dibujar)
		
		//Caso base
		
		if (node.getChildren().size() == 0) {
			return;
		}
		
		/**
		 * For que recorre uno a uno los segmentos hijos del segmento padre:
		 * Paso recursivo.
		 */
		
		for (Node child : node.getChildren()) {
			drawSkeleton(g, node.getX(), node.getY(), child);
		}
	}
}
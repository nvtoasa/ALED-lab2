package es.upm.aled.lab2.kinematics;

import java.util.List;

import es.upm.aled.lab2.gui.Node;

public class Segment {
	
	//Definimos los atributos de la clase
	
	private double length, angle;
	private List<Segment> children;
	
	//Método constructor
	
	public Segment(double length, double angle, List<Segment> children) {
		this.length = length;
		this.angle = angle;
		this.children = children;
	}
	
	//Devuelve la longitud del segmento
	
	public double getLength() {
		return length;
	}
	
	//Devuelve el ángulo que forma con su segmento padre
	
	public double getAngle() {
		return angle;
	}
	
	//Devuelve los segmentos hijos
	
	public List<Segment> getChildren() {
		return children;
	}
	
	//Establece el ángulo que forma el segmento con su segmento padre
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	//Añade un 'child' a la lista 'children' que contiene los segmentos hijos
	
	public void addChild(Segment child) {
		if (!children.contains(child))
			children.add(child);
	}
}

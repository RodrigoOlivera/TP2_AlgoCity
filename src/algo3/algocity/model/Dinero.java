package algo3.algocity.model;

import java.util.Observable;
import java.util.Observer;

import algo3.algocity.model.excepciones.NoTieneSuficientesFondosException;

public class Dinero implements Observer {
	
	Turno turno;
	Poblacion poblacion;

	int cantidad;

	public Dinero(Poblacion p, Turno t) {
		poblacion = p;
		turno = t;
		cantidad = 20000;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(turno.getTurno() % 30 == 0){
			cantidad += poblacion.getCantidad() * 10;
		}
	}

	public int getCantidad() {
		return cantidad;
	}
	
//	public void cobrar(int costo){
//		cantidad -= costo;
//	}
	
	public boolean cobrar(int costo) throws NoTieneSuficientesFondosException {
		if (cantidad < costo){
			throw new NoTieneSuficientesFondosException();
		}
		cantidad -= costo;
		return true;
	}
	
}

package app;

public class PuntuacionVO {
	
	private String idReceta;
	private String ip;
	private String puntos;
	
	public PuntuacionVO () {
		
	}
	
	public PuntuacionVO (String idReceta, String ip, String puntos) {
		this.idReceta = idReceta;
		this.ip = ip;
		this.puntos = puntos;
	}
	
	public String getIdReceta () {
		return this.idReceta;
	}
	
	public void setIdReceta (String nuevo) {
		this.idReceta = nuevo;
	}
	
	public String getIp () {
		return this.ip;
	}
	
	public void setIp (String nuevo) {
		this.ip = nuevo;
	}
	
	public String getPuntos () {
		return this.puntos;
	}
	
	public void setPuntos (String nuevo) {
		this.puntos = nuevo;
	}
}

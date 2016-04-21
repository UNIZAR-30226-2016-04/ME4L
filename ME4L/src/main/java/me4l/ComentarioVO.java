package me4l;

public class ComentarioVO {

	private String idReceta;
	private String idComentario;
	private String contenido;
	
	public ComentarioVO () {
		
	}
	
	public ComentarioVO (String idReceta, String comentario) {
		this.idReceta = idReceta;
		this.contenido = comentario;
	}
	
	public String getIdReceta () {
		return this.idReceta;
	}
	
	public void setIdReceta (String nuevo) {
		this.idReceta = nuevo;
	}
	
	public String getIdComentario () {
		return this.idComentario;
	}
	
	public void setIdComentario (String nuevo) {
		this.idComentario = nuevo;
	}
	
	public String getContenido () {
		return this.contenido;
	}
	
	public void setContenido (String nuevo) {
		this.contenido = nuevo;
	}
}

package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComentarioDAO {

    private Connection conexion;

    public ComentarioDAO (Connection conexion) { this.conexion = conexion; }

    public void addComentario (ComentarioVO comentario) {

        try {
            Statement s = conexion.createStatement();
            s.execute(
                    "INSERT INTO comentarios VALUES (null, '" + comentario.getIdReceta() + "', '"
                            + comentario.getContenido() + "');");
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarComentario (String idComentario, String contenido) {

        try {
            Statement s = conexion.createStatement();
            s.execute(
                    "UPDATE comentarios SET comentario='" + contenido + "' WHERE idComentario = '" + idComentario + "';");
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarComentario (String idComentario) {

        try {
            Statement s = conexion.createStatement();
            s.execute(
                    "DELETE FROM comentarios WHERE idComentario='" + idComentario + "' ;");
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ComentarioVO devolverComentario (String idComentario) {

        try {
            ComentarioVO comentario = new ComentarioVO();
            comentario.setIdComentario(idComentario);

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM comentarios WHERE idComentario='" + idComentario + "';");

            rs.next();
            comentario.setIdReceta(rs.getString("idReceta"));
            comentario.setContenido(rs.getString("comentario"));

            return comentario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean existeComentario (String idComentario) {

        try {
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM comentarios WHERE idComentario='" + idComentario + "';");
            boolean existe;
            if (rs.next()) {
                existe = true;
            }
            else {
                existe = false;
            }
            rs.close();
            return existe;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ComentarioVO> comentariosReceta (String idReceta) {

        try {
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM comentarios WHERE idReceta='" + idReceta + "';");

            ArrayList<ComentarioVO> comentarios = new ArrayList<>();
            String texto = "";
            
            while (rs.next()) {
                texto = rs.getString("comentario");
                ComentarioVO comentario = new ComentarioVO(idReceta,texto);
                comentarios.add(comentario);
            }
            s.close();
            return comentarios;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

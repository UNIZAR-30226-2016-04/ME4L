package me4l;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que implementa un patrón de acceso a BBDD de tipo Table Data Gateway,
 * en este caso, para la tabla de la BBDD que almacena los datos de un comentario.
 */
public class ComentarioDAO {

    private Connection conexion;

    /**
     * Constructor de la clase ComentarioDAO.
     *
     * @param conexion Objeto de tipo Connection que contiene la información necesaria
     *                 para realizar la conexión con la BBDD.
     */
    public ComentarioDAO (Connection conexion) { this.conexion = conexion; }

    /**
     * Función que se encarga de insertar los datos de un comentario en la BBDD.
     *
     * @param comentario Objeto de tipo ComentarioVO que contiene la información de un comentario
     *                   que se ha de almacenar en la BBDD.
     */
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

    /**
     * Función que se encarga de modificar los datos de un comentario existente en la BBDD.
     *
     * @param idComentario Cadena de caracteres que identifica el comentario, existente en la BBDD,
     *                     que ha de ser modificado.
     * @param contenido Cadena de caracteres que representa el nuevo valor del texto del comentario
     *                  almacenado en la BBDD.
     */
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

    /**
     * Función que se encarga de eliminar un comentario existente en la BBDD.
     *
     * @param idComentario Cadena de caracteres que identifica el comentario, existente en la BBDD,
     *                     que ha de ser eliminado.
     */
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

    /**
     * Función que se encarga de buscar en la BBDD los datos de un comentario existente.
     *
     * @param idComentario Cadena de caracteres que identifica el comentario, existente en la BBDD,
     *                     que ha de ser devuelto.
     * @return Objeto de tipo ComentarioVO con toda la información almacenada sobre el comentario
     *         almacenado en la BBDD con el id especificado.
     */
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

    /**
     * Función que se encarga de comprobar si existe un comentario en la BBDD, con el id
     * especificado como parámetro.
     *
     * @param idComentario Cadena de caracteres que identifica el comentario que ha de ser
     *                     buscado en la BBDD.
     * @return Variable booleana con valor true si el id especificado como parámetro, se
     *         corresponde con un comentario de la BBDD; y valor false en caso contrario.
     */
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

    /**
     * Función que se encarga de buscar y devolver todos los comentarios existentes en
     * la BBDD, referentes a la receta especificada en el id pasado como parámetro.
     *
     * @param idReceta Cadena de caracteres que identifica la receta de la que se han de
     *                 devolver todos sus comentarios.
     * @return Lista de objetos ComentarioVO que contiene todos los comentarios de la
     *         receta con id especificado como parámetro.
     */
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

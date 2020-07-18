package cursoonline.dao;

import curso.util.util;
import cursoonline.entidades.Curso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cursoDaoImpl implements CursoDAO { //tener todos sus metodo de la interfz

    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        Connection conn;
        try {
            conn = DriverManager.getConnection(util.url, util.user, util.password);

            String sql = "SELECT id,nombre FROM public.cursos;";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso(rs.getInt(1), rs.getString(2));
                cursos.add(curso);
            }

        } catch (SQLException ex) {
            //TODO AUTO-GENERATED CATCH BLOCK
            ex.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void ingresar(Curso curso) {
        //insert into cursos(id, nombre,
        String query = "INSERT INTO public.cursos(nombre)VALUES ( ?);";
        Connection conn;
        try {
            conn = DriverManager.getConnection(util.url, util.user, util.password);

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, curso.getNombre());
            stm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actualizar(Curso curso) {
        String query = "UPDATE public.cursos SET nombre=? WHERE id=?;";
        Connection conn;
        try {
            conn = DriverManager.getConnection(util.url, util.user, util.password);

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, curso.getNombre());
            stm.setInt(2, curso.getId());
            stm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM public.cursos WHERE id=?;";
        Connection conn;
        try {
            conn = DriverManager.getConnection(util.url, util.user, util.password);

            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            stm.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
}
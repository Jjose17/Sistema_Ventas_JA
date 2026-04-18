
package datos;

import database.Conexion;
import datos.interfaces.CrudSimpleInterface;
import entidades.Categoria;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class CategoriaDAO implements CrudSimpleInterface<Categoria>{

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public CategoriaDAO(){
        CON = Conexion.getInstance();
    }
    
    @Override
    public List<Categoria> listar(String texto) {
        List<Categoria> registros = new ArrayList();
        try{
            ps = CON.conectar().prepareStatement("SELECT * FROM Categoria WHERE nombre like¿?");
        ps.setString(1, "%" + texto + "%");
        rs = ps.executeQuery();
        while(rs.next()){
            registros.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
        }
        ps.close();
        rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        resp=false;
        try{
            ps=CON.conectar().prepareStatement("INSERT INTO Categoria (Nombre, descripcion, activo) VALUES (?,?,1)");
       ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if(ps.executeUpdate()> 0){
                resp = true;
                }
           ps.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            ps=null;
            CON.desconectar();

        }
        return resp;
    }

    @Override
    public boolean actualizar(Categoria obj) {
        resp=false;
        try{
            ps=CON.conectar().prepareStatement("UPDATE categoria SET nombre=?, descripcion=?, WHERE id=?");
       ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getId());
            if(ps.executeUpdate()> 0){
                resp = true;
                }
           ps.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            ps=null;
            CON.desconectar();

        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp=false;
        try{
            ps=CON.conectar().prepareStatement("UPDATE categoria SET activo=0, WHERE id=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()> 0){
                resp = true;
                }
           ps.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            ps=null;
            CON.desconectar();

        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
          resp=false;
        try{
            ps=CON.conectar().prepareStatement("UPDATE categoria SET activo=1, WHERE id=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()> 0){
                resp = true;
                }
           ps.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            ps=null;
            CON.desconectar();

        }
        return resp;
    }

    @Override
    public int total() {
          int totalRegistros=0;
        try{
            ps=CON.conectar().prepareStatement("SELECT COUNT(id) categoria");
            rs=ps.executeQuery();
            while(rs.next()){
                totalRegistros= rs.getInt("COUNT(id)");
            }
           ps.close();
           rs.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            ps=null;
            rs=null;
            CON.desconectar();

        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
          resp=false;
        try{
            ps=CON.conectar().prepareStatement("SELECT nombre FROM categoria WHERE nombre=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()> 0){
                resp = true;
                }
           ps.close();
           rs.close();
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e.getMessage());
            }finally{
            ps=null;
            rs=null;
            CON.desconectar();

        }
        return resp;
    }
    
    
    
}

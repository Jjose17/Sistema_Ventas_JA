
package negocio;

import datos.CategoriaDAO;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.DefaultTableModel;



public class CategoriaControl {
    
    private final CategoriaDAO DATOS;
    private Categoria obj;
    private DefaultTableModel modeloTabla;
    
    public CategoriaControl(){
        this.DATOS = new CategoriaDAO();
        this.obj = new Categoria();
    }
    
    public DefaultTableModel listar(String texto){
        List<Categoria> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos = ("id", "Nombre", "Descripcion", "Estado");
        this.modeloTabla = new DefaultTableModel(null, titulos);
        
        String estado;
        String[] registro = new String[4];
        
        for(Categoria item:lista){
            if(item.isActivo()){
                estado = "Activo";
            }else{
                estado= "Inactivo";
            }
            
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            registro[3] = estado;
            this.modeloTabla.addRow(registro);
        }
        return this.modeloTabla;
        
    }
    
    public String insertar(String nombre, String descripcion){
        if(DATOS.existe(nombre)){
            return "El registro ya existe";
        }else{
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if(DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registros";
            }
            
            
        }
    }
    
    public String actualizar(int id, String nombre, String nombreAnt, String descripcion){
        
    }
    
    public String desactivar(int id){
        
    }
    
    public String activar(int id){
        
    }
    
    public int total(){
        
    }
}

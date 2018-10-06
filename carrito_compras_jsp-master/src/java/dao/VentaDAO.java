/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import static java.lang.Integer.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carrito;
import model.Venta;
import model.Producto;
/**
 *
 * @author marti
 */
public class VentaDAO extends ConexionDAO {
       public void guardarVenta(Venta venta) throws Exception{
        Connection conn= null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int id_factura;
        
        try{
            conn = this.getDs().getConnection();
            String key[]={"id_factura"};
            java.util.Date d = new java.util.Date();
            String date = new String();
            
            String vsql="INSERT INTO venta (id_usuario,numero_tarjeta,titular_tarjeta,monto,vencimiento,fecha) VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(vsql,key);
            ps.setInt(1, venta.getId_usuario());
            ps.setString(2,venta.getNumero_tarjeta());
            ps.setString(3, venta.getTitular_tarjeta());
            ps.setBigDecimal(4,venta.getMonto());
            ps.setString(5,venta.getFechaVen());
            ps.setDate(6, venta.getFecha());
            
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id_factura=rs.getInt(1);
                venta.setId_factura(id_factura);
                this.guardaCarritos(venta);
            }
            
            ps.close();
            ps=null;
            conn.close();
            conn=null;
            
        } catch (Exception e) {
            throw e;
        }
        
        
    
    }
   
        
        
        
     public void guardaCarritos(Venta venta) throws Exception{
        Connection conn=null;
        PreparedStatement ps= null;
        
        
        try{
            conn = this.getDs().getConnection();
            
            for (Producto producto: venta.getProductos()) { 
            
            
            
            String date = new String();
            
            String vsql="INSERT INTO venta_producto (id_producto, id_venta) VALUES (?,?)";
            ps = conn.prepareStatement(vsql);
            ps.setInt(1,producto.getId());
            ps.setInt(2,venta.getId_factura());
            ps.executeUpdate();
            
            
            
            
        }
        
            ps.close();
            ps=null;
            conn.close();
            conn=null;
            
        } catch (Exception e){
            throw e;
        }
        
    }
        
    }    
    
    
   


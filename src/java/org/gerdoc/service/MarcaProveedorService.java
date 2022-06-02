/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.gerdoc.dao.Marca;
import org.gerdoc.dao.Producto;
import org.gerdoc.dao.Proveedor;

/**
 *
 * @author gerdoc
 */
public class MarcaProveedorService 
{
    public List<Producto> getProductoList( )
    {
        List<Producto>productoList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Producto producto = null;
        String sql = null;
        
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            sql = "SELECT IdProducto, nombre,descripcion, url,PrecioPub,costo, marca,proveedor FROM tblproducto INNER JOIN tblProveedor ON tblproducto.TBLProveedor_IdProveedor= TBLproveedor.IdProveedor INNER JOIN TBLMarca ON tblproducto.TBLMarca_IdMarca = TBLMarca.IdMarca;";
            resultSet = statement.executeQuery( sql );
            if( resultSet == null )
            {
                return null;
            }
            productoList = new ArrayList<>();
            while( resultSet.next() )
            {
                producto = new Producto( new Marca( ) , new Proveedor( ) );
                producto.setIdProducto(resultSet.getInt(1) );
                producto.setNombre( resultSet.getString(2) );
                producto.setDescripcion( resultSet.getString(3) );
                producto.setUrl( resultSet.getString(4) );
                producto.setPrecioPub(resultSet.getInt(5));
                producto.setCosto(resultSet.getInt(6));
                producto.getMarca( ).setMarca(resultSet.getString(7) );
                producto.getProveedor().setProveedor( resultSet.getString(8) );
                productoList.add(producto);
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return productoList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean addProducto( Producto producto )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into tblProducto(nombre,descripcion,url,PrecioPub,costo,TBLMarca_idMarca,TBLProveedor_IdProveedor)values(?,?,?,?,?,?,?);";
        int row = 0;
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getUrl());
            preparedStatement.setInt(4, producto.getPrecioPub());
            preparedStatement.setInt(5, producto.getCosto());
            preparedStatement.setInt(6, producto.getMarca().getIdMarca());
            preparedStatement.setInt(7, producto.getProveedor().getIdProveedor());
            row = preparedStatement.executeUpdate();
            MySqlConnection.closeConnection(connection);
            return row == 1;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteProducto( Producto producto )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM TBLProducto WHERE IdProducto = ?";
        int row = 0;
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, producto.getIdProducto());
            row = preparedStatement.executeUpdate();
            MySqlConnection.closeConnection(connection);
            return row == 1;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public Producto getProductoById( Integer id )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM TBLProducto WHERE IdProducto= ?";
        Producto producto = null;
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id );
            resultSet = preparedStatement.executeQuery( );
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                producto = new Producto();
                producto.setIdProducto(resultSet.getInt(1) );
                producto.setNombre(resultSet.getString(2));
                producto.setDescripcion(resultSet.getString(3));
                producto.setUrl(resultSet.getString(4));
                producto.setPrecioPub(resultSet.getInt(5));
                producto.setCosto(resultSet.getInt(6));
                
            }
            resultSet.close();
            MySqlConnection.closeConnection(connection);
            return producto;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean updateProducto( Producto producto )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update TBLProducto SET nombre=?,descripcion=?,url=?,preciopub=?,costo=?,TBLMarca_idMarca=?,TBLProveedor_IdProveedor=? WHERE IdProducto = ?";
        int row = 0;
        try 
        {
            connection = MySqlConnection.getConnection( );
            if( connection == null )
            {
                return false;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setString(2, producto.getDescripcion());
            preparedStatement.setString(3, producto.getUrl());
            preparedStatement.setInt(4, producto.getPrecioPub());
            preparedStatement.setInt(5, producto.getCosto());
            preparedStatement.setInt(6, producto.getMarca().getIdMarca());
            preparedStatement.setInt(7, producto.getProveedor().getIdProveedor());
            preparedStatement.setInt(8, producto.getIdProducto());
            row = preparedStatement.executeUpdate();
            MySqlConnection.closeConnection(connection);
            return row == 1;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    
}

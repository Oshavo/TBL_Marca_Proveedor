/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.helper;

import java.io.Serializable;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.gerdoc.dao.Marca;
import org.gerdoc.dao.Producto;
import org.gerdoc.dao.Proveedor;
import org.gerdoc.service.MarcaProveedorService;


/**
 *
 * @author gerdoc
 */
public class MarcaProveedorHelper implements Serializable
{
    private List<Producto>list;
    private Producto marcaProveedor;
    private Producto producto;

    public MarcaProveedorHelper() 
    {
    }
    
    public boolean loadList( )
    {
        list = new MarcaProveedorService().getProductoList();
        return list != null && list.size() > 0;
    }
   
    public boolean addMarcaProveedor( HttpServletRequest request )
    {
        marcaProveedor = new Producto( new Marca( ) , new Proveedor( ) );
        marcaProveedor.setNombre(request.getParameter( "nombre" ));
        if( marcaProveedor.getNombre()== null || marcaProveedor.getNombre().length()==0 )
        {
            return false;
        }
        marcaProveedor.setDescripcion(request.getParameter( "descripcion" ));
        if( marcaProveedor.getDescripcion()== null || marcaProveedor.getDescripcion().length()==0 )
        {
            return false;
        }
        marcaProveedor.setUrl(request.getParameter( "url" ));
        if( marcaProveedor.getUrl()== null || marcaProveedor.getUrl().length()==0 )
        {
            return false;
        }
        marcaProveedor.setPrecioPub(getInteger(request.getParameter( "preciopub" )));
        if( marcaProveedor.getPrecioPub()== null || marcaProveedor.getPrecioPub()==0 )
        {
            return false;
        }
        marcaProveedor.setCosto(getInteger(request.getParameter( "costo" )));
        if( marcaProveedor.getCosto()== null || marcaProveedor.getCosto()==0 )
        {
            return false;
        }
        marcaProveedor.getMarca().setIdMarca(getInteger(request.getParameter( "marca" )) );
        if( marcaProveedor.getMarca().getIdMarca()== null || marcaProveedor.getMarca().getIdMarca()== 0 )
        {
            return false;
        }
        marcaProveedor.getProveedor().setIdProveedor(getInteger(request.getParameter( "proveedor" )) );
        if( marcaProveedor.getProveedor().getIdProveedor() == null || marcaProveedor.getProveedor().getIdProveedor() == 0 )
        {
            return false;
        }
        return new MarcaProveedorService().addProducto(marcaProveedor);
    }
    
    public Integer getInteger( String campo )
    {
        Integer val = 0;
        if( campo == null || campo.length() == 0 )
        {
            return null;
        }
        try
        {
            val = new Integer(campo);
            return val;
        }
        catch(NumberFormatException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Producto> getList()
    {
        if( list == null || list.size( )== 0 )
        {
            if( !loadList( ) )
            {
                return null;
            }
        }
        return list;
    }
     public boolean deleteProducto( HttpServletRequest request )
    {
        producto = new Producto( ); 
        producto.setIdProducto( getInteger( request.getParameter( "id" )) );
        if( producto.getIdProducto()== null )
        {
            return false;
        }
        return new MarcaProveedorService().deleteProducto( producto );
    }
    public boolean update( HttpServletRequest request )
    {
        producto = new Producto( new Marca( ) , new Proveedor( ) ); 
        producto.setNombre(request.getParameter( "nombre" ) );
        if( producto.getNombre() == null )
        {
            return false;
        }
        producto.setDescripcion(request.getParameter( "descripcion" ) );
        if( producto.getDescripcion()== null || producto.getDescripcion().length() == 0 )
        {
            return false;
        }
        producto.setUrl(request.getParameter( "url" ) );
        if( producto.getUrl()== null || producto.getUrl().length() == 0 )
        {
            return false;
        }
        producto.setPrecioPub(getInteger(request.getParameter( "preciopub" ) ) );
        if( producto.getPrecioPub()== null )
        {
            return false;
        }
        producto.setCosto(getInteger(request.getParameter( "costo" ) ) );
        if( producto.getCosto()== null )
        {
            return false;
        }
        producto.getMarca().setIdMarca(getInteger(request.getParameter( "marca" )) );
        if( producto.getMarca().getIdMarca()== null || producto.getMarca().getIdMarca()== 0 )
        {
            return false;
        }
        producto.getProveedor().setIdProveedor(getInteger(request.getParameter( "proveedor" )) );
        if( producto.getProveedor().getIdProveedor() == null || producto.getProveedor().getIdProveedor() == 0 )
        {
            return false;
        }
        producto.setIdProducto(getInteger(request.getParameter("id") ));
        if( producto.getProveedor().getIdProveedor() == null || producto.getProveedor().getIdProveedor() == 0 )
        {
            return false;
        }
        return new MarcaProveedorService().updateProducto( producto );
    }
    public Producto getProductoById( HttpServletRequest request )
    {
        Producto producto = null;
        Integer id = null;
        id = getInteger( request.getParameter( "id" ) );
        if( id == null )
        {
            return null;
        }
        return new MarcaProveedorService().getProductoById(id);
    }

    public void setList(List<Producto> list) 
    {
        this.list = list;
    }

    public Producto getMarcaProveedor() 
    {
        return marcaProveedor;
    }

    public void setMarcaProveedor(Producto marcaProveedor) 
    {
        this.marcaProveedor = marcaProveedor;
    }
    
}

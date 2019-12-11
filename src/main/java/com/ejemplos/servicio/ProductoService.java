/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplos.servicio;

import com.ejemplo.response.Respuesta;
import com.ejemplos.dao.ProductoDao;
import com.ejemplos.modelo.Producto;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author BEMULAP
 */
@Path("/productos")
public class ProductoService {
    
    private static List<Producto> listaProductos = ProductoDao.getProductos();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response gerProductos(){
       return Response.ok(listaProductos).build();
    }
    
    
    @GET
    @Path("/{productoId}")
    @Produces(MediaType.APPLICATION_JSON)    
    public Response gerProductosById(@PathParam("productoId") int productoId){
        
        Optional<Producto> prod = listaProductos.stream().filter(p -> p.getId() == productoId).findFirst();

        if(prod.isPresent()){        
            return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No se encontró el producto", new Producto(productoId, "",0))).build();
        }
       return Response.ok(new Respuesta("Producto encontrado", prod)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarProducto(Producto producto){
        
        int maxProductId = listaProductos.stream().mapToInt(p -> p.getId()).max().getAsInt();
        producto.setId(maxProductId + 1);
        listaProductos.add(producto);
        return Response.ok(new Respuesta("Producto Agregado", producto)).build();
    }
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)    
    public Response actualizaProducto(Producto producto){
        Optional<Producto> prod = listaProductos.stream().filter(p -> p.getId() == producto.getId()).findFirst();
        
        if(prod.isPresent()){
            prod.get().setNombre(producto.getNombre());
            prod.get().setPrecio(producto.getPrecio());
            return Response.ok(new Respuesta("Producto Actualizado", producto)).build();                    
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No se encontró el producto", producto)).build();
        }
        
    }
    
    
    @DELETE
    @Path("/{productoId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@PathParam("productoId") int productoId){
        Optional<Producto> prod = listaProductos.stream().filter(p -> p.getId() == productoId).findFirst();
        
        if(prod.isPresent()){
            listaProductos.remove(prod.get());
            return Response.ok(new Respuesta("Producto Eliminado", productoId)).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No se encontró el producto", null)).build();
        }        
    }
    
}
    


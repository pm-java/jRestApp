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
import javax.ws.rs.GET;
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
        
        // Producto prod = listaProductos.stream().filter(p -> p.getId() == productoId).findFirst().get();        
        Producto prod = listaProductos.get(0);
        if(Objects.isNull(prod)){        
            return Response.status(Response.Status.NOT_FOUND).entity(new Respuesta("No se encontró el producto")).build();
        }
       return Response.ok(new Respuesta("Producto encontrado", prod)).build();
    }
}

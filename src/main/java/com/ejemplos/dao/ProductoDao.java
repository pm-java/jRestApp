/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplos.dao;

import com.ejemplos.modelo.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BEMULAP
 */
public class ProductoDao {
    
    public static List<Producto> getProductos(){
        
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Azucar", 8));
        productos.add(new Producto(2, "Manteca", 14));
        productos.add(new Producto(3, "Harina", 21));
        productos.add(new Producto(4, "Frijol", 28));
        productos.add(new Producto(5, "Arroz", 7));
        return productos;
    }
    
}

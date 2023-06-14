package com.proyecto.glosajeweb.Controller;

import com.proyecto.glosajeweb.Model.Categorias;
import com.proyecto.glosajeweb.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.List;

@RestController
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Aquí puedes personalizar la lógica de manejo de la excepción según tus necesidades
        String errorMessage = "Debes enviar una categoria obligatoriamente";
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> handleConnectException(ConnectException ex) {
        // Manejo personalizado para la excepción ConnectException
        String errorMessage = "Error de conexión: no se pudo establecer la conexión.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

 /*
  @GetMapping(value = "/")
    public String holaMundo(){
        return "Hola mundo";
    } */


    @GetMapping(value = "/categorias")
    public List<Categorias> getTasks(){
        return categoriaRepository.findAll();
    }

    @PostMapping(value = "/guardarCategoria")
    public String saveTask(@RequestBody Categorias categoria){

        if(categoria == null){
            return "No hay información para crear la nueva tarea";

        }
        if(categoria.getNombre_categoria() == null ||  categoria.getNombre_categoria().isEmpty() ){
            return "No se permiten campos vacíos o nulos para añadir una nueva categoria";
        }
        categoriaRepository.save(categoria);
        return  "Categoria Guardada";
    }

    @PutMapping(value = "/actualizar/{codCategoria}")
    public String actualizarCategoria(@PathVariable long codCategoria, @RequestBody Categorias categoria){
        Categorias categoriaActualizada = null;

        try{
            categoriaActualizada = categoriaRepository.findById(codCategoria).get();
        }catch (Exception e){
            return "NO SE PUEDE ACTUALIZAR LA CATEGORIA CON EL ID " + codCategoria;
        }

        if(categoria.getNombre_categoria() == null ||  categoria.getNombre_categoria().isEmpty() ){
            return "No se permiten campos vacíos o nulos";
        }

        categoriaActualizada.setNombre_categoria(categoria.getNombre_categoria());
        categoriaRepository.save(categoriaActualizada);
        return  "Categoria actualizada";
    }

    @DeleteMapping(value = "/eliminar/{codCategoria}")
    public String eliminarCategoria(@PathVariable long codCategoria){
        Categorias categoriaEliminada = null;

        try{
           categoriaEliminada = categoriaRepository.findById(codCategoria).get();
        }catch (Exception e){
            return "NO SE PUEDE ELIMINAR LA CATEGORIA CON EL ID " + codCategoria;
        }

        categoriaRepository.delete(categoriaEliminada);
        return  "Categoria Eliminada";
    }
}

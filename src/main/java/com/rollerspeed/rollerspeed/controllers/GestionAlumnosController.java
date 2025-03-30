package com.rollerspeed.rollerspeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.GestionAlumnos;
import com.rollerspeed.rollerspeed.service.GestionAlumnosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gestion Alumnos", description = "Operaciones sobre la gestion alumnos")
@Controller
@RequestMapping("/")
public class GestionAlumnosController {
    @Autowired
    private GestionAlumnosService objGestionAlumnosService;




       @Operation(
        summary = "Obtener todos los alumnos",
        description = "Devuelve una lista de alumnos registrados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                content = @Content(schema = @Schema(implementation = GestionAlumnos.class))),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    // Mostrar lista de registro de aspirantes
    @GetMapping("/viewGestionAlumnos")
    public String showViewGestionAlumnos(Model objModel) {
        // Obtener la lista de registros de aspirantes desde el servicio
        List<GestionAlumnos> listaGestionAlumnos = objGestionAlumnosService.findAll();

        // Agregar la lista al modelo
        objModel.addAttribute("listGestionAlumnos", listaGestionAlumnos);


        // Retornar el nombre de la vista
        return "viewGestionAlumnos";
    }


    
       // Mostrar formulario de registro de aspirantes
       @GetMapping("/viewFormGestionAlumnos")
       public String showViewFormGestionAlumnos(Model objModel) {
              objModel.addAttribute("gestionAlumnos", new GestionAlumnos());
              objModel.addAttribute("action", "/GestionAlumnos/create");
              return "viewFormGestionAlumnos";
       }
       @Operation(
        summary = "Eliminar un alumno",
        description = "Elimina un registro de alumno por su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Alumno eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    ) 
       // Eliminar registro de aspirantes
       @GetMapping("/GestionAlumnos/delete/{id}")
       public String deleteAspitante(@PathVariable Long id) {
              this.objGestionAlumnosService.delete(id);
              return "redirect:/viewGestionAlumnos";
       }

       @Operation(
        summary = "Mostrar formulario de edición",
        description = "Muestra el formulario para actualizar los datos de un alumno.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Formulario de actualización mostrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    ) 
       //Actualizar registro de aspirantes
       @GetMapping("/GestionAlumnos/update/{id}")
       public String showFormUpdate(@PathVariable Long id, Model objModel) {
              GestionAlumnos objGestionAlumnos = this.objGestionAlumnosService.findById(id);
              objModel.addAttribute("gestionAlumnos", objGestionAlumnos);
              objModel.addAttribute("action", "/GestionAlumnos/update/" + id);
              return "viewFormGestionAlumnos";
       }
       @Operation(
        summary = "Actualizar datos de un alumno",
        description = "Modifica los datos de un alumno existente en la base de datos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Alumno actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    ) 
       @PostMapping("/GestionAlumnos/update/{id}")
       public String postMethodName(@PathVariable Long id, @ModelAttribute GestionAlumnos objGestionAlumnos) {
              this.objGestionAlumnosService.update(id, objGestionAlumnos);
              return "redirect:/viewGestionAlumnos";
       }
       
        
       // Crear registro de aspirantes
       @PostMapping(value = "/GestionAlumnos/create",consumes = "application/x-www-form-urlencoded")
       public String createGestionAlumnos(@ModelAttribute GestionAlumnos objGestionAlumnos) {
              this.objGestionAlumnosService.save(objGestionAlumnos);
              return "redirect:/viewGestionAlumnos";
       }

@Operation(
        summary = "Registrar un nuevo alumno",
        description = "Guarda un nuevo alumno en la base de datos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Alumno registrado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )

       @PostMapping(value = "/GestionAlumnos/create",consumes = "application/json" )
       public String createGestionAlumnosJson(@RequestBody GestionAlumnos objGestionAlumnos) {
              this.objGestionAlumnosService.save(objGestionAlumnos);
              return "redirect:/viewGestionAlumnos";
       }
}

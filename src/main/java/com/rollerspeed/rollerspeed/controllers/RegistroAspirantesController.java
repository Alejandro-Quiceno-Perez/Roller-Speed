package com.rollerspeed.rollerspeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;
import com.rollerspeed.rollerspeed.service.RegistroAspirantesService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Registro Aspirantes", description = "Operaciones sobre el registro de aspirantes")
@Controller
@RequestMapping("/")
public class RegistroAspirantesController {
       @Autowired
       private RegistroAspirantesService objRegistroAspirantesService;

       @Operation(summary = "Obtener todos los aspirantes", description = "Devuelve una lista de aspirantes registrados en un txt.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion aspirantes", content = @Content(schema = @Schema(implementation = RegistroAspirantes.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Mostrar lista de registro de aspirantes
       @GetMapping("/viewRegistroAspirante")
       public String showViewRegistroAspirante(Model objModel) {
              // Obtener la lista de registros de aspirantes desde el servicio
              List<RegistroAspirantes> listaRegistroAspirantes = objRegistroAspirantesService.findAll();

              // Agregar la lista al modelo
              objModel.addAttribute("listRegistroAspirantes", listaRegistroAspirantes);

              // Retornar el nombre de la vista
              return "viewRegistroAspirantes";
       }

       @Operation(summary = "Guardar registro de aspirantes", description = "Realiza la creacion del registro en base de datos.", responses = {
                     @ApiResponse(responseCode = "200", description = "Guardo correctamente.", content = @Content(schema = @Schema(implementation = RegistroAspirantes.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Mostrar formulario de registro de aspirantes
       @GetMapping("/viewFormRegistroAspirantes")
       public String showViewFormRegistroAspirante(Model objModel) {
              objModel.addAttribute("registroAspirantes", new RegistroAspirantes());
              objModel.addAttribute("action", "/registroAspirantes/create");
              return "viewFormRegistroAspirantes";
       }

       @Operation(summary = "Eliminar regsitro de aspirantes.", description = "Devuelve id del regitro eliminado.", responses = {
                     @ApiResponse(responseCode = "200", description = "Eliminar de la gestion aspirantes", content = @Content(schema = @Schema(implementation = RegistroAspirantes.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Eliminar registro de aspirantes
       @GetMapping("/registroAspirantes/delete/{id}")
       public String deleteAspitante(@PathVariable Long id) {
              this.objRegistroAspirantesService.delete(id);
              return "redirect:/viewRegistroAspirante";
       }

       // Actualizar registro de aspirantes
       @GetMapping("/registroAspirantes/update/{id}")
       public String showFormUpdate(@PathVariable Long id, Model objModel) {
              RegistroAspirantes objRegistroAspirantes = this.objRegistroAspirantesService.findById(id);
              objModel.addAttribute("registroAspirantes", objRegistroAspirantes);
              objModel.addAttribute("action", "/registroAspirantes/update/" + id);
              return "viewFormRegistroAspirantes";
       }

       @PostMapping("/registroAspirantes/update/{id}")
       public String postMethodName(@PathVariable Long id, @ModelAttribute RegistroAspirantes objRegistroAspirantes) {
              this.objRegistroAspirantesService.update(id, objRegistroAspirantes);
              return "redirect:/viewRegistroAspirante";
       }

       @Operation(summary = "Guardar registro de aspirantes", description = "Realiza la creacion del registro en base de datos.", responses = {
                     @ApiResponse(responseCode = "200", description = "Guardo correctamente.", content = @Content(schema = @Schema(implementation = RegistroAspirantes.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Crear registro de aspirantes
       @PostMapping("/registroAspirantes/create")
       public String createRegistroAspirante(@ModelAttribute RegistroAspirantes objRegistroAspirantes) {
              this.objRegistroAspirantesService.save(objRegistroAspirantes);
              return "redirect:/viewRegistroAspirante";
       }

}

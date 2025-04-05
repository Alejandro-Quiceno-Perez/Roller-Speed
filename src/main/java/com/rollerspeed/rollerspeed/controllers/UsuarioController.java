package com.rollerspeed.rollerspeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.Usuario;
import com.rollerspeed.rollerspeed.service.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Registro Aspirantes", description = "Operaciones sobre el registro de aspirantes")
@Controller
@RequestMapping("/")
public class UsuarioController {
       @Autowired
       private UsuarioService objUsuarioService;

       @Operation(summary = "Obtener todos los aspirantes", description = "Devuelve una lista de aspirantes registrados en un txt.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion aspirantes", content = @Content(schema = @Schema(implementation = Usuario.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Mostrar lista de registro de aspirantes
       
       @GetMapping("/viewUsuarios")
       public String showViewRegistroAspirante(Model objModel) {
              // Obtener la lista de registros de aspirantes desde el servicio
              List<Usuario> listaUsuarios = objUsuarioService.findAll();

              // Agregar la lista al modelo
              objModel.addAttribute("listUser", listaUsuarios);

              // Retornar el nombre de la vista
              return "viewUsuarios";
       }

       // Mostrar formulario de registro de aspirantes
       
       @GetMapping("/viewFormUsuarios")
       public String showViewFormRegistroAspirante(Model objModel) {
              objModel.addAttribute("registroUsuarios", new Usuario());
              objModel.addAttribute("action", "/registroUsuarios/create");
              return "viewFormUsuarios";
       }

       @Operation(summary = "Eliminar regsitro de aspirantes.", description = "Devuelve id del regitro eliminado.", responses = {
                     @ApiResponse(responseCode = "200", description = "Eliminar de la gestion aspirantes", content = @Content(schema = @Schema(implementation = Usuario.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       
       // Eliminar registro de aspirantes
       @GetMapping("/registroUsuarios/delete/{id}")
       public String deleteAspitante(@PathVariable Long id) {
              this.objUsuarioService.delete(id);
              return "redirect:/viewUsuarios";
       }
       
       // Actualizar registro de aspirantes
       @GetMapping("/registroUsuarios/update/{id}")
       public String showFormUpdate(@PathVariable Long id, Model objModel) {
              Usuario objUsuarios = this.objUsuarioService.findById(id);
              objModel.addAttribute("registroUsuarios", objUsuarios);
              objModel.addAttribute("action", "/registroUsuarios/update/" + id);
              return "viewFormUsuarios";
       }

       
       @PostMapping(value = "/registroUsuarios/update/{id}", consumes = "application/x-www-form-urlencoded")
       public String postMethodName(@PathVariable Long id, @ModelAttribute Usuario objUsuario) {
              this.objUsuarioService.update(id, objUsuario);
              return "redirect:/viewUsuarios";
       }

       @Operation(summary = "Actualizar los aspirantes", description = "Devuelve aspirante registrado para actualizarlo.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion aspirantes", content = @Content(schema = @Schema(implementation = Usuario.class))),

                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       
       @PostMapping(value = "/registroUsuarios/update/{id}", consumes = "application/json")
       public String postMethodNameJson(@PathVariable Long id, @RequestBody Usuario objUsuario) {
              this.objUsuarioService.update(id, objUsuario);
              return "redirect:/viewUsuarios";
       }

       // Crear registro de aspirantes
       
       @PostMapping(value = "/registroUsuarios/create", consumes = "application/x-www-form-urlencoded")
       public String createRegistroAspiranteForm(@ModelAttribute Usuario objUsuario) {
              this.objUsuarioService.save(objUsuario);
              System.out.println("Datos recibidos desde formulario HTML: " + objUsuario);
              return "redirect:/viewUsuarios";
       }

       @Operation(summary = "Crear los aspirantes", description = "Devuelve una lista de aspirantes registrados en un txt.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion aspirantes", content = @Content(schema = @Schema(implementation = Usuario.class))),

                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       
       @PostMapping(value = "/registroUsuarios/create", consumes = "application/json")
       public String createRegistroAspiranteJson(@RequestBody Usuario objUsuario) {
              this.objUsuarioService.save(objUsuario);
              System.out.println("Datos recibidos en JSON: " + objUsuario);
              return "redirect:/viewUsuarios";
       }

}

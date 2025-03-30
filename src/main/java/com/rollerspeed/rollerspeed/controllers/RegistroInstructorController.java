package com.rollerspeed.rollerspeed.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.RegistroInstructor;
import com.rollerspeed.rollerspeed.service.RegistroInstructorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@RequestMapping("/")

public class RegistroInstructorController {

       @Autowired

       private RegistroInstructorService objRegistroInstructorService;

       @Operation(summary = "Obtener todos los instructores", description = "Devuelve una lista de instructores registrados en un txt.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion instructores", content = @Content(schema = @Schema(implementation = RegistroInstructor.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Mostrar lista de registro de instructores
       @GetMapping("/viewRegistroInstructor")
       public String showViewRegistroInstructor(Model objModel) {
              List<RegistroInstructor> listaInstructor = objRegistroInstructorService.findAll();
              objModel.addAttribute("listaInstructor", listaInstructor);
              return "viewRegistroInstructor";
       }

       // Mostrar formulario de registro del instructor
       @GetMapping("/viewFormRegistroInstructor")
       public String showViewFormRegistroInstructor(Model objModel) {
              objModel.addAttribute("registroInstructor", new RegistroInstructor());
              objModel.addAttribute("action", "/registroInstructor/create");
              return "viewFormRegistroInstructor";
       }

       @Operation(summary = "Eliminar los instructores", description = "Devuelve una lista de instructores eliminados en un txt.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion instructores", content = @Content(schema = @Schema(implementation = RegistroInstructor.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })

       // Eliminar registro del instructor
       @GetMapping("/registroInstructor/delete/{id}")
       public String deleteInstructor(@PathVariable Long id) {
              this.objRegistroInstructorService.delete(id);
              return "redirect:/viewRegistroInstructor";
       }

       // Actualizar registro de un instructor
       @GetMapping("/registroInstructor/update/{id}")
       public String showFormUpdate(@PathVariable Long id, Model objModel) {
              RegistroInstructor objRegistroInstructor = this.objRegistroInstructorService.findById(id);
              objModel.addAttribute("registroInstructor", objRegistroInstructor);
              objModel.addAttribute("action", "/registroInstructor/update/" + id);
              return "viewFormRegistroInstructor";
       }

       @PostMapping("/registroInstructor/update/{id}")
       public String updateRegistroInstructor(@PathVariable Long id,
                     @ModelAttribute RegistroInstructor objRegistroInstructor) {
              this.objRegistroInstructorService.update(id, objRegistroInstructor);
              return "redirect:/viewRegistroInstructor";

       }

       // Crear registro de instructores
       @PostMapping(value= "/registroInstructor/create", consumes = "application/x-www-form-urlencoded")
       public String createRegistroInstructor(@ModelAttribute RegistroInstructor objRegistroInstructor) {
              this.objRegistroInstructorService.save(objRegistroInstructor);
              return "redirect:/viewRegistroInstructor";
       }

       @Operation(summary = "Crear regirtro de los instructores", description = "Devuelve una lista de instructores registrados en un txt.", responses = {
                     @ApiResponse(responseCode = "200", description = "Lista la gestion instructores", content = @Content(schema = @Schema(implementation = RegistroInstructor.class))),
                     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
       })
       // Crear registro de instructores
       @PostMapping(value= "/registroInstructor/create", consumes = "application/json")
       public String createRegistroInstructorJson(@RequestBody RegistroInstructor objRegistroInstructor) {
              this.objRegistroInstructorService.save(objRegistroInstructor);
              return "redirect:/viewRegistroInstructor";
       }
}

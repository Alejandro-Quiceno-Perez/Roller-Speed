package com.rollerspeed.rollerspeed.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.RegistroAsistencia;
import com.rollerspeed.rollerspeed.service.RegistroAsistenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Registro de Asistencia", description = "Operaciones sobre el registro de asistencia")
@Controller
@RequestMapping("/")
public class RegistroAsistenciaController {
    @Autowired
       private RegistroAsistenciaService obRegistroAsistenciaService;

           @Operation(
        summary = "Obtener todos los registros de asistencia",
        description = "Devuelve una lista de registros de asistencia.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de registros de asistencia",
                content = @Content(schema = @Schema(implementation = RegistroAsistencia.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
       // Mostrar lista de registro de aspirantes
       @GetMapping("/viewRegistroAsistencia")
       public String showViewRegistroAsistencia(Model objModel) {
              // Obtener la lista de registros de aspirantes desde el servicio
              List<RegistroAsistencia> listaRegistroAsistencia = obRegistroAsistenciaService.findAll();

              // Agregar la lista al modelo
              objModel.addAttribute("listRegistroAsistencia", listaRegistroAsistencia);

              // Retornar el nombre de la vista
              return "viewRegistroAsistencia";
       }
       @Operation(
              summary = "Mostrar formulario de registro de asistencia",
              description = "Muestra un formulario para registrar asistencia.",
              responses = {
                  @ApiResponse(responseCode = "200", description = "Formulario mostrado correctamente")
              }
          )
       // Mostrar formulario de registro de aspirantes
       @GetMapping("/viewFormRegistroAsistencia")
       public String showViewFormRegistroAsistencia(Model objModel) {
              objModel.addAttribute("registroAsistencia", new RegistroAsistencia());
              objModel.addAttribute("action", "/RegistroAsistencia/create");
              return "viewFormRegistroAsistencia";
       }
       @Operation(
              summary = "Eliminar un registro de asistencia",
              description = "Elimina un registro de asistencia por ID.",
              responses = {
                  @ApiResponse(responseCode = "200", description = "Registro eliminado correctamente"),
                  @ApiResponse(responseCode = "404", description = "Registro no encontrado")
              }
          )
       // Eliminar registro de aspirantes
       @GetMapping("/RegistroAsistencia/delete/{idRegistroAsistencia}")
       public String deleteAspitante(@PathVariable Long idRegistroAsistencia) {
              this.obRegistroAsistenciaService.delete(idRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }
       @Operation(
              summary = "Mostrar formulario de actualización de asistencia",
              description = "Muestra un formulario para actualizar un registro de asistencia existente.",
              responses = {
                  @ApiResponse(responseCode = "200", description = "Formulario mostrado correctamente"),
                  @ApiResponse(responseCode = "404", description = "Registro no encontrado")
              }
          )
       //Actualizar registro de aspirantes
       @GetMapping("/RegistroAsistencia/update/{idRegistroAsistencia}")
       public String showFormUpdate(@PathVariable Long idRegistroAsistencia, Model objModel) {
              RegistroAsistencia obRegistroAsistencia = this.obRegistroAsistenciaService.findById(idRegistroAsistencia);
              objModel.addAttribute("registroAsistencia", obRegistroAsistencia);
              objModel.addAttribute("action", "/RegistroAsistencia/update/" + idRegistroAsistencia);
              return "viewFormRegistroAsistencia";
       }
       @Operation(
              summary = "Actualizar un registro de asistencia",
              description = "Actualiza un registro de asistencia por ID.",
              responses = {
                  @ApiResponse(responseCode = "200", description = "Registro actualizado correctamente"),
                  @ApiResponse(responseCode = "404", description = "Registro no encontrado")
              }
          )
       @PostMapping("/RegistroAsistencia/update/{idRegistroAsistencia}")
       public String postMethodName(@PathVariable Long idRegistroAsistencia, @ModelAttribute RegistroAsistencia objRegistroAsistencia) {
              this.obRegistroAsistenciaService.update(idRegistroAsistencia, objRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }
       
       
       // Crear registro de aspirantes
       @PostMapping(value = "/RegistroAsistencia/create", consumes = "application/x-www-form-urlencoded")
       public String createRegistroAsistencia(@ModelAttribute RegistroAsistencia objRegistroAsistencia) {
              this.obRegistroAsistenciaService.save(objRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }


@Operation(
              summary = "Crear un nuevo registro de asistencia",
              description = "Registra un nuevo registro de asistencia en la base de datos.",
              responses = {
                  @ApiResponse(responseCode = "201", description = "Registro creado correctamente"),
                  @ApiResponse(responseCode = "500", description = "Error interno del servidor")
              }
          )

       @PostMapping(value = "/RegistroAsistencia/create", consumes = "application/json")
       public String createRegistroAsistenciaJson(@RequestBody RegistroAsistencia objRegistroAsistencia) {
              this.obRegistroAsistenciaService.save(objRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }
}

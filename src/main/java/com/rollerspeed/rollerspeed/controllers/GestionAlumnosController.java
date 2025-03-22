package com.rollerspeed.rollerspeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.GestionAlumnos;
import com.rollerspeed.rollerspeed.service.GestionAlumnosService;

@Controller
@RequestMapping("/")
public class GestionAlumnosController {
    @Autowired
       private GestionAlumnosService objGestionAlumnosService;

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

       // Eliminar registro de aspirantes
       @GetMapping("/GestionAlumnos/delete/{id}")
       public String deleteAspitante(@PathVariable Long id) {
              this.objGestionAlumnosService.delete(id);
              return "redirect:/viewGestionAlumnos";
       }

       //Actualizar registro de aspirantes
       @GetMapping("/GestionAlumnos/update/{id}")
       public String showFormUpdate(@PathVariable Long id, Model objModel) {
              GestionAlumnos objGestionAlumnos = this.objGestionAlumnosService.findById(id);
              objModel.addAttribute("gestionAlumnos", objGestionAlumnos);
              objModel.addAttribute("action", "/GestionAlumnos/update/" + id);
              return "viewFormGestionAlumnos";
       }

       @PostMapping("/GestionAlumnos/update/{id}")
       public String postMethodName(@PathVariable Long id, @ModelAttribute GestionAlumnos objGestionAlumnos) {
              this.objGestionAlumnosService.update(id, objGestionAlumnos);
              return "redirect:/viewGestionAlumnos";
       }
       

       // Crear registro de aspirantes
       @PostMapping("/GestionAlumnos/create")
       public String createGestionAlumnos(@ModelAttribute GestionAlumnos objGestionAlumnos) {
              this.objGestionAlumnosService.save(objGestionAlumnos);
              return "redirect:/viewGestionAlumnos";
       }
}

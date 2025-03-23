package com.rollerspeed.rollerspeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;
import com.rollerspeed.rollerspeed.service.RegistroAspirantesService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class RegistroAspirantesController {
       @Autowired
       private RegistroAspirantesService objRegistroAspirantesService;

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

       // Mostrar formulario de registro de aspirantes
       @GetMapping("/viewFormRegistroAspirantes")
       public String showViewFormRegistroAspirante(Model objModel) {
              objModel.addAttribute("registroAspirantes", new RegistroAspirantes());
              objModel.addAttribute("action", "/registroAspirantes/create");
              return "viewFormRegistroAspirantes";
       }

       // Eliminar registro de aspirantes
       @GetMapping("/registroAspirantes/delete/{id}")
       public String deleteAspitante(@PathVariable Long id) {
              this.objRegistroAspirantesService.delete(id);
              return "redirect:/viewRegistroAspirante";
       }

       //Actualizar registro de aspirantes
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
       

       // Crear registro de aspirantes
       @PostMapping("/registroAspirantes/create")
       public String createRegistroAspirante(@ModelAttribute RegistroAspirantes objRegistroAspirantes) {
              this.objRegistroAspirantesService.save(objRegistroAspirantes);
              return "redirect:/viewRegistroAspirante";
       }

}

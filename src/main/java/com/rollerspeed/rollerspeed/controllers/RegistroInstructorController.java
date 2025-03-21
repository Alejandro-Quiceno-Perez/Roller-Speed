package com.rollerspeed.rollerspeed.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.rollerspeed.rollerspeed.entity.RegistroInstructor.RegistroInstructor;
import com.rollerspeed.rollerspeed.service.RegistroInstructorService;

@Controller
@RequestMapping("/")

public class RegistroInstructorController {

       @Autowired

       private RegistroInstructorService objRegistroInstructorService;


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
        @PostMapping("/registroInstructor/create")
        public String createRegistroInstructor(@ModelAttribute RegistroInstructor objRegistroInstructor) {
               this.objRegistroInstructorService.save(objRegistroInstructor);
               return "redirect:/viewRegistroInstructor";
        }
}
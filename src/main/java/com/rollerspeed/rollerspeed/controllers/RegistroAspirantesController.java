package com.rollerspeed.rollerspeed.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;
import com.rollerspeed.rollerspeed.service.RegistroAspirantesService;

@Controller
@RequestMapping("/")
public class RegistroAspirantesController {
       @Autowired
       private RegistroAspirantesService objRegistroAspirantesService;


// Mostrar lista de registro de aspirantes
       @GetMapping("/viewRegistroAspirante")
       public String showViewRegistroAspirante(Model objModel) {
              objModel.addAttribute("registroAspirantes", new RegistroAspirantes());
              return "viewRegistroAspirantes";
       }

       // Mostrar formulario de registro de aspirantes
       @GetMapping("/viewFormRegistroAspirantes")
       public String showViewFormRegistroAspirante(Model objModel) {
              objModel.addAttribute("registroAspirantes", new RegistroAspirantes());
              objModel.addAttribute("action", "/registroAspirantes/create");
              return "viewFormRegistroAspirantes";
       }

       @PostMapping("/registroAspirantes/create")
       public String createRegistroAspirante(@ModelAttribute RegistroAspirantes objRegistroAspirantes) {
              this.objRegistroAspirantesService.save(objRegistroAspirantes);
              return "redirect:/viewRegistroAspirantes";
       }

}

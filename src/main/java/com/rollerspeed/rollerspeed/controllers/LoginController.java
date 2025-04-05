package com.rollerspeed.rollerspeed.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
       @GetMapping("/viewLogin")
       public String login() {
              return "index"; // Retorna la vista de inicio de sesión
       }

       // @GetMapping("/logout")
       // public String logout() {
       //        return "login"; // Redirige a la página de inicio de sesión después de cerrar sesión
       // }
}

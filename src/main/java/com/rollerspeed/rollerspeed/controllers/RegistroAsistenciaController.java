package com.rollerspeed.rollerspeed.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.rollerspeed.rollerspeed.entity.RegistroAsistencia;
import com.rollerspeed.rollerspeed.service.RegistroAsistenciaService;

@Controller
@RequestMapping("/")
public class RegistroAsistenciaController {
    @Autowired
       private RegistroAsistenciaService obRegistroAsistenciaService;

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

       // Mostrar formulario de registro de aspirantes
       @GetMapping("/viewFormRegistroAsistencia")
       public String showViewFormRegistroAsistencia(Model objModel) {
              objModel.addAttribute("registroAsistencia", new RegistroAsistencia());
              objModel.addAttribute("action", "/RegistroAsistencia/create");
              return "viewFormRegistroAsistencia";
       }

       // Eliminar registro de aspirantes
       @GetMapping("/RegistroAsistencia/delete/{idRegistroAsistencia}")
       public String deleteAspitante(@PathVariable Long idRegistroAsistencia) {
              this.obRegistroAsistenciaService.delete(idRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }

       //Actualizar registro de aspirantes
       @GetMapping("/RegistroAsistencia/update/{idRegistroAsistencia}")
       public String showFormUpdate(@PathVariable Long idRegistroAsistencia, Model objModel) {
              RegistroAsistencia obRegistroAsistencia = this.obRegistroAsistenciaService.findById(idRegistroAsistencia);
              objModel.addAttribute("registroAsistencia", obRegistroAsistencia);
              objModel.addAttribute("action", "/RegistroAsistencia/update/" + idRegistroAsistencia);
              return "viewFormRegistroAsistencia";
       }

       @PostMapping("/RegistroAsistencia/update/{idRegistroAsistencia}")
       public String postMethodName(@PathVariable Long idRegistroAsistencia, @ModelAttribute RegistroAsistencia objRegistroAsistencia) {
              this.obRegistroAsistenciaService.update(idRegistroAsistencia, objRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }
       

       // Crear registro de aspirantes
       @PostMapping("/RegistroAsistencia/create")
       public String createRegistroAsistencia(@ModelAttribute RegistroAsistencia objRegistroAsistencia) {
              this.obRegistroAsistenciaService.save(objRegistroAsistencia);
              return "redirect:/viewRegistroAsistencia";
       }
}

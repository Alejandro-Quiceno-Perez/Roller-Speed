package com.rollerspeed.rollerspeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;

import com.rollerspeed.rollerspeed.entity.GestionClases;
// import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;
import com.rollerspeed.rollerspeed.service.GestionClasesService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/")
public class GestionClasesController {
    @Autowired
    private GestionClasesService objGestionClasesService;

    // Devuelve una lista con todos los objetos de tipo GestionClases de la base de datos
    @Operation(
        summary = "Listar una nueva gestión de clases",
        description = "Lista una nueva gestión de clases en la base de datos",
        responses ={
            @ApiResponse(responseCode = "200", description = "Clases registrada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al registrar la clase"),
        }
    )
    
    @GetMapping("/viewGestionClases")
    public String showViewGestionClases(Model objModel) {
        //Obtener la lista de gestión de clases desde el servicio
        List<GestionClases> listaGestionClases = objGestionClasesService.findAll();

        //Agregar la lista de gestión de clases al modelo
        objModel.addAttribute("listGestionClases", listaGestionClases);

        //Devolver el nombre de la vista
        return "viewGestionClases";
    }
    
    //Mostrar formulario de gestión de clases
    @GetMapping("/viewFormGestionClases")
    public String showViewFormGestionClases(Model objModel) {
        objModel.addAttribute("gestionClases", new GestionClases());
        objModel.addAttribute("action","/gestionClases/create");
        return "viewFormGestionClases";
    }
    
    //Eliminar una gestión de clases
    @Operation(
        summary = "Eliminar una nueva gestión de clases",
        description = "Elimina una nueva gestión de clases en la base de datos",
        responses ={
            @ApiResponse(responseCode = "200", description = "Clases registrada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al registrar la clase"),
        }
    )

    @GetMapping("/gestionClases/delete/{id}")
    public String deleteClase(@PathVariable Long id) {
            this.objGestionClasesService.delete(id);
            return "redirect:/viewGestionClases";
    }

    
     //Actualizar gestión de clases
    @GetMapping("/gestionClases/update/{id}")
    public String showFormUpdate(@PathVariable Long id, Model objModel) {
            GestionClases objGestionClases = this.objGestionClasesService.findById(id);
            objModel.addAttribute("gestionClases", objGestionClases);
            objModel.addAttribute("action", "/gestionClases/update/" + id);
            return "viewFormGestionClases";
    }

    @PostMapping("/gestionClases/update/{id}")
    public String postMethodName(@PathVariable Long id, @ModelAttribute GestionClases objGestionClases) {
            this.objGestionClasesService.update(id, objGestionClases);
            return "redirect:/viewGestionClases";
    }


    // Crea un objeto de tipo GestionClases en la base de datos
    @PostMapping(value = "/gestionClases/create", consumes = "application/x-www-form-urlencoded")
    public String createGestionClases(@ModelAttribute GestionClases objGestionClases) {
        this.objGestionClasesService.save(objGestionClases);
        return "redirect:/viewGestionClases";
    }

    @Operation(
        summary = "Crear una nueva gestión de clases",
        description = "Crea una nueva gestión de clases en la base de datos",
        responses ={
            @ApiResponse(responseCode = "200", description = "Clases registrada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al registrar la clase"),
        }
    )

    @PostMapping(value = "/gestionClases/create", consumes = "application/json")
    public String createGestionClasesJson(@RequestBody GestionClases objGestionClases) {
        this.objGestionClasesService.save(objGestionClases);
        return "redirect:/viewGestionClases";
    }
}


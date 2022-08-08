package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.Model;
import com.amdevelopment.NetCracker_Project.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/models")
@CrossOrigin(origins = "http://localhost:4200")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping("/getAllModels")
    public Iterable<Model> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping("/getModelById")
    @PreAuthorize("hasAuthority('Admin')")
    public Model getModelId(@RequestParam Integer id) {
        return modelService.getModelById(id);
    }

    @GetMapping("/getModelsFiltered")
    public Iterable<Model> getModelsFiltered(@RequestParam(required = false) String brand, @RequestParam(required = false) Integer year, @RequestParam(required = false) String type, @RequestParam(required = false) String model, @RequestParam(required = false) String color) {
        return modelService.getModelsFiltered(brand, year, type, model, color);
    }

    @PostMapping("/saveNewModel")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity saveNewModel(@RequestParam String name, @RequestParam Integer year, @RequestParam String brand, @RequestParam String color, @RequestParam String type, @RequestParam(required = false) String picture) {
        modelService.insertNewModel(name, year, brand, color, type, picture);
        return new ResponseEntity("Model created.",HttpStatus.CREATED);
    }

    @PutMapping("/updateModel")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity updateModel(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer year, @RequestParam String brand, @RequestParam String color, @RequestParam String type, @RequestParam(required = false) String picture) {
        modelService.updateModel(id, name, year, brand, color, type, picture);
        return new ResponseEntity("Update completed.",HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBrands")
    public Iterable<String> getBrands() {
        return modelService.getBrands();
    }

    @GetMapping("/getColors")
    public Iterable<String> getColors() {
        return modelService.getColors();
    }

    @GetMapping("/getTypes")
    public Iterable<String> getTypes() {
        return modelService.getTypes();
    }

    @GetMapping("/getYears")
    public Iterable<Integer> getYears() {
        return modelService.getYears();
    }
}

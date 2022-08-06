package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.Model;
import com.amdevelopment.NetCracker_Project.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Model getModelId(@RequestParam Integer id) {
        return modelService.getModelById(id);
    }

    @GetMapping("/getModelsFiltered")
    public Iterable<Model> getModelsFiltered(@RequestParam(required = false) String brand, @RequestParam(required = false) Integer year, @RequestParam(required = false) String type) {
        return modelService.getModelsFiltered(brand, year, type);
    }

    @PostMapping("/saveNewModel")
    public ResponseEntity saveNewModel(@RequestParam String name, @RequestParam Integer year, @RequestParam String brand, @RequestParam String color, @RequestParam String type, @RequestParam(required = false) String picture) {
        modelService.insertNewModel(name, year, brand, color, type, picture);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/updateModel")
    public ResponseEntity updateModel(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer year, @RequestParam String brand, @RequestParam String color, @RequestParam String type, @RequestParam(required = false) String picture) {
        modelService.updateModel(id, name, year, brand, color, type, picture);
        return new ResponseEntity(HttpStatus.ACCEPTED);
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

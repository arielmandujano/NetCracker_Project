package com.amdevelopment.NetCracker_Project.services;

import com.amdevelopment.NetCracker_Project.config.exceptions.BadRequestException;
import com.amdevelopment.NetCracker_Project.config.exceptions.DataBaseException;
import com.amdevelopment.NetCracker_Project.config.exceptions.NotFoundException;
import com.amdevelopment.NetCracker_Project.models.Model;
import com.amdevelopment.NetCracker_Project.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    public Iterable<Model> getAllModels() {
        return modelRepository.getAllModels();
    }

    public Model getModelById(Integer id) {
        if(id <= 0) {
            throw new BadRequestException("Model id cannot be 0 or negative.");
        }
        Model model = modelRepository.getModelById(id);
        if(model == null) {
            throw new NotFoundException("There is no model with id = " + id);
        }
        return model;
    }
    public Iterable<Model> getModelsFiltered(String brand, Integer year, String type) {
        return modelRepository.getModelsFiltered(brand, year, type);
    }

    public void insertNewModel(String name, Integer year, String brand, String color, String type, String picture) {
        if(name.equals("") || name == null) {
            throw new BadRequestException("Model name cannot be empty or be null.");
        }
        if(year < 1900 || year == null ) {
            throw new BadRequestException("Model year cannot be less than 1900 or cannot be null.");
        }
        if(color.equals("") || color == null) {
            throw new BadRequestException("Model color cannot be empty or be null.");
        }
        if(type.equals("") || type == null) {
            throw new BadRequestException("Model type cannot be empty or be null.");
        }
        try {
            modelRepository.insertNewModel(name, year, brand, color, type, picture);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while writing model information.");
        }


    }

    public void updateModel(Integer id, String name, Integer year, String brand, String color, String type, String picture) {
        if(id <= 0) {
            throw new BadRequestException("Model id cannot be 0 or negative.");
        }
        if(name.equals("") || name == null) {
            throw new BadRequestException("Model name cannot be empty or be null.");
        }
        if(year < 1900 || year == null ) {
            throw new BadRequestException("Model year cannot be less than 1900 or cannot be null.");
        }
        if(color.equals("") || color == null) {
            throw new BadRequestException("Model color cannot be empty or be null.");
        }
        if(type.equals("") || type == null) {
            throw new BadRequestException("Model type cannot be empty or be null.");
        }
        Model model = getModelById(id);
        if(model == null) {
            throw new NotFoundException("The model id must be the id of an existing model.");
        }
        try {
            modelRepository.updateModel(id, name, year, brand, color, type, picture);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while updating model information.");
        }
    }

    public Iterable<String> getBrands() {
        return modelRepository.getBrands();
    }

    public Iterable<String> getColors() {
        return modelRepository.getColors();
    }

    public Iterable<String> getTypes() {
        return modelRepository.getTypes();
    }

    public Iterable<Integer> getYears() {
        return modelRepository.getYears();
    }
}

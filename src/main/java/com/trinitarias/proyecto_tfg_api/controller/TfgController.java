package com.trinitarias.proyecto_tfg_api.controller;

import com.trinitarias.proyecto_tfg_api.dto.TfgDtos;
import com.trinitarias.proyecto_tfg_api.dto.TfgHistorialDto;
import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.service.TfgService;
import com.trinitarias.proyecto_tfg_api.validator.TfgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tfg")
@CrossOrigin(origins = "*")
public class TfgController {

    @Autowired
    private TfgService service;

    @Autowired
    private TfgValidator validator;

    /***
     * Cea el usuario
     * @param tfgUsuariosDto
     * @return
     */
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody TfgUsuariosDto tfgUsuariosDto, Errors error) {
        HttpStatus status = null;
        Object body = null;

        validator.validate(tfgUsuariosDto, error);
        if (!error.hasErrors()) {
            TfgUsuariosDto dto = service.crearUsuario(tfgUsuariosDto);
            if (tfgUsuariosDto != null) {
                status = HttpStatus.CREATED;
                body = dto;
            }else {
                status = HttpStatus.BAD_REQUEST;
                body = null;
            }
        }else{
            status = HttpStatus.BAD_REQUEST;
            body = error.getAllErrors();
        }
        return ResponseEntity.status(status).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUnUsuario(@PathVariable Long id){
        HttpStatus status = null;
        Object body = null;

        TfgUsuariosDto dto = service.findById(id);
        if (dto != null) {
            status = HttpStatus.OK;
            body = dto;
        }else{
            status = HttpStatus.NOT_FOUND;
            body = null;
        }
        return ResponseEntity.status(status).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody TfgUsuariosDto tfgUsuariosDto, Errors error) {
        HttpStatus status = null;
        Object body = null;

        validator.validate(tfgUsuariosDto, error);
        if (!error.hasErrors()) {
            TfgUsuariosDto dto = service.actualizarUsuario(id, tfgUsuariosDto);
            if (dto != null) {
                status = HttpStatus.OK;
                body = dto;
            } else{
                status = HttpStatus.NOT_FOUND;
                body = null;
            }
        }else{
            status = HttpStatus.NOT_FOUND;
            body = error.getAllErrors();
        }



        return ResponseEntity.status(status).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TfgUsuariosDto tfgUsuariosDto, Errors error){
        HttpStatus status = null;
        Object body = null;

        validator.validate(tfgUsuariosDto, error);
        if (!error.hasErrors()) {
            TfgUsuariosDto dto = service.login(tfgUsuariosDto);
            if (dto != null) {
                status = HttpStatus.OK;
                body = dto;
            }else {
                status = HttpStatus.BAD_REQUEST;
                body = null;
            }
        } else {
            status = HttpStatus.BAD_REQUEST;
            body = error.getAllErrors();
        }

        return ResponseEntity.status(status).body(body);
    }

    @PostMapping("/subirhistorial")
    public ResponseEntity<?> subirHistorial(@RequestBody TfgDtos tfgDtos){
        HttpStatus status = null;
        Object body = null;

        TfgHistorialDto dto = service.subirHistorial(tfgDtos.getTfgHistorialDto(), tfgDtos.getTfgUsuariosDto().getId_usuario());
        if (dto != null) {
            status = HttpStatus.OK;
            body = dto;
        } else{
            status = HttpStatus.BAD_REQUEST;
            body = null;
        }

        return ResponseEntity.status(status).body(body);
    }

    @PostMapping("/historial")
    public ResponseEntity<?> historial(@RequestBody TfgUsuariosDto tfgUsuariosDto){
        HttpStatus status = null;
        Object body = null;

        List<?> dtoH = service.obtenerHistorial(tfgUsuariosDto.getId_usuario());
        if (dtoH != null) {
            status = HttpStatus.OK;
            body = dtoH;
        } else{
            status = HttpStatus.BAD_REQUEST;
            body = null;
        }

        return ResponseEntity.status(status).body(body);
    }
}

package com.trinitarias.proyecto_tfg_api.controller;

import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.service.TfgService;
import com.trinitarias.proyecto_tfg_api.validator.TfgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tfg")
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
    public ResponseEntity<?> crearUsuario(@RequestBody TfgUsuariosDto tfgUsuariosDto) {
        HttpStatus status = null;
        Object body = null;


        TfgUsuariosDto dto = service.crearUsuario(tfgUsuariosDto);
        if (tfgUsuariosDto != null) {
            status = HttpStatus.CREATED;
            body = dto;
        } else {
            status = HttpStatus.BAD_REQUEST;
            body = null;
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
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody TfgUsuariosDto tfgUsuariosDto) {
        HttpStatus status = null;
        Object body = null;

        TfgUsuariosDto dto = service.actualizarUsuario(id, tfgUsuariosDto);
        if (dto != null) {
            status = HttpStatus.OK;
            body = dto;
        } else{
            status = HttpStatus.BAD_REQUEST;
            body = null;
        }

        return ResponseEntity.status(status).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TfgUsuariosDto tfgUsuariosDto){
        HttpStatus status = null;
        Object body = null;

        TfgUsuariosDto dto = service.login(tfgUsuariosDto);
        if (dto != null) {
            status = HttpStatus.OK;
            body = dto;
        } else{
            status = HttpStatus.BAD_REQUEST;
            body = null;
        }

        return ResponseEntity.status(status).body(body);
    }

}

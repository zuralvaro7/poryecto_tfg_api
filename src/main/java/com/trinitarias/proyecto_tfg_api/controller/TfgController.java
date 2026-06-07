package com.trinitarias.proyecto_tfg_api.controller;

import com.trinitarias.proyecto_tfg_api.constantes.TfgConstantes;
import com.trinitarias.proyecto_tfg_api.dto.TfgDtos;
import com.trinitarias.proyecto_tfg_api.dto.TfgHistorialDto;
import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.service.TfgEmailService;
import com.trinitarias.proyecto_tfg_api.service.TfgService;
import com.trinitarias.proyecto_tfg_api.validator.TfgValidator;
import com.trinitarias.proyecto_tfg_api.validator.TfgValidatorActualizar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tfg")
public class TfgController {

    @Autowired
    private TfgService service;

    @Autowired
    private TfgValidator validator;

    @Autowired
    private TfgValidatorActualizar validatorActualizar;

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
        tfgUsuariosDto.setId_usuario(id);
        validatorActualizar.validate(tfgUsuariosDto, error);
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

    @GetMapping("/verificar")
    public ResponseEntity<String> verificarUsuario(@RequestParam String token){
        HttpStatus status = null;
        String body = null;
        TfgUsuariosDto dto = service.findByToken(token);
        if(dto!=null){
            status = HttpStatus.OK;
            body = "Se ha autentificado correctamente, puedes cerrar esta pagina";
        }else{
            status = HttpStatus.NOT_FOUND;
            body = "No se ha podido autentificar vuelvalo ha intentar";
        }
        return ResponseEntity.status(status).body(body);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        service.eliminarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(TfgConstantes.ENDPOINT_LOGIN)
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

    @PostMapping(TfgConstantes.ENDPOINT_SUBIR_HISTORIAL)
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

    @GetMapping(TfgConstantes.ENDPOINT_HISTORIAL+"/{id}")
    public ResponseEntity<?> historial(@PathVariable Long id){
        HttpStatus status = null;
        Object body = null;
        TfgUsuariosDto dto = service.findById(id);
        List<?> dtoH = service.obtenerHistorial(id);
        if(dto.isActivo()) {
            if (dtoH != null) {
                status = HttpStatus.OK;
                body = dtoH;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        }else{
            status = HttpStatus.UNAUTHORIZED;
        }

        return ResponseEntity.status(status).body(body);
    }
}

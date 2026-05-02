package com.trinitarias.proyecto_tfg_api.validator;

import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.service.TfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TfgValidatorActualizar implements Validator {

    @Autowired
    TfgService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TfgUsuariosDto dto = (TfgUsuariosDto) target;

        if(dto.getNombre_usuario() == null || dto.getNombre_usuario().isBlank()) {
            errors.rejectValue("nombre_usuario","i18n.nombre_usuario.obligatorio" ,"El usuario es obligatorio");
        }

        if (dto.getEmail().isBlank()) {
            errors.rejectValue("email", "i18n.email.obligatorio", "El campo debe ser obligatorio");
        } else if (dto.getEmail().indexOf('@') < 0 || dto.getEmail().indexOf('.') < 0 || dto.getEmail().indexOf('@') > dto.getEmail().indexOf('.')) {
            errors.rejectValue("email", "i18n.email.formato", "El campo debe ser un email");
        } else if (service.buscarEmail(dto) != null && !service.findById(dto.getId_usuario()).getEmail().equals(dto.getEmail())) {
            errors.rejectValue("email", "i18n.email.existe", "El email ya existe");
        }
    }
}

package com.trinitarias.proyecto_tfg_api.validator;

import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.service.TfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TfgValidator implements Validator {

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
            errors.rejectValue("usuarios.usuario","i18n.usuario.obligatorio" ,"El usuario es obligatorio");
        }
        if(dto.getContrasena() == null || dto.getContrasena().isBlank()) {
            errors.rejectValue("usuarios.contrasena","i18n.contrasena.obligatorio" ,"La contraseña es obligatoria");
        }
        if(!dto.getNombre_usuario().isEmpty() && !dto.getContrasena().isEmpty()) {
            TfgUsuariosDto dtoU = service.login(dto);
            if(dtoU==null) {
                errors.rejectValue("usuarios.usuario","i18n.usuario.login" ,"El usuario y/o la contraseña son incorrectos");
            }
        }
        if (dto.getEmail().isBlank()) {
            errors.rejectValue("usuarios.email", "i18n.email.obligatorio", "El campo debe ser obligatorio");
        } else if(dto.getEmail().indexOf('@')<0 || dto.getEmail().indexOf('.')<0 || dto.getEmail().indexOf('@')>dto.getEmail().indexOf('.')) {
            errors.rejectValue("usuarios.email", "i18n.email.formato", "El campo debe ser un email");
        }
    }
}

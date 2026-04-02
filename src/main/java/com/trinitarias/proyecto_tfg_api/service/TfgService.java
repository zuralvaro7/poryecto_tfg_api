package com.trinitarias.proyecto_tfg_api.service;

import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.entity.TfgUsuariosEntity;
import com.trinitarias.proyecto_tfg_api.repository.TfgHistorialRepository;
import com.trinitarias.proyecto_tfg_api.repository.TfgUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TfgService {

    @Autowired
    private TfgUsuariosRepository tfgUsuariosRepository;

    @Autowired
    private TfgHistorialRepository tfgHistorialRepository;

    public TfgUsuariosDto crearUsuario(TfgUsuariosDto dto){
        TfgUsuariosEntity entity = tfgUsuariosRepository.save(transformFromDtoToEntity(dto));
        return transformFromEntityToDto(entity);
    }

    public TfgUsuariosDto findById(Long id){
        TfgUsuariosEntity entity = tfgUsuariosRepository.findById(id).get();
        return transformFromEntityToDto(entity);
    }

    public TfgUsuariosDto actualizarUsuario(Long id, TfgUsuariosDto dto){
        TfgUsuariosEntity entity = tfgUsuariosRepository.findById(id).get();

        if (dto.getNombre_usuario() != null) {
            entity.setNombre_usuario(dto.getNombre_usuario());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getContrasena() != null) {
            entity.setContrasena(dto.getContrasena());
        }
        TfgUsuariosEntity entityActualizado = tfgUsuariosRepository.save(entity);
        return transformFromEntityToDto(entityActualizado);
    }

    public void eliminarUsuario(Long id){
        TfgUsuariosEntity entity = tfgUsuariosRepository.findById(id).get();
        tfgUsuariosRepository.delete(entity);
    }



    public TfgUsuariosDto transformFromEntityToDto(TfgUsuariosEntity entity){
        TfgUsuariosDto dto = new TfgUsuariosDto();
        dto.setId_usuario(entity.getId_usuario());
        dto.setNombre_usuario(entity.getNombre_usuario());
        dto.setEmail(entity.getEmail());
        dto.setContrasena(entity.getContrasena());
        return dto;
    }
    public TfgUsuariosEntity transformFromDtoToEntity(TfgUsuariosDto dto){
        TfgUsuariosEntity entity = new TfgUsuariosEntity();
        entity.setId_usuario(dto.getId_usuario());
        entity.setNombre_usuario(dto.getNombre_usuario());
        entity.setEmail(dto.getEmail());
        entity.setContrasena(dto.getContrasena());
        return entity;
    }

}

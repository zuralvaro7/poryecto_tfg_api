package com.trinitarias.proyecto_tfg_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trinitarias.proyecto_tfg_api.dto.TfgHistorialDto;
import com.trinitarias.proyecto_tfg_api.dto.TfgUsuariosDto;
import com.trinitarias.proyecto_tfg_api.entity.TfgHistorialEntity;
import com.trinitarias.proyecto_tfg_api.entity.TfgUsuariosEntity;
import com.trinitarias.proyecto_tfg_api.repository.TfgHistorialRepository;
import com.trinitarias.proyecto_tfg_api.repository.TfgUsuariosRepository;

@Service
public class TfgService {

    @Autowired
    private TfgUsuariosRepository tfgUsuariosRepository;

    @Autowired
    private TfgHistorialRepository tfgHistorialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TfgEmailService emailService;



    public TfgUsuariosDto crearUsuario(TfgUsuariosDto dto){
        String token = UUID.randomUUID().toString();
        dto.setVeri_token(token);
        dto.setActivo(Boolean.FALSE);

        dto.setContrasena(encriptar(dto.getContrasena()));

        TfgUsuariosEntity entity = tfgUsuariosRepository.save(transformFromDtoToEntityU(dto));
        emailService.sendVerificationEmail(dto.getEmail(),token);
        return transformFromEntityToDtoU(entity);
    }

    public TfgUsuariosDto findById(Long id){
        TfgUsuariosEntity entity = tfgUsuariosRepository.findById(id).get();
        return transformFromEntityToDtoU(entity);
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
        return transformFromEntityToDtoU(entityActualizado);
    }

    public void eliminarUsuario(Long id){
        TfgUsuariosEntity entity = tfgUsuariosRepository.findById(id).get();
        tfgHistorialRepository.borrarHistorial(id);
        tfgUsuariosRepository.delete(entity);
    }

    public TfgUsuariosDto login(TfgUsuariosDto dto) {
        TfgUsuariosDto dtoU = null;
        if(verificarContrasena(dto.getNombre_usuario(), dto.getContrasena())) {
            TfgUsuariosEntity entity = tfgUsuariosRepository.findByUsuario(dto.getNombre_usuario(), tfgUsuariosRepository.findNombreUsuario(dto.getNombre_usuario()).getContrasena());
            if (entity != null) {
                dtoU = transformFromEntityToDtoU(entity);
            }
        }
        return dtoU;
    }

    public TfgUsuariosDto buscarEmail(TfgUsuariosDto dto) {
        TfgUsuariosEntity entity = tfgUsuariosRepository.findByEmail(dto.getEmail());
        TfgUsuariosDto dtoU= null;
        if(entity!=null) {
            dtoU = transformFromEntityToDtoU(entity);
        }
        return dtoU;
    }

    public TfgUsuariosDto findByToken(String token){
        TfgUsuariosEntity entity = tfgUsuariosRepository.findByToken(token);
        TfgUsuariosDto dtoU= null;
        if(entity!=null) {
            entity.setVeri_token(null);
            entity.setActivo(Boolean.TRUE);
            tfgUsuariosRepository.save(entity);
            dtoU = transformFromEntityToDtoU(entity);
        }
        return dtoU;
    }




    public TfgHistorialDto subirHistorial(TfgHistorialDto dto, Long id) {
        dto.setId_usuario(id);
        TfgHistorialEntity entity = tfgHistorialRepository.save(transformFromDtoToEntityH(dto));

        return transformFromEntityToDtoH(entity);
    }

    public List<?> obtenerHistorial(Long id) {
        List<?> lista = tfgHistorialRepository.buscarHistorial(id);
        return lista;
    }



    public String encriptar(String pass){
        return passwordEncoder.encode(pass);
    }

    public boolean verificarContrasena(String usuario, String passPlana) {
        TfgUsuariosEntity usuarioE = tfgUsuariosRepository.findNombreUsuario(usuario);

        if (usuarioE != null) {
            TfgUsuariosDto usuarioD = transformFromEntityToDtoU(usuarioE);
            return passwordEncoder.matches(passPlana, usuarioD.getContrasena());
        }
        return false; // Usuario no encontrado
    }





    public TfgUsuariosDto transformFromEntityToDtoU(TfgUsuariosEntity entity){
        TfgUsuariosDto dto = new TfgUsuariosDto();
        dto.setId_usuario(entity.getId_usuario());
        dto.setNombre_usuario(entity.getNombre_usuario());
        dto.setEmail(entity.getEmail());
        dto.setContrasena(entity.getContrasena());
        dto.setVeri_token(entity.getVeri_token());
        dto.setActivo(entity.isActivo());
        return dto;
    }
    public TfgUsuariosEntity transformFromDtoToEntityU(TfgUsuariosDto dto){
        TfgUsuariosEntity entity = new TfgUsuariosEntity();
        entity.setId_usuario(dto.getId_usuario());
        entity.setNombre_usuario(dto.getNombre_usuario());
        entity.setEmail(dto.getEmail());
        entity.setContrasena(dto.getContrasena());
        entity.setVeri_token(dto.getVeri_token());
        entity.setActivo(dto.isActivo());
        return entity;
    }

    public TfgHistorialDto transformFromEntityToDtoH(TfgHistorialEntity entity){
        TfgHistorialDto dto = new TfgHistorialDto();
        dto.setId_historial(entity.getId_historial());
        dto.setNumero_capitulo(entity.getNumero_capitulo());
        dto.setNombre_capitulo(entity.getNombre_capitulo());
        dto.setHora(entity.getHora());
        dto.setDia_visto(entity.getDia_visto());
        dto.setId_usuario(entity.getId_usuario());
        return dto;
    }
    public TfgHistorialEntity transformFromDtoToEntityH(TfgHistorialDto dto){
        TfgHistorialEntity entity = new TfgHistorialEntity();
        entity.setId_historial(dto.getId_historial());
        entity.setNumero_capitulo(dto.getNumero_capitulo());
        entity.setNombre_capitulo(dto.getNombre_capitulo());
        entity.setHora(dto.getHora());
        entity.setDia_visto(dto.getDia_visto());
        entity.setId_usuario(dto.getId_usuario());
        return entity;
    }


}

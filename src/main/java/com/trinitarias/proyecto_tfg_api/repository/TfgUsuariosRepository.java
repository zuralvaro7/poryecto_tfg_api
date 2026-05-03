package com.trinitarias.proyecto_tfg_api.repository;

import com.trinitarias.proyecto_tfg_api.entity.TfgUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TfgUsuariosRepository extends JpaRepository<TfgUsuariosEntity, Long> {

    @Query(value="SELECT * FROM usuarios WHERE nombre_usuario = :usuario AND contrasena=:cont", nativeQuery = true)
    public TfgUsuariosEntity findByUsuario(String usuario, String cont);

    @Query(value="SELECT * FROM usuarios WHERE email = :email", nativeQuery = true)
    public TfgUsuariosEntity findByEmail(String email);

    @Query(value="SELECT * FROM usuarios WHERE veri_token = :token", nativeQuery = true)
    public TfgUsuariosEntity findByToken(String token);
}

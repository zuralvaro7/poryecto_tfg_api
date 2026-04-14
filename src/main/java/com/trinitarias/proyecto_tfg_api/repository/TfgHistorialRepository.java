package com.trinitarias.proyecto_tfg_api.repository;

import com.trinitarias.proyecto_tfg_api.entity.TfgHistorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TfgHistorialRepository extends JpaRepository<TfgHistorialEntity, Long> {

    @Query(value="SELECT * FROM historial WHERE id_usuario = :id", nativeQuery = true)
    public List<?> buscarHistorial(Long id);
}

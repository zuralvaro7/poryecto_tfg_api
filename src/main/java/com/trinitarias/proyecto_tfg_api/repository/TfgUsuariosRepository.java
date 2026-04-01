package com.trinitarias.proyecto_tfg_api.repository;

import com.trinitarias.proyecto_tfg_api.entity.TfgUsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TfgUsuariosRepository extends JpaRepository<TfgUsuariosEntity, Long> {
}

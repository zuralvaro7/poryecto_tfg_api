package com.trinitarias.proyecto_tfg_api.dto;

public class TfgDtos {

    private TfgHistorialDto tfgHistorialDto;
    private TfgUsuariosDto tfgUsuariosDto;

    public TfgDtos() {
        super();
    }

    public TfgDtos(TfgHistorialDto tfgHistorialDto, TfgUsuariosDto tfgUsuariosDto) {
        this.tfgHistorialDto = tfgHistorialDto;
        this.tfgUsuariosDto = tfgUsuariosDto;
    }

    public TfgHistorialDto getTfgHistorialDto() {
        return tfgHistorialDto;
    }

    public void setTfgHistorialDto(TfgHistorialDto tfgHistorialDto) {
        this.tfgHistorialDto = tfgHistorialDto;
    }

    public TfgUsuariosDto getTfgUsuariosDto() {
        return tfgUsuariosDto;
    }

    public void setTfgUsuariosDto(TfgUsuariosDto tfgUsuariosDto) {
        this.tfgUsuariosDto = tfgUsuariosDto;
    }
}

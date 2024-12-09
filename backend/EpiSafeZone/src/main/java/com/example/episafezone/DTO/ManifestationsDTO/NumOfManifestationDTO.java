package com.example.episafezone.DTO.ManifestationsDTO;

import java.io.Serializable;

public class NumOfManifestationDTO implements Serializable {

    private String name;
    private Integer num;

    public NumOfManifestationDTO(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public NumOfManifestationDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}

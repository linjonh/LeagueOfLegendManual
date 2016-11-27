package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lin on 2016/11/27.
 */
@Entity
public class EquipmentEntity implements Serializable {
    public static final long serialVersionUID = 10020141L;

    private String name;
    private String description;
    private String plaintext;
    private String image;
    @Generated(hash = 940498251)
    public EquipmentEntity(String name, String description, String plaintext,
            String image) {
        this.name = name;
        this.description = description;
        this.plaintext = plaintext;
        this.image = image;
    }
    @Generated(hash = 1788303319)
    public EquipmentEntity() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPlaintext() {
        return this.plaintext;
    }
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}

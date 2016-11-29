package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;

@Entity
public class Spells implements Serializable {
    public static final long serialVersionUID = R.id.id_spells;
    @Id
    public Long   id;//表id
    @Unique
    public String legendNameId;//english name id.
    public String nameId;//英雄技能英文名
    public String name;//英雄技能中文文名
    public String description;//技能描述
    public String imageName;//技能图片名
    public String tooltip;//技能提示

    /**
     * "leveltip": {
     * "label": [
     * "伤害",
     * "法力消耗"
     * ],
     * "effect": [
     * "40/65/90/115/140",
     * "  65/70/75/80/85"
     * ]
     * },
     */
    public            String          leveltipLabel;//用$隔开
    public            String          leveltipEffect;//用$隔开
    public            String          resource;//消耗法力值

    @Generated(hash = 319677011)
    public Spells(Long id, String legendNameId, String nameId, String name,
            String description, String imageName, String tooltip, String leveltipLabel,
            String leveltipEffect, String resource) {
        this.id = id;
        this.legendNameId = legendNameId;
        this.nameId = nameId;
        this.name = name;
        this.description = description;
        this.imageName = imageName;
        this.tooltip = tooltip;
        this.leveltipLabel = leveltipLabel;
        this.leveltipEffect = leveltipEffect;
        this.resource = resource;
    }

    @Generated(hash = 315983169)
    public Spells() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegendNameId() {
        return this.legendNameId;
    }

    public void setLegendNameId(String legendNameId) {
        this.legendNameId = legendNameId;
    }

    public String getNameId() {
        return this.nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
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

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setLeveltipLabel(String leveltipLabel) {
        this.leveltipLabel = leveltipLabel;
    }

    public void setLeveltipEffect(String leveltipEffect) {
        this.leveltipEffect = leveltipEffect;
    }

    public String getLeveltipLabel() {
        return this.leveltipLabel;
    }

    public String getLeveltipEffect() {
        return this.leveltipEffect;
    }

}
package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

@Entity
public class Skins implements Serializable {
    public static final long serialVersionUID = R.id.id_skins;
    @Id
    public Long   id;//表id
    public long   heroId;//外键表id
    @Unique
    public String skinId;//皮肤数字编号,也是图片文件名称
    public String name;

    @Generated(hash = 1738991830)
    public Skins(Long id, long heroId, String skinId, String name) {
        this.id = id;
        this.heroId = heroId;
        this.skinId = skinId;
        this.name = name;
    }

    @Generated(hash = 434852196)
    public Skins() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getHeroId() {
        return this.heroId;
    }

    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }

    public String getSkinId() {
        return this.skinId;
    }

    public void setSkinId(String skinId) {
        this.skinId = skinId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
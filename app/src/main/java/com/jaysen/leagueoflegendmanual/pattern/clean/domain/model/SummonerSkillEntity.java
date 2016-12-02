package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/28.
 */
@Entity
public class SummonerSkillEntity implements Serializable {
    public static final long serialVersionUID = R.id.id_summoner_skill;
    @Id
    private Long   id;
    @Unique
    private String nameId;//召唤师英文名；
    private String name;
    private String description;
    private int    maxrank;//等级需求
    private String key;//descImageId
    private String image;//SummonerClairvoyance.png"

    @Generated(hash = 591852516)
    public SummonerSkillEntity(Long id, String nameId, String name,
                               String description, int maxrank, String key, String image) {
        this.id = id;
        this.nameId = nameId;
        this.name = name;
        this.description = description;
        this.maxrank = maxrank;
        this.key = key;
        this.image = image;
    }

    @Generated(hash = 1335098958)
    public SummonerSkillEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getMaxrank() {
        return this.maxrank;
    }

    public void setMaxrank(int maxrank) {
        this.maxrank = maxrank;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

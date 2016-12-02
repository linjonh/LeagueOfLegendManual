package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/2.
 */
@Entity
public class VodEntity implements Serializable {
    public static final long serialVersionUID = R.id.id_vod;
    @Id
    public Long   id;
    @Unique
    public String heroNameId;
    public String heroName;
    public String heroTitle;
    public String vodlink;
    @Generated(hash = 1469786715)
    public VodEntity(Long id, String heroNameId, String heroName, String heroTitle,
            String vodlink) {
        this.id = id;
        this.heroNameId = heroNameId;
        this.heroName = heroName;
        this.heroTitle = heroTitle;
        this.vodlink = vodlink;
    }
    @Generated(hash = 1871792342)
    public VodEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHeroNameId() {
        return this.heroNameId;
    }
    public void setHeroNameId(String heroNameId) {
        this.heroNameId = heroNameId;
    }
    public String getHeroName() {
        return this.heroName;
    }
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
    public String getHeroTitle() {
        return this.heroTitle;
    }
    public void setHeroTitle(String heroTitle) {
        this.heroTitle = heroTitle;
    }
    public String getVodlink() {
        return this.vodlink;
    }
    public void setVodlink(String vodlink) {
        this.vodlink = vodlink;
    }
}

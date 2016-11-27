package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/14.
 * hero entity
 */
@Entity
public class HeroEntity implements Serializable {
    public static final long serialVersionUID = 10020140L;
    @Id(autoincrement = true)
    public Long id;
    public String avatarUrl;
    public String nameId;//english name id.
    @Unique
    public String legendName;
    public String legendTitle;
    public String tags;

    @Generated(hash = 412546348)
    public HeroEntity() {
    }

    @Generated(hash = 143419842)
    public HeroEntity(Long id, String avatarUrl, String nameId, String legendName,
            String legendTitle, String tags) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.nameId = nameId;
        this.legendName = legendName;
        this.legendTitle = legendTitle;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Hero:[name: " + legendName
                + ",title: " + legendTitle
                + ",tags: " + tags
                + ",avatarUrl: " + avatarUrl + "]";
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLegendName() {
        return this.legendName;
    }

    public void setLegendName(String legendName) {
        this.legendName = legendName;
    }

    public String getLegendTitle() {
        return this.legendTitle;
    }

    public void setLegendTitle(String legendTitle) {
        this.legendTitle = legendTitle;
    }


    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
}

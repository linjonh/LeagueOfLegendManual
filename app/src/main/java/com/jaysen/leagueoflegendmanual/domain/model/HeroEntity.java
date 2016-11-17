package com.jaysen.leagueoflegendmanual.domain.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/14.
 * hero entity
 */
@Entity
public class HeroEntity implements Serializable {
    public static final long serialVersionUID = 10020140L;
    @Id(autoincrement = true)
    public long   id;
    public String avatarUrl;
    public String legendName;
    public String legendTitle;
    public String description;
    public String tags;

    @Generated(hash = 1492366395)
    public HeroEntity(long id, String avatarUrl, String legendName,
                      String legendTitle, String description, String tags) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.legendName = legendName;
        this.legendTitle = legendTitle;
        this.description = description;
        this.tags = tags;
    }

    @Generated(hash = 412546348)
    public HeroEntity() {
    }

    @Override
    public String toString() {
        return "Hero:[name: " + legendName
                + ",title: " + legendTitle
                + ",description: " + description
                + ",tags: " + tags
                + ",avatarUrl: " + avatarUrl + "]";
    }

    public long getId() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

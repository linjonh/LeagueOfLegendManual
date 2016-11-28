package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/14.
 * hero entity
 */
@Entity
public class HeroEntity implements Serializable {
    public static final long serialVersionUID = R.id.id_hero;
    @Id(autoincrement = true)
    public            Long                 id;
    public            String               avatarUrl;
    @Unique
    public            String               nameId;//english name id.
    public            long                 forienKey;//english name id.
    @Unique
    public            String               legendName;
    public            String               legendTitle;
    public            String               tags;
    @ToOne(joinProperty = "forienKey")
    public            HeroDetailInfoEntity heroDetailInfoEntity;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession           daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 629144359)
    private transient HeroEntityDao        myDao;
    @Generated(hash = 398299986)
    private transient Long                 heroDetailInfoEntity__resolvedKey;

    @Generated(hash = 412546348)
    public HeroEntity() {
    }

    @Generated(hash = 2044511375)
    public HeroEntity(Long id, String avatarUrl, String nameId, long forienKey,
                      String legendName, String legendTitle, String tags) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.nameId = nameId;
        this.forienKey = forienKey;
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

    public long getForienKey() {
        return this.forienKey;
    }

    public void setForienKey(long forienKey) {
        this.forienKey = forienKey;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 326642184)
    public HeroDetailInfoEntity getHeroDetailInfoEntity() {
        long __key = this.forienKey;
        if (heroDetailInfoEntity__resolvedKey == null
                || !heroDetailInfoEntity__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HeroDetailInfoEntityDao targetDao = daoSession
                    .getHeroDetailInfoEntityDao();
            HeroDetailInfoEntity heroDetailInfoEntityNew = targetDao.load(__key);
            synchronized (this) {
                heroDetailInfoEntity = heroDetailInfoEntityNew;
                heroDetailInfoEntity__resolvedKey = __key;
            }
        }
        return heroDetailInfoEntity;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 474177909)
    public void setHeroDetailInfoEntity(
            @NotNull HeroDetailInfoEntity heroDetailInfoEntity) {
        if (heroDetailInfoEntity == null) {
            throw new DaoException(
                    "To-one property 'forienKey' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.heroDetailInfoEntity = heroDetailInfoEntity;
            forienKey = heroDetailInfoEntity.getId();
            heroDetailInfoEntity__resolvedKey = forienKey;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 302425935)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHeroEntityDao() : null;
    }
}

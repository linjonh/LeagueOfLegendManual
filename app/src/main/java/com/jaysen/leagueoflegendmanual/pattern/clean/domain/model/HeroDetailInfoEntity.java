package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.AttributeInfo;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.AttributeInfoDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Skins;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.SkinsDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Spells;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.SpellsDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/28.
 */

@Entity
public class HeroDetailInfoEntity implements Serializable {
    public static final long serialVersionUID = R.id.id_hero_detail;
    @Id(autoincrement = true)
    public            Long                    id;//表id
    @Unique
    public            String                  nameId;//english name id.
    public            String                  description;//英雄背景介绍：对应lore字段
    @ToMany(referencedJoinProperty = "heroId")
    public            List<Skins>             skinIdNames;//皮肤标号和名称
    @ToMany(referencedJoinProperty = "heroId")
    public            List<AttributeInfo>     attributeInfo;//英雄属性：攻击防御，魔法，难度等
    @ToMany(joinProperties = {
            @JoinProperty(name = "nameId", referencedName = "legendNameId")
    })
    public            List<Spells>            spellsList;//英雄技能，（被动技能、 Q、 W、 E、 R）
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession              daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1036404908)
    private transient HeroDetailInfoEntityDao myDao;

    @Generated(hash = 1202050394)
    public HeroDetailInfoEntity(Long id, String nameId, String description) {
        this.id = id;
        this.nameId = nameId;
        this.description = description;
    }

    @Generated(hash = 966667648)
    public HeroDetailInfoEntity() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1228099615)
    public List<Skins> getSkinIdNames() {
        if (skinIdNames == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SkinsDao    targetDao      = daoSession.getSkinsDao();
            List<Skins> skinIdNamesNew = targetDao._queryHeroDetailInfoEntity_SkinIdNames(id);
            synchronized (this) {
                if (skinIdNames == null) {
                    skinIdNames = skinIdNamesNew;
                }
            }
        }
        return skinIdNames;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1843263714)
    public synchronized void resetSkinIdNames() {
        skinIdNames = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2027611823)
    public List<AttributeInfo> getAttributeInfo() {
        if (attributeInfo == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AttributeInfoDao targetDao = daoSession.getAttributeInfoDao();
            List<AttributeInfo> attributeInfoNew = targetDao
                    ._queryHeroDetailInfoEntity_AttributeInfo(id);
            synchronized (this) {
                if (attributeInfo == null) {
                    attributeInfo = attributeInfoNew;
                }
            }
        }
        return attributeInfo;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1499416973)
    public synchronized void resetAttributeInfo() {
        attributeInfo = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1125397108)
    public List<Spells> getSpellsList() {
        if (spellsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpellsDao    targetDao     = daoSession.getSpellsDao();
            List<Spells> spellsListNew = targetDao._queryHeroDetailInfoEntity_SpellsList(nameId);
            synchronized (this) {
                if (spellsList == null) {
                    spellsList = spellsListNew;
                }
            }
        }
        return spellsList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1467112082)
    public synchronized void resetSpellsList() {
        spellsList = null;
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
    @Generated(hash = 1845362782)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHeroDetailInfoEntityDao() : null;
    }


}



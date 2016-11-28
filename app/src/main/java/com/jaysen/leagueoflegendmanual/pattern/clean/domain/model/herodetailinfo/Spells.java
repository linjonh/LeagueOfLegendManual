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
    @ToMany(joinProperties = {
            @JoinProperty(name = "legendNameId", referencedName = "heroId")
    })
    public            List<LeveltipLabel>  leveltipLabel;
    @ToMany(joinProperties = {
            @JoinProperty(name = "legendNameId", referencedName = "heroId")
    })
    public            List<LeveltipEffect> leveltipEffect;
    public            String               resource;//消耗法力值
    @ToMany(joinProperties = {
            @JoinProperty(name = "legendNameId", referencedName = "heroId")
    })
    public            List<Allytips>       allytips;//当你使用该英雄时技能提示
    @ToMany(joinProperties = {
            @JoinProperty(name = "legendNameId", referencedName = "heroId")
    })
    public            List<Enemytips>      enemytips;//敌人使用英雄时技能提示
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession           daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 654227704)
    private transient SpellsDao            myDao;

    @Generated(hash = 849588792)
    public Spells(Long id, String legendNameId, String nameId, String name,
                  String description, String imageName, String tooltip, String resource) {
        this.id = id;
        this.legendNameId = legendNameId;
        this.nameId = nameId;
        this.name = name;
        this.description = description;
        this.imageName = imageName;
        this.tooltip = tooltip;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2007455396)
    public List<LeveltipLabel> getLeveltipLabel() {
        if (leveltipLabel == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LeveltipLabelDao targetDao = daoSession.getLeveltipLabelDao();
            List<LeveltipLabel> leveltipLabelNew = targetDao
                    ._querySpells_LeveltipLabel(legendNameId);
            synchronized (this) {
                if (leveltipLabel == null) {
                    leveltipLabel = leveltipLabelNew;
                }
            }
        }
        return leveltipLabel;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 926232143)
    public synchronized void resetLeveltipLabel() {
        leveltipLabel = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 759644939)
    public List<LeveltipEffect> getLeveltipEffect() {
        if (leveltipEffect == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LeveltipEffectDao targetDao = daoSession.getLeveltipEffectDao();
            List<LeveltipEffect> leveltipEffectNew = targetDao
                    ._querySpells_LeveltipEffect(legendNameId);
            synchronized (this) {
                if (leveltipEffect == null) {
                    leveltipEffect = leveltipEffectNew;
                }
            }
        }
        return leveltipEffect;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 85734300)
    public synchronized void resetLeveltipEffect() {
        leveltipEffect = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 214677986)
    public List<Allytips> getAllytips() {
        if (allytips == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AllytipsDao targetDao = daoSession.getAllytipsDao();
            List<Allytips> allytipsNew = targetDao
                    ._querySpells_Allytips(legendNameId);
            synchronized (this) {
                if (allytips == null) {
                    allytips = allytipsNew;
                }
            }
        }
        return allytips;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 76741996)
    public synchronized void resetAllytips() {
        allytips = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1830344690)
    public List<Enemytips> getEnemytips() {
        if (enemytips == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EnemytipsDao targetDao = daoSession.getEnemytipsDao();
            List<Enemytips> enemytipsNew = targetDao
                    ._querySpells_Enemytips(legendNameId);
            synchronized (this) {
                if (enemytips == null) {
                    enemytips = enemytipsNew;
                }
            }
        }
        return enemytips;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1029214454)
    public synchronized void resetEnemytips() {
        enemytips = null;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 486956905)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSpellsDao() : null;
    }

}
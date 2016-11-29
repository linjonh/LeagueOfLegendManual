package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Allytips;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.AllytipsDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.AttributeInfo;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.AttributeInfoDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Enemytips;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.EnemytipsDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.EquipmentRecommend;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.EquipmentRecommendDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Skins;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.SkinsDao;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Spells;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.SpellsDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
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
    public Long          id;//表id
    public long          heroId;//外键表AttributeInfo id
    @Unique
    public String        nameId;//english name id.
    public String        description;//英雄背景介绍：对应lore字段

    @ToMany(referencedJoinProperty = "heroId")
    public List<Skins>   skinIdNames;//皮肤标号和名称

    @ToOne(joinProperty = "heroId")
    public AttributeInfo attributeInfo;//英雄属性：攻击防御，魔法，难度等

    @ToMany(joinProperties = {@JoinProperty(name = "nameId", referencedName = "legendNameId")})
    public List<Spells>  spellsList;//英雄技能，（被动技能、 Q、 W、 E、 R）

    @ToMany(joinProperties = {@JoinProperty(name = "nameId", referencedName = "heroId")})
    public List<Allytips>  allytips;//当你使用该英雄时技能提示

    @ToMany(joinProperties = {@JoinProperty(name = "nameId", referencedName = "heroId")})
    public List<Enemytips> enemytips;//敌人使用英雄时技能提示

    @ToMany(referencedJoinProperty = "heroId")//对应到该表的主键id long型
    public            List<EquipmentRecommend> recommend1;//推荐装备召唤师峡谷

    @ToMany(referencedJoinProperty = "heroId")//对应到该表的主键id
    public            List<EquipmentRecommend> recommend2;//推荐装备极地大乱斗
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession               daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1036404908)
    private transient HeroDetailInfoEntityDao  myDao;
    @Generated(hash = 124468680)
    private transient Long                     attributeInfo__resolvedKey;


    @Generated(hash = 966667648)
    public HeroDetailInfoEntity() {
    }

    @Generated(hash = 1923659369)
    public HeroDetailInfoEntity(Long id, long heroId, String nameId, String description) {
        this.id = id;
        this.heroId = heroId;
        this.nameId = nameId;
        this.description = description;
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
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 210978643)
    public AttributeInfo getAttributeInfo() {
        long __key = this.heroId;
        if (attributeInfo__resolvedKey == null || !attributeInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AttributeInfoDao targetDao        = daoSession.getAttributeInfoDao();
            AttributeInfo    attributeInfoNew = targetDao.load(__key);
            synchronized (this) {
                attributeInfo = attributeInfoNew;
                attributeInfo__resolvedKey = __key;
            }
        }
        return attributeInfo;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1129283266)
    public List<EquipmentRecommend> getRecommend1() {
        if (recommend1 == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EquipmentRecommendDao targetDao = daoSession.getEquipmentRecommendDao();
            List<EquipmentRecommend> recommend1New = targetDao
                    ._queryHeroDetailInfoEntity_Recommend1(id);
            synchronized (this) {
                if (recommend1 == null) {
                    recommend1 = recommend1New;
                }
            }
        }
        return recommend1;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 562593592)
    public synchronized void resetRecommend1() {
        recommend1 = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 936973722)
    public List<EquipmentRecommend> getRecommend2() {
        if (recommend2 == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EquipmentRecommendDao targetDao = daoSession.getEquipmentRecommendDao();
            List<EquipmentRecommend> recommend2New = targetDao
                    ._queryHeroDetailInfoEntity_Recommend2(id);
            synchronized (this) {
                if (recommend2 == null) {
                    recommend2 = recommend2New;
                }
            }
        }
        return recommend2;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1138522752)
    public synchronized void resetRecommend2() {
        recommend2 = null;
    }

    public long getHeroId() {
        return this.heroId;
    }

    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1304430506)
    public void setAttributeInfo(@NotNull AttributeInfo attributeInfo) {
        if (attributeInfo == null) {
            throw new DaoException(
                    "To-one property 'heroId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.attributeInfo = attributeInfo;
            heroId = attributeInfo.getId();
            attributeInfo__resolvedKey = heroId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1434227093)
    public List<Allytips> getAllytips() {
        if (allytips == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AllytipsDao    targetDao   = daoSession.getAllytipsDao();
            List<Allytips> allytipsNew = targetDao._queryHeroDetailInfoEntity_Allytips(nameId);
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
    @Generated(hash = 1721228725)
    public List<Enemytips> getEnemytips() {
        if (enemytips == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EnemytipsDao    targetDao    = daoSession.getEnemytipsDao();
            List<Enemytips> enemytipsNew = targetDao._queryHeroDetailInfoEntity_Enemytips(nameId);
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
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1845362782)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHeroDetailInfoEntityDao() : null;
    }


}



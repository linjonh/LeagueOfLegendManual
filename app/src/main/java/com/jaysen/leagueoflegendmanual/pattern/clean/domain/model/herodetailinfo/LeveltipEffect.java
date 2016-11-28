package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/28.
 */
@Entity
public class LeveltipEffect implements Serializable {
    public static final long serialVersionUID = R.id.id_level_tip_effect;
    @Id
    public Long   id;//表id
    public String value;//伤害，冷却时间的值等
    public String heroId;

    @Generated(hash = 306383749)
    public LeveltipEffect(Long id, String value, String heroId) {
        this.id = id;
        this.value = value;
        this.heroId = heroId;
    }

    @Generated(hash = 1836431011)
    public LeveltipEffect() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHeroId() {
        return this.heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }
}

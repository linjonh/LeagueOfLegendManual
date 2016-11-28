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
public class LeveltipLabel implements Serializable {
    public static final long serialVersionUID = R.id.id_level_tip_label;
    @Id
    public Long   id;//表id
    public String name;//伤害，冷却时间等
    public String heroId;

    @Generated(hash = 135090490)
    public LeveltipLabel(Long id, String name, String heroId) {
        this.id = id;
        this.name = name;
        this.heroId = heroId;
    }

    @Generated(hash = 275655093)
    public LeveltipLabel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroId() {
        return this.heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }
}

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
public class Enemytips implements Serializable {
    public static final long serialVersionUID = R.id.id_enemy_tip;
    @Id
    public Long   id;//表id
    public String tip;//提示内容
    public String heroId;

    @Generated(hash = 2022173691)
    public Enemytips(Long id, String tip, String heroId) {
        this.id = id;
        this.tip = tip;
        this.heroId = heroId;
    }

    @Generated(hash = 17917763)
    public Enemytips() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getHeroId() {
        return this.heroId;
    }

    public void setHeroId(String heroId) {
        this.heroId = heroId;
    }
}

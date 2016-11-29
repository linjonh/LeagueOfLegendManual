package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/29.
 */
@Entity
public class EquipmentRecommend implements Serializable {
    public static final long serialVersionUID = R.id.id_recommend_equipment;
    @Id
    public Long   id;
    public long heroId;
    public String equipmentIds;//3089,3089,3089逗号隔开

    public String mode;//ARAM,CLASSIC
    public String type;//starting, essential, offensive, defensive

    @Generated(hash = 1137299010)
    public EquipmentRecommend() {
    }
    @Generated(hash = 2036125099)
    public EquipmentRecommend(Long id, long heroId, String equipmentIds,
            String mode, String type) {
        this.id = id;
        this.heroId = heroId;
        this.equipmentIds = equipmentIds;
        this.mode = mode;
        this.type = type;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentIds() {
        return this.equipmentIds;
    }
    public void setEquipmentIds(String equipmentIds) {
        this.equipmentIds = equipmentIds;
    }
    public String getMode() {
        return this.mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public long getHeroId() {
        return this.heroId;
    }
    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }
}

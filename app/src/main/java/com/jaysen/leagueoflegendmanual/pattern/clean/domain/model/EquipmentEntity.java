package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by lin on 2016/11/27.
 */
@Entity
public class EquipmentEntity implements Serializable {
    public static final long serialVersionUID = R.id.id_equipment;
    @Id(autoincrement = true)
    public  Long    id;
    @Unique
    @Property(nameInDb = "EQ_ID")
    private String  equipmentId;//装备索引Id
    private String  name;
    private String  description;
    private String  plaintext;
    private String  image;
    private String  into;
    private String  from;
    private String  tags;
    private int     goldBase;
    private int     goldTotal;
    private int     goldSell;
    private boolean purchasable;

    @Generated(hash = 185766276)
    public EquipmentEntity(Long id, String equipmentId, String name,
            String description, String plaintext, String image, String into,
            String from, String tags, int goldBase, int goldTotal, int goldSell,
            boolean purchasable) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.name = name;
        this.description = description;
        this.plaintext = plaintext;
        this.image = image;
        this.into = into;
        this.from = from;
        this.tags = tags;
        this.goldBase = goldBase;
        this.goldTotal = goldTotal;
        this.goldSell = goldSell;
        this.purchasable = purchasable;
    }

    @Generated(hash = 1788303319)
    public EquipmentEntity() {
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

    public String getPlaintext() {
        return this.plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return this.equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getGoldBase() {
        return this.goldBase;
    }

    public void setGoldBase(int goldBase) {
        this.goldBase = goldBase;
    }

    public int getGoldTotal() {
        return this.goldTotal;
    }

    public void setGoldTotal(int goldTotal) {
        this.goldTotal = goldTotal;
    }

    public int getGoldSell() {
        return this.goldSell;
    }

    public void setGoldSell(int goldSell) {
        this.goldSell = goldSell;
    }

    public boolean getPurchasable() {
        return this.purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }

    public String getInto() {
        return this.into;
    }

    public void setInto(String into) {
        this.into = into;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}

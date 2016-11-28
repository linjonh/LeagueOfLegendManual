package com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo;

import com.jaysen.leagueoflegendmanual.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class AttributeInfo implements Serializable{
    public static final long serialVersionUID = R.id.id_attribute_info;
    @Id
    public Long id;//表id
    public long heroId;//外键表id
    public int  attack;
    public int  defense;
    public int  magic;
    public int  difficulty;
    @Generated(hash = 1664177021)
    public AttributeInfo(Long id, long heroId, int attack, int defense, int magic,
            int difficulty) {
        this.id = id;
        this.heroId = heroId;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
    }
    @Generated(hash = 450848247)
    public AttributeInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getHeroId() {
        return this.heroId;
    }
    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }
    public int getAttack() {
        return this.attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return this.defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getMagic() {
        return this.magic;
    }
    public void setMagic(int magic) {
        this.magic = magic;
    }
    public int getDifficulty() {
        return this.difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
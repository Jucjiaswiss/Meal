package com.szu.zhaoyujia.meal.Model;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zhaoyujia on 2017/10/9.
 */

public class Meal extends BmobObject{
    private String title;
    private String intro;
    private Integer like;
    private Float health;
    private BmobFile image;
    private String tag_normal;
    private String tag_status;
    private List<Food> ingredient;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Float getHealth() {
        return health;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public String getTag_normal() {
        return tag_normal;
    }

    public void setTag_normal(String tag_normal) {
        this.tag_normal = tag_normal;
    }

    public String getTag_status() {
        return tag_status;
    }

    public void setTag_status(String tag_status) {
        this.tag_status = tag_status;
    }

    public List<Food> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Food> ingredient) {
        this.ingredient = ingredient;
    }
}

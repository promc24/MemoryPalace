package com.example.memorypalace.activities.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


//DATABASE TABLE ENTITIES
@Entity(tableName = "rooms")
public class Rooms implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "date_time")
    private String dateTime;

    @ColumnInfo(name = "image_1")
    private String image1;

    @ColumnInfo(name = "description_1")
    private String desc1;

    @ColumnInfo(name = "image_2")
    private String image2;

    @ColumnInfo(name = "description_2")
    private String desc2;

    @ColumnInfo(name = "image_3")
    private String image3;

    @ColumnInfo(name = "description_3")
    private String desc3;

    @ColumnInfo(name = "image_4")
    private String image4;

    @ColumnInfo(name = "description_4")
    private String desc4;

    @ColumnInfo(name = "image_5")
    private String image5;

    @ColumnInfo(name = "description_5")
    private String desc5;

    @ColumnInfo(name = "image_6")
    private String image6;

    @ColumnInfo(name = "description_6")
    private String desc6;

    @ColumnInfo(name = "image_7")
    private String image7;

    @ColumnInfo(name = "description_7")
    private String desc7;

    @ColumnInfo(name = "image_8")
    private String image8;

    @ColumnInfo(name = "description_8")
    private String desc8;

    @ColumnInfo(name = "image_9")
    private String image9;

    @ColumnInfo(name = "description_9")
    private String desc9;

    @ColumnInfo(name = "image_10")
    private String image10;

    @ColumnInfo(name = "description_10")
    private String desc10;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getDesc5() {
        return desc5;
    }

    public void setDesc5(String desc5) {
        this.desc5 = desc5;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public String getDesc6() {
        return desc6;
    }

    public void setDesc6(String desc6) {
        this.desc6 = desc6;
    }

    public String getImage7() {
        return image7;
    }

    public void setImage7(String image7) {
        this.image7 = image7;
    }

    public String getDesc7() {
        return desc7;
    }

    public void setDesc7(String desc7) {
        this.desc7 = desc7;
    }

    public String getImage8() {
        return image8;
    }

    public void setImage8(String image8) {
        this.image8 = image8;
    }

    public String getDesc8() {
        return desc8;
    }

    public void setDesc8(String desc8) {
        this.desc8 = desc8;
    }

    public String getImage9() {
        return image9;
    }

    public void setImage9(String image9) {
        this.image9 = image9;
    }

    public String getDesc9() {
        return desc9;
    }

    public void setDesc9(String desc9) {
        this.desc9 = desc9;
    }

    public String getImage10() {
        return image10;
    }

    public void setImage10(String image10) {
        this.image10 = image10;
    }

    public String getDesc10() {
        return desc10;
    }

    public void setDesc10(String desc10) {
        this.desc10 = desc10;
    }

    @NonNull
    @Override
    public String toString() {
        return title + ":" + dateTime;
    }
}

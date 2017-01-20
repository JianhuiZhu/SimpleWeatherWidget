package com.jianhui_zhu.simpleweatherwidget.model.domain;

/**
 * Created by jianhuizhu on 2017-01-17.
 */
public class Country
{
    private String EnglishName;

    private String ID;

    private String LocalizedName;

    public String getEnglishName ()
    {
        return EnglishName;
    }

    public void setEnglishName (String EnglishName)
    {
        this.EnglishName = EnglishName;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getLocalizedName ()
    {
        return LocalizedName;
    }

    public void setLocalizedName (String LocalizedName)
    {
        this.LocalizedName = LocalizedName;
    }

}
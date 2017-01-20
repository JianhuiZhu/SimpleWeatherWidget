package com.jianhui_zhu.simpleweatherwidget.model.domain;

/**
 * Created by jianhuizhu on 2017-01-18.
 */

public class Direction
{
    private String Degrees;

    private String Localized;

    private String English;

    public String getDegrees ()
    {
        return Degrees;
    }

    public void setDegrees (String Degrees)
    {
        this.Degrees = Degrees;
    }

    public String getLocalized ()
    {
        return Localized;
    }

    public void setLocalized (String Localized)
    {
        this.Localized = Localized;
    }

    public String getEnglish ()
    {
        return English;
    }

    public void setEnglish (String English)
    {
        this.English = English;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Degrees = "+Degrees+", Localized = "+Localized+", English = "+English+"]";
    }
}

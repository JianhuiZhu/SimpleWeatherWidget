package com.jianhui_zhu.simpleweatherwidget.model.domain;

/**
 * Created by jianhuizhu on 2017-01-18.
 */

public class Rain
{
    private String Value;

    private String UnitType;

    private String Unit;

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    public String getUnitType ()
    {
        return UnitType;
    }

    public void setUnitType (String UnitType)
    {
        this.UnitType = UnitType;
    }

    public String getUnit ()
    {
        return Unit;
    }

    public void setUnit (String Unit)
    {
        this.Unit = Unit;
    }

}

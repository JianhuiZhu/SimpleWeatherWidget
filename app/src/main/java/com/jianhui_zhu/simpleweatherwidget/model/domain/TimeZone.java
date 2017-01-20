package com.jianhui_zhu.simpleweatherwidget.model.domain;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class TimeZone
{
    private String Name;

    private String GmtOffset;

    private String IsDaylightSaving;

    private String NextOffsetChange;

    private String Code;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getGmtOffset ()
    {
        return GmtOffset;
    }

    public void setGmtOffset (String GmtOffset)
    {
        this.GmtOffset = GmtOffset;
    }

    public String getIsDaylightSaving ()
    {
        return IsDaylightSaving;
    }

    public void setIsDaylightSaving (String IsDaylightSaving)
    {
        this.IsDaylightSaving = IsDaylightSaving;
    }

    public String getNextOffsetChange ()
    {
        return NextOffsetChange;
    }

    public void setNextOffsetChange (String NextOffsetChange)
    {
        this.NextOffsetChange = NextOffsetChange;
    }

    public String getCode ()
    {
        return Code;
    }

    public void setCode (String Code)
    {
        this.Code = Code;
    }

}

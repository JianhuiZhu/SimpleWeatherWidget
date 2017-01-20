package com.jianhui_zhu.simpleweatherwidget.model.domain;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class GeoPosition
{
    private Elevation Elevation;

    private String Latitude;

    private String Longitude;

    public Elevation getElevation ()
    {
        return Elevation;
    }

    public void setElevation (Elevation Elevation)
    {
        this.Elevation = Elevation;
    }

    public String getLatitude ()
    {
        return Latitude;
    }

    public void setLatitude (String Latitude)
    {
        this.Latitude = Latitude;
    }

    public String getLongitude ()
    {
        return Longitude;
    }

    public void setLongitude (String Longitude)
    {
        this.Longitude = Longitude;
    }

}

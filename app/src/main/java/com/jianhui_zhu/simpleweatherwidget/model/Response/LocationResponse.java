package com.jianhui_zhu.simpleweatherwidget.model.Response;

import com.jianhui_zhu.simpleweatherwidget.model.domain.AdministrativeArea;
import com.jianhui_zhu.simpleweatherwidget.model.domain.Country;
import com.jianhui_zhu.simpleweatherwidget.model.domain.GeoPosition;
import com.jianhui_zhu.simpleweatherwidget.model.domain.Region;
import com.jianhui_zhu.simpleweatherwidget.model.domain.SupplementalAdminAreas;
import com.jianhui_zhu.simpleweatherwidget.model.domain.TimeZone;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class LocationResponse
{
    private String Key;

    private String Rank;

    private String Type;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.SupplementalAdminAreas[] SupplementalAdminAreas;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.AdministrativeArea AdministrativeArea;

    private String LocalizedName;

    private String PrimaryPostalCode;

    private String IsAlias;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.Country Country;

    private String[] DataSets;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.Region Region;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.GeoPosition GeoPosition;

    private String EnglishName;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.TimeZone TimeZone;

    private String Version;

    public String getKey ()
    {
        return Key;
    }

    public void setKey (String Key)
    {
        this.Key = Key;
    }

    public String getRank ()
    {
        return Rank;
    }

    public void setRank (String Rank)
    {
        this.Rank = Rank;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public SupplementalAdminAreas[] getSupplementalAdminAreas ()
    {
        return SupplementalAdminAreas;
    }

    public void setSupplementalAdminAreas (SupplementalAdminAreas[] SupplementalAdminAreas)
    {
        this.SupplementalAdminAreas = SupplementalAdminAreas;
    }

    public AdministrativeArea getAdministrativeArea ()
    {
        return AdministrativeArea;
    }

    public void setAdministrativeArea (AdministrativeArea AdministrativeArea)
    {
        this.AdministrativeArea = AdministrativeArea;
    }

    public String getLocalizedName ()
    {
        return LocalizedName;
    }

    public void setLocalizedName (String LocalizedName)
    {
        this.LocalizedName = LocalizedName;
    }

    public String getPrimaryPostalCode ()
    {
        return PrimaryPostalCode;
    }

    public void setPrimaryPostalCode (String PrimaryPostalCode)
    {
        this.PrimaryPostalCode = PrimaryPostalCode;
    }

    public String getIsAlias ()
    {
        return IsAlias;
    }

    public void setIsAlias (String IsAlias)
    {
        this.IsAlias = IsAlias;
    }

    public Country getCountry ()
    {
        return Country;
    }

    public void setCountry (Country Country)
    {
        this.Country = Country;
    }

    public String[] getDataSets ()
    {
        return DataSets;
    }

    public void setDataSets (String[] DataSets)
    {
        this.DataSets = DataSets;
    }

    public Region getRegion ()
    {
        return Region;
    }

    public void setRegion (Region Region)
    {
        this.Region = Region;
    }

    public GeoPosition getGeoPosition ()
    {
        return GeoPosition;
    }

    public void setGeoPosition (GeoPosition GeoPosition)
    {
        this.GeoPosition = GeoPosition;
    }

    public String getEnglishName ()
    {
        return EnglishName;
    }

    public void setEnglishName (String EnglishName)
    {
        this.EnglishName = EnglishName;
    }

    public TimeZone getTimeZone ()
    {
        return TimeZone;
    }

    public void setTimeZone (TimeZone TimeZone)
    {
        this.TimeZone = TimeZone;
    }

    public String getVersion ()
    {
        return Version;
    }

    public void setVersion (String Version)
    {
        this.Version = Version;
    }

}

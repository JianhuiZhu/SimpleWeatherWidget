package com.jianhui_zhu.simpleweatherwidget.model.Response;

import com.jianhui_zhu.simpleweatherwidget.model.domain.Temperature;

/**
 * Created by jianhuizhu on 2017-01-18.
 */

public class CurrentWeatherBriefResponse {
    private String IconPhrase;

    private String Link;

    private String IsDaylight;

    private String EpochDateTime;

    private String WeatherIcon;

    private String DateTime;

    private String MobileLink;

    private String PrecipitationProbability;

    private com.jianhui_zhu.simpleweatherwidget.model.domain.Temperature Temperature;

    public String getIconPhrase ()
    {
        return IconPhrase;
    }

    public void setIconPhrase (String IconPhrase)
    {
        this.IconPhrase = IconPhrase;
    }

    public String getLink ()
    {
        return Link;
    }

    public void setLink (String Link)
    {
        this.Link = Link;
    }

    public String getIsDaylight ()
    {
        return IsDaylight;
    }

    public void setIsDaylight (String IsDaylight)
    {
        this.IsDaylight = IsDaylight;
    }

    public String getEpochDateTime ()
    {
        return EpochDateTime;
    }

    public void setEpochDateTime (String EpochDateTime)
    {
        this.EpochDateTime = EpochDateTime;
    }

    public String getWeatherIcon ()
    {
        return WeatherIcon;
    }

    public void setWeatherIcon (String WeatherIcon)
    {
        this.WeatherIcon = WeatherIcon;
    }

    public String getDateTime ()
    {
        return DateTime;
    }

    public void setDateTime (String DateTime)
    {
        this.DateTime = DateTime;
    }

    public String getMobileLink ()
    {
        return MobileLink;
    }

    public void setMobileLink (String MobileLink)
    {
        this.MobileLink = MobileLink;
    }

    public String getPrecipitationProbability ()
    {
        return PrecipitationProbability;
    }

    public void setPrecipitationProbability (String PrecipitationProbability)
    {
        this.PrecipitationProbability = PrecipitationProbability;
    }

    public Temperature getTemperature ()
    {
        return Temperature;
    }

    public void setTemperature (Temperature Temperature)
    {
        this.Temperature = Temperature;
    }

}

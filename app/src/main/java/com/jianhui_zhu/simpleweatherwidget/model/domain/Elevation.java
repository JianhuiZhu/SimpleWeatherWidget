package com.jianhui_zhu.simpleweatherwidget.model.domain;

/**
 * Created by jianhuizhu on 2017-01-17.
 */

public class Elevation
{
    private Metric Metric;

    private Imperial Imperial;

    public Metric getMetric ()
    {
        return Metric;
    }

    public void setMetric (Metric Metric)
    {
        this.Metric = Metric;
    }

    public Imperial getImperial ()
    {
        return Imperial;
    }

    public void setImperial (Imperial Imperial)
    {
        this.Imperial = Imperial;
    }

}

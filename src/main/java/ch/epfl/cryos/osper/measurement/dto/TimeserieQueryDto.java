package ch.epfl.cryos.osper.measurement.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by kryvych on 03/10/16.
 */
public class TimeserieQueryDto {

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mmZ")
    private Date from;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mmZ")
    private Date until;

    private boolean includeData = true;

    private boolean includeInfo = true;

    private Integer limit;



    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public boolean isIncludeData() {
        return includeData;
    }

    public void setIncludeData(boolean includeData) {
        this.includeData = includeData;
    }

    public boolean isIncludeInfo() {
        return includeInfo;
    }

    public void setIncludeInfo(boolean includeInfo) {
        this.includeInfo = includeInfo;
    }

    @Override
    public String toString() {
        return "TimeserieQueryDto{" +
                "from=" + from +
                ", until=" + until +
                ", includeData=" + includeData +
                ", includeInfo=" + includeInfo +
                ", limit=" + limit +
                '}';
    }
}

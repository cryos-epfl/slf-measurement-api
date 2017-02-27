package ch.epfl.cryos.osper.measurement.dto;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by kryvych on 03/10/16.
 */
public class TimeserieQueryDto {

    private static final String[] DATE_FORMATS = {"yyyy-MM-dd'T'HH:mm'Z'"
            , "yyyy-MM-dd'T'HH:mm:ss'Z'"
            , "yyyy-MM-dd"};

    private Date from;

    private Date until;

    private boolean includeData = true;

    private boolean includeInfo = true;

    private Integer limit;


    public TimeserieQueryDto() {
    }

    public void setFrom(String from) {

        try {
            this.from = DateUtils.parseDate(from, DATE_FORMATS);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Not supported date format");
        }
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(String until) {
        try {
            this.until = DateUtils.parseDate(until, DATE_FORMATS);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Not supported data format");
        }
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

    public Date getFrom() {
        return from;
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

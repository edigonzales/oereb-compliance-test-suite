package ch.so.agi.oereb.cts.probe;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Result {
    protected String className; 
    
    protected String description;

    protected boolean success = true;
    
    protected String message;
    
    @JacksonXmlCData
    protected String request;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected Instant startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected Instant endTime;
    
    protected double responseTimeSecs = -1;
    
    @JacksonXmlElementWrapper(localName = "checkResults")
    @JacksonXmlProperty(localName = "check")
    protected List<Result> results = new ArrayList<Result>();
    
    @JsonIgnore
    protected List<Result> resultsFailed = new ArrayList<Result>();
    
    protected Map<String,Object> reportMap;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public double getResponseTimeSecs() {
        return responseTimeSecs;
    }

    public void setResponseTimeSecs(double responseTimeSecs) {
        this.responseTimeSecs = responseTimeSecs;
    }
    
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResultsFailed() {
        return resultsFailed;
    }

    public void setResultsFailed(List<Result> resultsFailed) {
        this.resultsFailed = resultsFailed;
    }

    /* TODO: Momentan auskommentiert. Weiss nicht, ob das eher verwirrt.
     * 
     * Negative Resultate werden nach oben durchgereicht.
     * D.h. es reicht ein negatives Result, damit Probe
     * und Resource auch negativ sind.
     */
    public void addResult(Result result) {
        this.results.add(result);
                
        if (!result.success) {
            this.success = false;
            this.resultsFailed.add(result);
            //
            //this.message = this.resultsFailed.get(0).getMessage();
        } 
//        else {
//            this.success = true;
//            this.message = "OK";
//        }
        
        if (result.getResponseTimeSecs() >= 0) {
            if (this.responseTimeSecs == -1) {
                this.responseTimeSecs = 0;
            }
            this.responseTimeSecs += result.getResponseTimeSecs();
        }
    }
     
    public void start() {
        this.startTime = Instant.now();
    }
    
    public void stop() {
        this.endTime = Instant.now();
        
        this.responseTimeSecs = Duration.between(startTime, endTime).toSeconds();
    }    
}

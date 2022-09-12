package ch.so.agi.oereb.cts.probe;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.Builder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.so.agi.oereb.cts.check.Check;
import ch.so.agi.oereb.cts.check.CheckFactory;
import ch.so.agi.oereb.cts.model.CheckVars;
import ch.so.agi.oereb.cts.model.ProbeConfig;

public class Probe {
    final Logger log = LoggerFactory.getLogger(Probe.class);
    
    private ProbeConfig probeConfig;
    
    private ProbeResult probeResult;

    private String description;
    
    private String requestUrl;
    
    private List<CheckVars> checksVars;
    
    private HttpRequest request;
    
    private HttpResponse<?> response;
   
    public Probe(ProbeConfig probeConfig) {
        //this.requestUrl = requestUrl;
        this.description = probeConfig.description();
        this.probeConfig = probeConfig;
        this.checksVars = probeConfig.checksVars();
    }
    
    public void run() throws IOException, InterruptedException {        
        log.info("Performing: " + this.getClass().getCanonicalName());
        
        var subsitutor = new StringSubstitutor(probeConfig.requestParams());
        var resolvedRequestTemplate = subsitutor.replace(probeConfig.requestTemplate());
        requestUrl = URLDecoder.decode(probeConfig.serviceEndpoint() + "/" + resolvedRequestTemplate, StandardCharsets.UTF_8.name());
        requestUrl = fixUrl(requestUrl);

        probeResult = new ProbeResult(/*this*/);
        probeResult.setRequest(requestUrl);
        probeResult.setDescription(getDescription());
        probeResult.start();

        performRequest();
        runChecks(checksVars);
        
        probeResult.stop();     
    };
    
    private void performRequest() throws IOException, InterruptedException {
        var httpClient = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .followRedirects(Redirect.ALWAYS)
                .build();

        var requestBuilder = HttpRequest.newBuilder();
        requestBuilder.GET().uri(URI.create(requestUrl));

        var standardRequestHeaders = new HashMap<String, String>() {
            {
                put("User-Agent", "OerebComplianceTestSuite");
            }
        };

        for (Map.Entry<String, String> entry : standardRequestHeaders.entrySet()) {
            requestBuilder.setHeader(entry.getKey(), entry.getValue());
        }
        
        request = requestBuilder.build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            
//        System.out.println(response.body());
//        System.out.println(response.statusCode());
//        HttpHeaders headers = response.headers();
//        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

    };
    
    private void runChecks(List<CheckVars> checksVars) throws IOException {
        for (CheckVars checkVars : checksVars) {
            Check check = CheckFactory.getCheck(checkVars.checkClass());
            check.setProbe(this);
            check.perform(checkVars);

            probeResult.addResult(check.getResult());
        }
    }
    
    public ProbeResult getProbeResult() {
        return probeResult;
    }

    public HttpResponse<?> getResponse() {
        return response;
    }
    
    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        return this.description = description;
    }
    
    // Entfernt doppelte Slashes
    private String fixUrl(String url) {
        return url.replaceAll("(?<=[^:\\s])(\\/+\\/)", "/");
    }
}

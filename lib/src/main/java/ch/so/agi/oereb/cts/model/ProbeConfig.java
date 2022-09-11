package ch.so.agi.oereb.cts.model;

import java.util.List;
import java.util.Map;

public record ProbeConfig(String serviceEndpoint, String description, String requestTemplate, Map<String,String> requestParams, List<CheckVars> checksVars) {}

package ch.so.agi.oereb.cts.model;

import java.util.Map;

public record CheckVars(String checkClass, Map<String, Parameter> parameters) {}

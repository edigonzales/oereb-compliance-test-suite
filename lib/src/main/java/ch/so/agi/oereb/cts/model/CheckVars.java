package ch.so.agi.oereb.cts.model;

import java.util.List;
import java.util.Map;

public record CheckVars(String checkClass, Map<String, Parameter> parameters) {}

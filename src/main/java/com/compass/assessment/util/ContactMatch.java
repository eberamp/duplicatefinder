package com.compass.assessment.util;

public record ContactMatch(
        Long sourceId,
        Long matchId,
        MatchScore accuracy
){}

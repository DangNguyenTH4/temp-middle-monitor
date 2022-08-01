package com.sunteco.middlemonitor.util;

import com.sunteco.middlemonitor.constant.Query;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryBuilder {
    public static String buildQueryFormat(String query){
//        String a = query+"%s"
//        String.format()
        return null;

    }
    public static String buildUriQueryRange(Query query){
        String queryRange = String.format("/query_range?query=%s&start={startTime}&end={endTime}&step={step}",query.getQueryTemplate());
        log.info("Query range : {}", queryRange);
        return queryRange;
    }

    public static String buildUriQueryRangeV2(Query query){
        String queryRange = String.format("/query_range?start={start}&end={end}&step={step}&query=%s",
                query.getQueryTemplate());
        log.info("Query range : {}", queryRange);
        return queryRange;
    }
}

package com.homihq.db2rest.d1;

import com.homihq.db2rest.d1.model.D1PostResponse;
import com.homihq.db2rest.jdbc.core.DbOperationService;
import com.homihq.db2rest.core.exception.GenericDataAccessException;
import com.homihq.db2rest.jdbc.core.model.DbTable;
import com.homihq.db2rest.core.dto.CreateBulkResponse;
import com.homihq.db2rest.core.dto.CreateResponse;
import com.homihq.db2rest.jdbc.rest.read.dto.CountResponse;
import com.homihq.db2rest.jdbc.rest.read.dto.ExistsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class D1OperationService implements DbOperationService {

    private final D1RestClient d1RestClient;

    @Override
    public int update(Map<String, Object> paramMap, String sql) {

        D1PostResponse response =
                d1RestClient.callD1(sql,
                        paramMap.values().stream().toList());

        log.info("Response - {}", response);

        if(!response.success()) throw new GenericDataAccessException("Error updating data from D1 service.");

        return 1;
    }


    @Override
    public List<Map<String, Object>> read(Map<String, Object> paramMap, String sql) {
        log.info("SQL - {}", sql);

        D1PostResponse response =
        d1RestClient.callD1(sql,
                paramMap == null ? List.of() :
                paramMap.values().stream().toList());

        log.info("response - {}", response);

        if(!response.success()) throw new GenericDataAccessException("Error reading data from D1 service.");

        return response.result().get(0).results();
    }

    @Override
    public Map<String, Object> findOne(String sql, Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public ExistsResponse exists(Map<String, Object> paramMap, String sql) {
        return null;
    }

    @Override
    public CountResponse count(Map<String, Object> paramMap, String sql) {
        return null;
    }

    @Override
    public Object queryCustom(boolean single, String sql, Map<String, Object> params) {
        return null;
    }

    @Override
    public int delete(Map<String, Object> paramMap, String sql) {
        D1PostResponse response =
                d1RestClient.callD1(sql,
                        paramMap.values().stream().toList());

        log.info("Response - {}", response);

        if(!response.success()) throw new GenericDataAccessException("Error deleting data from D1 service.");

        return 1;
    }

    @Override
    public CreateResponse create(Map<String, Object> paramMap, String sql, DbTable dbTable) {

        D1PostResponse response =
        d1RestClient.callD1(sql,
                paramMap.values().stream().toList());

        log.info("Response - {}", response);

        if(!response.success()) throw new GenericDataAccessException("Error reading data from D1 service.");

        return new CreateResponse(1, null);


    }

    @Override
    public CreateBulkResponse batchUpdate(List<Map<String, Object>> dataList, String sql, DbTable dbTable) {
        return null;
    }

    @Override
    public CreateBulkResponse batchUpdate(List<Map<String, Object>> dataList, String sql) {
        return null;
    }
}

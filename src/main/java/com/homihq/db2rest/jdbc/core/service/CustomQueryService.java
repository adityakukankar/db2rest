package com.homihq.db2rest.jdbc.core.service;

import com.homihq.db2rest.jdbc.rest.read.dto.QueryRequest;

public interface CustomQueryService {
    Object find(QueryRequest queryRequest);
}

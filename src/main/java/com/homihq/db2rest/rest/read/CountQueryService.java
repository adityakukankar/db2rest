package com.homihq.db2rest.rest.read;

import com.homihq.db2rest.dbop.JdbcOperationService;
import com.homihq.db2rest.exception.GenericDataAccessException;

import com.homihq.db2rest.rest.read.dto.CountResponse;
import com.homihq.db2rest.rest.read.processor.ReadProcessor;
import com.homihq.db2rest.rest.read.sql.QueryCreatorTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountQueryService {

    private final JdbcOperationService jdbcOperationService;

    private final List<ReadProcessor> processorList;
    private final QueryCreatorTemplate queryCreatorTemplate;

    public CountResponse count(com.homihq.db2rest.rest.read.dto.ReadContext readContext) {
        for (ReadProcessor processor : processorList) {
            processor.process(readContext);
        }

        String sql = queryCreatorTemplate.createCountQuery(readContext);
        log.info("{}", sql);
        log.info("{}", readContext.getParamMap());

        try {
            return jdbcOperationService.count(readContext.getParamMap(), sql);
        } catch (DataAccessException e) {
            log.error("Error in read op : " , e);
            throw new GenericDataAccessException(e.getMostSpecificCause().getMessage());
        }

    }




}

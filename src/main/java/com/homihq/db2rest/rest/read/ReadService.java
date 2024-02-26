package com.homihq.db2rest.rest.read;

import com.homihq.db2rest.dbop.JdbcOperationService;
import com.homihq.db2rest.exception.GenericDataAccessException;
import com.homihq.db2rest.rest.read.dto.ReadContext;
import com.homihq.db2rest.rest.read.sql.QueryCreatorTemplate;
import com.homihq.db2rest.rest.read.processor.ReadProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ReadService {

    private final JdbcOperationService jdbcOperationService;
    private final List<ReadProcessor> processorList;
    private final QueryCreatorTemplate queryCreatorTemplate;

    public Object findAll(ReadContext readContext) {
        for (ReadProcessor processor : processorList) {
            processor.process(readContext);
        }

        String sql = queryCreatorTemplate.createQuery(readContext);
        log.info("{}", sql);
        log.info("{}", readContext.getParamMap());

        try {
            return jdbcOperationService.read(readContext.getParamMap(), sql);
        } catch (DataAccessException e) {
            log.error("Error in read op : " , e);
            throw new GenericDataAccessException(e.getMostSpecificCause().getMessage());
        }
    }



}

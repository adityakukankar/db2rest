package com.homihq.db2rest.jdbc.rest.delete;

import com.homihq.db2rest.jdbc.core.service.DeleteService;
import com.homihq.db2rest.core.dto.DeleteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DeleteController implements DeleteRestApi {

    private final DeleteService deleteService;

    @Override
    public DeleteResponse delete(String tableName,
                                 String filter) {
        //TODO - Handle multi tenancy
        int rows = deleteService.delete(null, tableName, filter);
        log.debug("Number of rows deleted - {}", rows);
        return DeleteResponse.builder().rows(rows).build();
    }
}

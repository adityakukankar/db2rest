package com.homihq.db2rest.jdbc.core.service;

import com.homihq.db2rest.jdbc.rest.read.dto.ReadContext;

public interface ReadService {
    Object findAll(ReadContext readContext);
}

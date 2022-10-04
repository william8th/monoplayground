package com.williamheng.postgres;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface LockDao {

    @SqlQuery("""
            SELECT
            pg_backend_pid() AS process_id,
            pg_try_advisory_xact_lock(:lockId) AS locked
            """)
    @RegisterBeanMapper(LockRecord.class)
    LockRecord tryLock(@Bind("lockId") int lockId);

    @SqlQuery("SELECT pg_advisory_xact_unlock(?:0)")
    boolean unlock(int lockId);

    record LockRecord(int processId, boolean locked) {}

}

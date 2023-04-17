package fr.insee.sd44configuration.protect;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * Representation du parametrage d une datasource de connexion
 */
public final class DataSourceDefinition
{

    private Map<String, Object> parametres;

    /**
     * The fully qualified Java class name of the JDBC driver to be used.
     */
    private static final String DEFAULT_DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final String DRIVER_CLASS_NAME_LABEL = "driverClassName";

    /*
     * The dialect postgresql
     */
    private static final String DEFAULT_DIALECT_CLASS_NAME = "org.hibernate.dialect.PostgreSQLDialect";

    private static final String DIALECT_CLASS_NAME_LABEL = "dialect";

    /**
     * The maximum number of active connections that can be allocated from this pool at the same time, or non-positive for no limit.
     */
    private static final int DEFAULT_MAX_ACTIVE = GenericObjectPool.DEFAULT_MAX_ACTIVE;

    private static final String MAX_ACTIVE_LABEL = "maxActive";

    /**
     * The maximum number of connections that can remain idle in the pool, without extra ones being released, or negative for no limit.
     */
    private static final int DEFAULT_MAX_IDLE = GenericObjectPool.DEFAULT_MAX_IDLE;

    private static final String MAX_IDLE_LABEL = "maxIdle";

    /**
     * The minimum number of active connections that can remain idle in the pool, without extra ones being created, or 0 to create none.
     */
    private static final int DEFAULT_MIN_IDLE = GenericObjectPool.DEFAULT_MIN_IDLE;

    private static final String MIN_IDLE_LABEL = "minIdle";

    /**
     * The initial number of connections that are created when the pool is started.
     */
    private static final int DEFAULT_INITIAL_SIZE = 0;

    private static final String INITIAL_SIZE_LABEL = "initialSize";

    /**
     * The maximum number of milliseconds that the pool will wait (when there are no available connections) for a connection to be returned before
     * throwing an exception, or -1 to wait indefinitely.
     */
    private static final long DEFAULT_MAX_WAIT = GenericObjectPool.DEFAULT_MAX_WAIT;

    private static final String MAX_WAIT_LABEL = "maxWait";

    /**
     * Prepared statement pooling for this pool.
     */
    private static final boolean DEFAULT_POOL_PREPARED_STATEMENTS = false;

    private static final String POOL_PREPARED_STATEMENTS_LABEL = "poolPreparedStatements";

    /**
     * The maximum number of open statements that can be allocated from the statement pool at the same time, or non-positive for no limit. Since a
     * connection usually only uses one or two statements at a time, this is mostly used to help detect resource leaks.
     */
    private static final int DEFAULT_MAX_OPEN_PREPARED_STATEMENTS = GenericKeyedObjectPool.DEFAULT_MAX_TOTAL;

    private static final String MAX_OPEN_PREPARED_STATEMENTS_LABEL = "maxOpenPreparedStatements";

    /**
     * The indication of whether objects will be validated before being borrowed from the pool. If the object fails to validate, it will be dropped
     * from the pool, and we will attempt to borrow another.
     */
    private static final boolean DEFAULT_TEST_ON_BORROW = true;

    private static final String TEST_ON_BORROW_LABEL = "testOnBorrow";

    /**
     * The indication of whether objects will be validated before being returned to the pool.
     */
    private static final boolean DEFAULT_TEST_ON_RETURN = false;

    private static final String TEST_ON_RETURN_LABEL = "testOnReturn";

    /**
     * The number of milliseconds to sleep between runs of the idle object evictor thread. When non-positive, no idle object evictor thread will be
     * run.
     */
    private static final long DEFAUT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = GenericObjectPool.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;

    private static final String TIME_BETWEEN_EVICTION_RUNS_MILLIS_LABEL = "timeBetweenEvictionRunsMillis";

    /**
     * The number of objects to examine during each run of the idle object evictor thread (if any).
     */
    private static final int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = GenericObjectPool.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;

    private static final String NUM_TESTS_PER_EVICTION_RUN_LABEL = "numTestsPerEvictionRun";

    /**
     * The minimum amount of time an object may sit idle in the pool before it is eligable for eviction by the idle object evictor (if any).
     */
    private static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = GenericObjectPool.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;

    private static final String MIN_EVICTABLE_IDLE_TIME_MILLIS_LABEL = "minEvictableIdleTimeMillis";

    /**
     * The indication of whether objects will be validated by the idle object evictor (if any). If an object fails to validate, it will be dropped
     * from the pool.
     */
    private static final boolean DEFAULT_TEST_WHILE_IDLE = false;

    private static final String TEST_WHILE_IDLE_LABEL = "testWhileIdle";

    /**
     * The SQL query that will be used to validate connections from this pool before returning them to the caller. If specified, this query
     * <strong>MUST</strong> be an SQL SELECT statement that returns at least one row.
     */
    private static final String DEFAULT_VALIDATION_QUERY = null;

    private static final String VALIDATION_QUERY_LABEL = "validationQuery";

    /**
     * The default auto-commit state of connections created by this pool.
     */
    private static final boolean DEFAULT_DEFAULT_AUTO_COMMIT = true;

    private static final String DEFAULT_AUTO_COMMIT_LABEL = "defaultAutoCommit";

    /**
     * The default read-only state of connections created by this pool.
     */
    private static final Boolean DEFAULT_DEFAULT_READ_ONLY = null;

    private static final String DEFAULT_READ_ONLY_LABEL = "defaultReadOnly";

    /**
     * The default TransactionIsolation state of connections created by this pool.
     */
    private static final int DEFAULT_DEFAULT_TRANSACTION_ISOLATION = -1;

    private static final String DEFAULT_TRANSACTION_ISOLATION_LABEL = "defaultTransactionIsolation";

    /**
     * The default "catalog" of connections created by this pool.
     */
    private static final String DEFAULT_DEFAULT_CATALOG = null;

    private static final String DEFAULT_CATALOG_LABEL = "defaultCatalog";

    private static final Boolean DEFAULT_REMOVE_ABANDONED = false;

    private static final String REMOVE_ABANDONED_LABEL = "removeAbandoned";

    private static final int DEFAULT_REMOVE_ABANDONED_TIMEOUT = 300;

    private static final String REMOVE_ABANDONED_TIMEOUT_LABEL = "removeAbandonedTimeOut";

    private static final Boolean DEFAULT_LOG_ABANDONED = false;

    private static final String LOG_ABANDONED_LABEL = "logAbandoned";

    private static final String URL_LABEL = "url";

    private static final String USERNAME_LABEL = "username";

    private static final String MOTPASSE_LABEL = "password";

    public DataSourceDefinition()
    {
        parametres = new HashMap<>();
        parametres.put(DEFAULT_AUTO_COMMIT_LABEL, DEFAULT_DEFAULT_AUTO_COMMIT);
        parametres.put(DEFAULT_READ_ONLY_LABEL, DEFAULT_DEFAULT_READ_ONLY);
        parametres.put(DEFAULT_TRANSACTION_ISOLATION_LABEL, DEFAULT_DEFAULT_TRANSACTION_ISOLATION);
        parametres.put(DEFAULT_CATALOG_LABEL, DEFAULT_DEFAULT_CATALOG);
        parametres.put(DRIVER_CLASS_NAME_LABEL, DEFAULT_DRIVER_CLASS_NAME);
        parametres.put(DIALECT_CLASS_NAME_LABEL, DEFAULT_DIALECT_CLASS_NAME);
        parametres.put(MAX_ACTIVE_LABEL, DEFAULT_MAX_ACTIVE);
        parametres.put(MAX_IDLE_LABEL, DEFAULT_MAX_IDLE);
        parametres.put(MIN_IDLE_LABEL, DEFAULT_MIN_IDLE);
        parametres.put(INITIAL_SIZE_LABEL, DEFAULT_INITIAL_SIZE);
        parametres.put(MAX_WAIT_LABEL, DEFAULT_MAX_WAIT);
        parametres.put(POOL_PREPARED_STATEMENTS_LABEL, DEFAULT_POOL_PREPARED_STATEMENTS);
        parametres.put(MAX_OPEN_PREPARED_STATEMENTS_LABEL, DEFAULT_MAX_OPEN_PREPARED_STATEMENTS);
        parametres.put(TEST_ON_BORROW_LABEL, DEFAULT_TEST_ON_BORROW);
        parametres.put(TEST_ON_RETURN_LABEL, DEFAULT_TEST_ON_RETURN);
        parametres.put(TIME_BETWEEN_EVICTION_RUNS_MILLIS_LABEL, DEFAUT_TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        parametres.put(NUM_TESTS_PER_EVICTION_RUN_LABEL, DEFAULT_NUM_TESTS_PER_EVICTION_RUN);
        parametres.put(MIN_EVICTABLE_IDLE_TIME_MILLIS_LABEL, DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
        parametres.put(TEST_WHILE_IDLE_LABEL, DEFAULT_TEST_WHILE_IDLE);
        parametres.put(MOTPASSE_LABEL, null);
        parametres.put(URL_LABEL, null);
        parametres.put(USERNAME_LABEL, null);
        parametres.put(VALIDATION_QUERY_LABEL, DEFAULT_VALIDATION_QUERY);
        parametres.put(REMOVE_ABANDONED_LABEL, DEFAULT_REMOVE_ABANDONED);
        parametres.put(REMOVE_ABANDONED_TIMEOUT_LABEL, DEFAULT_REMOVE_ABANDONED_TIMEOUT);
        parametres.put(LOG_ABANDONED_LABEL, DEFAULT_LOG_ABANDONED);

    }

    // ----------------------------------------------------- Methodes publiques

    /**
     * Transforme ce bean en objet Properties.
     * 
     * @return les proprietes correspondant a ce bean
     */
    public Properties toProperties()
    {
        Properties proprietes = new Properties();
        for (Map.Entry<String, Object> parametre : parametres.entrySet())
        {
            if (parametre.getValue() != null)
                proprietes.setProperty(parametre.getKey(), parametre.getValue().toString());
        }
        return proprietes;
    }

    public Map<String, Object> getParametresMap()
    {
        return parametres;
    }
}

package fr.insee.sd44configuration.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.Stoppable;

import fr.insee.sd44configuration.SD44Configuration;
import fr.insee.sd44configuration.exception.InseeDataSourceException;
import fr.insee.sd44configuration.protect.LogBloc;

/**
 * Classe implementant les interfaces de connexion pour Hibernate 4.3.x ou 5.4.x
 */
@SuppressWarnings("serial")
public class SD44HibernateConnectionProvider implements ConnectionProvider, Configurable, Stoppable
{
    /** Logger de cette classe. */
    private static final Logger LOGGER = LogManager.getLogger(SD44HibernateConnectionProvider.class);

    /** Cle de configuration pour le nom du pool a utiliser. */
    public static final String POOL_NAME_KEY = "hibernate.connection.insee.poolName";

    /** DataSource utilisee en interne pour creer des connexions. */
    private transient BasicDataSource basicDS;

    /**
     * Contructeur
     */
    public SD44HibernateConnectionProvider()
    {
        LogBloc.debug(LOGGER, "Initialisation de SD44HibernateConnectionProvider");
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs(Class unwrapType)
    {
        return ConnectionProvider.class.equals(unwrapType) || SD44HibernateConnectionProvider.class.isAssignableFrom(unwrapType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> unwrapType)
    {
        if (ConnectionProvider.class.equals(unwrapType) || SD44HibernateConnectionProvider.class.isAssignableFrom(unwrapType))
            return (T) this;
        else
            throw new UnknownUnwrapTypeException(unwrapType);
    }

    /**
     * Obtention d une connexion
     */
    @Override
    public Connection getConnection() throws SQLException
    {
        LogBloc.debug(LOGGER, "Entree dans getConnection");
        Connection conn = null;
        try
        {
            conn = basicDS.getConnection();
        }
        finally
        {
            logStatistics();
        }
        return conn;
    }

    /**
     * Fermeture de la connexion obtenue
     */
    @Override
    public void closeConnection(Connection conn) throws SQLException
    {
        try
        {
            conn.close();
        }
        finally
        {
            logStatistics();
        }
    }

    @Override
    public boolean supportsAggressiveRelease()
    {
        return true;
    }

    /**
     * Configuration du pool qui est defini par une propriete dans le fichier de configuration hibernate
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void configure(Map configurationValues)
    {
        LogBloc.debug(LOGGER, "Entree dans configure");
        final String poolName = (String) configurationValues.get(POOL_NAME_KEY);

        LogBloc.debug(LOGGER, "Entree dans configure pour le pool " + poolName);
        if (StringUtils.isEmpty(poolName))
        {
            final String message = "Le nom du pool n existe pas";
            LOGGER.error(message);
            throw new HibernateException(message);
        }
        LogBloc.debug(LOGGER, "Injection du pool " + poolName + " dans Hibernate");

        try
        {
            this.basicDS = (BasicDataSource) SD44Configuration.getInstance().getDataSource(poolName);
        }
        catch (InseeDataSourceException e)
        {
            final String message = "Impossible de trouver le pool nomme : " + poolName;
            LOGGER.warn(message, e);
            throw new HibernateException(message, e);
        }
        catch (Exception e)
        {
            final String message = "Exception lors du chargement du pool nomme : " + poolName + " " + e.getMessage();
            LOGGER.error(message, e);
            throw new HibernateException(message, e);
        }

        if (this.basicDS == null)
        {
            String message = "Probleme lors du chargement du pool";
            LogBloc.error(LOGGER, message);
            throw new HibernateException(message);
        }
        LogBloc.debug(LOGGER, "Pool charge en cours");

        try
        {
            // The BasicDataSource has lazy initialization
            // borrowing a connection will start the DataSource
            // and make sure it is configured correctly.
            Connection conn = basicDS.getConnection();
            conn.close();
        }
        catch (SQLException e)
        {
            String message = "Impossible de chercher une connexion dans le pool";
            LOGGER.warn(message, e);
            try
            {
                basicDS.close();
            }
            catch (Exception e2)
            {
                LogBloc.error(LOGGER, "Anomalie de fermeture de la DataSource", e);
            }
            basicDS = null;
            throw new HibernateException(message, e);
        }

        LOGGER.info("Le pool a ete correctement mis en place");

    }

    /**
     * Arret et fermeture du provider
     */
    @Override
    public void stop()
    {
        LogBloc.debug(LOGGER, "Fermeture de SD44HibernateConnectionProvider");
        logStatistics();
        try
        {
            if (basicDS != null)
            {
                basicDS.close();
                basicDS = null;
            }
            else
            {
                LOGGER.warn("Impossible de fermer SD44HibernateConnectionProvider (non initialise)");
            }
        }
        catch (SQLException e)
        {
            final String message = "Impossible de fermer le pool de connexions";
            LOGGER.warn(message, e);
            throw new HibernateException(message, e);
        }
        LogBloc.debug(LOGGER, "Fermeture de SD44HibernateConnectionProvider terminee");
    }

    /**
     * Trace les statistiques d'utilisation du pool de connexions.
     */
    private void logStatistics()
    {
        LogBloc.trace(LOGGER, "active: " + basicDS.getNumActive() + " (max: " + basicDS.getMaxActive() + ")   " + "idle: " + basicDS.getNumIdle()
            + "(max: " + basicDS.getMaxIdle() + ")");
    }
}

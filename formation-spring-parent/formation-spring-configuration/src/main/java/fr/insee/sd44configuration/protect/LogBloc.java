package fr.insee.sd44configuration.protect;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Classe de reduction des bloc redondants test/action
 * 
 * @author Jaumotte Stephanie
 */
public class LogBloc
{
    private LogBloc()
    {
        // Constructeur hidden
    }

    public static void debug(Logger logger, String message)
    {
        if (logger.isDebugEnabled())
            logger.debug(message);
    }

    public static void debug(Logger logger, String message1, String message2)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(message1);
            logger.debug(message2);
        }
    }

    public static void trace(Logger logger, String message)
    {
        if (logger.isTraceEnabled())
            logger.trace(message);
    }

    public static void info(Logger logger, String message)
    {
        if (logger.isInfoEnabled())
            logger.info(message);
    }

    public static void warn(Logger logger, String message, Exception erreur)
    {
        if (logger.isWarnEnabled())
            logger.warn(message, erreur);
    }

    public static void warn(Logger logger, String message)
    {
        if (logger.isWarnEnabled())
            logger.warn(message);
    }

    public static void error(Logger logger, String message, Exception erreur)
    {
        if (logger.isErrorEnabled())
            logger.error(message, erreur);
    }

    public static void error(Logger logger, String message)
    {
        if (logger.isErrorEnabled())
            logger.error(message);
    }

    public static void custom(Logger logger, Level niveau, String message)
    {
        logger.log(niveau, message);
    }
}

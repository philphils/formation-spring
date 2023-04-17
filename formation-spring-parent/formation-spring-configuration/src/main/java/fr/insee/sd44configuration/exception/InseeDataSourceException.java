package fr.insee.sd44configuration.exception;

/**
 * Exception soulevee dans le cadre de la creation des datasources de connexions.
 */
@SuppressWarnings("serial")
public class InseeDataSourceException extends Exception
{

    /**
     * Constructeur simple.
     * 
     * @param arg0 message lie a cette exception
     */
    public InseeDataSourceException(String arg0)
    {
        super(arg0);
    }

    /**
     * Constructeur permettant le chainage des exceptions.
     * 
     * @param arg0 message lie a cette exception
     * @param arg1 exception ayant declenche cette exception
     */
    public InseeDataSourceException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
    }

}

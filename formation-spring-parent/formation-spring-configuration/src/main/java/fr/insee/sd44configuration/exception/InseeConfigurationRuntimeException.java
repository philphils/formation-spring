package fr.insee.sd44configuration.exception;

/**
 * Exception a l execution provoquee par le composant. <br>
 * Il s agit d une erreur non recuperable provoquee par le composant, qui doit normalement aboutir a l arret de l'application.
 * 
 * @author Denis Cabasson
 * @since 2007
 */
@SuppressWarnings("serial")
public class InseeConfigurationRuntimeException extends RuntimeException
{

    /**
     * Constructeur simple.
     * 
     * @param arg0 message lie a cette exception
     */
    public InseeConfigurationRuntimeException(String arg0)
    {
        super(arg0);
    }

    /**
     * Constructeur permettant le chainage des exceptions.
     * 
     * @param arg0 message lie a cette exception
     * @param arg1 exception ayant déclenche cette exception
     */
    public InseeConfigurationRuntimeException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
    }
}

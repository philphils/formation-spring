package fr.insee.sd44configuration.protect;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * Implementation d'une entree de configuration sous forme d'un fichier. <br>
 * Cette classe permet de voir un fichier de configuration sous la forme standardisee d'une entree de configuration.
 * 
 * @author Denis Cabasson
 * @author Insee 2007
 */
public final class FileEntryConfiguration implements EntryConfiguration
{

    // --------------------------------------------------- Variables d'instance

    /** Le fichier physique sous-jacent a cette entree. */
    private final File file;

    // ----------------------------------------------------------- Constructeur

    /**
     * Constructeur unique.
     * 
     * @param file le fichier properties e decorer.
     */
    public FileEntryConfiguration(File file)
    {
        this.file = file;
    }

    // ------------------------------------------ Implementation de ConfigurationEntry

    /**
     * {@inheritDoc}
     */
    public String getAbsolutePath()
    {
        return file.getAbsolutePath();
    }

    /**
     * {@inheritDoc}
     */
    public String getName()
    {
        return StringUtils.substringBeforeLast(file.getName(), Constantes.PROPERTIES_FILE_EXTENSION);
    }

    /**
     * {@inheritDoc}
     */
    public Configuration toConfig() throws ConfigurationException
    {
        return new PropertiesConfiguration(file);
    }

}

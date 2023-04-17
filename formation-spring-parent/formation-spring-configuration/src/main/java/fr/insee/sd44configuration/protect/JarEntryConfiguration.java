package fr.insee.sd44configuration.protect;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation d'une entree de configuration sous la forme d'une entree d'un fichier JAR. <br>
 * Cette classe permet de voir un fichier de configuration contenu dans un jar sous la forme standardisee d'une entree de configuration.
 * 
 * @author Denis Cabasson
 * @author Insee 2007
 */
public final class JarEntryConfiguration implements EntryConfiguration
{

    // ---------------------------------------------------- Variables statiques

    /** Logger de cette classe. */
    private static final Logger LOGGER = LogManager.getLogger(JarEntryConfiguration.class);

    // --------------------------------------------------- Variables d'instance

    /**
     * Le fichier Jar contenant cette entree.
     */
    private final JarFile jarFile;

    /**
     * Le chemin de l'entree a l'intzrieur du fichier Jar.
     */
    private final JarEntry jarEntry;

    // ----------------------------------------------------------- Constructeur

    /**
     * Constructeur unique.
     * 
     * @param jarFile le fichier jar contenant l'entree
     * @param jarEntry chemin dans le jar de l'entree
     */
    public JarEntryConfiguration(JarFile jarFile, JarEntry jarEntry)
    {
        this.jarFile = jarFile;
        this.jarEntry = jarEntry;
    }

    // ------------------------------------------ Implementation de ConfigurationEntry

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAbsolutePath()
    {
        return "jar:" + jarFile.getName() + "!/" + jarEntry.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration toConfig() throws ConfigurationException
    {
        PropertiesConfiguration conf;
        try
        {
            final InputStream is = jarFile.getInputStream(jarEntry);
            conf = new PropertiesConfiguration();
            conf.load(is);
            is.close();
        }
        catch (IOException e)
        {
            final String message = "Erreur de lecture de l'entree : " + this.getAbsolutePath();
            LOGGER.warn(message, e);
            throw new ConfigurationException(message, e);
        }
        return conf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        final String name = jarEntry.getName();
        return name.substring(name.lastIndexOf('/') + 1, name.lastIndexOf(Constantes.PROPERTIES_FILE_EXTENSION));
    }

}

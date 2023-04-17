package fr.insee.sd44configuration.protect;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;

/**
 * Interface definissant une entree de configuration.<br>
 * Une entree de configuration est entendu comme un element participant e la configuration de l'application.
 * 
 * @author Denis Cabasson
 * @author Insee 2007
 */
public interface EntryConfiguration
{

    /**
     * Transforme cette entree en element de configuration.
     * 
     * @return la configuration issue de cette entree
     * @throws ConfigurationException en cas d'erreur de recuperation de la configuration
     */
    Configuration toConfig() throws ConfigurationException;

    /**
     * Retourne le chemin complet d'acces e cet element de configuration.
     * 
     * @return le chemin complet d'acces e cette entree
     */
    String getAbsolutePath();

    /**
     * Retourne le nom de l'entree. <br>
     * Il s'agit du nom selon lequel les entrees vont etre triees.
     * 
     * @return le nom de l'entree
     */
    String getName();
}

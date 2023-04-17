package fr.insee.sd44configuration;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;

import fr.insee.sd44configuration.exception.InseeDataSourceException;

public final class SD44FinalConfiguration
{
    /** La configuration contenue dans cette classe. */
    private static final SD44Configuration configuration = SD44Configuration.getInstance();

    /**
     * Constructeur
     */
    private SD44FinalConfiguration()
    {
        // Vide
    }

    // ******************* GETTER SETTER

    public static Configuration getConfiguration()
    {
        return configuration.getConfiguration();
    }

    // ----------------------------- Methodes publiques

    /**
     * Retourne la DataSource correspondant au nom passe en parametre
     *
     * @param name nom de la DataSource souhaite
     * @return DataSource correspondant au nom fourni
     */
    public static DataSource getDataSource(String name) throws InseeDataSourceException
    {
        return configuration.getDataSource(name);
    }

    /**
     * Fonction retournant une connection du DataSource nomme
     */
    public static Connection getConnection(String name) throws InseeDataSourceException
    {
        return configuration.getConnection(name);
    }

}

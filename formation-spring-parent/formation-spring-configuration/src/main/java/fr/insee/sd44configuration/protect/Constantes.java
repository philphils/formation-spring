package fr.insee.sd44configuration.protect;

/**
 * Classe de regroupement des constantes (fin de redondance)
 * 
 * @author Jaumotte Stephanie
 */
public final class Constantes
{
    /** Cle sous laquelle est stocke le chemin des fichiers de configuration. */
    public static final String PROPERTIES_PATH_KEY = "properties.path";

    /** Expression reguliere appliquee pour la recherche de fichiers properties. */
    public static final String PROPERTIES_PATH = "fr/insee/config/";

    /** Prefixe utilise par les proprietes INSEE dans les proprietes systeme. */
    public static final String INSEE_SYSTEM_PREFIX = "fr.insee.";

    /** Prefixe utilise par les proprietes INSEE dans les proprietes systeme. */
    public static final String INSEE_SYSTEM_LOG4J2_SUFFIX = ".log.configuration";

    public static final String PROPERTIES_FILE_LOG4J2 = "log4j2.xml";

    /** Expression reguliere appliquee pour la recherche de fichiers properties. */
    public static final String PROPERTIES_FILE_EXTENSION = ".properties";

    /** Le prefixe des cles de configuration concernant les datasources */
    public static final String INSEE_DATASOURCE_PREFIX = "fr.insee.database";

    /** Cle de configuration pour le nom du pool a utiliser. */
    public static final String POOL_NAME_KEY = "hibernate.connection.insee.poolName";

    /** Propriete enviroronmnt contenant le repertoire base de Tomcat. */
    public static final String TOMCAT_BASE_ENV = "CATALINA_BASE";

    /** Propriete systeme contenant le repertoire base de Tomcat. */
    public static final String TOMCAT_BASE_SYS = "catalina.base";

    /** Le nomdu repertoire webapps dans le tomcat_base. */
    public static final String WEBAPPS_DIR = "webapps";

    /** Le nom du repertoire contenant la configuration dans le tomcat_base. */
    public static final String LOCALHOST_DIR = "conf/Catalina/localhost";

    private Constantes()
    {
        // do nothing
    }

}

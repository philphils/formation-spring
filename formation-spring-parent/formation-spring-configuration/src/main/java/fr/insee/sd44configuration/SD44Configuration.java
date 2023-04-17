package fr.insee.sd44configuration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import fr.insee.sd44configuration.exception.InseeConfigurationRuntimeException;
import fr.insee.sd44configuration.exception.InseeDataSourceException;
import fr.insee.sd44configuration.protect.ConfigurationReadOnly;
import fr.insee.sd44configuration.protect.Constantes;
import fr.insee.sd44configuration.protect.DataSourceDefinition;
import fr.insee.sd44configuration.protect.EntryConfiguration;
import fr.insee.sd44configuration.protect.Utilitaires;

public class SD44Configuration
{
    /** Logger de cette classe. */
    private static final Logger LOGGER = LogManager.getLogger(SD44Configuration.class);

    /** La configuration contenue dans cette classe. */
    private final CompositeConfiguration configurationComposite;

    /** La configuration dans cette classe, en lecture seule. */
    private final ConfigurationReadOnly configurationLectureSeule;

    // Sonarlint modification
    private static final String MOTPASSE_LABEL_EN = "password";

    private static final String MOTPASSE_LABEL_FR = "motdepasse";

    // ***** Specifique pour LOG4J2
    /** Liste des variables d'environnement ajoutees temporairement */
    private static List<String> listeVariableEnvironnement = new ArrayList<>();

    // ***** Specifique au DataSources
    /** Map contenant les DataSources, referencees par leur nom. */
    private static Map<String, DataSource> sources;

    private static Map<String, Map<String, Object>> sourcesParam;

    /*
     * ---------------------------------------------------------------------- CONSTRUCTEUR (singleton)
     */

    /**
     * Constructeur
     */
    private SD44Configuration()
    {
        this.configurationComposite = new CompositeConfiguration();
        this.configurationLectureSeule = new ConfigurationReadOnly(configurationComposite);
        try
        {
            initConfiguration();
            initLog();
            affichageProperties();
            setSources(new HashMap<>());
            setSourcesParam(new HashMap<>());
            initPropertiesDataSources();
        }
        catch (ConfigurationException e)
        {
            String message = "Erreur lors de l'initialisation de la configuration applicative";
            LOGGER.error(message, e);
            throw new InseeConfigurationRuntimeException(message, e);
        }
    }

    /** Instance unique pre-initialisee. */
    private static SD44Configuration lbINSTANCE;

    /**
     * Point d acces pour l instance unique du singleton.
     *
     * @return LienBase
     */
    public static SD44Configuration getInstance()
    {
        if (lbINSTANCE == null)
            lbINSTANCE = new SD44Configuration();
        return lbINSTANCE;
    }

    // ******************* GETTER SETTER

    public final Configuration getConfiguration()
    {
        return this.configurationLectureSeule;
    }

    private static void setSources(Map<String, DataSource> nvsources)
    {
        sources = nvsources;
    }

    private static Map<String, DataSource> getSources()
    {
        return sources;
    }

    private static void setSourcesParam(Map<String, Map<String, Object>> nvsourcesparam)
    {
        sourcesParam = nvsourcesparam;
    }

    public static Map<String, Map<String, Object>> getSourcesParam()
    {
        return sourcesParam;
    }

    // ----------------------------------------------------- Methodes protegees

    /**
     * Initialise la configuration applicative contenue dans ce composant.
     * 
     * @throws ConfigurationException en cas d'erreur d'instanciation de la configuration applicative
     */
    private final void initConfiguration() throws ConfigurationException
    {
        LOGGER.debug("Debut du chargement de la configuration applicative");
        addConfiguration(this.initSystemConfiguration(), "SYSTEM");
        addConfiguration(this.initProductionConfiguration(), "PRODUCTION");
        addConfiguration(this.initDevelopmentConfiguration(), "DEVELOPPEMENT");
        LOGGER.info("La configuration applicative a été correctement chargée");
        traceConfiguration(this.configurationComposite, "FINALE");
    }

    /**
     * Ajoute les configurations non nulles a la configuration courante.
     * 
     * @param config l'element de configuration a ajouter
     */
    private final void addConfiguration(Configuration config, String prefixe)
    {
        if (config != null && !config.isEmpty())
        {
            this.configurationComposite.addConfiguration(config);
            traceConfiguration(config, prefixe);
        }
    }

    /**
     * Trace la liste de toutes les proprietes de la configuration.
     * 
     * @param config la configuration a tracer
     * @param prefixe prefixe message (sert surtout pour JUnit)
     */
    public static void traceConfiguration(Configuration config, String prefixe)
    {
        if (LOGGER.isTraceEnabled())
        {
            LOGGER.trace("*** Affichage des valeurs stockees dans l objet Configuration {} ***", prefixe);
            for (Iterator<String> keys = config.getKeys(); keys.hasNext();)
            {
                String key = keys.next();
                LOGGER.trace("{} Valeur de la propriete {} : {}", prefixe, key,
                    key.contains(MOTPASSE_LABEL_EN) || key.contains(MOTPASSE_LABEL_FR) ? "****" : config.getProperty(key));
            }
        }
    }

    /**
     * Affiche l ensemble des proprietes contenue dans la configuration
     */
    private final void affichageProperties()
    {
        // Niveau "custom" d affichage des proprietes (superieur a FATAL = 100, OFF = 0)
        Level levelCustom = Level.forName("PARAMETRAGE", 99);
        affichageProperties(levelCustom, false);
        affichageProperties(levelCustom, true);
    }

    /**
     * Affiche l ensemble des proprietes contenue dans la configuration
     * 
     * @param levelCustom Niveau "custom" d affichage des proprietes (superieur a FATAL = 100, OFF = 0)
     * @param insee type de proprietes (insee ou non)
     */
    private final void affichageProperties(Level levelCustom, boolean insee)
    {
        // Niveau "custom" d affichage des proprietes (superieur a FATAL = 100, OFF = 0)
        LOGGER.log(levelCustom, "****** AFFICHAGE des parametres : {}INSEE ******", (insee ? "" : "NON "));
        Iterator<String> iterCles = getConfiguration().getKeys();
        while (iterCles.hasNext())
        {
            String cle = iterCles.next();
            if (!StringUtils.isEmpty(cle) &&
                ((!insee && !cle.startsWith(Constantes.INSEE_SYSTEM_PREFIX))
                    || (insee && cle.startsWith(Constantes.INSEE_SYSTEM_PREFIX))))
                LOGGER.log(levelCustom, "PROPRIETE : {} = {}", cle,
                    cle.contains(MOTPASSE_LABEL_EN) || cle.contains(MOTPASSE_LABEL_FR) ? "****" : getConfiguration().getProperty(cle));
        }
        if (!insee)
            LOGGER.log(levelCustom, "PROPRIETE (SYSTEM) : {} = {}", Constantes.PROPERTIES_PATH_KEY,
                System.getProperty(Constantes.PROPERTIES_PATH_KEY));
    }

    /**
     * Construit la configuration issue des proprietes systeme. <br>
     * Cette methode parcours les proprietes systeme et conserve celle qui commence par {@link #INSEE_SYSTEM_PREFIX}.
     * 
     * @return la configuration issue des proprietes systeme
     */
    private final Configuration initSystemConfiguration()
    {
        final Properties systemProperties = System.getProperties();
        @SuppressWarnings("unchecked")
        final Enumeration<String> enumsProperties = (Enumeration<String>) systemProperties.propertyNames();
        final Map<String, Object> configSystem = new HashMap<>();
        while (enumsProperties.hasMoreElements())
        {
            String key = enumsProperties.nextElement();
            LOGGER.trace("Analyse de la propriete systeme : {}", key);
            if (key.startsWith(Constantes.INSEE_SYSTEM_PREFIX))
                configSystem.put(key, systemProperties.get(key));
        }
        LOGGER.trace("Injection de {} propriete(s) systeme dans la configuration applicative", configSystem.size());
        return (new MapConfiguration(configSystem));
    }

    /**
     * Construit la configuration issue des fichiers de developpement. <br>
     * Cette methode prends en compte tous les fichiers contenus dans le classpath (y compris dans les jars) qui :
     * <ul>
     * <li>sont dans le sous-repertoire {@link #PROPERTIES_PATH}</li>
     * <li>ont l'extension {@link #PROPERTIES_FILE_EXTENSION}</li>
     * </ul>
     * Les fichiers dans les sous repertoires du repertoire ci-dessus ne sont pas pris compte.<br>
     * Les fichiers de configuration sont pris en compte dans l'ordre alphabetique de leurs noms (sans l'extension).<br>
     * Le fait d'avoir dans le classpath deux fichiers de configuration ayant le meme nom est considere comme une erreur et soulevera une exception
     * {@link ConfigurationException}.
     * 
     * @return la configuration issue des fichiers de developpement
     * @throws ConfigurationException en cas d'erreur de chargement de la configuration
     */
    private final Configuration initDevelopmentConfiguration()
        throws ConfigurationException
    {
        Set<String> fileUrls = new TreeSet<>();
        Set<String> jarUrls = new TreeSet<>();
        final Map<String, EntryConfiguration> propEntries = new LinkedHashMap<>();

        try
        {
            final Enumeration<URL> configEnum = this.getClass().getClassLoader().getResources(Constantes.PROPERTIES_PATH);

            while (configEnum.hasMoreElements())
            {
                URL configURL = configEnum.nextElement();
                LOGGER.trace("Recherche de configuration dans {} protocol {}", configURL, configURL.getProtocol());
                if ("jar".equals(configURL.getProtocol()))
                {
                    jarUrls.add(configURL.toString());
                }
                else
                {
                    fileUrls.add(configURL.toString());
                }
            }
            Utilitaires.addUniqueConfigEntriesFromFiles(propEntries, fileUrls);
            Utilitaires.addUniqueConfigEntriesFromJars(propEntries, jarUrls);
        }
        catch (IOException e)
        {
            final String message = "Erreur I/O lors de la lecture des fichiers de configuration";
            LOGGER.warn(message, e);
            throw new ConfigurationException(message, e);
        }
        catch (URISyntaxException e)
        {
            final String message = "Erreur d'adressage du fichier de configuration";
            LOGGER.warn(message, e);
            throw new ConfigurationException(message, e);
        }

        // Chargement des fichiers de configuration retenus
        return Utilitaires.buildConfigFromEntries(propEntries.values());
    }

    /**
     * Retourne la configuration issue des fichiers present dans le repertoire de configuration. <br>
     * Le repertoire de configuration est donne par la propriete {@link #PROPERTIES_PATH_KEY} des variables System Java.
     * 
     * @return la configuration issus des fichiers de production
     * @throws ConfigurationException en cas d'erreur de chargement de la configuration
     */
    private Configuration resolvePropertiesPath() throws ConfigurationException
    {
        final String path = System.getProperty(Constantes.PROPERTIES_PATH_KEY);

        // S'il n'y a pas de configuration specifique a la production
        if (path == null)
        {
            LOGGER.warn("La variable {} n'est pas initialisee : la configuration de production n'est pas prise en compte",
                Constantes.PROPERTIES_PATH_KEY);
            return null;
        }

        File configDir = new File(path);

        // si le repertoire de configuration n'est pas valide
        if (!(configDir.exists() && configDir.isDirectory()))
        {
            final String message = "Le repertoire " + path + " n'est pas valide";
            LOGGER.warn(message);
            throw new ConfigurationException(message);
        }
        return Utilitaires.buildConfFromDir(configDir);
    }

    // ----------------------------------------------------- Methodes Tomcat

    /**
     * Initialise la configuration de production dans un contexte web. <br>
     * Dans le cadre d'une application web, la configuration est composee de :
     * <ul>
     * <li>Les fichiers .properties du repertoire donne par <code>properties.path</code></li>
     * <li>Les fichiers .properties du repertoire {@link #LOCALHOST_DIR} situe dans CATALINA_BASE</li>
     * <li>Les fichiers .properties du repertoire {@link #WEBAPPS_DIR} situe dans CATALINA_BASE</li>
     * </ul>
     * Si la variable CATALINA_BASE n'est pas disponible dans l'environnement d'execution courant, le composant soulevera une exception.
     * 
     * @return la configuration de production de l'application
     */
    private Configuration initProductionConfiguration()
        throws ConfigurationException
    {
        CompositeConfiguration config = new CompositeConfiguration();

        // Ajout des fichiers issus du repertoire properties.path
        final Configuration propConfig = resolvePropertiesPath();

        if (propConfig != null && !propConfig.isEmpty())
        {
            LOGGER.info("Prise en compte de la configuration issue du repertoire properties.path");
            traceConfiguration(propConfig, "properties.path");
            config.addConfiguration(propConfig);
        }

        // Verification de la validite de Tomcat_base
        String tomcatPath = System.getProperty(Constantes.TOMCAT_BASE_SYS);
        if (tomcatPath == null)
        {
            tomcatPath = System.getenv(Constantes.TOMCAT_BASE_ENV);
            if (tomcatPath == null) // warn => info pour batch
            {
                LOGGER.info("Impossible de trouver le repertoire de base Tomcat :"
                    + " La configuration de production issue de Tomcat ne sera pas chargee.");
                return config;
            }
        }

        final File tomcatBase = new File(tomcatPath);
        if (!(tomcatBase.exists() && tomcatBase.isDirectory()))
        {
            final String message = "Erreur de recherche du TOMCAT_BASE";
            LOGGER.warn(message);
            throw new ConfigurationException(message);
        }
        LOGGER.debug("Le repertoire de base Tomcat : {} est pris en compte", tomcatBase.getAbsolutePath());

        // Ajout de la configuration issue du repertoire localhost
        final Configuration localhostConf = configurationLocalhost(tomcatBase);
        if (localhostConf != null && !localhostConf.isEmpty())
        {
            LOGGER.info("Prise en compte de la configuration issue du repertoire " + Constantes.LOCALHOST_DIR);
            traceConfiguration(localhostConf, "localhostConf");
            config.addConfiguration(localhostConf);
        }

        // Ajout de la configuration issue du repertoire webappas
        final Configuration webappsConf = configurationWebApp(tomcatBase);
        if (webappsConf != null && !webappsConf.isEmpty())
        {
            LOGGER.info("Prise en compte de la configuration issue du repertoire " + Constantes.WEBAPPS_DIR);
            traceConfiguration(webappsConf, "webappsConf");
            config.addConfiguration(webappsConf);
        }

        return config;
    }

    /**
     * Configuration issue du repertoire localhost
     * 
     * @param tomcatBase
     * @return
     * @throws ConfigurationException
     */
    private Configuration configurationLocalhost(File tomcatBase) throws ConfigurationException
    {
        // Ajout de la configuration issue du repertoire localhost
        File localhostDir = new File(tomcatBase, Constantes.LOCALHOST_DIR);
        if (!localhostDir.exists() || !localhostDir.isDirectory())
        {
            LOGGER.info("Le repertoire de configuration locale " + Constantes.LOCALHOST_DIR + " de Tomcat n'existe pas");
            return null;
        }
        else
        {
            return Utilitaires.buildConfFromDir(localhostDir);
        }
    }

    /**
     * Configuration issue du repertoire webapps
     * 
     * @param tomcatBase
     * @return
     * @throws ConfigurationException
     */
    private Configuration configurationWebApp(File tomcatBase) throws ConfigurationException
    {
        File webappsDir = new File(tomcatBase, Constantes.WEBAPPS_DIR);
        if (!webappsDir.exists() || !webappsDir.isDirectory())
        {
            final String message = "Le repertoire " + Constantes.WEBAPPS_DIR + " de Tomcat est inexistant.";
            LOGGER.warn(message);
            throw new ConfigurationException(message);
        }
        return Utilitaires.buildConfFromDir(new File(tomcatBase, Constantes.WEBAPPS_DIR));
    }

    // ******************************************** Chargeur Log4j2

    // ----------------------- Methodes

    /**
     * Recherche la cle contenant l emplacement du fichier de parametre log4j2 a charger, null si absent
     * 
     * @return Cle
     */
    private String rechercheCleLog()
    {
        Iterator<String> iterCles = getConfiguration().getKeys();
        while (iterCles.hasNext())
        {
            String cle = iterCles.next();
            if (!StringUtils.isEmpty(cle) && cle.startsWith(Constantes.INSEE_SYSTEM_PREFIX)
                && cle.endsWith(Constantes.INSEE_SYSTEM_LOG4J2_SUFFIX))
                return cle;
        }
        return null;
    }

    /**
     * Initialisation de log4j2 a partir du fichier indique dans la configuration ou recherche dans le package fr.insee.config
     */
    private final void initLog()
    {
        // permet d utiliser les ${} dans LOG4J2.XML
        creationVariablesEnv();
        String cle = this.rechercheCleLog();
        LOGGER.trace("cle config log4j2 : {}", cle);
        String cheminAccesFichierLog2Xml = null;
        if (cle != null)
        {
            cheminAccesFichierLog2Xml = getConfiguration().getString(cle);
            if (StringUtils.isEmpty(cheminAccesFichierLog2Xml))
                cheminAccesFichierLog2Xml = Constantes.PROPERTIES_PATH + Constantes.PROPERTIES_FILE_LOG4J2;
        }
        else
            cheminAccesFichierLog2Xml = Constantes.PROPERTIES_PATH + Constantes.PROPERTIES_FILE_LOG4J2;

        LOGGER.debug("Chemin de chargement config log4j2 : {}", cheminAccesFichierLog2Xml);
        URL logprops = this.getClass().getClassLoader().getResource(cheminAccesFichierLog2Xml);
        if (logprops != null)
        {
            LOGGER.debug("Configuration log4j2 ressource interne");
            // Ressource interne au projet : "log4j2.xml"
            if (new File(logprops.getFile()).exists())
                Configurator.initialize(null, logprops.getFile());
            else
                LOGGER.debug("Configuration log4j2 ressource interne non trouvee");
        }
        else
        {
            LOGGER.debug("Configuration log4j2 ressource externe");
            // Ressource externe au projet : "D:/Properties/log4j2.xml"
            if (new File(cheminAccesFichierLog2Xml).exists())
                Configurator.initialize(null, cheminAccesFichierLog2Xml);
            else
                LOGGER.debug("Configuration log4j2 ressource externe non trouvee");
        }
        // nettoie apres utilisation des ${} dans LOG4J2.XML
        suppressionVariablesEnv();
    }

    /**
     * Charge les proprietes insee dans des variables d environnement temporaire de la JVM
     */
    private void creationVariablesEnv()
    {
        LOGGER.debug("Definition des variables : creationVariablesEnv");
        Iterator<String> iterCles = getConfiguration().getKeys();
        while (iterCles.hasNext())
        {
            String cle = iterCles.next();
            if (!StringUtils.isEmpty(cle) && cle.startsWith(Constantes.INSEE_SYSTEM_PREFIX))
            {
                String variableEnvironnement = cle.substring(Constantes.INSEE_SYSTEM_PREFIX.length());
                if (System.getProperty(variableEnvironnement) == null)
                {
                    listeVariableEnvironnement.add(variableEnvironnement);
                    System.setProperty(variableEnvironnement, getConfiguration().getString(cle));
                    LOGGER.debug("Chargement temporaire de la variable d'environnement {}", variableEnvironnement);
                }
                else
                    LOGGER.debug("Valeur deja definie dans les variables d'environnement {}", variableEnvironnement);
            }
        }
    }

    /**
     * Supprime les variables d'environnement temporaires de la JVM
     */
    private void suppressionVariablesEnv()
    {
        for (String variableEnvironnement : listeVariableEnvironnement)
        {
            System.clearProperty(variableEnvironnement);
            LOGGER.debug("Suppression de la variable d'environnement temporaire {}", variableEnvironnement);
        }
    }

    /**
     * Ecriture d une ligne de log bidon (junit)
     */
    public static boolean getStatusTraceLogger()
    {
        return LOGGER.isTraceEnabled();
    }

    // --------------------------------------------- METHODES (AbstractPool)

    /**
     * Fonction initialisant les DataSources presents dans le fichier *.properties
     */
    private void initPropertiesDataSources()
    {
        String[] names = this.searchNames(getConfiguration().subset(Constantes.INSEE_DATASOURCE_PREFIX));
        for (String name : names)
        {
            if (!getSources().containsKey(name))
                ajouterNouveauDataSource(name);
            else
                LOGGER.warn("La DataSource {} est deja prise en compte. Sa configuration est ignoree.", name);
        }
    }

    /**
     * Cherche les noms des DataSources a instancier
     * 
     * @param config la configuration
     * @return la liste des noms de DataSources (par ordre alphabetique).
     */
    private final String[] searchNames(Configuration config)
    {
        final SortedSet<String> names = new TreeSet<>();
        for (Iterator<String> i = config.getKeys(); i.hasNext();)
        {
            final String key = i.next();
            final String name = (key.split("\\."))[0];
            names.add(name);
        }
        return names.toArray(new String[names.size()]);
    }

    /**
     * Fonction appelee par initPropertiesDataSources() creant les BasicDataSource du fichier .properties
     * 
     * @param name nom de la DataSource presente dans le fichier properties
     */
    private void ajouterNouveauDataSource(String name)
    {
        final DataSourceDefinition dsconf = new DataSourceDefinition();

        LOGGER.info("Configuration de la DataSource : {}", name);
        final Configuration conf = getConfiguration().subset(Constantes.INSEE_DATASOURCE_PREFIX).subset(name);
        Iterator<String> keys = conf.getKeys();
        Map<String, Object> params = dsconf.getParametresMap();
        while (keys.hasNext())
        {
            String param = keys.next();
            if (params.containsKey(param))
            {
                params.put(param, conf.getString(param));
                if (LOGGER.isDebugEnabled())
                {
                    LOGGER.debug("Parametre configuration Datasource '{}' : {}", param,
                        (MOTPASSE_LABEL_EN.equalsIgnoreCase(param) ? "*******" : conf.getString(param)));
                }
            }
            else
                LOGGER.warn("Le parametre {} ne correspond a aucun parametre de configuration implemente. {}, est ignoré", param, param);
        }

        instanciationDataSource(dsconf, params, name);
    }

    /**
     * Instanciation DataSource avec les parametres
     * 
     * @param dsconf
     * @param params
     * @param name
     */
    private void instanciationDataSource(DataSourceDefinition dsconf, Map<String, Object> params, String name)
    {
        if ((params.get(MOTPASSE_LABEL_EN) == null) || (params.get("url") == null) || (params.get("username") == null))
            LOGGER.warn("username, password ou url n est pas renseigne, la DataSource {} n est pas instanciee", name);
        else
        {
            try
            {
                getSourcesParam().put(name, params);
                getSources().put(name, BasicDataSourceFactory.createDataSource(dsconf.toProperties()));
                LOGGER.info("La DataSource : {} a ete correctement instanciee", name);
            }
            catch (Exception e)
            {
                final String message = "Erreur d'instanciation de la DataSource : " + name;
                LOGGER.error(message);
            }
        }
    }

    // ----------------------------- Methodes publiques

    /**
     * Retourne la DataSource correspondant au nom passe en parametre
     *
     * @param name nom de la DataSource souhaite
     * @return DataSource correspondant au nom fourni
     */
    public final DataSource getDataSource(String name) throws InseeDataSourceException
    {
        DataSource ds = getSources().get(name);
        if (ds == null)
        {
            final String message = "La DataSource '" + name + "' est inconnue";
            LOGGER.warn(message);
            throw new InseeDataSourceException(message);
        }
        return ds;
    }

    /**
     * Fonction retournant une connection du DataSource nomme
     */
    public final Connection getConnection(String name) throws InseeDataSourceException
    {
        try
        {
            return getDataSource(name).getConnection();
        }
        catch (SQLException e)
        {
            final String message = "Erreur de recherche de connection";
            LOGGER.warn(message, e);
            throw new InseeDataSourceException(message, e);
        }
    }

}

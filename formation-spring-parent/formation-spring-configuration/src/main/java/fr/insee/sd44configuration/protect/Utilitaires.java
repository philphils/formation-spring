package fr.insee.sd44configuration.protect;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Utilitaires
{
    /** Logger de cette classe. */
    private static final Logger LOGGER = LogManager.getLogger(Utilitaires.class);

    /** Filtre utilise pour trouver les fichiers <code>.properties</code>. */
    private static final FilenameFilter DEFAULT_FILE_FILTER = (dir, name) -> name.endsWith(Constantes.PROPERTIES_FILE_EXTENSION);

    private Utilitaires()
    {
        // Hidden
    }

    /**
     * Construit la configuration issue du repertoire de base.
     * 
     * @param configDir le repertoire de base dans lequel chercher la configuration
     * @return la configuration constituee par les fichiers de ce repertoire
     * @throws ConfigurationException en cas d'erreur de chargement de la configuration
     */
    public static Configuration buildConfFromDir(File configDir) throws ConfigurationException
    {
        // Ensemble ordonne de fichier (ordre alphabetique sur les noms sans extensions)
        LOGGER.info("Lecture du repertoire {}", configDir.getAbsolutePath());
        final SortedMap<String, EntryConfiguration> propFiles = new TreeMap<>();

        List<EntryConfiguration> entries = searchDirForConfig(configDir);

        addUniqueConfigEntries(propFiles, entries);

        return buildConfigFromEntries(propFiles.values());
    }

    /**
     * Retourne les fichiers de configuration contenus dans le repertoire
     * 
     * @param configDir le repertoire a parcourir
     * @return les entrees (fichiers) de configuration presents
     */
    public static List<EntryConfiguration> searchDirForConfig(File configDir)
    {
        final File[] files = configDir.listFiles(DEFAULT_FILE_FILTER);
        final List<EntryConfiguration> configFiles = new LinkedList<>();
        Set<File> orderedFiles = new TreeSet<>();
        if (files != null)
        {
            orderedFiles.addAll(Arrays.asList(files));
            for (Iterator<File> iterator = orderedFiles.iterator(); iterator.hasNext();)
            {
                File file = iterator.next();
                configFiles.add(new FileEntryConfiguration(file));
            }
        }
        return configFiles;
    }

    /**
     * Retourne les entrees de configuration contenus dans le fichier jar
     * 
     * @param configJar le fichier jar a parcourir
     * @return les entrees (dans le jar) de configuration presents
     */
    public static List<EntryConfiguration> searchJarForConfig(JarFile configJar)
    {
        List<EntryConfiguration> configJars = new LinkedList<>();
        Enumeration<JarEntry> jarEntries = configJar.entries();
        while (jarEntries.hasMoreElements())
        {
            final JarEntry entry = jarEntries.nextElement();
            final String name = entry.getName();
            boolean isValid = (entry.getSize() > 0);
            isValid = isValid && name.startsWith(Constantes.PROPERTIES_PATH);
            isValid = isValid && Constantes.PROPERTIES_PATH.equals(name.substring(0, name.lastIndexOf('/') + 1));
            isValid = isValid && name.endsWith(Constantes.PROPERTIES_FILE_EXTENSION);
            if (isValid)
                configJars.add(new JarEntryConfiguration(configJar, entry));
        }
        return configJars;
    }

    /**
     * Ajoute les entrees de configuration dans la Map. <br>
     * L ajout permet en particulier de verifier qu une entree de configuration ayant deja ce nom (sans extension) n est pas deja connu. Si une telle
     * entree existe, cette methode soulevera une exception {@link ConfigurationException}.
     * 
     * @param configMap la map des entrees de configuration deja prises en compte.
     * @param entries les entrees de configuration a prendre en compte
     * @throws ConfigurationException en cas d entree de configuration avec le meme nom deja pris en compte.
     */
    public static void addUniqueConfigEntries(final Map<String, EntryConfiguration> configMap, List<EntryConfiguration> entries)
        throws ConfigurationException
    {
        // On verifie qu il n y a pas de fichier avec ce nom
        for (EntryConfiguration entry : entries)
        {
            final String name = entry.getName();
            if (configMap.get(name) != null)
            {
                final String message = "L entree " + name + " est en double dans le chemin de classes";
                LOGGER.warn(message);
                LOGGER.debug("    Entree 1 : {}", configMap.get(name).getAbsolutePath());
                LOGGER.debug("    Entree 2 : {}", entry.getAbsolutePath());
                throw new ConfigurationException(message);
            }
            configMap.put(name, entry);
            LOGGER.debug("Prise en compte du fichier de developpement : {}", entry.getName());
            LOGGER.debug("    Chemin : {}", entry.getAbsolutePath());
        }
    }

    public static void addUniqueConfigEntriesFromFiles(Map<String, EntryConfiguration> propEntries, Set<String> fileUrls)
        throws URISyntaxException, ConfigurationException, MalformedURLException
    {
        for (String url : fileUrls)
        {
            URL url2 = new URL(url);
            final File configDir = new File(url2.toURI());
            List<EntryConfiguration> entries = searchDirForConfig(configDir);
            addUniqueConfigEntries(propEntries, entries);
        }
    }

    public static void addUniqueConfigEntriesFromJars(Map<String, EntryConfiguration> propEntries, Set<String> jarUrls)
        throws IOException, ConfigurationException
    {
        for (String url : jarUrls)
        {
            URL url2 = new URL(url);
            final JarURLConnection con = (JarURLConnection) url2.openConnection();
            con.setDefaultUseCaches(false);
            JarFile configJar = con.getJarFile();
            List<EntryConfiguration> entries = searchJarForConfig(configJar);
            addUniqueConfigEntries(propEntries, entries);
        }
    }

    /**
     * Construit une configuration a partir des entrees fournies.
     * 
     * @param bonEntries la liste des entrees de configuration
     * @return une configuration contenant l ensemble des entrees chargees.
     * @throws ConfigurationException en cas d erreur de chargement d une des entrees de configuration
     */
    public static CompositeConfiguration buildConfigFromEntries(Collection<EntryConfiguration> bonEntries) throws ConfigurationException
    {
        final CompositeConfiguration config = new CompositeConfiguration();
        for (EntryConfiguration entry : bonEntries)
        {
            LOGGER.debug("Prise en compte du fichier de configuration : {}", entry.getName());
            LOGGER.debug("    Chemin : {}", entry.getAbsolutePath());
            config.addConfiguration(entry.toConfig());
        }
        return config;
    }
}

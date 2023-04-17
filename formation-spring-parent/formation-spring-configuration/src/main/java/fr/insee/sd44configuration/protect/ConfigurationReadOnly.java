package fr.insee.sd44configuration.protect;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Decorateur de Configuration fonctionnant en lecture seule. <br>
 * L appel a une methode de modification de la configuration retournera {@link UnsupportedOperationException}.
 * 
 * @author Denis Cabasson
 * @author Insee 2007
 */
public final class ConfigurationReadOnly implements Configuration
{

    // ---------------------------------------------------- Variables statiques

    /** Logger de cette classe. */
    private static final Logger LOGGER = LogManager.getLogger(ConfigurationReadOnly.class);

    /** Message d'erreur souleve pour les operations non supportees. */
    private static final String ERROR_MESSAGE = "Operation impossible : cette configuration est en lecture seule";

    // --------------------------------------------------- Variables d'instance

    /** La configuration reelle, cachee par cet objet. */
    private final Configuration configurationLectureSeule;

    // ----------------------------------------------------------- Constructeur

    /**
     * Constructeur decorant une configuration existante.
     * 
     * @param configuration la configuration existante
     */
    public ConfigurationReadOnly(Configuration configuration)
    {
        this.configurationLectureSeule = configuration;
    }

    // ---------------------------------------- Implementation de Configuration

    /**
     * Configuration en lecture seule : operation non supportee. <br>
     * L'appel e cette methode soulevera une {@link UnsupportedOperationException}.
     */
    @Override
    public void addProperty(String key, Object value)
    {
        setProperty(key, value);
    }

    /**
     * Configuration en lecture seule : operation non supportee. <br>
     * L'appel e cette methode soulevera une {@link UnsupportedOperationException}.
     */
    @Override
    public void setProperty(String key, Object value)
    {
        LOGGER.warn(ERROR_MESSAGE);
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    /**
     * Configuration en lecture seule : operation non supportee. <br>
     * L'appel e cette methode soulevera une {@link UnsupportedOperationException}.
     */
    @Override
    public void clear()
    {
        LOGGER.warn(ERROR_MESSAGE);
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    /**
     * Configuration en lecture seule : operation non supportee. <br>
     * L'appel e cette methode soulevera une {@link UnsupportedOperationException}.
     */
    @Override
    public void clearProperty(String key)
    {
        LOGGER.warn(ERROR_MESSAGE);
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(String key)
    {
        return configurationLectureSeule.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getBigDecimal(String key)
    {
        return configurationLectureSeule.getBigDecimal(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getBigDecimal(String key, BigDecimal defaultValue)
    {
        return configurationLectureSeule.getBigDecimal(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getBigInteger(String key)
    {
        return configurationLectureSeule.getBigInteger(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigInteger getBigInteger(String key, BigInteger defaultValue)
    {
        return configurationLectureSeule.getBigInteger(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBoolean(String key)
    {
        return configurationLectureSeule.getBoolean(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getBoolean(String key, boolean defaultValue)
    {
        return configurationLectureSeule.getBoolean(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getBoolean(String key, Boolean defaultValue)
    {
        return configurationLectureSeule.getBoolean(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByte(String key)
    {
        return configurationLectureSeule.getByte(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getByte(String key, byte defaultValue)
    {
        return configurationLectureSeule.getByte(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte getByte(String key, Byte defaultValue)
    {
        return configurationLectureSeule.getByte(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(String key)
    {
        return configurationLectureSeule.getDouble(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDouble(String key, double defaultValue)
    {
        return configurationLectureSeule.getDouble(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getDouble(String key, Double defaultValue)
    {
        return configurationLectureSeule.getDouble(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(String key)
    {
        return configurationLectureSeule.getFloat(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getFloat(String key, float defaultValue)
    {
        return configurationLectureSeule.getFloat(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Float getFloat(String key, Float defaultValue)
    {
        return configurationLectureSeule.getFloat(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(String key)
    {
        return configurationLectureSeule.getInt(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInt(String key, int defaultValue)
    {
        return configurationLectureSeule.getInt(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getInteger(String key, Integer defaultValue)
    {
        return configurationLectureSeule.getInteger(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<String> getKeys()
    {
        return configurationLectureSeule.getKeys();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<String> getKeys(String prefix)
    {
        return configurationLectureSeule.getKeys(prefix);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getList(String key)
    {
        return configurationLectureSeule.getList(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getList(String key, List<?> defaultValue)
    {
        return configurationLectureSeule.getList(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(String key)
    {
        return configurationLectureSeule.getLong(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getLong(String key, long defaultValue)
    {
        return configurationLectureSeule.getLong(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getLong(String key, Long defaultValue)
    {
        return configurationLectureSeule.getLong(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getProperties(String key)
    {
        return configurationLectureSeule.getProperties(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getProperty(String key)
    {
        return configurationLectureSeule.getProperty(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getShort(String key)
    {
        return configurationLectureSeule.getShort(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public short getShort(String key, short defaultValue)
    {
        return configurationLectureSeule.getShort(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short getShort(String key, Short defaultValue)
    {
        return configurationLectureSeule.getShort(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(String key)
    {
        return configurationLectureSeule.getString(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(String key, String defaultValue)
    {
        return configurationLectureSeule.getString(key, defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getStringArray(String key)
    {
        return configurationLectureSeule.getStringArray(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty()
    {
        return configurationLectureSeule.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Configuration subset(String prefix)
    {
        return configurationLectureSeule.subset(prefix);
    }

}

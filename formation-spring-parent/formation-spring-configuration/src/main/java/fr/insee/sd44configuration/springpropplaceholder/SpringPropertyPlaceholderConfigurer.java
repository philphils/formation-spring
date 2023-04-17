package fr.insee.sd44configuration.springpropplaceholder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import fr.insee.sd44configuration.SD44Configuration;

/**
 * Cette classe permet de rendre disponibles les proprietes de la configuration pour Spring.
 *
 * @author FJR3O6
 * @author Jaumotte Stephanie
 */
public class SpringPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer
{

    public SpringPropertyPlaceholderConfigurer()
    {
        super();
        List<Properties> listProp = new ArrayList<>();
        Configuration conf = SD44Configuration.getInstance().getConfiguration();
        for (Iterator<String> iterator = conf.getKeys(); iterator.hasNext();)
        {
            String cle = iterator.next();
            Properties p = new Properties();
            p.setProperty(cle, conf.getString(cle));
            listProp.add(p);
        }
        super.setPropertiesArray(listProp.toArray(new Properties[] {}));
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue)
    {
        if (SD44Configuration.getInstance().getConfiguration().containsKey(propertyName))
        {
            return SD44Configuration.getInstance().getConfiguration().getString(propertyName);
        }
        return super.convertProperty(propertyName, propertyValue);
    }

}

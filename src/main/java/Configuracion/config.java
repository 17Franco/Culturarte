
package Configuracion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;


public class config {
    //ruta donde va estar el archivo de configuracion externo
    private static final String DIRECCION_CONFIGURACION= System.getProperty("user.home") + "/.Culturarte";
    //ruta completa con el file
    private static final String DIR_COMPLETA = DIRECCION_CONFIGURACION + "/config.properties";
    
    private static final config instance = new config();
    
    private Properties configProperties;
    
     private config() {
        verificarfile();
       
    }
     
    private void verificarfile() {
       try {
           File configDir = new File(DIRECCION_CONFIGURACION);
           File configFile = new File(DIR_COMPLETA);

            if (!configDir.exists()) {
                // Si la carpeta NO existe, la crea.
                if (configDir.mkdirs()) {
                     System.out.println("La carpeta de configuración fue creada: " + DIRECCION_CONFIGURACION);
                } else {
                     // Si falla la creación, no podemos continuar de forma segura
                     throw new IOException("Error al crear la carpeta de configuración.");
                }
            }
            
            
            if (!configFile.exists()) {
                System.out.println("El archivo de configuración no existe. Copiando el por defecto...");
                copiarArchivoPorDefecto(); 
            }
            
            configProperties = new Properties();
            try (InputStream inputStream = Files.newInputStream(configFile.toPath())) {
                configProperties.load(inputStream);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    private void copiarArchivoPorDefecto() throws IOException {
        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Files.copy(resourceStream, new File(DIR_COMPLETA).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    
    public static config getInstance() {
        return instance;
    }

   
    public String getProps(String key) {
        return configProperties.getProperty(key);
    }

}

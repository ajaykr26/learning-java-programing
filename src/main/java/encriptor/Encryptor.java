package encriptor;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Encryptor {
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String BASE_PATH = USER_DIR + "/src/test/resources/";
    public static final String ENVIRONMENT_PATH = BASE_PATH + "config/environment/";

    public static void main(String[] args) throws Exception {
        File filepath = new File(ENVIRONMENT_PATH);
        File[] files = filepath.listFiles();
        int i = 0;
        Map<Object, String> envMap = new HashMap<Object, String>();
        assert files != null;
        for (File file : files) {
            if (!file.getName().startsWith("SecureText-")) {
                envMap.put(i, file.getName().replace(".properties", ""));
                System.out.println(i + ". " + file.getName().replace(".properties", ""));
                i++;
            }
        }
        Scanner intScanner = new Scanner(System.in);
        System.out.println("Please select the environment: ");
        int env = intScanner.nextInt();
        String environment = envMap.get(env);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the key: ");
        String key = scanner.nextLine();
        System.out.println("Please enter the value: ");
        String value = scanner.nextLine();

        writeSecureText(environment, key, value);
    }

    private static void writeSecureText(String environment, String key, String valueToEncrypt) throws IOException {
        if (environment != null) {
            String propFilePath = ENVIRONMENT_PATH + "SecureText-" + environment + ".properties";
            String encryptedTextValue = encrypt(valueToEncrypt);
            System.out.println("Encrypted String:" + encryptedTextValue);
            File file = new File(propFilePath);
            if (!file.exists()) {
                final boolean newFile = file.createNewFile();
                System.out.println("SecureText-" + environment + ".properties file created");
                setProperty(propFilePath, key, encryptedTextValue);
                System.out.println("encrypted values of" + valueToEncrypt + "written in the " + "SecureText-" + environment + ".properties file");
            } else {
                setProperty(propFilePath, key, encryptedTextValue);
                System.out.println("encrypted values written in the " + "SecureText-" + environment + ".properties file");
            }
        } else {
            System.out.println("environment can not be null. please check the environment");
        }
    }

    private static String encrypt(String plainString) {
        String encryptedString = null;
        if (plainString != null) {
            byte[] byteArray = Base64.encodeBase64(plainString.getBytes());
            encryptedString = new String(byteArray);
        } else {
            System.out.println("please enter plain text to be encrypted");
        }
        return encryptedString;
    }

    public static PropertiesConfiguration getProperties(String propsFilePath) {
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
        try {
            File propsFile = new File(propsFilePath);
            if (propsFile.exists()) {
                FileInputStream inputStream = new FileInputStream(propsFilePath);
                propertiesConfiguration.load(inputStream);
                inputStream.close();
                return propertiesConfiguration;
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static void setProperty(String propFilePath, String key, String value) {
        PropertiesConfiguration propertiesConfiguration = getProperties(propFilePath);
        if (propertiesConfiguration != null) {
            propertiesConfiguration.setProperty(key, value);
            try {
                propertiesConfiguration.save(propFilePath);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}

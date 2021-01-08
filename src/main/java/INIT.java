import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author MakesYT
 */
public class INIT {
    static Logger logger=Logger.getLogger(INIT.class);
    public static void main(String[] args) {
        logger.info("开始初始化...");
        Config.loader();

    }
}

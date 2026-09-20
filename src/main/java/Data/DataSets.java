package Data;

import org.testng.annotations.DataProvider;

import java.util.List;

public class DataSets {

    @DataProvider(name = "languages")
    public static Object[][] languages() {
        return List.of(Language.values()).stream()
                .map(language -> new Object[]{language})
                .toArray(Object[][]::new);
    }
}

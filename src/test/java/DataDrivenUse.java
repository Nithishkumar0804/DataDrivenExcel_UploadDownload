import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class DataDrivenUse {
    @Test
    public static void excel() throws IOException {
        DataDriven d = new DataDriven();
        ArrayList<String> data = d.getData("Purchase");
        for (String ele : data) {
            System.out.println(ele);
        }
    }
}

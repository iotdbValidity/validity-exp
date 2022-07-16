import org.apache.iotdb.rpc.IoTDBConnectionException;
import org.apache.iotdb.rpc.StatementExecutionException;
import org.apache.iotdb.session.Session;
import org.apache.iotdb.session.SessionDataSet;

import java.io.IOException;
import java.util.List;


public class Exp1 {
    public static void main(String[] args) throws IoTDBConnectionException, StatementExecutionException {
        Session session = new Session("localhost" , 6667, "root", "root");
        session.open();
        SessionDataSet resultUDF = null;
        SessionDataSet resultTsFile = null;
        LogWriter lw = new LogWriter("./energy_res.csv");
//        String[] doubleArray = new String[]{"Appliances","lights","T1","RH_1","T2","RH_2","T3","RH_3","T4","RH_4","T5","RH_5","T6","RH_6","T7","RH_7","T8","RH_8", "T9","RH_9","T_out","Press_mm_hg","RH_out","Windspeed","Visibility","Tdewpoint","rv1","rv1"};
        lw.open();
        int i = 3;
        double[] res = new double[2];
        resultUDF = session.executeQueryStatement("select violationUDF(Appliances) from root.energy.d0");
        res[0] = resultUDF.next().getFields().get(0).getDoubleV();
        resultTsFile = session.executeQueryStatement("select Validity(Appliances) from root.energy.d0");
        res[1] = resultTsFile.next().getFields().get(0).getDoubleV();
        StringBuilder str = new StringBuilder();
        str.append("energy");
        str.append(' ');
        str.append(String.valueOf(res[0]));
        str.append(' ');
        str.append(String.valueOf(res[1]));
        str.append(' ');

        System.out.println(str);
        lw.log(str+"\n");
        lw.close();
    }

}

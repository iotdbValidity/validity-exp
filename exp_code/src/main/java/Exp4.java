import org.apache.iotdb.rpc.IoTDBConnectionException;
import org.apache.iotdb.rpc.StatementExecutionException;
import org.apache.iotdb.session.Session;
import org.apache.iotdb.session.SessionDataSet;

import java.io.IOException;
import java.util.List;


public class Exp4 {
    public static void main(String[] args) throws IoTDBConnectionException, StatementExecutionException {
        Session session = new Session("localhost" , 6667, "root", "root");
        session.open();
        SessionDataSet result = null;
        LogWriter lw = new LogWriter("./PAMAP2_res.csv");
//        String[] doubleArray = new String[]{"Appliances","lights","T1","RH_1","T2","RH_2","T3","RH_3","T4","RH_4","T5","RH_5","T6","RH_6","T7","RH_7","T8","RH_8", "T9","RH_9","T_out","Press_mm_hg","RH_out","Windspeed","Visibility","Tdewpoint","rv1","rv1"};
        lw.open();
        for (int i = 30 ; i <= 36 ; i ++){
            double[] res = new double[1];
            StringBuilder str = new StringBuilder();
            result = session.executeQueryStatement("select Validity(s"+i+") from root.PAMAP2.d0");
            res[0] = result.next().getFields().get(0).getDoubleV();
            str.append(String.valueOf("d" + 0 + "s" + i));
            str.append(' ');
            str.append(String.valueOf(res[0]));
            str.append(' ');

            System.out.println(str);
            lw.log(str+"\n");
        }
        lw.close();


        //muss data test
//        LogWriter lw = new LogWriter("./mussdata_res.csv");
//        lw.open();
//        long startTime, endTime;
//        lw.log("muss ratio,time\n");
//
//        String[] ratio_list = {"0", "0.05", "0.1", "0.15", "0.2", "0.25", "0.3", "0.35", "0.4", "0.45", "0.5"};
//
//        for (int i = 0 ; i <= 10 ; i ++){
//            startTime = System.currentTimeMillis();
//            for (int iternum=0; iternum<10; iternum++) {
//                result = session.executeQueryStatement("select Validity(s0) from root.muss.d" +i +
//                        " where time < 2019-10-07T00:00:00");
//            }
//            endTime = System.currentTimeMillis();
//            lw.log(ratio_list[i]+","+(endTime-startTime)/10+"\n");
//        }
//        lw.close();

        // energy dataset calculated by day
//        String[] features = {"Appliances", "lights", "T1", "RH_1", "T2", "RH_2", "T3",
//                "RH_3", "T4", "RH_4", "T5", "RH_5", "T6", "RH_6", "T7", "RH_7", "T8",
//                "RH_8", "T9", "RH_9", "T_out", "Press_mm_hg", "RH_out", "Windspeed",
//                "Visibility", "Tdewpoint", "rv1", "rv2"};
//
//        LogWriter lw = new LogWriter("./energy_res.csv");
//        lw.open();
//        for (int i = 0 ; i <= 27 ; i ++){
//            double tmp;
//            StringBuilder str = new StringBuilder();
//            str.append(features[i]+",");
//            int month = 1, nextmonth= 1;
//            int day = 11, nextday = 12;
//            while(month<=12) {
//                try {
//                    result = session.executeQueryStatement("select Validity(" + features[i] + ") from root.energy.d0 " +
//                            "where time>=2016-"+ month + "-" + day + "T00:00:00 " +
//                            "and time<2016-" + nextmonth + "-" + nextday + "T00:00:00");
//                    tmp = result.next().getFields().get(0).getDoubleV();
//                    day += 1;
//                    nextday += 1;
//                    str.append(String.valueOf(tmp)+",");
//                }
//                catch (NullFieldException e){
//                    str.append("0,");
//                    day += 1;
//                    nextday += 1;
//                }
//
//                if ((day==32 && (month==1||month==3) || (day==31 && month==4) || (day==30 && month==2))){
//                    day = 1;
//                    month += 1;
//                }
//                if ((nextday==32 && (nextmonth==1||nextmonth==3)) || (nextday==31 && nextmonth==4) ||
//                        (nextday==30 && nextmonth==2)){
//                    nextday = 1;
//                    nextmonth += 1;
//                }
//
//                if (month==5 && day==27) break;
//            }
//            System.out.println(str);
//            lw.log(str+"\n");
//        }
//        lw.close();


        //PAMAP2 dataset calculated by minute
//        String[] features = {"heart rate", "hand temperature", "hand A1", "hand A2", "hand A3", "hand A4", "hand A5",
//                "hand A6", "hand G1", "hand G2", "hand G3", "hand M1", "hand M2", "hand M3", "hand O1", "hand O2", "hand O3", "hand O4",
//                "chest temperature", "chest A1", "chest A2", "chest A3", "chest A4", "chest A5", "chest A6", "chest G1", "chest G2",
//                "chest G3", "chest M1", "chest M2", "chest M3", "chest O1", "chest O2", "chest O3", "chest O4"};
//
//        LogWriter lw = new LogWriter("./PAMAP2_res.csv");
//        lw.open();
//        for (int i = 2 ; i <= 36 ; i ++){
//            double tmp;
//            StringBuilder str = new StringBuilder();
//            str.append(features[i-2]+",");
//            int min = 0, nextmin= 1;
//            int hour = 8, nexthour = 8;
//            while(hour<=9) {
//                try {
//                    result = session.executeQueryStatement("select Validity(s" + i + ") from root.PAMAP2.d0 " +
//                            "where time>=1970-01-01T"+String.format("%02d", hour)+":"+String.format("%02d", min)+":00 " +
//                            "and time<1970-01-01T"+String.format("%02d", nexthour)+":"+String.format("%02d", nextmin)+":00 ");
//                    tmp = result.next().getFields().get(0).getDoubleV();
//                    min += 1;
//                    nextmin += 1;
//                    str.append(String.valueOf(tmp)+",");
//                }
//                catch (NullFieldException e){
//                    str.append("0,");
//                    min += 1;
//                    nextmin += 1;
//                }
//
//                if (min==60){
//                    min=0;
//                    hour+=1;
//                }
//                if (nextmin==60){
//                    nextmin=0;
//                    nexthour+=1;
//                }
//
//                if (hour==9 && min==2) break;
//            }
//            System.out.println(str);
//            lw.log(str+"\n");
//        }
//        lw.close();
    }

    public void read(){

    }
}

package com.practice.online_diagnost.diagnoster;


import com.practice.online_diagnost.diagnoster.file_handlers.DBInfo;
import com.practice.online_diagnost.diagnoster.file_handlers.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lb4 extends Lb3 {
    protected Map<Double, Map<Double, Double[]>> aBi;
    protected Map<Double, Map<Double, Double>> Li;
    protected Map<Double, Map<Double, Double>> probabilityQuoteBiDmx;
    protected Map<Double, Map<Double, Double>> probabilityBiDmx;

    public Lb4(Connection con, String tableName, double[] patients) {
        super(con, tableName, patients);
    }

    private static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    private void item2dot1() {
        aBi = new HashMap<>();
        for (Double patientId : this.patients) {
//            double[] patient = this.mXlsSheet.get(patientId.intValue() + 1).stream()
//                    .filter(d -> d.matches("[\\d.]+"))
//                    .mapToDouble(Double::new)
//                    .toArray();
            double[] patient = dihotomicTable[patientId.intValue()];
            Map<Double, Double[]> map = new HashMap<>();
            System.out.println(patientId.intValue() + " : " + Arrays.toString(patient));
            if (patientDiagnos.get(patientId).size() == 1) {
                continue;
            }
            patientDiagnos.get(patientId).stream().forEach(diagnos -> {
                Double[] alfaBi = new Double[this.P];
                System.out.println("Patient : " + patientId.intValue());
                System.out.println("Proba Sij/Bi : " + Arrays.toString(mTableProbalitySijBi.get(diagnos)));
                double[] arraySijBi = mTableProbalitySijBi.get(diagnos);
                for (int j = 0; j < this.P; j++) {
//                    if (j == 1 && diagnos == 1 || j == 5 && diagnos == 2) {
//                        System.out.println("---------------------------------");
//                        System.out.println("patient[j] : " + patient[j]);
//                        System.out.println("Sij/Bi[j] : " + arraySijBi[j]);
//                        System.out.println("probSj[j] : " + mProbabilitySj[j]);
//                        System.out.println("log2() " + log2(arraySijBi[j] / mProbabilitySj[j]));
//                        System.out.println("log2(1-) " + log2((1 - arraySijBi[j]) / (1 - mProbabilitySj[j])));
//                        System.out.println("------------------------------------");
//                    }
                    if (patient[j] == 1) {
                        alfaBi[j] = patient[j] * log2(arraySijBi[j] / mProbabilitySj[j]);
                    } else {
                        alfaBi[j] = (1 - patient[j]) * log2((1 - arraySijBi[j]) / (1 - mProbabilitySj[j]));
                    }
                }
                map.put(diagnos, alfaBi);
            });
            aBi.put(patientId, map);
        }
        System.out.println("***************");
        aBi.forEach((p, val) -> {
            System.out.println("patient: " + p);
            val.forEach((d, alfa) -> {
                System.out.println(d + " ->>> " + Arrays.toString(alfa));
            });
        });
    }


    private void item2dot2() {
        Li = new HashMap<>();
        probabilityQuoteBiDmx = new HashMap<>();
        probabilityBiDmx = new HashMap<>();
        for (Double patientId : this.patients) {
            if (patientDiagnos.get(patientId).size() == 1) {
                continue;
            }
            Map<Double, Double> liValues = new HashMap<>();
            Map<Double, Double> probabilityQuouteBiDmxValues = new HashMap<>();
//            Map<Double, Double> probabilityBiDmxValues = new HashMap<>();
            patientDiagnos.get(patientId).stream().forEach(diagnos -> {
                Double[] aBiForDiagnos = aBi.get(patientId).get(diagnos);
                double tempLi = 0;
                for (int j = 0; j < this.P; j++) {
                    tempLi += aBiForDiagnos[j];
                }
                tempLi += log2(mProbabilityBi.get(diagnos));
                liValues.put(diagnos, tempLi);
                probabilityQuouteBiDmxValues.put(diagnos, Math.pow(2, tempLi));
            });
            Li.put(patientId, liValues);
            probabilityQuoteBiDmx.put(patientId, probabilityQuouteBiDmxValues);

            System.out.println("******************");
            Li.forEach((k, v) -> {
                System.out.println("Diagnoz: " + k);
                System.out.println("Li : " + v);
            });
            System.out.println("******************");
            probabilityQuoteBiDmx.forEach((k, v) -> {
                System.out.println("Diagnoz: " + k);
                System.out.println("P'BiDmxi : " + v);
                double sumProbBiDmxTemp = 0d;
                for (Double value : v.values()) {
                    sumProbBiDmxTemp += value;
                }
                double base = sumProbBiDmxTemp;
                Map<Double, Double> temp = new HashMap<>();
                v.keySet().forEach(key -> {
                    double value = v.get(key);
                    value /= base;
                    temp.put(key, value);
                });
                probabilityBiDmx.put(k, temp);
            });
            System.out.println("****************** 44");
            probabilityBiDmx.forEach((k, v) -> {
                System.out.println("Diagnoz: " + k);
                System.out.println("PBiDmxi : " + v);
            });
        }

    }

    public static void main(String[] args) throws SQLException {
        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
        dbUtil.connect();
        Connection con = dbUtil.getJdbcConnection();
        Lb4 flb4 = new Lb4(con, "statistic_data", new double[]{2, 57, 10, 7});
        flb4.countN2iJ();
        flb4.countProbablitySijBi();
        flb4.predictDiagnosesForPatients();
        System.out.println("************************");
//        flb4.mXlsSheet.forEach(System.out::println);
        flb4.item2dot1();
        flb4.item2dot2();

    }
}

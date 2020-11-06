package com.practice.online_diagnost.diagnoster;


import com.practice.online_diagnost.diagnoster.file_handlers.DBInfo;
import com.practice.online_diagnost.diagnoster.file_handlers.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Lb4 extends Lb3 {
    protected Map<Double, Map<Double, Double[]>> aBi;
    protected Map<Double, Map<Double, Double>> Li;
    protected Map<Double, Map<Double, Double>> probabilityQuoteBiDmx;
    protected Map<Double, Map<Double, Double>> probabilityBiDmx;

    public Lb4(Connection con, String tableName, double[] patients) {
        super(con, tableName, patients);
    }

    public Lb4(Connection con, String tableName) {
        super(con, tableName);
    }

    private static double log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    private void item2dot1() {
        aBi = new HashMap<>();
        for (Double patientId : this.patients) {
            double[] patient = dihotomicTable[patientId.intValue()];
            Map<Double, Double[]> map = new HashMap<>();
            System.out.println(patientId.intValue() + " : " + Arrays.toString(patient));
            if (patientDiagnos.get(patientId).size() == 1) {
                continue;
            }
            patientDiagnos.get(patientId).stream().forEach(diagnos -> {
                Double[] alfaBi = new Double[this.P];
                System.out.println("Patient : " + patientId.intValue());
                System.out.println("Proba Sij/Bi : " + Arrays.toString(tableProbalitySijBi.get(diagnos)));
                double[] arraySijBi = tableProbalitySijBi.get(diagnos);
                for (int j = 0; j < this.P; j++) {
                    if (patient[j] == 1) {
                        alfaBi[j] = patient[j] * log2(arraySijBi[j] / probabilitySj[j]);
                    } else {
                        alfaBi[j] = (1 - patient[j]) * log2((1 - arraySijBi[j]) / (1 - probabilitySj[j]));
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

    private void item2dot1ForOnePatient(double[] symptoms) {
        aBi = new HashMap<>();
        Double patientId = patients[0];
        double[] patient = symptoms;
        Map<Double, Double[]> map = new HashMap<>();
        System.out.println(patientId.intValue() + " : " + Arrays.toString(patient));

        patientDiagnos.get(patientId).stream().forEach(diagnos -> {
            Double[] alfaBi = new Double[this.P];
            System.out.println("Patient : " + patientId.intValue());
            System.out.println("Proba Sij/Bi : " + Arrays.toString(tableProbalitySijBi.get(diagnos)));
            double[] arraySijBi = tableProbalitySijBi.get(diagnos);
            for (int j = 0; j < this.P; j++) {
                if (patient[j] == 1) {
                    alfaBi[j] = patient[j] * log2(arraySijBi[j] / probabilitySj[j]);
                } else {
                    alfaBi[j] = (1 - patient[j]) * log2((1 - arraySijBi[j]) / (1 - probabilitySj[j]));
                }
            }
            map.put(diagnos, alfaBi);
        });
        aBi.put(patientId, map);

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
                tempLi += log2(probabilityBi.get(diagnos));
                liValues.put(diagnos, tempLi);
                probabilityQuouteBiDmxValues.put(diagnos, Math.pow(2, tempLi));
            });
            Li.put(patientId, liValues);
            probabilityQuoteBiDmx.put(patientId, probabilityQuouteBiDmxValues);

            System.out.println("******************");
            Li.forEach((k, v) -> {
                System.out.println("Patient: " + k);
                System.out.println("Li : " + v);
            });
            System.out.println("******************-->>");
            probabilityQuoteBiDmx.forEach((k, v) -> {
                System.out.println("Patient: " + k);
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
                System.out.println("Patient: " + k);
                System.out.println("PBiDmxi : " + v);
            });
        }

    }

    private void item2dot2ForOnePatient() {
        Li = new HashMap<>();
        probabilityQuoteBiDmx = new HashMap<>();
        probabilityBiDmx = new HashMap<>();
        Double patientId = patients[0];

        Map<Double, Double> liValues = new HashMap<>();
        Map<Double, Double> probabilityQuouteBiDmxValues = new HashMap<>();
        patientDiagnos.get(patientId).stream().forEach(diagnos -> {
            Double[] aBiForDiagnos = aBi.get(patientId).get(diagnos);
            double tempLi = 0;
            for (int j = 0; j < this.P; j++) {
                tempLi += aBiForDiagnos[j];
            }
            tempLi += log2(probabilityBi.get(diagnos));
            liValues.put(diagnos, tempLi);
            probabilityQuouteBiDmxValues.put(diagnos, Math.pow(2, tempLi));
        });
        Li.put(patientId, liValues);
        probabilityQuoteBiDmx.put(patientId, probabilityQuouteBiDmxValues);

        System.out.println("******************");
        Li.forEach((k, v) -> {
            System.out.println("Patient: " + k);
            System.out.println("Li : " + v);
        });
        System.out.println("******************-->>");
        probabilityQuoteBiDmx.forEach((k, v) -> {
            System.out.println("Patient: " + k);
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
            System.out.println("Patient: " + k);
            System.out.println("PBiDmxi : " + v);
        });


    }

    public void run(double[] symptoms) {
        countN2iJ();
        countProbablitySijBi();
        predictDiagnosesForPatient(symptoms);
        item2dot1ForOnePatient(symptoms);
        item2dot2ForOnePatient();
    }

    public Map<Integer, Double> getPrediction () {
        Map<Integer,Double> answer = probabilityBiDmx.get(patients[0])
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().intValue(),
                        entry -> Double.valueOf(String.format("%.3f", entry.getValue()))));
        return answer;
    }


    public static void main(String[] args) throws SQLException {
        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
        dbUtil.connect();
        Connection con = dbUtil.getJdbcConnection();
        Lb4 flb4 = new Lb4(con, "statistic_data");
//        flb4.countN2iJ();
//        flb4.countProbablitySijBi();
//        flb4.predictDiagnosesForPatients();
//        System.out.println("************************");
//        flb4.item2dot1();
//        flb4.item2dot2();
        System.out.println("======================");

        flb4.run(new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0});
        System.out.println(flb4.getPrediction());

    }
}

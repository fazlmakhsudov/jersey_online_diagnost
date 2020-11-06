package com.practice.online_diagnost.diagnoster;


import com.practice.online_diagnost.diagnoster.file_handlers.DBInfo;
import com.practice.online_diagnost.diagnoster.file_handlers.DBUtil;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class Lb3 {
    private final static Logger LOG = Logger.getLogger(Lb3.class.getSimpleName());
    // source data
    protected final List<List<Object>> dbData;
    protected double[][] dihotomicTable;
    protected double[] patients;
    protected Map<Double, List<Double>> patientDiagnos;
    protected Map<Double, List<double[]>> diagnozGroups;
    protected Map<Double, Boolean> diagnoz100;
    protected final int diagnozColumn;
    protected int N; // all raws of parients
    protected int P; //number of symptoms
    protected final int M; //number of diagnoses
    protected int[] numberSj; // number patiens with symptom concerning whole N
    protected Map<Double, Double> probabilityBi;
    protected double[] probabilitySj;
    // item 2
    protected Map<Double, double[]> tableProbalitySijBi;
    protected Map<Double, double[][]> mN2iJ;

    public Lb3(Connection con, String tableName, double[] patients) {
        dbData = new ArrayList<>();
        getDataFromDb(con, tableName);
        this.diagnozColumn = dbData.get(0).size() - 1;
        initializeSourceData();
        this.M = this.diagnozGroups.size();
        this.tableProbalitySijBi = new TreeMap<>();
        this.mN2iJ = new TreeMap<>();
        this.probabilitySj = new double[this.P];
        this.probabilityBi = new TreeMap<>();
        this.patients = patients;
        this.patientDiagnos = new TreeMap<>();
        this.diagnoz100 = new TreeMap<>();
    }

    public Lb3(Connection con, String tableName) {
        dbData = new ArrayList<>();
        getDataFromDb(con, tableName);
        this.diagnozColumn = dbData.get(0).size() - 1;
        initializeSourceData();
        this.M = this.diagnozGroups.size();
        this.tableProbalitySijBi = new TreeMap<>();
        this.mN2iJ = new TreeMap<>();
        this.probabilitySj = new double[this.P];
        this.probabilityBi = new TreeMap<>();
        this.patients = new double[] {1d};
        this.patientDiagnos = new TreeMap<>();
        this.diagnoz100 = new TreeMap<>();
    }

    protected void initializeSourceData() {

        this.N = this.dbData.size();
        this.dihotomicTable = new double[this.N][this.P];
        this.numberSj = new int[this.P];
        this.getDihotomicData();
        this.diagnozGroups = new TreeMap<>();
        for (int i = 0; i < this.dbData.size(); i++) {
            Double diagnoz = (Double) this.dbData.get(i).get(this.diagnozColumn);
            if (this.diagnozGroups.getOrDefault(diagnoz, null) == null) {
                this.diagnozGroups.put(diagnoz, new ArrayList<>());
            }
            this.diagnozGroups.get(diagnoz).add(this.dihotomicTable[i]);
        }
    }

    protected void getDataFromDb(Connection con, String tableName) {
        String query = String.format("select * from %s;", tableName);
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            P = resultSetMetaData.getColumnCount() - 6;
            while (rs.next()) {
                List<Object> rowData = new ArrayList<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    rowData.add(rs.getObject(i));
                }
                dbData.add(rowData);
            }
        } catch (SQLException e) {
            LOG.severe(e.getMessage());
        }
    }

    protected void getDihotomicData() {
        System.out.println("\nDihotomic table: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < P; j++) {
                double value = (double) dbData.get(i).get(j+5);
                if (value == 1) {
                    this.numberSj[j]++;
                }
                this.dihotomicTable[i][j] = value;
            }
//            System.out.println(Arrays.toString(this.dihotomicTable[i]));
        }
    }

    public void countN2iJ() {
        for (Double diagnoz : this.diagnozGroups.keySet()) {
            List<double[]> certainGroup = this.diagnozGroups.get(diagnoz);
            double[][] n2ij = new double[this.P][1];
            for (double[] raw : certainGroup) {
                for (int j = 0; j < this.P; j++) {
                    if (raw[j] == 1) {
                        n2ij[j][0]++;
                    }
                }
            }
            this.mN2iJ.put(diagnoz, n2ij);
            // Probability Bi counting
            double temp = (double) this.diagnozGroups.get(diagnoz).size() / this.N;
            this.probabilityBi.put(diagnoz, temp);
        }
        // Probability Sj counting
//        System.out.println("\nP(Sj) vector:");
        for (int j = 0; j < this.P; j++) {
            this.probabilitySj[j] = (double) this.numberSj[j] / this.N;
//            System.out.println(" Probability Sj: " + this.probabilitySj[j]);
        }
        // testing
//        System.out.println("\nvectors P(Bi) with Ni of diagnoses, and N2ij arrays:");
        for (Double d : this.mN2iJ.keySet()) {
//            System.out.println("diagnos: " + d + " Ni: " + this.diagnozGroups.get(d).size() + " with probability" +
//                    " concerns N: " + this.probabilityBi.get(d));
            for (double[] raw : this.mN2iJ.get(d)) {
//                System.out.print(Arrays.toString(raw) + " ");
            }
//            System.out.println();
        }
    }

    public void countProbablitySijBi() {
//        System.out.println("\nTable of P(Sij/Bi): ");
        for (Double diagnoz : this.mN2iJ.keySet()) {
            double[] raw = new double[this.P];
            for (int j = 0; j < this.P; j++) {
                raw[j] = this.mN2iJ.get(diagnoz)[j][0] / this.diagnozGroups.get(diagnoz).size();
            }
//            System.out.println(diagnoz + "      " + Arrays.toString(raw));
            this.tableProbalitySijBi.put(diagnoz, raw);
        }
    }

    // table Haminga counting
    protected List<Double> countHamingaDistance(Double patientId, double[] patientDihotomicValues) {
        List<Double> patientDiagnos = new ArrayList<>();
        for (Double diagnos : this.tableProbalitySijBi.keySet()) {
            double[] rawProbalitySijBi = this.tableProbalitySijBi.get(diagnos);
            double sumD = 0d;
            boolean flag = true;
            for (int j = 0; j < rawProbalitySijBi.length; j++) {
                double temp = Math.abs(patientDihotomicValues[j] - rawProbalitySijBi[j]);
                if (temp == 1) {
                    System.out.println("stalo break: for diagnos " + diagnos);
                    flag = false;
                    break;
                }
                sumD += temp;
            }
            if (sumD == 1 || !flag) {
                continue;
            }
            if (sumD == 0d) {
                patientDiagnos.clear();
                this.diagnoz100.replace(patientId, true);
            }
            System.out.println("->>>> " + "diagnoz " + diagnos.intValue() + ", sumD(|Xj - P(Sj/Bi)|) " + sumD);
            patientDiagnos.add(diagnos);
        }
        return patientDiagnos;
    }

    public void predictDiagnosesForPatients() {

        for (Double patientId : this.patients) {
            System.out.println("\n**** patient Id: " + patientId.intValue());
            this.diagnoz100.put(patientId, false);
            double[] patientDihotomicValues = this.dihotomicTable[patientId.intValue() - 1];
            this.patientDiagnos.put(patientId, this.countHamingaDistance(patientId, patientDihotomicValues));
        }
        // testing
        System.out.println("\n Diagnos prediction: ");
        for (Double patientId : this.patients) {
            System.out.println("patient: " + patientId + " has been set diagnoses: " + this.patientDiagnos.get(patientId) + " 100% " + this.diagnoz100.get(patientId));
        }
    }

    public void predictDiagnosesForPatient(double []  symptoms) {

        double patientId = this.patients[0];
            this.diagnoz100.put(patientId, false);
            double[] patientDihotomicValues = symptoms;
            this.patientDiagnos.put(patientId, this.countHamingaDistance(patientId, patientDihotomicValues));

        // testing
        System.out.println("\n Diagnos prediction: ");

        System.out.println("patient: " + patientId + " has been set diagnoses: " + this.patientDiagnos.get(patientId) + " 100% " + this.diagnoz100.get(patientId));

    }

    public static void main(String[] args) throws SQLException {


        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
        dbUtil.connect();
        Connection con = dbUtil.getJdbcConnection();
        Lb3 flb3 = new Lb3(con, "statistic_data", new double[]{1, 2});
        flb3.countN2iJ();
        flb3.countProbablitySijBi();
        flb3.predictDiagnosesForPatient(new double[] {0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0});
//        System.setOut(STD_OUT);
    }
}

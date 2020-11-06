package com.practice.online_diagnost.services.domains;

import com.practice.online_diagnost.api.models.DiagnosterRequestModel;
import com.practice.online_diagnost.api.resources.v1.DiagnosterResourse;
import com.practice.online_diagnost.diagnoster.Lb4;
import com.practice.online_diagnost.repositories.util.DBManager;

import java.sql.Connection;
import java.util.*;
import java.util.logging.Logger;

public class DiagnosterService {
    private static final Logger LOG = Logger.getLogger(DiagnosterResourse.class.getSimpleName());

    public Map<Integer, Double> predictDiagnos(DiagnosterRequestModel diagnosterRequestModel) {
        Map<Integer, Double> prediction = new HashMap<>();
        double[] symptoms = diagnosterRequestModel.getQuestions().stream()
                .map(question -> question.getAnswer().matches("true") ? 1d : 0d)
                .mapToDouble(Double::doubleValue).toArray();
        System.out.println(" predictDiagnos symptoms" + Arrays.toString(symptoms));
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            Lb4 flb4 = new Lb4(con, "statistic_data");
            flb4.run(symptoms);
            prediction = flb4.getPrediction();
            con.commit();
        } catch (Exception e) {
            DBManager.rollback(con);
            LOG.severe(String.format("Cannot predict %s", e.getMessage()));
        } finally {
            DBManager.releaseConnection(con);
        }
        return prediction;
    }
}

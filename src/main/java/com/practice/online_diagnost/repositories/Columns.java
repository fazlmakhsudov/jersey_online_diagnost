package com.practice.online_diagnost.repositories;

import com.practice.online_diagnost.repositories.entities.AssignmentEntity;
import com.practice.online_diagnost.repositories.entities.SymptomEntity;

import java.sql.Date;
import java.util.List;

public  class Columns {
    // common columns' names
   public static final String ENTITY_ID = "id";
   public static final String ENTITY_NAME = "name";
   public static final String CREATED_DATE = "created_date";
   public static final String UPDATED_DATE = "updated_date";
    
    //public columns' names
   public static final String DIAGNOSES_ID= "diagnoses_id";
   public static final String MEDICS_ID = "medics_Id";

    public static final String TREATMENT_HISTORIES_ID = "treatment_histories_id";

}

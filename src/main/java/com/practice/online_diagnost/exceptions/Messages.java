package com.practice.online_diagnost.exceptions;

/**
 * Holder for messages of exceptions.
 *
 * @author Fazliddin Makhsudov
 */
public final class Messages {

    private Messages() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    // DB exceptions
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
    public static final String ERR_CANNOT_CLOSE_PREPARED_STATEMENT = "Cannot close a prepared statement";
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
    public static final String CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";

    //user entity exceptions
    public static final String ERR_CANNOT_INSERT_USER = "Cannot insert new user";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email";
    public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
    public static final String ERR_CANNOT_DELETE_USER = "Cannot delete new user";
    public static final String ERR_CANNOT_READ_ALL_USERS = "Cannot read all users";
    public static final String ERR_CANNOT_COUNT_ALL_USERS = "Cannot count all users";
    public static final String ERR_CANNOT_READ_USERS_WITH_LIMITATION = "Cannot read users with limitation";
    // role entity exceptions
    public static final String ERR_CANNOT_INSERT_ROLE = "Cannot insert new role";
    public static final String ERR_CANNOT_OBTAIN_ROLE_BY_ID = "Cannot obtain a role by its id";
    public static final String ERR_CANNOT_OBTAIN_ROLE_BY_NAME = "Cannot obtain a role by its name";
    public static final String ERR_CANNOT_UPDATE_ROLE = "Cannot update a role";
    public static final String ERR_CANNOT_DELETE_ROLE = "Cannot delete new role";
    public static final String ERR_CANNOT_READ_ALL_ROLES = "Cannot read all roles";
    public static final String ERR_CANNOT_COUNT_ALL_ROLES = "Cannot count all roles";
    public static final String ERR_CANNOT_READ_ROLES_WITH_LIMITATION = "Cannot read roles with limitation";
    // assignment entity exceptions
    public static final String ERR_CANNOT_INSERT_ASSIGNMENT = "Cannot insert new assignment";
    public static final String ERR_CANNOT_OBTAIN_ASSIGNMENT_BY_ID = "Cannot obtain a assignment by its id";
    public static final String ERR_CANNOT_OBTAIN_ASSIGNMENT_BY_NAME = "Cannot obtain a assignment by its name";
    public static final String ERR_CANNOT_UPDATE_ASSIGNMENT = "Cannot update a assignment";
    public static final String ERR_CANNOT_DELETE_ASSIGNMENT = "Cannot delete new assignment";
    public static final String ERR_CANNOT_READ_ALL_ASSIGNMENTS = "Cannot read all assignments";
    public static final String ERR_CANNOT_COUNT_ALL_ASSIGNMENTS = "Cannot count all assignments";
    public static final String ERR_CANNOT_COUNT_ASSIGNMENTS_WITH_CONDITION = "Cannot count assignments with condition";
    public static final String ERR_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION = "Cannot read assignments with limitation";

    // symptom entity exceptions
    public static final String ERR_CANNOT_INSERT_SYMPTOM = "Cannot insert new symptom";
    public static final String ERR_CANNOT_OBTAIN_SYMPTOM_BY_ID = "Cannot obtain a symptom by its id";
    public static final String ERR_CANNOT_OBTAIN_SYMPTOM_BY_NAME = "Cannot obtain a symptom by its name";
    public static final String ERR_CANNOT_UPDATE_SYMPTOM = "Cannot update a symptom";
    public static final String ERR_CANNOT_DELETE_SYMPTOM = "Cannot delete new symptom";
    public static final String ERR_CANNOT_READ_ALL_SYMPTOMS = "Cannot read all symptoms";
    public static final String ERR_CANNOT_COUNT_ALL_SYMPTOMS = "Cannot count all symptoms";
    public static final String ERR_CANNOT_COUNT_SYMPTOMS_WITH_CONDITION = "Cannot count symptoms with condition";
    public static final String ERR_CANNOT_READ_SYMPTOMS_WITH_LIMITATION = "Cannot read incoices with limitation";
    // diagnos entity exceptions
    public static final String ERR_CANNOT_INSERT_DIAGNOS = "Cannot insert new diagnos";
    public static final String ERR_CANNOT_OBTAIN_DIAGNOS_BY_ID = "Cannot obtain a diagnos by its id";
    public static final String ERR_CANNOT_OBTAIN_DIAGNOS_BY_NAME = "Cannot obtain a diagnos by its name";
    public static final String ERR_CANNOT_UPDATE_DIAGNOS = "Cannot update a diagnos";
    public static final String ERR_CANNOT_DELETE_DIAGNOS = "Cannot delete new diagnos";
    public static final String ERR_CANNOT_READ_ALL_DIAGNOSES = "Cannot read all diagnoses";
    public static final String ERR_CANNOT_COUNT_ALL_DIAGNOSES = "Cannot count all diagnoses";
    public static final String ERR_CANNOT_COUNT_DIAGNOSES_WITH_CONDITION = "Cannot count diagnoses with condition";
    public static final String ERR_CANNOT_READ_DIAGNOSES_WITH_LIMITATION = "Cannot read diagnoses with limitation";
    // disease entity exceptions
    public static final String ERR_CANNOT_INSERT_DISEASE = "Cannot insert new disease";
    public static final String ERR_CANNOT_OBTAIN_DISEASE_BY_ID = "Cannot obtain a disease by its id";
    public static final String ERR_CANNOT_OBTAIN_DISEASE_BY_NAME = "Cannot obtain a disease by its name";
    public static final String ERR_CANNOT_UPDATE_DISEASE = "Cannot update a disease";
    public static final String ERR_CANNOT_DELETE_DISEASE = "Cannot delete new disease";
    public static final String ERR_CANNOT_READ_ALL_DISEASES = "Cannot read all diseases";
    public static final String ERR_CANNOT_COUNT_ALL_DISEASES = "Cannot count all diseases";
    public static final String ERR_CANNOT_READ_DISEASES_WITH_LIMITATION = "Cannot read diseases with limitation";
    // treatment history entity exceptions
    public static final String ERR_CANNOT_INSERT_TREATMENTHISTORY = "Cannot insert new treatment history";
    public static final String ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID = "Cannot obtain a treatment history by its id";
    public static final String ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_NAME = "Cannot obtain a treatment history by its name";
    public static final String ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ITS_FIELDS = "Cannot obtain a treatment history by its fields(fromCityId, toCityId)";
    public static final String ERR_CANNOT_UPDATE_TREATMENTHISTORY = "Cannot update a treatment history";
    public static final String ERR_CANNOT_DELETE_TREATMENTHISTORY = "Cannot delete new treatment history";
    public static final String ERR_CANNOT_READ_ALL_TREATMENTHISTORIES = "Cannot read all treatment histories";
    public static final String ERR_CANNOT_COUNT_ALL_TREATMENTHISTORIES = "Cannot count all treatment histories";
    public static final String ERR_CANNOT_READ_TREATMENTHISTORIES_WITH_LIMITATION = "Cannot read treatment histories with limitation";

    // medic entity exceptions
    public static final String ERR_CANNOT_INSERT_MEDIC = "Cannot insert new medic";
    public static final String ERR_CANNOT_OBTAIN_MEDIC_BY_ID = "Cannot obtain a medic by its id";
    public static final String ERR_CANNOT_OBTAIN_MEDIC_BY_SPECIALIZATION = "Cannot obtain a medic by its name";
    public static final String ERR_CANNOT_UPDATE_MEDIC = "Cannot update a medic";
    public static final String ERR_CANNOT_DELETE_MEDIC = "Cannot delete new medic";
    public static final String ERR_CANNOT_READ_ALL_MEDICS = "Cannot read all medics";
    public static final String ERR_CANNOT_COUNT_ALL_MEDICS = "Cannot count all medics";
    public static final String ERR_CANNOT_READ_MEDICS_WITH_LIMITATION = "Cannot read medics with limitation";

    // patient entity exceptions
    public static final String ERR_CANNOT_INSERT_PATIENT = "Cannot insert new patient";
    public static final String ERR_CANNOT_OBTAIN_PATIENT_BY_ID = "Cannot obtain a patient by its id";
    public static final String ERR_CANNOT_OBTAIN_PATIENT_BY_NAME = "Cannot obtain a patient by its name";
    public static final String ERR_CANNOT_UPDATE_PATIENT = "Cannot update a patient";
    public static final String ERR_CANNOT_DELETE_PATIENT = "Cannot delete new patient";
    public static final String ERR_CANNOT_READ_ALL_PATIENTS = "Cannot read all patients";
    public static final String ERR_CANNOT_COUNT_ALL_PATIENTS = "Cannot count all patients";
    public static final String ERR_CANNOT_READ_PATIENTS_WITH_LIMITATION = "Cannot read patients with limitation";

    // questionary entity exceptions
    public static final String ERR_CANNOT_INSERT_QUESTIONARY = "Cannot insert new questionary";
    public static final String ERR_CANNOT_OBTAIN_QUESTIONARY_BY_ID = "Cannot obtain a questionary by its id";
    public static final String ERR_CANNOT_OBTAIN_QUESTIONARY_BY_NAME = "Cannot obtain a questionary by its name";
    public static final String ERR_CANNOT_UPDATE_QUESTIONARY = "Cannot update a questionary";
    public static final String ERR_CANNOT_DELETE_QUESTIONARY = "Cannot delete new questionary";
    public static final String ERR_CANNOT_READ_ALL_QUESTIONARIES = "Cannot read all questionaries";
    public static final String ERR_CANNOT_COUNT_ALL_QUESTIONARIES = "Cannot count all questionaries";
    public static final String ERR_CANNOT_READ_QUESTIONARIES_WITH_LIMITATION = "Cannot read questionaries with limitation";

    // question entity exceptions
    public static final String ERR_CANNOT_INSERT_QUESTION = "Cannot insert new question";
    public static final String ERR_CANNOT_OBTAIN_QUESTION_BY_ID = "Cannot obtain a question by its id";
    public static final String ERR_CANNOT_OBTAIN_QUESTION_BY_NAME = "Cannot obtain a question by its name";
    public static final String ERR_CANNOT_UPDATE_QUESTION = "Cannot update a question";
    public static final String ERR_CANNOT_DELETE_QUESTION = "Cannot delete new question";
    public static final String ERR_CANNOT_READ_ALL_QUESTIONS = "Cannot read all questions";
    public static final String ERR_CANNOT_COUNT_ALL_QUESTIONS = "Cannot count all questions";
    public static final String ERR_CANNOT_READ_QUESTIONS_WITH_LIMITATION = "Cannot read questions with limitation";

    // service layer exceptions
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_USER = "Cannot insert new user at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_USER = "Cannot update a user at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_USER = "Cannot delete new user at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_USERS = "Cannot read all users at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION = "Cannot read users with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_ROLE_BY_ID = "Cannot obtain a role by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_ROLE_BY_NAME = "Cannot obtain a role by its email at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_ROLE = "Cannot insert new role at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_ROLE = "Cannot update a role at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_ROLE = "Cannot delete new role at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_ROLES = "Cannot read all roles at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ROLES_WITH_LIMITATION = "Cannot read roles with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_ASSIGNMENT_BY_ID = "Cannot obtain a assignment by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_ASSIGNMENT_BY_NAME = "Cannot obtain a assignment by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_ASSIGNMENT = "Cannot insert new assignment at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_ASSIGNMENT = "Cannot update a assignment at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_ASSIGNMENT = "Cannot delete new assignment at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_ASSIGNMENTS = "Cannot read all assignments at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION = "Cannot read assignments with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_ID = "Cannot obtain a symptom by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_NAME = "Cannot obtain a symptom by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_SYMPTOM = "Cannot insert new symptom at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_SYMPTOM = "Cannot update a symptom at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_SYMPTOM = "Cannot delete new symptom at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_SYMPTOMS = "Cannot read all symptoms at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_SYMPTOMS_WITH_LIMITATION = "Cannot read symptoms with limitation at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_ITS_FIELDS = "Cannot obtain a symptom by its fields(fromCityId, toCityId) at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_DIAGNOS_BY_ID = "Cannot obtain a diagnos by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_DIAGNOS_BY_NAME = "Cannot obtain a diagnos by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOS = "Cannot insert new diagnos at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_DIAGNOS = "Cannot update a diagnos at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_DIAGNOS = "Cannot delete new diagnos at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_DIAGNOSS = "Cannot read all diagnos at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_DIAGNOSES_WITH_LIMITATION = "Cannot read diagnoses with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_DISEASE_BY_ID = "Cannot obtain a disease by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_DISEASE_BY_NAME = "Cannot obtain a disease by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_DISEASE = "Cannot insert new disease at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_DISEASE = "Cannot update a disease at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_DISEASE = "Cannot delete new disease at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_DISEASES = "Cannot read all disease at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_DISEASES_WITH_LIMITATION = "Cannot read diseases with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID = "Cannot obtain a treatment history by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_TREATMENTHISTORY_BY_NAME = "Cannot obtain a treatment history by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_TREATMENTHISTORY = "Cannot insert new treatment history at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_TREATMENTHISTORY = "Cannot update a treatment history at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_TREATMENTHISTORY = "Cannot delete new treatment history at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_TREATMENTHISTORIES = "Cannot read all treatment history at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_TREATMENTHISTORIES_WITH_LIMITATION = "Cannot read treatment histories with limitation at service layer";


    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENT_BY_ID = "Cannot obtain a patient by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENT_BY_NAME = "Cannot obtain a patient by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_PATIENT = "Cannot insert new patient at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_PATIENT = "Cannot update a patient at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_PATIENT = "Cannot delete new patient at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENTS = "Cannot read all patient at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_PATIENTS_WITH_LIMITATION = "Cannot read patients with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDIC_BY_ID = "Cannot obtain a medic by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDIC_BY_SPECIALIZATION = "Cannot obtain a medic by its specialization at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_MEDIC = "Cannot insert new medic at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_MEDIC = "Cannot update a medic at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_MEDIC = "Cannot delete new medic at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_MEDICS = "Cannot read all medics at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_MEDICS_WITH_LIMITATION = "Cannot read medics with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTIONARY_BY_ID = "Cannot obtain a questionary by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTIONARY_BY_NAME = "Cannot obtain a questionary by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_QUESTIONARY = "Cannot insert new questionary at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_QUESTIONARY = "Cannot update a questionary at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_QUESTIONARY = "Cannot delete new questionary at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONARIES = "Cannot read all questionaries at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_QUESTIONARIES_WITH_LIMITATION = "Cannot read questionaries with limitation at service layer";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTION_BY_ID = "Cannot obtain a question by its id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTION_BY_NAME = "Cannot obtain a question by its name at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_QUESTION = "Cannot insert new question at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_UPDATE_QUESTION = "Cannot update a question at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_DELETE_QUESTION = "Cannot delete new question at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONS = "Cannot read all questions at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_QUESTIONS_WITH_LIMITATION = "Cannot read questions with limitation at service layer";


    //Extractor exceptions
    public static final String ERR_CANNOT_EXTRACT_USER = "Cannot extract new user";
    public static final String ERR_CANNOT_EXTRACT_CITY = "Cannot extract new city";
    public static final String ERR_CANNOT_EXTRACT_REQUEST = "Cannot extract new request";
    public static final String ERR_CANNOT_EXTRACT_INVOICE = "Cannot extract new invoice";
    public static final String ERR_CANNOT_EXTRACT_DELIVERY = "Cannot extract new delivery";
    public static final String ERR_CANNOT_EXTRACT_RATE = "Cannot extract new rate";
    public static final String ERR_CANNOT_EXTRACT_DISTANCE = "Cannot extract new distance";

    // Request/Response expceptions
    public static final String ERR_CANNOT_HANDLE_POST_REQUEST = "Cannot handle post request";
    public static final String ERR_CANNOT_HANDLE_GET_REQUEST = "Cannot handle get request";

    // Builder exceptions
    public static final String ERR_CANNOT_MAP_USER = "Cannot map user";
    public static final String ERR_CANNOT_MAP_CITY = "Cannot map city";
    public static final String ERR_CANNOT_MAP_DISTANCE = "Cannot map distance";
    public static final String ERR_CANNOT_MAP_DELIVERY = "Cannot map delivery";
    public static final String ERR_CANNOT_MAP_INVOICE = "Cannot map invoice";
    public static final String ERR_CANNOT_MAP_REQUEST = "Cannot map request";
    public static final String ERR_CANNOT_MAP_RATE = "Cannot map rate";

    // Command exception

}
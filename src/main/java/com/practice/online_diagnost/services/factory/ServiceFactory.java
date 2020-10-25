package com.practice.online_diagnost.services.factory;


import com.practice.online_diagnost.repositories.*;
import com.practice.online_diagnost.services.*;

import java.util.logging.Logger;

/**
 * Factory class,
 * creates instances of Services
 *
 * @author Fazliddin Makhsudov
 */
public class ServiceFactory {
    private static final Logger LOG = Logger.getLogger("ServiceFactory");

    /**
     * Create Service intance
     *
     * @param serviceType service type
     * @return BaseService instance
     */
    public static BaseService createService(ServiceType serviceType) {
        LOG.info(String.format("creating instance of %s", serviceType));
        switch (serviceType) {
            case ROLE_SERVICE:
                return new RoleServiceImpl(new MySQLRoleRepositoryImpl());
            case ASSIGNMENT_SERVICE:
                return new AssignmentServiceImpl(new MySQLAssignmentRepositoryImpl());
            case SYMPTOM_SERVICE:
                return new SymptomServiceImpl(new MySQLSymptomRepositoryImpl());
            case DIAGNOS_SERVICE:
                return new DiagnosServiceImpl(new MySQLDiagnosRepositoryImpl());
            case TREATMENT_HISTORY_SERVICE:
                return new TreatmentHistoryServiceImpl(new MySQLTreatmentHistoryRepositoryImpl());
            case USER_SERVICE:
                return new UserServiceImpl(new MySQLUserRepositoryImpl());
            case PATIENT_SERVICE:
                return new PatientServiceImpl(new MySQLPatientRepositoryImpl());
            case MEDIC_SERVICE:
                return new MedicServiceImpl(new MySQLMedicRepositoryImpl());
            case DISEASE_SERVICE:
                return new DiseaseServiceImpl(new MySQLDiseaseRepositoryImpl());
            case QUESTION_SERVICE:
                return new QuestionServiceImpl(new MySQLQuestionRepositoryImpl());
            case QUESTIONARY_SERVICE:
                return new QuestionaryServiceImpl(new MySQLQuestionaryRepositoryImpl());
            default:
                LOG.info(String.format("error, no service ->> %s", serviceType));
                throw new EnumConstantNotPresentException(ServiceType.class, serviceType.name());
        }
    }

}

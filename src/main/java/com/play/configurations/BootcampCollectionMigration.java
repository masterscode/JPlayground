package com.play.configurations;


//import com.recruitmentportal.model.Applicant;
//import com.recruitmentportal.model.BootcampApplication;
//import com.recruitmentportal.repository.ApplicantRepository;
//import com.recruitmentportal.repository.BootcampApplicationRepository;
//import io.mongock.api.annotations.*;
//import lombok.RequiredArgsConstructor;
//
//import java.util.stream.Collectors;
//
//@ChangeUnit(id = "", order = "", author = "", runAlways = false, transactional = false)
//@RequiredArgsConstructor
//public class BootcampCollectionMigration {
//
//    private final BootcampApplicationRepository bootcampApplicationRepository;
//    private final ApplicantRepository applicantRepository;
//
//    @BeforeExecution
//    public void before() {
//        //...
//    }
//
//    @RollbackBeforeExecution
//    public void rollbackBefore() {
//        //...
//    }
//
//    @Execution
//    public void migrationMethod() {
//        var data = bootcampApplicationRepository
//                .findAll().stream()
//                .map(this::buildApplicant).collect(Collectors.toList());
//        applicantRepository.saveAll(data);
//    }
//
//    @RollbackExecution
//    public void rollback() {
//        //...
//    }
//
//    private Applicant buildApplicant(BootcampApplication application) {
//        return Applicant.builder()
//                .firstName(application.getFirstName())
//                .middleName(application.getMiddleName())
//                .lastName(application.getLastName())
//                .email(application.getEmail())
//                .dateOfBirth(application.getDateOfBirth())
//                .phoneNumber(application.getPhoneNumber())
//                .whatsAppNumber(application.getWhatsAppNumber())
//                .gender(application.getGender())
//
//                .stateOfOrigin(application.getStateOfOrigin())
//                .currentLocation(application.getCurrentLocation())
//                .linkedIn(application.getLinkedIn())
//
//                .cycleName(application.getCycleName())
//
//                .institution(application.getInstitution())
//                .degree(application.getDegree())
//                .nyscStatus(application.getNyscStatus())
//                .paymentOption(application.getPaymentOption())
//                .courseOfStudy(application.getCourseOfStudy())
//                .highestQualification(application.getHighestQualification())
//                .programmingExperience(application.getProgrammingExperience())
//                .hearAboutUs(application.getHearAboutUs())
//
//
//                .category(application.getCategory())
//                .completed(application.getCompleted())
//
//
//                .build();
//    }
//}

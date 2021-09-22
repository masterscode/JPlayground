package com.play.jSE;

import java.util.List;

public class PlayHere {

    public static void main(String[] args) {
        PlayHere playHere = new PlayHere();
        System.out.println(
                playHere.filterApplicantBy("male","completed","","")
        );
    }


    public List<Applicant> filterApplicantBy(String gender, String nysc, String cycle, String state){
        return getAllApplicants().stream().filter(applicant -> applicant.getGender().equalsIgnoreCase((gender.isBlank()? applicant.getGender() : gender))
                    && applicant.getNyscStatus().equalsIgnoreCase((nysc.isBlank()? applicant.getNyscStatus() : nysc))
                    && applicant.getCycle().equalsIgnoreCase((cycle.isBlank() ? applicant.getCycle() : cycle))
                    && applicant.getState().equalsIgnoreCase((state.isBlank() ? applicant.getState() : state))
        ).toList();
    }
    public List<Applicant> getAllApplicants(){
        final String male = "male";
        return List.of(
                new Applicant("emmanuel", male, "Edo", "sq006", "completed"),
                new Applicant("power", male, "Lagos", "sq003", "not completed"),
                new Applicant("victor", male, "Abuja", "sq001", "completed"),
                new Applicant("ochuko", "male", "Edo", "sq007", "not completed"),
                new Applicant("ikenna", "male", "Edo", "sq004", "not completed"),
                new Applicant("paul", "male", "Edo", "sq006", "completed"),
                new Applicant("mary", "male", "anambra", "sq005", "completed"),
                new Applicant("sophia", "female", "Delta", "sq005", "completed"),
                new Applicant("oke", "male", "Edo", "sq007", "not completed"),
                new Applicant("glory", "female", "Delta", "sq006", "not completed"),
                new Applicant("blessing", "male", "abuja", "sq007", "completed"),
                new Applicant("pope", "female", "Bayelsa", "sq008", "completed"),
                new Applicant("ferdinand", "male", "Rivers", "sq008", "completed"),
                new Applicant("wizkid", "male", "Lagos", "sq002", "completed"),
                new Applicant("poseidon", "male", "Ekiti", "sq001", "completed")

                );
    }
}

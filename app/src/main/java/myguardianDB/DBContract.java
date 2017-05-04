package myguardianDB;

import android.content.ContentValues;
import android.provider.BaseColumns;
//import android.support.design.widget.TabLayout;

/**
 * Created by aphart on 10/30/2016.
 */

public abstract class DBContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String BOOLEAN_TYPE = " BOOLEAN";
    private static final String COMMA = " , ";
    private static final String NOT_NULL = " NOT NULL";
    private static final String PRIMARYKEY_ID = " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE";

    public static final class Organization implements BaseColumns{
        public static final String TABLE_NAME = "orgid";
        public static final String ORG_NAME = "orgname";
        public static final String RELIGIOUS_AFFILIATION = "religiousaffiliation";
        public static final String WEBSITE_URL = "websiteurl";
        public static final String PRIMARY_PHONE = "primaryphone";
        public static final String SECONDARY_PHONE = "secondaryphone";
        public static final String PRIMARY_EMAIL = "primaryemail";
        public static final String SECONDARY_EMAIL = "secondaryemail";
        public static final String LOCATION = "location";
        public static final String HOURS_OFFICE = "officehours";
        public static final String HOURS_FOR_GETTING_BED = "bedreservationhours";
        public static final String HOURS_BREAKFAST = "breakfasthours";
        public static final String HOURS_RESTROOM = "restroomhours";
        public static final String HOURS_SHOWER = "showerhours";
        public static final String HOURS_LUNCH = "lunchhours";
        public static final String HOURS_DINNER = "dinnerhours";



        public ContentValues generateOrganizationTable(
                String OrganizationName,
                String ReligiousAffiliation,
                String PrimaryPhone,
                String SecondaryPhone,
                String PrimaryEmail,
                String SecondaryEmail,
                String WebsiteURL,
                String Location,
                String OfficeHours,
                String HoursToGetBed,
                String ShowerHours,
                String RestroomHours,
                String BreakfastHours,
                String LunchHours,
                String DinnerHours

        ){

            ContentValues values = new ContentValues();

            values.put(ORG_NAME, OrganizationName);
            values.put(RELIGIOUS_AFFILIATION, ReligiousAffiliation);
            values.put(WEBSITE_URL, WebsiteURL);
            values.put(PRIMARY_PHONE, PrimaryPhone);
            values.put(SECONDARY_PHONE, SecondaryPhone);
            values.put(PRIMARY_EMAIL, PrimaryEmail);
            values.put(SECONDARY_EMAIL, SecondaryEmail);
            values.put(LOCATION, Location);
            values.put(HOURS_OFFICE, OfficeHours);
            values.put(HOURS_FOR_GETTING_BED, HoursToGetBed);
            values.put(HOURS_SHOWER, ShowerHours);
            values.put(HOURS_RESTROOM, RestroomHours);
            values.put(HOURS_BREAKFAST, BreakfastHours);
            values.put(HOURS_LUNCH, LunchHours);
            values.put(HOURS_DINNER, DinnerHours);
            return values;
        }



    }
    public static final class Housing implements BaseColumns{
        public static final String TABLE_NAME = "housingid";
        public static final String LONTERM_HOUSING = "longtermhousing";
        public static final String SHORTTERM_HOUSING = "shorttermhousing";
        public static final String NUMBER_OF_BEDS = "numberofbeds";
        public static final String NUMBER_OF_RESTROOM_TOILETS = "numberoftoilets";
        public static final String NUMBER_OF_SHOWERS = "numberofshowers";
        public static final String SUPPLIES_CLOTHING = "suppliesclothing";
        public static final String SUPPLIES_TOILETRY = "suppliestoiletry";
        public static final String ORG_ID = "orgid";
        public static final String GENDER_RESTRICTION = "genderrestriction";

        public ContentValues generateHousingTable(
                boolean LongTermHousing,
                boolean ShortTermHousing,
                int NumberOfBeds,
                int NumberOfToilets,
                int NumberOfShowers,
                boolean SuppliesClothing,
                boolean SuppliesToiletries,
                int OrgId,
                String GenderRestriction

        ){

            ContentValues values = new ContentValues();
            values.put(LONTERM_HOUSING, LongTermHousing);
            values.put(SHORTTERM_HOUSING, ShortTermHousing);
            values.put(NUMBER_OF_BEDS, NumberOfBeds);
            values.put(NUMBER_OF_RESTROOM_TOILETS, NumberOfToilets);
            values.put(NUMBER_OF_SHOWERS, NumberOfShowers);
            values.put(SUPPLIES_CLOTHING, SuppliesClothing);
            values.put(SUPPLIES_TOILETRY, SuppliesToiletries);
            values.put(ORG_ID, OrgId);
            values.put(GENDER_RESTRICTION, GenderRestriction);

            return values;
        }
    }

    public static final class Food implements BaseColumns{
        public static final String FOOD_TABLE_NAME = "foodserviceid";
        public static final String SERVES_LUNCH = "serveslunch";
        public static final String SERVES_BREAKFAST = "servesbreakfast";
        public static final String SERVES_DINNER = "servesdinner";
        public static final String BAG_FOOD = "bagfood";
        public static final String FEEDING_CAPACITY = "feedingcapacity";
        public static final String ORG_ID = "orgid";
        public static final String GENDER_RESTRICTION = "genderrestriction";

        public ContentValues generateFoodTable(
                boolean ServesBreakfast,
                boolean ServesLunch,
                boolean ServesDinner,
                boolean BagFood,
                int FeedingCapacity,
                int OrgId,
                String GenderRestriction
        ){


            ContentValues values = new ContentValues();

            values.put(SERVES_BREAKFAST, ServesBreakfast);
            values.put(SERVES_LUNCH, ServesLunch);
            values.put(SERVES_DINNER, ServesDinner);
            values.put(BAG_FOOD, BagFood);
            values.put(FEEDING_CAPACITY, FeedingCapacity);
            values.put(ORG_ID, OrgId);
            values.put(GENDER_RESTRICTION, GenderRestriction);

            return values;
        }
    }

    public static final class JobTraining implements BaseColumns{
        public static final String JOB_TRAINING_TABLE_NAME = "jobtraining";
        public static final String RESUME_WRITING = "resumewriting";
        public static final String QUALIFIERS = "qualifiers";
        public static final String INTERVIEW_SKILLS = "interviewskills";
        public static final String JOB_TRAINING = "jobtraining";
        public static final String EMPLOYMENT_SERVICES = "employmentservices";
        public static final String JOB_PLACEMENT = "jobplacement" ;
        public static final String GENDER_RESTRICTION = "genderrestriction";
        public static final String ORG_ID = "orgid";

        public ContentValues generateJobTrainingTable(

                boolean ResumeWriting,
                String Qualifiers,
                boolean InterviewSkills,
                boolean JobTrainingOffered,
                boolean EmploymentServices,
                boolean JobPlacement,
                String GenderRestriction,
                int OrgId
        ){
            ContentValues values = new ContentValues();

            values.put(RESUME_WRITING, ResumeWriting);
            values.put(QUALIFIERS, Qualifiers);
            values.put(INTERVIEW_SKILLS , InterviewSkills);
            values.put(JOB_TRAINING, JobTrainingOffered);
            values.put(EMPLOYMENT_SERVICES, EmploymentServices);
            values.put(JOB_PLACEMENT, JobPlacement);
            values.put(GENDER_RESTRICTION, GenderRestriction);
            values.put(ORG_ID, OrgId);

            return values;
        }

    }

    public static final class PhysicalHealth implements BaseColumns{
        public static final String PHYSICAL_HEALTH_TABLE_NAME = "physicalhealthid";
        public static final String MEDICAL_TESTING_DESCRIPTION = "medicaltestingdescription";
        public static final String DISEASE_MANAGMENT = "diseasemanagement";
        public static final String MEDICAL_TESTING = "medicaltesting";
        public static final String DISEASE_MANAGEMENT_DESCRIPTION = "diseasemanagementdescription";
        public static final String FREE = "free";
        public static final String STD_CARE = "stdcare";
        public static final String BIRTH_CONTROL = "birthcontrol";
        public static final String ABORTION_SERVICES = "abortionservices";
        public static final String ADDICTION_MEDICATION = "addictionmedication";
        public static final String CHILD_HEALTHCARE_SPECIALIZATION = "childhealthcarespecialization";
        public static final String ACCEPTS_MEDICADE = "acceptsmedicade";
        public static final String PREGNANCY_CARE = "pregnacycare";
        public static final String ORG_ID = "orgid";
        public static final String GENDER_RESTRICTION = "genderrestriction";


        public ContentValues generatePhysicalHealthTable(
                boolean MedicalTesting,
                String MedicalTestingDecription,
                boolean DiseaseMgmt,
                String DiseaseMgmtDescription,
                boolean Free,
                boolean StdCare,
                boolean BirthControl,
                boolean AbortionServices,
                boolean AddictionMedication,
                boolean ChildHealthcareSpecialization,
                boolean AcceptsMedicade,
                boolean PregnancyCare,
                String GenderRestriction,
                int OrgID
        ){

            ContentValues values = new ContentValues();
            values.put(MEDICAL_TESTING, MedicalTesting);
            values.put(MEDICAL_TESTING_DESCRIPTION, MedicalTestingDecription);
            values.put(DISEASE_MANAGMENT, DiseaseMgmt);
            values.put(DISEASE_MANAGEMENT_DESCRIPTION, DiseaseMgmtDescription);
            values.put(FREE, Free);
            values.put(STD_CARE, StdCare);
            values.put(BIRTH_CONTROL, BirthControl);
            values.put(ABORTION_SERVICES, AbortionServices);
            values.put(ADDICTION_MEDICATION, AddictionMedication);
            values.put(CHILD_HEALTHCARE_SPECIALIZATION, ChildHealthcareSpecialization);
            values.put(ACCEPTS_MEDICADE, AcceptsMedicade);
            values.put(PREGNANCY_CARE, PregnancyCare);
            values.put(GENDER_RESTRICTION, GenderRestriction);
            values.put(ORG_ID, OrgID);

            return values;
        }



    }

    public static final class ResourceAbuseViolence implements BaseColumns{
        public static final String ABUSE_TABLE_NAME = "abuse";
        public static final String POLICE_COUNSELING = "policecounseling";
        public static final String PROVIDE_SERVICES_TO_CHILDREN = "providesservicestochildren";
        public static final String ANONYMITY_PROTECTED = "anonymityprotected";
        public static final String OFFERS_SHELTER = "offersshelter";
        public static final String MENTAL_HEALTH_ID = "mentalhealthid";
        public static final String ORG_ID = "orgid";
        public static final String GENDER_RESTRICTION = "genderrestriction";


        public ContentValues generateAbuseTable(
                boolean PoliceCounseling,
                boolean ProvidesServicesToChildren,
                boolean AnonymityProtected,
                boolean OffersShelter,
                String GenderRestriction,
                int OrgID,
                int MentalHealthId
        ){

            ContentValues values = new ContentValues();

            values.put(POLICE_COUNSELING, PoliceCounseling);
            values.put(PROVIDE_SERVICES_TO_CHILDREN, ProvidesServicesToChildren);
            values.put(ANONYMITY_PROTECTED, AnonymityProtected);
            values.put(OFFERS_SHELTER, OffersShelter);
            values.put(GENDER_RESTRICTION, GenderRestriction);
            values.put(ORG_ID, OrgID);
            values.put(MENTAL_HEALTH_ID, MentalHealthId);

            return values;
        }





    }

    public static final class MentalHealth implements BaseColumns{
        public static final String MENTAL_HEALTH_TABLE_NAME = "mentalhealthid";
        public static final String ADDICTION_HELP = "addictionhelp";
        public static final String GREIF_COUNSELING = "griefcounseling";
        public static final String LIVE_IN_ADDICTION_TREATMENT = "liveinaddictiontreatment";
        public static final String GENERAL_COUNSELING = "generalcounseling";
        public static final String ANXIETY_TREATMENT = "anxietytreatment";
        public static final String ANGER_MANAGMENT = "angermanagement";
        public static final String ABUSE_COUNSELING = "abusecounseling";
        public static final String FREE = "free";
        public static final String ORG_ID = "orgid";
        public static final String ACCEPTS_MEDICADE = "acceptsmedicade";
        public static final String GENDER_RESTRICTION = "genderrestriction";

        public ContentValues generateMentalHealth (
                boolean AddictionHelp,
                boolean GriefCounseling,
                boolean LiveInAddictionTreatment,
                boolean GeneralCounseling,
                boolean AnxietyTreatment,
                boolean AngerManagment,
                boolean AbuseCounseling,
                boolean Free,
                boolean AcceptsMedicaid,
                String GenderRestriction,
                int OrgId
        ) {

            ContentValues values = new ContentValues();

            values.put(ADDICTION_HELP, AddictionHelp);
            values.put(GREIF_COUNSELING, GriefCounseling);
            values.put(LIVE_IN_ADDICTION_TREATMENT, LiveInAddictionTreatment);
            values.put(GENERAL_COUNSELING, GeneralCounseling);
            values.put(ANXIETY_TREATMENT, AnxietyTreatment);
            values.put(ANGER_MANAGMENT, AngerManagment);
            values.put(ABUSE_COUNSELING, AbuseCounseling);
            values.put(FREE, Free);
            values.put(ACCEPTS_MEDICADE, AcceptsMedicaid);
            values.put(GENDER_RESTRICTION, GenderRestriction);
            values.put(ORG_ID, OrgId);

            return values;
        }

    }

    public static final class UtilityRentEviction implements BaseColumns {
        public static final String UTILITY_TABLE_NAME = "utilit";
        public static final String FINANCIAL_UTILITY_AID = "financialutilityaid";
        public static final String RENT_AID = "rentaid";
        public static final String EVICTION_INTERVENTION = "evictionintervention";
        public static final String BUDGET_COUNSELING = "budgetcounseling";
        public static final String ORG_ID = "orgid";
        public static final String GENDER_RESTRICTION = "genderrestriction";

        public ContentValues generateUtilityTableValues(
                boolean FinancialUtilityAid,
                boolean RentAid,
                boolean EvictionIntervention,
                boolean BudgetCounseling,
                String GenderRestriction,
                int OrgID
        ){

            ContentValues values = new ContentValues();
            values.put(FINANCIAL_UTILITY_AID, FinancialUtilityAid);
            values.put(RENT_AID, RentAid);
            values.put(EVICTION_INTERVENTION, EvictionIntervention);
            values.put(BUDGET_COUNSELING, BudgetCounseling);
            values.put(GENDER_RESTRICTION, GenderRestriction);
            values.put(ORG_ID, OrgID);

            return values;
        }
    }

    public static final class Government implements BaseColumns{
        public static final String GOVERNMENT_TABLE_NAME = "governmentid";
        public static final String PROGRAM = "program";
        public static final String QUALIFIER = "qualifier";
        public static final String GENDER_RESTRICTION = "genderrestriction";
        public static final String REQUIRED_DOCUMENTS = "requireddocuments";
        public static final String FINANCIAL_ASSISTANCE = "financialassitance";
        public static final String SERVICES = "services";
        public static final String SERVICES_DESCRIPTION = "servicesdescription";
        public static final String HOURS_SUNDAY = "sundayhours";
        public static final String HOURS_MONDAY = "mondayhours";
        public static final String HOURS_TUESDAY = "tuesdayhours";
        public static final String HOURS_WEDNESDAY = "wednesdayhours";
        public static final String HOURS_THURSDAY = "thursdayhours";
        public static final String HOURS_FRIDAY = "fridayhours";
        public static final String HOURS_SATURDAY = "saturdayhours";
        public static final String WEBSITE_URL = "websiteurl";
        public static final String PRIMARY_PHONE = "primaryphone";
        public static final String SECONDARY_PHONE = "secondaryphone";
        public static final String PRIMARY_EMAIL = "primaryemail";
        public static final String SECONDARY_EMAIL = "secondaryemail";
        public static final String LOCATION = "location";


        public static ContentValues generateGovernmentTableValues(
                String Program,
                String Qualifier,
                String RequiredDocuments,
                boolean FinancialAssistance,
                boolean Services,
                String ServicesDescription,
                String PrimaryPhone,
                String SecondaryPhone,
                String PrimaryEmail,
                String SecondaryEmail,
                String WebsiteURL,
                String Location,
                String SundayHours,
                String MondayHours,
                String TuesdayHours,
                String WednesdayHours,
                String ThursdayHours,
                String FridayHours,
                String SaturdayHours,
                String GenderRestriction
        ){

            ContentValues values = new ContentValues();
            values.put(PROGRAM, Program);
            values.put(QUALIFIER, Qualifier);
            values.put(REQUIRED_DOCUMENTS, RequiredDocuments);
            values.put(FINANCIAL_ASSISTANCE, FinancialAssistance);
            values.put(SERVICES, Services);
            values.put(SERVICES_DESCRIPTION, ServicesDescription);
            values.put(WEBSITE_URL, WebsiteURL);
            values.put(PRIMARY_PHONE, PrimaryPhone);
            values.put(SECONDARY_PHONE, SecondaryPhone);
            values.put(PRIMARY_EMAIL, PrimaryEmail);
            values.put(SECONDARY_EMAIL, SecondaryEmail);
            values.put(LOCATION, Location);
            values.put(HOURS_SUNDAY, SundayHours);
            values.put(HOURS_MONDAY, MondayHours);
            values.put(HOURS_TUESDAY, TuesdayHours);
            values.put(HOURS_WEDNESDAY, WednesdayHours);
            values.put(HOURS_THURSDAY, ThursdayHours);
            values.put(HOURS_FRIDAY, FridayHours);
            values.put(HOURS_SATURDAY, SaturdayHours);
            values.put(GENDER_RESTRICTION, GenderRestriction);

            return values;
        }
    }




    public static final class UserInfo implements BaseColumns{
        public static final String USER_INFO_TABLE = "userinfo";
        public static final String GENDER = "gender";
        public static final String PHONE_NUMBER = "phone";
        public static final String EMAIL = "email";
        public static final String PREFERRED_CONTACT_METHOD = "preferredcontactmethod";
        public static final String ADDICTION_STATUS = "addictionstatus";
        public static final String NUMBER_OF_CHILDREN = "numberofchildren";
        public static final String AGE = "age";
        public static final String BIRTH_DATE = "birthdate";
        public static final String HOUSING_STATUS = "housingstatus";
        public static final String JOB_STATUS = "jobstatus";
        public static final String EMPLOYMENT_STATUS = "employmentstatus";
        public static final String EDUCATION_LEVEL = "educationlevel";
        public static final String MENTAL_HEALTH_ISSUES = "mentalhealthissues";
        public static final String PHYSICAL_HEALTH_ISSUES = "healthissues";
        public static final String PREGNANT = "pregnant";
        public static final String ADDICTION_DETAILS_ID = "addictiondetailsid";
        public static final String MENTAL_HEALTH_ISSUES_ID = "mentalhealthissueid";
        public static final String PHYSICAL_HEALTH_ISSUES_ID = "physicalhealthissueid";
        public static final String IS_NEW_USER = "newuser";


        //Gender Constants
        public static final String MALE = "male";
        public static final String FEMALE = "female";
        public static final String NON_GENDERED = "nongendered";

        public static ContentValues createNewUserInfo(
                String Gender,
                String PhoneNumber,
                String Email,
                String PreferedContactMethod,
                boolean AddictionStatus,
                int NumberOfChildren,
                int Age,
                String BirthDate,
                String HousingStatus,
                String EmploymentStatus,
                String EducationLevel,
                boolean MentalHealthIssues,
                boolean PhysicalHealthIssues,
                boolean Pregnant,
                boolean IsNewUser
        ){
            ContentValues values = new ContentValues();

            values.put(GENDER, Gender);
            values.put(PHONE_NUMBER, PhoneNumber);
            values.put(EMAIL, Email);
            values.put(PREFERRED_CONTACT_METHOD, PreferedContactMethod);
            values.put(ADDICTION_STATUS, AddictionStatus);
            values.put(NUMBER_OF_CHILDREN, NumberOfChildren);
            values.put(AGE, Age);
            values.put(BIRTH_DATE, BirthDate);
            values.put(HOUSING_STATUS, HousingStatus);
            values.put(EMPLOYMENT_STATUS, EmploymentStatus);
            values.put(EDUCATION_LEVEL, EducationLevel);
            values.put(MENTAL_HEALTH_ISSUES, MentalHealthIssues);
            values.put(PHYSICAL_HEALTH_ISSUES, PhysicalHealthIssues);
            values.put(PREGNANT, Pregnant);
            values.put(IS_NEW_USER, IsNewUser);
            return values;
        }
    }

    public static final class MentalHealthIssues implements BaseColumns{
        /*
        * To be filled with a huge list of constants for mental health issues.
        * The point of this will be to have a backend database where we can have self-reported data for tracking
        * the issues that homeless may have with mental health. This means we can target the individual with needed interventions
        * to help stabilize their issues and help in keeping them off the streets.
        * */
    }
    public static final class PhysicalHealthIssues implements BaseColumns{
        /*
        * To be filled with a huge list of constants for physical health issues.
        *The point of this will be to have a backend database where we can have self-reported data for tracking
        * the issues that homeless may have with mental health. This means we can target the individual with needed interventions
        * to help stabilize their issues and ultimately save money intervening late stage at the ER */
    }

    //Constants class to be filled.
    // public static final class X implements BaseColumns{    }


    public static final String CREATE_ORGANIZATION_TABLE =
            "CREATE TABLE "
                    + Organization.TABLE_NAME
                    + " ("
                    + Organization._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + Organization.ORG_NAME + TEXT_TYPE + COMMA
                    + Organization.RELIGIOUS_AFFILIATION + TEXT_TYPE + COMMA
                    + Organization.WEBSITE_URL + TEXT_TYPE + COMMA
                    + Organization.PRIMARY_PHONE + TEXT_TYPE + COMMA
                    + Organization.SECONDARY_PHONE + TEXT_TYPE +COMMA
                    + Organization.PRIMARY_EMAIL + TEXT_TYPE +COMMA
                    + Organization.SECONDARY_EMAIL + TEXT_TYPE +COMMA
                    + Organization.LOCATION + TEXT_TYPE + COMMA
                    + Organization.HOURS_OFFICE + TEXT_TYPE + COMMA
                    + Organization.HOURS_FOR_GETTING_BED + TEXT_TYPE + COMMA
                    + Organization.HOURS_SHOWER + TEXT_TYPE + COMMA
                    + Organization.HOURS_RESTROOM + TEXT_TYPE + COMMA
                    + Organization.HOURS_BREAKFAST + TEXT_TYPE + COMMA
                    + Organization.HOURS_LUNCH + TEXT_TYPE + COMMA
                    + Organization.HOURS_DINNER + TEXT_TYPE
                    + " )";

    public static final String CREATE_HOUSING_TABLE =
            "CREATE TABLE "
                    + Housing.TABLE_NAME
                    + " ("
                    + Housing._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + Housing.LONTERM_HOUSING + BOOLEAN_TYPE + COMMA
                    + Housing.SHORTTERM_HOUSING + BOOLEAN_TYPE + COMMA
                    + Housing.NUMBER_OF_BEDS + INT_TYPE + COMMA
                    + Housing.NUMBER_OF_RESTROOM_TOILETS + INT_TYPE + COMMA
                    + Housing.NUMBER_OF_SHOWERS + INT_TYPE + COMMA
                    + Housing.SUPPLIES_CLOTHING + BOOLEAN_TYPE + COMMA
                    + Housing.SUPPLIES_TOILETRY + BOOLEAN_TYPE + COMMA
                    + Housing.ORG_ID + INT_TYPE +  COMMA
                    + Housing.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                    + "FOREIGN KEY(" + Housing.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")"
                    + " )";

public static final String CREATE_FOOD_TABLE =
        "CREATE TABLE "
        + Food.FOOD_TABLE_NAME
        + " ("
                + Food._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                + Food.SERVES_BREAKFAST + BOOLEAN_TYPE + COMMA
                + Food.SERVES_LUNCH + BOOLEAN_TYPE + COMMA
                + Food.SERVES_DINNER + BOOLEAN_TYPE + COMMA
                + Food.BAG_FOOD + BOOLEAN_TYPE + COMMA
                + Food.FEEDING_CAPACITY + INT_TYPE + COMMA
                + Food.ORG_ID + INT_TYPE + COMMA
                + Food.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                + "FOREIGN KEY(" + Food.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")"
                + " )";

    public static final String CREATE_JOB_TRAINING_TABLE =
            "CREATE TABLE "
            + JobTraining.JOB_TRAINING_TABLE_NAME
            +" ("
            + JobTraining._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + JobTraining.RESUME_WRITING + BOOLEAN_TYPE + COMMA
                    + JobTraining.QUALIFIERS + TEXT_TYPE + COMMA
                    + JobTraining.INTERVIEW_SKILLS + BOOLEAN_TYPE + COMMA
                    + JobTraining.JOB_TRAINING + BOOLEAN_TYPE + COMMA
                    + JobTraining.EMPLOYMENT_SERVICES + BOOLEAN_TYPE + COMMA
                    + JobTraining.JOB_PLACEMENT + BOOLEAN_TYPE + COMMA
                    + JobTraining.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                    + JobTraining.ORG_ID + INT_TYPE + COMMA
                    + "FOREIGN KEY(" + JobTraining.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")"
                    + " )";
    public static final String CREATE_PHYSICAL_HEALTH_TABLE =
            "CREATE TABLE "
            + PhysicalHealth.PHYSICAL_HEALTH_TABLE_NAME
            + " ("
                    + PhysicalHealth._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + PhysicalHealth.MEDICAL_TESTING + BOOLEAN_TYPE  + COMMA
                    + PhysicalHealth.MEDICAL_TESTING_DESCRIPTION + TEXT_TYPE + COMMA
                    + PhysicalHealth.DISEASE_MANAGMENT + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.DISEASE_MANAGEMENT_DESCRIPTION + TEXT_TYPE + COMMA
                    + PhysicalHealth.FREE + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.STD_CARE + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.BIRTH_CONTROL + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.ABORTION_SERVICES + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.ADDICTION_MEDICATION + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.CHILD_HEALTHCARE_SPECIALIZATION + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.ACCEPTS_MEDICADE + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.PREGNANCY_CARE + BOOLEAN_TYPE + COMMA
                    + PhysicalHealth.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                    + PhysicalHealth.ORG_ID + INT_TYPE + COMMA
                    + "FOREIGN KEY(" + PhysicalHealth.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")"
                    + " )";

    public static final String CREATE_ABUSE_TABLE =
            "CREATE TABLE "
            + ResourceAbuseViolence.ABUSE_TABLE_NAME
            + " ("
                    +ResourceAbuseViolence._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + ResourceAbuseViolence.POLICE_COUNSELING + BOOLEAN_TYPE + COMMA
                    + ResourceAbuseViolence.PROVIDE_SERVICES_TO_CHILDREN + BOOLEAN_TYPE + COMMA
                    + ResourceAbuseViolence.ANONYMITY_PROTECTED + BOOLEAN_TYPE + COMMA
                    + ResourceAbuseViolence.OFFERS_SHELTER + BOOLEAN_TYPE + COMMA
                    + ResourceAbuseViolence.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                    + ResourceAbuseViolence.ORG_ID + INT_TYPE + COMMA
                    + ResourceAbuseViolence.MENTAL_HEALTH_ID + INT_TYPE + COMMA
                    + "FOREIGN KEY(" + ResourceAbuseViolence.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")" + COMMA
                    + "FOREIGN KEY(" + ResourceAbuseViolence.MENTAL_HEALTH_ID +") " + "REFERENCES " + MentalHealth.MENTAL_HEALTH_TABLE_NAME+"(" + MentalHealth._ID +")"
                    + " )";
    public static final String CREATE_MENTAL_HEALTH_TABLE =
            "CREATE TABLE "
                    + MentalHealth.MENTAL_HEALTH_TABLE_NAME
                    + " ("
                    + MentalHealth._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + MentalHealth.ADDICTION_HELP + BOOLEAN_TYPE + COMMA
                    + MentalHealth.GREIF_COUNSELING + BOOLEAN_TYPE + COMMA
                    + MentalHealth.LIVE_IN_ADDICTION_TREATMENT + BOOLEAN_TYPE + COMMA
                    + MentalHealth.GENERAL_COUNSELING + BOOLEAN_TYPE + COMMA
                    + MentalHealth.ANXIETY_TREATMENT + BOOLEAN_TYPE + COMMA
                    + MentalHealth.ANGER_MANAGMENT  + BOOLEAN_TYPE + COMMA
                    + MentalHealth.ABUSE_COUNSELING + BOOLEAN_TYPE + COMMA
                    + MentalHealth.FREE + BOOLEAN_TYPE + COMMA
                    + MentalHealth.ACCEPTS_MEDICADE + BOOLEAN_TYPE + COMMA
                    + MentalHealth.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                    + MentalHealth.ORG_ID + INT_TYPE + COMMA
                    + "FOREIGN KEY(" + MentalHealth.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")"
                    + " )";
    public static final String CREATE_UTILITY_TABLE =
            "CREATE TABLE "
                    + UtilityRentEviction.UTILITY_TABLE_NAME
                    + " ("
                    + UtilityRentEviction._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + UtilityRentEviction.FINANCIAL_UTILITY_AID + BOOLEAN_TYPE + COMMA
                    + UtilityRentEviction.RENT_AID + BOOLEAN_TYPE + COMMA
                    + UtilityRentEviction.EVICTION_INTERVENTION + BOOLEAN_TYPE + COMMA
                    + UtilityRentEviction.BUDGET_COUNSELING + BOOLEAN_TYPE + COMMA
                    + UtilityRentEviction.GENDER_RESTRICTION + TEXT_TYPE + COMMA
                    + UtilityRentEviction.ORG_ID + INT_TYPE + COMMA
                    + "FOREIGN KEY(" + MentalHealth.ORG_ID +") " + "REFERENCES " + Organization.TABLE_NAME+"(" + Organization._ID +")"
                    + " )";
    public static final String CREATE_GOVERNMENT_TABLE =
            "CREATE TABLE "
                    + Government.GOVERNMENT_TABLE_NAME
                    + " ("
                    + Government._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + Government.PROGRAM + TEXT_TYPE + COMMA
                    + Government.QUALIFIER + TEXT_TYPE +COMMA
                    + Government.REQUIRED_DOCUMENTS + TEXT_TYPE + COMMA
                    + Government.FINANCIAL_ASSISTANCE + BOOLEAN_TYPE + COMMA
                    + Government.SERVICES + BOOLEAN_TYPE + COMMA
                    + Government.SERVICES_DESCRIPTION + TEXT_TYPE + COMMA
                    + Government.PRIMARY_PHONE + TEXT_TYPE + COMMA
                    + Government.SECONDARY_PHONE + TEXT_TYPE + COMMA
                    + Government.PRIMARY_EMAIL + TEXT_TYPE + COMMA
                    + Government.SECONDARY_EMAIL + TEXT_TYPE + COMMA
                    + Government.WEBSITE_URL + TEXT_TYPE + COMMA
                    + Government.LOCATION + TEXT_TYPE + COMMA
                    + Government.HOURS_SUNDAY + TEXT_TYPE + COMMA
                    + Government.HOURS_MONDAY + TEXT_TYPE + COMMA
                    + Government.HOURS_TUESDAY + TEXT_TYPE + COMMA
                    + Government.HOURS_WEDNESDAY + TEXT_TYPE + COMMA
                    + Government.HOURS_THURSDAY + TEXT_TYPE + COMMA
                    + Government.HOURS_FRIDAY + TEXT_TYPE + COMMA
                    + Government.HOURS_SATURDAY + TEXT_TYPE + COMMA
                    + Government.GENDER_RESTRICTION + TEXT_TYPE
            +" )";

    public static final String CREATE_USER_INFO_TABLE =
            "CREATE TABLE "
                    + UserInfo.USER_INFO_TABLE
                    + " ("
                    + UserInfo._ID + INT_TYPE + " NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE" + COMMA
                    + UserInfo.GENDER + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.PHONE_NUMBER +  TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.EMAIL + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.PREFERRED_CONTACT_METHOD + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.ADDICTION_STATUS + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.NUMBER_OF_CHILDREN + INT_TYPE + NOT_NULL + COMMA
                    + UserInfo.AGE + INT_TYPE + NOT_NULL + COMMA
                    + UserInfo.BIRTH_DATE + INT_TYPE + NOT_NULL + COMMA
                    + UserInfo.HOUSING_STATUS + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.JOB_STATUS + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.EMPLOYMENT_STATUS + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.EDUCATION_LEVEL + TEXT_TYPE + NOT_NULL + COMMA
                    + UserInfo.MENTAL_HEALTH_ISSUES + BOOLEAN_TYPE + NOT_NULL + COMMA
                    + UserInfo.PHYSICAL_HEALTH_ISSUES + BOOLEAN_TYPE + NOT_NULL + COMMA
                    + UserInfo.PREGNANT + BOOLEAN_TYPE + NOT_NULL //add comma when foreign keys are added.
//            Add the foreign keys when the Health issue lists are constructed in a later iteration of the database
                    + ")";


}

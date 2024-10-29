//author: Yashwanth Krishna
import java.util.*;
import java.io.*;
public class javaminiproject {
    static boolean checkDateValidity(int day,int month,int year){
        switch(month){
            case 1,3,5,7,8,10,12:
                if(day>31 || day < 1 || year<=0){
                    return false;
                }
                break;
            case 2:
                if(checkLeapYear(year)&&(day>29 || day < 1 || year<=0)){
                    return false;
                }
                else if(day>28 || day < 1 || year<=0){
                    return false;
                }
            case 4,6,9,11:
                if(day>30 || day<1 || year<=0){
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }
    static boolean checkLeapYear(int year){
        if(year%4==0 && (year%100!=0 || year%400==0)){
            return true;
        }
        return false;
    }
    static int[] parseDate(String day,String month,String year){
        int [] date = new int[3];
        date[0] = Integer.parseInt(day);
        date[1] = Integer.parseInt(month);
        date[2] = Integer.parseInt(year);
        return date;
    }
    static int [] calculateAge(int day , int month , int year , int ref_day , int ref_month , int ref_year){
        int [] age = new int[3];
        age[0] = ref_day - day;
        age[1] = ref_month - month;
        age[2] = ref_year - year;
        if(age[0]<0){
            age[0] = age[0] + noOfDays(ref_month-1, ref_year);
            age[1]--;
        }
        if(age[1]<0){
            age[1] = age[1] + 12;
            age[2]--;
        }
        return age;
    }
    static int [] calculateDoB(int day , int month , int year , int ref_day , int ref_month , int ref_year){
        int [] DoB = new int [3];
        DoB[0] = ref_day - day;
        DoB[1] = ref_month - month;
        DoB[2] = ref_year - year;
        if(DoB[0]<1){
            DoB[0] = DoB[0] + noOfDays(ref_month-1, ref_year);
            DoB[1]--;
        }
        if(DoB[1]<1){
            DoB[1] = DoB[1] + 12;
            DoB[2]--;
        }
        return DoB;
    }
    static int noOfDays(int month,int year){
        switch(month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 2:
                if(checkLeapYear(year)){
                    return 29;
                }else{
                    return 28;
                }
            case 4: case 6: case 9: case 11:
                return 30;
        }
        return 0;
    }
    public static void main(String args[]){
                Scanner sc = new Scanner(System.in);
                String dob,refDate,dlc;
                String format;
                System.out.print("\nEnter date of birth or Age (Ex:- AGE=0054-03-23 , DOB=19-05-2005) : ");
                dob=sc.nextLine();
                System.out.print("\nEnter reference date : ");
                refDate=sc.nextLine();
                System.out.print("\nChoose Dob format :\n1.YYYYdlcMMdlcDD"+"\n2.DDdlcMMdlcYYYY"+"\n3.MMdlcDDYYYY\n");
                format=sc.nextLine();
                System.out.print("\nInput delimiter character (dlc): ");
                dlc=sc.nextLine();
                int [] age = new int[3];
                if(dob.startsWith("DOB")){
                    String DateOfBirth = dob.split("=")[1];
                    String [] date1 = DateOfBirth.split(dlc);
                    String [] date2 = refDate.split(dlc);
                    int [] dob_date = new int [3];
                    int [] ref_date = new int [3];
                    switch(format){
                        case "1":
                            dob_date = parseDate(date1[2], date1[1], date1[0]);
                            ref_date = parseDate(date2[2], date2[1], date2[0]);
                            break;
                        case "2":
                            dob_date = parseDate(date1[0], date1[1], date1[2]);
                            ref_date = parseDate(date2[0], date2[1], date2[2]);
                            break;
                        case "3":
                            dob_date = parseDate(date1[1], date1[0], date1[2]);
                            ref_date = parseDate(date2[1], date2[0], date2[2]);
                            break;
                        default:
                            System.out.println("\nPlease select a valid format");
                            sc.close();
                            return;
                    }
                    if(!checkDateValidity(dob_date[0],dob_date[1], dob_date[2])){
                        System.out.println("\nPlease enter valid Date of Birth");
                        sc.close();
                        return;
                    }
                    if(!checkDateValidity(ref_date[0],ref_date[1], ref_date[2])){
                        System.out.println("\nPlease enter valid Reference Date");
                        sc.close();
                        return;
                    }
                    age=calculateAge(dob_date[0], dob_date[1], dob_date[2], ref_date[0], ref_date[1], ref_date[2]);
                    if(age[2]<0){
                        System.out.println("\nReference year is invalid");
                        sc.close();
                        return;
                    }
                    System.out.println("Age is "+age[0]+" days, "+age[1]+" months, "+age[2]+" years");
                }
                else if(dob.startsWith("AGE")){
                    dob = dob.split("=")[1];
                    String [] date1 = dob.split(dlc);
                    String [] date2 = refDate.split(dlc);
                    int [] dob_date = new int [3];
                    int [] ref_date = new int [3];
                    dob_date = parseDate(date1[2], date1[1], date1[0]);
                    switch(format){
                        case "1":
                            ref_date = parseDate(date2[2], date2[1], date2[0]);
                            break;
                        case "2":
                            ref_date = parseDate(date2[0], date2[1], date2[2]);
                            break;
                        case "3":   
                            ref_date = parseDate(date2[1], date2[0], date2[2]);
                            break;
                        default:
                            System.out.println("\nPlease select a valid format");
                            sc.close();
                            return;
                    }
                    if(dob_date[0]<=0 && dob_date[1]<=0 && dob_date[2]<=0){
                        System.out.println("\nPlease enter valid age");
                        sc.close();
                        return;
                    }
                    if(!checkDateValidity(ref_date[0],ref_date[1], ref_date[2])){
                        System.out.println("\nPlease enter valid Reference Date");
                        sc.close();
                        return;
                    }
                    age=calculateDoB(dob_date[0],dob_date[1],dob_date[2], ref_date[0], ref_date[1],ref_date[2]);
                    System.out.println("Date of Birth is "+age[0]+dlc+age[1]+dlc+age[2]+" ");
                }
                else{
                    System.out.println("\nPlease enter either in DOB=YYYYdlcMMdlcDD or AGE=00YYdlcMMdlcDD");
                    sc.close();
                    return;
                }
                sc.close();
                return;
    }
}
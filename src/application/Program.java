package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.SchoolGrade;
import model.enums.DaysOfTheWeek;
import model.exceptions.DomainException;

public class Program {

    private static String daysOfTheWeek;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List <SchoolGrade> list = new ArrayList<>();

        try {
            System.out.print("How many classes will be registered? ");
            int N = sc.nextInt();

            for (int i = 0; i < N; i++) {
                do {
                    System.out.print("Enter days of the week: ");
                    daysOfTheWeek = sc.next();
                    if (daysOfTheWeek.equals("SUNDAY")) {
                        System.out.println("This day is not available. Try again\n");
                    }
                    else {
                        System.out.print("Enter classes to register: ");
                        sc.nextLine();
                        String classes = sc.nextLine();
                        System.out.print("Enter initial hour(HH:mm): ");
                        Date initialHour = sdf.parse(sc.next());
                        System.out.print("Enter last hour(HH:mm): ");
                        Date lastHour = sdf.parse(sc.next());
                        System.out.println();

                        list.add(new SchoolGrade(classes, initialHour, lastHour, DaysOfTheWeek.valueOf(daysOfTheWeek)));
                    }
                }while (daysOfTheWeek.equals("SUNDAY"));
            } 

            System.out.println("Grade:\n");
            for (SchoolGrade schoolGrade : list) {
                System.out.println(schoolGrade);
            }

            System.out.print("\nWould you like to remove any class(y/n)? ");
            char resp = sc.next().charAt(0);

            if (resp == 'y') {
                System.out.print("Which class will be remove? ");
                String classes = sc.next();

                for (int i = 0; i < list.size(); i++) {
                    list.removeIf(x -> x.getClasses().equals(classes));
                }
                
                System.out.println("\nupdatedGrade:\n");
                for (SchoolGrade schoolGrade : list) {
                    System.out.println(schoolGrade);
                }
            }
        } 
        catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        catch (DomainException e) {
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Input error");
        }
        catch (NumberFormatException e) {
            System.out.println("Error: invalid format");
        }
        catch (RuntimeException e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
        finally {
            System.out.println("\nEnd of program");
        }
        
        sc.close();
    }
}
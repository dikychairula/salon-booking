package com.booking.service;

import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;

        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-11s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
    }

    public static void showAllCustomer(List<Person> customerList){
        int num = 1;
        String member = "";

        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-11s | %-11s | %-15s | %-15s | \n",
                "No.", "ID", "Nama", "Alamat", "Membership");
        System.out.println("+----------------------------------------------------------------------+");
        for (Person person : customerList) {
            if (person instanceof Customer) {
                member = ((Customer)person).getMember().getMembershipName();

                System.out.printf("| %-4s | %-11s | %-11s | %-15s | %-15s | \n",
                num, person.getId(), person.getName(), person.getAddress(), member);
                num++;
            }
        }
        System.out.println("+----------------------------------------------------------------------+");
    }

    public static void showAllEmployee(List<Person> employeeList){
        int num = 1;
        int experience = 0;
        
        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("| %-4s | %-11s | %-11s | %-15s | %-15s | \n",
        "No.", "ID", "Nama", "Alamat", "Pengalaman");                                       
        System.out.println("+----------------------------------------------------------------------+");
        for (Person person : employeeList) {
            if (person instanceof Employee) {
                experience = ((Employee)person).getExperience();

                System.out.printf("| %-4s | %-11s | %-11s | %-15s | %-15s | \n",
                num, person.getId(), person.getName(), person.getAddress(), experience);
                num++;
            }
        }
        System.out.println("+----------------------------------------------------------------------+");
    }

    public static void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        int keuntungan = 0;

        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-4s | %-8s | %-15s | %-35s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4s | %-8s | %-15s | %-35s | %-15s | %-15s | %-10s |\n",
            num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
            if (reservation.getWorkstage().equals("Finish")) {
                keuntungan += reservation.getReservationPrice();
            }
        }
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("Keuntungan :"+keuntungan);
    }

    public static void showAllService(List<Service> serviceList){

        int num = 1;
        System.out.println("+-------------------------------------------------------------+");
        System.out.printf("| %-4s | %-11s | %-20s | %-15s |\n",
                "No.", "ID", "Nama", "Harga");
        System.out.println("+-------------------------------------------------------------+");
        for (Service listService: serviceList) { 
            System.out.printf("| %-4s | %-11s | %-20s | %-15s |\n",
            num, listService.getServiceId(), listService.getServiceName(), listService.getPrice());
            num++;
        }

        System.out.println("+-------------------------------------------------------------+");
    }
}

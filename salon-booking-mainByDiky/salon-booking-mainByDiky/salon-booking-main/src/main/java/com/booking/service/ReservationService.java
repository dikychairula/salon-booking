package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {
    public static void createReservation(List<Person> listAllPerson, List<Service> listAllService){
        
        List<Service> servicesList = new ArrayList<Service>();
        List<Customer> customerList = new ArrayList<Customer>();
        List<Employee> employeeList = new ArrayList<Employee>();

        for (Person person : listAllPerson) {
            if (person instanceof Customer) {
                customerList.add((Customer)person);
            } else{
                employeeList.add((Employee)person);
            }
        }

        Customer customer = new Customer();
        Employee employee = new Employee();
        Service service = new Service();

        Scanner sc = new Scanner(System.in);
        boolean isLooping = false;
        boolean idIsFound = false;

        String inputCustId = "";

        do {
            System.out.println("Input Id Customer :");
            inputCustId = sc.nextLine();
            

            for (int i = 0; i < MenuService.personList.size(); i++) {
                if (MenuService.personList.get(i) instanceof Customer) {
                    if (MenuService.personList.get(i).getId().equalsIgnoreCase(inputCustId)) {
                        customer = (Customer)MenuService.personList.get(i);
                        idIsFound = true;
                        isLooping = true;
                        break;
                    }
                }
            }

            ValidationService.validateId(idIsFound,"Customer");

        } while (!isLooping);

    
        String inputEmpId = "";
        PrintService.showAllEmployee(listAllPerson);

        do {
            isLooping = false;
            idIsFound = false;

            System.out.println("Input Id Employee");
            inputEmpId = sc.nextLine();
            
            for (int i = 0; i < MenuService.personList.size(); i++) {
                if (MenuService.personList.get(i) instanceof Employee) {
                    if (MenuService.personList.get(i).getId().equalsIgnoreCase(inputEmpId)) {
                        employee = (Employee)MenuService.personList.get(i);
                        idIsFound = true;
                        isLooping = true;
                        break;
                    }
                }
            }

        ValidationService.validateId(idIsFound,"Employee");

        } while (!isLooping);

        PrintService.showAllService(listAllService);

        String inputIdSrv = "";
        int indexListService = 0;
        double price = 0;

        do {
            isLooping = false;
            boolean isLooping2 = false;
            boolean idSrvIsFound = false;
            boolean idSrvIsChosen = false;

            System.out.println("Input Id Service");
            inputIdSrv = sc.nextLine();

            for (int i = 0; i < listAllService.size(); i++) {
                if (listAllService.get(i).getServiceId().equalsIgnoreCase(inputIdSrv)) {
                    idSrvIsFound = true;
                    service = MenuService.serviceList.get(i);
                    servicesList.add(service);

                    price += service.getPrice();

                    do {
                        isLooping2 = true;
                        System.out.println("ingin menambah service? (y/n)");
                        String choice = sc.nextLine();

                        if (choice.equalsIgnoreCase("y")) {    
                            isLooping2 = true;
                            
                        } else if (choice.equalsIgnoreCase("n")) {
                            idSrvIsChosen = true;
                            isLooping2 = true;
                            
                        }else{

                        }

                    } while (!isLooping2);
                }
            }

            if (idSrvIsFound && idSrvIsChosen) {
                isLooping = true;
                break;
            }else if(idSrvIsFound){
                listAllService.remove(indexListService);
                System.out.println("Service telah dipilih");
            } else {
               System.out.println("Service ID tidak tersedia"); 
            }

        } while (!isLooping);
        
        String idReservasi = "Rsv-";
        int counterIdRsv = 1;

        Reservation result = new Reservation(idReservasi+counterIdRsv, customer, employee, servicesList, price, "In Process");
            MenuService.reservationList.add(result);
    }

    public static void editReservationWorkstage(List<Reservation> reservationsList){

        Scanner sc = new Scanner(System.in);

        boolean isLooping = false;
        boolean idIsFound = false;

        String regexStatusRev = "Finish|Cancel";
        String inputRevId = "";
        String statusWorkstage = "";

        do {
            PrintService.showRecentReservation(reservationsList);
            System.out.println("Input Id Reservasi :");
            inputRevId = sc.nextLine();

            for (Reservation reservation : reservationsList) {
                if (inputRevId.contains(reservation.getReservationId())) {
                    idIsFound = true;
                    statusWorkstage = ValidationService.validasiInput("Input status workstage (Finish/Cancel)", "Inputan harus berupa Finish/Cancel", regexStatusRev);
                
                    if (statusWorkstage.equalsIgnoreCase("Finish") && reservation.getWorkstage().equals("In Process")) {
                        reservation.setWorkstage("Finish");
                        isLooping = true;
                        break;
                    } else if (statusWorkstage.equalsIgnoreCase("Cancel") && reservation.getWorkstage().equals("In Process")){
                        reservation.setWorkstage("Cancel");
                        isLooping = true;
                        break;
                    }else{
                        
                    }
                }
            }
            ValidationService.validateId(idIsFound,"Reservasi");
        
        } while (!isLooping);
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}

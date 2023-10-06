package com.booking.service;

import java.util.Scanner;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
    public static void validateInput(){

    }

    public static void validateId(boolean idIsFound, String tipeId){
        if (idIsFound) {
            System.out.println(tipeId+" Id ditemukan");
        } else {
            System.out.println("Id yang dicari tidak tersedia");
        }
    }

    public static String validasiInput(String question, String errorMessage, String regex) {
		Scanner input = new Scanner(System.in);
		String result;
		boolean isLooping = true;
		do {
			isLooping = true;
			System.out.println(question);
			result = input.nextLine();
			
			//validasi menggunakan matches
			if (result.matches(regex)) {
				isLooping = false;
			}else {
				System.out.println(errorMessage);
			}
			
		} while (isLooping);
		return result;
	}

}

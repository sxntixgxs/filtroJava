package com.filtro.person.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.filtro.person.application.services.PersonService;
import com.filtro.person.domain.models.Person;

public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    public void createPerson(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese los datos solicitados ");
        String name = "";
        String surname = "";
        String email ="";
        String address ="";
        int idCity = 0;
        int idGender = 0;
        while(true){
            try{
                System.out.println("Ingrese el nombre -sin apellido- ");
                name = sc.nextLine();
                System.out.println("Ingrese el apellido");
                surname = sc.nextLine();
                System.out.println("Ingrese el email: ");
                email = sc.nextLine();
                System.out.println("Ingrese la direccion ");
                address = sc.nextLine();
                System.out.println("Presione para desplegar todas las ciudades disponibles, junto a su identificador: ");
                sc.nextLine();
                personService.showCities().forEach(System.out::println);
                sc.nextLine();
                System.out.println("Ingrese el identificador de la ciudad ");
                idCity = sc.nextInt();
                if(personService.showCities().size()<idCity){
                    System.out.println("Input invalido, intente de nuevo ");
                    sc.nextLine();
                    continue;
                }
                personService.showGenders().forEach(System.out::println);
                sc.nextLine();
                System.out.println("Ingrese el identificador del genero: ");
                idGender = sc.nextInt();
                if(personService.showGenders().size()<idGender){
                    System.out.println("Input invalido, intente de nuevo ");
                    sc.nextLine();
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("Dato invalido, intente de nuevo");
                continue;
            }
        } personService.addPerson(new Person(name, surname, idCity, address, idGender, email, idGender));
        System.out.println(name+" "+surname+" guardado con éxito. ");
    }
    
}

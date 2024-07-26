package com.filtro;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.filtro.config.DBConnection;
import com.filtro.person.application.services.PersonService;
import com.filtro.person.infrastructure.controllers.PersonController;
import com.filtro.person.infrastructure.repositories.PersonRepositoryImp;
import com.filtro.skill.application.services.SkillService;
import com.filtro.skill.infrastructure.controllers.SkillController;
import com.filtro.skill.infrastructure.repositories.SkillRepositoryImp;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        PersonRepositoryImp personRepository = new PersonRepositoryImp(dbConnection);
        PersonService personService = new PersonService(personRepository);
        PersonController personController = new PersonController(personService);


        SkillRepositoryImp skillRepository = new SkillRepositoryImp(dbConnection);
        SkillService skillService = new SkillService(skillRepository);
        SkillController skillController = new SkillController(skillService);
        while(true){
            int choice = menu();
            switch (choice) {
                case 1:
                    personController.createPerson();
                    break;
                case 2:
                    personController.updatePerson();
                    break;
                case 3:
                    skillController.createSkill();
                    break;
                case 4:
                    
                case 7:
                    break;
                default:
                    break;
            }break;
        }
    }
    public static int menu(){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(true){
            try{
                System.out.println("1. Registrar persona");
                System.out.println("2. Asignar habilidad a persona ");
                System.out.println("3. Crear habilidad ");
                System.out.println("4. Encontrar personas por habilidad ");
                System.out.println("5. Actualizar persona ");
                System.out.println("6. Eliminar persona");
                System.out.println("7. Salir");
                choice = sc.nextInt();
                if(choice<1||choice>7){
                    System.out.println("Error con la seleccion, intente de nuevo");
                    sc.nextLine();
                    continue;
                }
                return choice;
            }catch(InputMismatchException e){
                System.out.println("Error con la seleccion, intente de nuevo");
                sc.nextLine();
                continue;
            }

        }

    }
}
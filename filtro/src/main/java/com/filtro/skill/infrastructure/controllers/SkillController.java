package com.filtro.skill.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.filtro.skill.application.services.SkillService;
import com.filtro.skill.domain.models.Skill;

public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    public void createSkill(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Ingrese el nombre de la nueva habilidad");
            try {
                String nombre = sc.nextLine();
                Optional<Skill> optionalSkill = skillService.findSkillByName(nombre);
                if(!optionalSkill.isPresent()){
                    System.out.println("Registrando habilidad ...");
                    sc.nextLine();
                    skillService.createSkill(new Skill(nombre));
                    System.out.println("Registro completo ... ");
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("Error, vuelva a intentarlo");
                    sc.nextLine();
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input invalido, intente de nuevo ");
                sc.nextLine();
                continue;
            }
        }
    }
    public Optional<Skill> skillExists(){
        Scanner sc = new Scanner(System.in);
        while(true){
            sc.nextLine();
            System.out.println("Ingrese el id de la habilidad");
            try {
                String nombre = sc.nextLine();
                return skillService.findSkillByName(nombre);
            }catch(InputMismatchException e){
                System.out.println("error");
                continue;
            }
    }
    }
    public Integer getIdSkillByName(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Ingrese el nombre de la skill ");
            String skill = sc.nextLine();
            try {
                if(skillService.findSkillByName(skill).isPresent()){
                    return skillService.findSkillByName(skill).get().getId();
                }
                else{
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("error");
                continue;
            }
        
        }

    }
}

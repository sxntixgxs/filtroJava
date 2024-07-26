package com.filtro.skill.application.services;

import java.util.Optional;

import com.filtro.skill.domain.models.Skill;
import com.filtro.skill.domain.ports.in.SkillOperations;
import com.filtro.skill.domain.ports.out.SkillRepository;

public class SkillService implements SkillOperations{
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Optional<Skill> createSkill(Skill skill) {
        return skillRepository.saveNewSkill(skill);
    }

    @Override
    public Optional<Skill> findSkillByName(String skill) {
        return skillRepository.findSkillByName(skill);
        
    }
    
}

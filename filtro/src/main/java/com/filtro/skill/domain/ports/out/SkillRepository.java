package com.filtro.skill.domain.ports.out;

import java.util.Optional;

import com.filtro.skill.domain.models.Skill;

public interface SkillRepository {
    Optional<Skill> saveNewSkill(Skill skill);
    Optional<Skill> findSkillByName(String skill);
}

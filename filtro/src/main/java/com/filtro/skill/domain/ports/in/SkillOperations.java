package com.filtro.skill.domain.ports.in;

import java.util.Optional;

import com.filtro.skill.domain.models.Skill;

public interface SkillOperations {
    Optional<Skill> createSkill(Skill skill);
    Optional<Skill> findSkillByName(String skill);
}

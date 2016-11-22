package com.jaysen.leagueoflegendmanual.pattern.clean.domain.Filter;

import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

import java.util.List;

public interface HeroFilter {
    List<HeroEntity> filter(List<HeroEntity> tasks);
}
package com.jaysen.leagueoflegendmanual.domain.Filter;

import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import java.util.List;

public interface HeroFilter {
    List<HeroEntity> filter(List<HeroEntity> tasks);
}
package com.tvmaze.api.midd.services;




import com.tvmaze.api.midd.dtos.ShowResponseDto;

import java.util.List;
import java.util.Map;

public interface ShowService {
    List<ShowResponseDto> searchShows(String query);

    Map<String, Object> getShowById(Long showId);
}

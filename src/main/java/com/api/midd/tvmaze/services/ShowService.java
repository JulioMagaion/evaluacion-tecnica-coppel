package com.api.midd.tvmaze.services;




import com.api.midd.tvmaze.dtos.ShowResponseDto;

import java.util.List;

public interface ShowService {
    List<ShowResponseDto> searchShows(String query);
}

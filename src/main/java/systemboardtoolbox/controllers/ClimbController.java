package systemboardtoolbox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import systemboardtoolbox.models.Climb;
import systemboardtoolbox.models.GetClimbsRequest;
import systemboardtoolbox.models.PagedResult;
import systemboardtoolbox.services.ClimbService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/hold")
@RequiredArgsConstructor
@Validated
public class ClimbController {

    private final ClimbService climbService;

    @PostMapping
    public ResponseEntity<PagedResult<Climb>> getClimbs(@RequestBody GetClimbsRequest request) throws IOException {
        return ResponseEntity.ok(climbService.getClimbs(request));
    }
}

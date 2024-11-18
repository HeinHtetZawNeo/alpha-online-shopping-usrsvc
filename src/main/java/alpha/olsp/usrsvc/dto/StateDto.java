package alpha.olsp.usrsvc.dto;

import lombok.Builder;

@Builder
public record StateDto(
        String stateId,
        String stateName
) {
}
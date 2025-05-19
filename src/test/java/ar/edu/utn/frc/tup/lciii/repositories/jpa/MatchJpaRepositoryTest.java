package ar.edu.utn.frc.tup.lciii.repositories.jpa;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.helpers.MatchHelper;
import ar.edu.utn.frc.tup.lciii.helpers.PlayerHelper;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MatchJpaRepositoryTest {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Test
    void getAllByPlayerIdAndMatchStatusTest() {
        // Arrange
        PlayerEntity player = PlayerHelper.getPlayerEntity(PlayerHelper.EMAIL_OK, PlayerHelper.BALANCE_INIT);
        player = playerJpaRepository.save(player);

        MatchEntity activeMatch = MatchHelper.getMatchEntity(player, MatchStatus.PLAYING, MatchHelper.ROUNDS_ENTITY_EMPTY);
        MatchEntity finishedMatch = MatchHelper.getMatchEntity(player, MatchStatus.FINISH, MatchHelper.ROUNDS_ENTITY_EMPTY);

        matchJpaRepository.save(activeMatch);
        matchJpaRepository.save(finishedMatch);

        // Act
        Optional<List<MatchEntity>> matchesOptional = matchJpaRepository
                .getAllByPlayerIdAndMatchStatus(player.getId(), MatchStatus.PLAYING);

        // Assert
        assertThat(matchesOptional).isPresent();

        List<MatchEntity> matches = matchesOptional.get();
        assertThat(matches).hasSize(1); // Esperamos solo un match con status PLAYING
        assertThat(matches.get(0).getMatchStatus()).isEqualTo(MatchStatus.PLAYING);
        assertThat(matches.get(0).getPlayer().getId()).isEqualTo(player.getId());
    }
}

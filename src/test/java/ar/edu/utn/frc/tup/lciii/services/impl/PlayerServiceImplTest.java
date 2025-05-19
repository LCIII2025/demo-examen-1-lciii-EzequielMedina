package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerJpaRepository playerJpaRepository;

    @Mock
    private ModelMapper modelMapper;
    @Test
    void getPlayerById() {

        // Arrange
        Long playerId = 1L;
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(playerId);
        playerEntity.setUserName("testuser");

        Player expectedPlayer = new Player();
        expectedPlayer.setId(playerId);
        expectedPlayer.setUserName("testuser");

        when(playerJpaRepository.getReferenceById(playerId)).thenReturn(playerEntity);
        when(modelMapper.map(playerEntity, Player.class)).thenReturn(expectedPlayer);

        // Act
        Player result = playerService.getPlayerById(playerId);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUserName());
        assertEquals(playerId, result.getId());
    }

    @Test
    void getPlayerResponseDTOById() {
    }

    @Test
    void updatePlayerBalance() {
    }

    @Test
    void createNewPlayer() {
    }
}
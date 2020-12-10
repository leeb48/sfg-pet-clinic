package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {


    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    final String lastName = "lastName";
    final Long ownerId = 1L;
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(lastName)).thenReturn(returnOwner);

        Owner owner = service.findByLastName(lastName);

        assertEquals(lastName, owner.getLastName());

        verify(ownerRepository).findByLastName(lastName);
    }

    @Test
    void findAll() {

        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> findAllOwners = service.findAll();

        assertEquals(findAllOwners.size(), owners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(returnOwner));

        Owner foundOwner = service.findById(ownerId);

        assertEquals(returnOwner, foundOwner);
        verify(ownerRepository, times(1)).findById(ownerId);
    }


    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());

        Owner foundOwner = service.findById(ownerId);

        assertNull(foundOwner);
        verify(ownerRepository, times(1)).findById(ownerId);
    }

    @Test
    void save() {
        when(ownerRepository.save(returnOwner)).thenReturn(returnOwner);

        Owner savedOwner = service.save(returnOwner);

        assertEquals(returnOwner, savedOwner);
        verify(ownerRepository, times(1)).save(returnOwner);
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(returnOwner);
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);
        verify(ownerRepository).deleteById(ownerId);
    }
}
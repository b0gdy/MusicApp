package com.example.musicapp.Service;

import com.example.musicapp.mapper.AddressMapper;
import com.example.musicapp.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;
    

    @InjectMocks
    private AddressService addressService;

    @Test
    void deleteTest() {

        Integer id = 1;
        addressRepository.deleteById(id);

        assertThat(addressRepository.count()).isEqualTo(0);
        verify(addressRepository, times(1)).deleteById(id);

    }

    @Test
    void updateTest() {

        Integer id = 1;
        String name = "address1_test";
        addressRepository.update(id, name);

        assertThat(addressRepository.count()).isEqualTo(0);
        verify(addressRepository, times(1)).update(id, name);

    }

}

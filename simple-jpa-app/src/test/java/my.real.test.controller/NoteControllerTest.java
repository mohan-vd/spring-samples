package my.real.test.controller;

import my.real.test.config.AppDBConfig;
import my.real.test.config.AppMVCConfig;
import my.real.test.domain.Note;
import my.real.test.repo.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDBConfig.class, AppMVCConfig.class})
@WebAppConfiguration
public class NoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NoteRepository noteRepositoryMock;

    @InjectMocks
    private NoteController controller;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @Transactional
    public void getAllNotes() throws Exception {
        final Note first = new Note();
        first.setId(1);
        first.setContents("test content 1");
        first.setEmail("one@one.com");
        first.setName("test1");

        final Note second = new Note();
        second.setId(2);
        second.setContents("test content 2");
        second.setEmail("two@two.com");
        second.setName("test2");

        when(noteRepositoryMock.findAll()).thenReturn(Arrays.asList(first, second));
        mockMvc.perform(get("/notes/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(mvcResult -> {
                    final String jsonResponse = mvcResult.getResponse().getContentAsString();
                    assertTrue(jsonResponse.contains("test content 1"));
                    assertTrue(jsonResponse.contains("test content 2"));
                    assertTrue(jsonResponse.contains("test2"));
                    assertTrue(jsonResponse.contains("test1"));
                    assertTrue(jsonResponse.contains("two@two.com"));
                    assertTrue(jsonResponse.contains("one@one.com"));
                });
        verify(noteRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(noteRepositoryMock);
    }
}
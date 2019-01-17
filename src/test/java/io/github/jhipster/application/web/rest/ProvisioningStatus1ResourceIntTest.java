package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ProvisioningStatus1;
import io.github.jhipster.application.repository.ProvisioningStatus1Repository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProvisioningStatus1Resource REST controller.
 *
 * @see ProvisioningStatus1Resource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ProvisioningStatus1ResourceIntTest {

    private static final String DEFAULT_PVSID = "AAAAAAAAAA";
    private static final String UPDATED_PVSID = "BBBBBBBBBB";

    private static final String DEFAULT_SERIALNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIALNUMBER = "BBBBBBBBBB";

    @Autowired
    private ProvisioningStatus1Repository provisioningStatus1Repository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProvisioningStatus1MockMvc;

    private ProvisioningStatus1 provisioningStatus1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProvisioningStatus1Resource provisioningStatus1Resource = new ProvisioningStatus1Resource(provisioningStatus1Repository);
        this.restProvisioningStatus1MockMvc = MockMvcBuilders.standaloneSetup(provisioningStatus1Resource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProvisioningStatus1 createEntity(EntityManager em) {
        ProvisioningStatus1 provisioningStatus1 = new ProvisioningStatus1()
            .pvsid(DEFAULT_PVSID)
            .serialnumber(DEFAULT_SERIALNUMBER);
        return provisioningStatus1;
    }

    @Before
    public void initTest() {
        provisioningStatus1 = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvisioningStatus1() throws Exception {
        int databaseSizeBeforeCreate = provisioningStatus1Repository.findAll().size();

        // Create the ProvisioningStatus1
        restProvisioningStatus1MockMvc.perform(post("/api/provisioning-status-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provisioningStatus1)))
            .andExpect(status().isCreated());

        // Validate the ProvisioningStatus1 in the database
        List<ProvisioningStatus1> provisioningStatus1List = provisioningStatus1Repository.findAll();
        assertThat(provisioningStatus1List).hasSize(databaseSizeBeforeCreate + 1);
        ProvisioningStatus1 testProvisioningStatus1 = provisioningStatus1List.get(provisioningStatus1List.size() - 1);
        assertThat(testProvisioningStatus1.getPvsid()).isEqualTo(DEFAULT_PVSID);
        assertThat(testProvisioningStatus1.getSerialnumber()).isEqualTo(DEFAULT_SERIALNUMBER);
    }

    @Test
    @Transactional
    public void createProvisioningStatus1WithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provisioningStatus1Repository.findAll().size();

        // Create the ProvisioningStatus1 with an existing ID
        provisioningStatus1.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvisioningStatus1MockMvc.perform(post("/api/provisioning-status-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provisioningStatus1)))
            .andExpect(status().isBadRequest());

        // Validate the ProvisioningStatus1 in the database
        List<ProvisioningStatus1> provisioningStatus1List = provisioningStatus1Repository.findAll();
        assertThat(provisioningStatus1List).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProvisioningStatus1S() throws Exception {
        // Initialize the database
        provisioningStatus1Repository.saveAndFlush(provisioningStatus1);

        // Get all the provisioningStatus1List
        restProvisioningStatus1MockMvc.perform(get("/api/provisioning-status-1-s?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provisioningStatus1.getId().intValue())))
            .andExpect(jsonPath("$.[*].pvsid").value(hasItem(DEFAULT_PVSID.toString())))
            .andExpect(jsonPath("$.[*].serialnumber").value(hasItem(DEFAULT_SERIALNUMBER.toString())));
    }
    
    @Test
    @Transactional
    public void getProvisioningStatus1() throws Exception {
        // Initialize the database
        provisioningStatus1Repository.saveAndFlush(provisioningStatus1);

        // Get the provisioningStatus1
        restProvisioningStatus1MockMvc.perform(get("/api/provisioning-status-1-s/{id}", provisioningStatus1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(provisioningStatus1.getId().intValue()))
            .andExpect(jsonPath("$.pvsid").value(DEFAULT_PVSID.toString()))
            .andExpect(jsonPath("$.serialnumber").value(DEFAULT_SERIALNUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProvisioningStatus1() throws Exception {
        // Get the provisioningStatus1
        restProvisioningStatus1MockMvc.perform(get("/api/provisioning-status-1-s/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvisioningStatus1() throws Exception {
        // Initialize the database
        provisioningStatus1Repository.saveAndFlush(provisioningStatus1);

        int databaseSizeBeforeUpdate = provisioningStatus1Repository.findAll().size();

        // Update the provisioningStatus1
        ProvisioningStatus1 updatedProvisioningStatus1 = provisioningStatus1Repository.findById(provisioningStatus1.getId()).get();
        // Disconnect from session so that the updates on updatedProvisioningStatus1 are not directly saved in db
        em.detach(updatedProvisioningStatus1);
        updatedProvisioningStatus1
            .pvsid(UPDATED_PVSID)
            .serialnumber(UPDATED_SERIALNUMBER);

        restProvisioningStatus1MockMvc.perform(put("/api/provisioning-status-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProvisioningStatus1)))
            .andExpect(status().isOk());

        // Validate the ProvisioningStatus1 in the database
        List<ProvisioningStatus1> provisioningStatus1List = provisioningStatus1Repository.findAll();
        assertThat(provisioningStatus1List).hasSize(databaseSizeBeforeUpdate);
        ProvisioningStatus1 testProvisioningStatus1 = provisioningStatus1List.get(provisioningStatus1List.size() - 1);
        assertThat(testProvisioningStatus1.getPvsid()).isEqualTo(UPDATED_PVSID);
        assertThat(testProvisioningStatus1.getSerialnumber()).isEqualTo(UPDATED_SERIALNUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingProvisioningStatus1() throws Exception {
        int databaseSizeBeforeUpdate = provisioningStatus1Repository.findAll().size();

        // Create the ProvisioningStatus1

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvisioningStatus1MockMvc.perform(put("/api/provisioning-status-1-s")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provisioningStatus1)))
            .andExpect(status().isBadRequest());

        // Validate the ProvisioningStatus1 in the database
        List<ProvisioningStatus1> provisioningStatus1List = provisioningStatus1Repository.findAll();
        assertThat(provisioningStatus1List).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvisioningStatus1() throws Exception {
        // Initialize the database
        provisioningStatus1Repository.saveAndFlush(provisioningStatus1);

        int databaseSizeBeforeDelete = provisioningStatus1Repository.findAll().size();

        // Get the provisioningStatus1
        restProvisioningStatus1MockMvc.perform(delete("/api/provisioning-status-1-s/{id}", provisioningStatus1.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProvisioningStatus1> provisioningStatus1List = provisioningStatus1Repository.findAll();
        assertThat(provisioningStatus1List).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvisioningStatus1.class);
        ProvisioningStatus1 provisioningStatus11 = new ProvisioningStatus1();
        provisioningStatus11.setId(1L);
        ProvisioningStatus1 provisioningStatus12 = new ProvisioningStatus1();
        provisioningStatus12.setId(provisioningStatus11.getId());
        assertThat(provisioningStatus11).isEqualTo(provisioningStatus12);
        provisioningStatus12.setId(2L);
        assertThat(provisioningStatus11).isNotEqualTo(provisioningStatus12);
        provisioningStatus11.setId(null);
        assertThat(provisioningStatus11).isNotEqualTo(provisioningStatus12);
    }
}

package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ProvisioningStatus;
import io.github.jhipster.application.repository.ProvisioningStatusRepository;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProvisioningStatusResource REST controller.
 *
 * @see ProvisioningStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ProvisioningStatusResourceIntTest {

    private static final String DEFAULT_PVSID = "AAAAAAAAAA";
    private static final String UPDATED_PVSID = "BBBBBBBBBB";

    private static final String DEFAULT_SERIALNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIALNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_SITEID = 1L;
    private static final Long UPDATED_SITEID = 2L;

    private static final String DEFAULT_ENVIRONMENT = "AAAAAAAAAA";
    private static final String UPDATED_ENVIRONMENT = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATION = "AAAAAAAAAA";
    private static final String UPDATED_OPERATION = "BBBBBBBBBB";

    private static final Long DEFAULT_STATUS = 1L;
    private static final Long UPDATED_STATUS = 2L;

    private static final Long DEFAULT_LEVEL = 1L;
    private static final Long UPDATED_LEVEL = 2L;

    private static final Instant DEFAULT_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProvisioningStatusRepository provisioningStatusRepository;

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

    private MockMvc restProvisioningStatusMockMvc;

    private ProvisioningStatus provisioningStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProvisioningStatusResource provisioningStatusResource = new ProvisioningStatusResource(provisioningStatusRepository);
        this.restProvisioningStatusMockMvc = MockMvcBuilders.standaloneSetup(provisioningStatusResource)
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
    public static ProvisioningStatus createEntity(EntityManager em) {
        ProvisioningStatus provisioningStatus = new ProvisioningStatus()
            .pvsid(DEFAULT_PVSID)
            .serialnumber(DEFAULT_SERIALNUMBER)
            .username(DEFAULT_USERNAME)
            .siteid(DEFAULT_SITEID)
            .environment(DEFAULT_ENVIRONMENT)
            .operation(DEFAULT_OPERATION)
            .status(DEFAULT_STATUS)
            .level(DEFAULT_LEVEL)
            .timestamp(DEFAULT_TIMESTAMP);
        return provisioningStatus;
    }

    @Before
    public void initTest() {
        provisioningStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createProvisioningStatus() throws Exception {
        int databaseSizeBeforeCreate = provisioningStatusRepository.findAll().size();

        // Create the ProvisioningStatus
        restProvisioningStatusMockMvc.perform(post("/api/provisioning-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provisioningStatus)))
            .andExpect(status().isCreated());

        // Validate the ProvisioningStatus in the database
        List<ProvisioningStatus> provisioningStatusList = provisioningStatusRepository.findAll();
        assertThat(provisioningStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ProvisioningStatus testProvisioningStatus = provisioningStatusList.get(provisioningStatusList.size() - 1);
        assertThat(testProvisioningStatus.getPvsid()).isEqualTo(DEFAULT_PVSID);
        assertThat(testProvisioningStatus.getSerialnumber()).isEqualTo(DEFAULT_SERIALNUMBER);
        assertThat(testProvisioningStatus.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testProvisioningStatus.getSiteid()).isEqualTo(DEFAULT_SITEID);
        assertThat(testProvisioningStatus.getEnvironment()).isEqualTo(DEFAULT_ENVIRONMENT);
        assertThat(testProvisioningStatus.getOperation()).isEqualTo(DEFAULT_OPERATION);
        assertThat(testProvisioningStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProvisioningStatus.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testProvisioningStatus.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createProvisioningStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = provisioningStatusRepository.findAll().size();

        // Create the ProvisioningStatus with an existing ID
        provisioningStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProvisioningStatusMockMvc.perform(post("/api/provisioning-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provisioningStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ProvisioningStatus in the database
        List<ProvisioningStatus> provisioningStatusList = provisioningStatusRepository.findAll();
        assertThat(provisioningStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProvisioningStatuses() throws Exception {
        // Initialize the database
        provisioningStatusRepository.saveAndFlush(provisioningStatus);

        // Get all the provisioningStatusList
        restProvisioningStatusMockMvc.perform(get("/api/provisioning-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(provisioningStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].pvsid").value(hasItem(DEFAULT_PVSID.toString())))
            .andExpect(jsonPath("$.[*].serialnumber").value(hasItem(DEFAULT_SERIALNUMBER.toString())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].siteid").value(hasItem(DEFAULT_SITEID.intValue())))
            .andExpect(jsonPath("$.[*].environment").value(hasItem(DEFAULT_ENVIRONMENT.toString())))
            .andExpect(jsonPath("$.[*].operation").value(hasItem(DEFAULT_OPERATION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.intValue())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    
    @Test
    @Transactional
    public void getProvisioningStatus() throws Exception {
        // Initialize the database
        provisioningStatusRepository.saveAndFlush(provisioningStatus);

        // Get the provisioningStatus
        restProvisioningStatusMockMvc.perform(get("/api/provisioning-statuses/{id}", provisioningStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(provisioningStatus.getId().intValue()))
            .andExpect(jsonPath("$.pvsid").value(DEFAULT_PVSID.toString()))
            .andExpect(jsonPath("$.serialnumber").value(DEFAULT_SERIALNUMBER.toString()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.siteid").value(DEFAULT_SITEID.intValue()))
            .andExpect(jsonPath("$.environment").value(DEFAULT_ENVIRONMENT.toString()))
            .andExpect(jsonPath("$.operation").value(DEFAULT_OPERATION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.intValue()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProvisioningStatus() throws Exception {
        // Get the provisioningStatus
        restProvisioningStatusMockMvc.perform(get("/api/provisioning-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProvisioningStatus() throws Exception {
        // Initialize the database
        provisioningStatusRepository.saveAndFlush(provisioningStatus);

        int databaseSizeBeforeUpdate = provisioningStatusRepository.findAll().size();

        // Update the provisioningStatus
        ProvisioningStatus updatedProvisioningStatus = provisioningStatusRepository.findById(provisioningStatus.getId()).get();
        // Disconnect from session so that the updates on updatedProvisioningStatus are not directly saved in db
        em.detach(updatedProvisioningStatus);
        updatedProvisioningStatus
            .pvsid(UPDATED_PVSID)
            .serialnumber(UPDATED_SERIALNUMBER)
            .username(UPDATED_USERNAME)
            .siteid(UPDATED_SITEID)
            .environment(UPDATED_ENVIRONMENT)
            .operation(UPDATED_OPERATION)
            .status(UPDATED_STATUS)
            .level(UPDATED_LEVEL)
            .timestamp(UPDATED_TIMESTAMP);

        restProvisioningStatusMockMvc.perform(put("/api/provisioning-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProvisioningStatus)))
            .andExpect(status().isOk());

        // Validate the ProvisioningStatus in the database
        List<ProvisioningStatus> provisioningStatusList = provisioningStatusRepository.findAll();
        assertThat(provisioningStatusList).hasSize(databaseSizeBeforeUpdate);
        ProvisioningStatus testProvisioningStatus = provisioningStatusList.get(provisioningStatusList.size() - 1);
        assertThat(testProvisioningStatus.getPvsid()).isEqualTo(UPDATED_PVSID);
        assertThat(testProvisioningStatus.getSerialnumber()).isEqualTo(UPDATED_SERIALNUMBER);
        assertThat(testProvisioningStatus.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testProvisioningStatus.getSiteid()).isEqualTo(UPDATED_SITEID);
        assertThat(testProvisioningStatus.getEnvironment()).isEqualTo(UPDATED_ENVIRONMENT);
        assertThat(testProvisioningStatus.getOperation()).isEqualTo(UPDATED_OPERATION);
        assertThat(testProvisioningStatus.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProvisioningStatus.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testProvisioningStatus.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingProvisioningStatus() throws Exception {
        int databaseSizeBeforeUpdate = provisioningStatusRepository.findAll().size();

        // Create the ProvisioningStatus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProvisioningStatusMockMvc.perform(put("/api/provisioning-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(provisioningStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ProvisioningStatus in the database
        List<ProvisioningStatus> provisioningStatusList = provisioningStatusRepository.findAll();
        assertThat(provisioningStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProvisioningStatus() throws Exception {
        // Initialize the database
        provisioningStatusRepository.saveAndFlush(provisioningStatus);

        int databaseSizeBeforeDelete = provisioningStatusRepository.findAll().size();

        // Get the provisioningStatus
        restProvisioningStatusMockMvc.perform(delete("/api/provisioning-statuses/{id}", provisioningStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProvisioningStatus> provisioningStatusList = provisioningStatusRepository.findAll();
        assertThat(provisioningStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProvisioningStatus.class);
        ProvisioningStatus provisioningStatus1 = new ProvisioningStatus();
        provisioningStatus1.setId(1L);
        ProvisioningStatus provisioningStatus2 = new ProvisioningStatus();
        provisioningStatus2.setId(provisioningStatus1.getId());
        assertThat(provisioningStatus1).isEqualTo(provisioningStatus2);
        provisioningStatus2.setId(2L);
        assertThat(provisioningStatus1).isNotEqualTo(provisioningStatus2);
        provisioningStatus1.setId(null);
        assertThat(provisioningStatus1).isNotEqualTo(provisioningStatus2);
    }
}

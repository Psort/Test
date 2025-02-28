package pl.ps.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.ps.model.api.CampaignDto;
import pl.ps.model.api.CampaignEntry;
import pl.ps.model.api.Town;
import pl.ps.model.entity.CampaignEntity;
import pl.ps.model.entity.ProductEntity;
import pl.ps.model.entity.UserEntity;
import pl.ps.model.repository.CampaignRepository;
import pl.ps.model.repository.ProductRepository;
import pl.ps.model.repository.UserRepository;
import pl.ps.rest.Application;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CampaignControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CampaignRepository campaignRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@BeforeAll
	public static void setup(@Autowired UserRepository userRepository,
							 @Autowired ProductRepository productRepository,
							 @Autowired CampaignRepository campaignRepository) {
		UserEntity user = UserEntity.builder()
				.login("user1")
				.password("password1")
				.balance(200.0)
				.build();
		userRepository.save(user);

		ProductEntity product = ProductEntity.builder()
				.name("Product 1")
				.user(user)
				.build();
		productRepository.save(product);

		ProductEntity product2 = ProductEntity.builder()
				.name("Product 2")
				.user(user)
				.build();
		productRepository.save(product2);

		campaignRepository.save(CampaignEntity.builder()
				.name("Campaign")
				.keywords("Keyword")
				.bidAmount(100)
				.campaignFund(100)
				.status(false)
				.town(Town.CHICAGO)
				.radius(100)
				.product(product)
				.build());
	}

	@Test
	@Order(1)
	public void getCampaigns_ok() throws Exception {
		ProductEntity product = productRepository.findByName("Product 1");

		var result = mvc.perform(MockMvcRequestBuilders.get("/campaigns/{productId}", product.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		List<CampaignEntry> campaigns = objectMapper.readValue(result, new TypeReference<>() {});
		CampaignEntry campaign = campaigns.stream().findFirst().orElseThrow();

		assertEquals("Campaign", campaign.name());
		assertEquals("Keyword", campaign.keywords());
		assertEquals(100.0, campaign.bidAmount());
		assertFalse(campaign.status());
		assertEquals(100.0, campaign.campaignFund());
		assertEquals(Town.CHICAGO, campaign.town());
		assertEquals(100, campaign.radius());
	}

	@Test
	@Order(2)
	public void createCampaigns_ok() throws Exception {
		ProductEntity product = productRepository.findByName("Product 2");

		CampaignDto dto = CampaignDto.builder()
				.name("Campaign 1")
				.keywords("Keyword 1")
				.bidAmount(200.0)
				.campaignFund(200.0)
				.status(true)
				.town(Town.HOUSTON)
				.radius(1000)
				.productId(product.getId())
				.build();

		long countBefore = campaignRepository.count();

		var result = mvc.perform(MockMvcRequestBuilders.post("/campaigns")
						.content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		Long id = objectMapper.readValue(result, new TypeReference<>() {});
		CampaignEntity campaign = campaignRepository.findById(id).orElseThrow();

		assertEquals(countBefore + 1, campaignRepository.count());
		assertEquals(dto.name(), campaign.getName());
		assertEquals(dto.keywords(), campaign.getKeywords());
		assertEquals(dto.bidAmount(), campaign.getBidAmount());
		assertTrue(campaign.isStatus());
		assertEquals(dto.campaignFund(), campaign.getCampaignFund());
		assertEquals(dto.town(), campaign.getTown());
		assertEquals(dto.radius(), campaign.getRadius());
	}

	@Test
	@Order(3)
	public void updateCampaigns_ok() throws Exception {
		CampaignEntity campaign = campaignRepository.findByName("Campaign");

		CampaignDto dto = new CampaignDto("Updated Campaign", "Updated Keyword", 150.0, 250.0, true, Town.NEW_YORK, 500, campaign.getId());

		mvc.perform(MockMvcRequestBuilders.put("/campaigns/{id}", campaign.getId())
						.content(objectMapper.writeValueAsString(dto))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		CampaignEntity updatedCampaign = campaignRepository.findById(campaign.getId()).orElseThrow();
		assertEquals("Updated Campaign", updatedCampaign.getName());
	}

	@Test
	@Order(4)
	public void deleteCampaigns_ok() throws Exception {
		CampaignEntity campaign = campaignRepository.findByName("Campaign");
		long countBefore = campaignRepository.count();

		mvc.perform(MockMvcRequestBuilders.delete("/campaigns/{id}", campaign.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		assertEquals(countBefore - 1, campaignRepository.count());
	}
}

package pl.ps.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ps.model.api.CampaignDto;
import pl.ps.model.api.CampaignEntry;
import pl.ps.service.CampaignService;

import java.util.Set;

/**
 * @author Piotr Skowron
 */
@RestController
@RequestMapping(path = "/campaigns", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignController {

	private final CampaignService service;

	@Autowired
	public CampaignController(CampaignService service) {
		this.service = service;
	}

	@GetMapping("/{productId}")
	public Set<CampaignEntry> getCampaigns(@PathVariable Long productId) {
		return service.get(productId);
	}

	@PostMapping
	public Long creteCampaigns(@RequestBody CampaignDto dto) {
		return service.create(dto);
	}

	@DeleteMapping("/{id}")
	public void deleteCampaigns(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
	public void updateCampaigns(@PathVariable Long id, @RequestBody CampaignDto dto) {
		service.update(id, dto);
	}
}

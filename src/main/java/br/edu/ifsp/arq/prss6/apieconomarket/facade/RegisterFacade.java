package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Address;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Schedule;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.AddressRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.BrandRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.CategoryRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.MarketRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ProductRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ScheduleRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsFunc;

@Service
public class RegisterFacade {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private MarketRepository marketRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	

	public Long saveCategory(Category category) {
		String name = category.getName();
		category.setSearchName(UtilsFunc.treatSearchName(name));
		
		return categoryRepository.save(category).getId();
	}

	public Category updateCategory(Category category) {
		String name = category.getName();
		category.setSearchName(UtilsFunc.treatSearchName(name));
		
		return categoryRepository.save(category);
	}

	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

	public Long saveBrand(Brand brand) {
		String brandName = brand.getBrandName();
		brand.setSearchName(UtilsFunc.treatSearchName(brandName));
		
		return brandRepository.save(brand).getId();
	}

	public Brand updateBrand(Brand brand) {
		String brandName = brand.getBrandName();
		brand.setSearchName(UtilsFunc.treatSearchName(brandName));
		
		return brandRepository.save(brand);
	}

	public void deleteBrand(long id) {
		brandRepository.deleteById(id);
	}

	public Long saveMarket(Market market) {
		Address address = null;
		if(market.getAddress() != null) {
			address = addressRepository.save(market.getAddress());
		}
		
		List<Schedule> schedules = market.getSchedules();
		
		market.setSchedules(null);
		market.setAddress(address);
		Market marketSaved = marketRepository.save(market);
		
		if(schedules != null && schedules.size() > 0) {
			schedules.forEach(s -> {
				s.setMarket(marketSaved);
				scheduleRepository.save(s);
			});
		}
		
		return marketSaved.getId();
	}

	public Market updateMarket(Market market) {
		if(market.getId() == null) {
			throw new EntityNotFoundException("O Mercado não foi encontrado no banco.");
		}
		
		if(marketRepository.findById(market.getId()) == null) {
			throw new EntityNotFoundException("O Mercado não foi encontrado no banco.");			
		}
		
		Address address = null;
		if(market.getAddress() != null) {
			address = addressRepository.save(market.getAddress());
		}
		
		List<Schedule> schedules = market.getSchedules();

		market.setSchedules(null);
		market.setAddress(address);
		Market marketSaved = marketRepository.save(market);
		
		List<Schedule> savedSchedules = new ArrayList<>();
		if(schedules != null && schedules.size() > 0) {
			schedules.forEach(s -> {
				s.setMarket(marketSaved);
				savedSchedules.add(scheduleRepository.save(s));
			});
		}
		
		marketSaved.setSchedules(savedSchedules);
		return marketSaved;
	}

	public void deleteMarket(Long id) {
		marketRepository.deleteById(id);
	}

	public Long saveProduct(Product product) {
		product.setSearchName(UtilsFunc.deleteAllWhiteSpaces(product.getName()));
		return productRepository.save(product).getId();
	}

	public ProductDTO updateProduct(Product product) {
		return modelMapperUtil.productModelToDTO(productRepository.save(product));
	}

	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
}

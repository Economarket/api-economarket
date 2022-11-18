package br.edu.ifsp.arq.prss6.apieconomarket.utils;

public class EndpointsConstMapping {
	
	public static class AuthEP {
		public static final String MAIN = "/auth";
		public static final String LOGIN = "/auth/login"; //Endpoint auto-configurado pelo Spring Security
		public static final String LOGOUT = "/logout";
		public static final String REFRESH_TOKEN = "/token/refresh";				
	}
	
	public class BrandEP {
		public static final String MAIN = "/brand";				
		public static final String BY_ID = MAIN + "/{id}";				
		public static final String BY_NAME = MAIN + "/name";				
	}
	
	public class CategoryEP {
		public static final String MAIN = "/category";		
		public static final String BY_ID = MAIN + "/{id}";	
	}
	
	public class FieldUtilsEP {
		public static final String MAIN = "/fieldutils";
		public static final String UNITY = "/unity";
		public static final String BRAND = "/brand";
	}
	
	public class MarketEP {
		public static final String MAIN = "/market";
		public static final String BY_ID = MAIN + "/{id}";	
		public static final String DISTANCE = MAIN + "/distance";
	}
	
	public class PermissionEP {
		public static final String MAIN = "/permission";
		public static final String BY_ID = MAIN + "/{id}";
	}
	
	public class ProductEP {
		public static final String MAIN = "/product";		
		public static final String BY_ID = MAIN + "/{id}";
		public static final String BY_NAME = MAIN + "/name";
		public static final String BY_MARKET = MAIN + "/market/{id}";
		public static final String BY_CATEGORY = MAIN + "/category/{id}";
		public static final String BY_PRICE_RANGE = MAIN + "/pricerange";
	}
	
	public class RegisterEP {
		public static final String MAIN = "/register";
	}
	
	public class SearchEP {
		public static final String MAIN = "/search";
	}
	
	public class ShoppingEP {
		public static final String MAIN = "/shopping";
		public static final String BY_ID = "/{id}";
	}
	
	public class ProductListEP {
		public static final String MAIN = "/productList";
		public static final String BY_ID = "/{id}";
	}
	
	public class UserEP {
		public static final String MAIN = "/user";
		public static final String BY_ID = "/{id}";
		public static final String BY_USER_ID = MAIN + "/{userId}";
	}
	
}

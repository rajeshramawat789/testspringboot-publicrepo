package com.test.constants;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
public class UrlMappingConstant {

    private UrlMappingConstant(){}

    public static final String API_BASE_PATH = "/api/v1";

    public static final String USER = API_BASE_PATH + "/user";

    public static final String GET_USER = API_BASE_PATH + "/user/{userId}";

    public static final String UPDATE_USER = API_BASE_PATH + "/user";

    public static final String DELETE_USER = API_BASE_PATH + "/user/{userId}";

}

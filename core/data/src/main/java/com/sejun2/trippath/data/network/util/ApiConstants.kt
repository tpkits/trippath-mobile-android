package com.sejun2.trippath.data.network.util

object ApiConstants {
    // TripPath api base-url
    const val BASE_URL = "https://api-trippath.onrender.com/"
//    const val BASE_URL_DEV = "https://dev-api.trippath.com/"
//    const val BASE_URL_STAGING = "https://staging-api.trippath.com/"
    
    // API Endpoints
    object Endpoints {
        const val AUTH = "auth"
        const val USERS = "users"
        const val TRIPS = "trips"
        const val PLACES = "places"
        const val REVIEWS = "reviews"
    }
    
    // Headers
    object Headers {
        const val AUTHORIZATION = "Authorization"
        const val CONTENT_TYPE = "Content-Type"
        const val ACCEPT = "Accept"
        const val USER_AGENT = "User-Agent"
    }
    
    // Content Types
    object ContentType {
        const val APPLICATION_JSON = "application/json"
        const val MULTIPART_FORM_DATA = "multipart/form-data"
    }
    
    // HTTP Status Codes
    object HttpStatusCode {
        const val OK = 200
        const val CREATED = 201
        const val NO_CONTENT = 204
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val INTERNAL_SERVER_ERROR = 500
    }
    
    // Network Timeouts (in seconds)
    object Timeout {
        const val CONNECT = 30L
        const val READ = 30L
        const val WRITE = 30L
    }
}
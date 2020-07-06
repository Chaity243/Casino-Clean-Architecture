package com.chaitanya.data.remote.config

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

object NetworkConfig {
    // Error Codes

    /*1xx: Information*/
    const val RESPONSE_CODE_NO_DATA = 101 // Locally defined : No data

    /*2xx: Successful*/
    const val RESPONSE_CODE_SUCCESS = 200        // Success

    /*4xx: Client Error*/
    const val RESPONSE_CODE_DEACTIVATED = 403  // Forbidden
    const val RESPONSE_CODE_NOT_FOUND = 404
    const val RESPONSE_CODE_REQUEST_TIMEOUT = 408
    const val RESPONSE_CODE_UNEXPECTED = 444    // No Response
    const val RESPONSE_CODE_NO_CONTENT = 204

    /*5xx: Server Error*/
    const val RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500 // Unknown Error
    const val RESPONSE_CODE_NOT_IMPLEMENTED_ERROR = 501 // Unknown Error

    const val RESPONSE_CODE_GENERAL_UNKNOWN_ERROR = 600
    const val RESPONSE_CODE_GENERAL_SYSTEM_UNAVAILABLE_ERROR = 601
    const val RESPONSE_CODE_GENERAL_TRY_AGAIN_LATER_ERROR = 602
    const val RESPONSE_CODE_GENERAL_NETWORK_ERROR = 603

    // Error Messages
    const val ERROR_MESSAGE_DEACTIVATED = "Access Denied!"
    const val ERROR_MESSAGE_TIMEOUT_ERROR =
        "The request took longer than the server was prepared to wait"

    const val ERROR_MESSAGE_NOT_FOUND = "No Response Found!"
    const val ERROR_DATA_NOT_FOUND = "No Data Found!"
    const val ERROR_MESSAGE_INTERNAL_SERVER_ERROR =
        "The request was not completed. The server met an unexpected condition!"
    const val ERROR_MESSAGE_NOT_IMPLEMENTED_ERROR =
        "The request was not completed. The server did not support the functionality required!"
    const val ERROR_CODE_NO_FOUND = "No Content Found!"

    const val ERROR_MESSAGE_TRY_AGAIN_LATER = "Oops! We hit an error. Try again later."
    const val ERROR_MESSAGE_UNEXPECTED = "No Content Found!"
    const val ERROR_MESSAGE_SYSTEM_UNAVAILABLE =
        "System temporarily unavailable, please try again later"
    const val ERROR_MESSAGE_GENERAL_UNKNOWN = "Something went wrong!"
    const val ERROR_MESSAGE_GENERAL_NETWORK =
        "Oh! You are not connected to a wifi or cellular data network. Please connect and try again"
}